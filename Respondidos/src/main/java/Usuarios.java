import models.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Usuarios {

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
    }
}
