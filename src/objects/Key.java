package objects;

import entity.Entity;
import main.GamePanel;

public class Key extends Consumable {

    GamePanel gamePanel;

    public Key(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Key";
        collision = true;

        price = 100;
        description = "[" + name + "]\nIt opens a door.";
        down1 = setup("/res/objects/key", gamePanel.tileSize, gamePanel.tileSize);

    }

    @Override
    public void use(Entity entity) {
        gamePanel.gameBehavior = GamePanel.dialogBehavior;
        int objIndex = getDetected(entity, gamePanel.objects, "Door");

        if (objIndex != 999) {
            gamePanel.ui.dialogue = "You use the " + name + " and open the door.";
            gamePanel.playSFX(2);
            gamePanel.objects[gamePanel.currentMap][objIndex] = null;
        } else {
            gamePanel.ui.dialogue = "What are you doing?";
        }
    }
}
