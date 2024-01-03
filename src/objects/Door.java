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
    }

    @Override
    public void interact() {
        gamePanel.gameBehavior = GamePanel.dialogBehavior;
        gamePanel.ui.dialogue = "You need a key to open this.";

    }
}
