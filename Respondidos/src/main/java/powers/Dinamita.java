package powers;


import powers.Bomba;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
public class Dinamita extends Bomba {
    private static final int cantExplosiones = 2;
    
    public Dinamita(String respuestaCorrecta){
       this.nombre = "dinamita";
       this.descripcion = "¡Explota 2 respuestas incorrectas!";
       this.precio = 35;
       this.usos = 0;
       this.respuestaCorrecta = respuestaCorrecta;
    }
    
    @Override
    public int getCantExplosiones(){
        return cantExplosiones;
    }
    
}
