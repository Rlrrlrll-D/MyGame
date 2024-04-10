package tile.interactive;

import entity.Entity;
import main.GamePanel;
import objects.Pickaxe;

import java.awt.image.BufferedImage;

public class DestructibleWall extends InteractiveTile {
    GamePanel gamePanel;

    public DestructibleWall(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.gamePanel = gamePanel;

        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;

        down1 = setup("/tile/pictures/destructible_wall", gamePanel.tileSize, gamePanel.tileSize);
        destructible = true;
        life = 3;
    }

    public boolean isCorrectItem(Entity entity) {
        return entity.currentWeapon instanceof Pickaxe;
    }

    public void playSnd() {
        gamePanel.playSFX(23);
    }

    public InteractiveTile getDestroyForm() {
        return super.getDestroyForm();
    }

    public int getParticleSize() {
        return 15;
    }

    public int getParticleSpeed() {
        return 1;
    }

    public int getParticleMaxLife() {
        return 20;
    }

    public BufferedImage getParticleImg() {
        return this.down1;
    }
}
