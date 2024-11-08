package DAOs;

import java.sql.*;

/**
 * Clase que sirve como 'enlace' hacía la base de datos, definiendo los comportamientos más básicos.
 * @author Martis Ezequiel
 * @author Quesada Manuel
 * @version 2.0
 */
public class DataBaseDAO {
    protected Connection connection;
    protected ResultSet resultset;
    protected Statement statement;

    /**
     * Método que realiza la conexión con la base de datos 'mydb.db'.
     * @throws Exception
     * @author Martins Ezequiel
     * @author Quesada Manuel
     */
    public void connectDB() throws Exception {
        String url = "jdbc:sqlite:mydb.db";
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Método que realiza la desconexión con la base de datos.
     * @throws Exception
     * @author Martins Ezequiel
     * @author Quesada Manuel
     */
    public void disconnectDB() throws Exception {
        try {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Método que realiza una consulta a la base de datos a través de un String y lo almacena en el atributo 'resultset'.
     * @param sql
     * @throws Exception
     * @see ResultSet
     * @see String
     * @author Martins Ezequiel
     */
    public void consultarDB(String sql) throws Exception {
        try {
            connectDB();
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Método que realiza una actualización a la base de datos a través de un String, debe especificarse cómo realizar
     * el cambio en la base de datos. Ejemplo: INSERT INTO dataBase (columnName1, columnName2, ... , columnName*)
     * VALUES ('columnValue1', 'columnValue2', ... , 'columnValue*')
     * @param sql
     * @throws Exception
     * @author Martins Ezequiel
     */
    public void actualizarDB(String sql) throws Exception {
        try {
            connectDB();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            disconnectDB();
        }
    }

    /**
     * Getter del atributo connection.
     * @return Connection
     * @see Connection
     * @author Martins Ezequiel
     * @author Quesada Manuel
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Getter del atributo resultset.
     * @return ResultSet
     * @see ResultSet
     * @author Martins Ezequiel
     */
    public ResultSet getResultset() {
        return resultset;
    }
}
