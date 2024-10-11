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

                        // Obtengo pregunta
                        Pregunta pregunta = Pregunta.obtenerPregunta();

                        // Chequeo que la pregunta no se repita, ingreso a la lista del objeto Partida y busco la tupla dentro de la lista, si se encuentra repetida repito la creacion de pregunta
                        if (!(preguntasRealizadas.isEmpty())){
                            Tupla tuplaExistencia = new Tupla(pregunta.getIndicadorCategoria(),pregunta.getIdPregunta());

                            while (!checkTuplas(tuplaExistencia)){
                                pregunta = Pregunta.obtenerPregunta();
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

                            // 1 al 4 respuestas, 5 para poderes (aún implementar)
                            int enteroRespuesta = Libreria.catchInt(1,5);
                            int numero;
                            Poder poderAUsar;
                            if (enteroRespuesta == 5){
                                System.out.println(" === MENU DE PODERES ===");
                                System.out.println("¿Que poder desea usar?:");
                                System.out.println("1. Bombita (10p)");
                                System.out.println("2. Dinamita (20p)");
                                System.out.println("3. TNT (30p)");
                                System.out.println("4. Responder (35p)");
                                System.out.println("5. Reintento (15p)");
                                System.out.println("6. MorePoints (50p)");
                                
                                enteroRespuesta = Libreria.catchInt(1, 4);
                                
                                switch(enteroRespuesta){
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
                                    
                                    
                                }
                                usoPoder = true;
                            } else{
                                // Transito las tuplas y si la tupla con el numero ingresado coinside con la respuesta correcta, añado los puntajes y el juego sigue
                                // En caso de fallar, la partida es eliminada y lo unico que haría (todavia queda implementarlo) seria sumarle los puntos totales al jugador.
                                for (Tupla tuplas : listaRespuestasTuplas) {
                                    numero = ((Integer) tuplas.getPrimero()).intValue();
                                    if (numero == enteroRespuesta) {
                                        if (tuplas.getSegundo() == pregunta.getRespuestaCorrecta()) {
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