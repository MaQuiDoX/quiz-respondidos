import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Juego extends JFrame {
    public static void main(String[] args) {

        /*
        Ranking ranking = new Ranking();

        JFrame frame = new JFrame("Respondidos");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        Font fuenteRespondidos = new Font("Cooper Black", Font.BOLD, 40);
        JButton button1 = new JButton("Jugar");
        JButton button2 = new JButton("Salir");

        JLabel etiqueta = new JLabel("RESPONDIDOS");
        etiqueta.setFont(new Font("Cooper Black", Font.BOLD, 60));  // Fuente más grande
        etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);

        AtomicInteger valorDevuelto= new AtomicInteger();

        button1.addActionListener(e -> {
            valorDevuelto.set(obtenerNumero(1));

        });
        button2.addActionListener(e -> {
            valorDevuelto.set(obtenerNumero(2));

        });

        int valorNew = valorDevuelto.get();

        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);

        button1.setMaximumSize(new Dimension(300, 200));
        button2.setMaximumSize(new Dimension(300, 200));

        panel.add(Box.createVerticalStrut(50));  // Espacio superior
        panel.add(etiqueta);
        panel.add(Box.createVerticalStrut(80));  // Espacio entre texto y botones
        panel.add(button1);
        panel.add(Box.createVerticalStrut(30));  // Espacio entre botones
        panel.add(button2);
        panel.add(Box.createVerticalGlue());


        panel.setBackground(Color.lightGray);

        frame.add(panel);
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

*/
        Scanner sc = new Scanner(System.in);
        Ranking ranking = new Ranking();

        Jugador samu = new Jugador("Samu", 0);
        ranking.agregarJugador(samu);
        Jugador jug2 = new Jugador("JUGADOR 2", 50);
        ranking.agregarJugador(jug2);
        Jugador jug3 = new Jugador("ILLOJUAN", 100);
        ranking.agregarJugador(jug3);


        ranking.imprimirRanking();

        Partida partida = new Partida(new ArrayList<>(), null, samu);

        partida.iniciarPartida(samu);

    }

    private static int obtenerNumero(int valor) {
        return valor; // Puedes cambiar este valor a lo que necesites
    }

}
