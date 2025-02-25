package my2dGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JFrame {
    private JTextField nameField;

    public TitleScreen() {
        setTitle("Welcome to Sunset Adventures!");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Create a panel for the title screen
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 223, 186)); // Light background color
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Title label
        JLabel titleLabel = new JLabel("Welcome To Sunset Adventures!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(0, 102, 204)); // Title color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(30, 20, 20, 20);
        panel.add(titleLabel, gbc);

        // Name input field
        nameField = new JTextField(15);
        nameField.setFont(new Font("Arial", Font.PLAIN, 24));
        nameField.setForeground(Color.GRAY);
        nameField.setText("Enter your name...");
        nameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (nameField.getText().equals("Enter your name...")) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (nameField.getText().isEmpty()) {
                    nameField.setForeground(Color.GRAY);
                    nameField.setText("Enter your name...");
                }
            }
        });
        gbc.gridy = 1;
        panel.add(nameField, gbc);

        // Start button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setBackground(new Color(0, 153, 76)); // Button color
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = nameField.getText();
                if (playerName != null && !playerName.trim().isEmpty() && !playerName.equals("Enter your name...")) {
                    startGame(playerName);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter your name!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        gbc.gridy = 2;
        panel.add(startButton, gbc);

        add(panel, BorderLayout.CENTER);
        nameField.requestFocusInWindow();
    }

    private void startGame(String playerName) {
        this.dispose(); // Close the title screen
        JFrame gameWindow = new JFrame("Sunset Adventure!");
        GamePanel gamePanel = new GamePanel(playerName);
        gameWindow.add(gamePanel);
        gameWindow.pack();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
        gamePanel.setupGame();
        gamePanel.StartGameThread();
    }
}
