import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Definimos una interfaz para manejar la selección de opciones

public class DynamicMenuApp extends JFrame {

    // Atributo para almacenar la última opción seleccionada
    private int selectedOption = -1;

    public DynamicMenuApp(String consoleOutput, OptionSelectListener listener) {
        setTitle("Menú Dinámico");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        createInterfaceFromConsoleOutput(consoleOutput, panel, listener);

        add(panel);
        setVisible(true);
    }

    private void createInterfaceFromConsoleOutput(String consoleOutput, JPanel panel, OptionSelectListener listener) {
        String[] lines = consoleOutput.split("\n");

        for (String line : lines) {
            if (line.startsWith("!")) {
                // Encabezado
                JLabel headerLabel = new JLabel(line.substring(1).trim(), JLabel.CENTER);
                headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
                panel.add(headerLabel);

            } else if (line.startsWith("$")) {
                // Título Secundario
                JLabel subTitleLabel = new JLabel(line.substring(1).trim(), JLabel.CENTER);
                subTitleLabel.setFont(new Font("Arial", Font.BOLD, 14));
                panel.add(subTitleLabel);

            } else if (line.startsWith("#")) {
                // Casillero de Texto
                JLabel inputLabel = new JLabel(line.substring(1).trim(), JLabel.LEFT);
                inputLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                JTextField textField = new JTextField(15);
                panel.add(inputLabel);
                panel.add(textField);
            } else if (line.matches("^\\d+\\.\\s*.*")) {
                // Opción con número
                String[] parts = line.split("\\.", 2);
                int optionNumber = Integer.parseInt(parts[0].trim());
                String optionText = parts[1].trim();

                JButton optionButton = new JButton(optionText);

                // Asignamos un ActionListener al botón
                optionButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedOption = optionNumber; // Guardar el número en el atributo
                        listener.onOptionSelected(selectedOption); // Llamar al método de callback
                    }
                });

                panel.add(optionButton);
            }
        }
    }

    // Método para obtener la última opción seleccionada
    public int getSelectedOption() {
        return selectedOption;
    }

    public static void main(String[] args) {
        String consoleOutput = "!BIENVENIDOS\n1. Opción 1\n2. Opción 2\n3. Opción 3";

        // Crear la ventana y pasar un listener para manejar la selección
        SwingUtilities.invokeLater(() -> new DynamicMenuApp(consoleOutput, option -> {
            System.out.println("Opción seleccionada: " + option);
            JOptionPane.showMessageDialog(null, "Seleccionaste la opción " + option);
        }));
    }
}
