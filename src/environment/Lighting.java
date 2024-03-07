package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighting {
    public final int day = 0;
    public final int dusk = 1;
    public final int night = 2;
    public final int dawn = 3;
    public float filterAlpha;
    public int dayState = day;
    public int dayCounter;
    GamePanel gamePanel;
    BufferedImage darknessFilter;

    public Lighting(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setLightSource();
    }

    private void setLightSource() {
        darknessFilter = new BufferedImage(gamePanel.screenWidth, gamePanel.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = (Graphics2D) darknessFilter.getGraphics();

        if (gamePanel.player.currentLight == null) {
            graphics2D.setColor(new Color(0, 0, 0.1f, 0.98f));

        } else {
            int centerX = gamePanel.player.screenX + (gamePanel.tileSize) / 2;
            int centerY = gamePanel.player.screenY + (gamePanel.tileSize) / 2;

            Color[] color = new Color[12];
            float[] fraction = new float[12];

            color[0] = new Color(0, 0, 0.1f, 0.1f);
            color[1] = new Color(0, 0, 0.1f, 0.42f);
            color[2] = new Color(0, 0, 0.1f, 0.52f);
            color[3] = new Color(0, 0, 0.1f, 0.61f);
            color[4] = new Color(0, 0, 0.1f, 0.69f);
            color[5] = new Color(0, 0, 0.1f, 0.76f);
            color[6] = new Color(0, 0, 0.1f, 0.82f);
            color[7] = new Color(0, 0, 0.1f, 0.87f);
            color[8] = new Color(0, 0, 0.1f, 0.91f);
            color[9] = new Color(0, 0, 0.1f, 0.94f);
            color[10] = new Color(0, 0, 0.1f, 0.96f);
            color[11] = new Color(0, 0, 0.1f, 0.98f);

            fraction[0] = 0f;
            fraction[1] = 0.4f;
            fraction[2] = 0.5f;
            fraction[3] = 0.6f;
            fraction[4] = 0.65f;
            fraction[5] = 0.7f;
            fraction[6] = 0.75f;
            fraction[7] = 0.8f;
            fraction[8] = 0.85f;
            fraction[9] = 0.9f;
            fraction[10] = 0.95f;
            fraction[11] = 1f;

            RadialGradientPaint radialGradientPaint = new RadialGradientPaint(centerX, centerY, gamePanel.player.currentLight.lightRadius, fraction, color);
            graphics2D.setPaint(radialGradientPaint);
        }
        graphics2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        graphics2D.dispose();
    }

    public void resetDay() {
        dayState = day;
        filterAlpha = 0f;
    }

    public void update() {
        if (gamePanel.player.lightUp) {
            setLightSource();
            gamePanel.player.lightUp = false;
        }
        if (dayState == day) {
            dayCounter++;
            if (dayCounter > 1200) {
                dayState = dusk;
                dayCounter = 0;
            }
        }
        if (dayState == dusk) {
            filterAlpha += 0.001f;
            if (filterAlpha > 1f) {
                filterAlpha = 1f;
                dayState = night;
            }
        }
        if (dayState == night) {
            dayCounter++;
            if (dayCounter > 1200) {
                dayState = dawn;
                dayCounter = 0;
            }
        }
        if (dayState == dawn) {
            filterAlpha -= 0.001f;
            if (filterAlpha < 0) {
                filterAlpha = 0;
                dayState = day;
            }
        }
    }

    public void drawing(Graphics2D graphics2D) {

        if (gamePanel.currentArea == gamePanel.outside) {
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        }
        if (gamePanel.currentArea == gamePanel.outside || gamePanel.currentArea == gamePanel.dungeon) {
            graphics2D.drawImage(darknessFilter, 0, 0, null);
        }

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        var situation = switch (dayState) {
            case day -> "Day";
            case dusk -> "Dusk";
            case night -> "Night";
            case dawn -> "Dawn";
            default -> "";
        };

        if (gamePanel.gameBehavior != GamePanel.pauseBehavior) {
            graphics2D.setColor(Color.white);
            graphics2D.setFont(graphics2D.getFont().deriveFont(45f));
            graphics2D.drawString(situation, 800, 500);
        }
    }
}
