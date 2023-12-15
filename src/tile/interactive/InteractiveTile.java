package tile.interactive;

import entity.Entity;
import main.GamePanel;


public class InteractiveTile extends Entity {
    public boolean destructible;
    GamePanel gamePanel;

    public InteractiveTile(GamePanel gamePanel, int col, int row) {
        super(gamePanel);
        this.gamePanel = gamePanel;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;
        return isCorrectItem;
    }

    public InteractiveTile getDestroyForm() {
        InteractiveTile tile = null;
        return tile;
    }

    public void playSnd() {

    }

    public void update() {

    }
}
