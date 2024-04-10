package monster;

import entity.Monster;
import main.GamePanel;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;
import objects.Rock;

import java.util.Random;

public class RedSlime extends Monster {

    GamePanel gamePanel;

    public RedSlime(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "RedSlime";
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 8;
        life = maxLife;
        attack = 7;
        defence = 0;
        exp = 5;
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
        up1 = setup("/res/monster/red_slime_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/res/monster/red_slime_2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/res/monster/red_slime_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/res/monster/red_slime_2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/res/monster/red_slime_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/res/monster/red_slime_2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/res/monster/red_slime_1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/res/monster/red_slime_2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setAction() {

        if (onPath) {
            checkStopNotChasing(gamePanel.player, 15, 100);

            searchPath(getGoalCol(gamePanel.player), getGoalRow(gamePanel.player));
            shotCount();
            checkShootOrNot(100, 30);

        } else {
            checkStartNotChasing(gamePanel.player, 7, 100);
            getRandomDirection(120);
        }
    }

    public void damageReaction() {
        actionCounter = 0;
        switch (gamePanel.player.direct) {
            case "up", "stay_up" -> direct = "up";
            case "down", "stay" -> direct = "down";
            case "left", "stay_left" -> direct = "left";
            case "right", "stay_right" -> direct = "right";
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
