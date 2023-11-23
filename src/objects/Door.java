package objects;

import entity.Entity;
import main.GamePanel;

public class Door extends Entity {

    public Door(GamePanel gamePanel) {
        super(gamePanel);
        name = "Door";
        down1 = setup("/res/objects/door", gamePanel.tileSize, gamePanel.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
