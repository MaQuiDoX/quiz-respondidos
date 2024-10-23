/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package powers;

import Game.Pregunta;


/**
 * CambioPregunta es la clase que representa aquel poder que sirve para que el usuario pueda responder otra pregunta y no la presente.
 * Tener en cuenta que el usuario puede elegir la categoria de la pregunta que quiere responder, pero no sabe que pregunta le va a tocar.
 * @author Usuario
 */
public class CambioPregunta extends Poder {
    private int categoriaElegida;

    public CambioPregunta(){
        this.precio = 25;
    }
    public CambioPregunta(int respuestaUsuario){
        this.categoriaElegida = respuestaUsuario;
        this.precio = 25;
    }

    public int getCategoriaElegida() {
        return categoriaElegida;
    }
    
    /**
     * Uso del poder de cambio de pregunta.
     * @param respuestaUsuario recibe un numero del 1 al 6, cada numero representa una categoria de las distintas que hay para las preguntas. 
     * @param pregunta
     * @return una nueva pregunta al azar de la categoria elegida por el usuario.
     */
    @Override
    public Pregunta gastarPoder(int respuestaUsuario, Pregunta pregunta){
        return Pregunta.obtenerPregunta(respuestaUsuario);
        
    }
}
