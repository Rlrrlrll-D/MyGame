package main;

import data.Progress;
import entity.BigRock;
import entity.Merchant;
import entity.Oldman;
import monster.*;
import objects.*;
import tile.interactive.DestructibleWall;
import tile.interactive.DryTree;
import tile.interactive.MetalPlate;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        setObjectForMap0();
        setObjectForMap2();
        setObjectForMap3();
    }

    private void setObjectForMap0() {
        int numMap = 0;
//        gamePanel.objects[numMap][0] = new CoinBronze(gamePanel);
//        gamePanel.objects[numMap][0].worldX = 10 * gamePanel.tileSize;
//        gamePanel.objects[numMap][0].worldY = 11 * gamePanel.tileSize;

        gamePanel.objects[numMap][3] = new Axe(gamePanel);
        gamePanel.objects[numMap][3].worldX = 6 * gamePanel.tileSize;
        gamePanel.objects[numMap][3].worldY = 7 * gamePanel.tileSize;

//        gamePanel.objects[numMap][4] = new ShieldBlue(gamePanel);
//        gamePanel.objects[numMap][4].worldX = 18 * gamePanel.tileSize;
//        gamePanel.objects[numMap][4].worldY = 19 * gamePanel.tileSize;

//        gamePanel.objects[numMap][5] = new Door(gamePanel);
//        gamePanel.objects[numMap][5].worldX = 47 * gamePanel.tileSize;
//        gamePanel.objects[numMap][5].worldY = 36 * gamePanel.tileSize;
//
//        gamePanel.objects[numMap][6] = new Door(gamePanel);
//        gamePanel.objects[numMap][6].worldX = 47 * gamePanel.tileSize;
//        gamePanel.objects[numMap][6].worldY = 26 * gamePanel.tileSize;

//        gamePanel.objects[numMap][7] = new Heart(gamePanel);
//        gamePanel.objects[numMap][7].worldX = 41 * gamePanel.tileSize;
//        gamePanel.objects[numMap][7].worldY = 43 * gamePanel.tileSize;

        gamePanel.objects[numMap][8] = new Chest(gamePanel);
        gamePanel.objects[numMap][8].setLoot(new Key(gamePanel));
        gamePanel.objects[numMap][8].worldX = 32 * gamePanel.tileSize;
        gamePanel.objects[numMap][8].worldY = 30 * gamePanel.tileSize;

        gamePanel.objects[numMap][17] = new Chest(gamePanel);
        gamePanel.objects[numMap][17].setLoot(new Boots(gamePanel));
        gamePanel.objects[numMap][17].worldX = 43 * gamePanel.tileSize;
        gamePanel.objects[numMap][17].worldY = 9 * gamePanel.tileSize;

//        gamePanel.objects[numMap][18] = new Chest(gamePanel);
//        gamePanel.objects[numMap][18].setLoot(new PotionRed(gamePanel));
//        gamePanel.objects[numMap][18].worldX = 41 * gamePanel.tileSize;
//        gamePanel.objects[numMap][18].worldY = 39 * gamePanel.tileSize;

        gamePanel.objects[numMap][9] = new PotionRed(gamePanel);
        gamePanel.objects[numMap][9].worldX = 19 * gamePanel.tileSize;
        gamePanel.objects[numMap][9].worldY = 13 * gamePanel.tileSize;
//
//        gamePanel.objects[numMap][10] = new PotionRed(gamePanel);
//        gamePanel.objects[numMap][10].worldX = 32 * gamePanel.tileSize;
//        gamePanel.objects[numMap][10].worldY = 43 * gamePanel.tileSize;
//
//        gamePanel.objects[numMap][11] = new PotionRed(gamePanel);
//        gamePanel.objects[numMap][11].worldX = 30 * gamePanel.tileSize;
//        gamePanel.objects[numMap][11].worldY = 43 * gamePanel.tileSize;

//        gamePanel.objects[numMap][12] = new Lantern(gamePanel);
//        gamePanel.objects[numMap][12].worldX = 5 * gamePanel.tileSize;
//        gamePanel.objects[numMap][12].worldY = 39 * gamePanel.tileSize;

//        gamePanel.objects[numMap][13] = new Tent(gamePanel);
//        gamePanel.objects[numMap][13].worldX = 25 * gamePanel.tileSize;
//        gamePanel.objects[numMap][13].worldY = 45 * gamePanel.tileSize;

        gamePanel.objects[numMap][14] = new Door(gamePanel);
        gamePanel.objects[numMap][14].worldX = 13 * gamePanel.tileSize;
        gamePanel.objects[numMap][14].worldY = 39 * gamePanel.tileSize;


        // ... rest of the code for setting objects for map 0
    }

    private void setObjectForMap2() {
        int numMap = 2;
        gamePanel.objects[numMap][19] = new Chest(gamePanel);
        gamePanel.objects[numMap][19].setLoot(new Pickaxe(gamePanel));
        gamePanel.objects[numMap][19].worldX = 45 * gamePanel.tileSize;
        gamePanel.objects[numMap][19].worldY = 38 * gamePanel.tileSize;

        gamePanel.objects[numMap][20] = new Chest(gamePanel);
        gamePanel.objects[numMap][20].setLoot(new PotionRed(gamePanel));
        gamePanel.objects[numMap][20].worldX = gamePanel.tileSize;
        gamePanel.objects[numMap][20].worldY = 22 * gamePanel.tileSize;

        gamePanel.objects[numMap][21] = new Chest(gamePanel);
        gamePanel.objects[numMap][21].setLoot(new PotionRed(gamePanel));
        gamePanel.objects[numMap][21].worldX = 30 * gamePanel.tileSize;
        gamePanel.objects[numMap][21].worldY = 43 * gamePanel.tileSize;

        gamePanel.objects[numMap][22] = new DoorIron(gamePanel);
        gamePanel.objects[numMap][22].worldX = 5 * gamePanel.tileSize;
        gamePanel.objects[numMap][22].worldY = 3 * gamePanel.tileSize;
        // ... rest of the code for setting objects for map 2
    }

    private void setObjectForMap3() {
        int numMap = 3;

        //        gamePanel.objects[numMap][0] = new DoorIron(gamePanel);
//        gamePanel.objects[numMap][0].worldX = 25 * gamePanel.tileSize;
//        gamePanel.objects[numMap][0].worldY = 15 * gamePanel.tileSize;

        gamePanel.objects[numMap][1] = new BigDiamond(gamePanel);
        gamePanel.objects[numMap][1].worldX = 21 * gamePanel.tileSize;
        gamePanel.objects[numMap][1].worldY = 7 * gamePanel.tileSize;
        // ... rest of the code for setting objects for map 3
    }

    public void setNPC() {
        setNPCForMap0();
        setNPCForMap1();
        setNPCForMap2();
    }

    private void setNPCForMap0() {
        int numMap = 0;
        int i = 0;
        gamePanel.npc[numMap][i] = new Oldman(gamePanel);
        gamePanel.npc[numMap][i].worldX = gamePanel.tileSize * 23;
        gamePanel.npc[numMap][i].worldY = gamePanel.tileSize * 22;
    }

    private void setNPCForMap1() {
        int numMap = 1;
        int i = 0;
        gamePanel.npc[numMap][i] = new Merchant(gamePanel);
        gamePanel.npc[numMap][i].worldX = gamePanel.tileSize * 20;
        gamePanel.npc[numMap][i].worldY = gamePanel.tileSize * 47;
    }

    private void setNPCForMap2() {
        int numMap = 2;
        int i = 0;
        gamePanel.npc[numMap][i] = new BigRock(gamePanel);
        gamePanel.npc[numMap][i].worldX = gamePanel.tileSize * 47;
        gamePanel.npc[numMap][i].worldY = gamePanel.tileSize * 46;

        gamePanel.npc[numMap][i] = new BigRock(gamePanel);
        gamePanel.npc[numMap][i].worldX = gamePanel.tileSize * 47;
        gamePanel.npc[numMap][i].worldY = gamePanel.tileSize * 46;
        i++;

        gamePanel.npc[numMap][i] = new BigRock(gamePanel);
        gamePanel.npc[numMap][i].worldX = gamePanel.tileSize * 11;
        gamePanel.npc[numMap][i].worldY = gamePanel.tileSize * 31;

        // ... rest of the code for setting NPCs for map 2
    }

    public void setMonster() {
        setMonsterForMap0();
        setMonsterForMap2();
        setMonsterForMap3();
    }

    private void setMonsterForMap0() {
        int numMap = 0;
        int value = 1;
        for (int i = 0; i < 3; i++) {
            gamePanel.mon[numMap][i] = new Slime(gamePanel);
            gamePanel.mon[numMap][i].worldX = gamePanel.tileSize * (38 + value);
            gamePanel.mon[numMap][i].worldY = gamePanel.tileSize * 41;
            value++;
        }

        for (int i = 0; i < 3; i++) {
            gamePanel.mon[numMap][i + 5] = new RedSlime(gamePanel);
            gamePanel.mon[numMap][i + 5].worldX = gamePanel.tileSize * (6 + value);
            gamePanel.mon[numMap][i + 5].worldY = gamePanel.tileSize * 8;
            value++;

            gamePanel.mon[numMap][4] = new Ogr(gamePanel);
            gamePanel.mon[numMap][4].worldX = gamePanel.tileSize * 19;
            gamePanel.mon[numMap][4].worldY = gamePanel.tileSize * 27;
            // ... rest of the code for setting monsters for map 0
        }
    }

    private void setMonsterForMap2() {
        int numMap = 2;

        gamePanel.mon[numMap][0] = new Bat(gamePanel);
        gamePanel.mon[numMap][0].worldX = gamePanel.tileSize * 44;
        gamePanel.mon[numMap][0].worldY = gamePanel.tileSize * 41;

        gamePanel.mon[numMap][1] = new Bat(gamePanel);
        gamePanel.mon[numMap][1].worldX = gamePanel.tileSize * 38;
        gamePanel.mon[numMap][1].worldY = gamePanel.tileSize * 6;

        // ... rest of the code for setting monsters for map 2
    }

    private void setMonsterForMap3() {
        int numMap = 3;
        if (!Progress.bossDefeated) {
            gamePanel.mon[numMap][1] = new SkeletonZ(gamePanel);
            gamePanel.mon[numMap][1].worldX = gamePanel.tileSize * 23;
            gamePanel.mon[numMap][1].worldY = gamePanel.tileSize * 16;
        }// ... rest of the code for setting monsters for map 3
    }

    public void setInteractiveTile() {
        setInteractiveTileForMap0();
        setInteractiveTileForMap2();
    }

    private void setInteractiveTileForMap0() {
        int numMap = 0;
        int step = 0;

        for (int i = 0; i < 3; i++) {
            gamePanel.interactiveTile[numMap][i] = new DryTree(gamePanel, 30, 11 + i);

            step++;

        }
        System.out.println("step: " + step);
        for (int i = 0; i < 6; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 29, 13 + i);

            step++;

        }

        for (int i = 0; i < 5; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 32, 11 + i);
            step++;
        }
        for (int i = 0; i < 5; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 31, 15 + i);
            step++;
        }
        for (int i = 0; i < 3; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 33, 17 + i);
            step++;
        }
        for (int i = 0; i < 2; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 35, 17 + i);
            step++;
        }
        for (int i = 0; i < 4; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 34, 12 + i);
            step++;
        }
        for (int i = 0; i < 4; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 37, 15 + i);
            step++;
        }
        for (int i = 0; i < 5; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 38, 27 + i);
            step++;
        }
        for (int i = 0; i < 3; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 30, 29 + i);
            step++;
        }
        for (int i = 0; i < 6; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 11, 24 + i);
            step++;
        }
        for (int i = 0; i < 3; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 13, 19 + i);
            step++;
        }
        for (int i = 0; i < 3; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 11, 19 + i);
            step++;
        }
        for (int i = 0; i < 2; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 9, 20 + i);
            step++;
        }
        for (int i = 0; i < 4; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 26, 13 + i);
            step++;
        }
        step++;
        gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 31, 11);
        step++;
        gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 32, 19);
        step++;
        gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 34, 17);
        step++;
        gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 36, 18);
        step++;
        gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 35, 12);
        step++;
        gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 36, 29);
        step++;
        gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 12, 24);
        step++;
        gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 12, 29);
        step++;
        gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 12, 19);
        step++;
        gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 10, 21);
        step++;
        gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 19, 30);
        step++;
        gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 21, 32);
        step++;
        for (int i = 0; i < 2; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 35 + i, 15);
            step++;
        }
        for (int i = 0; i < 11; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 28 + i, 26);
            step++;
        }
        for (int i = 0; i < 7; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 30 + i, 28);
            step++;
        }
        for (int i = 0; i < 4; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 33 + i, 30);
            step++;
        }
        for (int i = 0; i < 9; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 30 + i, 32);
            step++;
        }
        for (int i = 0; i < 6; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 20 + i, 13);
            step++;
        }
        for (int i = 0; i < 3; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 19 + i, 31);
            step++;
        }
        for (int i = 0; i < 4; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 18 + i, 33);
            step++;
        }
        for (int i = 0; i < 2; i++) {
            gamePanel.interactiveTile[numMap][step] = new DryTree(gamePanel, 17 + i, 34);
            step++;
        }
    }

    private void setInteractiveTileForMap2() {
        int numMap = 2;
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                gamePanel.interactiveTile[numMap][k] = new DestructibleWall(gamePanel, 8 + j, 35 + i);
                k++;
            }
        }

        gamePanel.interactiveTile[numMap][13] = new MetalPlate(gamePanel, 2, 37);
        gamePanel.interactiveTile[numMap][14] = new MetalPlate(gamePanel, 38, 43);
        gamePanel.interactiveTile[numMap][15] = new MetalPlate(gamePanel, 48, 20);
        // ... rest of the code for setting interactive tiles for map 2
    }
}
