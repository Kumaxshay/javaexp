import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginForm extends JFrame implements ActionListener {

    private Container container;
    private JLabel userLabel, passLabel, messageLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    
    public LoginForm() {
        setTitle("Login Form");
        setBounds(500, 300, 350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 30, 80, 25);
        container.add(userLabel);

        userTextField = new JTextField();
        userTextField.setBounds(140, 30, 150, 25);
        container.add(userTextField);

        passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 70, 80, 25);
        container.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(140, 70, 150, 25);
        container.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(140, 110, 80, 25);
        loginButton.addActionListener(this);
        container.add(loginButton);

        messageLabel = new JLabel();
        messageLabel.setBounds(50, 140, 250, 25);
        messageLabel.setForeground(Color.RED);
        container.add(messageLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userTextField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("admin") && password.equals("password")) {
            messageLabel.setForeground(Color.GREEN);
            messageLabel.setText("Login successful!");
        } else {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Invalid username or password.");
        }
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }
}