import Game.Pregunta;
import utilities.Libreria;
import utilities.Tupla;

import java.util.ArrayList;

public class PartidaIndividual extends Partida {

    public void iniciarPartida(Jugador jugador) {
        LogrosPorPuntos logroDeBusqueda = new LogrosPorPuntos();
        ArrayList<Tupla<Integer, String>> listaRespuestasTuplas;
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
            listaRespuestasTuplas = generarRespuestasyPregunta(pregunta);
            int respuesta = Libreria.catchInt(1, 4);
            salir2 = comprobarRespuesta(respuesta, listaRespuestasTuplas, pregunta, new LogrosPorPuntos());
        }



        //nos interesa el puntaje una vez que termina la ronda, así que ahora mostramos los logros obtenidos
        //por puntaje
        //logroDeBusqueda.mostrarLogrosPorPuntos(jugadorActivo);
   
        listaRespuestasTuplas = new ArrayList<>();
        
    }

    public PartidaIndividual(ArrayList<ArrayList<Integer>> pR, ArrayList<Jugador> lJ, Jugador jA) {
        this.preguntasRealizadas = pR;
        this.listaJugadores = lJ;
        this.jugadorActivo = jA;
    }
}
