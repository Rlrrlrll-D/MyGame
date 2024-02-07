package objects;

import entity.Entity;
import main.GamePanel;

public class Tent extends Consumable {
    public static final String objName ="Tent";
    GamePanel gamePanel;

    public Tent(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = objName;
        down1 = setup("/res/objects/tent", gamePanel.tileSize, gamePanel.tileSize);
        description = "[Tent]\nYou can sleep until\nnext morning.";
        price = 300;
        stackable = true;
    }

    public boolean use(Entity entity) {
        gamePanel.gameBehavior = GamePanel.sleepBehavior;
        gamePanel.playSFX(17);
        gamePanel.player.life = gamePanel.player.maxLife;
        gamePanel.player.mana = gamePanel.player.maxMana;
        gamePanel.player.getSleepingImg(down1);
        return true;

    }
}
