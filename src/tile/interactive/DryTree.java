package tile.interactive;

import main.GamePanel;

public class DryTree extends InteractiveTile {
    GamePanel gamePanel;

    public DryTree(GamePanel gamePanel, int col, int row) {
        super(gamePanel, col, row);
        this.gamePanel = gamePanel;

        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;

        down1 = setup("/res/tiles_interactive/dry_tree", gamePanel.tileSize, gamePanel.tileSize);
        destructible = true;

    }
}
