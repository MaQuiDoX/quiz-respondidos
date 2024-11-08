package powers;

import Game.LogrosPorPuntos;
import Game.Partida;
import Game.Pregunta;
import java.util.ArrayList;
import powers.Poder;
import utilities.Libreria;
import utilities.Tupla;

/**
 * OtraOportunidad es la clase que sirve para mostrar en la ejecucion del programa, que el usuario puede responder otra vez en caso de equivocarse. 
 * @author Villegas Joaquin
 */
public class OtraOportunidad extends Poder{
    /**
     * listaRespuestasTuplas es el atributo que guarda las respuestas de la pregunta y el numero asignado
     */
    ArrayList<Tupla<Integer, String>> listaRespuestasTuplas;
    /**
     * Pregunta que se va a responder
     */
    Pregunta pregunta;
    /**
     * La partida en la que se esta jugando actualmente, contiene informacion valiosa.
     */
    Partida partida;
    /**
     * En caso de que hayan logros en la partida
     */
    LogrosPorPuntos logrosDeBusqueda;
    /**
     *
     */
    ArrayList<Integer> noConsiderar;

    /**
     * Constructor de la clase OtraOportunidad. Inicializa el poder con la lista de respuestas,
     * la pregunta, la partida actual y los índices a ignorar.
     *
     * @param listaRespuestasTuplas Lista de tuplas que contiene las respuestas posibles junto con su número asignado.
     * @param pregunta La pregunta a responder.
     * @param partida La partida actual en la que se está jugando.
     * @param ignorar Lista de índices de respuestas a ignorar.
     */
    public OtraOportunidad(ArrayList<Tupla<Integer, String>> listaRespuestasTuplas, Pregunta pregunta, Partida partida, ArrayList<Integer> ignorar){
        this.precio = 30;
        this.listaRespuestasTuplas = listaRespuestasTuplas;
        this.pregunta = pregunta;
        this.partida = partida;
        this.noConsiderar = ignorar;
    }

    /**
     * Permite al usuario gastar poder para responder una pregunta con dos oportunidades.
     * La función imprime la pregunta y posibles respuestas, permite al usuario
     * seleccionar una respuesta, y verifica si esta es correcta.
     *
     * @return true si la respuesta del usuario es correcta en cualquiera de las dos oportunidades;
     *         false en caso contrario.
     */
    @Override
    public Boolean gastarPoder(){
        System.out.println("¡Tienes dos oportunidades, aprovechalas!");
        int respuestaUsuario;
        boolean resultado = false;
        for (int i = 0; i < 2; i++){
            Libreria.imprimirPregunta(this.pregunta.getPregunta(), this.listaRespuestasTuplas);
            respuestaUsuario = Libreria.catchInt(1, 4);
            resultado = this.partida.comprobarRespuesta(respuestaUsuario, this.listaRespuestasTuplas, this.pregunta, logrosDeBusqueda, noConsiderar );
            
            if (!resultado){
                break;
            } else {
                System.out.println("¡Respuesta fallida, intentalo otra vez!");
            }
        }
        return resultado;
    }
}
