package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));


        } catch (IOException e) {
            e.getLocalizedMessage();
        }

    }

    public void drawing(Graphics2D graphics2D) {
        for (int row = 0; row < gamePanel.maxScreenRow; row++) {
            for (int col = 0; col < gamePanel.maxScreenCol; col++) {
                graphics2D.drawImage(tiles[0].image, col * gamePanel.tileSize,
                        row * gamePanel.tileSize, gamePanel.tileSize, gamePanel.tileSize, null);
            }

        }

    }
}
