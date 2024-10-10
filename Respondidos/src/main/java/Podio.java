import java.util.ArrayList;
import java.util.Iterator;

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
    //En el caso de que se asignen mal, la funcion es llamada en el constructor de "Jugador".
}
