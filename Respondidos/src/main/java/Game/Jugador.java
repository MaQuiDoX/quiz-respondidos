package Game;

import powers.Poder;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;

//puntaje son los puntos en total que lleva el jugador en el programa, puntajePartida son los puntos que lleva en la partida
public class Jugador {
    private String nombre;
    private int puntaje;
    private int racha=0;
    public ArrayList<Logros> logros;
    private int puntajePartida;
    private int contadorUsoPoderes;
    public ArrayList<Integer> idsPartidasActivas;

    public Jugador(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.logros = new ArrayList<Logros>();
        this.idsPartidasActivas = new ArrayList<>();
        //uno para racha y otro para puntos?
        this.puntajePartida = 0;
        this.contadorUsoPoderes = 0;
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

    public int getContadorUsoPoderes() {
        return contadorUsoPoderes;
    }
    //SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void sumarPuntos(int puntos) {
        this.puntaje += puntos;
    }

    public void incrementarContadorUsoPoderes() {
        this.contadorUsoPoderes += 1;
    }
    
    public void resetContadorUsoPoderes(){
        this.contadorUsoPoderes = 0;
    }
    //MÉTODOS
    public void mostrarPuntaje() {
        System.out.println(" ");
        System.out.println("PUNTAJE: " + this.puntaje);
        System.out.println(" ");
    }

    public void printEstadisticas(Jugador jugador){
        System.out.println("--- ESTADISTICAS ---");
        System.out.println("USUARIO: "+ jugador.nombre);
        mostrarPuntaje();
        System.out.println("LOGROS");
        for (Logros logro : this.logros){
            System.out.println("-"+logro.nombre);
        }
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


