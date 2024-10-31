package powers;


import powers.Bomba;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * TNT es la clase de maximo nivel de Bomba, la cual puede explotar 3 respuestas incorrectas.
 * @author Usuario
 */
public class TNT extends Bomba {
    private static final int cantExplosiones = 3;

    public TNT(){
        this.precio = 45;
    }
    public TNT(String respuestaCorrecta){
       this.nombre = "TNT";
       this.descripcion = "¡Explota 3 respuestas incorrectas!";
       this.precio = 45;
       this.respuestaCorrecta = respuestaCorrecta;
    }
    
    @Override
    public int getCantExplosiones(){
        return cantExplosiones;
    }


}
