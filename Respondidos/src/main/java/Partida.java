import Game.Pregunta;
import utilities.Tupla;
import utilities.Libreria;
import powers.OtraOportunidad;
import powers.TNT;
import powers.Dinamita;
import powers.Poder;
import powers.Bombita;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import powers.CambioPregunta;

import static java.lang.Thread.sleep;

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

        ventanaPregunta vPregunta = new ventanaPregunta();
        VentanaMenu vMenu = new VentanaMenu();
        MenuPoderes vPoderes = new MenuPoderes();

        vPregunta.setVisible(false);
        vMenu.setVisible(true);

        Scanner sc = new Scanner(System.in);
        int puntajeRonda = 0;
        int contadorPuntaje = 0;
        boolean salir1 = false;
        boolean salir2 = false;
        ArrayList<String> listaRespuestas = new ArrayList<>();
        ArrayList<Tupla<Integer,String>> listaRespuestasTuplas = new ArrayList<>();

        while (!salir1){
            System.out.println(" === PARTIDA INDIVIDUAL ===");
            System.out.println("Puntaje: " + puntajeRonda);
            System.out.println("1. Responder");
            System.out.println("2. Salir");
            //int opcion = Libreria.catchInt(1,2);

            Bombita bombitaMenu = new Bombita();
            Dinamita dinamitaMenu = new Dinamita();
            TNT tntMenu = new TNT();
            CambioPregunta cambioPreguntaMenu = new CambioPregunta();
            OtraOportunidad otraOportunidadMenu = new OtraOportunidad();

            salir2 = false;
            System.out.println(vMenu.getNumeroGlobalMenuPartida());

            do {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } while (vMenu.numeroGlobalMenuPartida != 1);

            switch (vMenu.getNumeroGlobalMenuPartida()){
                case 1:
                    vMenu.setVisible(false);
                    vPregunta.setVisible(true);

                    while (!salir2){
                        Poder poderAUsar = null;
                        // Obtengo pregunta
                        Pregunta pregunta = Pregunta.obtenerPregunta(-1);

                        // Chequeo que la pregunta no se repita, ingreso a la lista del objeto Partida y busco la tupla dentro de la lista, si se encuentra repetida repito la creacion de pregunta
                        if (!(preguntasRealizadas.isEmpty())){
                            Tupla tuplaExistencia = new Tupla(pregunta.getIndicadorCategoria(),pregunta.getIdPregunta());

                            while (!checkTuplas(tuplaExistencia)){
                                pregunta = Pregunta.obtenerPregunta(-1);
                                tuplaExistencia = new Tupla(pregunta.getIndicadorCategoria(),pregunta.getIdPregunta());
                            }
                        }

                        // Añado respuestas a una lista para mezclarlas y asignarles un numero
                        listaRespuestas.add(pregunta.getRespuestaCorrecta());
                        listaRespuestas.addAll(pregunta.getRespuestasIncorrectas());

                        int contador = 1;

                        Collections.shuffle(listaRespuestas);

                        for (String respuesta : listaRespuestas){
                            Tupla<Integer, String> tupla = new Tupla<>(contador, respuesta);
                            listaRespuestasTuplas.add(tupla);
                            contador++;
                        }


                        //INTRODUCCION LOGICA PARA OPCION 5
                        boolean usoPoder = false;
                        do{
                            Libreria.imprimirPregunta(pregunta.getPregunta(), listaRespuestasTuplas);
                            System.out.println("5: Comprar poder");

                            vPregunta.Pregunta.setText(pregunta.getPregunta());
                            vPregunta.pregunta1Button.setText(listaRespuestasTuplas.get(0).getSegundo().toString());
                            vPregunta.pregunta2Button.setText(listaRespuestasTuplas.get(1).getSegundo().toString());
                            vPregunta.pregunta3Button.setText(listaRespuestasTuplas.get(2).getSegundo().toString());
                            vPregunta.pregunta4Button.setText(listaRespuestasTuplas.get(3).getSegundo().toString());

                            do {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                            } while (vPregunta.numeroGlobalPregunta == 0);

                            // 1 al 4 respuestas, 5 para poderes (aún implementar)
                            //int enteroRespuesta = Libreria.catchInt(1,5);
                            int numero;

                            if (vPregunta.numeroGlobalPregunta == 5){

                                vPregunta.setVisible(false);
                                vPoderes.setVisible(true);
                                vPregunta.numeroGlobalPregunta = 0;

                                do {
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                    }
                                } while (vMenu.numeroGlobalMenuPartida == 0);

                                vPoderes.button1.setText("Bombita ("+bombitaMenu.getPrecio()+"p)");
                                vPoderes.button2.setText("Dinamita ("+dinamitaMenu.getPrecio()+"p)");
                                vPoderes.button3.setText("TNT ("+tntMenu.getPrecio()+"p)");
                                vPoderes.button4.setText("cambioPregunta ("+cambioPreguntaMenu.getPrecio()+"p)");
                                vPoderes.button5.setText("Otra Oportunidad ("+otraOportunidadMenu.getPrecio()+"p)");

                                //enteroRespuesta = Libreria.catchInt(1, 6);

                                switch(vPoderes.numeroGlobalMenuPoderes){
                                    case 1:
                                        poderAUsar = new Bombita(pregunta.getRespuestaCorrecta());
                                        poderAUsar.gastarPoder(listaRespuestasTuplas);
                                        break;
                                    case 2:
                                        poderAUsar = new Dinamita(pregunta.getRespuestaCorrecta());
                                        poderAUsar.gastarPoder(listaRespuestasTuplas);
                                        break;
                                    case 3:
                                        poderAUsar = new TNT(pregunta.getRespuestaCorrecta());
                                        poderAUsar.gastarPoder(listaRespuestasTuplas);
                                        break;
                                    case 4:
                                        /*
                                        System.out.println("¿De que categoria desea la pregunta?");
                                        System.out.println("1. Arte");
                                        System.out.println("2. Entretenimiento");
                                        System.out.println("3. Deporte");
                                        System.out.println("4. Ciencia");
                                        System.out.println("5. Historia");
                                        System.out.println("6. UNCuyo");
                                        enteroRespuesta = Libreria.catchInt(1, 6);
                                        poderAUsar = new CambioPregunta(enteroRespuesta);
                                        pregunta = poderAUsar.gastarPoder(enteroRespuesta, pregunta);
                                        
                                        listaRespuestas = new ArrayList<>();
                                        listaRespuestasTuplas = new ArrayList<>();
                                        // Añado respuestas a una lista para mezclarlas y asignarles un numero
                                        listaRespuestas.add(pregunta.getRespuestaCorrecta());
                                        listaRespuestas.addAll(pregunta.getRespuestasIncorrectas());

                                        contador = 1;

                                        Collections.shuffle(listaRespuestas);

                                        for (String respuesta : listaRespuestas){
                                            Tupla<Integer, String> tupla = new Tupla<>(contador, respuesta);
                                            listaRespuestasTuplas.add(tupla);
                                            contador++;
                                        }
                                        break;

                                         */
                                    case 5:
                                        poderAUsar = new OtraOportunidad();
                                        break;

                                }
                                jugador.restarPuntaje(poderAUsar.getPrecio());
                                usoPoder = true;


                            } else{
                                // Transito las tuplas y si la tupla con el numero ingresado coinside con la respuesta correcta, añado los puntajes y el juego sigue
                                // En caso de fallar, la partida es eliminada y lo unico que haría (todavia queda implementarlo) seria sumarle los puntos totales al jugador.
                                for (Tupla tuplas : listaRespuestasTuplas) {
                                    numero = ((Integer) tuplas.getPrimero()).intValue();
                                    if (numero == vPregunta.numeroGlobalPregunta) {
                                        if (tuplas.getSegundo() == pregunta.getRespuestaCorrecta()) {

                                            System.out.println("Respuesta correcta");
                                            vPregunta.numeroGlobalPregunta = 0;
                                            contadorPuntaje++;
                                            puntajeRonda = puntajeRonda + contadorPuntaje;
                                            preguntasRealizadas.add(new Tupla<>(pregunta.getIndicadorCategoria(),pregunta.getIdPregunta()));

                                            vPregunta.setVisible(false);
                                            vMenu.setVisible(true);
                                            vMenu.Puntaje.setText("Puntaje: "+puntajeRonda);
                                            vMenu.numeroGlobalMenuPartida = 0;
                                            vPregunta.numeroGlobalPregunta = 0;

                                            //Verifica si el jugador desbloqueó algún logro después de cada pregunta
                                            Logros logro = new LogrosPorRacha();
                                            logro.elegirNombre(jugadorActivo, preguntasRealizadas.size());
                                            if (logro.isNecesitaComprobar()) {

                                                logro.comprobar(jugadorActivo, logro);
                                            }

                                        } else {
                                            /*
                                            if (poderAUsar instanceof OtraOportunidad){
                                                System.out.println("¡Intentalo otra vez!");
                                                Tupla<Integer, String>[] otraChance = listaRespuestasTuplas.toArray(new Tupla[listaRespuestasTuplas.size()]);
                                                
                                                enteroRespuesta = Libreria.catchInt(1,4);
                                                enteroRespuesta -= 1;
                                                
                                                if (otraChance[enteroRespuesta].getSegundo() == pregunta.getRespuestaCorrecta()){
                                                    System.out.println("Respuesta correcta");
                                                    contadorPuntaje++;
                                                    puntajeRonda = puntajeRonda + contadorPuntaje;
                                                    preguntasRealizadas.add(new Tupla<>(pregunta.getIndicadorCategoria(),pregunta.getIdPregunta()));                                                    
                                                } else {
                                                    preguntasRealizadas.add(new Tupla<>(pregunta.getIndicadorCategoria(),pregunta.getIdPregunta()));
                                                    System.out.println("Respuesta fallida");
                                                    contadorPuntaje = 0;
                                                    salir2 = true;
                                                    salir1 = true;
                                                }
                                            } else {
                                            
                                                preguntasRealizadas.add(new Tupla<>(pregunta.getIndicadorCategoria(),pregunta.getIdPregunta()));
                                                System.out.println("Respuesta fallida");
                                                contadorPuntaje = 0;
                                                salir2 = true;
                                                salir1 = true;
                                            }


                                             */


                                        }

                                        //Verifica si el jugador desbloqueó algún logro después de sumar nuevos puntos
                                        Logros logro1 = new LogrosPorPuntos();
                                        logro1.elegirNombre(jugadorActivo, puntajeRonda);
                                        logro1.comprobar(jugadorActivo, logro1);
                                    }

                                }
                                listaRespuestas  = new ArrayList<>();
                                listaRespuestasTuplas = new ArrayList<>();
                                salir2 = true;
                                break;

                            }
                        
                        }while(usoPoder);

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

    private boolean checkTuplas(Tupla tuplaExistencia){
        for (Tupla tuplaEx : preguntasRealizadas){
            if ((tuplaEx.getPrimero() == tuplaExistencia.getPrimero())&&(tuplaEx.getSegundo() == tuplaExistencia.getSegundo())){
                return false;
            }
        }
        return true;
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