package objects;

import main.GamePanel;

public class DoorIron extends Obstacle {
    public static final String objName = "DoorIron";
    GamePanel gamePanel;

    public DoorIron(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = objName;
        down1 = setup("/res/objects/door_iron", gamePanel.tileSize, gamePanel.tileSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "It won't budge.";
    }

    @Override
    public void interact() {
        startDialog(this, 0);
    }
}
