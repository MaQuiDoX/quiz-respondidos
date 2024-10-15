import java.util.ArrayList;

public abstract class Logros {
    protected String nombre;
    protected int meta;
    protected static int total = 1;


    public Logros(int num) {

        elegirNombre(num);


    }

    public String getNombre() {
        return nombre;
    }

    public void elegirNombre(int num) {

    }


    public void mostrarLogro(){
        System.out.println(nombre);
    }

    public void comprobar(Jugador jugador, Logros logro) {

        if (!jugador.getLogros().contains(logro)) {
            jugador.getLogros().add(logro);

            meta += meta;
            total ++;
            mostrarLogro();
        }



    }


}
