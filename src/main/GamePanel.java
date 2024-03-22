package main;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import tile.Map;
import tile.TileManager;
import tile.interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    public static final int maxScreenCol = 20;
    public static final int maxScreenRow = 12;
    public final static int maxMap = 10;
    public static final int titleBehavior = 0;
    public static final int playBehavior = 1;

    public static final int pauseBehavior = 2;
    public static final int dialogBehavior = 3;
    public static final int characterBehavior = 4;
    public static final int optionsBehavior = 5;
    public static final int gameOverBehavior = 6;
    public static final int transitionBehavior = 7;
    public static final int tradeBehavior = 8;
    public static final int sleepBehavior = 9;
    public static final int mapBehavior = 10;
    public static final int cutSceneBehavior = 11;
    //  screen settings
    private static final int originalTileSize = 16;
    private static final int scale = 3;
    private static final int FPS = 60;
    public static int maxWorldCol = 50;
    public static int maxWorldRow = 50;
    public int gameBehavior;
    public int currentMap = 0;
    public final int outside = 50;
    public final int indoor = 51;
    public final int dungeon = 52;
    public int currentArea;
    public int nextArea;
    public int tileSize = originalTileSize * scale;
    public int screenWidth = tileSize * maxScreenCol;
    public final int dfl_X = screenWidth / 2 - tileSize / 2;

    int screenWidth2 = screenWidth;
    public int screenHeight = tileSize * maxScreenRow;
    public final int dfl_Y = screenHeight / 2 - tileSize / 2;
    int screenHeight2 = screenHeight;
    public boolean fullScreenOn;
    public boolean musicOn;
    public boolean bossBattleOn;
    public CollisionChecker checker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public EventHandler eventHandler = new EventHandler(this);
    public PathFinder pathFinder = new PathFinder(this);
    public UI ui = new UI(this);
    public ArrayList<Entity> particleArrayList = new ArrayList<>();
    public KeyHandler keyHandler = new KeyHandler(this);
    public Player player = new Player(this, keyHandler);
    public Entity[][] npc = new Entity[maxMap][10];
    public Entity[][] objects = new Entity[maxMap][30];
    public InteractiveTile[][] interactiveTile = new InteractiveTile[maxMap][50];
    public Entity[][] mon = new Entity[maxMap][20];
    public Entity[][] projectile = new Entity[maxMap][20];
    public Sound sound = new Sound();
    public TileManager tileManager = new TileManager(this);
    public Thread gameThread;
    public EntityGenerator entityGenerator = new EntityGenerator(this);
    public CutSceneManager cutSceneManager = new CutSceneManager(this);
    EnvironmentManager environmentManager = new EnvironmentManager(this);
    Map map = new Map(this);
    BufferedImage imgTempScreen;
    Graphics2D graphics2D;
    Config config = new Config(this);
    Sound SFX = new Sound();
    SaveLoad saveLoad = new SaveLoad(this);
    ArrayList<Entity> entityArrayList = new ArrayList<>();


    public GamePanel() throws Exception {
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
        environmentManager.setup();
        gameBehavior = titleBehavior;
        currentArea = outside;
        imgTempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB_PRE);
        graphics2D = (Graphics2D) imgTempScreen.getGraphics();
        setFullScreen();

    }


    public void resetGame(boolean restart) {
        stopMusic();
        currentArea = outside;
        removeTempEntity();
        bossBattleOn = false;
        player.setDefaultPos();
        player.restoreStatus();
        player.resetCounter();
        assetSetter.setNPC();
        assetSetter.setMonster();
        if (restart) {
            player.setDefaultVal();
            assetSetter.setObject();
            assetSetter.setInteractiveTile();
            environmentManager.lighting.resetDay();
        }
    }

    private void setFullScreen() {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();

        if (fullScreenOn) {
            graphicsDevice.setFullScreenWindow(Main.window);
            screenWidth2 = Main.window.getWidth();
            screenHeight2 = Main.window.getHeight();
        } else {
            graphicsDevice.setFullScreenWindow(null);
            screenWidth2 = screenWidth;
            screenHeight2 = screenHeight;
        }
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
                try {
                    update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    toBufferImage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                drawToScreen();
                accumulate--;
            }
        }
    }

    private void update() throws IOException {

        if (gameBehavior == playBehavior) {

            player.update();

            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            for (int i = 0; i < mon[1].length; i++)
                if (mon[currentMap][i] != null) {
                    if (mon[currentMap][i].isAlive && !mon[currentMap][i].isDying) {
                        mon[currentMap][i].update();
                    }
                    if (!mon[currentMap][i].isAlive) {
                        mon[currentMap][i].checkDrop();
                        mon[currentMap][i] = null;
                    }
                }
            for (int i = 0; i < projectile[1].length; i++)
                if (projectile[currentMap][i] != null) {
                    if (projectile[currentMap][i].isAlive) {
                        projectile[currentMap][i].update();
                    }
                    if (!projectile[currentMap][i].isAlive) {
                        projectile[currentMap][i] = null;
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
            for (int i = 0; i < interactiveTile[1].length; i++) {
                if (interactiveTile[currentMap][i] != null) {
                    interactiveTile[currentMap][i].update();
                }
            }
            environmentManager.update();
        }
        if (gameBehavior == pauseBehavior) {
        }
    }

    private void toBufferImage() throws IOException {

        super.paintComponent(graphics2D);
        long drawStart = 0;
        if (keyHandler.chkDrawTime) {
            drawStart = System.nanoTime();
        }
        if (gameBehavior == titleBehavior) {
            ui.drawing(graphics2D);

        } else if (gameBehavior == mapBehavior) {
            map.drawFullMapScreen(graphics2D);
        } else {
            tileManager.drawing(graphics2D);
            for (int i = 0; i < interactiveTile[1].length; i++) {
                if (interactiveTile[currentMap][i] != null)
                    interactiveTile[currentMap][i].drawing(graphics2D);
            }

            entityArrayList.add(player);

            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityArrayList.add(npc[currentMap][i]);
                }
            }
            for (int i = 0; i < objects[1].length; i++) {
                if (objects[currentMap][i] != null) {
                    entityArrayList.add(objects[currentMap][i]);
                }
            }
            for (int i = 0; i < mon[1].length; i++) {
                if (mon[currentMap][i] != null) {
                    entityArrayList.add(mon[currentMap][i]);
                }
            }
            for (int i = 0; i < projectile[1].length; i++) {
                if (projectile[currentMap][i] != null) {
                    entityArrayList.add(projectile[currentMap][i]);
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
            environmentManager.drawing(graphics2D);
            map.drawMiniMap(graphics2D);
            cutSceneManager.drawing(graphics2D);
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

    private void drawToScreen() {
        Graphics graphics = getGraphics();
        graphics.drawImage(imgTempScreen, 0, 0, screenWidth2, screenHeight2, null);
        graphics.dispose();
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

    public void playGameOver() {
        sound.setFile(16);
        sound.play();
    }

    public void changeArea() {
        if (nextArea != currentArea) {
            stopMusic();
            if (nextArea == outside) {
                playMusic(0);
            }
            if (nextArea == indoor) {
                playMusic(22);
            }
            if (nextArea == dungeon) {
                playMusic(21);
            }
            assetSetter.setNPC();
        }
        currentArea = nextArea;
        assetSetter.setMonster();
    }
    public void removeTempEntity() {
        for (int i = 0; i < maxMap; i++) {
            for (int j = 0; j < objects[1].length; j++) {
                if (objects[i][j] != null && objects[i][j].mustDelete) {
                    objects[i][j] = null;
                }
            }
        }
    }
}
