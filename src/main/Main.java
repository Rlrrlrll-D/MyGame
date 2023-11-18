package main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, FontFormatException {

        JFrame window = new JFrame();

        window.setLocationRelativeTo(null);
        window.setTitle("My Hero Adventure");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        GamePanel gamePanel = new GamePanel();

        window.setVisible(true);
        window.setResizable(false);
        window.add(gamePanel);
        window.pack();

        gamePanel.setupGame();
        gamePanel.startGameThread();


    }
}
