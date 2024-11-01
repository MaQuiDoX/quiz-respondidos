import Game.Jugador;
import Game.PartidaIndividual;
import Game.PartidaVersus;
import Game.Partida;
import DAOs.UsuariosDAO;
import utilities.Libreria;
import utilities.Tupla;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Juego {
    public static void main(String[] args) throws Exception {

        boolean salir = false;
        ArrayList<Tupla<Integer,Partida>> listaPartidas = new ArrayList<>();
        int counter = 1;

        Jugador jugadorActivo = Usuarios.registerUsuario();

        Ranking ranking = new Ranking();
//        Jugador samu = new Jugador("Samu", 500);
//        Jugador jug2 = new Jugador("JUGADOR 2", 50);
//        Jugador jug3 = new Jugador("ILLOJUAN", 100);
//        ranking.agregarJugador(samu);
//        ranking.agregarJugador(jug2);
//        ranking.agregarJugador(jug3);

        while (!salir){
            System.out.println("RESPONDIDOS: Jugador Activo: "+ jugadorActivo.getNombre());
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar nueva Partida Individual");
            System.out.println("3. Iniciar nueva Partida Versus");
            System.out.println("4. Estadísticas");
            System.out.println("5. Ranking");
            System.out.println("6. Continuar Partidas activas");
            System.out.println("7. Seleccionar Jugador");
            System.out.println("8. Salir");

            int opcion = Libreria.catchInt(1,8);
            switch (opcion){
                case 1:
                    Jugador newJugador = Usuarios.registerUsuario();
                    break;
                case 2:
                    //Reemplazar samu por JugadorActivo cuando sea posible (cuando esté listo el inciso 7). (Nacho)

                    PartidaIndividual partidaI = new PartidaIndividual(new ArrayList<>(), new ArrayList<>(), jugadorActivo);
                    partidaI.iniciarPartida(jugadorActivo);

                    listaPartidas.add(new Tupla<>(counter, partidaI));
                    jugadorActivo.idsPartidasActivas.add(counter);
                    counter++;

                    break;
                case 3:
                    //Reemplazar samu por JugadorActivo cuando sea posible (cuando esté listo el inciso 7). (Nacho)
                    PartidaVersus versus = new PartidaVersus(new ArrayList<>(), null, null);
                    versus.iniciarPartida(null, null);

                    listaPartidas.add(new Tupla<>(counter, versus));
                    jugadorActivo.idsPartidasActivas.add(counter);
                    counter++;
                    break;
                case 4:
                    //Acá iría JugadorActivo.mostrarEstadisticas.
                    break;
                case 5:
                    ranking.imprimirRanking();
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    salir = true;
            }
        }
    }
}