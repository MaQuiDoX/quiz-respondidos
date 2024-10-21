import java.util.ArrayList;

public abstract class Logros {
    protected String nombre;
    protected int meta;
    protected boolean necesitaComprobar;



    public Logros() {

        necesitaComprobar = false;
        this.meta= meta;


    }

    public int getMeta() {
        return meta;
    }

    public String getNombre() {

        return nombre;
    }

    public boolean isNecesitaComprobar() {
        return necesitaComprobar;
    }

    public void elegirNombre(Jugador jugador, int num) {

    }

    public void buscarMeta(Jugador jugador){

    }

    public void mostrarLogro(){
        System.out.println("LOGRO OBTENIDO: " +nombre);
    }

    public void comprobar(Jugador jugador, Logros logro) {

        if (!jugador.getLogros().contains(logro)) {
            jugador.getLogros().add(logro);



            mostrarLogro();

        }



    }


}
