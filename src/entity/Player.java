package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    BufferedImage image;

    public final int screenX;
    public final int screenY;
    public int keys, temp;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        screenX = gamePanel.dfl_X;
        screenY = gamePanel.dfl_Y;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDfltX = solidArea.x;
        solidAreaDfltY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        setDefaultVal();
        getPlayerImg();

    }

    public void setDefaultVal() {
        worldX = gamePanel.dfl_X + gamePanel.tileSize * 24 - gamePanel.tileSize / 2 - gamePanel.screenWidth / 2;
        worldY = gamePanel.dfl_Y + gamePanel.tileSize * 24 - gamePanel.tileSize / 2 - gamePanel.screenHeight / 2;
        speed = 4;
        stayDirect = "begin";
        direct = "stay";
    }

    public void getPlayerImg() {
        try {
            stay1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/stay1.png")));
            stay2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/stay2.png")));
            stay3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/stay3.png")));
            stayup1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/stay_up1.png")));
            stayup2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/stay_up2.png")));
            stayup3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/stay_up3.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/me_right2.png")));


        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }

    public void update() {

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {

            if (keyHandler.upPressed) {
                direct = "up";

            } else if (keyHandler.downPressed) {
                direct = "down";

            } else if (keyHandler.leftPressed) {
                direct = "left";

            } else {
                direct = "right";

            }
            collisionOn = false;
            gamePanel.checker.checkTile(this);
            int objectIndex = gamePanel.checker.checkObject(this, true);

            pickUp(objectIndex);

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
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }

            }
            spriteImageChange(4);


        } else {
            switch (stayDirect) {
                case "begin", "down":
                    direct = "stay";
                    break;
                case "up":
                    direct = "stay_up";
                    break;
            }

            spriteImageChange(15);


        }

    }



    private void spriteImageChange(int delay) {
        counter++;
        if (counter > delay) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                switch (direct) {
                    case "left", "right":
                        spriteNum = 1;
                        break;
                    default:
                        spriteNum = 3;
                }

            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            counter = 0;
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
            String objectName = gamePanel.motherObject[counter].name;
            switch (objectName) {
                case "Key":
                    gamePanel.playSFX(1);
                    keys++;
                    gamePanel.motherObject[counter] = null;
                    gamePanel.ui.showMsg("You got a key!");
                    break;
                case "Door":

                    if (keys > 0) {
                        gamePanel.playSFX(2);
                        gamePanel.motherObject[counter] = null;
                        keys--;
                        gamePanel.ui.showMsg("You opened the door!");
                        System.out.println(objectName + " open, Key: " + keys);
                    } else {
                        sfxDelay(20);

                        gamePanel.ui.showMsg("You need a key!");
                        System.out.println(objectName + " close, Key: " + keys);
                        System.out.println(temp);
                    }
                    break;

                case "Boots":
                    gamePanel.playSFX(4);
                    speed += 1;
                    gamePanel.motherObject[counter] = null;
                    gamePanel.ui.showMsg("Speed up!");
                    break;

                case "Chest":
                    gamePanel.ui.finished = true;
                    gamePanel.stopMusic();
                    gamePanel.playSFX(5);
                    gamePanel.motherObject[counter] = null;
                    break;
            }

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
                    image = stayup1;
                }
                if (spriteNum == 2) {
                    image = stayup2;
                }
                if (spriteNum == 3) {
                    image = stayup3;
                }
                break;


        }

        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.setColor(Color.red);
        graphics2D.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

    }
}
