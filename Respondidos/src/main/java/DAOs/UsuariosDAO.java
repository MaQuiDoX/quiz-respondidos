package DAOs;

import Game.Jugador;
import com.google.gson.Gson;

import java.sql.ResultSet;

public class UsuariosDAO extends DataBaseDAO{

    public UsuariosDAO() throws Exception{
        // Crea la tabla usuarios si es que no existe
        String createTable = "CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY, nombre TEXT, contrasena TEXT, puntaje INTEGER, logros TEXT)";
        this.actualizarDB(createTable);
    }

    // ----- POR MOTIVOS DE TESTEOS ES ESTE MÉTODO -----
    public void borrarBaseDatosUsuarios() throws Exception {
        this.actualizarDB("DELETE FROM usuarios");
    }

    public ResultSet searchAllUsers() throws Exception{
        try{
            consultarDB("SELECT * FROM usuarios");
            return this.resultset;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean existUserName(String nameDB) throws Exception {
        try {
            String sql = "SELECT nombre FROM usuarios "
                    + " WHERE nombre = '" + nameDB + "'";
            consultarDB(sql);
            if (resultset.next()) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw ex;
        } finally {
            disconnectDB();
        }
    }

    public String searchUserName(String nameDB) throws Exception {
        try {
            String sql = "SELECT * FROM usuarios "
                    + " WHERE nombre = '" + nameDB + "'";
            consultarDB(sql);
            if (resultset.next()) {
                return this.resultset.getString("nombre");
            }
            return null;
        } catch (Exception ex) {
            throw ex;
        } finally {
            disconnectDB();
        }
    }

    public String searchUserPassword(String nameDB, String passwordDB) throws Exception {
        try {
            String sql = "SELECT contrasena FROM usuarios "
                    + " WHERE nombre = '" + nameDB + "' AND contrasena = '" + passwordDB + "'";
            consultarDB(sql);
            if (resultset.next()) {
                return this.resultset.getString("contrasena");
            }
            return null;
        } catch (Exception ex) {
            throw ex;
        } finally {
            disconnectDB();
        }
    }

    public int searchUserScore(String nameDB) throws Exception {
        try {
            String sql = "SELECT puntaje FROM usuarios "
                    + " WHERE nombre = '" + nameDB + "'";
            consultarDB(sql);
            if (resultset.next()) {
                return this.resultset.getInt("puntaje");
            }
            return 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            disconnectDB();
        }
    }

    public String searchUserLogros(String nameDB) throws Exception {
        try {
            String sql = "SELECT logros FROM usuarios "
                    + " WHERE nombre = '" + nameDB + "'";
            consultarDB(sql);
            if (resultset.next()) {
                return this.resultset.getString("logros");
            }
            return null;
        } catch (Exception ex) {
            throw ex;
        } finally {
            disconnectDB();
        }
    }

    public void updateUserLogros(String nombre, String logros) throws Exception {
        try {
            String sql = "UPDATE usuarios SET logros = '"+logros
                    + "' where nombre = '"+nombre+"'";
            actualizarDB(sql);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void updateUserPoints(String nombre, int puntaje) throws Exception{
        try {
            String sql = "UPDATE usuarios SET puntaje = '" + puntaje + "' WHERE nombre = '" + nombre + "'";
            actualizarDB(sql);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void addUser(String nameDB, String passwordDB, int scoreDB, String logrosDB) throws Exception {
        try {
            String sql = "INSERT INTO usuarios (nombre, contrasena, puntaje, logros) " +
                    "VALUES ('"+nameDB+"', '"+passwordDB+"', '"+scoreDB+"', '"+logrosDB+"')";
            actualizarDB(sql);
        } catch (Exception ex) {
            throw ex;
        }
    }
}