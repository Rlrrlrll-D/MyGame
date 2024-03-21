package monster;

import data.Progress;
import entity.Monster;
import main.GamePanel;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;

import java.util.Objects;
import java.util.Random;

public class SkeletonZ extends Monster {

    public static final String monName = "SkeletonZ";
    GamePanel gamePanel;

    public SkeletonZ(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = monName;
        boss = true;
        sleeping = true;
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
        setDialog();

    }

    public void setDialog() {

        dialogues[0][0] = "I am the king of the dead!";
        dialogues[0][1] = "You are not welcome here.\nNo one can steal my treasure!";
        dialogues[0][2] = "I will kill you and take your soul!";
        dialogues[0][3] = "WELCOME TO YOUR DOOM!";
    }

    private void getImg() {
        var i = 5;
        if (!inRage) {
            up1 = setup("/res/monster/skeleton_up_1", gamePanel.tileSize * i, gamePanel.tileSize * i);
            up2 = setup("/res/monster/skeleton_up_2", gamePanel.tileSize * i, gamePanel.tileSize * i);
            down1 = setup("/res/monster/skeleton_down_1", gamePanel.tileSize * i, gamePanel.tileSize * i);
            down2 = setup("/res/monster/skeleton_down_2", gamePanel.tileSize * i, gamePanel.tileSize * i);
            left1 = setup("/res/monster/skeleton_left_1", gamePanel.tileSize * i, gamePanel.tileSize * i);
            left2 = setup("/res/monster/skeleton_left_2", gamePanel.tileSize * i, gamePanel.tileSize * i);
            right1 = setup("/res/monster/skeleton_right_1", gamePanel.tileSize * i, gamePanel.tileSize * i);
            right2 = setup("/res/monster/skeleton_right_2", gamePanel.tileSize * i, gamePanel.tileSize * i);
        }
        if (inRage) {
            up1 = setup("/res/monster/skeleton_phase2_up_1", gamePanel.tileSize * i, gamePanel.tileSize * i);
            up2 = setup("/res/monster/skeleton_phase2_up_2", gamePanel.tileSize * i, gamePanel.tileSize * i);
            down1 = setup("/res/monster/skeleton_phase2_down_1", gamePanel.tileSize * i, gamePanel.tileSize * i);
            down2 = setup("/res/monster/skeleton_phase2_down_2", gamePanel.tileSize * i, gamePanel.tileSize * i);
            left1 = setup("/res/monster/skeleton_phase2_left_1", gamePanel.tileSize * i, gamePanel.tileSize * i);
            left2 = setup("/res/monster/skeleton_phase2_left_2", gamePanel.tileSize * i, gamePanel.tileSize * i);
            right1 = setup("/res/monster/skeleton_phase2_right_1", gamePanel.tileSize * i, gamePanel.tileSize * i);
            right2 = setup("/res/monster/skeleton_phase2_right_2", gamePanel.tileSize * i, gamePanel.tileSize * i);
        }

    }

    private void getAttackImg() {
        var i = 5;
        if (!inRage) {
            attackUp1 = setup("/res/monster/skeleton_attack_up_1", gamePanel.tileSize * i, gamePanel.tileSize * 2 * i);
            attackUp2 = setup("/res/monster/skeleton_attack_up_2", gamePanel.tileSize * i, gamePanel.tileSize * 2 * i);
            attackDown1 = setup("/res/monster/skeleton_attack_down_1", gamePanel.tileSize * i, gamePanel.tileSize * 2 * i);
            attackDown2 = setup("/res/monster/skeleton_attack_down_2", gamePanel.tileSize * i, gamePanel.tileSize * 2 * i);
            attackLeft1 = setup("/res/monster/skeleton_attack_left_1", gamePanel.tileSize * 2 * i, gamePanel.tileSize * i);
            attackLeft2 = setup("/res/monster/skeleton_attack_left_2", gamePanel.tileSize * 2 * i, gamePanel.tileSize * i);
            attackRight1 = setup("/res/monster/skeleton_attack_right_1", gamePanel.tileSize * 2 * i, gamePanel.tileSize * i);
            attackRight2 = setup("/res/monster/skeleton_attack_right_2", gamePanel.tileSize * 2 * i, gamePanel.tileSize * i);
        }
        if (inRage) {
            attackUp1 = setup("/res/monster/skeleton_phase2_attack_up_1", gamePanel.tileSize * i, gamePanel.tileSize * 2 * i);
            attackUp2 = setup("/res/monster/skeleton_phase2_attack_up_2", gamePanel.tileSize * i, gamePanel.tileSize * 2 * i);
            attackDown1 = setup("/res/monster/skeleton_phase2_attack_down_1", gamePanel.tileSize * i, gamePanel.tileSize * 2 * i);
            attackDown2 = setup("/res/monster/skeleton_phase2_attack_down_2", gamePanel.tileSize * i, gamePanel.tileSize * 2 * i);
            attackLeft1 = setup("/res/monster/skeleton_phase2_attack_left_1", gamePanel.tileSize * 2 * i, gamePanel.tileSize * i);
            attackLeft2 = setup("/res/monster/skeleton_phase2_attack_left_2", gamePanel.tileSize * 2 * i, gamePanel.tileSize * i);
            attackRight1 = setup("/res/monster/skeleton_phase2_attack_right_1", gamePanel.tileSize * 2 * i, gamePanel.tileSize * i);
            attackRight2 = setup("/res/monster/skeleton_phase2_attack_right_2", gamePanel.tileSize * 2 * i, gamePanel.tileSize * i);
        }

    }

    public void setAction() {
        if (!inRage && life < maxLife / 2) {
            inRage = true;
            getImg();
            getAttackImg();
            defaultSpeed++;
            speed = defaultSpeed;
            attack *= 2;
        }
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
        gamePanel.bossBattleOn = false;
        Progress.bossDefeated= true;
        gamePanel.stopMusic();
        gamePanel.playSFX(21);
        for (int i = 0; i < gamePanel.objects[1].length; i++) {
            if (gamePanel.objects[gamePanel.currentMap][i] != null && Objects.equals(gamePanel.objects[gamePanel.currentMap][i].name, "DoorIron")) {
                gamePanel.playSFX(24);
                gamePanel.objects[gamePanel.currentMap][i] = null;
            }
        }
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