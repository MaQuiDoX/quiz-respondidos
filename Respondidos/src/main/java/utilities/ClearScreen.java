package utilities;

/**
 * Clase encargada de eliminar lo mostrado en consola
 * @version 1.1, 18/9/23
 * @author Quesada Manuel
 */
public class ClearScreen {
    /**
     * Metodo que se encarga de detectar el sistema operativo de la computadora, y dependiendo de este, opte por eliminar lo mostrado en consola de una forma u otra.
     */
    public static void cls() {
        try {
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
