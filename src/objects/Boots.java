package objects;

import main.GamePanel;

public class Boots extends Obstacle {
    public static final String objName = "Boots";

    public Boots(GamePanel gamePanel) {
        super(gamePanel);
        name = objName;
        down1 = setup("/res/objects/boots", gamePanel.tileSize, gamePanel.tileSize);

    }
}
