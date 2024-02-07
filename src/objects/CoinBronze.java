package objects;

import entity.Entity;
import main.GamePanel;

public class CoinBronze extends PickUpOnlyItems {
    public static final String objName ="Bronze Coin";
    GamePanel gamePanel;

    public CoinBronze(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        name = objName;
        value = 1;
        down1 = setup("/res/objects/coin_bronze", gamePanel.tileSize, gamePanel.tileSize);
    }

    public boolean use(Entity entity) {
        gamePanel.playSFX(1);
        gamePanel.ui.addMsg("Coin +" + value);
        gamePanel.player.coin += value;
        return true;
    }
}
