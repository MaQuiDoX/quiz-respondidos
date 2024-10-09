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
        int puntajeRonda = 0;
        int contadorPuntaje = 0;
        boolean salir1 = false;
        boolean salir2 = false;
        boolean comprobarExistencia = false;
        ArrayList<String> listaRespuestas = new ArrayList<>();
        ArrayList<Tupla<Integer,String>> listaRespuestasTuplas = new ArrayList<>();

        while (!salir1){
            System.out.println(" === PARTIDA INDIVIDUAL ===");
            System.out.println("Puntaje: " + puntajeRonda);
            System.out.println("1. Responder");
            System.out.println("2. Salir");
            int opcion = Libreria.catchInt(1,2);

            salir2 = false;
            switch (opcion){
                case 1:
                    while (!salir2){

                        Pregunta pregunta = Pregunta.obtenerPregunta();
                        System.out.println("Preguntas realizadas antes:");
                        for (Tupla tupla : preguntasRealizadas){
                            System.out.print("<"+tupla.getPrimero()+","+tupla.getSegundo()+">    ");
                        }
                        System.out.println();
                        do {
                            Tupla tuplaExistencia = new Tupla(pregunta.getIndicadorCategoria(),pregunta.getIndicadorCategoria());
                            if (!preguntasRealizadas.isEmpty()){
                                if (preguntasRealizadas.contains(tuplaExistencia)){
                                    comprobarExistencia = false;
                                    pregunta = Pregunta.obtenerPregunta();
                                } else {
                                    comprobarExistencia = true;
                                }
                            } else {
                                comprobarExistencia = true;
                            }
                        } while (!comprobarExistencia);
                        comprobarExistencia = false;

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
                                    contadorPuntaje++;
                                    puntajeRonda = puntajeRonda + contadorPuntaje;
                                    preguntasRealizadas.add(new Tupla<>(pregunta.getIndicadorCategoria(),pregunta.getIdPregunta()));
                                } else {
                                    //System.out.println("Respuesta fallida");
                                    //contadorPuntaje = 0;
                                    //salir2 = true;
                                    //salir1 = true;
                                    preguntasRealizadas.add(new Tupla<>(pregunta.getIndicadorCategoria(),pregunta.getIdPregunta()));

                                }
                            }

                        }
                        listaRespuestas  = new ArrayList<>();
                        listaRespuestasTuplas = new ArrayList<>();
                        salir2 = true;
                        break;
                    }
                    break;
                case 2:
                    salir1 = true;
                    break;
            }
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