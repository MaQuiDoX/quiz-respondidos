import utilities.Tupla;

import java.util.ArrayList;

public class LogrosRachaCatgoria extends LogrosPorRacha{

    protected String categoria;
    protected ArrayList<Tupla<Integer, Integer>> contadores;

//    public LogrosRachaCatgoria(int id) {
//        gestionContadores(id);
//
//
//    }

    public void inicializarContadores(){
        contadores = new ArrayList<>();

        for (int i = 0; i <= 5; i++) {
            contadores.add(new Tupla<>(i, 0));
        }
    }

//    public void gestionContadores(int num) {
//        Integer aumentar = contadores.get(num-1).getSegundo();
//        contadores.get(num-1).setSegundo(aumentar++);
//
//    }


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


    @Override
    public boolean elegirNombre(Jugador jugador, int id) {

            int rachaActual = contadores.get(id-1).getSegundo();
            definirCategoria(id);
            buscarMeta(jugador, id);
            if (rachaActual == meta) {
                this.nombre = "LOGRO OBTENIDO: Contestar "+this.meta+" preguntas de "+this.categoria+"en una partida";
                return true;
            } else {return false;}

    }


    public void buscarMeta(Jugador jugador, int id) {

            int rachaActual=contadores.get(id-1).getSegundo();
            if (rachaActual<5) {
                this.meta= 5;
            } else {
                for (int j = 0; j<= jugador.getLogros().size()-1; j++) {
                    if (jugador.getLogros().get(j) instanceof LogrosRachaCatgoria) {
                        this.meta= jugador.getLogros().get(j).getMeta()+5;
                    }
                }

            }

    }
}
