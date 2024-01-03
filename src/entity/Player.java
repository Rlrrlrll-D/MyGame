package entity;

import main.GamePanel;
import main.KeyHandler;
import objects.*;

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

        setDefaultVal();
        getPlayerImg();
        getPlayerAttackImage();
        setInventory();
    }

    public void setDefaultVal() {
        worldX = gamePanel.tileSize;
        worldY = gamePanel.tileSize * 39;
        stayDirect = "begin";
        direct = "stay";
        defaultSpeed = 4;
        speed = defaultSpeed;
        level = 1;
        maxLife = 6;
        maxMana = 4;
        mana = maxMana;
        life = maxLife;
        ammo = 10;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 10000;
        currentWeapon = new Sword(gamePanel);
        //currentWeapon = new Axe(gamePanel);
        currentShield = new ShieldWood(gamePanel);
        projectile = new Fireball(gamePanel);
        //projectile = new Rock(gamePanel);
        attack = getAttack();
        defence = getDefence();
    }

    public void setDefaultPos() {
        worldX = gamePanel.tileSize;
        worldY = gamePanel.tileSize * 39;
        stayDirect = "begin";
        direct = "stay";
    }

    public void restoreLifeMana() {
        mana = maxMana;
        life = maxLife;
        invincible = false;
    }

    public void setInventory() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new Key(gamePanel));
        inventory.add(new Axe(gamePanel));
    }

    private int getAttack() {

        attackArea = currentWeapon.attackArea;
        return attack = strength * currentWeapon.attackValue;
    }

    private int getDefence() {
        return defence = dexterity * currentShield.defenceValue;
    }

    private void getPlayerImg() {

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

    private void getPlayerAttackImage() {
        if (currentWeapon instanceof Sword) {
            attackUp1 = setup("/res/player/attack_up1", gamePanel.tileSize, gamePanel.tileSize * 2);
            attackUp2 = setup("/res/player/attack_up2", gamePanel.tileSize, gamePanel.tileSize * 2);
            attackDown1 = setup("/res/player/attack_down1", gamePanel.tileSize, gamePanel.tileSize * 2);
            attackDown2 = setup("/res/player/attack_down2", gamePanel.tileSize, gamePanel.tileSize * 2);
            attackLeft1 = setup("/res/player/attack_left1", gamePanel.tileSize * 2, gamePanel.tileSize);
            attackLeft2 = setup("/res/player/attack_left2", gamePanel.tileSize * 2, gamePanel.tileSize);
            attackRight1 = setup("/res/player/attack_right1", gamePanel.tileSize * 2, gamePanel.tileSize);
            attackRight2 = setup("/res/player/attack_right2", gamePanel.tileSize * 2, gamePanel.tileSize);
        }
        if (currentWeapon instanceof Axe) {
            attackUp1 = setup("/res/player/axe_up1", gamePanel.tileSize, gamePanel.tileSize * 2);
            attackUp2 = setup("/res/player/axe_up2", gamePanel.tileSize, gamePanel.tileSize * 2);
            attackDown1 = setup("/res/player/axe_down1", gamePanel.tileSize, gamePanel.tileSize * 2);
            attackDown2 = setup("/res/player/axe_down2", gamePanel.tileSize, gamePanel.tileSize * 2);
            attackLeft1 = setup("/res/player/axe_left1", gamePanel.tileSize * 2, gamePanel.tileSize);
            attackLeft2 = setup("/res/player/axe_left2", gamePanel.tileSize * 2, gamePanel.tileSize);
            attackRight1 = setup("/res/player/axe_right1", gamePanel.tileSize * 2, gamePanel.tileSize);
            attackRight2 = setup("/res/player/axe_right2", gamePanel.tileSize * 2, gamePanel.tileSize);

        }
    }

    private void knockEscape(Entity entity, int knockPower) {
        entity.direct = direct;
        entity.speed +=knockPower;
        entity.escapeKnock=true;
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

            gamePanel.checker.checkEntity(this, gamePanel.interactiveTile);

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
        if (gamePanel.keyHandler.shotKeyPressed && !projectile.isAlive && shotAvailableCounter == shotDelay && projectile.haveRes(this)) {
            projectile.set(worldX + gamePanel.tileSize / 4, worldY + gamePanel.tileSize / 4, direct, true, this);
            projectile.subtractRes(this);

            for (int i = 0; i < gamePanel.projectile[1].length; i++) {
                if (gamePanel.projectile[gamePanel.currentMap][i]==null){
                    gamePanel.projectile[gamePanel.currentMap][i]=projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
            gamePanel.playSFX(14);
        }
        invincible(60);
        shotCount(30);
        checkLife();
        checkMana();
        checkGameOver();
    }

    private void checkGameOver() {
        if (life <= 0) {
            gamePanel.gameBehavior = GamePanel.gameOverBehavior;
            gamePanel.stopMusic();
            if (gamePanel.sound.volumeScale < 5) {
                gamePanel.sound.volumeScale = 5;
            }
            gamePanel.playGameOver();
            gamePanel.sound.volumeScale = 2;
            gamePanel.ui.commandNum = -1;
        }
    }

    private void checkLife() {
        if (life > maxLife) {
            life = maxLife;
        }
    }

    private void checkMana() {
        if (mana > maxMana) {
            mana = maxMana;
        }
    }

    private void attack() {
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
            damageMonster(monsterIndex, attack, currentWeapon.knockPower);
            int interTileIndex = gamePanel.checker.checkEntity(this, gamePanel.interactiveTile);
            damageInterTile(interTileIndex);
            int projectileIndex = gamePanel.checker.checkEntity(this,gamePanel.projectile);
            damageProjectile(projectileIndex);
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

    private void damageProjectile(int projectileIndex) {
        if (projectileIndex!=999){
            Entity projectile = gamePanel.projectile[gamePanel.currentMap][projectileIndex];
            projectile.isAlive = false;
            generateParticle(projectile,projectile);
        }
    }

    private void damageInterTile(int interTileIndex) {
        if (interTileIndex != 999 && gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex].destructible
                && gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex].isCorrectItem(this) && !gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex].invincible) {
            gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex].playSnd();
            gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex].life--;
            gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex].invincible = true;

            generateParticle(gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex], gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex]);

            if (gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex].life == 0) {
                gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex] = gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex].getDestroyForm();
            }
        }
    }

    private void checkDirect() {
        switch (direct) {
            case "up", "stay_up":
                worldY -= speed;
                stayDirect = "up";
                break;
            case "down", "stay":
                worldY += speed;
                stayDirect = "down";
                break;
            case "left", "stay_left":
                worldX -= speed;
                stayDirect = "left";
                break;
            case "right", "stay_right":
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

    private void touchMonster(int i) {
        if (i != 999) {
            if (!invincible && !gamePanel.mon[gamePanel.currentMap][i].isDying) {
                gamePanel.playSFX(8);
                int damage = gamePanel.mon[gamePanel.currentMap][i].attack - defence;
                if (damage < 0) {
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, int attack, int knockPower) {
        if (i != 999) {

            if (!gamePanel.mon[gamePanel.currentMap][i].invincible) {

                gamePanel.playSFX(7);

                if (knockPower>0){
                    knockEscape(gamePanel.mon[gamePanel.currentMap][i],knockPower);
                }

                int dmg = attack - gamePanel.mon[gamePanel.currentMap][i].defence;
                if (dmg < 0) {
                    dmg = 0;
                }
                gamePanel.mon[gamePanel.currentMap][i].life -= dmg;
                gamePanel.ui.addMsg(dmg + " damage!");
                gamePanel.mon[gamePanel.currentMap][i].invincible = true;
                gamePanel.mon[gamePanel.currentMap][i].damageReaction();

                if (gamePanel.mon[gamePanel.currentMap][i].life <= 0) {
                    gamePanel.mon[gamePanel.currentMap][i].isDying = true;
                    gamePanel.ui.addMsg("Killed the " + gamePanel.mon[gamePanel.currentMap][i].name + "!");
                    gamePanel.ui.addMsg("Exp +" + gamePanel.mon[gamePanel.currentMap][i].exp);
                    exp += gamePanel.mon[gamePanel.currentMap][i].exp;
                    checkLevelUp();

                }
            }
        }
    }

    private void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp *= 2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defence = getDefence();
            gamePanel.playSFX(11);
            gamePanel.gameBehavior = GamePanel.dialogBehavior;
            gamePanel.ui.dialogue = "You are level " + level + " now!\n" + "You feel stronger!";

        }
    }

    private void interactNPC(int i) {
        if (gamePanel.keyHandler.enterPressed) {
            if (i != 999) {
                notAttacked = true;
                gamePanel.gameBehavior = GamePanel.dialogBehavior;
                gamePanel.npc[gamePanel.currentMap][i].speak();

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

    private void pickUp(int counter) {

        if (counter != 999) {
            if (gamePanel.objects[gamePanel.currentMap][counter] instanceof PickUpOnlyItems) {
                gamePanel.objects[gamePanel.currentMap][counter].use(this);
                gamePanel.objects[gamePanel.currentMap][counter] = null;
            } else if (gamePanel.objects[gamePanel.currentMap][counter] instanceof Obstacle) {
                if (keyHandler.enterPressed) {
                    notAttacked = true;
                    gamePanel.objects[gamePanel.currentMap][counter].interact();

                }
            } else {
                String txt;

                if (inventory.size() != maxInventorySize) {
                    inventory.add(gamePanel.objects[gamePanel.currentMap][counter]);
                    gamePanel.playSFX(1);
                    txt = "Got a " + gamePanel.objects[gamePanel.currentMap][counter].name + "!";
                } else {
                    txt = "You cannot carry any more!";
                }
                gamePanel.ui.addMsg(txt);
                gamePanel.objects[gamePanel.currentMap][counter] = null;
            }
        }
    }

    public void selectItem() {
        int itemIndex = gamePanel.ui.getItemIndex(gamePanel.ui.playerSlotCol, gamePanel.ui.playerSlotRow);
        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem instanceof Sword || selectedItem instanceof Axe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem instanceof ShieldWood || selectedItem instanceof ShieldBlue) {
                currentShield = selectedItem;
                defence = getDefence();
            }
            if (selectedItem instanceof Consumable) {
                if (selectedItem.use(this)) {
                    inventory.remove(itemIndex);
                }
            }
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




