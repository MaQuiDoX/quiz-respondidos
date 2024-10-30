package Game;

import Game.LogrosPorRacha;
import Game.Jugador;
import utilities.Tupla;

import java.util.ArrayList;

public class LogrosRachaCatgoria extends LogrosPorRacha{

    protected String categoria;
    protected int meta;
    protected ArrayList<Tupla<Integer, Integer>> contadores;

    public LogrosRachaCatgoria(int id) {
        super(id);


    }

    public void inicializarContadores(){
        contadores = new ArrayList<>();

        for (int i = 1; i <= 6; i++) {
            contadores.add(new Tupla<>(i, 0));
        }
    }

    public void gestionContadores(int num) {
        Integer aumentar = contadores.get(num).getSegundo();
        contadores.get(num).setSegundo(aumentar++);
        definirCategoria(num);
    }


    public void definirCategoria(int numero) {
        switch (numero) {
            case 1: categoria = "Arte";

            case 2: categoria = "Entretenimiento";

            case 3: categoria = "Deporte";

            case 4: categoria = "Ciencia";

            case 5: categoria = "Historia";

            case 6: categoria = "UNCuyo";

        }

    }

    public void recorrer(Jugador jugador) {

        for (int i = 0; i<=jugador.getLogros().size()-1; i++) {
            if (jugador.getLogros().get(i) instanceof LogrosPorRacha) {
                int elegir = ((LogrosPorRacha) jugador.getLogros().get(i)).getIdentificador();
                gestionContadores(elegir);
            }
        }
    }

    @Override
    public void buscarMeta(Jugador jugador) {
        for (int i = 0; i<= jugador.getLogros().size()-1; i++) {

        }
    }
}
