package main;

import objects.Chest;
import objects.Door;
import objects.Key;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

    }

    public void setObject() {
        gamePanel.motherObject[0] = new Key();
        gamePanel.motherObject[0].worldX = 25 * gamePanel.tileSize;
        gamePanel.motherObject[0].worldY = 14 * gamePanel.tileSize;

        gamePanel.motherObject[1] = new Key();
        gamePanel.motherObject[1].worldX = 40 * gamePanel.tileSize;
        gamePanel.motherObject[1].worldY = 30 * gamePanel.tileSize;

        gamePanel.motherObject[2] = new Key();
        gamePanel.motherObject[2].worldX = 10 * gamePanel.tileSize;
        gamePanel.motherObject[2].worldY = 11 * gamePanel.tileSize;

        gamePanel.motherObject[3] = new Door();
        gamePanel.motherObject[3].worldX = 47 * gamePanel.tileSize;
        gamePanel.motherObject[3].worldY = 10 * gamePanel.tileSize;

        gamePanel.motherObject[4] = new Chest();
        gamePanel.motherObject[4].worldX = 23 * gamePanel.tileSize;
        gamePanel.motherObject[4].worldY = 28 * gamePanel.tileSize;

        gamePanel.motherObject[5] = new Door();
        gamePanel.motherObject[5].worldX = 47 * gamePanel.tileSize;
        gamePanel.motherObject[5].worldY = 21 * gamePanel.tileSize;

        gamePanel.motherObject[6] = new Door();
        gamePanel.motherObject[6].worldX = 47 * gamePanel.tileSize;
        gamePanel.motherObject[6].worldY = 47 * gamePanel.tileSize;


    }

}