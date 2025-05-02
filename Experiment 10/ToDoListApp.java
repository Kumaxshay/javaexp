import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ToDoListApp extends JFrame implements ActionListener {

    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton, removeButton;

    public ToDoListApp() {
        setTitle("To-Do List App");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  

        taskField = new JTextField();
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Selected");

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setLayout(new BorderLayout(10, 10));
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(taskField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(taskList);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(removeButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(this);
        removeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Task cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == removeButton) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a task to remove.", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ToDoListApp().setVisible(true);
        });
    }
}

