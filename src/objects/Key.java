package objects;

import entity.Entity;
import main.GamePanel;

public class Key extends Entity {


    public Key(GamePanel gamePanel) {
        super(gamePanel);

        name = "Key";
        down1 = setup("/res/objects/key", gamePanel.tileSize, gamePanel.tileSize);
        collision=true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

}
