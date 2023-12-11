package objects;

import entity.Projectile;
import main.GamePanel;

public class Fireball extends Projectile {

    GamePanel gamePanel;

    public Fireball(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        name = "Fireball";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        isAlive = false;
        getImg();

    }

    public void getImg() {
        stay1 = setup("/res/projectile/fireball_down1", gamePanel.tileSize, gamePanel.tileSize);
        stay2 = setup("/res/projectile/fireball_down2", gamePanel.tileSize, gamePanel.tileSize);
        stay3 = setup("/res/projectile/fireball_down1", gamePanel.tileSize, gamePanel.tileSize);
        stay_up1 = setup("/res/projectile/fireball_up1", gamePanel.tileSize, gamePanel.tileSize);
        stay_up2 = setup("/res/projectile/fireball_up2", gamePanel.tileSize, gamePanel.tileSize);
        stay_up3 = setup("/res/projectile/fireball_up1", gamePanel.tileSize, gamePanel.tileSize);
        stay_left1 = setup("/res/projectile/fireball_left1", gamePanel.tileSize, gamePanel.tileSize);
        stay_left2 = setup("/res/projectile/fireball_left2", gamePanel.tileSize, gamePanel.tileSize);
        stay_left3 = setup("/res/projectile/fireball_left1", gamePanel.tileSize, gamePanel.tileSize);
        stay_right1 = setup("/res/projectile/fireball_right1", gamePanel.tileSize, gamePanel.tileSize);
        stay_right2 = setup("/res/projectile/fireball_right2", gamePanel.tileSize, gamePanel.tileSize);
        stay_right3 = setup("/res/projectile/fireball_right1", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/res/projectile/fireball_down1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/res/projectile/fireball_down2", gamePanel.tileSize, gamePanel.tileSize);
        up1 = setup("/res/projectile/fireball_up1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/res/projectile/fireball_up2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/res/projectile/fireball_left1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/res/projectile/fireball_left2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/res/projectile/fireball_right1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/res/projectile/fireball_right2", gamePanel.tileSize, gamePanel.tileSize);
    }
}
