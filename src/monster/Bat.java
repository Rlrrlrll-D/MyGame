package monster;

import entity.Entity;
import main.GamePanel;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;

import java.util.Random;

public class Bat extends Entity {
    GamePanel gamePanel;

    public Bat(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Bat";
        defaultSpeed = 4;
        speed = defaultSpeed;
        maxLife = 7;
        life = maxLife;
        attack = 7;
        defence = 0;
        exp = 7;

        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImg();


    }

    private void getImg() {
        up1 = setup("/res/monster/bat_down_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/res/monster/bat_down_2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/res/monster/bat_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/res/monster/bat_down_2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/res/monster/bat_down_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/res/monster/bat_down_2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/res/monster/bat_down_1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/res/monster/bat_down_2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setAction() {

        if (onPath) {
//            checkStopNotChasing(gamePanel.player, 15, 100);
//
//            searchPath(getGoalCol(gamePanel.player), getGoalRow(gamePanel.player));

        } else {
//            checkStartNotChasing(gamePanel.player, 5, 100);
            getRandomDirection(15);
        }
    }
//    public void update() {
//        if (escapeKnock) {
//            checkCollision();
//            if (collisionOn) {
//                knockCounter = 0;
//                escapeKnock = false;
//                speed = defaultSpeed;
//            } else {
//                switch (knockBackDirect) {
//                    case "up", "stay_up":
//                        worldY -= speed;
//                        break;
//                    case "down", "stay":
//                        worldY += speed;
//                        break;
//                    case "left", "stay_left":
//                        worldX -= speed;
//                        break;
//                    case "right", "stay_right":
//                        worldX += speed;
//                        break;
//                    default:
//                        throw new IllegalStateException("Unexpected value: " + direct);
//                }
//            }
//            knockTime(10);
//
//        } else if (isAttack) {
//            attack();
//        } else {
//            setAction();
//            checkCollision();
//            if (!collisionOn) {
//
//                switch (direct) {
//
//                    case "up":
//                        worldY -= speed;
//                        break;
//                    case "down":
//                        worldY += speed;
//                        break;
//                    case "left":
//                        worldX -= speed;
//                        break;
//                    case "right":
//                        worldX += speed;
//                        break;
//                    default:
//                        throw new IllegalStateException("Unexpected value: " + direct);
//                }
//            }
//            spriteImageChange(2);
//        }
//        invincible(40);
//        shotCount();
//        offBalanceTime();
//    }

    public void damageReaction() {
        actionCounter = 0;
//        switch (gamePanel.player.direct) {
//            case "up", "stay_up":
//                direct = "up";
//                break;
//            case "down", "stay":
//                direct = "down";
//                break;
//            case "left", "stay_left":
//                direct = "left";
//                break;
//            case "right", "stay_right":
//                direct = "right";
//                break;
//        }
//        onPath = true;
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
