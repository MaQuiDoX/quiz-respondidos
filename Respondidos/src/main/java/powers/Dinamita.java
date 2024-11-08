package powers;

import powers.Bomba;

/**
 * Dinamita es la clase de mediano nivel de Bomba, la cual solo puede explotar 2 respuestas incorrectas.
 * @author Villegas Joaquin
 */
public class Dinamita extends Bomba {
    /**
     * cantExplosiones es la cantidad de explosiones permitidas por el poder
     */
    private static final int cantExplosiones = 2;

    /**
     * Constructor de la clase Bombita, le asignamos el nombre, la descripcion, el precio y cual es la respuesta correcta de la pregunta.
     * @param respuestaCorrecta
     */
    public Dinamita(String respuestaCorrecta){
       this.nombre = "dinamita";
       this.descripcion = "¡Explota 2 respuestas incorrectas!";
       this.precio = 25;
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
