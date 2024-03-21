package main;

import entity.PlayerDummy;
import objects.DoorIron;

import java.awt.*;
import java.util.Objects;

public class CutSceneManager {
    public final int NA = 0;
    public final int skeletonZ = 1;
    public int scene;
    public int scenePhase;
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
    }
}