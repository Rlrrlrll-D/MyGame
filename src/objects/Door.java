package objects;

import main.GamePanel;

public class Door extends Obstacle {
    GamePanel gamePanel;
    public Door(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Door";
        down1 = setup("/res/objects/door", gamePanel.tileSize, gamePanel.tileSize);
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
        dialogues[0][0] = "You need a key to open this.";
    }

    @Override
    public void interact() {
        startDialog(this, 0);

    }
}
