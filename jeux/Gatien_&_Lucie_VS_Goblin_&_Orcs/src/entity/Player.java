package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import Inventory.Hand;
import carte.AttackCarte;
import main.GamePanel;
import main.KeyHandler;

import Inventory.Inventory;


public class Player extends Entity implements Combatant {
	protected int PA;
	protected int health;
	protected String name;
	private int PA_courrant;
	private boolean isAlive = true;

	private boolean isSelectingAttack = false;
	private AttackCarte selectedAttack = null;

	GamePanel m_gp;
	KeyHandler m_keyH;
	private Inventory inventory;

	private int targetX, targetY;
	private boolean moving = false;
	private final int tileSize;
	private int PM;
	private int maxHealth;
	public Hand hand;
	private boolean pmAlreadyDeducted = false;
	private int startTileX, startTileY;

	public Player(GamePanel a_gp, KeyHandler a_keyH, int PA, int health, String name, int PM) {
		this.m_gp = a_gp;
		this.m_keyH = a_keyH;
		this.tileSize = m_gp.TILE_SIZE;
		this.PA = PA;
		this.PA_courrant = PA;
		this.health = health;
		this.maxHealth = health;
		this.name = name;
		this.PM = PM;
		this.targetX = m_x;
		this.targetY = m_y;
		this.setDefaultValues();
		this.getPlayerImageDroite();
		moving = false;
		pmAlreadyDeducted = true;

		inventory = new Inventory(10);
		hand = new Hand();
	}
	public Hand getHand() {
		return hand;
	}

