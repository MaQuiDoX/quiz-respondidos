import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


public class VentanaMenu extends JFrame {
    private JLabel Respondidos;
    private JButton Iniciar;
    private JPanel MainPanel;
    private JButton salirButton;

    public int numeroGlobalMenuPartida;

    public VentanaMenu(){
        setContentPane(MainPanel);
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        Iniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numeroGlobalMenuPartida = 1;
            }
        });

        salirButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.exit(0);
           }
        });

    }

    public int getNumeroGlobalMenuPartida() {
        return numeroGlobalMenuPartida;
    }

    public void setNumeroGlobalMenuPartida(int numeroGlobalMenuPartida) {
        this.numeroGlobalMenuPartida = numeroGlobalMenuPartida;
    }
}
