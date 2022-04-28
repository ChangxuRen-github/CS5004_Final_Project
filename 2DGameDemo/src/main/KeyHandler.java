package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

        switch(gamePanel.gameState) {
            case GamePanel.PLAY_STATE -> playState(keyCode);
            case GamePanel.PAUSE_STATE -> pauseState(keyCode);
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

    private void playState(int keyCode) {
        switch(keyCode) {
            case KeyEvent.VK_A -> gamePanel.tank.incrementGunAngle();
            case KeyEvent.VK_D -> gamePanel.tank.decrementGunAngle();
            case KeyEvent.VK_W -> gamePanel.tank.incrementGunPower();
            case KeyEvent.VK_S -> gamePanel.tank.decrementGunPower();
            case KeyEvent.VK_LEFT -> leftPressed = true;
            case KeyEvent.VK_RIGHT -> rightPressed = true;
            case KeyEvent.VK_SPACE -> gamePanel.tank.fire();
            case KeyEvent.VK_P -> gamePanel.gameState = GamePanel.PAUSE_STATE;
        }

    }

    private void pauseState(int keyCode) {

        switch (keyCode) {
            case KeyEvent.VK_P -> gamePanel.gameState = GamePanel.PLAY_STATE;
            case KeyEvent.VK_R -> gamePanel.reset();
        }
    }

}
