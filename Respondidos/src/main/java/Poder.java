
import java.util.ArrayList;

public abstract class Poder {
    protected String nombre;
    protected String descripcion;
    protected int usos;
    protected int precio;
    
    protected void gastarPoder(){
        
    }
    
    protected void gastarPoder(ArrayList<Tupla<Integer,String>> listaTuplas){
        System.out.println("Entra aca");
    }
    
    protected void imprimirInfo(){
        System.out.println("Este es: " + this.nombre + " , " + descripcion);
    }
    
    protected void setUsos(){
        usos += 1;
    }
    
    protected int getUsos(){
        return usos;
    }
}
