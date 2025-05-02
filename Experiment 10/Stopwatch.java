import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Stopwatch extends JFrame implements ActionListener {

    private JLabel timeLabel;
    private JButton startButton, stopButton, resetButton;

    private Timer timer;          
    private int elapsedTime = 0;

    public Stopwatch() {
        setTitle("Simple Stopwatch");
        setSize(300, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        timeLabel = new JLabel("Elapsed Time: 0 s", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        add(timeLabel, BorderLayout.CENTER);

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        resetButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, e -> {
            elapsedTime++;
            timeLabel.setText("Elapsed Time: " + elapsedTime + " s");
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            timer.start();
        } else if (e.getSource() == stopButton) {
            timer.stop();
        } else if (e.getSource() == resetButton) {
            timer.stop();
            elapsedTime = 0;
            timeLabel.setText("Elapsed Time: 0 s");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Stopwatch().setVisible(true);
        });
    }
}


