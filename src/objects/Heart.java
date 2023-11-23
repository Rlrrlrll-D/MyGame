package objects;

import entity.Entity;
import main.GamePanel;

public class Heart extends Entity {


    public Heart(GamePanel gamePanel) {
        super(gamePanel);
        name = "Heart";
        image = setup("/res/objects/heart_empty", gamePanel.tileSize, gamePanel.tileSize);
        image1 = setup("/res/objects/heart_half", gamePanel.tileSize, gamePanel.tileSize);
        image2 = setup("/res/objects/heart_full", gamePanel.tileSize, gamePanel.tileSize);


    }
}