	public void setMaxHealth() {
		this.health = maxHealth;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getTileCol() {
		return m_x / tileSize;
	}

	public int getTileRow() {
		return m_y / tileSize;
	}

	public int getPM() {
		return PM;
	}

	public void setPM(int PM) {
		this.PM = PM;
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
		return PA_courrant;
	}

	public boolean getisAlive() {
		return isAlive;
	}

	public void takeDamage(int damage) {
		health -= damage;
		if (health <= 0) isAlive = false;
	}

	public void heal(int amount) {
		health += amount;
	}

	public void usePA(int amount) {
		PA_courrant -= amount;
	}

	public void resetPA() {
		PA_courrant = PA;
	}
	public void setX(int X){
		m_x = X;
	}
	public void setY(int Y){
		m_y = Y;
	}


	public int distanceTo(Entity entity) {
		int dx = Math.abs(entity.getX() - m_x) / m_gp.TILE_SIZE;
		int dy = Math.abs(entity.getY() - m_y) / m_gp.TILE_SIZE;
		return dx + dy;
	}

	public boolean isInRange(Entity entity, int range) {
		return distanceTo(entity) <= range;
	}

	@Override
	public String getName() {
		return name;
	}

	protected void setDefaultValues() {
		m_x = 100;
		m_y = 100;
		m_speed = 1;
	}

	public void getPlayerImageDroite() {
		try {
			if (this.getName()=="Gatien") {
				m_idleImage = ImageIO.read(getClass().getResource("/player/chevalier_droite.png"));
			} else {
				m_idleImage = ImageIO.read(getClass().getResource("/player/mage_droite.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getPlayerImageGauche() {
		try {
			if (this.getName()=="Gatien") {
				m_idleImage = ImageIO.read(getClass().getResource("/player/chevalier_gauche.png"));
			} else {
				m_idleImage = ImageIO.read(getClass().getResource("/player/mage_gauche.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void moveToTile(int tileCol, int tileRow) {
		//s on clique sur la case où on est déjà, on annule le mouvement
		if (getTileCol() == tileCol && getTileRow() == tileRow) {
			moving = false;
			return;
		}

		startTileX = this.getTileCol();
		startTileY = this.getTileRow();

		targetX = tileCol * tileSize;
		targetY = tileRow * tileSize;

		moving = true;
		pmAlreadyDeducted = false;
	}


	public void update() {

		if (m_keyH.customKeyPressed) {
			System.out.println("Tu as passé ton tour !");
			m_keyH.customKeyPressed = false; // optionnel selon le comportement voulu
		}
		// Blocage déplacement si en train de sélectionner une attaque
		if (!isSelectingAttack && moving) {
			int playerCol = getTileCol();
			int playerRow = getTileRow();
			List<Point> reachableTiles = m_gp.getReachableTiles(playerCol, playerRow, PM);
			Point targetTile = new Point(targetX / tileSize, targetY / tileSize);

			if (!reachableTiles.contains(targetTile)) {
				moving = false;
				return;
			}

			int dx = targetX - m_x;
			int dy = targetY - m_y;

			if (dx != 0) m_x += (dx > 0) ? Math.min(m_speed, dx) : Math.max(-m_speed, dx);
			if (dy != 0) m_y += (dy > 0) ? Math.min(m_speed, dy) : Math.max(-m_speed, dy);

			if (m_x == targetX && m_y == targetY) moving = false;
		}
		// Gestion des touches 1 à 12 pour activer ou désactiver une attaque spécifique
		for (int i = 0; i < 12; i++) {
			if (m_keyH.spellKeysPressed[i]) {
				System.out.println(("test"));

				// Important : reset la touche dès maintenant pour éviter les répétitions
				m_keyH.spellKeysPressed[i] = false;

				if (!isSelectingAttack) {
					// Récupérer la i-ème carte d'attaque dans l'inventaire (si elle existe)
					int attackCount = 0;
					for (var item : hand.getCartes()) {
						if (item instanceof AttackCarte) {
							if (attackCount == i) {
								selectedAttack = (AttackCarte) item;
								isSelectingAttack = true;
								break;
							}
							attackCount++;
						}
					}
				} else {
					// Une attaque était déjà sélectionnée, on annule la sélection
					cancelAttackSelection();
				}

				// Une seule touche doit être traitée par update
				break;
			}
		}
		if (m_x == targetX && m_y == targetY && !pmAlreadyDeducted) {
			int endTileX = getTileCol();
			int endTileY = getTileRow();
			int distance = Math.abs(endTileX - startTileX) + Math.abs(endTileY - startTileY);
			PM = Math.max(0, PM - distance);
			pmAlreadyDeducted = true;

//			System.out.println("Distance parcourue : " + distance);
//			System.out.println("PM restants : " + PM);

			moving = false;
		}
	}

	public boolean isSelectingAttack() {
		return isSelectingAttack;
	}

	public AttackCarte getSelectedAttack() {
		return selectedAttack;
	}

	public void cancelAttackSelection() {
		isSelectingAttack = false;
		selectedAttack = null;
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(m_idleImage, m_x, m_y, tileSize, tileSize, null);
		// Dessiner la barre de vie au-dessus du joueur
		int barWidth = tileSize;
		int barHeight = 6;
		int barX = m_x;
		int barY = m_y - barHeight - 4; // position un peu au-dessus du joueur

		// Barre de fond (barre vide)
		g2.setColor(Color.GRAY);
		g2.fillRect(barX, barY, barWidth, barHeight);

		// Barre de vie actuelle (verte)
		float healthRatio = (float) health / (float) maxHealth;
		int healthWidth = (int) (barWidth * healthRatio);

		g2.setColor(Color.GREEN);
		g2.fillRect(barX, barY, healthWidth, barHeight);

		g2.setColor(Color.BLACK);
		g2.drawRect(barX, barY, barWidth, barHeight);
		// Ton code actuel de sélection d'attaque
		if (isSelectingAttack && selectedAttack != null) {
			int range = selectedAttack.getRange();
			int tileX = getTileCol();
			int tileY = getTileRow();

			g2.setColor(new Color(255, 0, 0, 100)); // rouge transparent
			for (int dx = -range; dx <= range; dx++) {
				for (int dy = -range; dy <= range; dy++) {
					int dist = Math.abs(dx) + Math.abs(dy);
					if (dist <= range) {
						int drawX = (tileX + dx) * tileSize;
						int drawY = (tileY + dy) * tileSize;
						g2.fillRect(drawX, drawY, tileSize, tileSize);
					}
				}
			}
		}
	}
}