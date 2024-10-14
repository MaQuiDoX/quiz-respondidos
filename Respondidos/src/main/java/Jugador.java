import java.util.ArrayList;

public class Jugador{
    private String nombre;
    private int puntaje;
    public ArrayList<Logro> logros;
    private int racha=0;

    public Jugador(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.logros= new ArrayList<Logro>();
    }
    //GETTERS
    public String getNombre() {
        return nombre;
    }
    public int getPuntaje() {
        return puntaje;
    }
    public ArrayList<Logro> getLogros() {
        return logros;
    }
    //SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void sumarPuntos(int puntos){
        this.puntaje += puntos;
    }

    //MÉTODOS
    public void mostrarPuntaje(){
        System.out.println(" ");
        System.out.println("Puntaje: "+ this.puntaje);
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
}
