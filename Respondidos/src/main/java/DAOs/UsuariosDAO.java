package DAOs;

public class UsuariosDAO extends DataBaseDAO{

    public boolean searchUserName(String nameDB) throws Exception {
        try {
            String sql = "SELECT * FROM usuarios "
                    + " WHERE nombre = '" + nameDB + "'";

            consultarDB(sql);

            while (resultset.next()) {
                return true;
            }
            disconnectDB();
            return false;
        } catch (Exception ex) {
            disconnectDB();
            throw ex;
        }
    }
}
