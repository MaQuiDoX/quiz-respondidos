package DAOs;

import Game.Jugador;
import com.google.gson.Gson;

import java.sql.ResultSet;

/**
 * Clase que maneja todas las operaciones relacionadas con los usuarios en la base de datos.
 * @author Giraudo Ignacio
 * @author Martins Ezequiel
 * @author Quesada Manuel
 */
public class UsuariosDAO extends DataBaseDAO{

    /**
     * Constructor de la clase UsuariosDAO.
     * Inicializa y crea la tabla "usuarios" en la base de datos si no existe.
     *
     * @throws Exception si ocurre algún error durante la creación de la tabla.
     */
    public UsuariosDAO() throws Exception{
        // Crea la tabla usuarios si es que no existe
        String createTable = "CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY, nombre TEXT, contrasena TEXT, puntaje INTEGER, logros TEXT)";
        this.actualizarDB(createTable);
    }

    /**
     * Realiza una consulta en la base de datos para buscar todos los usuarios.
     *
     * @return un ResultSet que contiene todos los registros de los usuarios obtenidos de la base de datos.
     * @throws Exception si ocurre un error durante la consulta a la base de datos.
     */
    public ResultSet searchAllUsers() throws Exception{
        try{
            consultarDB("SELECT * FROM usuarios");
            return this.resultset;
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Chequea si un nombre de usuario ya existe dentro de la base de datos.
     *
     * @param nameDB el nombre de usuario que chequea en la base de datos.
     * @return True si el nombre de usuario exsite, false en caso contrario.
     * @throws Exception si hay un error durante la consulta o conexión de la base de datos.
     */
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

    /**
     * Busca un nombre de usuario en la base de datos usando el nombre proporcionado.
     *
     * @param nameDB El nombre del usuario a buscar en la base de datos.
     * @return El nombre del usuario si se encuentra en la base de datos, o null si el usuario no existe.
     * @throws Exception Si hay un error durante la consulta o conexión de la base de datos.
     */
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

    /**
     * Busca la contraseña de un usuario en la base de datos usando el nombre de usuario y la contraseña proporcionados.
     *
     * @param nameDB el nombre del usuario en la base de datos
     * @param passwordDB la contraseña del usuario en la base de datos
     * @return la contraseña encontrada si el usuario existe y la contraseña coincide, o null si el usuario no existe o la contraseña es incorrecta
     * @throws Exception si ocurre un error durante la consulta o conexión de base de datos
     */
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

    /**
     * Este método busca la puntuación de un usuario en la base de datos usando el nombre de usuario proporcionado.
     *
     * @param nameDB el nombre de usuario a buscar en la base de datos.
     * @return la puntuación del usuario si se encuentra el usuario, o 0 si el usuario no existe.
     * @throws Exception si hay un error durante la consulta o conexión de la base de datos.
     */
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

    /**
     * Busca los logros de un usuario en la base de datos utilizando el nombre de usuario proporcionado.
     *
     * @param nameDB el nombre del usuario a buscar en la base de datos.
     * @return los logros del usuario si se encuentra en la base de datos, o null si el usuario no existe.
     * @throws Exception si ocurre un error durante la consulta o desconexión de la base de datos.
     */
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

    /**
     * Actualiza los logros de un usuario en la base de datos.
     *
     * @param nombre El nombre del usuario cuyo registro debe ser actualizado.
     * @param logros Los logros nuevos que deben ser asociados al usuario.
     * @throws Exception Si ocurre un error durante la actualización de la base de datos.
     */
    public void updateUserLogros(String nombre, String logros) throws Exception {
        try {
            String sql = "UPDATE usuarios SET logros = '"+logros
                    + "' where nombre = '"+nombre+"'";
            actualizarDB(sql);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Actualiza los puntos de un usuario en la base de datos.
     *
     * @param nombre El nombre del usuario cuyos puntos deben ser actualizados.
     * @param puntaje La nueva puntuación que debe ser establecida para el usuario.
     * @throws Exception Si ocurre un error durante la actualización en la base de datos.
     */
    public void updateUserPoints(String nombre, int puntaje) throws Exception{
        try {
            String sql = "UPDATE usuarios SET puntaje = '" + puntaje + "' WHERE nombre = '" + nombre + "'";
            actualizarDB(sql);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Añade un nuevo usuario a la base de datos.
     *
     * @param nameDB El nombre del usuario a añadir a la base de datos.
     * @param passwordDB La contraseña del usuario a añadir a la base de datos.
     * @param scoreDB El puntaje inicial del usuario a añadir a la base de datos.
     * @param logrosDB Los logros iniciales del usuario a añadir a la base de datos.
     * @throws Exception Si ocurre un error durante la inserción del usuario en la base de datos.
     */
    public void addUser(String nameDB, String passwordDB, int scoreDB, String logrosDB) throws Exception {
        try {
            String sql = "INSERT INTO usuarios (nombre, contrasena, puntaje, logros) " +
                    "VALUES ('"+nameDB+"', '"+passwordDB+"', '"+scoreDB+"', '"+logrosDB+"')";
            actualizarDB(sql);
        } catch (Exception ex) {
            throw ex;
        } finally {
            disconnectDB();
        }
    }

    /**
     * Elimina un usuario de la base de datos utilizando su nombre.
     *
     * @param nameDB El nombre del usuario a eliminar de la base de datos.
     * @throws Exception Si ocurre algún error durante la eliminación del usuario en la base de datos.
     */
    public void deleteUser(String nameDB) throws Exception {
        try {
            String sql = "DELETE FROM usuarios WHERE nombre='"+nameDB+"'";
            actualizarDB(sql);
        } catch (Exception ex) {
            throw ex;
        } finally {
            disconnectDB();
        }
    }
}