package entity;

import main.GamePanel;
import main.UtilityTool;
import monster.Slime;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
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
    public boolean escape;
    public boolean isAttack;
    public boolean isAlive = true;
    public boolean isDying;
    public boolean hpBarOn;
    public int invinCounter;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;
    public int escapeCounter = 0;
    public BufferedImage image, image1, image2, temp;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String name;
    public boolean collision;
    // public int type; //0 - player, 1=npc, 2=monster
    GamePanel gamePanel;
    String[] dialogues = new String[20];


    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setAction() {
    }

    public void damageReaction() {

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
        boolean touchPlayer = gamePanel.checker.checkPlayer(this);
        if (this instanceof Slime && touchPlayer) {
            if (!gamePanel.player.invincible) {
                gamePanel.playSFX(6);
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
        invincible(40);
        escaping(50, this);
    }

    protected void invincible(int delay) {
        if (invincible) {
            invinCounter++;
            if (invinCounter > delay) {
                invincible = false;
                invinCounter = 0;
            }
        }
    }

    protected void escaping(int delay, Entity entity) {
        if (escape) {
            escapeCounter++;
            entity.speed = 3;
            if (escapeCounter > delay) {
                escape = false;
                entity.speed = 1;
                escapeCounter = 0;
            }
        }
    }

    protected void blinkEntity(Graphics2D graphics2D, float i, int interval) {

        if (invinCounter >= interval && invinCounter < interval * 2
                || invinCounter >= interval * 3 && invinCounter < interval * 4
                || invinCounter >= interval * 5 && invinCounter < interval * 6
                || invinCounter >= interval * 7 && invinCounter < interval * 8
                || invinCounter >= interval * 9 && invinCounter < interval * 10)
            graphics2D.setComposite((AlphaComposite.getInstance(AlphaComposite.SRC_OVER, i)));
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

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool utilityTool = new UtilityTool();
        temp = null;
        try {
            temp = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            temp = utilityTool.scaleImage(temp, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public void dyingAnim(Graphics2D graphics2D, float i, int interval) {
        dyingCounter++;
        if (dyingCounter >= interval && dyingCounter < interval * 2
                || dyingCounter >= interval * 3 && dyingCounter < interval * 4
                || dyingCounter >= interval * 5 && dyingCounter < interval * 6
                || dyingCounter >= interval * 7 && dyingCounter < interval * 8
                || dyingCounter >= interval * 9 && dyingCounter < interval * 10)
            graphics2D.setComposite((AlphaComposite.getInstance(AlphaComposite.SRC_OVER, i)));
        if (dyingCounter > interval * 10) {
            isDying = false;
            isAlive = false;
        }
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

            if (this instanceof Slime && hpBarOn) {
                double oneScale = (double) gamePanel.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                graphics2D.setColor(new Color(0x090202));
                graphics2D.fillRect(scrX - 1, scrY - 11, gamePanel.tileSize + 2, 12);

                graphics2D.setColor(new Color(0x861515));
                graphics2D.fillRect(scrX, scrY - 10, (int) hpBarValue, 10);
                hpBarCounter++;

                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible) {
                hpBarOn = true;
                hpBarCounter = 0;
                blinkEntity(graphics2D, 0.3f, 4);
            }
            if (isDying) {
                dyingAnim(graphics2D, 0.01f, 4);
            }
            Color shadow = new Color(12, 12, 12, 55);
            graphics2D.setColor(shadow);
            graphics2D.fillRoundRect(scrX, scrY + gamePanel.tileSize - gamePanel.tileSize / 3 / 2, gamePanel.tileSize, gamePanel.tileSize / 3, 10, 10);
            graphics2D.drawImage(image, scrX, scrY, gamePanel.tileSize, gamePanel.tileSize, null);
            graphics2D.setComposite((AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)));
        }
    }
}
