package main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static JFrame window;
    public static void main(String[] args) throws IOException, FontFormatException {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("My Hero Adventure");
        window.setUndecorated(true);


        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();


    }
}
