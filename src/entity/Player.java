package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity {

    public final int screenX;
    public final int screenY;
    public int /*keys,*/ temp;
    KeyHandler keyHandler;
    BufferedImage image;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);

        this.keyHandler = keyHandler;
        screenX = gamePanel.dfl_X;
        screenY = gamePanel.dfl_Y;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        setDefaultVal();
        getPlayerImg();

    }

    public void setDefaultVal() {
        worldX = gamePanel.dfl_X + gamePanel.tileSize * 24 - gamePanel.tileSize / 2 - gamePanel.screenWidth / 2;
        worldY = gamePanel.dfl_Y + gamePanel.tileSize * 24 - gamePanel.tileSize / 2 - gamePanel.screenHeight / 2;
        speed = 4;
        maxLife = 6;
        life = maxLife;
        stayDirect = "begin";
        direct = "stay";

    }

    public void getPlayerImg() {

        stay1 = setup("/res/player/stay1");
        stay2 = setup("/res/player/stay2");
        stay3 = setup("/res/player/stay3");
        stay_up1 = setup("/res/player/stay_up1");
        stay_up2 = setup("/res/player/stay_up2");
        stay_up3 = setup("/res/player/stay_up3");
        stay_left1 = setup("/res/player/stay_left1");
        stay_left2 = setup("/res/player/stay_left2");
        stay_left3 = setup("/res/player/stay_left3");
        stay_right1 = setup("/res/player/stay_right1");
        stay_right2 = setup("/res/player/stay_right2");
        stay_right3 = setup("/res/player/stay_right3");
        up1 = setup("/res/player/me_up1");
        up2 = setup("/res/player/me_up2");
        down1 = setup("/res/player/me_down1");
        down2 = setup("/res/player/me_down2");
        left1 = setup("/res/player/me_left1");
        left2 = setup("/res/player/me_left2");
        right1 = setup("/res/player/me_right1");
        right2 = setup("/res/player/me_right2");
    }


    public void update() {

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {

            if (keyHandler.upPressed) {
                direct = "up";

            }
            if (keyHandler.downPressed) {
                direct = "down";

            }
            if (keyHandler.leftPressed) {
                direct = "left";

            }
            if (keyHandler.rightPressed) {
                direct = "right";

            }
            collisionOn = false;
            gamePanel.checker.checkTile(this);
            int objectIndex = gamePanel.checker.checkObject(this, true);

            pickUp(objectIndex);
            int npcIndex = gamePanel.checker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);

            if (!collisionOn) {

                switch (direct) {
                    case "up":
                        worldY -= speed;
                        stayDirect = "up";
                        break;
                    case "down":
                        worldY += speed;
                        stayDirect = "down";
                        break;
                    case "left":
                        worldX -= speed;
                        stayDirect = "left";
                        break;
                    case "right":
                        worldX += speed;
                        stayDirect = "right";
                        break;
                }
            }
            spriteImageChange(3);

        } else {

            switch (stayDirect) {
                case "begin", "down":
                    direct = "stay";
                    break;
                case "up":
                    direct = "stay_up";
                    break;
                case "left":
                    direct = "stay_left";
                    break;
                case "right":
                    direct = "stay_right";
                    break;
            }
            spriteImageChange(10);
        }
        gamePanel.eventHandler.checkEvent();
    }

    private void interactNPC(int i) {

        if (i != 999) {
            if (gamePanel.keyHandler.enterPressed) {
                gamePanel.gameBehavior = gamePanel.dialogBehavior;
                gamePanel.npc[i].speak();
            }
        }
    }


    public void sfxDelay(int delay) {
        temp++;
        if (temp > delay) {
            gamePanel.playSFX(3);
            temp = 0;
        }
    }


    public void pickUp(int counter) {
        if (counter != 999) {
//            String objectName = gamePanel.motherObject[counter].name;
//            switch (objectName) {
//                case "Key":
//                    gamePanel.playSFX(1);
//                    keys++;
//                    gamePanel.motherObject[counter] = null;
//                    gamePanel.ui.showMsg("You got a key!");
//                    break;
//                case "Door":
//
//                    if (keys > 0) {
//                        gamePanel.playSFX(2);
//                        gamePanel.motherObject[counter] = null;
//                        keys--;
//                        gamePanel.ui.showMsg("You opened the door!");
//                        System.out.println(objectName + " open, Key: " + keys);
//                    } else {
//                        sfxDelay(20);
//
//                        gamePanel.ui.showMsg("You need a key!");
//                        System.out.println(objectName + " close, Key: " + keys);
//                        System.out.println(temp);
//                    }
//                    break;
//
//                case "Boots":
//                    gamePanel.playSFX(4);
//                    speed += 1;
//                    gamePanel.motherObject[counter] = null;
//                    gamePanel.ui.showMsg("Speed up!");
//                    break;
//
//                case "Chest":
//                    gamePanel.ui.finished = true;
//                    gamePanel.stopMusic();
//                    gamePanel.playSFX(5);
//                    gamePanel.motherObject[counter] = null;
//                    break;
//            }

        }
    }


    public void drawing(Graphics2D graphics2D) {


        switch (direct) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;

            case "stay":
                if (spriteNum == 1) {
                    image = stay1;
                }
                if (spriteNum == 2) {
                    image = stay2;
                }
                if (spriteNum == 3) {
                    image = stay3;
                }
                break;

            case "stay_up":
                if (spriteNum == 1) {
                    image = stay_up1;
                }
                if (spriteNum == 2) {
                    image = stay_up2;
                }
                if (spriteNum == 3) {
                    image = stay_up3;
                }
                break;
            case "stay_left":
                if (spriteNum == 1) {
                    image = stay_left1;
                }
                if (spriteNum == 2) {
                    image = stay_left2;
                }
                if (spriteNum == 3) {
                    image = stay_left3;
                }
                break;
            case "stay_right":
                if (spriteNum == 1) {
                    image = stay_right1;
                }
                if (spriteNum == 2) {
                    image = stay_right2;
                }
                if (spriteNum == 3) {
                    image = stay_right3;
                }
                break;


        }

        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.setColor(Color.red);
        graphics2D.drawRect(screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
        graphics2D.setColor(Color.white);
        graphics2D.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);


    }
}
