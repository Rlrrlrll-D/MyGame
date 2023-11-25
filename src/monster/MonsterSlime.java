package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MonsterSlime extends Entity {

    GamePanel gamePanel;
    public MonsterSlime(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        type = 2;
        direct = "up";
        name = "Slime";
        speed = 1;
        maxLife = 20;
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
        up1 = setup("/res/monster/slime-down1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/res/monster/slime-down2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/res/monster/slime-down1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/res/monster/slime-down2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/res/monster/slime-down1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/res/monster/slime-down2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/res/monster/slime-down1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/res/monster/slime-down2", gamePanel.tileSize, gamePanel.tileSize);

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
