
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
    
    
    
    @Override
    protected void gastarPoder(ArrayList<Tupla<Integer,String>> respuestas){
        int i = 0;
        while (i < getCantExplosiones()){
            Iterator<Tupla<Integer,String>> answersIt = respuestas.iterator();
            
            while (answersIt.hasNext()){
                Tupla<Integer,String> currentTupla = answersIt.next();
                
                if (currentTupla.getSegundo() != respuestaCorrecta){
                    answersIt.remove();
                }
                i += 1;
                
                if (i > getCantExplosiones()){
                    break;
                } 
            }
        }
    }
   protected abstract int getCantExplosiones();
}
