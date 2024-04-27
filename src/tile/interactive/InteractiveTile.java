package tile.interactive;

import entity.Entity;
import main.GamePanel;


public class InteractiveTile extends Entity {
    public boolean destructible;
    GamePanel gamePanel;

    public InteractiveTile(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
    }

    public boolean isCorrectItem(Entity entity) {
        return false;
    }

    public InteractiveTile getDestroyForm() {
        return null;
    }

    public void playSnd() {

    }

    public void update() {
        // Only update if the tile is visible on the screen
        if (isOnScreen()) {
        invincible(20);
    }
    }

    private boolean isOnScreen() {
        // Check if the tile's world coordinates are within the screen's bounds
        int screenWidth = gamePanel.getWidth();
        int screenHeight = gamePanel.getHeight();
        return worldX >= 0 && worldX <= screenWidth && worldY >= 0 && worldY <= screenHeight;
    }


}
