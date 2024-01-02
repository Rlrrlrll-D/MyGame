package monster;

import entity.Entity;
import main.GamePanel;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;
import objects.Rock;

import java.util.Random;

public class Slime extends Entity {

    GamePanel gamePanel;

    public Slime(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
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

    public void update() {
        super.update();
        int xDistance = Math.abs(worldX - gamePanel.player.worldX);
        int yDistance = Math.abs(worldY - gamePanel.player.worldY);
        int tileDistance = (xDistance + yDistance) / gamePanel.tileSize;
        if (!onPath && tileDistance < 5) {
            int i = new Random().nextInt(100) + 1;
            if (i > 50) {
                onPath = true;
            }
//            if (onPath&& tileDistance>20){
//                onPath = false;
//            }
        }
    }

    public void setAction() {
        if (onPath) {

            int goalCol = (gamePanel.player.worldX + gamePanel.player.solidArea.x) / gamePanel.tileSize;
            int goalRow = (gamePanel.player.worldY + gamePanel.player.solidArea.y) / gamePanel.tileSize;
            searchPath(goalCol, goalRow);

            int i = new Random().nextInt(200) + 1;
            if (i > 197 && !projectile.isAlive && shotAvailableCounter == shotDelay) {
                projectile.set(worldX + gamePanel.tileSize / 4, worldY + gamePanel.tileSize / 4, direct, true, this);
                // gamePanel.projectileArrayList.add(projectile);
                for (int j = 0; j < gamePanel.projectile[1].length; j++) {
                    if (gamePanel.projectile[gamePanel.currentMap][j] == null) {
                        gamePanel.projectile[gamePanel.currentMap][j] = projectile;
                        break;
                    }
                }
                shotAvailableCounter = 0;
            }
            shotCount(50);

        } else {

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
        escape = true;
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
