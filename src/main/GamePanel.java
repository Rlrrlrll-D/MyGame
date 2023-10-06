package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    int FPS = 60;


    int plrX= screenWidth/2;
    int plrY= screenHeight/2;
    int plrSpeed = 3;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(163, 130, 20));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double interval = (double) 1000000000 /FPS;
        double accumulate = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            accumulate += (currentTime - lastTime)/interval;
            lastTime = currentTime;
            while (accumulate>=1){
                update();
                repaint();
                accumulate --;
            }
        }
    }

    public void update() {
        if (keyHandler.upPressed){
            plrY-=plrSpeed;
        } else if (keyHandler.downPressed) {
            plrY +=plrSpeed;
        } else if (keyHandler.leftPressed) {
            plrX-=plrSpeed;
        } else if (keyHandler.rightPressed) {
            plrX+=plrSpeed;
        }

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(new Color(119, 52, 6));
        graphics2D.fillRect(plrX, plrY, tileSize, tileSize);
        graphics2D.dispose();

    }
}
