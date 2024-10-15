

public class LogrosPorPuntos extends Logros{

    int meta = 50;

    public LogrosPorPuntos(int puntos) {
        super( puntos);


    }


    @Override
    public void elegirNombre(int puntos) {
        if (puntos >= meta && puntos < (meta += meta)) {
            this.nombre = "LLeguar a " +meta+ "  puntos";

        }

    }

}
