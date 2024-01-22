package objects;

import entity.Entity;
import main.GamePanel;

public class Chest extends Obstacle {
    GamePanel gamePanel;

    public Chest(GamePanel gamePanel) {
        super(gamePanel);
        name = "Chest";
        this.gamePanel = gamePanel;


        collision = true;
        image = setup("/res/objects/chest", gamePanel.tileSize, gamePanel.tileSize);
        image1 = setup("/res/objects/chest_opened", gamePanel.tileSize, gamePanel.tileSize);
        down1 = image;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void  setLoot(Entity loot){
        this.loot = loot;
    }

    public void interact() {
        gamePanel.gameBehavior = GamePanel.dialogBehavior;
        if (!opened) {
            gamePanel.playSFX(3);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("You open the chest and find a ").append(loot.name).append("!");
            if (!gamePanel.player.canObtainItem(loot)) {
                stringBuilder.append("\n...But you cannot carry and more!");
            } else {
                stringBuilder.append("\nYou obtain the ").append(loot.name).append("!");
                down1 = image1;
                opened = true;
            }
            gamePanel.ui.dialogue = stringBuilder.toString();
        } else {
            gamePanel.ui.dialogue = "It's empty";
        }
    }
}
