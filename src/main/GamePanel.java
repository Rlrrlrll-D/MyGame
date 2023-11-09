package main;

import entity.Entity;
import entity.Player;
import objects.MotherObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    //  screen settings
    final int originalTileSize = 16;
    final int scale = 3;

    public int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 14;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int dfl_X = screenWidth / 2 - tileSize / 2;
    public final int dfl_Y = screenHeight / 2 - tileSize / 2;


    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    final int FPS = 60;

    TileManager tileManager = new TileManager(this);


    public CollisionChecker checker = new CollisionChecker(this);

    public AssetSetter assetSetter = new AssetSetter(this);

    Sound sound = new Sound();
    Sound SFX = new Sound();
    public UI ui = new UI(this);
    public final int pauseBehavior = 2;
    public final int titleBehavior = 0;
    public final int playBehavior = 1;
    public final int dialogBehavior = 3;
    public int gameBehavior;


    KeyHandler keyHandler = new KeyHandler(this);
    public MotherObject[] motherObject = new MotherObject[20];
    public Entity[] npc = new Entity[10];

    public Player player = new Player(this, keyHandler);


    Thread gameThread;


    //public boolean musicOn;


    public GamePanel() throws IOException, FontFormatException {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(12, 23, 30));
        setDoubleBuffered(true);
        addKeyListener(keyHandler);
        setFocusable(true);


    }

    public void setupGame() {
        //assetSetter.setObject();
        assetSetter.setNPC();
        keyHandler.musicOn = true;
        gameBehavior = titleBehavior;
        // playMusic(0);
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
            while (accumulate > 1) {
                update();
                repaint();
                accumulate--;
            }
        }
    }

    public void update() {

        if (gameBehavior == playBehavior) {

            for (Entity entity : npc) {

                if (entity != null) {
                    entity.update();
                }
            }
            player.update();
        }
        if (gameBehavior == pauseBehavior) {

        }

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        long drawStart = 0;
        if (keyHandler.chkDrawTime) {
            drawStart = System.nanoTime();
        }
        if (gameBehavior == titleBehavior) {
            ui.drawing(graphics2D);
        } else {
            tileManager.drawing(graphics2D);
            for (MotherObject object : motherObject) {
                if (object != null) {
                    object.drawing(graphics2D, this);
                }
            }
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.drawing(graphics2D);
                }
            }

            player.drawing(graphics2D);
            ui.drawing(graphics2D);
        }


        if (keyHandler.chkDrawTime) {

            long drawEnd = System.nanoTime();
            long passTime = drawEnd - drawStart;
            graphics2D.setFont(ui.Pixel.deriveFont(Font.PLAIN, 10F));
            graphics2D.setColor(Color.black);
            graphics2D.drawString("Draw Time: " + passTime, 20, 20);
        }
        graphics2D.dispose();


    }


    public void playMusic(int count) {
        sound.setFile(count);
        sound.play();
        sound.loop();

    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSFX(int count) {
        SFX.setFile(count);
        SFX.play();
    }


}
