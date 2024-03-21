package entity;

import main.GamePanel;

public class PlayerDummy extends Entity{
    public static final String npcName = "PlayerDummy";
    public PlayerDummy(GamePanel gamePanel) {
        super(gamePanel);
        name = npcName;
        getImg();
    }
    public void getImg() {

        stay1 = setup("/res/player/stay1", gamePanel.tileSize, gamePanel.tileSize);
        stay2 = setup("/res/player/stay2", gamePanel.tileSize, gamePanel.tileSize);
        stay3 = setup("/res/player/stay3", gamePanel.tileSize, gamePanel.tileSize);
        stay_up1 = setup("/res/player/stay_up1", gamePanel.tileSize, gamePanel.tileSize);
        stay_up2 = setup("/res/player/stay_up2", gamePanel.tileSize, gamePanel.tileSize);
        stay_up3 = setup("/res/player/stay_up3", gamePanel.tileSize, gamePanel.tileSize);
        stay_left1 = setup("/res/player/stay_left1", gamePanel.tileSize, gamePanel.tileSize);
        stay_left2 = setup("/res/player/stay_left2", gamePanel.tileSize, gamePanel.tileSize);
        stay_left3 = setup("/res/player/stay_left3", gamePanel.tileSize, gamePanel.tileSize);
        stay_right1 = setup("/res/player/stay_right1", gamePanel.tileSize, gamePanel.tileSize);
        stay_right2 = setup("/res/player/stay_right2", gamePanel.tileSize, gamePanel.tileSize);
        stay_right3 = setup("/res/player/stay_right3", gamePanel.tileSize, gamePanel.tileSize);
        up1 = setup("/res/player/me_up1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/res/player/me_up2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/res/player/me_down1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/res/player/me_down2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/res/player/me_left1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/res/player/me_left2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/res/player/me_right1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/res/player/me_right2", gamePanel.tileSize, gamePanel.tileSize);
    }
}
