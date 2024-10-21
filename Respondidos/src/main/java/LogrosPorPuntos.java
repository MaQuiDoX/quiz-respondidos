

public class LogrosPorPuntos extends Logros{

    int meta = 50;




    @Override
    public void elegirNombre(Jugador jugador, int puntos) {
        if (puntos >= meta && puntos < (meta += meta)) {
            this.nombre = "LLeguar a " +meta+ "  puntos";

        }

    }

}
