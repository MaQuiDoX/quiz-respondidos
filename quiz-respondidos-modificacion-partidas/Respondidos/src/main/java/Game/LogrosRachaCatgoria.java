package Game;

import Game.LogrosPorRacha;
import Game.Jugador;
import utilities.Tupla;

import java.util.ArrayList;

public class LogrosRachaCatgoria extends Logros{

    public void recorrer(ArrayList<Integer> preguntas, int id, Jugador jugador, boolean versus) {
        for(int i = 0; i <= preguntas.size() - 1; ++i) {
            if (i % 5 == 0 && i !=0) {
                LogrosRachaCatgoria logro = new LogrosRachaCatgoria();
                this.meta = i;
                this.nombrar(definirCategoria(id), versus);
                comprobar(jugador, logro);
            }
        }

    }

    public String definirCategoria(int numero) {
        switch (numero) {
            case 1 :
                return "Arte";

            case 2 :
                return "Entretenimiento";

            case 3:
                return "Deporte";

            case 4 :
                return "Ciencia";

            case 5 :
                return "Historia";

            case 6 :
                return "UNCuyo";



        }return "";
    }

    public void nombrar(String categoria, boolean versus) {
        if (versus) {
            this.nombre = "LOGRO OBTENIDO: Contestar " + this.meta + " preguntas de " + categoria + "en una partida 1v1";
        } else this.nombre = "LOGRO OBTENIDO: Contestar " + this.meta + " preguntas de " + categoria + "en una partida";
    }
}
