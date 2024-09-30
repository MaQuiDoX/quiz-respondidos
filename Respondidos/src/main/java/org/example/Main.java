package org.example;

import org.example.models.ConnectDB;

import javax.xml.transform.Result;
import java.sql.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        // Creo objeto base de datos
        ConnectDB db = new ConnectDB();

        // Creo el objeto y lo conecto a la base de datos con el nombre "mydb.db"
        Connection connection = db.getConnection();

        // Si se encuentra una base de datos habil podemos ingresar, en caso contrario no se da una conexión
        if (connection != null) {
            try {
                // Instancio el objeto con el cual operaremos en la base de datos
                Statement statement = connection.createStatement();

                // No tener en cuenta, la uso para arrancar de 0 el programa sin que se dupliquen los elementos que inserto.
                statement.executeUpdate("DROP TABLE IF EXISTS pregunta");

                // Creo una tabla llamada students si no existe previamente
                String createTable = "CREATE TABLE IF NOT EXISTS pregunta (id INTEGER PRIMARY KEY, pregunta TEXT, respuestacorrecta TEXT, respuestafallida1 TEXT, respuestafallida2 TEXT, respuestafallida3 TEXT)";
                statement.executeUpdate(createTable);

                // INSERTAR INFO A LA BASE DE DATOS
                statement.executeUpdate("INSERT INTO pregunta (pregunta, respuestacorrecta,respuestafallida1,respuestafallida2,respuestafallida3) VALUES ('Como me llamo?','Matias','Martiniano','Rodrigo','Samuel')");
                statement.executeUpdate("INSERT INTO pregunta (pregunta, respuestacorrecta,respuestafallida1,respuestafallida2,respuestafallida3) VALUES ('Como estamos?','Bien','Resfriado','Mareado','Contento')");
                statement.executeUpdate("INSERT INTO pregunta (pregunta, respuestacorrecta,respuestafallida1,respuestafallida2,respuestafallida3) VALUES ('Que dia es hoy?','Domingo','Lunes','Martes','Miercoles')");

                // IMPRIMIR INFO DE LA BASE DE DATOS

                // Copio la base de datos todos los elementos
                ResultSet resultSet = statement.executeQuery("SELECT * FROM pregunta");

                System.out.println("BASE DE DATOS DE PREGUNTAS Y RESPUESTAS");
                System.out.println("ID\tPregunta\tRespuestaC\tRespuestaF1\tRespuestaF2\tRespuestaF3");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String pregunta = resultSet.getString("pregunta");
                    String respuestacorrecta = resultSet.getString("respuestacorrecta");
                    String respuestafallida1 = resultSet.getString("respuestafallida1");
                    String respuestafallida2 = resultSet.getString("respuestafallida2");
                    String respuestafallida3 = resultSet.getString("respuestafallida3");
                    System.out.println(id + "\t" + pregunta + "\t" + respuestacorrecta + "\t" + respuestafallida1 + "\t" + respuestafallida2 + "\t" + respuestafallida3 + "\t");
                }

                // Update data
                // Copiadisimo, edita elementos de la lista a partir de ID
                //statement.executeUpdate(“UPDATE students SET name = ‘Johnny’ WHERE id = 1”);
                //System.out.println(“Data Updated”);

                // BORRAR DATA
                //statement.executeUpdate("DELETE FROM pregunta WHERE id = 1");

                System.out.println();

                // PRUEBA DE RESPUESTA DE JUEGO
                System.out.println("PRUEBA DE PREGUNTA");
                System.out.println();

                // Instancio el objeto para que pueda ser llamado todas las veces que sea necesaria ingresando solamente el valor del ID para acceder a las filas de la tabla de preguntas
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pregunta WHERE id = ?");

                // Al segundo valor le añado el ID al que deseo ingresar, randomizo un numero del 1 al 3 (solo tengo 3 preguntas en la base de datos)
                int random = new Random().nextInt(3) + 1;
                preparedStatement.setInt(1,random);

                // Accedo a la fila con el statement preparado
                ResultSet setPregunta = preparedStatement.executeQuery();

                int count = 1;
                // Realmente no se porque hay que ponerlo en un while pero sino explota
                while (setPregunta.next()) {
                    // Obtengo de la fila los valores de las columnas y las convierto en String
                    String pregunta = setPregunta.getString("pregunta");
                    System.out.println(pregunta);

                    // Aca meto las cuatro respuestas posibles en una lista para mezclarlas
                    ArrayList<String> listaRespuestas = new ArrayList<>();
                    String respuestacorrecta = setPregunta.getString("respuestacorrecta");
                    listaRespuestas.add(respuestacorrecta);
                    String respuestafallida1 = setPregunta.getString("respuestafallida1");
                    listaRespuestas.add(respuestafallida1);
                    String respuestafallida2 = setPregunta.getString("respuestafallida2");
                    listaRespuestas.add(respuestafallida2);
                    String respuestafallida3 = setPregunta.getString("respuestafallida3");
                    listaRespuestas.add(respuestafallida3);

                    // Mezcla
                    Collections.shuffle(listaRespuestas);

                    // Aca añado las respuestas mezcladas a una tupla junto a un numero asociado para la posterior resolución
                    ArrayList<Tupla> listaRespuestasTuplas = new ArrayList<>();
                    for (String respuesta : listaRespuestas) {
                        System.out.println(count + ". " + respuesta);
                        Tupla<Integer, String> tupla = new Tupla<>(count, respuesta);
                        listaRespuestasTuplas.add(tupla);
                        count++;
                    }

                    // Ingreso numero de respuesta
                    System.out.println("Ingrese respuesta correcta: ");
                    Integer respuesta = sc.nextInt();

                    // Transito las 4 tuplas comprobando que la respuesta brindada por el usuario sea la misma que la respuesta correcta (seguro hay una forma mas facil pero fue la primera que se me ocurrió)
                    for (Tupla tuplas : listaRespuestasTuplas) {
                        if (tuplas.getPrimero() == respuesta) {
                            if (tuplas.getSegundo() == respuestacorrecta) {
                                System.out.println("Respuesta correcta");
                            } else {
                                System.out.println("Respuesta fallida");
                            }
                        }
                    }
                    count = 1;
                    listaRespuestas.clear();
                    listaRespuestasTuplas.clear();
                }


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