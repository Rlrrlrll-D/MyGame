package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventure 2D");
        window.setLocationRelativeTo(null);
        window.setAlwaysOnTop(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();


        window.setVisible(true);

        gamePanel.setupGame();

        gamePanel.startGameThread();

    }
}
