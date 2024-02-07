package objects;

import main.GamePanel;

public class Lantern extends Light {
    public static final String objName ="Lantern";
    public Lantern(GamePanel gamePanel) {
        super(gamePanel);
        name = objName;
        down1 = setup("/res/objects/lantern", gamePanel.tileSize, gamePanel.tileSize);
        description = "[Lantern]\nIlluminates your \nsurroundings.";
        price = 200;
        lightRadius = 230;
    }
}
