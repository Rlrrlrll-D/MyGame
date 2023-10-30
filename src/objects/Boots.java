package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Boots extends MotherObject {
    GamePanel gamePanel;

    public Boots(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Boots";
        try {

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/boots.png")));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }
}
