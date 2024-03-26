package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class TileManager {
    public Tile[] tiles;
    public int[][][] mapTileNum;
    GamePanel gamePanel;
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(GamePanel gamePanel) throws Exception {
        this.gamePanel = gamePanel;
        InputStream inputStream = getClass().getResourceAsStream("/maps/tiles_data.txt");
        assert inputStream != null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            fileNames.add(line);
            collisionStatus.add(bufferedReader.readLine());
        }
        bufferedReader.close();

        tiles = new Tile[fileNames.size()];

        getTileImage();

        inputStream = getClass().getResourceAsStream("/maps/world01.txt");

        assert inputStream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line2 = reader.readLine();
        String[] maxTile = line2.split(" ");

        GamePanel.maxWorldCol = maxTile.length;
        GamePanel.maxWorldRow = maxTile.length;
        mapTileNum = new int[GamePanel.maxMap][GamePanel.maxWorldCol][GamePanel.maxWorldRow];

        reader.close();

        loadMap("/maps/world01.txt", 0);
        loadMap("/maps/world02.txt", 1);
        loadMap("/maps/dungeon01.txt", 2);
        loadMap("/maps/dungeon02.txt", 3);

    }

    private void getTileImage() {
        for (int i = 0; i < fileNames.size(); i++) {

            String filename;
            boolean collision;

            filename = fileNames.get(i);

            collision = collisionStatus.get(i).equals("true");
            setup(i, filename, collision);
        }
    }

    public void setup(int index, String name, boolean collision) {

        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/" + name)));
            tiles[index].image = UtilityTool.scaleImage(tiles[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tiles[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) throws Exception {
        var inputStream = getClass().getResourceAsStream(filePath);
        assert inputStream != null;
        var bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
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

        for (int worldRow = 0; worldRow < GamePanel.maxWorldRow; worldRow++){
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
            }
        }
    }
}
