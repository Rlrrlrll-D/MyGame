package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Key extends MotherObject {
    GamePanel gamePanel;

    public Key(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Key";
        try {

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/key.png")));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }

}
