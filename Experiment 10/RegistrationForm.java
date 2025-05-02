import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegistrationForm extends JFrame implements ActionListener {

    private JTextField nameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JLabel nameErrorLabel, emailErrorLabel, passwordErrorLabel, confirmPasswordErrorLabel, successLabel;
    private JButton registerButton;

    public RegistrationForm() {
        setTitle("User Registration Form");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 30, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(160, 30, 160, 25);
        add(nameField);

        nameErrorLabel = new JLabel();
        nameErrorLabel.setBounds(160, 55, 200, 15);
        nameErrorLabel.setForeground(Color.RED);
        add(nameErrorLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 80, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(160, 80, 160, 25);
        add(emailField);

        emailErrorLabel = new JLabel();
        emailErrorLabel.setBounds(160, 105, 200, 15);
        emailErrorLabel.setForeground(Color.RED);
        add(emailErrorLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 130, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 130, 160, 25);
        add(passwordField);

        passwordErrorLabel = new JLabel();
        passwordErrorLabel.setBounds(160, 155, 200, 15);
        passwordErrorLabel.setForeground(Color.RED);
        add(passwordErrorLabel);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(50, 180, 120, 25);
        add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(160, 180, 160, 25);
        add(confirmPasswordField);

        confirmPasswordErrorLabel = new JLabel();
        confirmPasswordErrorLabel.setBounds(160, 205, 200, 15);
        confirmPasswordErrorLabel.setForeground(Color.RED);
        add(confirmPasswordErrorLabel);

        registerButton = new JButton("Register");
        registerButton.setBounds(160, 240, 100, 30);
        registerButton.addActionListener(this);
        add(registerButton);

        successLabel = new JLabel();
        successLabel.setBounds(50, 290, 300, 25);
        successLabel.setForeground(new Color(0, 128, 0)); 
        add(successLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nameErrorLabel.setText("");
        emailErrorLabel.setText("");
        passwordErrorLabel.setText("");
        confirmPasswordErrorLabel.setText("");
        successLabel.setText("");

        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        boolean valid = true;

        if (name.isEmpty()) {
            nameErrorLabel.setText("Name is required.");
            valid = false;
        }

        if (email.isEmpty()) {
            emailErrorLabel.setText("Email is required.");
            valid = false;
        } else if (!email.contains("@") || !email.contains(".")) {
            emailErrorLabel.setText("Invalid email format.");
            valid = false;
        }

        if (password.isEmpty()) {
            passwordErrorLabel.setText("Password is required.");
            valid = false;
        }

        if (confirmPassword.isEmpty()) {
            confirmPasswordErrorLabel.setText("Please confirm password.");
            valid = false;
        } else if (!password.equals(confirmPassword)) {
            confirmPasswordErrorLabel.setText("Passwords do not match.");
            valid = false;
        }

        if (valid) {
            successLabel.setText("Registration Successful!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}




