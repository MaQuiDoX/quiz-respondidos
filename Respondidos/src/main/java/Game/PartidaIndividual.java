package Game;

import java.util.ArrayList;

/**
 * Esta clase representa una Partida individual. Esta clase hereda de la clase base Partida e
 * implementa la interfaz Interfaz para proporcionar funcionalidad específica para partidas de un solo jugador.
 * @author Villegas Joaquin
 */
public class PartidaIndividual extends Partida implements Interfaz {

    /**
     * Inicia una nueva partida para el jugador especificado. Durante este proceso, se establecen
     * listas de preguntas ya realizadas y se lleva a cabo el turno del jugador, seguido del cálculo
     * de su puntaje.
     *
     * @param jugador El jugador que va a iniciar la partida.
     * @throws Exception si ocurre algún error durante la inicialización de la partida.
     */
    public void iniciarPartida(Jugador jugador) throws Exception {
        ArrayList <Integer> noConsiderar = new ArrayList<Integer>();
        LogrosPorPuntos logroDeBusqueda = new LogrosPorPuntos();
        //System.out.println(jugador.getPuntaje());
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> subLista = new ArrayList<>();
            preguntasRealizadas.add(subLista);
        }
        
        turnoJugador(jugador, noConsiderar);
        calcularPuntaje(jugador);
    }

    /**
     * Constructor que inicializa una instancia de la clase PartidaIndividual con las preguntas
     * realizadas y el jugador activo proporcionados.
     *
     * @param pR Una lista de listas que contiene las preguntas realizadas.
     * @param jA El jugador que va a participar en la partida.
     */
    public PartidaIndividual(ArrayList<ArrayList<Integer>> pR, Jugador jA) {
        this.preguntasRealizadas = pR;
        this.jugadorActivo = jA;
    }

    /**
     * Calcula el puntaje final del jugador al finalizar la partida.
     * Este método agrega el puntaje de la partida al total del jugador,
     * resetea la racha del jugador, resetea el contador de uso de poderes
     * y pone a cero el puntaje de la partida.
     *
     * @param jugador El jugador cuyo puntaje se va a calcular.
     */
    @Override
    public void calcularPuntaje(Jugador jugador){
        //le sacamos 20 porque somos malos
        int puntajeGanado = jugador.getPuntajePartida();
        jugador.sumarPuntos(puntajeGanado);
        jugador.resetRacha();
        jugador.resetContadorUsoPoderes();
        jugador.setPuntajePartida(0);
        
    }
}
