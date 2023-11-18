package main;

import entity.NPC_Oldman;
import monster.MonsterSlime;

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
//        gamePanel.objects[0] = new Key(gamePanel);
//        gamePanel.objects[0].worldX = 10 * gamePanel.tileSize;
//        gamePanel.objects[0].worldY = 11 * gamePanel.tileSize;
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
        gamePanel.mon[0] = new MonsterSlime(gamePanel);
        gamePanel.mon[0].worldX = gamePanel.tileSize * 11;
        gamePanel.mon[0].worldY = gamePanel.tileSize * 18;

        gamePanel.mon[1] = new MonsterSlime(gamePanel);
        gamePanel.mon[1].worldX = gamePanel.tileSize * 11;
        gamePanel.mon[1].worldY = gamePanel.tileSize * 20;


    }

}