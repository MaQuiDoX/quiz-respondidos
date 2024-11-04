package Game;


import Game.Logros;
import Game.Jugador;


public class LogrosPorRacha extends Logros{

    LogrosPorRacha(){
        this.tipo = this.getClass().getSimpleName();
    }

    @Override
    public boolean elegirNombre(Jugador jugador, int racha, boolean versus) {

        if (jugador.getLogros().isEmpty()) {
            this.meta = 5;
        } else {
            buscarMeta(jugador);
        }

        if (racha == meta) {
            if (versus){
                this.nombre = "Contestar " +meta+ " preguntas seguidas en partida 1v1";
            } else {this.nombre = "Contestar " +meta+ " preguntas seguidas";}
            return true;

        } else {return false; }
    }

    @Override
    public void buscarMeta(Jugador jugador) {

        int buscar = jugador.getLogros().size()-1;

        if (jugador.getLogros().get(buscar) instanceof LogrosPorRacha) {
            this.meta = jugador.getLogros().get(buscar).getMeta() + 5;
        }
    }

    public void mostrarLogrosPorRacha(Jugador jugador) {
        for (int i = 0; i <= jugador.getLogros().size() - 1; i++) {
            if (jugador.getLogros().get(i) instanceof LogrosPorRacha) {
                mostrarLogro(jugador, i);

            }
        }
    }





}



