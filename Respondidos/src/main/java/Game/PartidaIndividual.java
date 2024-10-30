package Game;

import Game.Partida;
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
        boolean usoTienda = false;
        
        Pregunta pregunta = null;
        ArrayList<Tupla<Integer, String>> listaRespuestasTuplas = null;
        while (!salir2) {
                System.out.println("////PUNTAJE ACTUAL GANADO: " + jugador.getPuntajePartida() + "////");
                //Si usoTienda = true, significa que el usuario uso la tienda, significa que solo le debemos imprimir la pregunta nomas.
                //Si usoTienda = false, no la uso
                if (!usoTienda){
                    pregunta = Pregunta.obtenerPregunta(-1);
                    //if necesario en caso de errores.
                    if (pregunta == null){
                        System.out.println("Buscando una pregunta...");
                        continue;
                    }
                    if (preguntasRealizadas.get(pregunta.getIndicadorCategoria() - 1).contains(pregunta.getIdPregunta())) {
                        pregunta = Pregunta.obtenerPregunta(-1);
                    }
                    listaRespuestasTuplas = generarRespuestasyPregunta(pregunta);
                } else {
                    Libreria.imprimirPregunta(pregunta.getPregunta(), listaRespuestasTuplas);
                }
                
                int respuesta = Libreria.catchInt(1, 5);
                if (respuesta == 5){
                    //Tupla de:
                    //        1. Tupla que tiene la nueva Pregunta, y Un Arraylist de respuestas en Tupla.
                    //        2. Booleano
                   Tupla<Tupla<Pregunta,ArrayList<Tupla<Integer, String>>>, Boolean> checkUsoPoder = tiendaPoderes(jugador, listaRespuestasTuplas, pregunta);
                   
                   //Si el jugador uso el poder CambioPregunta realizar:
                   if (checkUsoPoder.getPrimero() != null){
                       //le cambiamos la pregunta y sus respuestas.
                       pregunta = checkUsoPoder.getPrimero().getPrimero();
                       listaRespuestasTuplas = checkUsoPoder.getPrimero().getSegundo();
                   }
                   
                   //Si el jugador uso el poder OtraOportunidad y llego un false, respondio correctamente, true en caso contrario:
                   if (checkUsoPoder.getSegundo() != null){
                        if (checkUsoPoder.getSegundo() == false){
                            salir2 = false;
                            //PARA QUE REHAGA OTRA, SI NO VUELVE A REPETIR LA MISMA
                            usoTienda = false;
                            continue;
                        } else {
                            salir2 = true;
                        }
                   }

                   
                    usoTienda = true;
                } else {
                    salir2 = comprobarRespuesta(respuesta, listaRespuestasTuplas, pregunta, new LogrosPorPuntos());
                    usoTienda = false;
                }
                
                

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
