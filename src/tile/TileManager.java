package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;
    int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        getTileImage();
        try {
            loadMap("/maps/map01.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    public void loadMap(String filePath) throws Exception {
        InputStream inputStream = getClass().getResourceAsStream(filePath);
        assert inputStream != null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        for (int row = 0; row < gamePanel.maxScreenRow; row++) {
            String string = bufferedReader.readLine();
            for (int col = 0; col < gamePanel.maxScreenCol; col++) {

                String[] numbers = string.split(" ");
                int num = Integer.parseInt(numbers[col]);
                mapTileNum[col][row] = num;
            }
        }
        bufferedReader.close();
    }

    public void drawing(Graphics2D graphics2D) {
        for (int row = 0; row < gamePanel.maxScreenRow; row++) {
            for (int col = 0; col < gamePanel.maxScreenCol; col++) {
                int tNum = mapTileNum[col][row];
                graphics2D.drawImage(tiles[tNum].image, col * gamePanel.tileSize,
                        row * gamePanel.tileSize, gamePanel.tileSize, gamePanel.tileSize, null);
            }

        }

    }
}
