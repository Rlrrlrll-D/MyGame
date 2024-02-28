package objects;

import entity.Entity;
import main.GamePanel;

public class Pickaxe extends Entity {
    public static final String objName = "Pickaxe";

    public Pickaxe(GamePanel gamePanel) {
        super(gamePanel);

        name = objName;
        down1 = setup("/res/objects/pickaxe", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 2;
        motionDelay1 = 5;
        motionDelay2 = 10;
        motionDelay3 = 20;
        attackArea.width = 30;
        attackArea.height = 30;
        price = 75;
        description = "[Pickaxe]\nYou will dig it!";
    }
}
