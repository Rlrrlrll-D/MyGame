package main;

import entity.NPC_Oldman;
import monster.Slime;
import objects.*;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

    }

    public void setObject() {

        gamePanel.objects[0] = new CoinBronze(gamePanel);
        gamePanel.objects[0].worldX = 10 * gamePanel.tileSize;
        gamePanel.objects[0].worldY = 11 * gamePanel.tileSize;

        gamePanel.objects[1] = new CoinBronze(gamePanel);
        gamePanel.objects[1].worldX = 11 * gamePanel.tileSize;
        gamePanel.objects[1].worldY = 13 * gamePanel.tileSize;

        gamePanel.objects[2] = new CoinBronze(gamePanel);
        gamePanel.objects[2].worldX = 14 * gamePanel.tileSize;
        gamePanel.objects[2].worldY = 15 * gamePanel.tileSize;


        gamePanel.objects[3] = new Axe(gamePanel);
        gamePanel.objects[3].worldX = 16 * gamePanel.tileSize;
        gamePanel.objects[3].worldY = 18 * gamePanel.tileSize;

        gamePanel.objects[4] = new ShieldBlue(gamePanel);
        gamePanel.objects[4].worldX = 18 * gamePanel.tileSize;
        gamePanel.objects[4].worldY = 19 * gamePanel.tileSize;
//
        gamePanel.objects[5] = new PotionRed(gamePanel);
        gamePanel.objects[5].worldX = 47 * gamePanel.tileSize;
        gamePanel.objects[5].worldY = 10 * gamePanel.tileSize;
//
        gamePanel.objects[6] = new ManaCrystal(gamePanel);
        gamePanel.objects[6].worldX = 47 * gamePanel.tileSize;
        gamePanel.objects[6].worldY = 46 * gamePanel.tileSize;


        gamePanel.objects[7] = new Heart(gamePanel);
        gamePanel.objects[7].worldX = 41 * gamePanel.tileSize;
        gamePanel.objects[7].worldY = 43 * gamePanel.tileSize;
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