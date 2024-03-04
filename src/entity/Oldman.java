package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Oldman extends Entity {

    public Oldman(GamePanel gamePanel) {
        super(gamePanel);
        name = "Oldman";
        direct = "down";
        speed = 1;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

        dialogSet = -1;

        getImg();
        setDialogue();
    }

    private void getImg() {

        up1 = setup("/res/npc/oldman_up1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/res/npc/oldman_up2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/res/npc/oldman_down1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/res/npc/oldman_down2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/res/npc/oldman_left1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/res/npc/oldman_left2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/res/npc/oldman_right1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/res/npc/oldman_right2", gamePanel.tileSize, gamePanel.tileSize);
    }

    private void setDialogue() {
        dialogues[0][0] = "Hello, lad.";
        dialogues[0][1] = "So you've come to this island \nto find the treasure?";
        dialogues[0][2] = "I used to be a great wizard but now... \nI'm a bit too old for taking \nan adventure.";
        dialogues[0][3] = "Well, good luck on you.";

        dialogues[1][0] = "If you become tired, reset at the water";
        dialogues[1][1] = "However, the monsters reappear if you rest. \nI don't know why but that's how it works.";
        dialogues[1][2] = "In any case, don't push yourself too hard.";

        dialogues[2][0] = "I wonder how to open that door...";
    }

    public void setAction() {
        if (onPath) {
            int goalCol = (gamePanel.player.worldX + gamePanel.player.solidArea.x) / gamePanel.tileSize;
            int goalRow = (gamePanel.player.worldY + gamePanel.player.solidArea.y) / gamePanel.tileSize;
            searchPath(goalCol, goalRow);

        } else {

            actionCounter++;
            if (actionCounter == 120) {

                Random random = new Random();
                int i = random.nextInt(100) + 1;
                if (i <= 25) {
                    direct = "up";
                }
                if (i > 25 && i <= 50) {
                    direct = "down";
                }
                if (i > 50 && i <= 75) {
                    direct = "left";
                }
                if (i > 75) {
                    direct = "right";
                }
                actionCounter = 0;
            }
        }
    }

    public void speak() {
        facePlayer();
        startDialog(this, dialogSet);
        dialogSet++;
        if (dialogues[dialogSet][0] == null) {
            dialogSet--;
        }
        if (gamePanel.player.life < gamePanel.player.maxLife / 3) {
            dialogSet = 1;
        }
        onPath = true;
    }
}
