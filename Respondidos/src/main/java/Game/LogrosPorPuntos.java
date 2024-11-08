package Game;

import Game.Jugador;
import java.util.Iterator;

/**
 * La clase LogrosPorPuntos representa un tipo específico de logro que un jugador puede alcanzar
 * basándose en la cantidad de puntos obtenidos. Esta clase extiende la clase abstracta Logros
 * y proporciona implementaciones específicas para manejar logros basados en puntos.
 * @author Ferrari Paulina
 * @author Villegas Joaquin
 */
public class LogrosPorPuntos extends Logros{

    LogrosPorPuntos(){
        this.tipo = this.getClass().getSimpleName();
    }

    /**
     * Determina y asigna un nombre de logro basado en los puntos obtenidos
     * por el jugador, y si el modo de juego es versus.
     *
     * @param jugador El jugador que está intentando lograr una meta.
     * @param puntos Los puntos obtenidos por el jugador.
     * @param versus Booleano que indica*/
    @Override
    public boolean elegirNombre(Jugador jugador, int puntos, boolean versus) {

        buscarMeta(jugador);

        if (puntos == meta || puntos > meta ) {
            if (versus) {
                this.nombre = "Llegar a " +meta+ " puntos en una partida 1v1";
            } else {this.nombre = "Llegar a " +meta+ " puntos";}
            return true;

        } else {return false;}

    }

    /**
     * Busca y establece la meta del jugador basada en sus logros previos.
     *
     * @param jugador El jugador cuyos logros están siendo evaluados.
     */
    @Override
    public void buscarMeta(Jugador jugador) {

        try {
            int buscar = jugador.getLogros().size()-1;
            if (jugador.getLogros().get(buscar) instanceof LogrosPorPuntos) {
                this.meta = jugador.getLogros().get(buscar).getMeta() + 100;
            }
            else { this.meta = 100;}

        } catch (ArrayIndexOutOfBoundsException e) {

            this.meta = 100;
        } catch (IndexOutOfBoundsException e) {
            this.meta = 100;
        }

    }

    /**
     * Muestra todos los logros de tipo LogrosPorPuntos de un jugador.
     *
     * @param jugador El jugador cuyos logros de tipo LogrosPorPuntos se quieren mostrar.
     */
    public void mostrarLogrosPorPuntos(Jugador jugador) {
        for (int i = 0; i <= jugador.getLogros().size() - 1; i++) {
            if (jugador.getLogros().get(i) instanceof LogrosPorPuntos) {
                mostrarLogro(jugador, i);

            }
        }
    }

}
