package tile;

import main.GamePanel;
import main.UtilityTool;

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
    public int[][][] mapTileNum;
    boolean drawPath = true;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNum = new int[GamePanel.maxMap][GamePanel.maxWorldCol][GamePanel.maxWorldRow];
        getTileImage();
        try {
            loadMap("/maps/world01.txt", 0);
            loadMap("/maps/world02.txt", 1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getTileImage() {

        setup(0, "grass", false);
        setup(1, "water", true);
        setup(2, "wall", true);
        setup(3, "earth", false);
        setup(4, "tree", true);
        setup(5, "sand", false);
        setup(6, "trunk", false);
        setup(7, "hut", false);
        setup(8, "hut2", false);

    }
    public void setup(int index, String name, boolean collision) {

        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/" + name + ".png")));
            tiles[index].image = UtilityTool.scaleImage(tiles[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tiles[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) throws Exception {
        InputStream inputStream = getClass().getResourceAsStream(filePath);
        assert inputStream != null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        for (int row = 0; row < GamePanel.maxWorldRow; row++) {
            String string = bufferedReader.readLine();
            for (int col = 0; col < GamePanel.maxWorldCol; col++) {

                String[] numbers = string.split(" ");
                int num = Integer.parseInt(numbers[col]);
                mapTileNum[map][col][row] = num;
            }
        }
        bufferedReader.close();
    }
    public void drawing(Graphics2D graphics2D) {

        for (int worldRow = 0; worldRow < GamePanel.maxWorldRow; worldRow++) {
            for (int worldCol = 0; worldCol < GamePanel.maxWorldCol; worldCol++) {
                int tNum = mapTileNum[gamePanel.currentMap][worldCol][worldRow];
                int wrdX = worldCol * gamePanel.tileSize;
                int wrdY = worldRow * gamePanel.tileSize;
                int scrX = wrdX - gamePanel.player.worldX + gamePanel.player.screenX;
                int scrY = wrdY - gamePanel.player.worldY + gamePanel.player.screenY;
                if (wrdX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                        wrdX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                        wrdY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                        wrdY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
                    graphics2D.drawImage(tiles[tNum].image, scrX, scrY, null);

                }
                // graphics2D.setColor(new Color(150, 150, 150, 150));
                //graphics2D.drawRect( scrX, scrY, gamePanel.tileSize, gamePanel.tileSize);
                // graphics2D.drawString(worldCol + "," + worldRow, scrX + gamePanel.tileSize / 8, scrY + gamePanel.tileSize / 2);
            }
        }
        if (drawPath) {
            graphics2D.setColor(new Color(255, 0, 0, 70));
            for (int i = 0; i < gamePanel.pathFinder.pathList.size(); i++) {
                int wrdX = gamePanel.pathFinder.pathList.get(i).col * gamePanel.tileSize;
                int wrdY = gamePanel.pathFinder.pathList.get(i).row * gamePanel.tileSize;
                int scrX = wrdX - gamePanel.player.worldX + gamePanel.player.screenX;
                int scrY = wrdY - gamePanel.player.worldY + gamePanel.player.screenY;

                graphics2D.fillRect(scrX, scrY, gamePanel.tileSize, gamePanel.tileSize);

            }
        }
    }
}
