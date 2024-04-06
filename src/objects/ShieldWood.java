package objects;

import entity.Entity;
import main.GamePanel;

public class ShieldWood extends Entity {
    public static final String objName = "Shield";

    public ShieldWood(GamePanel gamePanel) {
        super(gamePanel);
        name = objName;
        price = 150;
        down1 = setup("/res/objects/shield", gamePanel.tileSize, gamePanel.tileSize);
        defenceValue = 1;
        description = "[" + name + "]\nMade by wood.";
    }
}
