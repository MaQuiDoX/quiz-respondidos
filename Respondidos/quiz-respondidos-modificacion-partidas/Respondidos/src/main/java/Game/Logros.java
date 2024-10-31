package Game;

import Game.Jugador;
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

    public boolean elegirNombre(Jugador jugador, int num, boolean versus) {

        return false;
    }

    public void buscarMeta(Jugador jugador){

    }

    public void mostrarLogro(Jugador jugador, int ind){
        System.out.println("LOGRO OBTENIDO: " +jugador.getLogros().get(ind).getNombre());
    }

    public void comprobar(Jugador jugador, Logros logro) {


            for (int i=0; i<=jugador.getLogros().size()-1; i++) {
                String existe = jugador.getLogros().get(i).getNombre();
                if (!existe.equals(logro.nombre)){

                    jugador.getLogros().add(logro);


                    if (logro instanceof LogrosPorRacha) {
                        int ind = jugador.getLogros().size()-1;
                        mostrarLogro( jugador, ind);
                    }

                    if(logro instanceof LogrosRachaCatgoria){
                        System.out.println(this.nombre);
                    }

                }
            }




    }

}
