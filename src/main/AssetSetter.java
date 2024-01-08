package main;

import entity.Merchant;
import entity.Oldman;
import monster.Slime;
import objects.*;
import tile.interactive.DryTree;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void setObject() {
        int numMap = 0;
        gamePanel.objects[numMap][0] = new CoinBronze(gamePanel);
        gamePanel.objects[numMap][0].worldX = 10 * gamePanel.tileSize;
        gamePanel.objects[numMap][0].worldY = 11 * gamePanel.tileSize;


        gamePanel.objects[numMap][3] = new Axe(gamePanel);
        gamePanel.objects[numMap][3].worldX = 16 * gamePanel.tileSize;
        gamePanel.objects[numMap][3].worldY = 18 * gamePanel.tileSize;

        gamePanel.objects[numMap][4] = new ShieldBlue(gamePanel);
        gamePanel.objects[numMap][4].worldX = 18 * gamePanel.tileSize;
        gamePanel.objects[numMap][4].worldY = 19 * gamePanel.tileSize;

        gamePanel.objects[numMap][5] = new Door(gamePanel);
        gamePanel.objects[numMap][5].worldX = 45 * gamePanel.tileSize;
        gamePanel.objects[numMap][5].worldY = 36 * gamePanel.tileSize;

        gamePanel.objects[numMap][6] = new Door(gamePanel);
        gamePanel.objects[numMap][6].worldX = 45 * gamePanel.tileSize;
        gamePanel.objects[numMap][6].worldY = 26 * gamePanel.tileSize;

        gamePanel.objects[numMap][7] = new Heart(gamePanel);
        gamePanel.objects[numMap][7].worldX = 41 * gamePanel.tileSize;
        gamePanel.objects[numMap][7].worldY = 43 * gamePanel.tileSize;

        gamePanel.objects[numMap][8] = new Chest(gamePanel, new Key(gamePanel));
        gamePanel.objects[numMap][8].worldX = 45 * gamePanel.tileSize;
        gamePanel.objects[numMap][8].worldY = 39 * gamePanel.tileSize;

        gamePanel.objects[numMap][9] = new PotionRed(gamePanel);
        gamePanel.objects[numMap][9].worldX = 35 * gamePanel.tileSize;
        gamePanel.objects[numMap][9].worldY = 43 * gamePanel.tileSize;

        gamePanel.objects[numMap][10] = new PotionRed(gamePanel);
        gamePanel.objects[numMap][10].worldX = 32 * gamePanel.tileSize;
        gamePanel.objects[numMap][10].worldY = 43 * gamePanel.tileSize;

        gamePanel.objects[numMap][11] = new PotionRed(gamePanel);
        gamePanel.objects[numMap][11].worldX = 30 * gamePanel.tileSize;
        gamePanel.objects[numMap][11].worldY = 43 * gamePanel.tileSize;

        gamePanel.objects[numMap][12] = new Lantern(gamePanel);
        gamePanel.objects[numMap][12].worldX = 27 * gamePanel.tileSize;
        gamePanel.objects[numMap][12].worldY = 43 * gamePanel.tileSize;

        gamePanel.objects[numMap][13] = new Tent(gamePanel);
        gamePanel.objects[numMap][13].worldX = 25 * gamePanel.tileSize;
        gamePanel.objects[numMap][13].worldY = 43 * gamePanel.tileSize;
//        gamePanel.objects[2] = nw Door(gamePanel);
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
        int numMap = 0;
        int i = 0;
        gamePanel.npc[numMap][i] = new Oldman(gamePanel);
        gamePanel.npc[numMap][i].worldX = gamePanel.tileSize * 11;
        gamePanel.npc[numMap][i].worldY = gamePanel.tileSize * 15;

        numMap = 1;
        gamePanel.npc[numMap][i] = new Merchant(gamePanel);
        gamePanel.npc[numMap][i].worldX = gamePanel.tileSize * 20;
        gamePanel.npc[numMap][i].worldY = gamePanel.tileSize * 47;

    }

    public void setMonster() {
        int numMap = 0;
        int value = 1;
        for (int i = 0; i < 3; i++) {
            gamePanel.mon[numMap][i] = new Slime(gamePanel);
            gamePanel.mon[numMap][i].worldX = gamePanel.tileSize * (11 + value);
            gamePanel.mon[numMap][i].worldY = gamePanel.tileSize * 18;
            value++;

        }
        numMap = 1;
        for (int i = 0; i < 6; i++) {
            gamePanel.mon[numMap][i] = new Slime(gamePanel);
            gamePanel.mon[numMap][i].worldX = gamePanel.tileSize * (11 + value);
            gamePanel.mon[numMap][i].worldY = gamePanel.tileSize * 18;
            value++;

        }
    }

    public void setInteractiveTile() {
        int numMap = 0;
        for (int i = 0; i < 6; i++) {
            gamePanel.interactiveTile[numMap][i] = new DryTree(gamePanel, 25, 13 + i);
        }
    }
}