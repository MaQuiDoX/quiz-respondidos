package Game;

import Game.Partida;
import Game.Pregunta;
import utilities.Libreria;
import utilities.Tupla;

import java.util.ArrayList;

public class PartidaIndividual extends Partida implements PuntajeJuego {

    public void iniciarPartida(Jugador jugador) throws Exception {
        LogrosPorPuntos logroDeBusqueda = new LogrosPorPuntos();
        System.out.println(jugador.getPuntaje());
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> subLista = new ArrayList<>();
            preguntasRealizadas.add(subLista);
        }
        
        turnoJugador(jugador);
        calcularPuntaje(jugador);
        }



        //nos interesa el puntaje una vez que termina la ronda, así que ahora mostramos los logros obtenidos
        //por puntaje
        //logroDeBusqueda.mostrarLogrosPorPuntos(jugadorActivo);
   
        //listaRespuestasTuplas = new ArrayList<>();
        
    

    public PartidaIndividual(ArrayList<ArrayList<Integer>> pR, ArrayList<Jugador> lJ, Jugador jA) {
        this.preguntasRealizadas = pR;
        this.listaJugadores = lJ;
        this.jugadorActivo = jA;
    }
    
    @Override
    public void calcularPuntaje(Jugador jugador){
        //le sacamos 20 porque somos malos
        int puntajeGanado = jugador.getPuntajePartida();
        jugador.sumarPuntos(puntajeGanado - 20);
        jugador.resetRacha();
        jugador.resetContadorUsoPoderes();
        jugador.setPuntajePartida(0);
        
    }
}
