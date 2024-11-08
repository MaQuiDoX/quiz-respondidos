/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package powers;

import Game.Pregunta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;


/**
 * CambioPregunta es la clase que representa aquel poder que sirve para que el usuario pueda responder otra pregunta y no la actual.
 * Tener en cuenta que el usuario puede elegir la categoria de la pregunta que quiere responder, pero no sabe qué pregunta le va a tocar.
 *
 * @author Giraudo Ignacio
 * @author Villegas Joaquín
 */
public class CambioPregunta extends Poder {
    private int categoriaElegida;
    public CambioPregunta(int respuestaUsuario){
        this.categoriaElegida = respuestaUsuario;
        this.precio = 25;
    }
    
    /**
     * Uso del poder de cambio de pregunta.
     * @param respuestaUsuario recibe un numero del 1 al 6, cada numero representa una categoria de las distintas que hay para las preguntas.
     * @return una nueva pregunta al azar de la categoria elegida por el usuario.
     *
     * @author Villegas Joaquín
     */
    @Override
    public Pregunta gastarPoder(int respuestaUsuario, ArrayList<ArrayList<Integer>> preguntasRealizadas, Set<Integer> categoriasTerminadas) throws Exception {
        Pregunta pregunta = Pregunta.obtenerPregunta(respuestaUsuario, categoriasTerminadas);

        while (preguntasRealizadas.get(pregunta.getIndicadorCategoria() - 1).contains(pregunta.getIdPregunta())) {

            pregunta = Pregunta.obtenerPregunta(respuestaUsuario, categoriasTerminadas);
        }
        return pregunta;
    }
}
