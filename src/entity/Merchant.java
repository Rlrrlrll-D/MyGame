package entity;

import main.GamePanel;
import objects.*;

import java.awt.*;

public class Merchant extends Entity {
    public Merchant(GamePanel gamePanel) {
        super(gamePanel);
        direct = "up";
        speed = 0;
        delay = 25;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        getImg();
        setDialog();
        setItem();
    }

    public void getImg() {

        up1 = setup("/res/npc/merchant1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/res/npc/merchant2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/res/npc/merchant1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/res/npc/merchant2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/res/npc/merchant1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/res/npc/merchant2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/res/npc/merchant1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/res/npc/merchant2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setDialog() {
        dialogues[0][0] = "He he, so you found me. \nI have some good stuff. \nDo you want to trade?";
        dialogues[1][0] = "Come again, bro!";
        dialogues[2][0] = "You need more coin to buy that!";
        dialogues[3][0] = "You cannot carry anymore!";
        dialogues[4][0] = "You cannot sell an equipped item!";
    }

    public void setItem() {
        inventory.add(new Key(gamePanel));
        inventory.add(new ShieldBlue(gamePanel));
        inventory.add(new Sword(gamePanel));
        inventory.add(new Axe(gamePanel));
        inventory.add(new PotionRed(gamePanel));
    }

    public void speak() {
        //facePlayer();
        gamePanel.gameBehavior = GamePanel.tradeBehavior;
        gamePanel.ui.npc = this;
    }
}
