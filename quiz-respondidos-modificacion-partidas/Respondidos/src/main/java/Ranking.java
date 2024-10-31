import Game.Jugador;
import java.util.ArrayList;
import java.util.Comparator;

/*
REVISAR MEJOR FORMA DE OPTIMIZARLO.
 */
public class Ranking {
    private ArrayList<Jugador> ranking = new ArrayList<>();;

    public Ranking() {
    }

    /**
     * Ordena e imprime el ranking del primer al último elemento de la lista. (de mayor a menor puntaje).
     * @author Nacho
     */
    public void imprimirRanking() {
        this.ordenarRanking();
        System.out.println("-----------------------");
        System.out.println("Ranking de Jugadores:");
        System.out.println("-----------------------");

        int posicion = 1;
        for (Jugador jugador : ranking) {
            System.out.println(posicion + ". " + jugador.getNombre() + " - Puntaje: " + jugador.getPuntaje());
            posicion++;
        }
    }
    public void agregarJugador(Jugador jugador){
        ranking.add(jugador);
    }

    public void ordenarRanking() {
        ranking.sort(Comparator.comparingInt(Jugador::getPuntaje).reversed());
    }

}
