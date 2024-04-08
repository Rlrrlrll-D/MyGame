package monster;

import entity.Monster;
import main.GamePanel;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;

import java.util.Random;

public class Ogr extends Monster {
    GamePanel gamePanel;
    public final int SIZE;

    public Ogr(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        SIZE = gamePanel.tileSize;
        name = "Ogr";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 8;
        defence = 2;
        knockPower = 5;
        motionDelay1 = 18;
        motionDelay2 = 36;
        motionDelay3 = 72;
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
        String prefix = "/res/monster/ogr_";
        up1 = setup(prefix + "up1", SIZE, SIZE);
        up2 = setup(prefix + "up2", SIZE, SIZE);
        down1 = setup(prefix + "down1", SIZE, SIZE);
        down2 = setup(prefix + "down2", SIZE, SIZE);
        left1 = setup(prefix + "left1", SIZE, SIZE);
        left2 = setup(prefix + "left2", SIZE, SIZE);
        right1 = setup(prefix + "right1", SIZE, SIZE);
        right2 = setup(prefix + "right2", SIZE, SIZE);
    }

    private void getAttackImg() {
        String prefix = "/res/monster/ogr_attack_";
        attackUp1 = setup(prefix + "up1", SIZE, SIZE * 2);
        attackUp2 = setup(prefix + "up2", SIZE, SIZE * 2);
        attackDown1 = setup(prefix + "down1", SIZE, SIZE * 2);
        attackDown2 = setup(prefix + "down2", SIZE, SIZE * 2);
        attackLeft1 = setup(prefix + "left1", SIZE * 2, SIZE);
        attackLeft2 = setup(prefix + "left2", SIZE * 2, SIZE);
        attackRight1 = setup(prefix + "right1", SIZE * 2, SIZE);
        attackRight2 = setup(prefix + "right2", SIZE * 2, SIZE);
    }

    public void setAction() {

        if (onPath) {
            checkStopNotChasing(gamePanel.player, 15, 100);
            searchPath(getGoalCol(gamePanel.player), getGoalRow(gamePanel.player));
        } else {
            checkStartNotChasing(gamePanel.player, 5, 100);
            getRandomDirection(150);
        }
        if (!isAttack) {
            checkAttackOrNot(30, gamePanel.tileSize * 4, gamePanel.tileSize);
        }
    }

    public void damageReaction() {
        actionCounter = 0;
        direct = gamePanel.player.direct.replace("stay_", "");
        onPath = true;
    }

    public void checkDrop() {
        int i = new Random().nextInt(100) + 1;
        if (i < 50) {
            dropItem(new CoinBronze(gamePanel));
        } else if (i < 75) {
            dropItem(new Heart(gamePanel));
        } else {
            dropItem(new ManaCrystal(gamePanel));
        }
    }
}
