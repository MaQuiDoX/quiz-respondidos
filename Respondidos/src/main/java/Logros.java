import java.util.ArrayList;

public abstract class Logros {
    protected String nombre;
    protected int meta;


    public Logros() {

        this.meta= meta;
    }

    public int getMeta() {
        return meta;
    }

    public String getNombre() {

        return nombre;
    }

    public boolean elegirNombre(Jugador jugador, int num) {

        return false;
    }

    public void buscarMeta(Jugador jugador){

    }

    public void mostrarLogro(Jugador jugador, int ind){
        System.out.println("LOGRO OBTENIDO: " +jugador.getLogros().get(ind).getNombre());
    }

    public void comprobar(Jugador jugador, Logros logro) {

        if (!jugador.getLogros().contains(logro)) {
            jugador.getLogros().add(logro);


            if (logro instanceof LogrosPorRacha) {
                int ind = jugador.getLogros().size()-1;
                mostrarLogro( jugador, ind);
            }
        }
    }

}
