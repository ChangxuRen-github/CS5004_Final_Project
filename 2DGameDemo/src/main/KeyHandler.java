package main;

import entity.Tank;
import tile.TileManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {
    private final GamePanel gamePanel;
    public boolean leftPressed, rightPressed;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_A) {
            // press key A to increment gun angel
            gamePanel.tank.incrementGunAngle();
        } else if (keyCode == KeyEvent.VK_D) {
            // press key D to decrement gun angel
            gamePanel.tank.decrementGunAngle();
        } else if (keyCode == KeyEvent.VK_W) {
            // press key W to increment gun power
            gamePanel.tank.incrementGunPower();
        } else if (keyCode == KeyEvent.VK_S) {
            // press key S to decrement gun power
            gamePanel.tank.decrementGunPower();
        } else if (keyCode == KeyEvent.VK_LEFT) {
            // press left key to move tank to left
            leftPressed = true;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            // press right key to move tank to right
            rightPressed = true;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            gamePanel.tank.fire();
        } else if (keyCode == KeyEvent.VK_P) {
            if (gamePanel.gameState == GamePanel.PLAY_STATE) {
                gamePanel.gameState = GamePanel.PAUSE_STATE;
            } else if (gamePanel.gameState == GamePanel.PAUSE_STATE) {
                gamePanel.gameState = GamePanel.PLAY_STATE;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = false;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

    }

}
