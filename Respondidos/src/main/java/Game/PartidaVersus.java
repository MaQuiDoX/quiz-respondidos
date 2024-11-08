package Game;
import java.util.ArrayList;

/**
 * Esta clase representa una Partida Versus. Esta clase herede de la clase base Partida e
 * implementa la interfaz Interfaz para proporcionar funcionalidad específica para partidas de dos jugadores.
 * @author Villegas Joaquin
 */
public class PartidaVersus extends Partida implements Interfaz {
    protected Jugador jugador1;
    protected Jugador jugador2;

    /**
     * Constructor de la clase PartidaVersus.
     *
     * @param pR Lista de preguntas realizadas.
     * @param j1 Primer jugador de la partida.
     * @param j2 Segundo jugador de la partida.
     */
    public PartidaVersus(ArrayList<ArrayList<Integer>> pR, Jugador j1, Jugador j2) {
        this.preguntasRealizadas = pR;
        this.jugador1 = j1;
        this.jugador2 = j2;
        this.jugadorActivo = null;
    }

    /**
     * Cambia el jugador activo en una partida versus.
     *
     * @param jugador El jugador que se quiere establecer como el nuevo jugador activo.
     */
    public void cambiarJugador(Jugador jugador){
        if (this.jugadorActivo != jugador){
            this.jugadorActivo = jugador;
        } else {
            System.out.println("El jugador ingresado ya esta jugando");
        }
    }

    /**
     * Inicia una partida entre dos jugadores. La partida finaliza cuando uno
     * de los jugadores alcanza o supera los 100 puntos.
     *
     * @param j1 Primer jugador de la partida.
     * @param j2 Segundo jugador de la partida.
     * @throws Exception Puede lanzar una excepción durante el desarrollo del juego.
     */
    public void iniciarPartida(Jugador j1, Jugador j2) throws Exception {
        ArrayList <Integer> noConsiderarJ1 = new ArrayList<>();
        ArrayList <Integer> noConsiderarJ2 = new ArrayList<Integer>();
        ArrayList<Integer> arrayAusar;
        partidaVersus = true;
        int turno = 0;
        boolean partidaNoTerminada = true;
        
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> subLista = new ArrayList<>();
            preguntasRealizadas.add(subLista);
        }
        
        while (partidaNoTerminada){
            //La logica con la que nos fijaremos de quien es el turno
            if (turno % 2 == 0){
                cambiarJugador(j1);
                j1.resetRacha();
                arrayAusar = noConsiderarJ1;
            } else {
                cambiarJugador(j2);
                j2.resetRacha();
                arrayAusar = noConsiderarJ2;
            }
            
            System.out.println("=== Es el turno de: " + this.jugadorActivo.getNombre() + " que va " + this.jugadorActivo.getPuntajePartida() + " puntos///");
            turnoJugador(this.jugadorActivo, arrayAusar);
            //Revisamos que la partida haya terminado (cuando alguno de los dos jugadores llegue o sobrepase el limite, por ejemplo 100 puntos.
            if (this.jugadorActivo.getPuntajePartida() >= 100){
                System.out.println("El juego termino, nuestro ganador es: " + this.jugadorActivo.getNombre());
                partidaNoTerminada = false;
            }
            
            turno++;
            
        }
        calcularPuntaje(j1);
        calcularPuntaje(j2);
        
        
    }

    /**
     * Calcula y actualiza el puntaje total del jugador después de una partida.
     * Dependiendo del puntaje de la partida, el puntaje total del jugador se ajustará
     * de manera diferente.
     *
     * @param jugador El jugador cuyo puntaje debe ser calculado y actualizado.
     */
    @Override
    public void calcularPuntaje(Jugador jugador){
        if (jugador.getPuntajePartida() >= 100){
            int resto = jugador.getPuntajePartida() - 25;
            int puntosTotales = 25 + (resto - (2* jugador.getContadorUsoPoderes()));
            jugador.sumarPuntos(puntosTotales);
        } else {
            jugador.sumarPuntos(10);
        }
        jugador.resetRacha();
        jugador.resetContadorUsoPoderes();
        jugador.setPuntajePartida(0);
    }
}
