package DAOs;

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


    public boolean searchUserName(String nameDB) throws Exception {
        try {
            String sql = "SELECT * FROM usuarios "
                    + " WHERE nombre = '" + nameDB + "'";
            consultarDB(sql);
            if (!resultset.next()) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw ex;
        } finally {
            disconnectDB();
        }
    }

    public boolean searchUserPassword(String passwordDB) throws Exception {
        try {
            String sql = "SELECT * FROM usuarios "
                    + " WHERE contrasena = '" + passwordDB + "'";
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
}