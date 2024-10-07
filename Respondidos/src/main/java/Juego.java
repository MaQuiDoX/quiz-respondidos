import java.util.InputMismatchException;
import java.util.Scanner;

public class Juego {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Jugador samu = new Jugador();
        Partida partida = new Partida(null, null, samu);

        partida.iniciarPartida(samu);


    }


}
