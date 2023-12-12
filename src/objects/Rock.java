package objects;

import entity.Projectile;
import main.GamePanel;

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
        stay1 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        stay2 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        stay3 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        stay_up1 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        stay_up2 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        stay_up3 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        stay_left1 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        stay_left2 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        stay_left3 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        stay_right1 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        stay_right2 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        stay_right3 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        up1 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/res/projectile/rock_down1", gamePanel.tileSize, gamePanel.tileSize);
    }
}
