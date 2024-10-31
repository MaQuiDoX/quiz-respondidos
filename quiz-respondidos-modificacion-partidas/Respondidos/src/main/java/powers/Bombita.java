package powers;


import powers.Bomba;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Bombita es la clase de menor nivel de Bomba, la cual solo puede explotar 1 respuesta incorrecta.
 * @author Usuario
 */
public class Bombita extends Bomba {
    private static final int cantExplosiones = 1;

    public Bombita(){
        this.precio = 15;
    }
    public Bombita(String respuestaCorrecta){
        this.nombre = "bombita";
        this.descripcion = "¡Explota 1 respuesta incorrecta!";
        this.precio = 15;
        this.respuestaCorrecta = respuestaCorrecta;
    }
    
    @Override
    public int getCantExplosiones(){
        return cantExplosiones;
    }
    
 
}
