package Game;

import Game.Jugador;
import java.util.ArrayList;

/**
 * La clase Logros representa una clase básica abstracta para los diferentes tipos de logros
 * que un jugador puede obtener. Cada logro tiene un nombre, una meta a lograr y un tipo.
 * @author Ferrari Paulina
 * @author Villegas Joaquin
 */
public abstract class Logros {
    protected String nombre;
    protected int meta;
    protected String tipo;

    /**
     * Constructor por defecto de la clase Logros.
     * Inicializa la meta del logro con el valor pasado como parámetro.
     */
    public Logros() {
        this.meta= meta;
    }

    /**
     * Obtiene la meta establecida para el logro.
     *
     * @return La meta como un entero.
     */
    public int getMeta() {
        return meta;
    }

    /**
     * Obtiene el nombre del logro.
     *
     * @return El nombre del logro.
     */
    public String getNombre() {

        return nombre;
    }

    /**
     * Elige un nombre para el logro en el número dado y el modo versus.
     *
     *
     */
    public boolean elegirNombre(Jugador jugador, int num, boolean versus) {
        return false;
    }

    /**
     * Busca la meta específica de un jugador para determinar los logros que puede alcanzar.
     *
     * @param jugador El jugador cuyo progreso se revisa para buscar una meta.
     */
    public void buscarMeta(Jugador jugador){
    }

    /**
     * Muestra el logro obtenido por el jugador en una posición específica en una lista.
     *
     * @param jugador El jugador que ha obtenido el logro.
     * @param ind El índice del logro en la lista de logros del jugador.
     */
    public void mostrarLogro(Jugador jugador, int ind){
        System.out.println("Logro obtenido: " +jugador.getLogros().get(ind).getNombre());
    }

    /**
     * Comprueba los logros del jugador y añade un nuevo logro si aún no se ha alcanzado.
     *
     * @param jugador El jugador cuyos logros se están revisando.
     * @param logro El logro para comprobar y potencialmente añadir a los logros del jugador.
     */
    public void comprobar(Jugador jugador, Logros logro) {
        for (int i = 0; i <= jugador.getLogros().size() - 1; i++) {
            String existe = jugador.getLogros().get(i).getNombre();
            if (!existe.equals(logro.nombre)) {
                jugador.addLogro(logro);
            }
        }

        if (jugador.getLogros().isEmpty()) {
            jugador.addLogro(logro);
        }

    }



}
