package powers;


import Game.LogrosPorPuntos;
import Game.Partida;
import Game.Pregunta;
import java.util.ArrayList;
import powers.Poder;
import utilities.Libreria;
import utilities.Tupla;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * OtraOportunidad es la clase que sirve para mostrar en la ejecucion del programa, que el usuario puede responder otra vez en caso de equivocarse. 
 * @author Usuario
 */
public class OtraOportunidad extends Poder{
    
    ArrayList<Tupla<Integer, String>> listaRespuestasTuplas;
    Pregunta pregunta;
    Partida partida;
    LogrosPorPuntos logrosDeBusqueda;
    
    public OtraOportunidad(ArrayList<Tupla<Integer, String>> listaRespuestasTuplas, Pregunta pregunta, Partida partida){
        this.precio = 30;
        this.listaRespuestasTuplas = listaRespuestasTuplas;
        this.pregunta = pregunta;
        this.partida = partida;
    }
    @Override
    public Boolean gastarPoder(){
        System.out.println("¡Tienes dos oportunidades, aprovechalas!");
        int respuestaUsuario;
        boolean resultado = false;
        for (int i = 0; i < 2; i++){
            Libreria.imprimirPregunta(this.pregunta.getPregunta(), this.listaRespuestasTuplas);
            respuestaUsuario = Libreria.catchInt(1, 4);
            resultado = this.partida.comprobarRespuesta(respuestaUsuario, this.listaRespuestasTuplas, this.pregunta, logrosDeBusqueda);
            
            if (!resultado){
                
                break;
            } else {
                System.out.println("¡Respuesta fallida, intentalo otra vez!");
            }
        }
        return resultado;
    }
}
