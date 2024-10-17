import java.util.ArrayList;
import java.util.Scanner;

public class Juego {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ranking ranking = new Ranking();

        Jugador samu = new Jugador("Samu", 0);
        ranking.agregarJugador(samu);
        Jugador jug2 = new Jugador("JUGADOR 2", 50);
        ranking.agregarJugador(jug2);
        Jugador jug3 = new Jugador("ILLOJUAN", 100);
        ranking.agregarJugador(jug3);


        ranking.imprimirRanking();

        Partida partida = new Partida(new ArrayList<>(), null, samu);

        partida.iniciarPartida(samu);

    }


}
