import DAOs.UsuariosDAO;
import Game.*;
import com.google.gson.Gson;
import utilities.Libreria;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Usuarios {

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

        Jugador jugador = new Jugador(nombre, 0);
        addUsuarioDB(jugador, contrasena);
        return jugador;
    }
    public Jugador logUsuario() throws Exception {
        UsuariosDAO db = new UsuariosDAO();
        Scanner scanner = new Scanner(System.in);
        int puntajeDB = 0;
        System.out.println(" ");
        System.out.println("Ingrese su nombre de usuario: ");
        String nombreDB = scanner.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String contrasenaDB = scanner.nextLine();

        ResultSet nameInDB = db.searchUserName(nombreDB);
        ResultSet passwordInDB = db.searchUserPassword(nombreDB, contrasenaDB);
        if (nameInDB!=null && passwordInDB!=null) {
            System.out.println("Sesión iniciada correctamente");
            puntajeDB = nameInDB.getInt("puntaje");
            // TESTEO
            System.out.println(nameInDB.getString("nombre") + "+" + passwordInDB.getString("contrasena") + "+" + nameInDB.getInt("puntaje"));
            return new Jugador(nombreDB, puntajeDB);
        } else {
            System.out.println(" ");
            System.out.println("Usuario y/o contraseña no válidos");
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

        db.searchAllUsers();
        ResultSet rs = db.getResultset();

        ArrayList<Jugador> arrayList = new ArrayList<>();
        //System.out.println("++++++++++"); // TESTEOS TESTEOS
        while(rs.next()){
            String nombre = rs.getString("nombre");
            String contrasena = rs.getString("contrasena");
            int puntaje = rs.getInt("puntaje");
            //System.out.println("+++++ Name: "+nombre+" +++++ Password: "+contrasena+" +++++ Score: "+puntaje); // TESTEOS TESTEOS
            Jugador newPlayer = new Jugador(nombre, puntaje);
            arrayList.add(newPlayer);
        }
        //System.out.println("++++++++++"); // TESTEOS TESTEOS
        return arrayList;
    }
    public void actualizarLogrosBase(Jugador jugador) throws Exception {
        UsuariosDAO db = new UsuariosDAO();
        Gson gson = new Gson();
        String logrosText = gson.toJson(jugador.getLogros());
        db.updateUserLogros(jugador.getNombre(), logrosText);
    }

    // LLEVAR ESTO A UsuariosDAO!!!!!
    public static void addUsuarioDB(Jugador jugador, String contrasenaDB) throws Exception {
        String nombreDB = jugador.getNombre();
        int puntajeDB = jugador.getPuntaje();
        int rachaDB = jugador.getRacha();

        UsuariosDAO db = new UsuariosDAO();

        // LLEVAR ESTE CODIGO A UsuariosDAO
        db.actualizarDB("INSERT INTO usuarios (nombre, contrasena, puntaje, racha) VALUES ('"+nombreDB+"', '"+contrasenaDB+"', '"+puntajeDB+"', '"+rachaDB+"')");
        // LLEVAR ESTE CODIGO A UsuariosDAO
    }

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
}
