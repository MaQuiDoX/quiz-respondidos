package Game;

import java.util.ArrayList;

/**
 * La clase Jugador representa a un jugador, almacenando la información
 * relevante sobre su nombre, puntaje, logros, contraseña, racha, puntaje de partida y el contador
 * de uso de poderes.
 *
 * @author Ferrari Paulina
 * @author Giraudo Ignacio
 * @author Martins Ezequiel
 * @author Quesada Manuel
 * @author Villegas Joaquin
 */
public class Jugador {
    private String nombre;
    private int puntaje;
    public ArrayList<Logros> logros;
    private String contrasena;
    private int racha=0;
    private int puntajePartida;
    private int contadorUsoPoderes;

    /**
     * Constructor para la clase Jugador.
     *
     * @param nombre1 El nombre del jugador.
     * @param contrasena1 La contraseña del jugador.
     * @param puntaje1 El puntaje inicial del jugador.
     * @param logros1 La lista de logros inicial del jugador.
     */
    public Jugador(String nombre1, String contrasena1, int puntaje1, ArrayList<Logros> logros1) {
        this.nombre = nombre1;
        this.puntaje = puntaje1;
        this.contrasena = contrasena1;
        this.logros = logros1;
        this.puntajePartida = 0;
        this.contadorUsoPoderes = 0;
    }

    /**
     * Getter del atributo nombre
     * @return nombre
     *
     * @author Giraudo Ignacio
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter del atributo puntaje
     * @return puntaje
     *
     * @author Giraudo Ignacio
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * El método resta el precio de los poderes al atributo puntajePartida
     * @param precio
     *
     * @author Giraudo Ignacio
     */
    public void restarPuntaje(int precio){
        this.puntaje-=precio;
    }

    /**
     * Getter del atributo contraseña
     * @return contrasena
     *
     * @author Quesada Manuel
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Getter del atributo logros (ArrayList)
     * @return logros
     *
     * @author Giraudo Ignacio
     */
    public ArrayList<Logros> getLogros() {
        return logros;
    }

    /**
     * Agrega un elemento de la clase Logros a la lista de logros del jugador.
     * @param logro
     *
     * @author Giraudo Ignacio
     */
    public void addLogro(Logros logro) {
        this.logros.add(logro);
    }


    /**
    * Getter del atributo contadorUsoPoderes
    * @return contadorUsoPoderes
    *
    * @author Villegas Joaquin
     */
    public int getContadorUsoPoderes() {
        return contadorUsoPoderes;
    }


    /**
     * Setter del atributo nombre
     * @param nombre
     *
     * @author Giraudo Ignacio
     */
    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    /**
     * Setter del atributo puntos
     * @param puntos
     *
     * @author Giraudo Ignacio
     */
    public void sumarPuntos(int puntos) {

        this.puntaje += puntos;
    }

    /**
     * El método incrementa en uno el atributo contadorUsoPoderes.
     *
     * @author Villegas Joaquin
     */
    public void incrementarContadorUsoPoderes() {

        this.contadorUsoPoderes += 1;
    }

    /**
     * El método resetea a cero el atributo contadorUsoPoderes.
     *
     * @author Villegas Joaquin
     */
    public void resetContadorUsoPoderes(){

        this.contadorUsoPoderes = 0;
    }

    /**
     * Imprime por pantalla puntaje del jugador.
     *
     * @author Giraudo Ignacio
     */
    public void mostrarPuntaje() {
        System.out.println(" ");
        System.out.println("PUNTAJE: " + this.puntaje);
        System.out.println(" ");
    }

    /**
     * Imprime por pantalla el nombre, el puntaje y los logros del jugador activo.
     * @param jugador
     *
     * @author Giraudo Ignacio
     */
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
     * El método incrementa en uno la racha (es utilizado cuando el jugador activo responda una pregunta correctamente).
     *
     * @author Giraudo Ignacio
     */
    public void incrementarRacha(){
        this.racha+=1;
    }

    /**
     * El  método resetea la racha a cero (es utilizado cuando el jugador activo responde mal una pregunta).
     *
     * @author Giraudo Ignacio
     */
    public void resetRacha(){
        this.racha=0;
    }

    /**
     * Getter del atributo racha (int).
     * @return racha
     *
     * @author Giraudo Ignacio
     */
    public int getRacha(){
        return this.racha;
    }

    /**
     * Getter del atributo puntajePartida (int).
     * @return puntajePartida
     *
     * @author Villegas Joaquín
     */
    public int getPuntajePartida() {
        return puntajePartida;
    }

    /**
     * Setter del atributo puntajePartida (int).
     * @param puntajePartida
     *
     * @author Villegas Joaquín
     */
    public void setPuntajePartida(int puntajePartida) {
        this.puntajePartida = puntajePartida;
    }
}


