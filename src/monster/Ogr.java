package monster;

import entity.Monster;
import main.GamePanel;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;

import java.util.Random;

public class Ogr extends Monster {
    GamePanel gamePanel;

    public Ogr(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Ogr";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 8;
        defence = 2;
        motionDelay1 = 24;
        motionDelay2 = 48;
        motionDelay3 = 96;
        exp = 10;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;

        getImg();
        getAttackImg();


    }

    private void getImg() {
        up1 = setup("/res/monster/ogr_up1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/res/monster/ogr_up2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/res/monster/ogr_down1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/res/monster/ogr_down2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/res/monster/ogr_left1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/res/monster/ogr_left2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/res/monster/ogr_right1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/res/monster/ogr_right2", gamePanel.tileSize, gamePanel.tileSize);
    }

    private void getAttackImg() {
        attackUp1 = setup("/res/monster/ogr_attack_up1", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackUp2 = setup("/res/monster/ogr_attack_up2", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackDown1 = setup("/res/monster/ogr_attack_down1", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackDown2 = setup("/res/monster/ogr_attack_down2", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackLeft1 = setup("/res/monster/ogr_attack_left1", gamePanel.tileSize * 2, gamePanel.tileSize);
        attackLeft2 = setup("/res/monster/ogr_attack_left2", gamePanel.tileSize * 2, gamePanel.tileSize);
        attackRight1 = setup("/res/monster/ogr_attack_right1", gamePanel.tileSize * 2, gamePanel.tileSize);
        attackRight2 = setup("/res/monster/ogr_attack_right2", gamePanel.tileSize * 2, gamePanel.tileSize);
    }

    public void setAction() {

        if (onPath) {
            checkStopNotChasing(gamePanel.player, 15, 100);
            searchPath(getGoalCol(gamePanel.player), getGoalRow(gamePanel.player));
        } else {
            checkStartNotChasing(gamePanel.player, 5, 100);
            getRandomDirection();
        }
        if (!isAttack) {
            checkAttackOrNot(30, gamePanel.tileSize * 4, gamePanel.tileSize);
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
