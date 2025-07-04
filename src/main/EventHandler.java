package main;

import data.Progress;
import entity.Entity;

import java.util.LinkedHashMap;
import java.util.Map;

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
            Map<Event, Runnable> eventActionMap = new LinkedHashMap<>();

            eventActionMap.put(new Event(0, 36, 12, "any"), () -> teleport(1, 14, 44, gamePanel.indoor));
            eventActionMap.put(new Event(1, 14, 44, "any"), () -> teleport(0, 36, 12, gamePanel.outside));
            eventActionMap.put(new Event(0, 11, 39, "any"), () -> teleport(2, 2, 47, gamePanel.dungeon));
            eventActionMap.put(new Event(2, 2, 47, "any"), () -> teleport(0, 11, 39, gamePanel.outside));
            eventActionMap.put(new Event(2, 47, 2, "any"), () -> teleport(3, 24, 45, gamePanel.dungeon));
            eventActionMap.put(new Event(3, 24, 45, "any"), () -> teleport(2, 47, 2, gamePanel.dungeon));
            eventActionMap.put(new Event(3, 24, 38, "up"), this::skeletonZ);
            eventActionMap.put(new Event(0, 33, 38, "any"), () -> healingPool(GamePanel.dialogBehavior));
            eventActionMap.put(new Event(1, 20, 45, "stay"), () -> speak(gamePanel.npc[1][0]));

            for (Map.Entry<Event, Runnable> entry : eventActionMap.entrySet()) {
                Event event = entry.getKey();
                if (hit(event.map, event.col, event.row, event.direction)) {
                    entry.getValue().run();
                    break;
                }
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

//    private void damagePit() {
//        gamePanel.playSFX(6);
//        eventMaster.startDialog(eventMaster, 0);
//        gamePanel.player.life--;
//        canTouchEvent = false;
//
//    }

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

    private void skeletonZ() {
        if (!gamePanel.bossBattleOn && !Progress.bossDefeated) {
            gamePanel.gameBehavior = GamePanel.cutSceneBehavior;
            gamePanel.cutSceneManager.scene = gamePanel.cutSceneManager.skeletonZ;

        }
    }

    private static class Event {
        int map;
        int col;
        int row;
        String direction;

        Event(int map, int col, int row, String direction) {
            this.map = map;
            this.col = col;
            this.row = row;
            this.direction = direction;
        }
    }
}