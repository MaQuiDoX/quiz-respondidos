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
            Boolean nameAvailable = db.searchUserName(nombre);
            if (nameAvailable) {
                salir = true;
                System.out.println("Nombre disponible. Puede ingresar la contraseña.");
            } else {
                System.out.println("Nombre no disponible. Intente con otro.");
            }
        }
        System.out.println("Ingrese la contrasena:");
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
        // addUsuarioDB(jugador, contrasena);
        return jugador;
    }
    /*public Jugador loadUsuario(String nombreDB, String contrasenaDB){
        ConnectDB db = new ConnectDB();
        Connection connection = db.getConnection();
        System.out.println("ESTAMOS EN CARGAR USUARIO JAJAJAJAJJAS QUE LOCOS");
        int puntajeDB = 0;
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet nombreInDB = statement.executeQuery("SELECT * FROM usuarios WHERE nombre = '"+nombreDB+"'");
                ResultSet contrasenaInDB = statement.executeQuery("SELECT * FROM usuarios WHERE contrasena = '"+contrasenaDB+"'");
                if (nombreInDB.next() && contrasenaInDB.next()) {
                    System.out.println("Usuario cargado correctamente");
                    System.out.println(nombreInDB.getString("nombre")+"+"+nombreInDB.getString("contrasena")+"+"+nombreInDB.getInt("puntaje"));
                    puntajeDB = nombreInDB.getInt("puntaje");
                }
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                db.closeConnection();
            }
        } else {
            System.out.println("Conexion a la base de datos fallida, no se puede cargar el usuario");
            // -----$%&$%&-----$%&$%&-----$%&$%&----- Hacer que salga de acá xd -----$%&$%&-----$%&$%&-----$%&$%&-----
        }
        return new Jugador(nombreDB,puntajeDB);
    }
    public void addUsuarioDB(Jugador jugador, String contrasenaDB){
        // Preparar variables para insertar dentro de la base de datos
        String nombreDB = jugador.getNombre();
        int puntajeDB = jugador.getPuntaje();
        int rachaDB = jugador.getRacha();

        // Creo objeto base de datos
        ConnectDB db = new ConnectDB();
        // Creo el objeto y lo conecto a la base de datos con el nombre "mydb.db"
        Connection connection = db.getConnection();

        // Si se encuentra una base de datos habil podemos ingresar, en caso contrario no se da una conexión
        if (connection != null) {
            try {
                // Instancio el objeto con el cual operaremos en la base de datos
                Statement statement = connection.createStatement();

                // Creo una tabla llamada students si no existe previamente
                String createTable = "CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY, nombre TEXT, contrasena TEXT, puntaje INTEGER, racha INTEGER)";
                statement.executeUpdate(createTable);

                System.out.println("-----------------TESTEOS-----------------");
                // Busca todas las apariciones de nombreDB dentro de la base de datos
                ResultSet namesInDB = statement.executeQuery("SELECT * FROM usuarios WHERE nombre = '"+nombreDB+"'");
                // Verifica si el nombre ya esta utilizado, de no ser así lo anade a la data base
                if (!namesInDB.next()) {
                    statement.executeUpdate("INSERT INTO usuarios (nombre, contrasena, puntaje, racha) VALUES ('"+nombreDB+"', '"+contrasenaDB+"', '"+puntajeDB+"', '"+rachaDB+"')");
                    System.out.println("ENTRE EN EL CONDICIONAL AHGHHAGAGHGA");
                }
                System.out.println("--------ACA TIENE QUE PASAR ALGO---------");
                System.out.println("--------------FIN TESTEOS----------------");

                // IMPRIMIR INFO DE LA BASE DE DATOS
                // Copio la base de datos todos los elementos
                ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios");

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

                // Update data
                // Copiadisimo, edita elementos de la lista a partir de ID
                //statement.executeUpdate(“UPDATE students SET name = ‘Johnny’ WHERE id = 1”);
                //System.out.println(“Data Updated”);

                // Close resources
                resultSet.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                db.closeConnection();
            }
        } else {
            System.out.println("Conexion a la base de datos fallida");
        }
    }*/
}
