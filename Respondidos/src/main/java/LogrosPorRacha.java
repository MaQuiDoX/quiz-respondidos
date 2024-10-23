
public class LogrosPorRacha extends Logros{

    protected  int identificador;

    public LogrosPorRacha(int id) {
        this.identificador = id;
    }

    public int getIdentificador() {
        return identificador;
    }

    @Override
    public boolean elegirNombre(Jugador jugador, int racha) {

        if (jugador.getLogros().isEmpty()) {
            this.meta = 5;
        } else {
            buscarMeta(jugador);
        }

        if (racha == meta) {
            this.nombre = "Contestar " +meta+ "  preguntas seguidas";
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







}



