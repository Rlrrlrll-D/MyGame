package objects;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class Fireball extends Projectile {

    GamePanel gamePanel;

    public Fireball(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        name = "Fireball";
        speed = 6;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        isAlive = false;
        getImg();

    }

    public void getImg() {
        stay1 = setup("/res/projectile/fireball_down1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay2 = setup("/res/projectile/fireball_down2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay3 = setup("/res/projectile/fireball_down1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_up1 = setup("/res/projectile/fireball_up1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_up2 = setup("/res/projectile/fireball_up2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_up3 = setup("/res/projectile/fireball_up1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_left1 = setup("/res/projectile/fireball_left1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_left2 = setup("/res/projectile/fireball_left2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_left3 = setup("/res/projectile/fireball_left1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_right1 = setup("/res/projectile/fireball_right1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_right2 = setup("/res/projectile/fireball_right2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        stay_right3 = setup("/res/projectile/fireball_right1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        down1 = setup("/res/projectile/fireball_down1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        down2 = setup("/res/projectile/fireball_down2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        up1 = setup("/res/projectile/fireball_up1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        up2 = setup("/res/projectile/fireball_up2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        left1 = setup("/res/projectile/fireball_left1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        left2 = setup("/res/projectile/fireball_left2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        right1 = setup("/res/projectile/fireball_right1", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
        right2 = setup("/res/projectile/fireball_right2", (int) (gamePanel.tileSize / 1.5), (int) (gamePanel.tileSize / 1.5));
    }

    public boolean haveRes(Entity user) {
        return user.mana >= useCost;
    }

    public void subtractRes(Entity user) {
        user.mana -= useCost;
    }

    public Color getParticleColor() {
        return new Color(128, 4, 4);
    }

    public int getParticleSize() {
        return 20;
    }

    public int getParticleSpeed() {
        return 1;
    }

    public int getParticleMaxLife() {
        return 20;
    }
}
