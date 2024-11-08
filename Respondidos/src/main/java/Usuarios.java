import DAOs.UsuariosDAO;
import Game.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import utilities.Libreria;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * La clase Usuario es responsable de manejar operaciones relacionadas con el usuario como el Registro,
 * el Logueo, la carga de datos, y la actualizacion del mismo. Este interactua con UsuariosDAO para operar la
 * información del usuario en la database.
 * @author Giraudo Ignacio
 * @author Martins Ezequiel
 * @author Villegas Joaquín
 * @author Quesada Manuel
 */
public class Usuarios {

    /**
     * Instancia de Gson configurada para manejar la serialización y deserialización de objetos de tipo Logros.
     * Utiliza un LogrosTypeAdapter personalizado para resolver el problema de deserialización de objetos Logros abstractos,
     * permitiendo la conversión adecuada de JSON a subclases específicas de Logros.
     */
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Logros.class, new LogrosTypeAdapter())
            .create();

    /**
     * Registra un nuevo usuario solicitando al usuario un nombre de usuario y una contraseña,}
     * confirmando la contraseña y asegurándose de que el nombre de usuario sea único.
     *
     * @return un objeto Jugador recién registrado con el nombre de usuario y contraseña proporcionados.
     * @throws Exception si un error ocurre durante el Registro
     */
    public static Jugador registerUsuario() throws Exception {
        UsuariosDAO db = new UsuariosDAO();
        Scanner sc = new Scanner(System.in);
        Boolean salir = false;

        String nombre = "";
        String contrasena = "";

        System.out.println("Ingrese su nombre de usuario:");
        while(!salir){
            nombre = sc.nextLine();
            boolean nameAvailable = !db.existUserName(nombre); // Invertimos el resultado porque si existe queremos que no este disponible y viceversa.
            if (nameAvailable) {
                salir = true;
                System.out.println("Nombre disponible. Puede ingresar la contraseña.");
            } else {
                System.out.println("Nombre no disponible. Intente con otro.");
            }
        }
        System.out.println(" ");

        System.out.println("Ingrese su contrasena:");
        salir = false;
        while(!salir){
            contrasena = sc.nextLine();
            System.out.println("Confirme su contrasena:");
            String contrasenaConfirm = sc.nextLine();
            if (Objects.equals(contrasena, contrasenaConfirm)){
                salir = true;
                System.out.println("Contraseña confirmada. Su usuario ha sido creado.");
            } else {
                System.out.println("Las contraseñas no coinciden. Intente de nuevo.");
            }
        }
        System.out.println(" ");
        Jugador jugador = new Jugador(nombre, contrasena,0,new ArrayList<>());
        Gson gson = new Gson();
        db.addUser(jugador.getNombre(), jugador.getContrasena(), jugador.getPuntaje(), gson.toJson(jugador.getLogros()));
        return jugador;
    }

    /**
     * Inicia sesión para un usuario existente solicitando el nombre de usuario y la contraseña.
     * Si la autenticación es exitosa, devuelve un objeto Jugador con los detalles del jugador.
     * En caso de fallar la autenticación, permite al usuario intentar nuevamente
     * o registrar un nuevo usuario.
     *
     * @return un objeto Jugador correspondiente al usuario autenticado o recién registrado.
     * @throws Exception si ocurre un error durante el proceso de inicio de sesión o registro.
     */
    public Jugador logUsuario() throws Exception {
        UsuariosDAO db = new UsuariosDAO();
        Scanner scanner = new Scanner(System.in);
        int puntaje = 0;
        String logros;
        System.out.println(" ");
        System.out.println("Ingrese su nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.println(" ");
        System.out.println("Ingrese su contraseña: ");
        String contrasena = scanner.nextLine();
        String nameInDB = db.searchUserName(nombre);
        String passwordInDB = db.searchUserPassword(nombre, contrasena);
        if (nameInDB!=null && passwordInDB!=null) {
            System.out.println(" ");
            System.out.println("Sesión iniciada correctamente");
            System.out.println(" ");
            puntaje = db.searchUserScore(nombre);
            logros = db.searchUserLogros(nombre);
            ArrayList<Logros> logrosDBarray;
            logrosDBarray = reinstanciarLogros(logros);
            try {
                // Pausa la ejecución del programa por 1 segundo (1,000 milisegundos)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("El hilo fue interrumpido.");
            }
            return new Jugador(nombre, contrasena, puntaje, logrosDBarray);
        } else {
            System.out.println(" ");
            System.out.println("Usuario y/o contraseña no válidos");
            System.out.println(" ");
            System.out.println("1. Intentar nuevamente");
            System.out.println("2. Crear nuevo usuario \n ");
            int opcion = Libreria.catchInt(1, 2);
            if (opcion == 1) {
                return logUsuario(); // Aquí retorna el resultado de logUsuario()
            } else {
                return registerUsuario();
            }
        }
    }

    /**
     * Carga todos los usuarios de la base de datos y los devuelve como una lista de objetos Jugador.
     *
     * @return un ArrayLista de objetos Jugador que representan a todos los usuarios cargados desde la base de datos.
     * @throws Exception si hay un error al cargar los usuarios.
     */
    public ArrayList<Jugador> loadAllUsuarios() throws Exception {
        UsuariosDAO db = new UsuariosDAO();
        try {
            db.searchAllUsers();
            ResultSet rs = db.getResultset();

            ArrayList<Jugador> arrayList = new ArrayList<>();
            while(rs.next()){
                String nombre = rs.getString("nombre");
                String contrasena = rs.getString("contrasena");
                int puntaje = rs.getInt("puntaje");
                String logros = rs.getString("logros");
                Jugador newPlayer = new Jugador(nombre, contrasena,puntaje,reinstanciarLogros(logros));
                arrayList.add(newPlayer);
            }
            return arrayList;
        } catch (Exception ex) {
            System.out.println("Error al cargar todos los usuarios");
            throw ex;
        } finally {
            db.disconnectDB();
        }
    }

    /**
     * Actualiza los logros de un jugador en la base de datos.
     *
     * @param jugador El objeto Jugador cuyos logros se actualizarán en la base de datos.
     * @throws Exception Si ocurre un error durante la actualización de los logros en la base de datos.
     */
    public void actualizarLogrosBase(Jugador jugador) throws Exception {
        UsuariosDAO db = new UsuariosDAO();
        ArrayList<Logros> logrosList = jugador.getLogros();
        String logrosText = gson.toJson(logrosList);
        db.updateUserLogros(jugador.getNombre(), logrosText);
    }

    /**
     * Actualiza los puntos de un jugador en la base de datos.
     *
     * @param jugador El objeto Jugador cuyos puntos se actualizarán en la base de datos.
     * @throws Exception Si ocurre un error durante la actualización de los puntos en la base de datos.
     */
    public void actualizarPuntosUsuario(Jugador jugador) throws Exception {
        UsuariosDAO db = new UsuariosDAO();

        db.updateUserPoints(jugador.getNombre(),jugador.getPuntaje());
    }

    /**
     * Convierte una cadena JSON en una lista de objetos Logros.
     *
     * @param logros la cadena JSON que representa una lista de objetos Logros.
     * @return una lista de objetos Logros.
     * @throws Exception si ocurre un error al deserializar la cadena JSON.
     */
    public ArrayList<Logros> reinstanciarLogros(String logros) throws Exception {
        Type listType = new TypeToken<ArrayList<Logros>>() {}.getType();
        return gson.fromJson(logros, listType);
    }

    /**
     * Elimina un jugador de la lista de usuarios, asegurándose de que el jugador activo y los administradores no sean eliminados.
     * Luego solicita al usuario que seleccione a un jugador de la lista filtrada que desea eliminar de la base de datos.
     *
     * @param admins Una lista de nombres de usuario que tienen privilegios de administrador y no deben ser eliminados.
     * @param jugadorActivo El jugador actualmente activo que no debe ser eliminado.
     * @throws Exception Si ocurre un error al eliminar el usuario de la base de datos.
     */
    public void eliminarJugador(ArrayList<String> admins, Jugador jugadorActivo) throws Exception {
        UsuariosDAO db =new UsuariosDAO();

        System.out.println("ELIMINAR JUGADOR"); System.out.println(" ");
        ArrayList<Jugador> listaDeleteJugador;
        listaDeleteJugador = this.loadAllUsuarios();

        //Remueve a los admins de la lista de posibles eliminados.
        listaDeleteJugador.removeIf(jugador -> jugador.getNombre().equals(jugadorActivo.getNombre()));
        listaDeleteJugador.removeIf(jugador -> admins.contains(jugador.getNombre()));

        int contadorCambioJugador = 0;
        System.out.println("Elija de la lista, indicando el número que lo acompaña, el Jugador que desea eliminar:");
        System.out.println(" ");
        System.out.println("0. SALIR");
        for (Jugador jugador : listaDeleteJugador) {
            contadorCambioJugador++;
            System.out.println(contadorCambioJugador + ". " + jugador.getNombre());
        }
        System.out.println(" ");
        if (listaDeleteJugador.isEmpty()){
            return;
        }else{

            int seleccion = Libreria.catchInt(0,contadorCambioJugador);
            if (seleccion==0){
                return;
            }
            Jugador jugadorEliminar = listaDeleteJugador.get(seleccion-1);

            try {
                db.deleteUser(jugadorEliminar.getNombre());
            }catch (Exception ex) {
                System.out.println("Error al eliminar el usuario");
                throw ex;
            }
        }
    }
}