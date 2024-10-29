import Game.Pregunta;
import utilities.Tupla;
import utilities.Libreria;
import powers.OtraOportunidad;
import powers.TNT;
import powers.Dinamita;
import powers.Poder;
import powers.Bombita;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import powers.CambioPregunta;

public abstract class Partida {
    protected ArrayList<ArrayList<Integer>> preguntasRealizadas;
    private ArrayList<Jugador> listaJugadores;
    private Jugador jugadorActivo;

    public void iniciarPartida(Jugador jugador) {
        for (int i = 0; i < 6; i++) {
            ArrayList<Integer> subLista = new ArrayList<>();
            preguntasRealizadas.add(subLista);
        }

        Pregunta pregunta = Pregunta.obtenerPregunta(-1);

        if (preguntasRealizadas.get(pregunta.getIndicadorCategoria() - 1).contains(pregunta.getIdPregunta())) {
            pregunta = Pregunta.obtenerPregunta(-1);
        }

        ArrayList<Tupla<Integer, String>> listaRespuestasTuplas = generarRespuestasyPregunta(pregunta);
        int respuesta = Libreria.catchInt(1, 4);
        comprobarRespuesta(respuesta, listaRespuestasTuplas, pregunta);


        //nos interesa el puntaje una vez que termina la ronda, así que ahora mostramos los logros obtenidos
        //por puntaje
        logroDeBusqueda.mostrarLogrosPorPuntos(jugadorActivo);
        listaRespuestas  = new ArrayList<>();
        listaRespuestasTuplas = new ArrayList<>();
        salir2 = true;
    }


    public void tiendaPoderes(Jugador jugador, ArrayList<Tupla<Integer, String>> listaRespuestasTuplas, Pregunta pregunta) {
        //INTRODUCCION LOGICA PARA OPCION 5
        Poder poderAUsar;

        // 1 al 4 respuestas, 5 para poderes (aún implementar)
        int enteroRespuesta = Libreria.catchInt(1, 5);

        System.out.println(" === MENU DE PODERES ===");
        System.out.println("¿Que poder desea usar?:");
        System.out.println("1. Bombita (10p)");
        System.out.println("2. Dinamita (10p)");
        System.out.println("3. TNT (10p)");
        System.out.println("4. cambioPregunta (10p)");
        System.out.println("5. Otra Oportunidad (10p)");

        enteroRespuesta = Libreria.catchInt(1, 6);
        switch (enteroRespuesta) {
            case 1:
                poderAUsar = new Bombita(pregunta.getRespuestaCorrecta());
                if (jugador.getPuntaje() >= poderAUsar.getPrecio()) {
                    jugador.restarPuntaje(poderAUsar.getPrecio());
                    poderAUsar.gastarPoder(listaRespuestasTuplas);
                } else {
                    System.out.println(" ");
                    System.out.println("PUNTAJE INSUFICIENTE");
                    System.out.println(" ");
                }
                break;
            case 2:
                poderAUsar = new Dinamita(pregunta.getRespuestaCorrecta());
                if (jugador.getPuntaje() >= poderAUsar.getPrecio()) {
                    jugador.restarPuntaje(poderAUsar.getPrecio());
                    poderAUsar.gastarPoder(listaRespuestasTuplas);
                } else {
                    System.out.println(" ");
                    System.out.println("PUNTAJE INSUFICIENTE");
                    System.out.println(" ");
                }
                break;
            case 3:
                poderAUsar = new TNT(pregunta.getRespuestaCorrecta());
                if (jugador.getPuntaje() >= poderAUsar.getPrecio()) {
                    jugador.restarPuntaje(poderAUsar.getPrecio());
                    poderAUsar.gastarPoder(listaRespuestasTuplas);
                } else {
                    System.out.println(" ");
                    System.out.println("PUNTAJE INSUFICIENTE");
                    System.out.println(" ");
                }
                break;
            case 4:
                poderAUsar = new CambioPregunta(enteroRespuesta);
                if (jugador.getPuntaje() >= poderAUsar.getPrecio()) {

                    jugador.restarPuntaje(poderAUsar.getPrecio());
                    System.out.println("¿De que categoria desea la pregunta?");
                    System.out.println("1. Arte");
                    System.out.println("2. Entretenimiento");
                    System.out.println("3. Deporte");
                    System.out.println("4. Ciencia");
                    System.out.println("5. Historia");
                    System.out.println("6. UNCuyo");
                    enteroRespuesta = Libreria.catchInt(1, 6);
                    pregunta = poderAUsar.gastarPoder(enteroRespuesta, pregunta);

                    ArrayList<String> listaRespuestas = new ArrayList<>();
                    listaRespuestasTuplas = new ArrayList<>();
                    // Añado respuestas a una lista para mezclarlas y asignarles un numero
                    listaRespuestas.add(pregunta.getRespuestaCorrecta());
                    listaRespuestas.addAll(pregunta.getRespuestasIncorrectas());

                    int contador = 1;

                    Collections.shuffle(listaRespuestas);

                    for (String respuesta : listaRespuestas) {
                        Tupla<Integer, String> tupla = new Tupla<>(contador, respuesta);
                        listaRespuestasTuplas.add(tupla);
                        contador++;
                    }

                } else {
                    System.out.println(" ");
                    System.out.println("PUNTAJE INSUFICIENTE");
                    System.out.println(" ");
                }

                break;
            case 5:
                poderAUsar = new OtraOportunidad();
                if (jugador.getPuntaje() >= poderAUsar.getPrecio()) {
                    jugador.restarPuntaje(poderAUsar.getPrecio());
                } else {
                    System.out.println(" ");
                    System.out.println("PUNTAJE INSUFICIENTE");
                    System.out.println(" ");
                }
                break;

        }
    }

