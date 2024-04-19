package tile.interactive;

import entity.Entity;
import main.GamePanel;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;
import objects.Pickaxe;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class DestructibleWall extends InteractiveTile {
    GamePanel gamePanel;
    private final Map<Class<? extends Entity>, Integer> dropRates;
    private final Random random = new Random();

    public DestructibleWall(GamePanel gamePanel, int col, int row) {
        super(gamePanel);

        dropRates = new LinkedHashMap<>(); // Maintain insertion order
        dropRates.put(null, 60); // 60% chance to drop nothing
        dropRates.put(CoinBronze.class, 27); // 27% chance to drop Heart
        dropRates.put(Heart.class, 9); //9% chance to drop ManaCrystal
        dropRates.put(ManaCrystal.class, 4); // 4% chance to drop Pickaxe

        this.gamePanel = gamePanel;

        this.worldX = gamePanel.tileSize * col;
        this.worldY = gamePanel.tileSize * row;

        down1 = setup("/tile/pictures/destructible_wall", gamePanel.tileSize, gamePanel.tileSize);
        destructible = true;
        life = 3;
    }

    public boolean isCorrectItem(Entity entity) {
        return entity.currentWeapon instanceof Pickaxe;
    }

    public void playSnd() {
        gamePanel.playSFX(23);
    }

    public InteractiveTile getDestroyForm() {
        return super.getDestroyForm();
    }

    public int getParticleSize() {
        return 15;
    }

    public int getParticleSpeed() {
        return 1;
    }

    public int getParticleMaxLife() {
        return 20;
    }

    public BufferedImage getParticleImg() {
        return this.down1;
    }

    public void checkDrop() {
        int rand = random.nextInt(100) + 1;
        int accumulatedProbability = 0;
        for (Map.Entry<Class<? extends Entity>, Integer> entry : dropRates.entrySet()) {
            accumulatedProbability += entry.getValue();
            if (rand <= accumulatedProbability) {
                Entity item = null;
                if (entry.getKey() != null) {
                    try {
                        item = entry.getKey().getConstructor(GamePanel.class).newInstance(gamePanel);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                dropItem(item);
                break;
            }
        }
    }
}
