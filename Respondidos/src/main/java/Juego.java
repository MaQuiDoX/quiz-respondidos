
import Game.Jugador;
import Game.PartidaIndividual;
import Game.PartidaVersus;
import utilities.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * La clase Juego representa el punto de entrada principal de la aplicación.
 * Se encarga de manejar el flujo principal del juego.
 *
 * La clase permite a los jugadores:
 * - Registrarse
 * - Iniciar partidas individuales o versus
 * - Ver estadísticas y ranking
 * - Seleccionar otros jugadores
 * - Salir del juego
 *
 * El juego tiene una lista de administradores que tienen opciones adicionales
 * tales como la eliminación de usuarios.
 *
 * @author Giraudo Ignacio
 * @author Villegas Joaco
 * @author Ferrari Paulina
 * @author Martins Ezequiel
 * @author Quesada Manuel
 */

public class Juego {
    public static void main(String[] args) throws Exception {
        boolean salir = false;

        Usuarios usuarios = new Usuarios();
        Scanner sc = new Scanner(System.in);
        ClearScreen.cls();

        /*
        String titulo = " R E S P O N D I D O S ";
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("**************************************");
        System.out.println("* * * * * * * * * * * * * * * * * *  *");
        System.out.println("*       " + titulo + "      *");
        System.out.println("* * * * * * * * * * * * * * * * * *  *");
        System.out.println("**************************************");
         */

        /*
        System.out.println("   ____     U _____ u   ____       ____       U  ___ u   _   _       ____                    ____       U  ___ u   ____     ");
        System.out.println("U |  _\"\\ u  \\| ___\"|/  / __\"| u  U|  _\"\\ u     \\/\"_ \\/  | \\ |\"|     |  _\"\\        ___       |  _\"\\       \\/\"_ \\/  / __\"| u  ");
        System.out.println(" \\| |_) |/   |  _|\"   <\\___ \\/   \\| |_) |/     | | | | <|  \\| |>   /| | | |      |_\"_|     /| | | |      | | | | <\\___ \\/   ");
        System.out.println("  |  _ <     | |___    u___) |    |  __/   .-,_| |_| | U| |\\  |u   U| |_| |\\      | |      U| |_| |\\ .-,_| |_| |  u___) |   ");
        System.out.println("  |_| \\_\\    |_____|   |____/>>   |_|       \\_)-\\___/   |_| \\_|     |____/ u    U/| |\\u     |____/ u  \\_)-\\___/   |____/>>  ");
        System.out.println("  //   \\\\_   <<   >>    )(  (__)  ||>>_          \\\\     ||   \\\\,-.   |||_    .-,_|___|_,-.   |||_          \\\\      )(  (__) ");
        System.out.println(" (__)  (__) (__) (__)  (__)      (__)__)        (__)    (_\")  (_/   (__)_)    \\_)-' '-(_/   (__)_)        (__)    (__)      ");
         */
        final String amarillo = "\u001B[33m";
        final String rojo = "\u001B[31m";
        final String reset = "\u001B[0m";
        final String naranja = "\u001B[33m";
        final String verde = "\u001B[32m";
        final String celeste = "\u001B[36m";
        final String azul = "\u001B[34m";
        final String magenta = "\u001B[35m";

        System.out.println(celeste+"╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗\n" +
                "║                                                                                                                                                                                        ║\n" +
                "║ "+rojo+"  -###%%%%#*+:  "+naranja+"   -##%%%%%%%%%# "+amarillo+"    =#%@@@@%*+. "+verde+" .####%%%#*+-        -*%@@@@%*=.     :#%%%-      ****.   ##%%%%%#*=:       *%%%+   =##%%%%##+-.         :+#%@@@%#+:       :+#@@@@%#+-   ║\n" +
                "║ "+rojo+" *@@@@@@@@@@@@= "+naranja+"  +@@@@@@@@@@@@ "+amarillo+" =@@@@@@@@@@@- "+verde+"  -@@@@@@@@@@@@+    +@@@@@@@@@@@@*.   =@@@@@=    .@@@@:  .@@@@@@@@@@@@*.    @@@@#   #@@@@@@@@@@@#-     :%@@@@@@@@@@@%-    #@@@@@@@@@@%   ║\n" +
                "║ "+rojo+" #@@@@-::-#@@@@- "+naranja+" +@@@@:::::::. "+amarillo+" @@@@*.  .:=- "+verde+"  -@@@@#--=*@@@@+ .%@@@@*-..:+@@@@@.  =@@@@@@#   .@@@@-  :@@@@+--+#@@@@@-   @@@@#   #@@@@--=*@@@@@#   +@@@@%=:.:-%@@@@+  *@@@@:  .:-+    ║\n" +
                "║ "+rojo+" #@@@@:   :@@@@= "+naranja+" +@@@@......   "+amarillo+" @@@@@*=:.    "+verde+"  -@@@@*    %@@@% *@@@@=      -@@@@*  =@@@@@@@@. .@@@@-  :@@@@-    .@@@@@.  @@@@#   %@@@%     *@@@@+ .@@@@%       @@@@@: +@@@@#+-.       ║\n" +
                "║ "+rojo+" #@@@@+==+%@@@@. "+naranja+" +@@@@@@@@@@. "+amarillo+"  -@@@@@@@@@#=  "+verde+" -@@@@#::-+@@@@+ @@@@@        @@@@%  =@@@@+@@@@:.@@@@:  :@@@@-     =@@@@=  @@@@%   %@@@%      @@@@% =@@@@=       +@@@@=  *@@@@@@@@%+:   ║\n" +
                "║ "+rojo+" %@@@@@@@@@@%+. "+naranja+"  *@@@@######   "+amarillo+"   :=+*%@@@@@% "+verde+" -@@@@@@@@@@@@+  %@@@@.       @@@@%  =@@@@.:@@@@+@@@@:  :@@@@-     =@@@@-  @@@@#   %@@@%      @@@@% -@@@@+       *@@@@=    -=*#%@@@@@=  ║\n" +
                "║ "+rojo+" %@@@@:-%@@@%:  "+naranja+"  *@@@@         "+amarillo+" .:      #@@@@."+verde+" -@@@@@###*=-    =@@@@*      #@@@@+  =@@@@. .#@@@@@@@:  :@@@@:    :@@@@@   @@@@#   %@@@%     *@@@@=  @@@@@:     :@@@@@.  :.     -@@@@#  ║\n" +
                "║ "+rojo+" %@@@@   *@@@@+  "+naranja+" *@@@@+++++++- "+amarillo+" #@@#+++*@@@@%  "+verde+"-@@@@#           +@@@@@*==+%@@@@#   =@@@@.   *@@@@@@.  :@@@@#++*%@@@@%:   @@@@#   %@@@@*+*#@@@@@+   :@@@@@#+=+#@@@@@-  :@@%*+++%@@@@=  ║\n" +
                "║ "+rojo+" #@@@@    -@@@@* "+naranja+" *@@@@@@@@@@@* "+amarillo+" *%@@@@@@@@#=  "+verde+" -@@@@#            :#@@@@@@@@@@#-    =@@@@.    =@@@@@.  :@@@@@@@@@@@#=     @@@@#   %@@@@@@@@@@%+:      +@@@@@@@@@@@+    =#@@@@@@@@@*:   ║\n" +
                "║"+reset+"                                                                                                                                                                                        ║\n" +
                "╚════════════════════════════════════════════════════════════════╦══════════════════════════════════════════════════════╦════════════════════════════════════════════════════════════════╝\n" +
                "                                                                 ║ Poli - xXGiraudoXx - Rumblet - MaQuiDoX - cocoproman ║\n" +
                "                                                                 ╚══════════════════════════════════════════════════════╝");

        try {
            // Pausa la ejecución del programa por 4 segundos (4,000 milisegundos)
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido.");
        }
        ClearScreen.cls();
        Jugador jugadorActivo = usuarios.logUsuario();
        ArrayList<String> admins = new ArrayList<>();
        admins.add("xXGiraudoXx");
        admins.add("cocoproman");
        admins.add("Rumblet");
        admins.add("MaQuiDoX");
        admins.add("poli");

        while (!salir){
            ClearScreen.cls();
            System.out.println("  RESPONDIDOS           Jugador Activo: "+ jugadorActivo.getNombre());
            System.out.println(" ");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Partida Individual");
            System.out.println("3. Iniciar Partida Versus");
            System.out.println("4. Estadísticas");
            System.out.println("5. Ranking");
            System.out.println("6. Seleccionar Jugador");
            System.out.println("7. Salir");
            int opcion=0;
            //Si es admin se le permite acceder a la 8va opcion (DELETE USER)
            if (admins.contains(jugadorActivo.getNombre())){
                 opcion = Libreria.catchInt(1,8);
            } else{
                 opcion = Libreria.catchInt(1,7);
            }


            ClearScreen.cls();
            switch (opcion){
                case 1:
                    Jugador newJugador = Usuarios.registerUsuario();
                    ClearScreen.cls();
                    break;
                case 2:
                    PartidaIndividual partidaI = new PartidaIndividual(new ArrayList<>(), jugadorActivo);
                    partidaI.iniciarPartida(jugadorActivo);

                    usuarios.actualizarPuntosUsuario(jugadorActivo);
                    usuarios.actualizarLogrosBase(jugadorActivo);
                    try {
                        // Pausa la ejecución del programa por 4 segundos (4,000 milisegundos)
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        System.out.println("El hilo fue interrumpido.");
                    }
                    ClearScreen.cls();

                    break;
                case 3:
                    PartidaVersus versus = new PartidaVersus(new ArrayList<>(), null, null);

                    ArrayList<Jugador> listaJugadoresRegistrados;
                    listaJugadoresRegistrados = usuarios.loadAllUsuarios();
                    //System.out.println(listaJugadoresRegistrados);
                    int contadorJugadoresVersus = 1;

                    Jugador finalJugadorActivo1 = jugadorActivo;
                    listaJugadoresRegistrados.removeIf(jugador -> jugador.getNombre().equals(finalJugadorActivo1.getNombre()));

                    System.out.println("Elija su contrincante de la lista indicando el número que lo acompaña:");
                    for (Jugador jugador : listaJugadoresRegistrados) {
                        System.out.println(contadorJugadoresVersus + ". " + jugador.getNombre());
                        contadorJugadoresVersus++;
                    }

                    int seleccion = Libreria.catchInt(1,contadorJugadoresVersus);

                    Jugador jugadorVersus = listaJugadoresRegistrados.get(seleccion-1);

                    while (true){
                        System.out.println("Ingrese la contraseña del jugador seleccionado: " + jugadorVersus.getNombre());
                        String contraNew = sc.nextLine();
                        ClearScreen.cls();
                        if (!Objects.equals(contraNew, jugadorVersus.getContrasena())){
                            System.out.println("Contraseña Incorrecta...");
                            System.out.println("¿Desea ingresar la contraseña nuevamente?");
                            System.out.println("1. Si");
                            System.out.println("2. No");
                            int opcion2 = Libreria.catchInt(1,2);
                            if (opcion2 == 2){
                                break;
                            }
                        } else if (Objects.equals(contraNew, jugadorVersus.getContrasena())){
                            versus.iniciarPartida(jugadorActivo, jugadorVersus);
                            usuarios.actualizarPuntosUsuario(jugadorActivo);
                            usuarios.actualizarPuntosUsuario(jugadorVersus);
                            usuarios.actualizarLogrosBase(jugadorActivo);
                            usuarios.actualizarLogrosBase(jugadorVersus);
                            break;
                        }

                    }
                    try {
                        // Pausa la ejecución del programa por 4 segundos (4,000 milisegundos)
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("El hilo fue interrumpido.");
                    }
                    ClearScreen.cls();
                    break;
                case 4:
                    //Acá iría JugadorActivo.mostrarEstadisticas.
                    jugadorActivo.printEstadisticas(jugadorActivo);
                    try {
                        // Pausa la ejecución del programa por 4 segundos (4,000 milisegundos)
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        System.out.println("El hilo fue interrumpido.");
                    }
                    ClearScreen.cls();
                    break;
                case 5:
                    Ranking ranking = new Ranking();
                    ranking.imprimirRanking();
                    try {
                        // Pausa la ejecución del programa por 4 segundos (4,000 milisegundos)
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        System.out.println("El hilo fue interrumpido.");
                    }
                    ClearScreen.cls();
                    break;
                case 6:
                    ArrayList<Jugador> listaCambioJugador;
                    listaCambioJugador = usuarios.loadAllUsuarios();

                    Jugador finalJugadorActivo = jugadorActivo;
                    listaCambioJugador.removeIf(jugador -> jugador.getNombre().equals(finalJugadorActivo.getNombre()));

                    int contadorCambioJugador = 1;
                    System.out.println("Elija de la lista, indicando el número que lo acompaña, el Jugador con el cual se desea jugar:");
                    for (Jugador jugador : listaCambioJugador) {
                        System.out.println(contadorCambioJugador + ". " + jugador.getNombre());
                        contadorCambioJugador++;
                    }

                    int seleccionCambio = Libreria.catchInt(1,contadorCambioJugador);

                    Jugador jugadorCambio = listaCambioJugador.get(seleccionCambio-1);

                    while (true){
                        System.out.println("Ingrese la contraseña del jugador seleccionado: " + jugadorCambio.getNombre());
                        String contraNewCambio = sc.nextLine();

                        if (!Objects.equals(contraNewCambio, jugadorCambio.getContrasena())){
                            System.out.println("Contraseña Incorrecta...");
                            System.out.println("¿Desea ingresar la contraseña nuevamente?");
                            System.out.println("1. Si");
                            System.out.println("2. No");
                            int opcion2 = Libreria.catchInt(1,2);
                            if (opcion2 == 2){
                                break;
                            }
                        } else if (Objects.equals(contraNewCambio, jugadorCambio.getContrasena())){
                            jugadorActivo = jugadorCambio;
                            break;
                        }
                    }
                    ClearScreen.cls();
                    break;
                case 7:
                    salir = true;
                    break;
                case 8:
                    usuarios.eliminarJugador(admins, jugadorActivo);
                    ClearScreen.cls();
                    break;
            }

        }
    }
}