package entity;

import main.GamePanel;
import main.UtilityTool;
import monster.Slime;
import tile.interactive.DestructibleWall;
import tile.interactive.DryTree;
import tile.interactive.Trunk;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Entity {
    public final int maxInventorySize = 20;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);

    public int solidAreaDefaultX, solidAreaDefaultY;

    public int worldX, worldY;

    public int speed;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defence;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int ammo;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;

    public Projectile projectile;
    public ArrayList<Entity> inventory = new ArrayList<>();

    public int value;

    public int attackValue;
    public int defenceValue;
    public String description;
    public int useCost;
    public int price;
    public BufferedImage shadow, stay1, stay2, stay3, stay_up1, stay_up2, stay_up3,
            stay_left1, stay_left2, stay_left3, stay_right1, stay_right2, stay_right3, up1, up2, down1, down2, left1, left2, right1, right2;
    public String direct = "down", stayDirect = "begin";

    public int spriteNum = 1, counter = 0;
    public int maxLife;
    public int life;
    public int defaultSpeed;
    public int maxMana;
    public int mana;
    public int actionCounter;
    public int dialogCount;
    public int dialogSet;
    public int shotAvailableCounter;
    public int guardCounter;
    public int offBalanceCounter;


    public boolean collisionOn;
    public boolean invincible;
    public boolean escapeKnock;
    public boolean isAttack;
    public boolean isAlive = true;
    public boolean isDying;
    public boolean hpBarOn;
    public boolean onPath;
    public boolean stackable;
    public boolean offBalance;

    public Entity loot;
    public boolean opened;

    public int amount = 1;
    public int lightRadius;
    public int invinCounter;
    public int dyingCounter;
    public int hpBarCounter;
    public int knockCounter;
    public int knockPower;
    public int shotDelay;
    public int motionDelay1;
    public int motionDelay2;
    public int motionDelay3;
    public BufferedImage image, image1, image2, temp;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2, guardUp, guardDown, guardLeft, guardRight;
    public String name;
    public boolean collision;
    public Entity attacker;
    public Entity linkedEntity;
    public String knockBackDirect;
    public boolean guarding;
    public boolean transparent;
    GamePanel gamePanel;
    public String[][] dialogues = new String[20][20];


    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void resetCounter() {
        counter = 0;
        actionCounter = 0;
        invinCounter = 0;
        shotAvailableCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        knockCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }

    public int getLeftX() {
        return worldX + solidArea.x;
    }

    public int getXDistance(Entity entity) {
        return Math.abs(worldX - entity.worldX);
    }

    public int getYDistance(Entity entity) {
        return Math.abs(worldY - entity.worldY);
    }

    public int getTileDistance(Entity entity) {
        return (getXDistance(entity) + getYDistance(entity)) / gamePanel.tileSize;
    }

    public int getGoalCol(Entity entity) {
        return (entity.worldX + entity.solidArea.x) / gamePanel.tileSize;
    }

    public int getGoalRow(Entity entity) {
        return (entity.worldY + entity.solidArea.y) / gamePanel.tileSize;

    }

    public int getRightX() {
        return worldX + solidArea.x + solidArea.width;
    }

    public int getTopY() {
        return worldY + solidArea.y;
    }

    public int getBottomY() {
        return worldY + solidArea.y + solidArea.height;
    }

    public int getCol() {
        return (worldX + solidArea.x) / gamePanel.tileSize;
    }

    public int getRow() {
        return (worldY + solidArea.y) / gamePanel.tileSize;
    }


    public void setAction() {
    }

    public void damageReaction() {

    }

    public void setLoot(Entity loot) {

    }

    public void setKnockEscape(Entity entity, Entity attacker, int knockPower) {

        this.attacker = attacker;
        entity.knockBackDirect = attacker.direct;
        entity.speed += knockPower;
        entity.escapeKnock = true;
    }

    public void speak() {

        facePlayer();

    }

    public void startDialog(Entity entity, int setNum) {
        gamePanel.gameBehavior = GamePanel.dialogBehavior;
        gamePanel.ui.npc = entity;
        dialogSet = setNum;
    }

    protected void facePlayer() {
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
    }

    public void interact() {

    }

    public boolean use(Entity entity) {
        return false;
    }

    public void checkDrop() {
    }

    protected int getDetected(Entity user, Entity[][] objects, String name) {
        int i = 999;
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direct) {
            case "up", "stay_up":
                nextWorldY = user.getTopY() - gamePanel.player.speed;
                break;
            case "down", "stay":
                nextWorldY = user.getBottomY() + gamePanel.player.speed;
                break;
            case "left", "stay_left":
                nextWorldX = user.getLeftX() - gamePanel.player.speed;
                break;
            case "right", "stay_right":
                nextWorldX = user.getRightX() + gamePanel.player.speed;
                break;
        }
        int col = nextWorldX / gamePanel.tileSize;
        int row = nextWorldY / gamePanel.tileSize;

        for (int j = 0; j < objects[1].length; j++) {
            if (objects[gamePanel.currentMap][j] != null) {
                if (objects[gamePanel.currentMap][j].getCol() == col &&
                        objects[gamePanel.currentMap][j].getRow() == row &&
                        objects[gamePanel.currentMap][j].name.equals(name)) {

                    i = j;
                    break;
                }
            }
        }
        return i;
    }

    protected void searchPath(int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x) / gamePanel.tileSize;
        int startRow = (worldY + solidArea.y) / gamePanel.tileSize;

        gamePanel.pathFinder.setNodes(startCol, startRow, goalCol, goalRow);

        if (gamePanel.pathFinder.search()) {
            int nextX = gamePanel.pathFinder.pathList.get(0).col * gamePanel.tileSize;
            int nextY = gamePanel.pathFinder.pathList.get(0).row * gamePanel.tileSize;

            int entLeftX = worldX + solidArea.x;
            int entRightX = worldX + solidArea.x + solidArea.width;
            int entTopY = worldY + solidArea.y;
            int entBottomY = worldY + solidArea.y + solidArea.height;

            if (entTopY > nextY && entLeftX >= nextX && entRightX < nextX + gamePanel.tileSize) {

                direct = "up";

            } else if (entTopY < nextY && entLeftX >= nextX && entRightX < nextX + gamePanel.tileSize) {
                direct = "down";


            } else if (entTopY >= nextY && entBottomY < nextY + gamePanel.tileSize) {

                if (entLeftX > nextX) {
                    direct = "left";
                }
                if (entLeftX < nextX) {
                    direct = "right";
                }
            } else if (entTopY > nextY && entLeftX > nextX) {

                direct = "up";
                checkCollision();
                if (collisionOn) {
                    direct = "left";
                }
            } else if (entTopY > nextY && entLeftX < nextX) {
                direct = "up";
                checkCollision();
                if (collisionOn) {
                    direct = "right";
                }
            } else if (entTopY < nextY && entLeftX > nextX) {
                direct = "down";
                checkCollision();
                if (collisionOn) {
                    direct = "left";
                }
            } else if (entTopY < nextY && entLeftX < nextX) {
                direct = "down";
                checkCollision();
                if (collisionOn) {
                    direct = "right";
                }
            }
//            int nextCol = gamePanel.pathFinder.pathList.get(0).col;
//            int nextRow = gamePanel.pathFinder.pathList.get(0).row;
//
//            if (nextCol == goalCol && nextRow == goalRow) {
//                onPath = false;
//            }
        }
    }

    protected void attack() {
        counter++;
        if (counter <= motionDelay1) {
            spriteNum = 1;
        }
        if (counter > motionDelay1 && counter <= motionDelay2) {
            spriteNum = 2;
        }
        if (counter > motionDelay2 && counter <= motionDelay3) {
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
            if (this instanceof Monster) {
                if (gamePanel.checker.checkPlayer(this)) {
                    damagePlayer(attack);
                }
            } else {
                int monsterIndex = gamePanel.checker.checkEntity(this, gamePanel.mon);
                gamePanel.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockPower);
                int interTileIndex = gamePanel.checker.checkEntity(this, gamePanel.interactiveTile);
                gamePanel.player.damageInterTile(interTileIndex);
                int projectileIndex = gamePanel.checker.checkEntity(this, gamePanel.projectile);
                gamePanel.player.damageProjectile(projectileIndex);
            }

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (counter > motionDelay3) {
            spriteNum = 1;
            counter = 0;
            isAttack = false;
        }
    }

    protected void checkCollision() {
        collisionOn = false;
        gamePanel.checker.checkTile(this);
        gamePanel.checker.checkObject(this, false);
        gamePanel.checker.checkEntity(this, gamePanel.npc);
        gamePanel.checker.checkEntity(this, gamePanel.mon);
        gamePanel.checker.checkEntity(this, gamePanel.interactiveTile);
        boolean touchPlayer = gamePanel.checker.checkPlayer(this);
        if (this instanceof Slime && touchPlayer) {
            damagePlayer(attack);
        }
    }

    protected void dropItem(Entity dropped) {
        for (int i = 0; i < gamePanel.objects[1].length; i++) {
            if (gamePanel.objects[gamePanel.currentMap][i] == null) {
                gamePanel.objects[gamePanel.currentMap][i] = dropped;
                gamePanel.objects[gamePanel.currentMap][i].worldX = worldX;
                gamePanel.objects[gamePanel.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public void update() {
        if (escapeKnock) {
            checkCollision();
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
            knockTime(10);

        } else if (isAttack) {
            attack();
        } else {
            setAction();
            checkCollision();
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
        invincible(40);
        shotCount();
        offBalanceTime();
    }

    protected void move(String direct) {

    }

    protected void offBalanceTime() {
        if (offBalance) {
            offBalanceCounter++;
            if (offBalanceCounter > 60) {
                offBalance = false;
                offBalanceCounter = 0;
            }
        }
    }

    protected void knockTime(int value) {
        knockCounter++;
        if (knockCounter == value) {
            knockCounter = 0;
            escapeKnock = false;
            speed = defaultSpeed;
        }
    }

    protected void shotCount() {
        this.shotDelay = 30;
        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
    }

    protected void checkAttackOrNot(int rate, int straight, int horizontal) {
        boolean targetInRange = false;
        int xD = getXDistance(gamePanel.player);
        int yD = getYDistance(gamePanel.player);

        switch (direct) {
            case "up":
                if (gamePanel.player.worldY < worldY && yD < straight && xD < horizontal) {
                    targetInRange = true;
                }
                break;
            case "down":
                if (gamePanel.player.worldY > worldY && yD < straight && xD < horizontal) {
                    targetInRange = true;
                }
                break;
            case "left":
                if (gamePanel.player.worldX < worldX && xD < straight && yD < horizontal) {
                    targetInRange = true;
                }
                break;
            case "right":
                if (gamePanel.player.worldX > worldX && xD < straight && yD < horizontal) {
                    targetInRange = true;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direct);
        }
        if (targetInRange) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                isAttack = true;
                spriteNum = 1;
                counter = 0;
                shotAvailableCounter = 0;
            }
        }
    }

    public void checkStopNotChasing(Entity entity, int distance, int rate) {
        if (getTileDistance(entity) > distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = false;
            }
        }
    }

    public void checkStartNotChasing(Entity entity, int distance, int rate) {
        if (getTileDistance(entity) < distance) {
            int i = new Random().nextInt(rate);
            if (i == 0) {
                onPath = true;
            }
        }
    }

    public void getRandomDirection(int interval) {
        actionCounter++;
        if (actionCounter == interval) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direct = "up";
            }
            if (i > 25 && i <= 50) {
                direct = "down";
            }
            if (i > 50 && i <= 75) {
                direct = "left";
            }
            if (i > 75) {
                direct = "right";
            }
            actionCounter = 0;
        }
    }

    public void checkShootOrNot(int rate, int shotInterval) {
        int i = new Random().nextInt(rate);
        if (i == 0 && !projectile.isAlive && shotAvailableCounter == shotInterval) {
            projectile.set(worldX + gamePanel.tileSize / 4, worldY + gamePanel.tileSize / 4, direct, true, this);
            for (int j = 0; j < gamePanel.projectile[1].length; j++) {
                if (gamePanel.projectile[gamePanel.currentMap][j] == null) {
                    gamePanel.projectile[gamePanel.currentMap][j] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }

    public void damagePlayer(int attack) {
        if (!gamePanel.player.invincible) {
            int damage = attack - gamePanel.player.defence;
            if (gamePanel.player.guarding && getOppositeDirect()) {
                System.out.println(gamePanel.player.guardCounter + " " + counter);
                if (gamePanel.player.guardCounter < 10) {
                    damage = 0;
                    gamePanel.playSFX(19);
                    setKnockEscape(this, gamePanel.player, knockPower);
                    offBalance = true;
                    counter = -60;
                } else {
                    damage /= 3;
                    gamePanel.playSFX(18);
                }
            } else {
                gamePanel.playSFX(6);
                if (damage < 1) {
                    damage = 1;
                }
            }
            if (damage != 0) {
                gamePanel.player.transparent = true;
                setKnockEscape(gamePanel.player, this, knockPower);
            }
            gamePanel.player.life -= damage;
            gamePanel.player.invincible = true;
        }
    }

    private boolean getOppositeDirect() {
        return (gamePanel.player.direct.equals("up") || gamePanel.player.direct.equals("stay_up")) && direct.equals("down") ||
                (gamePanel.player.direct.equals("down") || gamePanel.player.direct.equals("stay")) && direct.equals("up") ||
                (gamePanel.player.direct.equals("left") || gamePanel.player.direct.equals("stay_left")) && direct.equals("right") ||
                (gamePanel.player.direct.equals("right") || gamePanel.player.direct.equals("stay_right")) && direct.equals("left");
    }

    protected void invincible(int delay) {
        if (invincible) {
            invinCounter++;
            if (invinCounter > delay) {
                invincible = false;
                transparent = false;
                invinCounter = 0;
            }
        }
    }


    public int getParticleSize() {
        return 0;
    }

    public int getParticleSpeed() {
        return 0;
    }

    public int getParticleMaxLife() {
        return 0;
    }

    public BufferedImage getParticleImg() {
        return null;
    }

    protected void generateParticle(Entity generator, Entity target) {

        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();
        BufferedImage image = generator.getParticleImg();

        Particle p1 = new Particle(gamePanel, target, image, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gamePanel, target, image, size, speed, maxLife, -2, 1);
        Particle p3 = new Particle(gamePanel, target, image, size, speed, maxLife, 2, -1);
        Particle p4 = new Particle(gamePanel, target, image, size, speed, maxLife, 2, 1);
        gamePanel.particleArrayList.add(p1);
        gamePanel.particleArrayList.add(p2);
        gamePanel.particleArrayList.add(p3);
        gamePanel.particleArrayList.add(p4);
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
        temp = null;
        try {
            temp = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            temp = UtilityTool.scaleImage(temp, width, height);

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
            int tempScreenX = scrX;
            int tempScreenY = scrY;

            switch (direct) {
                case "up":
                    if (isAttack) {
                        tempScreenY = scrY - gamePanel.tileSize;
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
                        tempScreenX = scrX - gamePanel.tileSize;
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
                        tempScreenY = scrY - gamePanel.tileSize;
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
                        tempScreenX = scrX - gamePanel.tileSize;
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

            if (this instanceof Monster && hpBarOn) {
                double oneScale = (double) gamePanel.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                graphics2D.setColor(new Color(0x090202));
                graphics2D.fillRect(scrX - 1, scrY - 15, gamePanel.tileSize + 2, 12);

                graphics2D.setColor(new Color(0x861515));
                graphics2D.fillRect(scrX, scrY - 14, (int) hpBarValue, 10);
                hpBarCounter++;

                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible && !(this instanceof DryTree) && !(this instanceof DestructibleWall)) {
                hpBarOn = true;
                hpBarCounter = 0;
                blinkEntity(graphics2D, 0.3f, 4);
            }
            if (isDying) {
                dyingAnim(graphics2D, 0.01f, 4);
            }


            graphics2D.drawImage(image, tempScreenX, tempScreenY, null);
            shadow = setup("/res/objects/shadow", gamePanel.tileSize, gamePanel.tileSize / 4);

            if (!(this instanceof DryTree) && !(this instanceof Trunk) && !(this instanceof DestructibleWall)) {
                graphics2D.drawImage(shadow, scrX, scrY + gamePanel.tileSize - 6, null);
            }
            graphics2D.setComposite((AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)));
        }
    }
}
















































