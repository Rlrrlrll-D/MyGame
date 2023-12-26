package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class Oldman extends Entity {

    public Oldman(GamePanel gamePanel) {
        super(gamePanel);

        direct = "down";
        speed = 1;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getImg();
        setDialog();
    }

    public void getImg() {

        /*stay1 = setup("stay1");
        stay2 = setup("stay2");
        stay3 = setup("stay3");
        stay_up1 = setup("stay_up1");
        stay_up2 = setup("stay_up2");
        stay_up3 = setup("stay_up3");
        stay_left1 = setup("stay_left1");
        stay_left2 = setup("stay_left2");
        stay_left3 = setup("stay_left3");
        stay_right1 = setup("stay_right1");
        stay_right2 = setup("stay_right2");
        stay_right3 = setup("stay_right3");*/
        up1 = setup("/res/npc/oldman_up1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/res/npc/oldman_up2", gamePanel.tileSize, gamePanel.tileSize);
        //up3 = setup("/res/npc/oldman_up2");

        down1 = setup("/res/npc/oldman_down1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/res/npc/oldman_down2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/res/npc/oldman_left1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/res/npc/oldman_left2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/res/npc/oldman_right1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/res/npc/oldman_right2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setDialog() {
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you've come to this island \nto find the treasure?";
        dialogues[2] = "I used to be a great wizard but now... \nI'm a bit too old for taking \nan adventure.";
        dialogues[3] = "Well, good luck on you.";

    }

    public void setAction() {
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
    public void speak() {
        super.speak();

    }
}
