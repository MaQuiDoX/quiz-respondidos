package DAOs;

import java.sql.*;

public class DataBaseDAO {
    protected Connection connection;
    protected ResultSet resultset;
    protected Statement statement;

    public void connectDB() throws Exception {
        String url = "jdbc:sqlite:mydb.db";
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void disconnectDB() throws Exception {
        try {
            if (resultset != null) {
                System.out.println("Cerrando conexion");
                resultset.close();
            }
            if (statement != null) {
                System.out.println("Cerrando conexion");
                statement.close();
            }
            if (connection != null) {
                System.out.println("Cerrando conexion");
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

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

    public Connection getConnection() {
        return connection;
    }

    public ResultSet getResultset() {
        return resultset;
    }

    public Statement getStatement() {
        return statement;
    }
}
