import java.util.ArrayList;
import java.util.Collections;

/*
REVISAR MEJOR FORMA DE OPTIMIZARLO.
 */
public class Podio{
    private ArrayList<Jugador> ranking = new ArrayList<>();;

    /**
     * Imprime el ranking del primer al último elemento de la lista.
     * @author Nacho
     */
    public void imprimirRanking(){
        for (int i=0; i<=ranking.size(); i++){
            System.out.println((i+1)+"-: "+ ranking.get(i).getNombre());
        }
    }
    public void agregarJugador(Jugador jugador){
        ranking.add(jugador);
    }

    public void ordenarRanking(){
        Collections.sort(this.ranking, (Jugador a, Jugador b)->a.getPuntaje().compareTo(b.getPuntaje()));
    }

}
