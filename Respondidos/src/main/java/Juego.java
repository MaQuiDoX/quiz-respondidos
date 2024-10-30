import Game.Jugador;
import Game.PartidaIndividual;
import Game.PartidaVersus;
import Game.Partida;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Juego {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ranking ranking = new Ranking();


        Jugador samu = new Jugador("Samu", 500);
        ranking.agregarJugador(samu);
        Jugador jug2 = new Jugador("JUGADOR 2", 50);
        ranking.agregarJugador(jug2);
        Jugador jug3 = new Jugador("ILLOJUAN", 100);
        ranking.agregarJugador(jug3);


        while (true){
            Partida partida = new PartidaVersus(new ArrayList<>(), jug2, samu);

            partida.iniciarPartida(samu, jug2);        
        }

    }

    private static int obtenerNumero(int valor) {
        return valor; // Puedes cambiar este valor a lo que necesites
    }


}
