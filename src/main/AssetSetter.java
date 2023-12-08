package main;

import entity.NPC_Oldman;
import monster.Slime;
import objects.Key;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

    }

    public void setObject() {
//        gamePanel.motherObject[0] = new Key(gamePanel);
//        gamePanel.motherObject[0].worldX = 25 * gamePanel.tileSize;
//        gamePanel.motherObject[0].worldY = 14 * gamePanel.tileSize;
//
//        gamePanel.motherObject[1] = new Key(gamePanel);
//        gamePanel.motherObject[1].worldX = 40 * gamePanel.tileSize;
//        gamePanel.motherObject[1].worldY = 30 * gamePanel.tileSize;
//
        gamePanel.objects[0] = new Key(gamePanel);
        gamePanel.objects[0].worldX = 10 * gamePanel.tileSize;
        gamePanel.objects[0].worldY = 11 * gamePanel.tileSize;

        gamePanel.objects[1] = new Key(gamePanel);
        gamePanel.objects[1].worldX = 11 * gamePanel.tileSize;
        gamePanel.objects[1].worldY = 13 * gamePanel.tileSize;

        gamePanel.objects[2] = new Key(gamePanel);
        gamePanel.objects[2].worldX = 14 * gamePanel.tileSize;
        gamePanel.objects[2].worldY = 15 * gamePanel.tileSize;
////
//        gamePanel.objects[1] = new Door(gamePanel);
//        gamePanel.objects[1].worldX = 47 * gamePanel.tileSize;
//        gamePanel.objects[1].worldY = 10 * gamePanel.tileSize;
//
//        gamePanel.objects[2] = new Door(gamePanel);
//        gamePanel.objects[2].worldX = 47 * gamePanel.tileSize;
//        gamePanel.objects[2].worldY = 21 * gamePanel.tileSize;
//
//        gamePanel.objects[3] = new Door(gamePanel);
//        gamePanel.objects[3].worldX = 47 * gamePanel.tileSize;
//        gamePanel.objects[3].worldY = 47 * gamePanel.tileSize;
////
//        gamePanel.objects[4] = new Chest(gamePanel);
//        gamePanel.objects[4].worldX = 10 * gamePanel.tileSize;
//        gamePanel.objects[4].worldY = 46 * gamePanel.tileSize;
////
//
////
//
////
//        gamePanel.objects[7] = new Boots(gamePanel);
//        gamePanel.objects[7].worldX = 49 * gamePanel.tileSize;
//        gamePanel.objects[7].worldY = 48 * gamePanel.tileSize;
    }

    public void setNPC() {
        gamePanel.npc[0] = new NPC_Oldman(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 11;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 15;


    }

    public void setMonster() {
        int value = 1;
        for (int i = 0; i < 3; i++) {
            gamePanel.mon[i] = new Slime(gamePanel);
            gamePanel.mon[i].worldX = gamePanel.tileSize * (11 + value);
            gamePanel.mon[i].worldY = gamePanel.tileSize * 18;
            value++;

        }
    }

}