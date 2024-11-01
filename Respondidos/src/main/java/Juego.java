import DAOs.DataBaseDAO;
import Game.Jugador;
import Game.PartidaIndividual;
import Game.PartidaVersus;
import Game.Partida;
import DAOs.UsuariosDAO;
import utilities.Libreria;
import utilities.Tupla;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Juego {
    public static void main(String[] args) throws Exception {

        boolean salir = false;
        int contadorIdPartida = 1;

        UsuariosDAO db = new UsuariosDAO();

        Usuarios usuarios = new Usuarios();
        Scanner sc = new Scanner(System.in);

        //EN LUGAR DE REGISTRAR UNO NUEVO, INICIAR SESIÓN.
        Jugador jugadorActivo = usuarios.registerUsuario();

        Ranking ranking = new Ranking();
//        Jugador samu = new Jugador("Samu", 500);
//        Jugador jug2 = new Jugador("JUGADOR 2", 50);
//        Jugador jug3 = new Jugador("ILLOJUAN", 100);
//        ranking.agregarJugador(samu);
//        ranking.agregarJugador(jug2);
//        ranking.agregarJugador(jug3);

        while (!salir){
            System.out.println("RESPONDIDOS: Jugador Activo: "+ jugadorActivo.getNombre());
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Partida Individual");
            System.out.println("3. Iniciar Partida Versus");
            System.out.println("4. Estadísticas");
            System.out.println("5. Ranking");
            System.out.println("6. Seleccionar Jugador");
            System.out.println("7. Salir");

            usuarios.actualizarLogrosBase(jugadorActivo);

            int opcion = Libreria.catchInt(1,8);
            switch (opcion){
                case 1:
                    Jugador newJugador = Usuarios.registerUsuario();
                    break;
                case 2:
                    //Reemplazar samu por JugadorActivo cuando sea posible (cuando esté listo el inciso 7). (Nacho)

                    PartidaIndividual partidaI = new PartidaIndividual(new ArrayList<>(), jugadorActivo);
                    partidaI.iniciarPartida(jugadorActivo);


                    break;
                case 3:
                    //Reemplazar samu por JugadorActivo cuando sea posible (cuando esté listo el inciso 7). (Nacho)
                    PartidaVersus versus = new PartidaVersus(new ArrayList<>(), null, null);

                    ArrayList<Jugador> listaJugadoresRegistrados;
                    listaJugadoresRegistrados = usuarios.loadAllUsuarios();
                    //System.out.println(listaJugadoresRegistrados);
                    int contadorJugadoresVersus = 1;

                    listaJugadoresRegistrados.removeIf(jugador -> jugador.getNombre().equals(jugadorActivo.getNombre()));

                    System.out.println("Elija su contrincante de la lista indicando el número que lo acompaña:");
                    for (Jugador jugador : listaJugadoresRegistrados) {
                        System.out.println(contadorJugadoresVersus + ". " + jugador.getNombre());
                        contadorJugadoresVersus++;
                    }

                    int seleccion = Libreria.catchInt(1,contadorJugadoresVersus);

                    Jugador jugadorVersus = listaJugadoresRegistrados.get(seleccion-1);

                    versus.iniciarPartida(jugadorActivo, jugadorVersus);
                    break;
                case 4:
                    //Acá iría JugadorActivo.mostrarEstadisticas.
                    jugadorActivo.printEstadisticas(jugadorActivo);
                    break;
                case 5:
                    ranking.imprimirRanking();
                    break;
                case 6:
                    ArrayList<Jugador> listaCambioJugador;
                    listaCambioJugador = usuarios.loadAllUsuarios();

                    listaCambioJugador.removeIf(jugador -> jugador.getNombre().equals(jugadorActivo.getNombre()));

                    int contadorCambioJugador = 1;
                    System.out.println("Elija de la lista, indicando el número que lo acompaña, el Jugador con el cual se desea jugar:");
                    for (Jugador jugador : listaCambioJugador) {
                        System.out.println(contadorCambioJugador + ". " + jugador.getNombre());
                        contadorCambioJugador++;
                    }

                    int seleccionCambio = Libreria.catchInt(1,contadorCambioJugador);

                    Jugador jugadorCambio = listaCambioJugador.get(seleccionCambio-1);

//                    System.out.println("Ingrese la contraseña:");
//                    String contra = sc.nextLine();

//                  if (contra != jugadorCambio)
//                  Actualizar jugador activo anterior aca?
                    break;
                case 7:


                    break;
                case 8:
                    salir = true;
            }
            // Actualizar jugador activo aca !!
        }
    }
}