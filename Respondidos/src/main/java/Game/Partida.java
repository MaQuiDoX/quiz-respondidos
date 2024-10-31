package Game;

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
    protected ArrayList<Jugador> listaJugadores;
    protected Jugador jugadorActivo;
    //maybe esta no es la mejor forma
    boolean partidaVersus= false;

    public void iniciarPartida(Jugador jugador) {

    }
    
    public void iniciarPartida(Jugador j1, Jugador j2){
        
    }

    public void turnoJugador(Jugador jugador){
        boolean salir2 = false;
        boolean usoTienda = false;
        
        Pregunta pregunta = null;
        ArrayList<Tupla<Integer, String>> listaRespuestasTuplas = null;
        while (!salir2) {
                System.out.println("////PUNTAJE ACTUAL GANADO: " + jugador.getPuntajePartida() + "////");
                //Si usoTienda = true, significa que el usuario uso la tienda, significa que solo le debemos imprimir la pregunta nomas.
                //Si usoTienda = false, no la uso
                if (!usoTienda){
                    pregunta = Pregunta.obtenerPregunta(-1);
                    //if necesario en caso de errores.
                    if (pregunta == null){
                        System.out.println("Buscando una pregunta...");
                        continue;
                    }
                    if (preguntasRealizadas.get(pregunta.getIndicadorCategoria() - 1).contains(pregunta.getIdPregunta())) {
                        pregunta = Pregunta.obtenerPregunta(-1);
                    }
                    listaRespuestasTuplas = generarRespuestasyPregunta(pregunta);
                } else {
                    Libreria.imprimirPregunta(pregunta.getPregunta(), listaRespuestasTuplas);
                }
                
                int respuesta = Libreria.catchInt(1, 5);
                if (respuesta == 5){
                    jugador.incrementarContadorUsoPoderes();
                    //Tupla de:
                    //        1. Tupla que tiene la nueva Pregunta, y Un Arraylist de respuestas en Tupla.
                    //        2. Booleano
                   Tupla<Tupla<Pregunta,ArrayList<Tupla<Integer, String>>>, Boolean> checkUsoPoder = tiendaPoderes(jugador, listaRespuestasTuplas, pregunta);
                   
                   //Si el jugador uso el poder CambioPregunta realizar:
                   if (checkUsoPoder.getPrimero() != null){
                       //le cambiamos la pregunta y sus respuestas.
                       pregunta = checkUsoPoder.getPrimero().getPrimero();
                       listaRespuestasTuplas = checkUsoPoder.getPrimero().getSegundo();
                   }
                   
                   //Si el jugador uso el poder OtraOportunidad y llego un false, respondio correctamente, true en caso contrario:
                   if (checkUsoPoder.getSegundo() != null){
                        if (checkUsoPoder.getSegundo() == false){
                            salir2 = false;
                            //PARA QUE REHAGA OTRA, SI NO VUELVE A REPETIR LA MISMA
                            usoTienda = false;
                            continue;
                        } else {
                            salir2 = true;
                        }
                   }

                   
                    usoTienda = true;
                } else {
                    salir2 = comprobarRespuesta(respuesta, listaRespuestasTuplas, pregunta, new LogrosPorPuntos());

                    usoTienda = false;
                }
                

        }
        LogrosPorPuntos logroDeBusqueda = new LogrosPorPuntos();
        logroDeBusqueda.mostrarLogrosPorPuntos(jugadorActivo);
        LogrosRachaCatgoria logroEntrada= new LogrosRachaCatgoria();
        for (int i = 0; i<=5; i++){
            logroEntrada.recorrer(preguntasRealizadas.get(i), i, jugadorActivo, partidaVersus);

        }
    }
    
    public Tupla<Tupla<Pregunta,ArrayList<Tupla<Integer, String>>>, Boolean> tiendaPoderes(Jugador jugador, ArrayList<Tupla<Integer, String>> listaRespuestasTuplas, Pregunta pregunta) {
        //INTRODUCCION LOGICA PARA OPCION 5
        Poder poderAUsar;

        // 1 al 4 respuestas, 5 para poderes (aún implementar)
        int enteroRespuesta;

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
                return new Tupla(null, null);
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
                return new Tupla(null, null);
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
                return new Tupla(null, null);
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
                    
                    boolean preguntaNull = false;
                    while (!preguntaNull){
                       
                        pregunta = poderAUsar.gastarPoder(enteroRespuesta);
                        if (pregunta != null){
                            preguntaNull = true;
                        } else {
                            System.out.println("tengo que ver esto");
                        }
                    }
                   

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
                    return new Tupla(new Tupla(pregunta, listaRespuestasTuplas), null);    
                } else {
                    System.out.println(" ");
                    System.out.println("PUNTAJE INSUFICIENTE");
                    System.out.println(" ");
                }

                break;
            case 5:
                poderAUsar = new OtraOportunidad(listaRespuestasTuplas, pregunta, this);
                Boolean comprobacionRespuesta = null;
                if (jugador.getPuntaje() >= poderAUsar.getPrecio()) {
                    jugador.restarPuntaje(poderAUsar.getPrecio());
                    comprobacionRespuesta = poderAUsar.gastarPoder();
                } else {
                    System.out.println(" ");
                    System.out.println("PUNTAJE INSUFICIENTE");
                    System.out.println(" ");
                }
                return new Tupla(null, comprobacionRespuesta);

        }
        return new Tupla(null, null);
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
        System.out.println("5: Usar Poder");
        return listaRespuestasTuplas;
    }

    public boolean comprobarRespuesta(int respuestaUsuario, ArrayList<Tupla<Integer, String>> listaRespuestasTuplas, Pregunta pregunta, LogrosPorPuntos logrosDeBusqueda) {
        boolean respuestaIncorrecta = false;
        int numero;
        for (Tupla tuplas : listaRespuestasTuplas) {
            numero = ((Integer) tuplas.getPrimero()).intValue();
            if (numero == respuestaUsuario) {
                if (tuplas.getSegundo() == pregunta.getRespuestaCorrecta()) {
                    System.out.println("Respuesta correcta");
                    this.jugadorActivo.incrementarRacha();
                    int newPuntaje = this.jugadorActivo.getPuntajePartida() + this.jugadorActivo.getRacha();
                    this.jugadorActivo.setPuntajePartida(newPuntaje);
                    
                    //GENERA ERROR: REVISAR
                    this.preguntasRealizadas.get(pregunta.getIndicadorCategoria() - 1).add(pregunta.getIdPregunta());
                    respuestaIncorrecta = false;
                    //Verifica si el jugador desbloqueó algún logro después de cada pregunta
                    Logros logro = new LogrosPorRacha();
                   boolean comprobar = logro.elegirNombre(jugadorActivo, jugadorActivo.getRacha(), partidaVersus);
                    if (comprobar) {

                        logro.comprobar(jugadorActivo, logro);
                    }
                    

                    //Nos aseguramos de que se creen todos los logros por puntaje, en caso de que la partida
                    //termine con más puntos que la meta inicial (50 puntos)
                    Logros logro1 = new LogrosPorPuntos();
                    boolean comprobar1 = logro1.elegirNombre(jugadorActivo, jugadorActivo.getPuntajePartida(), partidaVersus);
                    if (comprobar1) {
                        logro1.comprobar(jugadorActivo, logro1);

                    }
                } else {
                    //GENERA ERROR REVISAR
                    this.preguntasRealizadas.get(pregunta.getIndicadorCategoria() - 1).add(pregunta.getIdPregunta());
                    System.out.println("Respuesta fallida");
                    //contadorPuntaje = 0;

                    respuestaIncorrecta = true;

                    
                }

                //logrosDeBusqueda.mostrarLogrosPorPuntos(jugadorActivo);

            }
        }
        return respuestaIncorrecta;
    }
}