package monster;

import entity.Monster;
import main.GamePanel;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;
import objects.Rock;

import java.util.Random;

public class Slime extends Monster {

    GamePanel gamePanel;

    public Slime(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
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

    private void getImg() {
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

        if (onPath) {
            //checkStopNotChasing(gamePanel.player, 15, 100);

            //searchPath(getGoalCol(gamePanel.player), getGoalRow(gamePanel.player));

        } else {
            //checkStartNotChasing(gamePanel.player, 5, 100);
            getRandomDirection(140);
        }
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
        onPath = true;
    }

    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;
        if (i < 50) {
            dropItem(new CoinBronze(gamePanel));
        }
        if (i >= 50 && i < 75) {
            dropItem(new Heart(gamePanel));
        }
        if (i >= 75 && i < 100) {
            dropItem(new ManaCrystal(gamePanel));
        }
    }
}
