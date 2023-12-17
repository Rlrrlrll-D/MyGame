package tile.interactive;

import entity.Entity;
import main.GamePanel;
import objects.Axe;

import java.awt.image.BufferedImage;

public class DryTree extends InteractiveTile {
    GamePanel gamePanel;

    public DryTree(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.gamePanel = gamePanel;

        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;


        down1 = setup("/res/tiles/dry_tree", gamePanel.tileSize, gamePanel.tileSize);
        destructible = true;
        life = 3;
    }

    public boolean isCorrectItem(Entity entity) {
        return entity.currentWeapon instanceof Axe;
    }

    public void playSnd() {
        gamePanel.playSFX(15);
    }

    public InteractiveTile getDestroyForm() {
        return new Trunk(gamePanel, worldX / gamePanel.tileSize, worldY / gamePanel.tileSize);
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
