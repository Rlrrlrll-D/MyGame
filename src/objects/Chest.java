package objects;

import entity.Entity;
import main.GamePanel;

public class Chest extends Entity {

    public Chest(GamePanel gamePanel) {
        super(gamePanel);
        name = "Chest";
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        collision = true;
        down1 = setup("/res/objects/chest");


    }

}
