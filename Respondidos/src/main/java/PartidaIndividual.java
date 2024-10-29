import Game.Pregunta;
import utilities.Libreria;
import utilities.Tupla;

import java.util.ArrayList;

public class PartidaIndividual extends Partida {

    public void iniciarPartida(Jugador jugador) {
        LogrosPorPuntos logroDeBusqueda = new LogrosPorPuntos();
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> subLista = new ArrayList<>();
            preguntasRealizadas.add(subLista);
        }
        boolean salir2 = false;
        while (!salir2) {


            Pregunta pregunta = Pregunta.obtenerPregunta(-1);

            if (preguntasRealizadas.get(pregunta.getIndicadorCategoria() - 1).contains(pregunta.getIdPregunta())) {
                pregunta = Pregunta.obtenerPregunta(-1);
            }
            ArrayList<Tupla<Integer, String>> listaRespuestasTuplas = generarRespuestasyPregunta(pregunta);
            int respuesta = Libreria.catchInt(1, 4);
            comprobarRespuesta(respuesta, listaRespuestasTuplas, pregunta);
        }



        //nos interesa el puntaje una vez que termina la ronda, así que ahora mostramos los logros obtenidos
        //por puntaje
        logroDeBusqueda.mostrarLogrosPorPuntos(jugadorActivo);
        listaRespuestas  = new ArrayList<>();
        listaRespuestasTuplas = new ArrayList<>();
        salir2 = true;
    }

    public PartidaIndividual(ArrayList<ArrayList<Integer>> pR, ArrayList<Jugador> lJ, Jugador jA) {
        this.preguntasRealizadas = pR;
        this.listaJugadores = lJ;
        this.jugadorActivo = jA;
    }
}
