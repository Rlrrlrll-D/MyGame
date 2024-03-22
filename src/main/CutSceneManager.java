package main;

import entity.PlayerDummy;
import objects.BigDiamond;
import objects.DoorIron;

import java.awt.*;
import java.util.Objects;

public class CutSceneManager {
    public final int NA = 0;
    public final int skeletonZ = 1;
    public final int end = 2;
    public int scene;
    public int scenePhase;
    public int counter;
    float alpha;
    int y;
    GamePanel gamePanel;
    Graphics2D graphics2D;

    public CutSceneManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void drawing(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
        switch (scene) {
            case NA:
                break;
            case skeletonZ:
                skeletonZ();
                break;
            case end:
                end();
                break;
        }
    }

    private void skeletonZ() {
        if (scenePhase == 0) {
            gamePanel.bossBattleOn = true;
            for (int i = 0; i < gamePanel.objects[1].length; i++) {
                if (gamePanel.objects[gamePanel.currentMap][i] == null) {
                    gamePanel.objects[gamePanel.currentMap][i] = new DoorIron(gamePanel);
                    gamePanel.objects[gamePanel.currentMap][i].worldX = 24 * gamePanel.tileSize;
                    gamePanel.objects[gamePanel.currentMap][i].worldY = 39 * gamePanel.tileSize;
                    gamePanel.objects[gamePanel.currentMap][i].mustDelete = true;
                    gamePanel.playSFX(19);
                    break;
                }
            }
            for (int i = 0; i < gamePanel.npc[1].length; i++) {
                if (gamePanel.npc[gamePanel.currentMap][i] == null) {
                    gamePanel.npc[gamePanel.currentMap][i] = new PlayerDummy(gamePanel);
                    gamePanel.npc[gamePanel.currentMap][i].worldX = gamePanel.player.worldX;
                    gamePanel.npc[gamePanel.currentMap][i].worldY = gamePanel.player.worldY;
                    gamePanel.npc[gamePanel.currentMap][i].direct = gamePanel.player.direct;
                    break;
                }
            }
            gamePanel.player.draw = false;
            gamePanel.stopMusic();
            scenePhase++;
        }
        if (scenePhase == 1) {
            gamePanel.player.worldY -= 2;
            if (gamePanel.player.worldY < 16 * gamePanel.tileSize) {
                scenePhase++;
            }

        }
        if (scenePhase == 2) {
            for (int i = 0; i < gamePanel.mon[1].length; i++) {
                if ((gamePanel.mon[gamePanel.currentMap][i] != null) && (Objects.equals(gamePanel.mon[gamePanel.currentMap][i].name, "SkeletonZ"))) {

                    gamePanel.mon[gamePanel.currentMap][i].sleeping = false;
                    gamePanel.ui.npc = gamePanel.mon[gamePanel.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }
        if (scenePhase == 3) {
            gamePanel.ui.drawDialogScreen();

        }
        if (scenePhase == 4) {
            for (int i = 0; i < gamePanel.npc[1].length; i++) {
                if (gamePanel.npc[gamePanel.currentMap][i] != null && Objects.equals(gamePanel.npc[gamePanel.currentMap][i].name, "PlayerDummy")) {
                    gamePanel.player.worldX = gamePanel.npc[gamePanel.currentMap][i].worldX;
                    gamePanel.player.worldY = gamePanel.npc[gamePanel.currentMap][i].worldY;
                    gamePanel.npc[gamePanel.currentMap][i] = null;
                    break;
                }

            }
            gamePanel.player.draw = true;
            scene = NA;
            scenePhase = 0;
            gamePanel.gameBehavior = GamePanel.playBehavior;
            gamePanel.musicOn = false;
            gamePanel.stopMusic();
            gamePanel.keyHandler.musicCheck(25);

        }
    }

    private void end() {
        if (scenePhase == 0) {
            gamePanel.stopMusic();
            gamePanel.ui.npc = new BigDiamond(gamePanel);
            scenePhase++;
        }
        if (scenePhase == 1) {
            gamePanel.ui.drawDialogScreen();
            //scenePhase++;
        }
        if (scenePhase == 2) {
            gamePanel.playSFX(26);
            scenePhase++;
        }
        if (scenePhase == 3) {
            if (counterReached(300)) {
                scenePhase++;
            }
        }
        if (scenePhase == 4) {
            alpha += 0.007f;
            if (alpha > 1f) {
                alpha = 1f;
            }
            drawFade(alpha);
            if (alpha == 1f) {
                alpha = 0;
                scenePhase++;
            }
        }
        if (scenePhase == 5) {
            drawFade(1f);
            alpha += 0.007f;
            if (alpha > 1f) {
                alpha = 1f;
            }
            String string = "The End";
            drawString(alpha, 55f, gamePanel.screenHeight / 2, string);
            if(counterReached(600)) {
                scenePhase++;
            }
        }
    }

    private boolean counterReached(int count) {
        counter++;
        if (counter >= count) {
            counter = 0;
            return true;
        }
        return false;
    }

    private void drawFade(float alpha) {

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }

    public void drawString(float alpha, float fontSize, int y, String string ) {
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        graphics2D.setFont(graphics2D.getFont().deriveFont(fontSize));
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(string, gamePanel.screenWidth / 2 - string.length() * 5, y);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

    }
}