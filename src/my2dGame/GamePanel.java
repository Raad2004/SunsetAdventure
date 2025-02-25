package my2dGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JOptionPane; // Import for user input dialog

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    public final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 24;
    public final int maxScreenRow = 20;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    int FPS = 60;
    public final int maxWorldCol=100;
    public final int maxWorldRow=100;
    public final int worldWidth= tileSize*maxWorldCol;
    public final int worldHeight= tileSize * maxWorldRow;
    
    
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se= new Sound();
    
   
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter (this);
    public UI ui = new UI(this);
    Thread gameThread;
    
    
    public String playerName; // Add field for player's name
    public Player player; // Declare player field
    public SuperObject obj[]= new SuperObject [10];
    
    public GamePanel(String playerName) { // Modify constructor
        this.playerName = playerName; // Store player's name
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
        // Initialize player after playerName is set
        this.player = new Player(this, keyH, playerName); // Initialize player here
    }

    public void setupGame() {
    	aSetter.setObject();
    	playMusic(0);
    }
    
    public void StartGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        

        while (gameThread != null) {
            update();
            repaint();
           
            

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        
        for(int i=0;i < obj.length;i++) {
        	if(obj[i]!=null) {
        		obj[i].draw(g2, this);
        	}
        }
        
        player.draw(g2);
        ui.draw(g2);
        
        g2.dispose();
    }
    public void playMusic(int i) {
    	music.setFile(i);
    	music.play();
    	music.loop();
    }
    public void stopMusic() {
    	music.stop();
    }
    
    public void playSE(int i) {
    	se.setFile(i);
    	se.play();
    }
}



