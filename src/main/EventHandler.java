package main;

import entity.Entity;

public class EventHandler {
    GamePanel gamePanel;
    EventRect[][][] eventRect;
    Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTouchEvent;
    int tmpMap, tmpCol, tmpRow;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        eventMaster = new Entity(gamePanel);
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
        setDialogue();
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
                teleport(1, 14, 44, gamePanel.indoor);
            } else if (hit(1, 14, 44, "any")) {
                teleport(0, 14, 44, gamePanel.outside);

            } else if (hit(0, 4, 43, "any")) {
                teleport(2, 2, 47, gamePanel.dungeon);
            } else if (hit(2, 2, 47, "any")) {
                teleport(0, 4, 43, gamePanel.outside);

            } else if (hit(2, 3, 2, "any")) {
                teleport(3, 24, 45, gamePanel.dungeon);
            } else if (hit(3, 24, 45, "any")) {
                teleport(2, 3, 2, gamePanel.dungeon);

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

    private void setDialogue() {
        eventMaster.dialogues[0][0] = "You fall into a pit! :(";
        eventMaster.dialogues[1][0] = """
                You drink the water.\s
                Your life and mana have been recovered.\s
                (The progress has been saved)""";
        eventMaster.dialogues[1][1] = "Damn, this is good water!";
    }

    private void damagePit() {
        gamePanel.playSFX(6);
        eventMaster.startDialog(eventMaster, 0);
        gamePanel.player.life--;
        canTouchEvent = false;

    }

    public void healingPool(int gameBehavior) {

        if (gamePanel.keyHandler.enterPressed && gamePanel.player.life != gamePanel.player.maxLife) {
            gamePanel.gameBehavior = gameBehavior;
            gamePanel.player.notAttacked = true;
            gamePanel.playSFX(10);

            eventMaster.startDialog(eventMaster, 1);
            gamePanel.player.life = gamePanel.player.maxLife;
            gamePanel.player.mana = gamePanel.player.maxMana;
            gamePanel.assetSetter.setMonster();
            gamePanel.saveLoad.save();
        }
    }

    private void teleport(int map, int col, int row, int area) {

        gamePanel.gameBehavior = GamePanel.transitionBehavior;
        gamePanel.nextArea = area;
        tmpMap = map;
        tmpCol = col;
        tmpRow = row;

        canTouchEvent = false;


    }
}