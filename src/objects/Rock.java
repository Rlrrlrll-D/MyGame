package objects;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.image.BufferedImage;

public class Rock extends Projectile {
    GamePanel gamePanel;

    public Rock(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        name = "Rock";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        isAlive = false;
        getImg();

    }

    public void getImg() {
        stay1 = setup("/res/projectile/rock_down1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay2 = setup("/res/projectile/rock_down2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay3 = setup("/res/projectile/rock_down3", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_up1 = setup("/res/projectile/rock_down1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_up2 = setup("/res/projectile/rock_down2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_up3 = setup("/res/projectile/rock_down3", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_left1 = setup("/res/projectile/rock_down1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_left2 = setup("/res/projectile/rock_down2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_left3 = setup("/res/projectile/rock_down3", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_right1 = setup("/res/projectile/rock_down1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_right2 = setup("/res/projectile/rock_down2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_right3 = setup("/res/projectile/rock_down3", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        down1 = setup("/res/projectile/rock_down1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        down2 = setup("/res/projectile/rock_down2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        up1 = setup("/res/projectile/rock_down1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        up2 = setup("/res/projectile/rock_down2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        left1 = setup("/res/projectile/rock_down1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        left2 = setup("/res/projectile/rock_down2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        right1 = setup("/res/projectile/rock_down1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        right2 = setup("/res/projectile/rock_down2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
    }

    public boolean haveRes(Entity user) {
        return user.ammo >= useCost;
    }

    public void subtractRes(Entity user) {
        user.ammo -= useCost;
    }


    public int getParticleSize() {
        return 10;
    }

    public int getParticleSpeed() {
        return 1;
    }

    public int getParticleMaxLife() {
        return 20;
    }

    public BufferedImage getParticleImg() {
        return this.down1;
    }
}
