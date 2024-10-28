import utilities.Tupla;

import java.util.ArrayList;

public class LogrosRachaCatgoria extends Logros{

    //String categoria;


// public LogrosRachaCatgoria(int id, int preguntasRespondidas){
//    definirCategoria(id);
//
//          }




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
    public boolean elegirNombre(Jugador jugador, int tamanioArray) {

        // buscarMeta(preguntasRespondidas);
        //if (tamanioArray == this.meta) {
        //   this.nombre= "LOGRO OBTENIDO: Contestar "+this.meta+" preguntas de "+this.categoria+"en una partida";
        //   return true
        //  }else{return false;}

            int rachaActual = contadores.get(id-1).getSegundo();
            definirCategoria(id);
            buscarMeta(jugador, id);
            if (rachaActual == meta) {
                this.nombre = "LOGRO OBTENIDO: Contestar "+this.meta+" preguntas de "+this.categoria+"en una partida";
                return true;
            } else {return false;}

    }



    public void buscarMeta(Jugador jugador, String categoria) {

        //if (jugador.getLogros())
        //for(int i = 0; i<=jugador.getLogros.size()-1; i++) {
        //      if(jugador.getLogros.get(i) instanceof LogrosRachaCatgoria && jugador.getLogros.get(i).getCategoria = categoria) {
        //         this.meta = jugador.getLogros.get(i).getMeta +5
        //      }else{this.meta = 5}
        //   }
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
