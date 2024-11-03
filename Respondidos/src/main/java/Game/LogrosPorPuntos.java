package Game;

import Game.Jugador;
import java.util.Iterator;

public class LogrosPorPuntos extends Logros{




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

    public void mostrarLogrosPorPuntos(Jugador jugador) {
        for (int i = 0; i <= jugador.getLogros().size() - 1; i++) {
            if (jugador.getLogros().get(i) instanceof LogrosPorPuntos) {
                mostrarLogro(jugador, i);

            }
        }
    }

}
