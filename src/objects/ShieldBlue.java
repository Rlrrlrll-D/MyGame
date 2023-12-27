package objects;

import entity.Entity;
import main.GamePanel;

public class ShieldBlue extends Entity {
    public ShieldBlue(GamePanel gamePanel) {
        super(gamePanel);
        //direct = "down";
        name = "ShieldBlue";
        price = 250;
        down1 = setup("/res/objects/shield_blue", gamePanel.tileSize, gamePanel.tileSize);
        defenceValue = 2;
        description = "[" + name + "]\nA shiny blue shield.";
    }
}
