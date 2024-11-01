package DAOs;

import java.sql.ResultSet;

public class UsuariosDAO extends DataBaseDAO{

    public UsuariosDAO() throws Exception{
        // Crea la tabla usuarios si es que no existe
        String createTable = "CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY, nombre TEXT, contrasena TEXT, puntaje INTEGER, racha INTEGER)";
        this.actualizarDB(createTable);
    }

    // ----- POR MOTIVOS DE TESTEOS ES ESTE MÉTODO -----
    public void borrarBaseDatosUsuarios() throws Exception {
        this.actualizarDB("DELETE FROM usuarios");
    }

    // HACER UNA FORMA DE TRAER TODA LA TABLA EN UNA BUSQUEDA Y NO INVOLUCRAR ESTE SELECT nombre FROM...
    public boolean existUserName(String nameDB) throws Exception {
        try {
            String sql = "SELECT * FROM usuarios "
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

    public ResultSet searchUserName(String nameDB) throws Exception {
        try {
            String sql = "SELECT * FROM usuarios "
                    + " WHERE nombre = '" + nameDB + "'";
            consultarDB(sql);
            if (resultset.next()) {
                return this.resultset;
            }
            return null;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public ResultSet searchUserPassword(String nameDB, String passwordDB) throws Exception {
        try {
            String sql = "SELECT contrasena FROM usuarios "
                    + " WHERE nombre = '" + nameDB + "' AND contrasena = '" + passwordDB + "'";
            consultarDB(sql);
            if (resultset.next()) {
                return this.resultset;
            }
            return null;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public ResultSet searchAllUsers() throws Exception{
        try{
            consultarDB("SELECT * FROM usuarios");
            return this.resultset;
        } catch (Exception ex) {
            throw ex;
        } finally{
            disconnectDB();
        }
    }
}