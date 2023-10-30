package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel();


        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Adventure 2D");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);
        window.add(gamePanel);
        window.pack();


        gamePanel.setupGame();

        gamePanel.startGameThread();

    }
}
