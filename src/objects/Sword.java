package objects;


import entity.Entity;
import main.GamePanel;

public class Sword extends Entity {
    public Sword(GamePanel gamePanel) {
        super(gamePanel);
        name = "Sword";
        down1 = setup("/res/objects/sword", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 1;
        description = "[" + name + "]\nAn old sword.";
    }
}
