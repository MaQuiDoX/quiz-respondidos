package powers;


import powers.Bomba;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * TNT es la clase de maximo nivel de Bomba, la cual puede explotar 3 respuestas incorrectas.
 * @author Villegas Joaquin
 */
public class TNT extends Bomba {
    /**
     * cantExplosiones es la cantidad de explosiones permitidas por el poder
     */
    private static final int cantExplosiones = 3;

    /**
     * Constructor de la clase TNT, le asignamos el nombre, la descripcion, el precio y cual es la respuesta correcta de la pregunta.
     * @param respuestaCorrecta
     */
    public TNT(String respuestaCorrecta){
       this.nombre = "TNT";
       this.descripcion = "¡Explota 3 respuestas incorrectas!";
       this.precio = 45;
       this.respuestaCorrecta = respuestaCorrecta;
    }

    /**
     * Getter de la cantidad de Explosiones
     * @return entero que muestra la cantidad.
     */
    @Override
    public int getCantExplosiones(){
        return cantExplosiones;
    }


}
