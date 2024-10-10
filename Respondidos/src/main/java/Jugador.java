import java.util.ArrayList;

public class Jugador{
    private String nombre;
    private int puntaje;
    public ArrayList<Poder> poderes; //Los poderes no los ibamos a aplicar en el momento??? Borrar en ese caso. (NACHO)
    public ArrayList<Logro> logros;

    public Jugador(String nombre, int puntaje) {

        this.nombre = nombre;
        this.puntaje = puntaje;
        this.logros= new ArrayList<Logro>();
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

    //Metodo para verificar si el jugador ya tiene el logro
    // public Logros buscarLogro(String nombreLogro) {
    //    for (Logros logro : logros) {
    //        if (logro.getNombre().equals(nombreLogro)) {
    //            return logro;
    //        }
    //    }
    //    return null;
    //    }

    //Añadir el logro si mo existe
    // public boolean añadirLogro(Logro logro) {
    //   if (existe = null) {
    //      logros.add(logro);
    //      return aumentar = true;
    //  } else {return aumentar = false;}
    //}





}


