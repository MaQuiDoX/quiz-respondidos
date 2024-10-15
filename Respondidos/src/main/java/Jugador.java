import java.util.ArrayList;


public class Jugador {
    private String nombre;
    private int puntaje;
    public ArrayList<Poder> poderes; //Los poderes no los ibamos a aplicar en el momento??? Borrar en ese caso. (NACHO)
    public ArrayList<Logros> logros;

    public Jugador(String nombre, int puntaje) {

        this.nombre = nombre;
        this.puntaje = puntaje;
        this.logros = new ArrayList<Logros>();
        //AGREGAR METODO "AGREGARJUGADOR" DE PODIO.
    }

    //GETTERS
    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public ArrayList<Poder> getPoderes() {
        return poderes;
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



}


