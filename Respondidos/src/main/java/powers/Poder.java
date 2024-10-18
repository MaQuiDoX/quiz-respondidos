package powers;


import Game.Pregunta;
import utilities.Tupla;
import java.util.ArrayList;

public abstract class Poder {
    protected String nombre;
    protected String descripcion;
    protected int usos;
    protected int precio;
    
    public void gastarPoder(){
        
    }
    
    public void gastarPoder(ArrayList<Tupla<Integer,String>> listaTuplas){
        
    }
    
    public Pregunta gastarPoder(int respuestaUsuario, Pregunta pregunta){
        return null;
    }
    
    public void imprimirInfo(){
        System.out.println("Este es: " + this.nombre + " , " + descripcion);
    }
    
    public void setUsos(){
        usos += 1;
    }
    
    public int getUsos(){
        return usos;
    }

   public int getPrecio(){ return this.precio; }
}
