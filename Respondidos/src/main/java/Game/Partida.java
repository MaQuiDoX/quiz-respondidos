package Game;

import utilities.ClearScreen;
import utilities.Tupla;
import utilities.Libreria;
import powers.OtraOportunidad;
import powers.TNT;
import powers.Dinamita;
import powers.Poder;
import powers.Bombita;

import java.util.*;

import powers.CambioPregunta;

public abstract class Partida {
    public ArrayList<ArrayList<Integer>> preguntasRealizadas;
    public Jugador jugadorActivo;
    //maybe esta no es la mejor forma
    boolean partidaVersus= false;

    String categorias = "Arte,Entretenimiento,Deporte,Ciencia,Historia,Uncuyo";
    String[] elementos = categorias.split(",");
    ArrayList<String> listaCategorias = new ArrayList<>(Arrays.asList(elementos));

    //Atributos que nos serviran para el caso donde ya no quedan preguntas.
    protected static final HashMap<Integer, Integer> maximoPreguntas = new HashMap<>();
    protected Set<Integer> categoriasAgotadas = new HashSet<>();
    static {
        maximoPreguntas.put(1, 20);
        maximoPreguntas.put(2, 20);
        maximoPreguntas.put(3, 20);
        maximoPreguntas.put(4,20);
        maximoPreguntas.put(5,25);
        maximoPreguntas.put(6,15);
    }
    public void iniciarPartida(Jugador jugador) throws Exception {

    }
    
    public void iniciarPartida(Jugador j1, Jugador j2) throws Exception {
        
    }

    public void turnoJugador(Jugador jugador) throws Exception {
        boolean salir2 = false;
        boolean usoTienda = false;
        
        Pregunta pregunta = null;
        ArrayList<Tupla<Integer, String>> listaRespuestasTuplas = null;
        LogrosPorPuntos logroDeBusqueda = new LogrosPorPuntos();
        LogrosPorRacha logroDeBusqueda1 = new LogrosPorRacha();
        while (!salir2) {
                System.out.println("=== Puntaje total ganado: " + jugador.getPuntajePartida() + " ===");
                //Si usoTienda = true, significa que el usuario uso la tienda, significa que solo le debemos imprimir la pregunta nomas.
                //Si usoTienda = false, no la uso
                if (!usoTienda){
                    //Caso donde ya no hay mas preguntas.
                    if (categoriasAgotadas.size() == 6){
                        System.out.println("¡No hay mas preguntas... Ganaste el premio mayor! (10.000 PUNTOS)");
                        jugador.setPuntajePartida(10000);
                        return;
                    }
                    pregunta = Pregunta.obtenerPregunta(-1, categoriasAgotadas);
                    //if necesario en caso de errores.
                    if (pregunta == null){
                        System.out.println("Buscando una pregunta...");
                        continue;
                    }
                    while (preguntasRealizadas.get(pregunta.getIndicadorCategoria() - 1).contains(pregunta.getIdPregunta())) {
                        pregunta = Pregunta.obtenerPregunta(-1, categoriasAgotadas);
                    }
                    listaRespuestasTuplas = generarRespuestasyPregunta(pregunta);
                } else {
                    Libreria.imprimirPregunta(pregunta.getPregunta(), listaRespuestasTuplas);
                }

            /**
             *
             * BORRAR
             */
            //System.out.println("ID DE LA PREGUNTA: " + pregunta.getIdPregunta());

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
                    salir2 = comprobarRespuesta(respuesta, listaRespuestasTuplas, pregunta, logroDeBusqueda);

                    usoTienda = false;
                }

           


        }

        logroDeBusqueda.mostrarLogrosPorPuntos(jugadorActivo);
        LogrosRachaCatgoria logroEntrada= new LogrosRachaCatgoria();
        for (int i = 0; i<=5; i++){
            logroEntrada.recorrer(preguntasRealizadas.get(i), i, jugadorActivo, partidaVersus);

        }
        logroDeBusqueda1.mostrarLogrosPorRacha(jugadorActivo);

    }
    
    public Tupla<Tupla<Pregunta,ArrayList<Tupla<Integer, String>>>, Boolean> tiendaPoderes(Jugador jugador, ArrayList<Tupla<Integer, String>> listaRespuestasTuplas, Pregunta pregunta) throws Exception {
        //INTRODUCCION LOGICA PARA OPCION 5
        Poder poderAUsar;

        int enteroRespuesta;

        System.out.println(" === MENU DE PODERES ===");
        System.out.println("¿Que poder desea usar?:");
        System.out.println("1. Bombita (15p)");
        System.out.println("2. Dinamita (25p)");
        System.out.println("3. TNT (45p)");
        System.out.println("4. cambioPregunta (25p)");
        System.out.println("5. Otra Oportunidad (30p)");

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
                    
                    boolean preguntaNull = false;
                    while (!preguntaNull){
                        enteroRespuesta = Libreria.catchInt(1, 6);
                        if (this.categoriasAgotadas.contains(enteroRespuesta)) {
                            System.out.println("Esa categoria ya no tiene respuestas, por favor elige otra categoria.");
                            continue;
                        }
                        pregunta = poderAUsar.gastarPoder(enteroRespuesta, this.preguntasRealizadas, this.categoriasAgotadas);
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

        System.out.println("Categoria: "+ listaCategorias.get(pregunta.getIndicadorCategoria()-1));
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

                    try {
                        // Pausa la ejecución del programa por 2 segundos (2.000 milisegundos)
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("El hilo fue interrumpido.");
                    }
                    ClearScreen.cls();

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
                    respuestaIncorrecta = true;
                    try {
                        // Pausa la ejecución del programa por 2.5 segundos (2.500 milisegundos)
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("El hilo fue interrumpido.");
                    }
                    //Limpio la pantalla
                    ClearScreen.cls();

                }
            }
        }
        //Añadimos a categoriasAgotadas el id de la categoria que se agoto, comparandolo con el tamaño del arraylist correspondiente en preguntasRealizadas
        if (maximoPreguntas.get(pregunta.getIndicadorCategoria()) == preguntasRealizadas.get(pregunta.getIndicadorCategoria() - 1).size()){
            categoriasAgotadas.add(pregunta.getIndicadorCategoria());
        }
        return respuestaIncorrecta;
    }
}