package entity;

import main.GamePanel;

public class Projectile extends Entity {
    Entity user;

    public Projectile(GamePanel gamePanel) {
        super(gamePanel);
        solidArea.width = 20;
        solidArea.height = 20;
        delay = 7;
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
        explosionHittingProjectiles();
        explosionHittingObstacle();
        explosionHittingMonster();
        explosionHittingPlayer();
        directChange();
        lifeSpan();
        spriteImageChange(delay);
    }

    private void explosionHittingProjectiles() {
        for (int i = 0; i < gamePanel.projectile[1].length; i++) {
            if (gamePanel.projectile[gamePanel.currentMap][i] != null) {
                Projectile otherProjectile = (Projectile) gamePanel.projectile[gamePanel.currentMap][i];
                if (otherProjectile != this && gamePanel.checker.checkProjectileCollision(this, otherProjectile)) {
                    generateParticle(user.projectile, user.projectile);
                    generateParticle(otherProjectile.user.projectile, otherProjectile.user.projectile);
                    isAlive = false;
                    otherProjectile.isAlive = false;
                    break;
                }
            }
        }
    }

    private void explosionHittingObstacle() {
        collisionOn = false;
        Entity entity = gamePanel.checker.getCollidingEntity(this);
        if (collisionOn) {
            generateParticle(user.projectile, entity);
            isAlive = false;
        }
    }

    private void explosionHittingMonster() {
        if (user == gamePanel.player) {
            int monIndex = gamePanel.checker.checkEntity(this, gamePanel.mon);
            if (monIndex != 999) {
                gamePanel.player.damageMonster(monIndex, this, attack * (gamePanel.player.level / 2), knockPower);
                generateParticle(user.projectile, gamePanel.mon[gamePanel.currentMap][monIndex]);
                isAlive = false;
            }
        }
    }

    private void explosionHittingPlayer() {
        if (user != gamePanel.player) {
            boolean contactPlayer = gamePanel.checker.checkPlayer(this);
            if (!gamePanel.player.invincible && contactPlayer) {
                damagePlayer(attack);
                generateParticle(user.projectile, user.projectile);
                isAlive = false;
            }
        }
    }

    private void directChange() {
        switch (direct) {
            case "up", "stay_up" -> worldY -= speed;
            case "down", "stay" -> worldY += speed;
            case "left", "stay_left" -> worldX -= speed;
            case "right", "stay_right" -> worldX += speed;
            default -> throw new IllegalStateException("Unexpected value: " + direct);
        }
    }

    private void lifeSpan() {
        life--;
        if (life <= 0) {
            isAlive = false;
        }
    }

    public boolean haveRes(Entity user) {
        return false;
    }

    public void subtractRes(Entity user) {

    }
}
