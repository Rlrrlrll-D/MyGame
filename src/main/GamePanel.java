package main;

import entity.Player;
import objects.MotherObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //  screen settings
    final int originalTileSize = 16;
    final int scale = 3;

    public int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int dfl_X = screenWidth / 2 - tileSize / 2;
    public final int dfl_Y = screenHeight / 2 - tileSize / 2;

    // world settings

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    public CollisionChecker checker = new CollisionChecker(this);

    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);

    public MotherObject[] motherObject =new MotherObject[10];
    TileManager tileManager = new TileManager(this);

    final int FPS = 60;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(38, 37, 37, 255));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }
    public void setupGame(){
        assetSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double interval = (double) 1000000000 / FPS;
        double accumulate = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            accumulate += (currentTime - lastTime) / interval;
            lastTime = currentTime;
            while (accumulate >= 1) {
                update();
                repaint();
                accumulate--;
            }
        }
    }

    public void update() {
        player.update();

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        tileManager.drawing(graphics2D);
        for (MotherObject object : motherObject) {
            if (object != null) {
                object.drawing(graphics2D, this);
            }
        }
        player.drawing(graphics2D);
        graphics2D.dispose();

    }
}
