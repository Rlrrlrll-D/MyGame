package objects;

import entity.Entity;
import main.GamePanel;

import java.util.Timer;
import java.util.TimerTask;

public class Boots extends Consumable {
    private final Timer timer;
    public static final String objName = "Boots";
    GamePanel gamePanel;

    public Boots(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        name = objName;
        value = 3;
        price = 200;
        timer = new Timer();
        description = "[" + name + "]\nIncreases your speed by " + value;
        down1 = setup("/res/objects/boots", gamePanel.tileSize, gamePanel.tileSize);
        setDialogue();
    }

    private void setDialogue() {
        dialogues[0][0] = "You put on the " + name + "and feel a sudden burst of speed!";
    }

    public boolean use(Entity entity) {
        startDialog(this, 0);
        booster();
        return true;
    }

    private void booster() {
        gamePanel.player.speed += value;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gamePanel.player.speed = gamePanel.player.defaultSpeed;
                gamePanel.ui.addMsg("The effect of the " + name + " has worn off.");
            }
        }, 10000);
    }
}
