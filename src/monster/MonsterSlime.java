package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MonsterSlime extends Entity {
    public MonsterSlime(GamePanel gamePanel) {
        super(gamePanel);

        type = 2;
        direct = "up";
        name = "Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImg();


    }

    public void getImg() {
        up1 = setup("/res/monster/slime-down1");
        up2 = setup("/res/monster/slime-down2");
        down1 = setup("/res/monster/slime-down1");
        down2 = setup("/res/monster/slime-down2");
        left1 = setup("/res/monster/slime-down1");
        left2 = setup("/res/monster/slime-down2");
        right1 = setup("/res/monster/slime-down1");
        right2 = setup("/res/monster/slime-down2");

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
}
