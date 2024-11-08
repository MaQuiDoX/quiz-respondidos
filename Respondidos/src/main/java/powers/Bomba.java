package powers;


import utilities.Tupla;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Bomba es una sub-clase Abstracta de Poder, tiene como finalidad mostrar los comportamientos iguales de sus hijos.
 * @see Bombita
 * @see Dinamita
 * @see TNT
 * @author Villegas Joaquin
 */
public abstract class Bomba extends Poder {
    /**
     * atributo en el que se guarda la respuesta correcta, para asi no eliminarla en el proceso.
     */
    protected String respuestaCorrecta; 
    
    
    /**
    * Aplica el poder de explosión sobre las respuestas, eliminando algunas 
    * respuestas incorrectas según la cantidad de explosiones permitidas.
    * 
    * @param respuestas Una lista de cuatro (4) tuplas que contienen un índice y una cadena con la respuesta.
    */
    @Override
    public void gastarPoder(ArrayList<Tupla<Integer,String>> respuestas){
        //se inicializan dos variables
        int i = 0;
        int j = 0;
        //Checkea cuantas respuestas hay sin eliminar
        for (Tupla<Integer,String> currentTupla : respuestas){
            if (currentTupla.getSegundo() != "Eliminado"){
                j += 1;
            }
        }
        //Si la cantidad de respuestas sin eliminar es menor o igual a la cantidad que queremos eliminar,
        //no vamos a usar el poder (porque si no, nos quedamos sin respuestas).
        if (j <= getCantExplosiones()){
            System.out.println("¡No podemos usar ese poder!");
            return;
        } 
        //Si llegamos a esta etapa, recorremos entonces la lista de tuplas, en caso de que encontremos 
        //una respuesta incorrecta y no esta eliminada, entonces la eliminamos y la variable i aumenta 1.
        //Tener en cuenta que i tiene que ser menor a la cantidad de explosiones permitidas para que funcione.
        while (i < getCantExplosiones()){
            Iterator<Tupla<Integer,String>> answersIt = respuestas.iterator();
            
            while (answersIt.hasNext()){
                Tupla<Integer,String> currentTupla = answersIt.next();
                
                if (((currentTupla.getSegundo() != respuestaCorrecta) && (currentTupla.getSegundo() != "Eliminado"))
                        && (i < getCantExplosiones())) {
                    currentTupla.setSegundo("Eliminado");
                     i += 1;
                }
               
                
            }
        }
    }
   public abstract int getCantExplosiones();
}
