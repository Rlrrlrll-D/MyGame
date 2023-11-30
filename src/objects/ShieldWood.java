package objects;

import entity.Entity;
import main.GamePanel;

public class ShieldWood extends Entity {
    public ShieldWood(GamePanel gamePanel) {
        super(gamePanel);
        name = "Shield";
        down1 = setup("/res/objects/shield", gamePanel.tileSize, gamePanel.tileSize);
        defenceValue = 1;
    }
}
