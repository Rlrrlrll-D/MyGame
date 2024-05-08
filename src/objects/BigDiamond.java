package objects;

import entity.Entity;
import main.GamePanel;

public class BigDiamond extends PickUpOnlyItems {
    public static final String objName = "BigDiamond";
    GamePanel gamePanel;

    public BigDiamond(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.name = objName;
        down1 = setup("/res/objects/BigDiamond", 64, 64);
        setDialogues();
    }

    private void setDialogues() {
        dialogues[0][0] = "You found a Big Diamond!";
        dialogues[0][1] = "It's the legendary treasure!";
    }

    @Override
    public boolean use(Entity entity) {
        gamePanel.gameBehavior = GamePanel.cutSceneBehavior;
        gamePanel.cutSceneManager.scene = gamePanel.cutSceneManager.end;
        return true;
    }
}