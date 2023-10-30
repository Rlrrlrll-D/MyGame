package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Door extends MotherObject {
    GamePanel gamePanel;

    public Door(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Door";
        try {

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/door.png")));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
        collision = true;
    }

}
