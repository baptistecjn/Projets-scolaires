package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CharacterSelectionPanel extends JPanel {
    private BufferedImage[] charImages;

    private JFrame parentFrame;
    private String[] characters = {"Gatien", "Lucie"};
    private Rectangle[] charBounds;
    private int hoveredIndex = -1;

    public CharacterSelectionPanel(JFrame frame) {
        charImages = new BufferedImage[characters.length];

        try {
            charImages[0] = ImageIO.read(getClass().getResource("/player/chevalier_droite.png"));
            charImages[1] = ImageIO.read(getClass().getResource("/player/mage_droite.png"));
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer erreur ici
        }
        this.parentFrame = frame;
        this.setPreferredSize(new Dimension(800, 450));
        this.setBackground(new Color(30, 30, 60)); // bleu foncé nuit

        charBounds = new Rectangle[characters.length];
        int startX = 160;
        int startY = 170;
        int width = 140;
        int height = 180;
        int gap = 200;

        for (int i = 0; i < characters.length; i++) {
            charBounds[i] = new Rectangle(startX + i * (width + gap), startY, width, height);
        }

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                for (int i = 0; i < charBounds.length; i++) {
                    if (charBounds[i].contains(p)) {
                        String selectedChar = characters[i];
                        System.out.println("Personnage sélectionné : " + selectedChar);
                        parentFrame.dispose();
                        launchGame(selectedChar);
                        break;
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoveredIndex = -1;
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point p = e.getPoint();
                int oldHover = hoveredIndex;
                hoveredIndex = -1;
                for (int i = 0; i < charBounds.length; i++) {
                    if (charBounds[i].contains(p)) {
                        hoveredIndex = i;
                        break;
                    }
                }
                if (hoveredIndex != oldHover) {
                    repaint();
                }
            }
        });
    }

    private void launchGame(String characterName) {
        JFrame gameWindow = new JFrame();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("ESIR1 - Projet Prog - Joueur: " + characterName);

        GamePanel gamePanel = new GamePanel(characterName);
        gameWindow.add(gamePanel);
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
        gamePanel.startGameThread();

        music.SoundPlayer music = new music.SoundPlayer("/musique/background_music.wav");
        music.loop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        // Fond dégradé vertical du panel
        GradientPaint gp = new GradientPaint(0, 0, new Color(40, 40, 90), 0, getHeight(), new Color(10, 10, 30));
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Titre centré en haut
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Segoe UI", Font.BOLD, 28));
        String title = "Choisissez votre personnage";
        int titleWidth = g2.getFontMetrics().stringWidth(title);
        g2.drawString(title, (getWidth() - titleWidth) / 2, 70);

        // Police pour les noms
        g2.setFont(new Font("Segoe UI", Font.BOLD, 20));

        // Paramètre pour l'image à l'intérieur du rectangle (padding)
        int padding = 12;

        for (int i = 0; i < characters.length; i++) {
            Rectangle r = charBounds[i];

            // Dessiner le fond arrondi (couleur ou dégradé si hover)
            if (i == hoveredIndex) {
                GradientPaint hoverGrad = new GradientPaint(
                        r.x, r.y, new Color(100, 180, 255, 200),
                        r.x, r.y + r.height, new Color(30, 120, 220, 180));
                g2.setPaint(hoverGrad);
                g2.fillRoundRect(r.x, r.y, r.width, r.height, 30, 30);
            } else {
                g2.setColor(new Color(70, 70, 90));
                g2.fillRoundRect(r.x, r.y, r.width, r.height, 25, 25);
            }

            // Dessiner l'image à l'intérieur du rectangle (avec padding)
            if (charImages[i] != null) {
                int imgX = r.x + padding;
                int imgY = r.y + padding;
                int imgWidth = r.width - 2 * padding;
                int imgHeight = r.height - 2 * padding;
                g2.drawImage(charImages[i], imgX, imgY, imgWidth, imgHeight, null);
            }

            // Dessiner la bordure arrondie par-dessus
            if (i == hoveredIndex) {
                g2.setStroke(new BasicStroke(4));
                g2.setColor(new Color(180, 220, 255));
                g2.drawRoundRect(r.x, r.y, r.width, r.height, 30, 30);
            } else {
                g2.setStroke(new BasicStroke(2));
                g2.setColor(new Color(120, 120, 140));
                g2.drawRoundRect(r.x, r.y, r.width, r.height, 25, 25);
            }

            // Dessiner le nom centré en dessous du rectangle
            String name = characters[i];
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(name);
            int textX = r.x + (r.width - textWidth) / 2;
            int textY = r.y + r.height + 30;

            // Ombre légère sous le texte pour relief
            g2.setColor(new Color(0, 0, 0, 100));
            g2.drawString(name, textX + 1, textY + 1);

            // Texte principal blanc
            g2.setColor(Color.WHITE);
            g2.drawString(name, textX, textY);
        }

        g2.dispose();
    }
}