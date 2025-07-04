package monster;

import entity.Monster;
import main.GamePanel;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;

import java.util.Random;

public class Bat extends Monster {
    GamePanel gamePanel;

    public Bat(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Bat";
        defaultSpeed = 4;
        speed = defaultSpeed;
        maxLife = 7;
        life = maxLife;
        attack = 6;
        defence = 0;
        exp = 7;
        delay = 4;

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

        getRandomDirection(15);

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
