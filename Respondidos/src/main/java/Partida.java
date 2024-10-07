import java.util.ArrayList;
import java.util.Collections;

public class Partida {
    private ArrayList<Tupla<Integer, Integer>> preguntasRealizadas;
    private ArrayList<Jugador> listaJugadores;
    private Jugador jugadorActivo;

    public Partida(ArrayList<Tupla<Integer, Integer>> pR, ArrayList<Jugador> lJ, Jugador jA) {
        this.preguntasRealizadas = pR;
        this.listaJugadores = lJ;
        this.jugadorActivo = jA;
    }

    public void cambiarJugador(Jugador jugador){
        if (jugadorActivo != jugador){
            jugadorActivo = jugador;
        } else {
            System.out.println("El jugador ingresado ya esta jugando");
        }
    }

    public void iniciarPartida(Jugador jugador){
        System.out.println(" === PARTIDA INDIVIDUAL ===");
        System.out.println("1. Iniciar");
        System.out.println("2. Consultar Puntajes");
        System.out.println("3. Salir");
        boolean salir = false;
        ArrayList<String> listaRespuestas = new ArrayList<>();
        ArrayList<Tupla<Integer,String>> listaRespuestasTuplas = new ArrayList<>();
        int opcion = Libreria.catchInt(1,3);

        switch (opcion){
            case 1:
                while (!salir){
                    Pregunta pregunta = Pregunta.obtenerPregunta();
                    listaRespuestas.add(pregunta.getRespuestaCorrecta());
                    listaRespuestas.addAll(pregunta.getRespuestasIncorrectas());

                    int contador = 1;

                    Collections.shuffle(listaRespuestas);

                    for (String respuesta : listaRespuestas){
                        Tupla<Integer, String> tupla = new Tupla<>(contador, respuesta);
                        listaRespuestasTuplas.add(tupla);
                        contador++;
                    }

                    Libreria.imprimirPregunta(pregunta.getPregunta(), listaRespuestasTuplas);

                    int enteroRespuesta = Libreria.catchInt(1,5);
                    int numero;

                    for (Tupla tuplas : listaRespuestasTuplas) {
                        numero = ((Integer) tuplas.getPrimero()).intValue();
                        if (numero == enteroRespuesta) {
                            if (tuplas.getSegundo() == pregunta.getRespuestaCorrecta()) {
                                System.out.println("Respuesta correcta");
                            } else {
                                System.out.println("Respuesta fallida");
                            }
                        }
                    }

                    salir = true;
                    break;
                }
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    public void iniciarPartida(Jugador jugador1, Jugador jugador2){
    }



    public ArrayList<Tupla<Integer, Integer>> getPreguntasRealizadas() {
        return preguntasRealizadas;
    }

    public void setPreguntasRealizadas(ArrayList<Tupla<Integer, Integer>> preguntasRealizadas) {
        this.preguntasRealizadas = preguntasRealizadas;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public Jugador getJugadorActivo() {
        return jugadorActivo;
    }

    public void setJugadorActivo(Jugador jugadorActivo) {
        this.jugadorActivo = jugadorActivo;
    }
}