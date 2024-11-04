import DAOs.UsuariosDAO;
import Game.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import utilities.Libreria;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Usuarios {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Logros.class, new LogrosTypeAdapter())
            .create();
    public static Jugador registerUsuario() throws Exception {
        UsuariosDAO db = new UsuariosDAO();
        Scanner sc = new Scanner(System.in);
        Boolean salir = false;

        String nombre = "";
        String contrasena = "";

        System.out.println("Ingrese su nombre de usuario:");
        while(!salir){
            nombre = sc.nextLine();
            boolean nameAvailable = !db.existUserName(nombre); // Invertimos el resultado porque si existe queremos que no este disponible y viceversa.
            if (nameAvailable) {
                salir = true;
                System.out.println("Nombre disponible. Puede ingresar la contraseña.");
            } else {
                System.out.println("Nombre no disponible. Intente con otro.");
            }
        }
        System.out.println(" ");

        System.out.println("Ingrese su contrasena:");
        salir = false;
        while(!salir){
            contrasena = sc.nextLine();
            System.out.println("Confirme su contrasena:");
            String contrasenaConfirm = sc.nextLine();
            if (Objects.equals(contrasena, contrasenaConfirm)){
                salir = true;
                System.out.println("Contraseña confirmada. Su usuario ha sido creado.");
            } else {
                System.out.println("Las contraseñas no coinciden. Intente de nuevo.");
            }
        }
        System.out.println(" ");
        Jugador jugador = new Jugador(nombre, contrasena,0,new ArrayList<>());
        Gson gson = new Gson();
        db.addUser(jugador.getNombre(), jugador.getContrasena(), jugador.getPuntaje(), gson.toJson(jugador.getLogros()));
        return jugador;
    }

    public Jugador logUsuario() throws Exception {
        UsuariosDAO db = new UsuariosDAO();
        Scanner scanner = new Scanner(System.in);
        int puntaje = 0;
        String logros;
        System.out.println(" ");
        System.out.println("Ingrese su nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.println(" ");
        System.out.println("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();
        String nameInDB = db.searchUserName(nombre);
        String passwordInDB = db.searchUserPassword(nombre, contrasena);
        if (nameInDB!=null && passwordInDB!=null) {
            System.out.println(" ");
            System.out.println("Sesión iniciada correctamente");
            System.out.println(" ");
            puntaje = db.searchUserScore(nombre);
            logros = db.searchUserLogros(nombre);
            ArrayList<Logros> logrosDBarray;
            logrosDBarray = reinstanciarLogros(logros);
            return new Jugador(nombre, contrasena, puntaje, logrosDBarray);
        } else {
            System.out.println(" ");
            System.out.println("Usuario y/o contraseña no válidos");
            System.out.println(" ");
            System.out.println("1. Intentar nuevamente");
            System.out.println("2. Crear nuevo usuario \n ");
            int opcion = Libreria.catchInt(1, 2);
            if (opcion == 1) {
                return logUsuario(); // Aquí retorna el resultado de logUsuario()
            } else {
                return registerUsuario();
            }
        }
    }

    public ArrayList<Jugador> loadAllUsuarios() throws Exception {
        UsuariosDAO db = new UsuariosDAO();
        try {
            db.searchAllUsers();
            ResultSet rs = db.getResultset();

            ArrayList<Jugador> arrayList = new ArrayList<>();
            while(rs.next()){
                String nombre = rs.getString("nombre");
                String contrasena = rs.getString("contrasena");
                int puntaje = rs.getInt("puntaje");
                String logros = rs.getString("logros");
                Jugador newPlayer = new Jugador(nombre, contrasena,puntaje,reinstanciarLogros(logros));
                arrayList.add(newPlayer);
            }
            return arrayList;
        } catch (Exception ex) {
            System.out.println("Error al cargar todos los usuarios");
            throw ex;
        } finally {
            db.disconnectDB();
        }
    }

    public void actualizarLogrosBase(Jugador jugador) throws Exception {
        UsuariosDAO db = new UsuariosDAO();
        ArrayList<Logros> logrosList = jugador.getLogros();
        String logrosText = gson.toJson(logrosList);
        db.updateUserLogros(jugador.getNombre(), logrosText);
    }

    public void actualizarPuntosUsuario(Jugador jugador) throws Exception {
        UsuariosDAO db = new UsuariosDAO();

        db.updateUserPoints(jugador.getNombre(),jugador.getPuntaje());
    }
    // ++++++++++++++++++++++++++++++++++++
    // ++++++ ¿¿BORRAR ESTE METODO?? ++++++
    // ++++++++++++++++++++++++++++++++++++
    public void printUsuarios() throws Exception {
        UsuariosDAO db = new UsuariosDAO();

        db.consultarDB("SELECT * FROM usuarios");

        ResultSet resultSet = db.getResultset();

        System.out.println("BASE DE DATOS DE USUARIOS");
        System.out.println("ID\tNombre\tContrasena   \tPuntaje\tRacha\t");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String pregunta = resultSet.getString("nombre");
            String respuestacorrecta = resultSet.getString("contrasena");
            String respuestafallida1 = resultSet.getString("puntaje");
            String respuestafallida2 = resultSet.getString("racha");
            System.out.println(id + "\t" + pregunta + "\t" + respuestacorrecta + "\t" + respuestafallida1 + "\t" + respuestafallida2 + "\t");
        }
    }
    // ++++++++++++++++++++++++++++++++++++

    public ArrayList<Logros> reinstanciarLogros(String logros) throws Exception {
        Type listType = new TypeToken<ArrayList<Logros>>() {}.getType();
        return gson.fromJson(logros, listType);
    }
}