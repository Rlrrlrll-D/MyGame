package objects;

import entity.Entity;
import main.GamePanel;

public class Key extends Entity {


    public Key(GamePanel gamePanel) {
        super(gamePanel);

        name = "Key";
        down1 = setup("/res/objects/key");

    }

}
