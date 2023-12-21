package main;


import entity.Entity;
import entity.Player;
import tile.TileManager;
import tile.interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    public int gameBehavior;
    public static final int maxScreenCol = 20;
    public static final int maxScreenRow = 12;
    public static final int maxWorldCol = 50;
    public static final int maxWorldRow = 50;

    public static final int titleBehavior = 0;
    public static final int playBehavior = 1;
    public static final int pauseBehavior = 2;
    public static final int dialogBehavior = 3;
    public static final int characterBehavior = 4;
    public static final int optionsBehavior = 5;
    public static final int gameOverBehavior = 6;

    //  screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    final int FPS = 60;
    public int tileSize = originalTileSize * scale;
    public int screenWidth = tileSize * maxScreenCol;
    public final int dfl_X = screenWidth / 2 - tileSize / 2;
    public int screenHeight = tileSize * maxScreenRow;
    public final int dfl_Y = screenHeight / 2 - tileSize / 2;
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    public boolean fullScreenOn;
    public boolean musicOn;
    BufferedImage imgTempScreen;
    Graphics2D graphics2D;
    public CollisionChecker checker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public EventHandler eventHandler = new EventHandler(this);

    Config config = new Config(this);

    public UI ui = new UI(this);
    public ArrayList<Entity> particleArrayList = new ArrayList<>();
    public KeyHandler keyHandler = new KeyHandler(this);

    public Player player = new Player(this, keyHandler);
    public Entity[] npc = new Entity[10];
    public Entity[] objects = new Entity[20];
    public InteractiveTile[] interactiveTile = new InteractiveTile[50];
    public Entity[] mon = new Entity[20];

    Sound sound = new Sound();
    Sound SFX = new Sound();
    ArrayList<Entity> entityArrayList = new ArrayList<>();
    public ArrayList<Entity> projectileArrayList = new ArrayList<>();
    TileManager tileManager = new TileManager(this);
    public Thread gameThread;


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
        assetSetter.setInteractiveTile();
        gameBehavior = titleBehavior;
        imgTempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        graphics2D = (Graphics2D) imgTempScreen.getGraphics();

        if (fullScreenOn) {
            setFullScreen();
        }
    }

    public void retry() {
        player.setDefaultPos();
        player.restoreLifeMana();
        assetSetter.setNPC();
        assetSetter.setMonster();
        //musicOn =false;

    }

    public void restart() {
        player.setDefaultPos();
        player.setDefaultVal();
        player.restoreLifeMana();
        player.setInventory();
        assetSetter.setObject();
        assetSetter.setNPC();
        assetSetter.setMonster();
        assetSetter.setInteractiveTile();
        musicOn = false;
    }

    public void setFullScreen() {

        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        graphicsDevice.setFullScreenWindow(Main.window);

        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();

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
                try {
                    toBufferImage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                drawToScreen();
                // repaint();
                accumulate--;
            }
        }
    }

    public void update() {

        if (gameBehavior == playBehavior) {

            player.update();

            for (Entity entity : npc)
                if (entity != null) {
                    entity.update();
                }
            for (int i = 0; i < mon.length; i++)
                if (mon[i] != null) {
                    if (mon[i].isAlive) {
                        mon[i].update();
                    }
                    if (!mon[i].isAlive) {
                        mon[i].checkDrop();
                        mon[i] = null;
                    }
                }
            for (int i = 0; i < projectileArrayList.size(); i++)
                if (projectileArrayList.get(i) != null) {
                    if (projectileArrayList.get(i).isAlive) {
                        projectileArrayList.get(i).update();
                    }
                    if (!projectileArrayList.get(i).isAlive) {
                        projectileArrayList.remove(i);
                    }
                }
            for (int i = 0; i < particleArrayList.size(); i++)
                if (particleArrayList.get(i) != null) {
                    if (particleArrayList.get(i).isAlive) {
                        particleArrayList.get(i).update();
                    }
                    if (!particleArrayList.get(i).isAlive) {
                        particleArrayList.remove(i);
                    }
                }
            for (InteractiveTile tile : interactiveTile) {
                if (tile != null) {
                    tile.update();
                }
            }
        }
        if (gameBehavior == pauseBehavior) {
        }
    }

    public void toBufferImage() throws IOException {
        super.paintComponent(graphics2D);
        long drawStart = 0;
        if (keyHandler.chkDrawTime) {
            drawStart = System.nanoTime();
        }
        if (gameBehavior == titleBehavior) {
            ui.drawing(graphics2D);

        } else {

            tileManager.drawing(graphics2D);

            for (InteractiveTile tile : interactiveTile) {
                if (tile != null) {
                    tile.drawing(graphics2D);
                }
            }

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
            for (Entity entity : projectileArrayList) {
                if (entity != null) {
                    entityArrayList.add(entity);
                }

            }
            for (Entity entity : particleArrayList) {
                if (entity != null) {
                    entityArrayList.add(entity);
                }
            }
            entityArrayList.sort(Comparator.comparingInt(entity -> entity.worldY));

            for (Entity entity : entityArrayList) {
                entity.drawing(graphics2D);
            }
            entityArrayList.clear();
            ui.drawing(graphics2D);
        }
        if (keyHandler.chkDrawTime) {

            long drawEnd = System.nanoTime();
            long passTime = drawEnd - drawStart;
            graphics2D.setFont(ui.Monica.deriveFont(Font.BOLD, 17F));
            graphics2D.setColor(Color.black);
            String str = "Draw Time: " + passTime;
            graphics2D.drawString(str.toUpperCase(), screenWidth - (int) graphics2D.getFontMetrics().getStringBounds(str + tileSize / 2, graphics2D).getWidth(), (int) (tileSize / 2 * 1.5));
        }
    }

    public void drawToScreen() {
        Graphics graphics = getGraphics();
        graphics.drawImage(imgTempScreen, 0, 0, screenWidth2, screenHeight2, null);
        graphics.dispose();
    }

