package main;

import java.awt.*;

public class EventHandler {


    GamePanel gamePanel;
    Rectangle eventRect;
    int eventRectDefaultX;
    int eventRectDefaultY;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.height = 2;
        eventRect.width = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        if (hit(7, 44, "down")) {

            damagePit(gamePanel.dialogBehavior);
        }
        if (hit(12, 43, "any")) {
            healingPool(gamePanel.dialogBehavior);
        }


    }

    private void damagePit(int gameBehavior) {

        gamePanel.gameBehavior = gameBehavior;
        gamePanel.ui.dialogue = "You fall into a pit! :(";
        gamePanel.player.life--;
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {

        boolean hit = false;
        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        eventRect.x = eventCol * gamePanel.tileSize + eventRect.x;
        eventRect.y = eventRow * gamePanel.tileSize + eventRect.y;

        if (gamePanel.player.solidArea.intersects(eventRect)) {
            if (gamePanel.player.direct.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;


        return hit;

    }

    public void healingPool(int gameBehavior) {

        if (gamePanel.keyHandler.enterPressed) {
            gamePanel.gameBehavior = gameBehavior;
            gamePanel.ui.dialogue = "You drink the water. \nYour life has been recovered! :)";

            gamePanel.player.life = gamePanel.player.maxLife;
        }
    }


}