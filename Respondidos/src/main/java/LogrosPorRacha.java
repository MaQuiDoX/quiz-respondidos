
public class LogrosPorRacha extends Logros{






    public void elegirNombre(Jugador jugador, int racha) {

        if (jugador.getLogros().isEmpty()) {
            this.meta = 5;
        } else {
            buscarMeta(jugador);
        }

        System.out.println("NUEVA META: "+meta);

        if (racha == meta) {
            this.nombre = "Contestar " +meta+ "  preguntas seguidas";
            necesitaComprobar = true;


        }


    }

    @Override
    public void buscarMeta(Jugador jugador) {

        int buscar = jugador.getLogros().size()-1;

        this.meta = jugador.getLogros().get(buscar).getMeta() +5;


    }
}



