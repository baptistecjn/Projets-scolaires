package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;
import main.GamePanel;

/**
 * Définition du comportement d'un monstre dans le jeu.
 */
public class Monster extends Entity  implements Combatant{

    protected int PA;           // Points d'action
    protected int health;       // Points de vie
    protected String name;      // Nom du monstre
    protected boolean alive;    // Etat de vie
    private final int tileSize;
    private int damage;

    private int maxHealth;

    protected GamePanel m_gp;
    protected static final Random rand = new Random();

    /**
     * Constructeur du monstre.
     *
     * @param a_gp   GamePanel principal du jeu
     * @param PA     Points d'action du monstre
     * @param health Points de vie du monstre
     * @param name   Nom du monstre
     */
    public Monster(GamePanel a_gp, int PA, int health, String name, int damage) {
        this.m_gp = a_gp;
        this.PA = PA;
        this.health = health;
        this.maxHealth = health;
        this.name = name;
        this.alive = true;
        this.tileSize = m_gp.TILE_SIZE;
        this.damage = damage;


        setDefaultValues();
        loadMonsterImage();
    }
    @Override
    public boolean getisAlive() {
        return this.alive;  // ou this.isAlive selon ce que tu utilises
    }

    public String getName() {
        return name;
    }

    public int getTileCol() {
        return m_x / m_gp.TILE_SIZE;
    }

    public int getTileRow() {
        return m_y / m_gp.TILE_SIZE;
    }

    public int getX() {
        return m_x;
    }

    public int getY() {
        return m_y;
    }

    public int getHealth() {
        return health;
    }

    public int getPA() {
        return PA;
    }

    public boolean isAlive() {
        return alive;
    }

    /**
     * Inflige des dégâts au monstre.
     *
     * @param damage Points de dégâts à infliger
     */
    public void takeDamage(int damage) {
        if (!alive) return;

        health -= damage;
        if (health <= 0) {
            health = 0;
            alive = false;
        }
    }

    /**
     * Soigne le monstre.
     *
     * @param amount Montant des points de vie à restaurer
     */
    public void heal(int amount) {
        if (!alive) return;
        health += amount;
    }

    /**
     * Utilise des points d'action.
     *
     * @param amount Nombre de PA à dépenser
     */
    public void usePA(int amount) {
        if (!alive || PA < amount) return;
        PA -= amount;
    }

    /**
     * Calcule la distance euclidienne entre ce monstre et une autre entité.
     *
     * @param entity L'entité cible
     * @return distance en pixels
     */
    public int distanceTo(Entity entity) {
        int dx = Math.abs(entity.getX() - m_x) / m_gp.TILE_SIZE;
        int dy = Math.abs(entity.getY() - m_y) / m_gp.TILE_SIZE;
        return dx + dy;
    }

    /**
     * Vérifie si une entité est dans une portée donnée.
     *
     * @param entity L'entité cible
     * @param range  Distance maximale
     * @return true si dans la portée, false sinon
     */
    public boolean isInRange(Entity entity, int range) {
        return distanceTo(entity) <= range;
    }

    /**
     * Initialise les valeurs par défaut du monstre (position aléatoire, vitesse).
     */
    protected void setDefaultValues() {
        m_x = rand.nextInt(700, 900);
        m_y = rand.nextInt(400, 600);
        m_speed = 1;
    }

    /**
     * Charge l'image du monstre, ou une image de remplacement si indisponible.
     */
    protected void loadMonsterImage() {
        try {
            m_idleImage = ImageIO.read(getClass().getResource("/player/gobelin_gauche.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Image du monstre non trouvée. Utilisation d'un carré rouge.");
            m_idleImage = new BufferedImage(m_gp.TILE_SIZE, m_gp.TILE_SIZE, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = m_idleImage.createGraphics();
            g.setColor(java.awt.Color.RED);
            g.fillRect(0, 0, m_gp.TILE_SIZE, m_gp.TILE_SIZE);
            g.dispose();
        }
    }

    /**
     * Mise à jour du monstre : déplacement aléatoire si PA disponibles et monstre vivant.
     */
    public void update() {
        if (!alive || PA <= 0) return;

        int dx = rand.nextInt(3) - 1; // -1, 0 ou 1
        int dy = rand.nextInt(3) - 1;

        m_x += dx * m_speed;
        m_y += dy * m_speed;

        usePA(1); // Chaque déplacement coûte 1 PA
    }

    /**
     * Dessine le monstre dans le jeu.
     *
     * @param a_g2 contexte graphique 2D
     */
    public void draw(Graphics2D a_g2) {
        if (!alive) return;
        a_g2.drawImage(m_idleImage, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
        // Dessiner la barre de vie au-dessus du joueur
        int barWidth = tileSize;
        int barHeight = 6;
        int barX = m_x;
        int barY = m_y - barHeight - 4; // position un peu au-dessus du joueur

        // Barre de fond (barre vide)
        a_g2.setColor(Color.GRAY);
        a_g2.fillRect(barX, barY, barWidth, barHeight);

        // Barre de vie actuelle (verte)
        float healthRatio = (float) health / (float) maxHealth;
        int healthWidth = (int) (barWidth * healthRatio);

        a_g2.setColor(Color.GREEN);
        a_g2.fillRect(barX, barY, healthWidth, barHeight);

        a_g2.setColor(Color.BLACK);
        a_g2.drawRect(barX, barY, barWidth, barHeight);
    }

    //Fonction pour se diriger vers un ennemi automatiquement
    public void moveToPlayer(Player target) {
        if (!alive) return;

        int targetCol = target.getX() / tileSize;
        int targetRow = target.getY() / tileSize;
        int currentCol = m_x / tileSize;
        int currentRow = m_y / tileSize;

        int dx = targetCol - currentCol;
        int dy = targetRow - currentRow;

        //le monstre peut faire jusqu'à max 3 pas vers sa cible
        int steps = 3;
        while (steps > 0 && (dx != 0 || dy != 0)) {
            int moveX = Integer.compare(dx, 0); // -1, 0 ou 1
            int moveY = Integer.compare(dy, 0); // -1, 0 ou 1

            m_x += moveX * tileSize;
            m_y += moveY * tileSize;

            dx -= moveX;
            dy -= moveY;
            steps--;
        }
    }

    //méthode pour que le monstre attaque le jouer s'il est a coté de lui
    public void attackPlayer(Player target) {
        if (!alive || !target.getisAlive()) return;

        int dx = Math.abs(target.getX() - m_x);
        int dy = Math.abs(target.getY() - m_y);

        boolean isAdjacent = (dx <=70 && dy <=70);

        if (isAdjacent) {
            target.takeDamage(damage);
            System.out.println(name + " attaque " + target.getName() + " et inflige " + damage + " dégâts !");
        }
    }


}