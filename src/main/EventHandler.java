package main;

import entity.Entity;

public class EventHandler {
    GamePanel gamePanel;
    EventRect[][][] eventRect;

    int previousEventX, previousEventY;
    boolean canTouchEvent;
    int tmpMap, tmpCol, tmpRow;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        eventRect = new EventRect[GamePanel.maxMap][GamePanel.maxWorldCol][GamePanel.maxWorldRow];

        for (int map = 0; map < GamePanel.maxMap; map++) {
            for (int col = 0; col < GamePanel.maxWorldCol; col++) {
                for (int row = 0; row < GamePanel.maxWorldRow; row++) {

                    eventRect[map][col][row] = new EventRect();
                    eventRect[map][col][row].x = 23;
                    eventRect[map][col][row].y = 23;
                    eventRect[map][col][row].height = 2;
                    eventRect[map][col][row].width = 2;
                    eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
                    eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

                }

            }
        }

    }

    public void checkEvent() {
        int xDistance = Math.abs(gamePanel.player.worldX - previousEventX);
        int yDistance = Math.abs(gamePanel.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gamePanel.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {

            if (hit(0, 7, 44, "down")) {
                damagePit();
            } else if (hit(0, 9, 44, "down")) {
                damagePit();
            } else if (hit(0, 14, 44, "any")) {
                teleport(1, 14, 44);
            } else if (hit(1, 14, 44, "any")) {
                teleport(0, 14, 44);
            } else if (hit(0, 12, 43, "any")) {
                healingPool(GamePanel.dialogBehavior);
            } else if (hit(1, 20, 45, "stay")) {
                speak(gamePanel.npc[1][0]);
            }
        }
    }

    private void speak(Entity entity) {
        if (gamePanel.keyHandler.enterPressed) {
            gamePanel.gameBehavior = GamePanel.dialogBehavior;
            gamePanel.player.notAttacked = true;
            entity.speak();
        }
    }

    public boolean hit(int map, int eventCol, int eventRow, String reqDirection) {

        boolean hit = false;
        if (map == gamePanel.currentMap) {
            gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
            gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

            eventRect[map][eventCol][eventCol].x = eventCol * gamePanel.tileSize + eventRect[map][eventCol][eventCol].x;
            eventRect[map][eventCol][eventCol].y = eventRow * gamePanel.tileSize + eventRect[map][eventCol][eventCol].y;

            if (gamePanel.player.solidArea.intersects(eventRect[map][eventCol][eventCol]) && !eventRect[map][eventCol][eventRow].eventDone) {
                if (gamePanel.player.direct.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                    previousEventX = gamePanel.player.worldX;
                    previousEventY = gamePanel.player.worldY;
                }
            }
            gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
            gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
            eventRect[map][eventCol][eventCol].x = eventRect[map][eventCol][eventCol].eventRectDefaultX;
            eventRect[map][eventCol][eventCol].y = eventRect[map][eventCol][eventCol].eventRectDefaultY;
        }
        return hit;
    }

    private void damagePit() {
        gamePanel.gameBehavior = GamePanel.dialogBehavior;
        gamePanel.playSFX(6);
        gamePanel.ui.dialogue = "You fall into a pit! :(";
        canTouchEvent = false;
        gamePanel.player.life--;
    }

    public void healingPool(int gameBehavior) {

        if (gamePanel.keyHandler.enterPressed && gamePanel.player.life != gamePanel.player.maxLife) {
            gamePanel.player.isAttack = false;
            gamePanel.playSFX(10);
            gamePanel.gameBehavior = gameBehavior;
            gamePanel.player.notAttacked = true;
            gamePanel.ui.dialogue = "You drink the water. \nYour life and mana have been recovered! :)";
            gamePanel.player.life = gamePanel.player.maxLife;
            gamePanel.player.mana = gamePanel.player.maxMana;
            gamePanel.assetSetter.setMonster();
        }
    }

    private void teleport(int map, int col, int row) {

        gamePanel.gameBehavior = GamePanel.transitionBehavior;

        tmpMap = map;
        tmpCol = col;
        tmpRow = row;

        canTouchEvent = false;


    }
}