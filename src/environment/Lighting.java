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
        graphics2D.setColor(new Color(0, 0, 0.1f, 0.97f));
    } else {
        int centerX = gamePanel.player.screenX + (gamePanel.tileSize) / 2;
        int centerY = gamePanel.player.screenY + (gamePanel.tileSize) / 2;

        RadialGradientPaint radialGradientPaint = getRadialGradientPaint(centerX, centerY);
        graphics2D.setPaint(radialGradientPaint);
    }
    graphics2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
    graphics2D.dispose();
}

    private RadialGradientPaint getRadialGradientPaint(int centerX, int centerY) {
        Color[] color = {
            new Color(0, 0, 0.1f, 0.1f),
            new Color(0, 0, 0.1f, 0.42f),
            new Color(0, 0, 0.1f, 0.52f),
            new Color(0, 0, 0.1f, 0.61f),
            new Color(0, 0, 0.1f, 0.69f),
            new Color(0, 0, 0.1f, 0.76f),
            new Color(0, 0, 0.1f, 0.82f),
            new Color(0, 0, 0.1f, 0.87f),
            new Color(0, 0, 0.1f, 0.91f),
            new Color(0, 0, 0.1f, 0.92f),
            new Color(0, 0, 0.1f, 0.93f),
            new Color(0, 0, 0.1f, 0.94f)
        };

        float[] fraction = {0f, 0.4f, 0.5f, 0.6f, 0.65f, 0.7f, 0.75f, 0.8f, 0.85f, 0.9f, 0.95f, 1f};

        return new RadialGradientPaint(centerX, centerY, gamePanel.player.currentLight.lightRadius, fraction, color);
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

        switch (dayState) {
            case day:
                updateDayState(dusk, 1800);
                break;
            case dusk:
            filterAlpha += 0.001f;
                filterAlphaChange();
                break;
            case night:
                updateDayState(dawn, 1200);
                break;
            case dawn:
            filterAlpha -= 0.001f;
                filterAlphaChange();
                break;
        }
    }

    private void filterAlphaChange() {
        if (filterAlpha > 1f) {
            filterAlpha = 1f;
            dayState = night;
        } else if (filterAlpha < 0) {
            filterAlpha = 0;
            dayState = day;
        }
    }

    private void updateDayState(int nextState, int counterLimit) {
        dayCounter++;
        if (dayCounter > counterLimit) {
            dayState = nextState;
            dayCounter = 0;
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

    }
}
