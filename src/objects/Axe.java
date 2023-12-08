package objects;

import entity.Entity;
import main.GamePanel;

public class Axe extends Entity {
    public Axe(GamePanel gamePanel) {
        super(gamePanel);

        name = "Woodcutter's Axe";
        direct = "down";
        down1 = setup("/res/objects/axe", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[Woodcutter's Axe]\nA bit rusty but still \ncan cut some trees.";
    }
}
