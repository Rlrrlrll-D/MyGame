package objects;

import main.GamePanel;

public class Boots extends Obstacle {


    public Boots(GamePanel gamePanel) {
        super(gamePanel);
        name = "Boots";
        down1 = setup("/res/objects/boots", gamePanel.tileSize, gamePanel.tileSize);

    }
}
