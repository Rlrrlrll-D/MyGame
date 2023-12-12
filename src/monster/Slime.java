package monster;

import entity.Entity;
import main.GamePanel;
import objects.Rock;

import java.util.Random;

public class Slime extends Entity {

    GamePanel gamePanel;

    public Slime(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        //type = 2;
        //direct = "up";
        name = "Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defence = 0;
        exp = 2;
        projectile = new Rock(gamePanel);

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
        int i = new Random().nextInt(100) + 1;
        if (i > 99 && !projectile.isAlive && shotAvailableCounter == shotDelay) {
            projectile.set(worldX, worldY, direct, true, this);
            gamePanel.projectileArrayList.add(projectile);
            shotAvailableCounter = 0;
        }
        shotCount(50);
    }

    public void damageReaction() {
        actionCounter = 0;
        switch (gamePanel.player.direct) {
            case "up", "stay_up":
                direct = "up";
                break;
            case "down", "stay":
                direct = "down";
                break;
            case "left", "stay_left":
                direct = "left";
                break;
            case "right", "stay_right":
                direct = "right";
                break;
        }
        escape = true;


    }
}