//    public void paintComponent(Graphics graphics) {


//        super.paintComponent(graphics);
//        Graphics2D graphics2D = (Graphics2D) graphics;
//        long drawStart = 0;
//        if (keyHandler.chkDrawTime) {
//            drawStart = System.nanoTime();
//        }
//        if (gameBehavior == titleBehavior) {
//            ui.drawing(graphics2D);
//        } else {
//
//            tileManager.drawing(graphics2D);
//
//            for (InteractiveTile tile : interactiveTile) {
//                if (tile != null) {
//                    tile.drawing(graphics2D);
//                }
//            }
//
//            entityArrayList.add(player);
//
//            for (Entity entity : npc) {
//                if (entity != null) {
//                    entityArrayList.add(entity);
//                }
//
//            }
//            for (Entity entity : objects) {
//                if (entity != null) {
//                    entityArrayList.add(entity);
//                }
//
//            }
//            for (Entity entity : mon) {
//                if (entity != null) {
//                    entityArrayList.add(entity);
//                }
//
//            }
//            for (Entity entity : projectileArrayList) {
//                if (entity != null) {
//                    entityArrayList.add(entity);
//                }
//
//            }
//            for (Entity entity : particleArrayList) {
//                if (entity != null) {
//                    entityArrayList.add(entity);
//                }
//            }
//            entityArrayList.sort(Comparator.comparingInt(entity -> entity.worldY));
//
//            for (Entity entity : entityArrayList) {
//                entity.drawing(graphics2D);
//            }
//            entityArrayList.clear();
//            ui.drawing(graphics2D);
//        }
//        if (keyHandler.chkDrawTime) {
//
//            long drawEnd = System.nanoTime();
//            long passTime = drawEnd - drawStart;
//            graphics2D.setFont(ui.Monica.deriveFont(Font.BOLD, 17F));
//            graphics2D.setColor(Color.black);
//            String str = "Draw Time: " + passTime;
//            graphics2D.drawString(str.toUpperCase(), screenWidth - (int) graphics2D.getFontMetrics().getStringBounds(str + tileSize / 2, graphics2D).getWidth(), (int) (tileSize / 2 * 1.5));
//        }
//        graphics2D.dispose();
//    }

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

    public void playGameOver() {
        sound.setFile(16);
        sound.play();

    }
}
