package objects;

import entity.Entity;
import main.GamePanel;

public class PotionRed extends Consumable {
    GamePanel gamePanel;
    int value = 5;

    public PotionRed(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = "Red Potion";
        down1 = setup("/res/objects/potion_red", gamePanel.tileSize, gamePanel.tileSize);
        description = "[Red Potion]\nHeals your life by  " + value;
    }

    public void use(Entity entity) {
        gamePanel.gameBehavior = GamePanel.dialogBehavior;
        gamePanel.ui.dialogue = "You drink the " + name + "!\n" + "Your life has been recovered by " + value + ".";
        entity.life += value;
        if (gamePanel.player.life > gamePanel.player.maxLife) {
            gamePanel.player.life = gamePanel.player.maxLife;
        }
        gamePanel.playSFX(10);
    }
}
