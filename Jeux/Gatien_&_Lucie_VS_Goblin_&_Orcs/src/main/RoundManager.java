package main;

import java.awt.*;

public class RoundManager {
    private int currentRound = 1;
    private boolean showMessage = false;
    private long messageStartTime;
    private final int displayDuration = 2000; // en millisecondes (2s)

    public void nextRound() {
        currentRound++;
        showRoundMessage();
    }

    public void showRoundMessage() {
        showMessage = true;
        messageStartTime = System.currentTimeMillis();
    }

    public void draw(Graphics2D g2, int screenWidth) {
        if (showMessage) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - messageStartTime > displayDuration) {
                showMessage = false;
                return;
            }

            String text = "Round " + currentRound;
            g2.setFont(new Font("Arial", Font.BOLD, 48));
            FontMetrics fm = g2.getFontMetrics();
            int stringWidth = fm.stringWidth(text);
            int stringHeight = fm.getHeight();

            int paddingX = 30;
            int paddingY = 20;

            int boxWidth = stringWidth + paddingX * 2;
            int boxHeight = stringHeight + paddingY * 2;

            int x = (screenWidth - boxWidth) / 2;
            int y = 80; // position y du rectangle (tu peux ajuster)

            // Dessiner le fond semi-transparent avec coins arrondis
            Color backgroundColor = new Color(0, 0, 0, 180); // noir avec transparence
            g2.setColor(backgroundColor);
            g2.fillRoundRect(x, y, boxWidth, boxHeight, 30, 30);

            // Dessiner le contour jaune du rectangle
            g2.setColor(Color.YELLOW);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(x, y, boxWidth, boxHeight, 30, 30);

            // Dessiner le texte au centre du rectangle
            int textX = x + paddingX;
            int textY = y + paddingY + fm.getAscent();

            g2.drawString(text, textX, textY);
        }
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void resetRounds() {
        currentRound = 1;
        showMessage = false;
    }
}