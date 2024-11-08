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

/**
 * Esta clase representa una partida en un juego de preguntas y respuestas.
 * Maneja el flujo del juego, incluyendo la gestión de preguntas, categorías,
 * y el estado de los jugadores.
 *
 * @author Ferrari Paulina
 * @author Giraudo Ignacio
 * @author Martins Ezequiel
 * @author Quesada Manuel
 * @author Villegas Joaquin
 *
 */
public abstract class Partida {
    protected ArrayList<ArrayList<Integer>> preguntasRealizadas;
    protected Jugador jugadorActivo;
    protected boolean partidaVersus= false;

    protected String categorias = "Arte,Entretenimiento,Deporte,Ciencia,Historia,Uncuyo";
    protected String[] elementos = categorias.split(",");
    protected ArrayList<String> listaCategorias = new ArrayList<>(Arrays.asList(elementos));

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

    /**
     * Inicia una partida individual asignando el jugador que la comenzará.
     *
     * @param jugador El objeto de la clase Jugador que iniciará la partida.
     * @throws Exception Si ocurre un error durante la inicialización de la partida.
     */
    public void iniciarPartida(Jugador jugador) throws Exception {
    }

    /**
     * Inicia una partida en modo versus, asignando los dos jugadores que participarán.
     *
     * @param j1 El primer objeto de la clase Jugador que participará en la partida.
     * @param j2 El segundo objeto de la clase Jugador que participará en la partida.
     * @throws Exception Si ocurre un error durante la inicialización de la partida.
     */
    public void iniciarPartida(Jugador j1, Jugador j2) throws Exception {
        
    }

    /**
     * gestiona lo que debe ocurrir durante el turno del jugador
     * @param jugador
     * @param ignorar
     * @throws Exception
     */
    public void turnoJugador(Jugador jugador, ArrayList<Integer> ignorar) throws Exception {
        // Será el turno del jugador mientras salir2 = false. salir2 será verdadero una vez el jugador falle
        boolean salir2 = false;
        boolean usoTienda = false;
        
        Pregunta pregunta = null;

        // Acá metemos las preguntas junto con un número que las identifique para que el usuario ingrese la respuesta
        ArrayList<Tupla<Integer, String>> listaRespuestasTuplas = null;
        LogrosPorPuntos logroDeBusqueda = new LogrosPorPuntos();
        LogrosPorRacha logroDeBusqueda1 = new LogrosPorRacha();
        while (!salir2) {
                System.out.println("=== Puntaje total ganado: " + jugador.getPuntajePartida() + " ===");
                // Si usoTienda = true, significa que el usuario uso la tienda, significa que solo le debemos imprimir la pregunta.
                // Si usoTienda = false, no la uso
                if (!usoTienda){
                    // Caso donde ya no hay mas preguntas.
                    if (categoriasAgotadas.size() == 6){
                        System.out.println("¡No hay mas preguntas... Ganaste el premio mayor! (10.000 PUNTOS)");
                        jugador.setPuntajePartida(10000);
                        return;
                    }

                    // Obtenemos la pregunta (el método en pregunta accede a la base de datos)
                    pregunta = Pregunta.obtenerPregunta(-1, categoriasAgotadas);
                    //if necesario en caso de errores.
                    if (pregunta == null){
                        System.out.println("Buscando una pregunta...");
                        continue;
                    }
                    // Mientras la pregunta obtenida en la base esté en el array de preguntas que ya se hicieron, se busca otra
                    while (preguntasRealizadas.get(pregunta.getIndicadorCategoria() - 1).contains(pregunta.getIdPregunta())) {
                        pregunta = Pregunta.obtenerPregunta(-1, categoriasAgotadas);
                    }
                    // Llenamos con las respuestas de la pregunta que se obtuvo y el número con el que el jugador puede seleccionar
                    listaRespuestasTuplas = generarRespuestasyPregunta(pregunta);
                } else {
                    // Imprimimos la pregunta, las repuestas y los números pero sin la opcion de ingresar a la tienda (en este caso usoTienda = true)
                    Libreria.imprimirPregunta(pregunta.getPregunta(), listaRespuestasTuplas);
                }

            // Verificamos cual fue la opción elegida por el jugador
            int respuesta = Libreria.catchInt(1, 5);
                // Si su respuesta fue 5, quiere entrar en la tienda
                if (respuesta == 5){

                    jugador.incrementarContadorUsoPoderes();
                    //Tupla de:
                    //        1. Tupla que tiene la nueva Pregunta, y Un Arraylist de respuestas en Tupla.
                    //        2. Booleano
                   Tupla<Tupla<Pregunta,ArrayList<Tupla<Integer, String>>>, Boolean> checkUsoPoder = tiendaPoderes(jugador, listaRespuestasTuplas, pregunta, ignorar);
                   
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
                    salir2 = comprobarRespuesta(respuesta, listaRespuestasTuplas, pregunta, logroDeBusqueda, ignorar);

                    usoTienda = false;
                }
        }

        // Creamos los logros obtenidos durante la partida, una vez terminada se los mostramos
        logroDeBusqueda.mostrarLogrosPorPuntos(jugadorActivo);
        logroDeBusqueda1.mostrarLogrosPorRacha(jugadorActivo);

        // Instanciamos para poder acceder al método recorrer
        LogrosRachaCatgoria logroEntrada= new LogrosRachaCatgoria();
        logroEntrada.recorrer(preguntasRealizadas, jugadorActivo, partidaVersus, ignorar);
    }

