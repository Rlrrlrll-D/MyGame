package objects;

import entity.Entity;
import main.GamePanel;

public class ManaCrystal extends PickUpOnlyItems {
    GamePanel gamePanel;

    public ManaCrystal(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Mana Crystal";
        price = 275;
        value = 1;
        down1 = setup("/res/objects/mana_full", (int) (gamePanel.tileSize / 1.25), (int) (gamePanel.tileSize / 1.25));
        image = setup("/res/objects/mana_full", (int) (gamePanel.tileSize / 1.25), (int) (gamePanel.tileSize / 1.25));
        image1 = setup("/res/objects/mana_empty", (int) (gamePanel.tileSize / 1.25), (int) (gamePanel.tileSize / 1.25));

    }

    public boolean use(Entity entity) {
        gamePanel.playSFX(4);
        gamePanel.ui.addMsg("Mana +" + value);
        entity.mana += value;
        return true;
    }
}
