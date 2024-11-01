package Game;


import DAOs.DataBaseDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

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


    public Pregunta(int indicador, int id, String preg, String respCorr,ArrayList<String> respIncorr){
        this.indicadorCategoria = indicador;
        this.idPregunta = id;
        this.pregunta = preg;
        this.respuestaCorrecta = respCorr;
        this.respuestasIncorrectas = respIncorr;
    }


    /**
     * Función que al ser llamada devuelve un objeto Pregunta construído a partir del acceso aleatorio a cualquier lista de preguntas de la base de datos.
     * @return Objeto pregunta
     */
    public static Pregunta obtenerPregunta(int validarNumero) throws Exception {
        DataBaseDAO conn = new DataBaseDAO();
        conn.connectDB();

        ResultSet rs = null;
        PreparedStatement obtenerPregDeCategoria = null;

        int numCategoria = new Random().nextInt(6)+1;

//        if (validarNumero == -1) {
//            numCategoria = 5;
//        } else {
//            numCategoria = validarNumero;
//        }

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

    public int getIndicadorCategoria() {
        return indicadorCategoria;
    }

    public void setIndicadorCategoria(int indicadorCategoria) {
        this.indicadorCategoria = indicadorCategoria;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public ArrayList<String> getRespuestasIncorrectas() {
        return respuestasIncorrectas;
    }

    public void setRespuestasIncorrectas(ArrayList<String> respuestasIncorrectas) {
        this.respuestasIncorrectas = respuestasIncorrectas;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }
}