    /**
     * Permite al jugador utilizar diferentes poderes durante la partida.
     * Dependiendo del poder elegido, afecta la lista de respuestas disponibles o la pregunta actual.
     *
     * @param jugador El objeto de la clase Jugador que está utilizando el poder.
     * @param listaRespuestasTuplas La lista de respuestas disponibles para la pregunta actual.
     * @param pregunta El objeto de la clase Pregunta que contiene la pregunta y sus respuestas.
     * @param ignorar Una lista de enteros que indica las categorías de preguntas a ignorar.
     * @return Una tupla que contiene una tupla de pregunta y lista de respuestas, o un valor booleano indicando el resultado de la acción del poder.
     * @throws Exception Si ocurre un error durante la ejecución del método.
     */
    public Tupla<Tupla<Pregunta,ArrayList<Tupla<Integer, String>>>, Boolean> tiendaPoderes(Jugador jugador, ArrayList<Tupla<Integer, String>> listaRespuestasTuplas, Pregunta pregunta, ArrayList<Integer> ignorar) throws Exception {
        Poder poderAUsar;
        int enteroRespuesta;

        System.out.println(" === MENU DE PODERES ===");
        System.out.println("¿Que poder desea usar?:");
        System.out.println("1. Bombita (15p)");
        System.out.println("2. Dinamita (25p)");
        System.out.println("3. TNT (45p)");
        System.out.println("4. cambioPregunta (25p)");
        System.out.println("5. Otra Oportunidad (30p)");

        // Verificamos que poder quiere usar el jugador
        enteroRespuesta = Libreria.catchInt(1, 6);

        // Según el poder elegido, instanciamos y llamamos a la lógica de ese poder y le restamos al jugador los puntos que haya pagado
        switch (enteroRespuesta) {
            case 1:
                poderAUsar = new Bombita(pregunta.getRespuestaCorrecta());
                if (jugador.getPuntaje() >= poderAUsar.getPrecio()) {
                    jugador.restarPuntaje(poderAUsar.getPrecio());
                    poderAUsar.gastarPoder(listaRespuestasTuplas);
                } else {
                    // Siempre y cuando tenga el puntaje necesario
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
                poderAUsar = new OtraOportunidad(listaRespuestasTuplas, pregunta, this, ignorar);
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

    /**
     * Genera una lista de respuestas en forma de tuplas numeradas a partir de la pregunta dada.
     * La lista incluye tanto la respuesta correcta como las respuestas incorrectas, mezcladas aleatoriamente.
     * Imprime la categoría de la pregunta y las posibles respuestas. También da la opción de usar un poder
     * especial durante el juego.
     *
     * @param pregunta Un objeto de la clase Pregunta que contiene la pregunta y sus posibles respuestas.
     * @return Una lista de tuplas, donde cada tupla contiene un número y una respuesta asociada.
     */
    public ArrayList<Tupla<Integer, String>> generarRespuestasyPregunta(Pregunta pregunta) {

        ///guardamos las respuestas de la pregunta
        ArrayList<String> listaRespuestas = new ArrayList<>();
        ArrayList<Tupla<Integer, String>> listaRespuestasTuplas = new ArrayList<>();
        // Añado respuestas a una lista para mezclarlas y asignarles un numero
        listaRespuestas.add(pregunta.getRespuestaCorrecta());
        listaRespuestas.addAll(pregunta.getRespuestasIncorrectas());

        int contador = 1;

        ///mezclamos las respuestas
        Collections.shuffle(listaRespuestas);

        ///vamos creando las tuplas (numero-respuesta) y guardandolas en el array que creamos anteriormente
        for (String respuesta : listaRespuestas) {
            Tupla<Integer, String> tupla = new Tupla<>(contador, respuesta);
            listaRespuestasTuplas.add(tupla);
            contador++;
        }

        ///si no se usó el poder (usoPoder=false) se da la opción de acceder a la tienda
        System.out.println("Categoria: "+ listaCategorias.get(pregunta.getIndicadorCategoria()-1));
        Libreria.imprimirPregunta(pregunta.getPregunta(), listaRespuestasTuplas);
        System.out.println("5: Usar Poder");
        return listaRespuestasTuplas;
    }

    /**
     * Verifica si la respuesta proporcionada por el usuario es correcta o incorrecta, actualiza la
     * puntuación y el estado del jugador, y maneja la lógica de los logros.
     *
     * @param respuestaUsuario El valor entero que representa la respuesta dada por el usuario.
     * @param listaRespuestasTuplas Lista de tuplas que contienen las respuestas posibles para la pregunta, cada tupla incluye un número y una respuesta.
     * @param pregunta El objeto Pregunta que contiene la información de la pregunta actual.
     * @param logrosDeBusqueda El objeto LogrosPorPuntos que gestiona los logros por puntos en el juego.
     * @param ignorar Lista de enteros que indica las categorías de preguntas a ignorar.
     * @return true si la respuesta es incorrecta; false si la respuesta es correcta.
     */
    public boolean comprobarRespuesta(int respuestaUsuario, ArrayList<Tupla<Integer, String>> listaRespuestasTuplas, Pregunta pregunta, LogrosPorPuntos logrosDeBusqueda, ArrayList<Integer> ignorar) {
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
                        // Pausa la ejecución del programa por 1 segundos (1000 milisegundos)
                        Thread.sleep(1000);
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
                    this.preguntasRealizadas.get(pregunta.getIndicadorCategoria() - 1).add(pregunta.getIdPregunta());
                    System.out.println("Respuesta fallida");
                    respuestaIncorrecta = true;
                    ignorar.add(pregunta.getIndicadorCategoria());
                    try {
                        // Pausa la ejecución del programa por 1.5 segundos (1500 milisegundos)
                        Thread.sleep(1500);
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