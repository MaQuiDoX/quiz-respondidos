package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaMenu extends JFrame {
    private JLabel PartidaIndividual;
    private JButton Iniciar;
    private JPanel MainPanel;
    private JButton salirButton;
    public JLabel Puntaje;


    public int numeroGlobalMenuPartida;

    public VentanaMenu() {
        setTitle("Juego - Menu Principal");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Texto en grande "RESPONDIDOS"
        JLabel lblTitulo = new JLabel("RESPONDIDOS", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel con botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(7, 1, 10, 10));

        JButton btnRegistrar = new JButton("Registrarse");
        JButton btnPartidaIndividual = new JButton("Iniciar Partida Individual");
        JButton btnPartidaVersus = new JButton("Iniciar Partida Versus");
        JButton btnEstadisticas = new JButton("Estadísticas");
        JButton btnRanking = new JButton("Ranking");
        JButton btnSeleccionarJugador = new JButton("Seleccionar Jugador");
        JButton btnSalir = new JButton("Salir");

        // Agregar botones al panel
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnPartidaIndividual);
        panelBotones.add(btnPartidaVersus);
        panelBotones.add(btnEstadisticas);
        panelBotones.add(btnRanking);
        panelBotones.add(btnSeleccionarJugador);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);
    }

    public int getNumeroGlobalMenuPartida() {
        return numeroGlobalMenuPartida;
    }

    public void setNumeroGlobalMenuPartida(int numeroGlobalMenuPartida) {
        this.numeroGlobalMenuPartida = numeroGlobalMenuPartida;
    }
}