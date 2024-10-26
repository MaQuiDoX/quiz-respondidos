import utilities.Libreria;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Juego {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ranking ranking = new Ranking();

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        Jugador samu = new Jugador("Samu", 500);
        ranking.agregarJugador(samu);
        Jugador jug2 = new Jugador("JUGADOR 2", 50);
        ranking.agregarJugador(jug2);
        Jugador jug3 = new Jugador("ILLOJUAN", 100);
        ranking.agregarJugador(jug3);

        while (!salir){
            System.out.println("RESPONDIDOS: Jugador Activo: (Samu)");
            System.out.println("1. Registrar Jugador");
            System.out.println("2. Iniciar Partida Individual");
            System.out.println("3. Iniciar Partida Versus");
            System.out.println("4. Consultar Estadísticas");
            System.out.println("5. Consultar Ranking");
            System.out.println("6. Consultar partidas activas"); // remil final
            System.out.println("7. Seleccionar Jugador");
            System.out.println("8. Salir");

            int opcion = Libreria.catchInt(1,8);
            switch (opcion){
                case 1:
                    break;
                case 2:
                    Partida partida = new Partida(new ArrayList<>(), new ArrayList<>(), null);
                    partida.iniciarPartida(samu);

                    // Ahora podemos meter el objeto partida a la lista de partidas activas del jugador y podemos volver a utilizar el objeto anterior ya instanciado para otro jugador.

                    System.out.println(partida.getJugadorActivo());
                    System.out.println(partida.getListaJugadores());
                    System.out.println(partida.getPreguntasRealizadas());
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    salir = true;
            }

        }




        ranking.imprimirRanking();

        Partida partida = new Partida(new ArrayList<>(), null, samu);

        partida.iniciarPartida(samu);
    }

    private static int obtenerNumero(int valor) {
        return valor; // Puedes cambiar este valor a lo que necesites
    }


}
