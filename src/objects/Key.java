package objects;

import entity.Entity;
import main.GamePanel;

public class Key extends Consumable {
    public static final String objName ="Key";
    GamePanel gamePanel;

    public Key(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = objName;
        collision = true;
        stackable = true;
        price = 100;
        description = "[" + name + "]\nIt opens a door.";
        down1 = setup("/res/objects/key", gamePanel.tileSize, gamePanel.tileSize);
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You use the " + name + " and open the door.";
        dialogues[1][0] = "What are you doing?";
    }

    @Override
    public boolean use(Entity entity) {

        int objIndex = getDetected(entity, gamePanel.objects, "Door");

        if (objIndex != 999) {
            startDialog(this, 0);
            gamePanel.playSFX(2);
            gamePanel.objects[gamePanel.currentMap][objIndex] = null;
            return true;
        } else {
            startDialog(this, 1);
            return false;
        }
    }
}
