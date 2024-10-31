import DAOs.UsuariosDAO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Juego {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);


        Jugador samu = new Jugador("Samu", 500);
//        ranking.agregarJugador(samu);
        Jugador jug2 = new Jugador("JUGADOR 2", 50);
//        ranking.agregarJugador(jug2);
        Jugador jug3 = new Jugador("ILLOJUAN", 100);
//        ranking.agregarJugador(jug3);

        // ----- TESTEOS USUARIOS -----
        // UsuariosDAO usuDAO = new UsuariosDAO();
        // usuDAO.borrarBaseDatosUsuarios();
        Usuarios usu = new Usuarios();
        usu.printUsuarios();
        // usu.addUsuarioDB(samu, "el777boy$exy");
        // usu.addUsuarioDB(jug2, "fuaLocaz0");
        // usu.addUsuarioDB(jug3, "gatitoTravieso69");
        Jugador jugadorRegistrado = usu.registerUsuario();

        // Jugador jugadorCargado = usu.loadUsuario(jugadorRegistrado.getNombre(), "draukeo");
        // System.out.println("Nombre jugador Cargado: "+jugadorCargado.getNombre()+"\n Puntuación jugador Cargado: "+jugadorCargado.getPuntaje());
        Jugador jugadorCargado2 = usu.loadUsuario("Samu", "el777boy$exy");
        usu.loadAllUsuarios();
        // System.out.println("Nombre jugador Cargado2: "+jugadorCargado2.getNombre()+"\n Puntuación jugador Cargado2: "+jugadorCargado2.getPuntaje());
        // ----- FIN TESTEOS USARIOS -----

        Ranking ranking = new Ranking();
        ranking.imprimirRanking();

        Partida partida = new Partida(new ArrayList<>(), null, samu);

        partida.iniciarPartida(samu);
    }

    private static int obtenerNumero(int valor) {
        return valor; // Puedes cambiar este valor a lo que necesites
    }


}
