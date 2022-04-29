package edu.neu.main;

import edu.neu.entity.Explosion;
import edu.neu.entity.Tank;
import edu.neu.entity.Target;
import edu.neu.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable{

    public final int originalTileSize = 10;
    // Scaling up the sprite to 3 times
    public final int scale = 3;
    // Scaling up the size of sprite
    public final int tileSize = originalTileSize * scale;
    // Defines how many titles to be displayed on the screen horizontally
    public final int maxScreenCol = 32;
    // Defines how may title to be displayed on the screen vertically
    public final int maxScreenRow = 24;
    // Defines the width of the screen
    public final int screenWidth = tileSize * maxScreenCol;
    // Define the height of the screen
    public final int screenHeight = tileSize * maxScreenRow;
    // Define Frame per second of the game
    public final int fps = 60;

    // GamePanel has a KeyHandler
    public final KeyHandler keyHandler = new KeyHandler(this);
    // GamePanel has a tank in it
    public Tank tank = new Tank(this);
    // GamePanel has a target
    public Target target = new Target(this);
    // GamePanel has a TileManager to manage tiles
    public TileManager tileManager = new TileManager(this);
    // GamePanel has a collision checker to check collision
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public List<Explosion> explosionList = new ArrayList<Explosion>();


    // Game state
    public int gameState;
    public static final int PLAY_STATE = 1;
    public static final int PAUSE_STATE = 2;


    // Game thread
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        // register KeyListener to JPanel
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        init();
    }

    private void init() {
        gameState = PLAY_STATE;
    }

    public void startGameThread() {
        // Thread constructor take a runnable object
        gameThread = new Thread(this);
        // Let the JVM call run() method
        gameThread.start();
    }

    public Tank getTank() {
        return this.tank;
    }
    @Override
    public void run() {
        double drawInterval = 1_000_000_000.0 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime = 0;
        long timer = 0;
        int drawCount = 0;

        // Game loop
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // UPDATE: update information such as sprite positions
                update();
                // DRAW: Draw everything on the panel
                repaint();

                delta--;
                drawCount++;
            }

            if (timer >= 1_000_000_000) {
                System.out.println("Gun angle: " + tank.getGunAngle());
                System.out.println("Gun power: " + tank.getGunPower());
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
        if (gameState == PLAY_STATE) {
            tank.update();
            target.update();
        } else if (gameState == PAUSE_STATE) {

        }
    }

    public void paintComponent(Graphics g) {
        // Set JPanel
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // draw tiles
        tileManager.draw(g2);
        // draw tank
        tank.draw(g2);
        // draw target
        target.draw(g2);

        for (Explosion explosion: explosionList) {
            explosion.draw(g2);
        }

        g2.dispose();
    }

    public void reset() {
        tank.reset();
        target.reset();

        explosionList = new ArrayList<Explosion>();
    }
}
