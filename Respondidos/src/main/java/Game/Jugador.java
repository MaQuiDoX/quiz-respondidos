package Game;

import powers.Poder;
import java.util.ArrayList;

//puntaje son los puntos en total que lleva el jugador en el programa, puntajePartida son los puntos que lleva en la partida
public class Jugador {
    private String nombre;
    private int puntaje;
    private int racha=0;
    public ArrayList<Poder> poderes; //Los poderes no los ibamos a aplicar en el momento??? Borrar en ese caso. (NACHO)
    public ArrayList<Logros> logros;
    private int puntajePartida;


    public Jugador(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.logros = new ArrayList<Logros>();
        //uno para racha y otro para puntos?
        this.puntajePartida = 0;
    }

    //GETTERS
    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void restarPuntaje(int precio){
        this.puntaje-=precio;
    }

    public ArrayList<Logros> getLogros() {
        return logros;
    }

    //SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void sumarPuntos(int puntos) {
        this.puntaje += puntos;
    }

    //MÉTODOS
    public void mostrarPuntaje() {
        System.out.println(" ");
        System.out.println("Puntaje: " + this.puntaje);
        System.out.println(" ");
    }


    /**
     * El primer método incrementa en uno la racha(será utilizado cuando responda una pregunta correctamente)
     * El segundo método resetea la racha (será utilizado cuando el jugador responda mal una pregunta)
     * El tercer y último método nos brinda la racha.
     */
    public void incrementarRacha(){
        this.racha+=1;
    }

    public void resetRacha(){
        this.racha=0;
    }

    public int getRacha(){
        return this.racha;
    }
    
    public int getPuntajePartida() {
        return puntajePartida;
    }

    public void setPuntajePartida(int puntajePartida) {
        this.puntajePartida = puntajePartida;
    }
}


