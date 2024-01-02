package objects;


import entity.Entity;
import main.GamePanel;

public class Sword extends Entity {
    public Sword(GamePanel gamePanel) {
        super(gamePanel);
        name = "Sword";
        price = 200;
        down1 = setup("/res/objects/sword", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 1;
        knockPower=2;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn old sword.";

    }
}
