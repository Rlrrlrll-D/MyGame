package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public int worldX, worldY;
    public int speed;

    public BufferedImage shadow, stay1, stay2, stay3, stay_up1, stay_up2, stay_up3,
            stay_left1, stay_left2, stay_left3, stay_right1, stay_right2, stay_right3, up1, up2, down1, down2, left1, left2, right1, right2;
    public String direct, stayDirect;

    public int spriteNum = 1, counter = 0;
    public int maxLife;
    public int life;
    public int actionCounter;
    public int dialogCount;
    public boolean collisionOn;
    public boolean invincible;
    public int invinCounter;
    public BufferedImage image, image1, image2, temp;
    public String name;
    public boolean collision;
    public int type; //0 - player, 1=npc, 2=monster
    GamePanel gamePanel;
    String[] dialogues = new String[20];


    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    public void setAction() {
    }

    public void speak() {

        switch (gamePanel.player.direct) {
            case "up", "stay_up":
                direct = "down";
                break;
            case "down", "stay":
                direct = "up";
                break;
            case "left", "stay_left":
                direct = "right";
                break;
            case "right", "stay_right":
                direct = "left";
                break;
        }

        gamePanel.ui.dialogue = dialogues[dialogCount];
        dialogCount++;
        if (dialogues[dialogCount] == null) {
            dialogCount = 0;
        }


    }

    public void update() {
        setAction();
        collisionOn = false;
        gamePanel.checker.checkTile(this);
        gamePanel.checker.checkObject(this, false);
        gamePanel.checker.checkEntity(this, gamePanel.npc);
        gamePanel.checker.checkEntity(this, gamePanel.mon);
        //gamePanel.checker.checkPlayer(this);
        boolean touchPlayer = gamePanel.checker.checkPlayer(this);
        if (this.type == 2 && touchPlayer) {
            if (!gamePanel.player.invincible) {
                gamePanel.player.life--;
                gamePanel.player.invincible = true;
            }
        }
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
                default:
                    throw new IllegalStateException("Unexpected value: " + direct);
            }

        }
        spriteImageChange(17);
    }

    protected void blinkPlayer(Graphics2D graphics2D) {

        if (invinCounter >= 0 && invinCounter < 6
                || invinCounter >= 12 && invinCounter < 18
                || invinCounter >= 24 && invinCounter < 30
                || invinCounter >= 36 && invinCounter < 42
                || invinCounter >= 48 && invinCounter < 54)
            graphics2D.setComposite((AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.05f)));


    }

    protected void spriteImageChange(int delay) {
        counter++;
        if (counter > delay) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                if (this instanceof Player) {
                    switch (direct) {
                        case "left", "right":
                            spriteNum = 1;
                            break;
                        default:
                            spriteNum = 3;
                    }
                } else {
                    spriteNum = 1;
                }
            } else if (spriteNum == 3) {
                spriteNum = 1;
            }
            counter = 0;
        }
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool utilityTool = new UtilityTool();
        temp = null;
        try {
            temp = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            temp = utilityTool.scaleImage(temp, gamePanel.tileSize, gamePanel.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public void drawing(Graphics2D graphics2D) {
        int scrX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int scrY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
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
                default:
                    throw new IllegalStateException("Unexpected value: " + direct);
            }
            Color shadow = new Color(12, 12, 12, 55);
            graphics2D.setColor(shadow);
            graphics2D.fillRoundRect(scrX, scrY + gamePanel.tileSize - gamePanel.tileSize / 3 / 2, gamePanel.tileSize, gamePanel.tileSize / 3, 10, 10);
            graphics2D.drawImage(image, scrX, scrY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
