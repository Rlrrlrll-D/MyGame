package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    public static final int maxScreenCol = 16;
    public static final int maxScreenRow = 14;
    public static final int maxWorldCol = 50;
    public static final int maxWorldRow = 50;
    public static final int pauseBehavior = 2;
    public static final int titleBehavior = 0;
    public static final int playBehavior = 1;
    public static final int dialogBehavior = 3;
    //  screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    final int FPS = 60;
    public int tileSize = originalTileSize * scale;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int dfl_X = screenWidth / 2 - tileSize / 2;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int dfl_Y = screenHeight / 2 - tileSize / 2;
    public CollisionChecker checker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public EventHandler eventHandler = new EventHandler(this);
    public UI ui = new UI(this);
    public int gameBehavior;
    public KeyHandler keyHandler = new KeyHandler(this);
    public Entity[] objects = new Entity[20];
    public Player player = new Player(this, keyHandler);
    public Entity[] npc = new Entity[10];
    public Entity[] mon = new Entity[20];

    TileManager tileManager = new TileManager(this);
    Sound sound = new Sound();
    Sound SFX = new Sound();
    ArrayList<Entity> entityArrayList = new ArrayList<>();
    public Thread gameThread;


    //public boolean musicOn;


    public GamePanel() throws IOException, FontFormatException {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(new Color(12, 23, 30));
        setDoubleBuffered(true);
        addKeyListener(keyHandler);
        setFocusable(true);


    }

    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        assetSetter.setMonster();
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

            player.update();

            for (Entity entity : npc) {

                if (entity != null) {
                    entity.update();
                }
            }
            for (Entity entity : mon) {

                if (entity != null) {
                    entity.update();
                }
            }

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
        if (gameBehavior != titleBehavior) {

            tileManager.drawing(graphics2D);


            entityArrayList.add(player);
            for (Entity entity : npc) {
                if (entity != null) {
                    entityArrayList.add(entity);
                }

            }
            for (Entity entity : objects) {
                if (entity != null) {
                    entityArrayList.add(entity);
                }

            }
            for (Entity entity : mon) {
                if (entity != null) {
                    entityArrayList.add(entity);
                }

            }

            entityArrayList.sort(Comparator.comparingInt(entity -> entity.worldY));

            for (Entity entity : entityArrayList) {
                entity.drawing(graphics2D);
            }
            for (int i = 0; i < entityArrayList.size(); i++) {
                entityArrayList.remove(i);
            }
        }

        ui.drawing(graphics2D);

        if (keyHandler.chkDrawTime) {

            long drawEnd = System.nanoTime();
            long passTime = drawEnd - drawStart;
            graphics2D.setFont(ui.Monica.deriveFont(Font.BOLD, 17F));
            graphics2D.setColor(Color.black);
            String str = "Draw Time: " + passTime;
            graphics2D.drawString(str.toUpperCase(), screenWidth - (int) graphics2D.getFontMetrics().getStringBounds(str + tileSize / 2, graphics2D).getWidth(), (int) (tileSize / 2 * 1.5));
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
