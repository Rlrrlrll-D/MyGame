package entity;

import main.GamePanel;
import objects.*;

import java.awt.*;

public class Merchant extends Entity {
    public Merchant(GamePanel gamePanel) {
        super(gamePanel);
        direct = "down";
        speed = 0;

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
        dialogues[0] = "He he, so you found me. \nI have some good stuff. \nDo you want to trade?";
    }

    public void setItem() {
        inventory.add(new Key(gamePanel));
        inventory.add(new ShieldBlue(gamePanel));
        inventory.add(new Sword(gamePanel));
        inventory.add(new PotionRed(gamePanel));
        inventory.add(new Axe(gamePanel));
    }

    public void speak() {
        super.speak();
        gamePanel.gameBehavior = GamePanel.tradeBehavior;
        gamePanel.ui.npc = this;
    }

//    public void update() {
//        collisionOn = false;
//        gamePanel.checker.checkTile(this);
//        gamePanel.checker.checkObject(this, false);
//        gamePanel.checker.checkEntity(this, gamePanel.npc);
//        gamePanel.checker.checkEntity(this, gamePanel.mon);
//        gamePanel.checker.checkEntity(this, gamePanel.interactiveTile);
//
//
//        if (!collisionOn) {
//
//            switch (direct) {
//                case "up", "right", "down", "left":
//                    speed = 0;
//                    break;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + direct);
//            }
//        }
//        spriteImageChange(17);
//        //invincible(40);
//    }
}
