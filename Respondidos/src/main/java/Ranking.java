import Game.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * La clase Ranking es la encargada de ordenar e imprimir la lista de jugadores teniendo en cuenta sus puntajes
 * ordenándolos de mayor a menor.
 *
 * La clase permite:
 * - Mostrar por pantalla el ranking de jugadores actualizado al momento.
 *
 * @author Giraudo Ignacio
 * @author Martins Ezequiel
 */
public class Ranking {
    private ArrayList<Jugador> ranking = new ArrayList<>();;

    /**
     * Constructor de la clase Ranking
     *
     * @author Giraudo Ignacio
     * @author Martins Ezequiel
     *
     */
    public Ranking() {
        try{
            Usuarios usu = new Usuarios();
            ranking = usu.loadAllUsuarios();
        } catch (Exception ex){
            System.out.println("Hubo un error al crear el ranking");
        }
    }

    /**
     * Imprime el ranking del primer al último elemento de la lista.
     * @author Giraudo Ignacio
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

    /**
     * Ordena el ranking de mayor a menor puntaje.
     *
     * @author Giraudo Ignacio
     */
    private void ordenarRanking() {ranking.sort(Comparator.comparingInt(Jugador::getPuntaje).reversed());
    }
}
