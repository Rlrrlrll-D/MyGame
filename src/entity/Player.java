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
    public int keys = 0;

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
        direct = "stay";
    }

    public void getPlayerImg() {
        try {
            stay1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/stay1.png")));
            stay2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/stay2.png")));
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
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }

            }

            spriteImageChange(10);
        } else {
            direct = "stay";
            spriteImageChange(20);
        }

    }

    private void spriteImageChange(int delta) {
        counter++;
        if (counter > delta) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            counter = 0;
        }
    }
    private void sfxDelay(int delay) {
        counter++;
        if (counter>delay){
            gamePanel.playSFX(3);
            counter=0;
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
                    System.out.println(objectName + ": " + keys);
                    break;
                case "Door":

                    if (keys > 0) {
                        gamePanel.playSFX(2);
                        gamePanel.motherObject[counter] = null;
                        keys--;
                        System.out.println(objectName + " open, Key: " + keys);
                    } else {
                        sfxDelay(10);
                        System.out.println(objectName + " close, Key: " + keys);
                    }
                    break;

                case "Boots":
                    gamePanel.playSFX(4);
                    speed += 1;
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
                break;

        }

        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.setColor(Color.red);
        graphics2D.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

    }
}
