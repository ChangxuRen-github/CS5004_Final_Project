package main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setLayout(new BorderLayout(1, 1));

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        GameController controller = new GameController(gamePanel);

        window.add(controller, BorderLayout.NORTH);
        window.add(gamePanel, BorderLayout.SOUTH);

        window.pack();

        //window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();
    }
}
