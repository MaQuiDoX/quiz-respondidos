package Game;


import Game.Logros;
import Game.Jugador;

/**
 * La clase LogrosPorRacha representa un tipo de logro basado en una racha consecutiva de responder varias preguntas correctamente de forma consecutiva.
 * Extiende la clase base Logros para proporcionar una implementación específica
 * de logros basados en racha.
 * @author Ferrari Paulina
 * @author Villegas Joaquin
 */
public class LogrosPorRacha extends Logros{

    LogrosPorRacha(){
        this.tipo = this.getClass().getSimpleName();
    }

    /**
     * Establece el nombre del logro basado en la racha de respuestas correctas del jugador.
     * Si la racha del jugador coincide con la meta establecida, se asigna un nombre de logro específico
     * y se devuelve true. En caso contrario, se devuelve false.
     *
     * @param jugador El jugador cuyo logro se evaluará.
     * @param racha La racha actual de respuestas correctas del jugador.
     * @param versus Indica si la racha fue obtenida en una partida 1v1.
     * @return true si la racha del jugador coincide con la meta, false en caso contrario.
     */
    @Override
    public boolean elegirNombre(Jugador jugador, int racha, boolean versus) {

        if (jugador.getLogros().isEmpty()) {
            this.meta = 5;
        } else {
            buscarMeta(jugador);
        }

        if (racha == meta) {
            if (versus){
                this.nombre = "Contestar " +meta+ " preguntas seguidas en partida 1v1";
            } else {this.nombre = "Contestar " +meta+ " preguntas seguidas";}
            return true;

        } else {return false; }
    }

    /**
     * Busca y establece una nueva meta basada en el último logro por racha del jugador.
     * Si el último logro en la lista del jugador es una instancia de LogrosPorRacha,
     * la nueva meta se incrementará en 5.
     *
     * @param jugador El objeto Jugador cuyos logros se evaluarán para determinar la nueva meta.
     */
    @Override
    public void buscarMeta(Jugador jugador) {

        int buscar = jugador.getLogros().size()-1;

        if (jugador.getLogros().get(buscar) instanceof LogrosPorRacha) {
            this.meta = jugador.getLogros().get(buscar).getMeta() + 5;
        }
    }

    /**
     * Muestra los logros del jugador que son de tipo LogrosPorRacha.
     *
     * @param jugador El objeto Jugador cuyos logros se evaluarán y mostrarán
     *                si son instancias de LogrosPorRacha.
     */
    public void mostrarLogrosPorRacha(Jugador jugador) {
        for (int i = 0; i <= jugador.getLogros().size() - 1; i++) {
            if (jugador.getLogros().get(i) instanceof LogrosPorRacha) {
                mostrarLogro(jugador, i);

            }
        }
    }





}



