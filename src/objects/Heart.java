package objects;

import entity.Entity;
import main.GamePanel;

public class Heart extends PickUpOnlyItems {

    GamePanel gamePanel;

    public Heart(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Heart";
        value = 2;

        down1 = setup("/res/objects/heart_full", gamePanel.tileSize, gamePanel.tileSize);
        image = setup("/res/objects/heart_empty", gamePanel.tileSize, gamePanel.tileSize);
        image1 = setup("/res/objects/heart_half", gamePanel.tileSize, gamePanel.tileSize);
        image2 = setup("/res/objects/heart_full", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void use(Entity entity) {
        gamePanel.playSFX(4);
        gamePanel.ui.addMsg("Life +" + value);
        entity.life += value;

    }
}
