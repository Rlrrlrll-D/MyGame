package entity;

import main.GamePanel;

public class Projectile extends Entity {
    Entity user;

    public Projectile(GamePanel gamePanel) {
        super(gamePanel);
        solidArea.width = 10;
        solidArea.height = 10;
    }

    public void set(int worldX, int worldY, String direct, boolean isAlive, Entity user) {
        this.user = user;
        this.worldX = worldX;
        this.worldY = worldY;
        this.direct = direct;
        this.isAlive = isAlive;
        this.life = this.maxLife;

    }

    public void update() {
        collisionOn = false;
        //gamePanel.checker.checkTile(this);
        Entity entity = gamePanel.checker.getCollidingEntity(this);

        if (collisionOn) {
            generateParticle(user.projectile, entity);
            isAlive = false;
        }


        if (user == gamePanel.player) {
            int monIndex = gamePanel.checker.checkEntity(this, gamePanel.mon);
            if (monIndex != 999) {
                gamePanel.player.damageMonster(monIndex, this, attack * (gamePanel.player.level / 2), knockPower);
                generateParticle(user.projectile, gamePanel.mon[gamePanel.currentMap][monIndex]);
                isAlive = false;
            }
        }
        if (user != gamePanel.player) {
            boolean contactPlayer = gamePanel.checker.checkPlayer(this);
            if (!gamePanel.player.invincible && contactPlayer) {
                damagePlayer(attack);
                generateParticle(user.projectile, user.projectile);
                isAlive = false;
            }

        }
        switch (direct) {
            case "up", "stay_up" -> worldY -= speed;
            case "down", "stay" -> worldY += speed;
            case "left", "stay_left" -> worldX -= speed;
            case "right", "stay_right" -> worldX += speed;
            default -> throw new IllegalStateException("Unexpected value: " + direct);
        }

        life--;
        if (life <= 0) {
            isAlive = false;
        }
        spriteImageChange(7);
    }

    public boolean haveRes(Entity user) {
        return false;
    }

    public void subtractRes(Entity user) {

    }
}
