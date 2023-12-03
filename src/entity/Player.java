package entity;

import main.GamePanel;
import main.KeyHandler;
import objects.ShieldWood;
import objects.Sword;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity {

    public final int screenX;
    public final int screenY;
    public int /*keys,*/ temp;
    public boolean notAttacked;
    KeyHandler keyHandler;
    BufferedImage image;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);

        this.keyHandler = keyHandler;
        name = "Player";
        screenX = gamePanel.dfl_X;
        screenY = gamePanel.dfl_Y;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultVal();
        getPlayerImg();
        getPlayerAttackImage();

    }

    public void setDefaultVal() {
        worldX = gamePanel.tileSize;
        worldY = gamePanel.tileSize * 39;
        stayDirect = "begin";
        direct = "stay";
        speed = 4;
        level = 1;
        maxLife = 6;
        life = maxLife;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new Sword(gamePanel);
        currentShield = new ShieldWood(gamePanel);
        attack = getAttack();
        defence = getDefence();


    }

    public int getAttack() {
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefence() {
        return defence = dexterity * currentShield.defenceValue;
    }

    public void getPlayerImg() {

        stay1 = setup("/res/player/stay1", gamePanel.tileSize, gamePanel.tileSize);
        stay2 = setup("/res/player/stay2", gamePanel.tileSize, gamePanel.tileSize);
        stay3 = setup("/res/player/stay3", gamePanel.tileSize, gamePanel.tileSize);
        stay_up1 = setup("/res/player/stay_up1", gamePanel.tileSize, gamePanel.tileSize);
        stay_up2 = setup("/res/player/stay_up2", gamePanel.tileSize, gamePanel.tileSize);
        stay_up3 = setup("/res/player/stay_up3", gamePanel.tileSize, gamePanel.tileSize);
        stay_left1 = setup("/res/player/stay_left1", gamePanel.tileSize, gamePanel.tileSize);
        stay_left2 = setup("/res/player/stay_left2", gamePanel.tileSize, gamePanel.tileSize);
        stay_left3 = setup("/res/player/stay_left3", gamePanel.tileSize, gamePanel.tileSize);
        stay_right1 = setup("/res/player/stay_right1", gamePanel.tileSize, gamePanel.tileSize);
        stay_right2 = setup("/res/player/stay_right2", gamePanel.tileSize, gamePanel.tileSize);
        stay_right3 = setup("/res/player/stay_right3", gamePanel.tileSize, gamePanel.tileSize);
        up1 = setup("/res/player/me_up1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/res/player/me_up2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/res/player/me_down1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/res/player/me_down2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/res/player/me_left1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/res/player/me_left2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/res/player/me_right1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/res/player/me_right2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/res/player/attack_up1", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackUp2 = setup("/res/player/attack_up2", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackDown1 = setup("/res/player/attack_down1", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackDown2 = setup("/res/player/attack_down2", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackLeft1 = setup("/res/player/attack_left1", gamePanel.tileSize * 2, gamePanel.tileSize);
        attackLeft2 = setup("/res/player/attack_left2", gamePanel.tileSize * 2, gamePanel.tileSize);
        attackRight1 = setup("/res/player/attack_right1", gamePanel.tileSize * 2, gamePanel.tileSize);
        attackRight2 = setup("/res/player/attack_right2", gamePanel.tileSize * 2, gamePanel.tileSize);
    }


    public void update() {
        if (isAttack) {
            attack();
        } else if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed || keyHandler.enterPressed) {

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

            int monIndex = gamePanel.checker.checkEntity(this, gamePanel.mon);
            touchMonster(monIndex);

            gamePanel.eventHandler.checkEvent();

            if (!collisionOn && !keyHandler.enterPressed) {
                checkDirect();
            }
            if (keyHandler.enterPressed && !notAttacked) {
                gamePanel.playSFX(9);
                isAttack = true;
                counter = 0;
            }
            notAttacked = false;

            gamePanel.keyHandler.enterPressed = false;

            spriteImageChange(5);

        } else {
            checkStayDirect();
            spriteImageChange(15);
        }
        invincible(60);
    }


    public void attack() {
        counter++;
        if (counter <= 3) {
            spriteNum = 1;
        }
        if (counter > 3 && counter <= 6) {
            spriteNum = 2;
        }
        if (counter > 6 && counter <= 12) {
            spriteNum = 3;
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direct) {
                case "stay_up", "up":
                    worldY -= gamePanel.tileSize - gamePanel.tileSize / 4;
                    break;
                case "stay", "down":
                    worldY += gamePanel.tileSize - gamePanel.tileSize / 4;
                    break;
                case "stay_left", "left":
                    worldX -= gamePanel.tileSize - gamePanel.tileSize / 4;
                    break;
                case "stay_right", "right":
                    worldX += gamePanel.tileSize - gamePanel.tileSize / 4;
                    break;
            }
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gamePanel.checker.checkEntity(this, gamePanel.mon);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (counter > 12) {
            spriteNum = 1;
            counter = 0;

            isAttack = false;
        }

    }

    private void checkDirect() {
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
            default:
                throw new IllegalStateException("Unexpected value: " + direct);
        }
    }

    private void checkStayDirect() {
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
            default:
                throw new IllegalStateException("Unexpected value: " + stayDirect);
        }
    }

    public void touchMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                gamePanel.playSFX(8);
                int damage = gamePanel.mon[i].attack - defence;
                if (damage < 0) {
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {

            if (!gamePanel.mon[i].invincible) {

                gamePanel.playSFX(7);
                int damage = attack - gamePanel.mon[i].defence;
                if (damage < 0) {
                    damage = 0;
                }
                gamePanel.mon[i].life -= damage;
                gamePanel.mon[i].invincible = true;
                gamePanel.mon[i].damageReaction();

                if (gamePanel.mon[i].life <= 0) {
                    gamePanel.mon[i].isDying = true;
                }
            }
        }
    }

    private void interactNPC(int i) {
        if (gamePanel.keyHandler.enterPressed) {
            if (i != 999) {
                notAttacked = true;
                gamePanel.gameBehavior = GamePanel.dialogBehavior;
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
/*
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
*/
        }


    }


    public void drawing(Graphics2D graphics2D) {
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direct) {
            case "up":
                if (isAttack) {
                    tempScreenY = screenY - gamePanel.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                if (!isAttack) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }

                break;
            case "down":
                if (isAttack) {
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }
                if (!isAttack) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                break;
            case "left":
                if (isAttack) {
                    tempScreenX = screenX - gamePanel.tileSize;
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                }
                if (!isAttack) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                break;
            case "right":
                if (isAttack) {
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                }
                if (!isAttack) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                break;

            case "stay":
                if (isAttack) {
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                    if (spriteNum == 3) {
                        image = attackDown2;
                    }
                }
                if (!isAttack) {
                    if (spriteNum == 1) {
                        image = stay1;
                    }
                    if (spriteNum == 2) {
                        image = stay2;
                    }
                    if (spriteNum == 3) {
                        image = stay3;
                    }
                }
                break;

            case "stay_up":
                if (isAttack) {
                    tempScreenY = screenY - gamePanel.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                    if (spriteNum == 3) {
                        image = attackUp2;
                    }
                }
                if (!isAttack) {
                    if (spriteNum == 1) {
                        image = stay_up1;
                    }
                    if (spriteNum == 2) {
                        image = stay_up2;
                    }
                    if (spriteNum == 3) {
                        image = stay_up3;
                    }
                }
                break;
            case "stay_left":
                if (isAttack) {
                    tempScreenX = screenX - gamePanel.tileSize;
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                    if (spriteNum == 3) {
                        image = attackLeft2;
                    }
                }
                if (!isAttack) {
                    if (spriteNum == 1) {
                        image = stay_left1;
                    }
                    if (spriteNum == 2) {
                        image = stay_left2;
                    }
                    if (spriteNum == 3) {
                        image = stay_left3;
                    }
                }
                break;
            case "stay_right":
                if (isAttack) {
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                    if (spriteNum == 3) {
                        image = attackRight2;
                    }
                }
                if (!isAttack) {
                    if (spriteNum == 1) {
                        image = stay_right1;
                    }
                    if (spriteNum == 2) {
                        image = stay_right2;
                    }
                    if (spriteNum == 3) {
                        image = stay_right3;
                    }
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direct);
        }
        if (invincible) {
            blinkEntity(graphics2D, 0.05f, 5);
        }

        Color shadow = new Color(12, 12, 12, 55);
        graphics2D.setColor(shadow);
        graphics2D.fillRoundRect(screenX + solidArea.x, screenY + gamePanel.tileSize - gamePanel.tileSize / 3 / 2, solidArea.width, gamePanel.tileSize / 3, 10, 10);
        graphics2D.drawImage(image, tempScreenX, tempScreenY, null);
        graphics2D.setComposite((AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)));

/*
        // AttackArea
        tempScreenX = screenX + solidArea.x;
        tempScreenY = screenY + solidArea.y;
        switch (direct) {
            case "up", "stay_up":
                tempScreenY = screenY - attackArea.height;
                break;
            case "down", "stay":
                tempScreenY = screenY + gamePanel.tileSize;
                break;
            case "left", "stay_left":
                tempScreenX = screenX - attackArea.width;
                break;
            case "right", "stay_right":
                tempScreenX = screenX + gamePanel.tileSize;
                break;
        }
        graphics2D.setColor(Color.red);
        graphics2D.setStroke(new BasicStroke(1));
        graphics2D.drawRect(tempScreenX, tempScreenY, attackArea.width, attackArea.height);
        graphics2D.setColor(Color.white);
        graphics2D.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 26));
        graphics2D.drawString("Invincible:" + invinCounter, 10, 400);
*/

    }


}




