package main;

import javax.swing.*;
import java.util.Objects;

public class Main {

    public static JFrame window;

    public static void main(String[] args) throws Exception {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("My Hero Adventure");
        new Main().setIcon();

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        gamePanel.config.loadConfig();
        if (gamePanel.fullScreenOn) {
            window.setUndecorated(true);
        }
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
    private void setIcon() {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/player/icon.png")));
        window.setIconImage(icon.getImage());
    }
}
