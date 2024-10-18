package powers;


import utilities.Tupla;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */


public abstract class Bomba extends Poder {
    protected String respuestaCorrecta; //PARA IDENTIFICAR AQUELLA LA QUE NO TENEMOS QUE ELIMINAR
    
    
    //¿Y si en vez de eliminar le ponemos como "Eliminada"? asi no pensamos una logica la cual sea reponer el numero de la respuesta correcta
    //La eliminacion la esta haciendo lineal, si uno se llega a dar cuenta puede sacar facil las respuestas correctas. (¿implementarlo de otra forma o dejarlo asi?)
    @Override
    public void gastarPoder(ArrayList<Tupla<Integer,String>> respuestas){
        int i = 0;
        int j = 0;
        for (Tupla<Integer,String> currentTupla : respuestas){
            if (currentTupla.getSegundo() != "eliminado"){
                j += 1;
            }
        }
        
        if (j <= getCantExplosiones()){
            System.out.println("¡No podemos usar ese poder!");
            return;
        } 
        
        while (i < getCantExplosiones()){
            Iterator<Tupla<Integer,String>> answersIt = respuestas.iterator();
            
            while (answersIt.hasNext()){
                Tupla<Integer,String> currentTupla = answersIt.next();
                
                if (((currentTupla.getSegundo() != respuestaCorrecta) && (currentTupla.getSegundo() != "eliminado"))
                        && (i < getCantExplosiones())) {
                    currentTupla.setSegundo("eliminado");
                     i += 1;
                }
               
                
            }
        }
    }
   public abstract int getCantExplosiones();
}
