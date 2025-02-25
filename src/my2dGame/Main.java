package my2dGame; 

import javax.swing.JFrame; 
import javax.swing.JOptionPane; // Import for user input dialog

public class Main { 

    public static void main(String[] args) { 
        TitleScreen titleScreen = new TitleScreen();
        titleScreen.setVisible(true);
    }

    private static String showTitleScreen() {
        String message = "Welcome To Sunset Adventures!\n\nEnter your name:";
        return JOptionPane.showInputDialog(null, message, "Title Screen", JOptionPane.PLAIN_MESSAGE);
    }

    private static void startGame(String playerName) {
        JFrame window = new JFrame(); // Creates a new JFrame instance, which is the main window of the application.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensures the application exits when the window is closed.
        window.setResizable(false); // Disables resizing of the window to maintain a fixed layout.
        window.setTitle("Sunset Adventure!"); // Sets the title of the window to "Sunset Adventure!".

        GamePanel gamePanel = new GamePanel(playerName); // Pass name to GamePanel
        window.add(gamePanel); // Adds the GamePanel to the JFrame, embedding it in the window.
        window.pack(); // Adjusts the window size to fit the preferred size of its components (GamePanel).

        window.setLocationRelativeTo(null); // Centers the window on the screen.
        window.setVisible(true); // Makes the window visible to the user.
        
        gamePanel.setupGame();
        gamePanel.StartGameThread();
    }
}
