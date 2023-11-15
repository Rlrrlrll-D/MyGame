package main;

public class EventHandler {


    GamePanel gamePanel;
    EventRect[][] eventRect;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        eventRect = new EventRect[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        for (int col = 0; col < gamePanel.maxWorldCol; col++) {
            for (int row = 0; row < gamePanel.maxWorldRow; row++) {

                eventRect[col][row] = new EventRect();
                eventRect[col][row].x = 23;
                eventRect[col][row].y = 23;
                eventRect[col][row].height = 2;
                eventRect[col][row].width = 2;
                eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
                eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            }

        }

    }

    public void checkEvent() {
        int xDistatce = Math.abs(gamePanel.player.worldX - previousEventX);
        int yDistatce = Math.abs(gamePanel.player.worldY - previousEventY);
        int distance = Math.max(xDistatce, yDistatce);
        if (distance > gamePanel.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {

            if (hit(7, 44, "down")) {
                damagePit(gamePanel.dialogBehavior);
            }
            if (hit(9, 44, "down")) {
                damagePit(gamePanel.dialogBehavior);
            }
            if (hit(14, 44, "any")) {

                teleport(43, 43,gamePanel.dialogBehavior);
            }
            if (hit(12, 43, "any")) {
                healingPool(gamePanel.dialogBehavior);
            }
        }
    }

    private void damagePit(int gameBehavior) {


        gamePanel.gameBehavior = gameBehavior;
        gamePanel.playSFX(6);
        gamePanel.ui.dialogue = "You fall into a pit! :(";
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
        gamePanel.player.life--;
    }

    private void teleport(int col, int row, int gameBehavior) {

        gamePanel.gameBehavior = gameBehavior;
        gamePanel.ui.dialogue = "Yahoo!.. Teleport!.. ;)";
        gamePanel.player.worldX = col * gamePanel.tileSize;
        gamePanel.player.worldY = row * gamePanel.tileSize;


    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {

        boolean hit = false;
        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        eventRect[eventCol][eventCol].x = eventCol * gamePanel.tileSize + eventRect[eventCol][eventCol].x;
        eventRect[eventCol][eventCol].y = eventRow * gamePanel.tileSize + eventRect[eventCol][eventCol].y;

        if (gamePanel.player.solidArea.intersects(eventRect[eventCol][eventCol]) && !eventRect[eventCol][eventRow].eventDone) {
            if (gamePanel.player.direct.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
                previousEventX = gamePanel.player.worldX;
                previousEventY = gamePanel.player.worldY;
            }
        }
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRect[eventCol][eventCol].x = eventRect[eventCol][eventCol].eventRectDefaultX;
        eventRect[eventCol][eventCol].y = eventRect[eventCol][eventCol].eventRectDefaultY;


        return hit;

    }

    public void healingPool(int gameBehavior) {

        if (gamePanel.keyHandler.enterPressed && gamePanel.player.life != gamePanel.player.maxLife) {
            gamePanel.gameBehavior = gameBehavior;
            gamePanel.ui.dialogue = "You drink the water. \nYour life has been recovered! :)";

            gamePanel.player.life = gamePanel.player.maxLife;
        }
    }
}