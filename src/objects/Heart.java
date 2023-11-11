package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Heart extends MotherObject {
    GamePanel gamePanel;

    public Heart(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Heart";
        try {

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/heart_empty.png")));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
            image1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/heart_half.png")));
            image1 = utilityTool.scaleImage(image1, gamePanel.tileSize, gamePanel.tileSize);
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/heart_full.png")));
            image2 = utilityTool.scaleImage(image2, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }
}
