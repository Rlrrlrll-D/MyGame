package monster;

import entity.Monster;
import main.GamePanel;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;

import java.util.Random;

public class SkeletonZ extends Monster {

    public static final String monName = "SkeletonZ";
    GamePanel gamePanel;

    public SkeletonZ(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = monName;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 50;
        life = maxLife;
        attack = 10;
        defence = 2;
        knockPower = 5;
        motionDelay1 = 12;
        motionDelay2 = 24;
        motionDelay3 = 48;
        exp = 50;

        int size = gamePanel.tileSize * 5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48 * 2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;

        getImg();
        getAttackImg();


    }

    private void getImg() {
        var i = 5;

        up1 = setup("/res/monster/skeleton_up_1", gamePanel.tileSize * i, gamePanel.tileSize * i);
        up2 = setup("/res/monster/skeleton_up_2", gamePanel.tileSize * i, gamePanel.tileSize * i);
        down1 = setup("/res/monster/skeleton_down_1", gamePanel.tileSize * i, gamePanel.tileSize * i);
        down2 = setup("/res/monster/skeleton_down_2", gamePanel.tileSize * i, gamePanel.tileSize * i);
        left1 = setup("/res/monster/skeleton_left_1", gamePanel.tileSize * i, gamePanel.tileSize * i);
        left2 = setup("/res/monster/skeleton_left_2", gamePanel.tileSize * i, gamePanel.tileSize * i);
        right1 = setup("/res/monster/skeleton_right_1", gamePanel.tileSize * i, gamePanel.tileSize * i);
        right2 = setup("/res/monster/skeleton_right_2", gamePanel.tileSize * i, gamePanel.tileSize * i);
    }

    private void getAttackImg() {
        var i = 5;
        attackUp1 = setup("/res/monster/skeleton_attack_up_1", gamePanel.tileSize * i, gamePanel.tileSize * 2 * i);
        attackUp2 = setup("/res/monster/skeleton_attack_up_2", gamePanel.tileSize * i, gamePanel.tileSize * 2 * i);
        attackDown1 = setup("/res/monster/skeleton_attack_down_1", gamePanel.tileSize * i, gamePanel.tileSize * 2 * i);
        attackDown2 = setup("/res/monster/skeleton_attack_down_2", gamePanel.tileSize * i, gamePanel.tileSize * 2 * i);
        attackLeft1 = setup("/res/monster/skeleton_attack_left_1", gamePanel.tileSize * 2 * i, gamePanel.tileSize * i);
        attackLeft2 = setup("/res/monster/skeleton_attack_left_2", gamePanel.tileSize * 2 * i, gamePanel.tileSize * i);
        attackRight1 = setup("/res/monster/skeleton_attack_right_1", gamePanel.tileSize * 2 * i, gamePanel.tileSize * i);
        attackRight2 = setup("/res/monster/skeleton_attack_right_2", gamePanel.tileSize * 2 * i, gamePanel.tileSize * i);
    }

    public void setAction() {

        if (getTileDistance(gamePanel.player) < 10) {
            moveTowardPlayer(60);
        } else {
            getRandomDirection(150);
        }
        if (!isAttack) {
            checkAttackOrNot(60, gamePanel.tileSize * 7, gamePanel.tileSize * 5);
        }
    }

    public void damageReaction() {
        actionCounter = 0;
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
