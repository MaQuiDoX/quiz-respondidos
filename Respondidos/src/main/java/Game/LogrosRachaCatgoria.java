package Game;

import Game.LogrosPorRacha;
import Game.Jugador;
import utilities.Tupla;

import java.util.ArrayList;

/**
 * Esta clase representa los logros del tipo "contestar cierta cantidad de preguntas de una misma categoría"
 */
public class LogrosRachaCatgoria extends Logros{

    /**
     * Este constructor sirve para la desearilización de los logros traidos de la base, para que podamos asociar cada logro almacenado
     * con su clase específica
     */
    LogrosRachaCatgoria(){
        this.tipo = this.getClass().getSimpleName();
    }

    /**
     * Este es el método que va a crear y guardar los logros de esta clase
     * lo hace recorriendo la matriz de preguntas realizadas
     * @param preguntas
     * @param jugador
     * @param versus
     * @param ignorar
     */
    public void recorrer(ArrayList<ArrayList<Integer>> preguntas, Jugador jugador, boolean versus, ArrayList<Integer> ignorar) {
        int tamanio;

        // Recorremos los array que representan cada categoría
        for (int i = 0; i <= preguntas.size()-1; i++){
            int descontar = 0;

            // Recorremos el array que guardó en partida todas las preguntas que se realizaron y se respondieron mal

            for(int k= 0; k<= ignorar.size()-1; k++){
                if (ignorar.get(k)==i) {
                    //si la categoría de la pregunta que se falló coincide con la categoría actual,
                    //se incrementa un contador. Ese contador va a descontar las preguntas fallidas de la cantidad total
                    //de preguntas de esa categoría
                    descontar = +1;
                }
            }

            // Recorremos los array que tienen todas las preguntas realizadas de una categoría
            for(int j = 0; j <= preguntas.get(i).size()-descontar; ++j) {
                //si el número de preguntas contestadas correctamente de la categoría es múltiplo
                //de 5, se crea el logro, luego comprobamos si ese logro debe añadirse o ya existe
                if (j % 5 == 0 && j !=0) {
                    LogrosRachaCatgoria logro = new LogrosRachaCatgoria();
                    logro.meta = i;
                    logro.nombrar(definirCategoria(i), versus);
                    comprobar(jugador, logro);
                }
            }
        }

    }

    /**
     * Nombre de la categoría según su identificador en la matriz
     * @param numero
     * @return
     */
    public String definirCategoria(int numero) {
        switch (numero) {
            case 0 :
                return "Arte";

            case 1 :
                return "Entretenimiento";

            case 2:
                return "Deporte";

            case 3 :
                return "Ciencia";

            case 4 :
                return "Historia";

            case 5 :
                return "UNCuyo";



        }return "";
    }

    /**
     * Si se creó un logro, se le asigna nombre
     * @param categoria
     * @param versus
     */
    public void nombrar(String categoria, boolean versus) {
        if (versus) {
            this.nombre = "LOGRO OBTENIDO: Contestar " + this.meta + " preguntas de " + categoria + " en una partida 1v1";
        } else this.nombre = "LOGRO OBTENIDO: Contestar " + this.meta + " preguntas de " + categoria + " en una partida";
    }
}
