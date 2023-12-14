package objects;

import entity.Entity;
import main.GamePanel;

public class CoinBronze extends PickUpOnlyItems {

    GamePanel gamePanel;

    public CoinBronze(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        name = "Bronze Coin";
        value = 1;
        down1 = setup("/res/objects/coin_bronze", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void use(Entity entity) {
        gamePanel.playSFX(1);
        gamePanel.ui.addMsg("Coin +" + value);
        gamePanel.player.coin += value;
    }
}
