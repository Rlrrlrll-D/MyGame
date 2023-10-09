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
    public Tile[] tiles;
    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        try {
            loadMap("/maps/world01.txt");
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
            tiles[1].collision = true;

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
            tiles[4].collision = true;

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));


        } catch (IOException e) {
            e.getLocalizedMessage();
        }

    }

    public void loadMap(String filePath) throws Exception {
        InputStream inputStream = getClass().getResourceAsStream(filePath);
        assert inputStream != null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        for (int row = 0; row < gamePanel.maxWorldRow; row++) {
            String string = bufferedReader.readLine();
            for (int col = 0; col < gamePanel.maxWorldCol; col++) {

                String[] numbers = string.split(" ");
                int num = Integer.parseInt(numbers[col]);
                mapTileNum[col][row] = num;
            }
        }
        bufferedReader.close();
    }

    public void drawing(Graphics2D graphics2D) {
        for (int worldRow = 0; worldRow < gamePanel.maxWorldRow; worldRow++) {
            for (int worldCol = 0; worldCol < gamePanel.maxWorldCol; worldCol++) {
                int tNum = mapTileNum[worldCol][worldRow];
                int wrdX = worldCol * gamePanel.tileSize;
                int wrdY = worldRow * gamePanel.tileSize;
                int scrX = wrdX - gamePanel.player.worldX + gamePanel.player.screenX;
                int scrY = wrdY - gamePanel.player.worldY + gamePanel.player.screenY;
                if (wrdX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                        wrdX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                        wrdY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                        wrdY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
                    graphics2D.drawImage(tiles[tNum].image, scrX,
                            scrY, gamePanel.tileSize, gamePanel.tileSize, null);
                }
            }

        }

    }
}
