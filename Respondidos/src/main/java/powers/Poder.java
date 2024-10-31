package powers;


import Game.Pregunta;
import utilities.Tupla;
import java.util.ArrayList;

/**
 * Clase abstracta que representa las caracteristicas basicas de los poderes o habilidades (ayuda al usuario) y su funcionamiento.
 * @author Usuario
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
     * uso del poder, en este caso se necesita alguna informacion para llevarla a cabo.
     * @param listaTuplas ArrayList de la clase Tupla.
     * @see Tupla
     * @see Bomba
     */
    public void gastarPoder(ArrayList<Tupla<Integer,String>> listaTuplas){
        
    }
    
     /**
     * uso del poder, en este caso se necesita alguna informacion para llevarla a cabo.
     * @param respuestaUsuario dato numerico ingresado por el usuario
     * @see CambioPregunta
     */
    public Pregunta gastarPoder(int respuestaUsuario){
        return null;
    }
    
    public void imprimirInfo(){
        System.out.println("Este es: " + this.nombre + " , " + descripcion);
    }
    

   public int getPrecio(){ return this.precio; }
}
