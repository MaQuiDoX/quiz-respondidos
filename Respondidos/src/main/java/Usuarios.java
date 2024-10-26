import DAOs.DataBaseDAO;
import DAOs.UsuariosDAO;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Usuarios {
    public Jugador registerUsuario() throws Exception {
        UsuariosDAO db = new UsuariosDAO();
        Scanner sc = new Scanner(System.in);
        Boolean salir = false;

        String nombre = "";
        String contrasena = "";

        System.out.println("Ingrese su nombre de usuario:");
        while(!salir){
            nombre = sc.nextLine();
            boolean nameAvailable = db.searchUserName(nombre);
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
    public Jugador loadUsuario(String nombreDB, String contrasenaDB) throws Exception {
        UsuariosDAO db = new UsuariosDAO();

        int puntajeDB = 0;

        db.searchUserName(nombreDB);
        ResultSet nombreInDB = db.getResultset();
        db.searchUserPassword(contrasenaDB);
        ResultSet contrasenaInDB = db.getResultset();
        if (nombreInDB.next() && contrasenaInDB.next()) {
            System.out.println("Usuario cargado correctamente");
            System.out.println(nombreInDB.getString("nombre")+"+"+nombreInDB.getString("contrasena")+"+"+nombreInDB.getInt("puntaje"));
            puntajeDB = nombreInDB.getInt("puntaje");
        }
        return new Jugador(nombreDB,puntajeDB);
    }

    public void addUsuarioDB(Jugador jugador, String contrasenaDB) throws Exception {
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
