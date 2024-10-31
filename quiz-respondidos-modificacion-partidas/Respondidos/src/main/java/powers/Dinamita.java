package powers;


import powers.Bomba;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Dinamita es la clase de mediano nivel de Bomba, la cual solo puede explotar 2 respuestas incorrectas.
 * @author Usuario
 */
public class Dinamita extends Bomba {
    private static final int cantExplosiones = 2;

    public Dinamita(){
        this.precio = 25;
    }
    public Dinamita(String respuestaCorrecta){
       this.nombre = "dinamita";
       this.descripcion = "¡Explota 2 respuestas incorrectas!";
       this.precio = 25;
       this.respuestaCorrecta = respuestaCorrecta;
    }
    
    @Override
    public int getCantExplosiones(){
        return cantExplosiones;
    }
    
}
