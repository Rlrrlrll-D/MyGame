package objects;

import entity.Entity;
import main.GamePanel;

public class ManaCrystal extends Entity {
    GamePanel gamePanel;

    public ManaCrystal(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Mana Crystal";
        image = setup("/res/objects/mana_full", (int) (gamePanel.tileSize / 1.25), (int) (gamePanel.tileSize / 1.25));
        image1 = setup("/res/objects/mana_empty", (int) (gamePanel.tileSize / 1.25), (int) (gamePanel.tileSize / 1.25));

    }
}
