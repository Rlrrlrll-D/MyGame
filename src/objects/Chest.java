package objects;

import entity.Entity;
import main.GamePanel;

public class Chest extends Obstacle {
    GamePanel gamePanel;
    public static final String objName ="Chest";

    public Chest(GamePanel gamePanel) {
        super(gamePanel);
        name = objName;
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

    public void setLoot(Entity loot) {
        this.loot = loot;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You open the chest and find a " + loot.name + "\n...But you cannot carry and more!";
        dialogues[1][0] = "You open the chest and find a " + loot.name + "\nYou obtain the " + loot.name + "!";
        dialogues[2][0] = "It's empty";
    }

    public void interact() {

        if (!opened) {
            gamePanel.playSFX(3);

            if (!gamePanel.player.canObtainItem(loot)) {
                startDialog(this, 0);
            } else {
                startDialog(this, 1);
                down1 = image1;
                opened = true;
            }
        } else {
            startDialog(this, 2);
        }
    }
}
