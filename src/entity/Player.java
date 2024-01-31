package entity;

import main.GamePanel;
import main.KeyHandler;
import objects.*;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity {

    public final int screenX;
    public final int screenY;
    public boolean notAttacked;
    public boolean lightUp;

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
    }

    public void setDefaultVal() {
        worldX = gamePanel.tileSize * 2;
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
        currentShield = new ShieldWood(gamePanel);
        projectile = new Fireball(gamePanel);
        currentLight = null;
        attack = getAttack();
        defence = getDefence();
        getImg();
        getAttackImage();
        getGuardImg();
        setInventory();
        setDialogue();
    }

    public void setDefaultPos() {
        gamePanel.currentMap = 0;
        worldX = gamePanel.tileSize;
        worldY = gamePanel.tileSize * 39;
        speed = defaultSpeed;
        stayDirect = "begin";
        direct = "stay";
    }

    public void restoreStatus() {
        mana = maxMana;
        life = maxLife;
        speed = defaultSpeed;
        invincible = false;
        transparent = false;
        isAttack = false;
        guarding = false;
        escapeKnock = false;
        lightUp = true;
    }

    public void setInventory() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new Key(gamePanel));
        inventory.add(new Axe(gamePanel));
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        motionDelay1 = currentWeapon.motionDelay1;
        motionDelay2 = currentWeapon.motionDelay2;
        motionDelay3 = currentWeapon.motionDelay3;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefence() {
        return defence = dexterity * currentShield.defenceValue;
    }

    public void getImg() {

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

    private void getGuardImg() {

        guardUp = setup("/res/player/guard_up", gamePanel.tileSize, gamePanel.tileSize * 2);
        guardDown = setup("/res/player/guard_down", gamePanel.tileSize, gamePanel.tileSize * 2);
        guardLeft = setup("/res/player/guard_left", gamePanel.tileSize * 2, gamePanel.tileSize);
        guardRight = setup("/res/player/guard_right", gamePanel.tileSize * 2, gamePanel.tileSize);
    }

    public void getSleepingImg(BufferedImage image) {
        stay1 = image;
        stay2 = image;
        stay3 = image;
        stay_up1 = image;
        stay_up2 = image;
        stay_up3 = image;
        stay_left1 = image;
        stay_left2 = image;
        stay_left3 = image;
        stay_right1 = image;
        stay_right2 = image;
        stay_right3 = image;
        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 = image;
        right2 = image;
    }

    public void getAttackImage() {
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

    public void update() {
        if (escapeKnock) {
            collisionOn = false;
            gamePanel.checker.checkTile(this);
            gamePanel.checker.checkObject(this, true);
            gamePanel.checker.checkEntity(this, gamePanel.npc);
            gamePanel.checker.checkEntity(this, gamePanel.mon);
            gamePanel.checker.checkEntity(this, gamePanel.interactiveTile);

            if (collisionOn) {
                knockCounter = 0;
                escapeKnock = false;
                speed = defaultSpeed;
            } else {
                switch (knockBackDirect) {
                    case "up", "stay_up":
                        worldY -= speed;
                        break;
                    case "down", "stay":
                        worldY += speed;
                        break;
                    case "left", "stay_left":
                        worldX -= speed;
                        break;
                    case "right", "stay_right":
                        worldX += speed;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + direct);
                }
            }
            knockTime(15);

        } else if (isAttack) {
            attack();
        } else if (keyHandler.spacePressed) {

            guarding = true;
            guardCounter++;

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
            guarding = false;
            guardCounter = 0;
            spriteImageChange(5);

        } else {
            checkStayDirect();
            spriteImageChange(15);
            guarding = false;
            guardCounter = 0;
        }
        if (gamePanel.keyHandler.shotKeyPressed && !projectile.isAlive && shotAvailableCounter == shotDelay && projectile.haveRes(this)) {
            projectile.set(worldX + gamePanel.tileSize / 4, worldY + gamePanel.tileSize / 4, direct, true, this);
            projectile.subtractRes(this);

            for (int i = 0; i < gamePanel.projectile[1].length; i++) {
                if (gamePanel.projectile[gamePanel.currentMap][i] == null) {
                    gamePanel.projectile[gamePanel.currentMap][i] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
            gamePanel.playSFX(14);
        }
        invincible(60);
        shotCount();
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

    public int getCurrentWeaponSlot() {
        int currentWeaponSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == currentWeapon) {
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }

    public int getCurrentShieldSlot() {
        int currentShieldSlot = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) == currentShield) {
                currentShieldSlot = i;
            }
        }
        return currentShieldSlot;
    }

    public void damageProjectile(int projectileIndex) {
        if (projectileIndex != 999) {
            Entity projectile = gamePanel.projectile[gamePanel.currentMap][projectileIndex];
            projectile.isAlive = false;
            generateParticle(projectile, projectile);
        }
    }

    public void damageInterTile(int interTileIndex) {
        if (interTileIndex != 999 && gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex].destructible && gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex].isCorrectItem(this) && !gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex].invincible) {
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
                if (damage < 1) {
                    damage = 1;
                }
                life -= damage;
                invincible = true;
                transparent = true;
            }
        }
    }

    public void damageMonster(int i, Entity attacker, int attack, int knockPower) {
        if (i != 999) {

            if (!gamePanel.mon[gamePanel.currentMap][i].invincible) {

                gamePanel.playSFX(7);

                if (knockPower > 0) {
                    setKnockEscape(gamePanel.mon[gamePanel.currentMap][i], attacker, knockPower);
                }
                if (gamePanel.mon[gamePanel.currentMap][i].offBalance) {
                    attack *= 5;
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

    public void setDialogue() {
        dialogues[0][0] = "You are level " + level + " now!\n" + "You feel stronger!";
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

            startDialog(this, 0);
        }
    }

    private void interactNPC(int i) {
        if (gamePanel.keyHandler.enterPressed) {
            if (i != 999) {
                notAttacked = true;
                gamePanel.npc[gamePanel.currentMap][i].speak();

            }
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

                if (canObtainItem(gamePanel.objects[gamePanel.currentMap][counter])) {

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
                getAttackImage();
            }
            if (selectedItem instanceof ShieldWood || selectedItem instanceof ShieldBlue) {
                currentShield = selectedItem;
                defence = getDefence();
            }
            if (selectedItem instanceof Light) {
                if (currentLight == selectedItem) {
                    currentLight = null;
                } else {
                    currentLight = selectedItem;
                }
                lightUp = true;
            }
            if (selectedItem instanceof Consumable) {
                if (selectedItem.use(this)) {
                    if (selectedItem.amount > 1) {
                        selectedItem.amount--;
                    } else {
                        inventory.remove(itemIndex);
                    }

                }
            }
        }
    }

    private int searchItemInInventory(String itemName) {
        int index = 999;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean canObtainItem(Entity entity) {
        boolean canObtain = false;
        if (entity.stackable) {
            int index = searchItemInInventory(entity.name);
            if (index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            } else {
                if (inventory.size() != maxInventorySize) {
                    inventory.add(entity);
                    canObtain = true;
                }
            }
        } else {
            if (inventory.size() != maxInventorySize) {
                inventory.add(entity);
                canObtain = true;
            }
        }
        return canObtain;
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
                if (guarding) {
                    tempScreenY = screenY - gamePanel.tileSize;
                    image = guardUp;
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
                if (guarding) {
                    image = guardDown;
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
                if (guarding) {
                    tempScreenX = screenX - gamePanel.tileSize;
                    image = guardLeft;
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
                if (guarding) {
                    image = guardRight;
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
                if (guarding) {
                    image = guardDown;
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
                if (guarding) {
                    tempScreenY = screenY - gamePanel.tileSize;
                    image = guardUp;
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
                if (guarding) {
                    tempScreenX = screenX - gamePanel.tileSize;
                    image = guardLeft;
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
                if (guarding) {
                    image = guardRight;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direct);
        }
        if (transparent) {
            blinkEntity(graphics2D, 0.04f, 5);
        }

        shadow = setup("/res/objects/shadow", gamePanel.tileSize, gamePanel.tileSize / 4);
        graphics2D.drawImage(shadow, screenX, screenY - 6 + gamePanel.tileSize, null);
        graphics2D.drawImage(image, tempScreenX, tempScreenY, null);
        graphics2D.setComposite((AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)));

    }

}




