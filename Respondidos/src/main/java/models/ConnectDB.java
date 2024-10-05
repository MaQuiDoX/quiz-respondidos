package models;

//import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private Connection connection;

    public ConnectDB(){
        String url = "jdbc:sqlite:mydb.db";
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void closeConnection(){
        try {
            if (connection != null) {
                System.out.println("Cerrando conexion");
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
