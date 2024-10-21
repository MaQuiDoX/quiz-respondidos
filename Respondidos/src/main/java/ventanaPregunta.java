import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ventanaPregunta extends JFrame {
    public JButton pregunta2Button;
    public JButton pregunta1Button;
    public JButton pregunta3Button;
    public JButton pregunta4Button;
    public JLabel Pregunta;
    private JPanel panelPregunta;
    private JButton poderesButton;

    public int numeroGlobalPregunta;

    public ventanaPregunta() {
        setContentPane(panelPregunta);
        setTitle("Menu Pregunta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);


        pregunta1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numeroGlobalPregunta=1;
            }
        });
        pregunta2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numeroGlobalPregunta = 2;
            }
        });
        pregunta3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numeroGlobalPregunta = 3;
            }
        });
        pregunta4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numeroGlobalPregunta = 4;
            }
        });
        poderesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numeroGlobalPregunta = 5;
            }
        });
    }
}
