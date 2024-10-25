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
        Scanner sc = new Scanner(System.in);
        int puntajeRonda = 0;
        int contadorPuntaje = 0;
        boolean salir1 = false;
        boolean salir2 = false;
        ArrayList<String> listaRespuestas = new ArrayList<>();
        ArrayList<Tupla<Integer,String>> listaRespuestasTuplas = new ArrayList<>();
        LogrosPorPuntos logroDeBusqueda = new LogrosPorPuntos();
        LogrosRachaCatgoria iniciar = new LogrosRachaCatgoria(0);
        iniciar.inicializarContadores();

        while (!salir1){
            System.out.println(" === PARTIDA INDIVIDUAL ===");
            System.out.println("Puntaje: " + puntajeRonda);
            System.out.println("1. Responder");
            System.out.println("2. Salir");
            int opcion = Libreria.catchInt(1,2);
            ////////////////////////////////////////////////////////////////
            Bombita bombitaMenu = new Bombita();
            Dinamita dinamitaMenu = new Dinamita();
            TNT tntMenu = new TNT();
            CambioPregunta cambioPreguntaMenu = new CambioPregunta();
            OtraOportunidad otraOportunidadMenu = new OtraOportunidad();
            ////////////////////////////////////////////////////////////////

            salir2 = false;
            switch (opcion){
                case 1:
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

                            // 1 al 4 respuestas, 5 para poderes (aún implementar)
                            int enteroRespuesta = Libreria.catchInt(1,5);
                            int numero;
                            
                            if (enteroRespuesta == 5){
                                System.out.println(" === MENU DE PODERES ===");
                                System.out.println("¿Que poder desea usar?:");
                                System.out.println("1. Bombita ("+bombitaMenu.getPrecio()+"p)");
                                System.out.println("2. Dinamita ("+dinamitaMenu.getPrecio()+"p)");
                                System.out.println("3. TNT ("+tntMenu.getPrecio()+"p)");
                                System.out.println("4. cambioPregunta ("+cambioPreguntaMenu.getPrecio()+"p)");
                                System.out.println("5. Otra Oportunidad ("+otraOportunidadMenu.getPrecio()+"p)");
                                
                                enteroRespuesta = Libreria.catchInt(1, 6);
                                
                                switch(enteroRespuesta){
                                    case 1:
                                        poderAUsar = new Bombita(pregunta.getRespuestaCorrecta());
                                        if (jugador.getPuntaje()>=poderAUsar.getPrecio()){
                                            jugador.restarPuntaje(poderAUsar.getPrecio());
                                            poderAUsar.gastarPoder(listaRespuestasTuplas);
                                        }else{
                                            System.out.println(" ");System.out.println("PUNTAJE INSUFICIENTE");
                                            System.out.println(" ");
                                        }
                                        break;
                                    case 2:
                                        poderAUsar = new Dinamita(pregunta.getRespuestaCorrecta());
                                        if (jugador.getPuntaje()>=poderAUsar.getPrecio()){
                                            jugador.restarPuntaje(poderAUsar.getPrecio());
                                            poderAUsar.gastarPoder(listaRespuestasTuplas);
                                        }else{
                                            System.out.println(" ");System.out.println("PUNTAJE INSUFICIENTE");
                                            System.out.println(" ");
                                        }
                                        break;
                                    case 3:
                                        poderAUsar = new TNT(pregunta.getRespuestaCorrecta());
                                        if (jugador.getPuntaje()>=poderAUsar.getPrecio()){
                                            jugador.restarPuntaje(poderAUsar.getPrecio());
                                            poderAUsar.gastarPoder(listaRespuestasTuplas);
                                        }else{
                                            System.out.println(" ");System.out.println("PUNTAJE INSUFICIENTE");
                                            System.out.println(" ");
                                        }
                                        break;
                                    case 4:
                                        poderAUsar = new CambioPregunta(enteroRespuesta);
                                        if (jugador.getPuntaje()>=poderAUsar.getPrecio()){

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

                                        }else{
                                            System.out.println(" ");System.out.println("PUNTAJE INSUFICIENTE");
                                            System.out.println(" ");
                                        }

                                        break;
                                    case 5:
                                        poderAUsar = new OtraOportunidad();
                                        if (jugador.getPuntaje()>=poderAUsar.getPrecio()){
                                            jugador.restarPuntaje(poderAUsar.getPrecio());
                                        }else{
                                            System.out.println(" ");System.out.println("PUNTAJE INSUFICIENTE");
                                            System.out.println(" ");
                                        }
                                        break;
                                    
                                }

                                usoPoder = true;
                            } else{
                                // Transito las tuplas y si la tupla con el numero ingresado coinside con la respuesta correcta, añado los puntajes y el juego sigue
                                // En caso de fallar, la partida es el
                                // iminada y lo unico que haría (todavia queda implementarlo) seria sumarle los puntos totales al jugador.
                                boolean flagOtroIntento = false;
                                do{
                                  for (Tupla tuplas : listaRespuestasTuplas){
                                      numero = ((Integer) tuplas.getPrimero()).intValue();
                                      if (numero == enteroRespuesta){
                                          if (tuplas.getSegundo() == pregunta.getRespuestaCorrecta()){
                                              System.out.println("Respuesta correcta");
                                              contadorPuntaje++;
                                              puntajeRonda = puntajeRonda + contadorPuntaje;
                                              preguntasRealizadas.add(new Tupla<>(pregunta.getIndicadorCategoria(),pregunta.getIdPregunta()));

                                              //Verifica si el jugador desbloqueó algún logro después de cada pregunta
                                              Logros logro = new LogrosPorRacha();
                                              boolean comprobar = logro.elegirNombre(jugadorActivo, preguntasRealizadas.size());
                                              if (comprobar) {

                                                logro.comprobar(jugadorActivo, logro);
                                              }



                                             //Nos aseguramos de que se creen todos los logros por puntaje, en caso de que la partida
                                             //termine con más puntos que la meta inicial (50 puntos)
                                             Logros logro1 = new LogrosPorPuntos();
                                             boolean comprobar1 =logro1.elegirNombre(jugadorActivo, puntajeRonda);
                                             if (comprobar1) {
                                                logro1.comprobar(jugadorActivo, logro1);

                                             }
                                             flagOtroIntento = false;
                                            }else {
                                                preguntasRealizadas.add(new Tupla<>(pregunta.getIndicadorCategoria(),pregunta.getIdPregunta()));
                                                System.out.println("Respuesta fallida");
                                          
                                                if (poderAUsar instanceof OtraOportunidad){
                                                    System.out.println("¡Tienes otro intento!, Responder otra vez:");
                                                    flagOtroIntento = true;
                                                    poderAUsar = null;
                                                    enteroRespuesta = Libreria.catchInt(1, 4);
                                                } else{
                                                    contadorPuntaje = 0;
                                                    salir2 = true;
                                                    salir1 = true;
                                                    flagOtroIntento = false;
                                                }
                                            }
                                        }
                                    }
                                }while (flagOtroIntento);
                                       

                                //nos interesa el puntaje una vez que termina la ronda, así que ahora mostramos los logros obtenidos
                                //por puntaje
                                logroDeBusqueda.mostrarLogrosPorPuntos(jugadorActivo);
                                listaRespuestas  = new ArrayList<>();
                                listaRespuestasTuplas = new ArrayList<>();
                                salir2 = true;

                                for (int i= 0; i<=preguntasRealizadas.size()-2;i++){
                                    LogrosRachaCatgoria logro2= new LogrosRachaCatgoria(preguntasRealizadas.get(i).getPrimero());
                                    boolean comprobar2 = logro2.elegirNombre(jugadorActivo, preguntasRealizadas.get(i).getPrimero());
                                    if (comprobar2) {
                                        logro2.comprobar(jugadorActivo, logro2);
                                    }
                                }

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