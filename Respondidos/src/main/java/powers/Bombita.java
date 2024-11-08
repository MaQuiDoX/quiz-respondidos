package powers;


import powers.Bomba;

/**
 * Bombita es la clase de menor nivel de Bomba, la cual solo puede explotar 1 respuesta incorrecta.
 * @author Villegas Joaquin
 */
public class Bombita extends Bomba {
    /**
     * cantExplosiones es la cantidad de explosiones permitidas por el poder
     */
    private static final int cantExplosiones = 1;

    /**
     * Constructor de la clase Bombita, le asignamos el nombre, la descripcion, el precio y cual es la respuesta correcta de la pregunta.
     * @param respuestaCorrecta
     */
    public Bombita(String respuestaCorrecta){
        this.nombre = "bombita";
        this.descripcion = "¡Explota 1 respuesta incorrecta!";
        this.precio = 15;
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