    public ArrayList<Tupla<Integer, String>> generarRespuestasyPregunta(Pregunta pregunta) {


        ArrayList<String> listaRespuestas = new ArrayList<>();
        ArrayList<Tupla<Integer, String>> listaRespuestasTuplas = new ArrayList<>();
        // Añado respuestas a una lista para mezclarlas y asignarles un numero
        listaRespuestas.add(pregunta.getRespuestaCorrecta());
        listaRespuestas.addAll(pregunta.getRespuestasIncorrectas());

        int contador = 1;

        Collections.shuffle(listaRespuestas);

        for (String respuesta : listaRespuestas) {
            Tupla<Integer, String> tupla = new Tupla<>(contador, respuesta);
            listaRespuestasTuplas.add(tupla);
            contador++;
        }

        Libreria.imprimirPregunta(pregunta.getPregunta(), listaRespuestasTuplas);
        return listaRespuestasTuplas;
    }

    public void comprobarRespuesta(int respuestaUsuario, ArrayList<Tupla<Integer, String>> listaRespuestasTuplas, Pregunta pregunta, LogrosPorPuntos logrosDeBusqueda) {

        int numero;
        for (Tupla tuplas : listaRespuestasTuplas) {
            numero = ((Integer) tuplas.getPrimero()).intValue();
            if (numero == respuestaUsuario) {
                if (tuplas.getSegundo() == pregunta.getRespuestaCorrecta()) {
                    System.out.println("Respuesta correcta");
                    //contadorPuntaje++;
                    //puntajeRonda = puntajeRonda + contadorPuntaje;
                    preguntasRealizadas.get(pregunta.getIndicadorCategoria()).add(pregunta.getIdPregunta());

                    //Verifica si el jugador desbloqueó algún logro después de cada pregunta
                    Logros logro = new LogrosPorRacha();
                    boolean comprobar = logro.elegirNombre(jugadorActivo, preguntasRealizadas.size());
                    if (comprobar) {

                        logro.comprobar(jugadorActivo, logro);
                    }

                    //Nos aseguramos de que se creen todos los logros por puntaje, en caso de que la partida
                    //termine con más puntos que la meta inicial (50 puntos)
                    Logros logro1 = new LogrosPorPuntos();
                    boolean comprobar1 = logro1.elegirNombre(jugadorActivo, puntajeRonda);
                    if (comprobar1) {
                        logro1.comprobar(jugadorActivo, logro1);

                    }
                } else {
                    preguntasRealizadas.get(pregunta.getIndicadorCategoria()).add(pregunta.getIdPregunta());
                    System.out.println("Respuesta fallida");
                    contadorPuntaje = 0;
                    salir2 = true;
                    salir1 = true;
                    flagOtroIntento = false;

                    preguntasRealizadas1 = preguntasRealizadas;
                }

                logrosDeBusqueda.mostrarLogrosPorPuntos(jugadorActivo);
                listaRespuestas  = new ArrayList<>();
                listaRespuestasTuplas = new ArrayList<>();
            }
        }
    }
}