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
        invincible(20);
    }


}
