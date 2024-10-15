
public class LogrosPorRacha extends Logros{

    int meta = 5;

    public LogrosPorRacha(int racha) {
        super(racha);
    }


    @Override
    public void elegirNombre(int racha) {
        if (racha >= meta && racha < (meta += meta)) {
            this.nombre = "Contestar " +meta+ "  preguntas seguidas";

        }
    }


}
