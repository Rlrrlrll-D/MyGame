package objects;

import entity.Entity;
import main.GamePanel;

public class Key extends Entity {


    public Key(GamePanel gamePanel) {
        super(gamePanel);
        name = "Key";
        collision = true;
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        price = 100;
        description = "[" + name + "]\nIt opens a door.";
        down1 = setup("/res/objects/key", gamePanel.tileSize, gamePanel.tileSize);

    }

}
