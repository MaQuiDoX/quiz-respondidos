package Game;


import DAOs.DataBaseDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Representa una pregunta con sus detalles asociados como categoría, respuesta correcta y respuestas incorrectas.
 * @author Quesada Manuel
 * @author Martins Ezequiel
 * @author Villegas Joaquin
 */
public class Pregunta {
    private int indicadorCategoria;
    private int idPregunta;

    // 1: Arte
    // 2: Entretenimiento
    // 3: Deporte
    // 4: Ciencia
    // 5: Historia
    // 6: Uncuyo

    private String pregunta;
    private String respuestaCorrecta;
    private ArrayList<String> respuestasIncorrectas;

    /**
     * Constructor para la clase de Pregunta.
     *
     * @param indicador El indicador de categoría para la pregunta.
     * @param id El identificador único para la pregunta.
     * @param preg El texto de la pregunta.
     * @param respCorr La respuesta correcta a la pregunta.
     * @param respIncorr Una lista de respuestas incorrectas para la pregunta.
     */
    public Pregunta(int indicador, int id, String preg, String respCorr,ArrayList<String> respIncorr){
        this.indicadorCategoria = indicador;
        this.idPregunta = id;
        this.pregunta = preg;
        this.respuestaCorrecta = respCorr;
        this.respuestasIncorrectas = respIncorr;
    }


    /**
     * Obtiene una pregunta aleatoria de la base de datos basada
     * en la categoría proporcionada o elige una categoría aleatoria si el número de categoría proporcionada es -1.
     *
     * @param validarNumero el número de categoría que debe validarse. Si -1, se elegirá una categoría aleatoria.
     * @param categoriasAgotadas un conjunto de números de categoría que están agotados y no deben elegirse.
     * @return Objeto pregunta que contiene la pregunta y sus detalles o nulo si no se encuentra ninguna pregunta.
     * @throws Exception si se produce algún error de acceso a la base de datos
     */
    public static Pregunta obtenerPregunta(int validarNumero, Set<Integer> categoriasAgotadas) throws Exception {
        DataBaseDAO conn = new DataBaseDAO();
        conn.connectDB();

        ResultSet rs = null;
        PreparedStatement obtenerPregDeCategoria = null;

        int numCategoria;

        if (validarNumero == -1) {
            do {
                numCategoria = new Random().nextInt(6)+1;
            } while (categoriasAgotadas.contains(numCategoria));

        } else {
            numCategoria = validarNumero;
        }

        int numPregunta = 0;


        // La tabla maneja a partir del uno, y el random del 0, por eso le sumamos 1

        switch (numCategoria) {
            case 1:
                numPregunta = new Random().nextInt(20) + 1;

                obtenerPregDeCategoria = conn.getConnection().prepareStatement("SELECT * FROM preguntasArte WHERE id = ?");
                obtenerPregDeCategoria.getResultSet();
                obtenerPregDeCategoria.setInt(1, numPregunta);
                rs = obtenerPregDeCategoria.executeQuery();
                break;
            case 2:
                numPregunta = new Random().nextInt(20) + 1;

                obtenerPregDeCategoria = conn.getConnection().prepareStatement("SELECT * FROM preguntasEntretenimiento WHERE id = ?");
                obtenerPregDeCategoria.setInt(1, numPregunta);
                rs = obtenerPregDeCategoria.executeQuery();
                break;
            case 3:
                numPregunta = new Random().nextInt(20) + 1;

                obtenerPregDeCategoria = conn.getConnection().prepareStatement("SELECT * FROM preguntasDeporte WHERE id = ?");
                obtenerPregDeCategoria.setInt(1, numPregunta);
                rs = obtenerPregDeCategoria.executeQuery();
                break;
            case 4:
                numPregunta = new Random().nextInt(20) + 1;

                obtenerPregDeCategoria = conn.getConnection().prepareStatement("SELECT * FROM preguntasCiencia WHERE id = ?");
                obtenerPregDeCategoria.setInt(1, numPregunta);
                rs = obtenerPregDeCategoria.executeQuery();
                break;
            case 5:
                numPregunta = new Random().nextInt(25) + 1;

                obtenerPregDeCategoria = conn.getConnection().prepareStatement("SELECT * FROM preguntasHistoria WHERE id = ?");
                obtenerPregDeCategoria.setInt(1, numPregunta);
                rs = obtenerPregDeCategoria.executeQuery();
                break;
            case 6:
                numPregunta = new Random().nextInt(15) + 1;

                obtenerPregDeCategoria = conn.getConnection().prepareStatement("SELECT * FROM preguntasUncuyo WHERE id = ?");
                obtenerPregDeCategoria.setInt(1, numPregunta);
                rs = obtenerPregDeCategoria.executeQuery();
                break;
            default:
                System.out.println("Índice de categoría incorrecto.");
                break;
        }

        while (rs.next()) {
            String preg = rs.getString("pregunta");
            String respuestaCorrecta = rs.getString("respuestacorrecta");
            ArrayList<String> respuestasIncorr = new ArrayList<>();
            respuestasIncorr.add(rs.getString("respuestaincorrecta1"));
            respuestasIncorr.add(rs.getString("respuestaincorrecta2"));
            respuestasIncorr.add(rs.getString("respuestaincorrecta3"));
            Pregunta pregunta = new Pregunta(numCategoria, numPregunta, preg, respuestaCorrecta, respuestasIncorr);

            conn.disconnectDB();
            return pregunta;
        }
        return null;
    }

    /**
     * Obtiene el indicador de categoría de la pregunta.
     *
     * @return un entero que representa el indicador de categoría de la pregunta.
     */
    public int getIndicadorCategoria() {
        return indicadorCategoria;
    }

    /**
     * Obtiene el texto de la pregunta.
     *
     * @return la cadena de texto que representa la pregunta.
     */
    public String getPregunta() {
        return pregunta;
    }

    /**
     * Obtiene la respuesta correcta de la pregunta.
     *
     * @return la cadena de texto que representa la respuesta correcta de la pregunta.
     */
    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    /**
     * Obtiene la lista de respuestas incorrectas asociadas a la pregunta.
     *
     * @return una lista de cadenas representando las respuestas incorrectas.
     */
    public ArrayList<String> getRespuestasIncorrectas() {
        return respuestasIncorrectas;
    }

    /**
     * Obtiene el identificador único de la pregunta.
     *
     * @return un entero que representa el identificador único de la pregunta.
     */
    public int getIdPregunta() {
        return idPregunta;
    }
}


