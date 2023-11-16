package objects;

import entity.Entity;
import main.GamePanel;

public class Heart extends Entity {


    public Heart(GamePanel gamePanel) {
        super(gamePanel);
        name = "Heart";
        image= setup("/res/objects/heart_empty");
        image1 = setup("/res/objects/heart_half");
        image2 = setup("/res/objects/heart_full");


    }
}
