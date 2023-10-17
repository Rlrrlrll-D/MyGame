package main;

import objects.Door;
import objects.Key;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel){

        this.gamePanel = gamePanel;

    }
    public void setObject(){
        gamePanel.motherObject[0] = new Key();
        gamePanel.motherObject[0].worldX =25*gamePanel.tileSize;
        gamePanel.motherObject[0].worldY = 14*gamePanel.tileSize;

        gamePanel.motherObject[1] = new Door();
        gamePanel.motherObject[1].worldX =23*gamePanel.tileSize;
        gamePanel.motherObject[1].worldY = 21*gamePanel.tileSize;

    }

}