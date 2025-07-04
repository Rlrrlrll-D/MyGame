package entity;

import main.GamePanel;
import main.KeyHandler;
import monster.Ogr;
import monster.RedSlime;
import monster.SkeletonZ;
import monster.Slime;
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
        worldX = gamePanel.tileSize * 26;
        worldY = gamePanel.tileSize * 23;
        stayDirect = "down";
        direct = "stay";
        defaultSpeed = 4;
        speed = defaultSpeed;
        level = 1;
        maxLife = 6;
        maxMana = 2;
        mana = maxMana;
        life = maxLife;
        ammo = 10;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 1;
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
        changePosition();
        gamePanel.sound.volumeScale = 1;
        speed = defaultSpeed;
        stayDirect = "down";
        direct = "stay";
    }

    private void changePosition() {
        switch (gamePanel.currentMap) {
            case 0 -> {
                worldX = gamePanel.tileSize * 27;
                worldY = gamePanel.tileSize * 23;
            }
            case 2 -> {
                worldX = gamePanel.tileSize * 2;
                worldY = gamePanel.tileSize * 46;
            }
            case 3 -> {
                worldX = gamePanel.tileSize * 24;
                worldY = gamePanel.tileSize * 44;
            }
        }
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
        inventory.add(new Tent(gamePanel));
        inventory.add(new Lantern(gamePanel));

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

        if (currentWeapon instanceof Pickaxe) {
            attackUp1 = setup("/res/player/pick_up_1", gamePanel.tileSize, gamePanel.tileSize * 2);
            attackUp2 = setup("/res/player/pick_up_2", gamePanel.tileSize, gamePanel.tileSize * 2);
            attackDown1 = setup("/res/player/pick_down_1", gamePanel.tileSize, gamePanel.tileSize * 2);
            attackDown2 = setup("/res/player/pick_down_2", gamePanel.tileSize, gamePanel.tileSize * 2);
            attackLeft1 = setup("/res/player/pick_left_1", gamePanel.tileSize * 2, gamePanel.tileSize);
            attackLeft2 = setup("/res/player/pick_left_2", gamePanel.tileSize * 2, gamePanel.tileSize);
            attackRight1 = setup("/res/player/pick_right_1", gamePanel.tileSize * 2, gamePanel.tileSize);
            attackRight2 = setup("/res/player/pick_right_2", gamePanel.tileSize * 2, gamePanel.tileSize);

        }
    }

    public void setSleepingImg(BufferedImage image) {
        stay1 = stay2 = stay3 = stay_up1 = stay_up2 = stay_up3 = stay_left1 = stay_left2 = stay_left3 = stay_right1 = stay_right2 = stay_right3 = up1 = up2 = down1 = down2 = left1 = left2 = right1 = right2 = image;
    }

    public void update() {
        if (escapeKnock) {
            handleEscapeKnock();
        } else if (isAttack) {
            attack();
        } else if (keyHandler.spacePressed) {
            handleGuarding();
        } else if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed || keyHandler.enterPressed) {
            handleMovementAndInteraction();
        } else {
            handleIdleState();
        }
        handleProjectileFiring();
        invincible(60);
        shotCount();
        checkLife();
        checkMana();
        if (!keyHandler.modeOfGod) {
            checkGameOver();
        }
    }

    private void handleEscapeKnock() {
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
            moveInDirection(knockBackDirect);
        }
        knockTime(15);
    }

    private void handleGuarding() {
        guarding = true;
        guardCounter++;
    }

    private void handleMovementAndInteraction() {
        setDirectionBasedOnInput();
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
        delay = 5;
        spriteImageChange(delay);
        delay = defaultDelay;
    }

    private void handleIdleState() {
        checkStayDirect();
        spriteImageChange(delay);
        guarding = false;
        guardCounter = 0;
    }

    private void handleProjectileFiring() {
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
    }

    private void setDirectionBasedOnInput() {
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
    }

    private void moveInDirection(String direction) {
        switch (direction) {
            case "up", "stay_up" -> worldY -= speed;
            case "down", "stay" -> worldY += speed;
            case "left", "stay_left" -> worldX -= speed;
            case "right", "stay_right" -> worldX += speed;
            default -> throw new IllegalStateException("Unexpected value: " + direct);
        }
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
                gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex].checkDrop();
                gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex] = gamePanel.interactiveTile[gamePanel.currentMap][interTileIndex].getDestroyForm();

            }
        }
    }

    private void checkDirect() {
        switch (direct) {
            case "up", "stay_up" -> worldY -= speed;
            case "down", "stay" -> worldY += speed;
            case "left", "stay_left" -> worldX -= speed;
            case "right", "stay_right" -> worldX += speed;
            default -> throw new IllegalStateException("Unexpected value: " + direct);
        }
        stayDirect = direct.replace("stay_", "");
    }

    private void checkStayDirect() {
        switch (stayDirect) {
            case "down", "stay" -> direct = "stay";
            case "up", "stay_up" -> direct = "stay_up";
            case "left", "stay_left" -> direct = "stay_left";
            case "right", "stay_right" -> direct = "stay_right";
            default -> throw new IllegalStateException("Unexpected value: " + stayDirect);
        }
    }

    private void touchMonster(int i) {
        if (i != 999) {
            if (!invincible && !gamePanel.mon[gamePanel.currentMap][i].isDying) {
                gamePanel.playSFX(6);
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

                if (gamePanel.mon[gamePanel.currentMap][i] instanceof Slime || gamePanel.mon[gamePanel.currentMap][i] instanceof RedSlime) {
                    gamePanel.playSFX(27);
                } else if (gamePanel.mon[gamePanel.currentMap][i] instanceof Ogr) {
                    gamePanel.playSFX(28);
                } else if (gamePanel.mon[gamePanel.currentMap][i] instanceof SkeletonZ) {
                    gamePanel.playSFX(29);
                }


                if (knockPower > 0) {
                    setKnockEscape(gamePanel.mon[gamePanel.currentMap][i], attacker, knockPower);
                }
                if (gamePanel.mon[gamePanel.currentMap][i].offBalance) {
                    if (gamePanel.mon[gamePanel.currentMap][i] instanceof SkeletonZ) {
                        attack *= 2;
                    } else {
                        attack *= 5;
                    }
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

    private void setDialogue() {
        dialogues[0][0] = "You are level " + level + " now!\n" + "You feel stronger!";
    }

    private void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            nextLevelExp *= 2;
            maxLife += 2;
            maxMana += 1;
            strength++;
            dexterity++;
            attack = getAttack();
            defence = getDefence();
            gamePanel.playSFX(11);
            gamePanel.gameBehavior = GamePanel.dialogBehavior;
            setDialogue();
            startDialog(this, 0);
        }
    }

    private void interactNPC(int i) {
        if (i != 999) {
            if (gamePanel.keyHandler.enterPressed) {
                notAttacked = true;
                gamePanel.npc[gamePanel.currentMap][i].speak();
            }
            gamePanel.npc[gamePanel.currentMap][i].move(direct);
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
            if (selectedItem instanceof Sword || selectedItem instanceof Axe || selectedItem instanceof Pickaxe) {
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
        Entity newItem = gamePanel.entityGenerator.getObj(entity.name);
        if (newItem.stackable) {
            int index = searchItemInInventory(newItem.name);
            if (index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            } else {
                if (inventory.size() != maxInventorySize) {
                    inventory.add(newItem);
                    canObtain = true;
                }
            }
        } else {
            if (inventory.size() != maxInventorySize) {
                inventory.add(newItem);
                canObtain = true;
            }
        }
        return canObtain;
    }

    public void drawing(Graphics2D graphics2D) {
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direct) {
            case "up" -> tempScreenY = handleUpState(tempScreenY);
            case "down" -> handleDownState();
            case "left" -> tempScreenX = handleLeftState(tempScreenX);
            case "right" -> handleRightState();
            case "stay" -> handleStayState();
            case "stay_up" -> tempScreenY = handleStayUpState(tempScreenY);
            case "stay_left" -> tempScreenX = handleStayLeftState(tempScreenX);
            case "stay_right" -> handleStayRightState();
            default -> throw new IllegalStateException("Unexpected value: " + direct);
        }
        if (transparent) {
            blinkEntity(graphics2D, 0.04f, 5);
        }
        if (draw) {
            shadow = setup("/res/objects/shadow", gamePanel.tileSize, gamePanel.tileSize / 4);
            graphics2D.drawImage(shadow, screenX, screenY - 6 + gamePanel.tileSize, null);
            graphics2D.drawImage(image, tempScreenX, tempScreenY, null);
        }
        graphics2D.setComposite((AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)));
    }

    private int handleUpState(int tempScreenY) {
        if (isAttack) {
            tempScreenY = screenY - gamePanel.tileSize;
            if (spriteNum == 1) {
                image = attackUp1;
            }
            if (spriteNum == 2) {
                image = attackUp2;
            }
        } else {
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
        return tempScreenY;
    }

    private void handleDownState() {
        if (isAttack) {
            if (spriteNum == 1) {
                image = attackDown1;
            }
            if (spriteNum == 2) {
                image = attackDown2;
            }
        } else {
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
    }

    private int handleLeftState(int tempScreenX) {
        if (isAttack) {
            tempScreenX = screenX - gamePanel.tileSize;
            if (spriteNum == 1) {
                image = attackLeft1;
            }
            if (spriteNum == 2) {
                image = attackLeft2;
            }
        } else {
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
        return tempScreenX;
    }

    private void handleRightState() {
        if (isAttack) {
            if (spriteNum == 1) {
                image = attackRight1;
            }
            if (spriteNum == 2) {
                image = attackRight2;
            }
        } else {
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
    }

    private void handleStayState() {
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
        } else {
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
    }

    private int handleStayUpState(int tempScreenY) {
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
        } else {
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
        return tempScreenY;
    }

    private int handleStayLeftState(int tempScreenX) {
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
        } else {
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
        return tempScreenX;
    }

    private void handleStayRightState() {
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
        } else {
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
    }
}



