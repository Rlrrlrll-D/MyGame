package objects;

import entity.Entity;
import main.GamePanel;

public class PotionRed extends Consumable {
    GamePanel gamePanel;
    public PotionRed(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Red Potion";
        stackable = true;
        price = 300;
        value = 5;
        down1 = setup("/res/objects/potion_red", gamePanel.tileSize, gamePanel.tileSize);
        description = "[Red Potion]\nHeals your life by  " + value;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You drink the " + name + "!\n" + "Your life has been recovered by " + value + ".";
    }

    public boolean use(Entity entity) {

        startDialog(this, 0);
        entity.life += value;
        gamePanel.playSFX(10);
        return true;
    }
}
