/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package powers;

import Game.Pregunta;


/**
 *
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
    
    @Override
    public Pregunta gastarPoder(int respuestaUsuario, Pregunta pregunta){
        return Pregunta.obtenerPregunta(respuestaUsuario);
        
    }
}
