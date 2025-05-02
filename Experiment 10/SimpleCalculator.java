import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleCalculator extends JFrame implements ActionListener {

    private JTextField displayField;
    private JPanel buttonPanel;

    private String operator;
    private double num1, num2, result;
    private boolean operatorClicked;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttonLabels = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", "C", "=", "/"
        };

        for (String text : buttonLabels) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            if (operatorClicked) {
                displayField.setText("");
                operatorClicked = false;
            }
            displayField.setText(displayField.getText() + command);
        } else if (command.matches("[+\\-*/]")) {
            try {
                num1 = Double.parseDouble(displayField.getText());
                operator = command;
                operatorClicked = true;
            } catch (NumberFormatException ex) {
                displayField.setText("Error");
            }
        } else if (command.equals("=")) {
            try {
                num2 = Double.parseDouble(displayField.getText());
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": 
                        if (num2 == 0) {
                            displayField.setText("Cannot divide by 0");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                displayField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                displayField.setText("Error");
            }
        } else if (command.equals("C")) {
            displayField.setText("");
            num1 = num2 = result = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimpleCalculator().setVisible(true);
        });
    }
}
