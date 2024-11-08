package powers;

import Game.Pregunta;
import utilities.Tupla;
import java.util.ArrayList;
import java.util.Set;

/**
 * Clase abstracta que representa las caracteristicas basicas de los poderes o habilidades (ayuda al usuario) y su funcionamiento.
 * @author Villegas Joaquin
 */
public abstract class Poder {
    protected String nombre;
    /**
     * descripcion de la accion que se logra al usar el poder
     */
    protected String descripcion;
   
    protected int precio;
    
    public Boolean gastarPoder(){
        return null;
    }
    
    /**
     * Uso del poder, en este caso se necesita alguna informacion para llevarla a cabo.
     * @param listaTuplas ArrayList de la clase Tupla.
     * @see Tupla
     * @see Bomba
     */
    public void gastarPoder(ArrayList<Tupla<Integer,String>> listaTuplas){
        
    }
    
     /**
     * Uso del poder, en este caso se necesita alguna informacion para llevarla a cabo.
     * @param respuestaUsuario dato numerico ingresado por el usuario
     * @see CambioPregunta
     */
    public Pregunta gastarPoder(int respuestaUsuario, ArrayList<ArrayList<Integer>> preguntasRealizadas, Set<Integer> categoriasTerminadas) throws Exception {
        return null;
    }

    /**
     * Obtiene el precio del poder.
     *
     * @return el precio del poder.
     */
    public int getPrecio(){ return this.precio; }
}
