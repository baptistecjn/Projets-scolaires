package main;

import java.awt.*;
import javax.swing.JPanel;

import carte.Carte;
import entity.Combatant;
import entity.Player;
import entity.Monster;
import tile.TileManager;

import java.util.Collection;
import java.util.List;
import java.awt.image.BufferedImage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Inventory.*;

import carte.AttackCarte;

import java.util.ArrayList;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable {

	final int ORIGINAL_TILE_SIZE = 17;
	final int SCALE = 3;
	public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
	public final int MAX_SCREEN_COL = 22;
	public final int MAX_SCREEN_ROW = 14;
	public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
	public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
	private boolean firstTurn = true;
	private boolean combatStarted = false;
	private boolean isPlayerTurn = true;
	private boolean turnActive = false; // true = tour en cours, false = tour terminé
	private long turnStartTime = 0;
	private final long turnDuration = 20000; // 5 secondes par tour
	private boolean mode_combat = false;

	private int currentRound = 1;
	private boolean showMessage = false;
	private long messageStartTime;
	private final int displayDuration = 2000;
	private final long centerDisplayDuration = 1000;

	private final int npcTileX = 970;
	private final int npcTileY = 450;

	boolean isLobby = true;

	int compteur_niveau = 1;
	int m_FPS;

	KeyHandler m_keyH;
	Thread m_gameThread;
	Player m_player;
	TileManager m_tileM;



	Defausse m_defausse;

	List<Monster> monsterList;
	List<Carte> ttcarteListChevalier;
	List<Carte> ttcarteListMage;

	public GamePanel(String characterName) {
		m_FPS = 60;
		m_keyH = new KeyHandler();

		// Création du joueur.
		m_player = new Player(this, m_keyH, 20, 100, characterName, 12);

		monsterList = new ArrayList<>();

		m_tileM = new TileManager(this);

		m_defausse = new Defausse();

		ttcarteListChevalier = new ArrayList<>();
		ttcarteListChevalier.add( new AttackCarte("attaque_de_base", 1, 2,"Attaque de base", 10));
		ttcarteListChevalier.add( new AttackCarte("coup_sanguinaire", 1, 2,"Coup sanguinaire", 15));
		ttcarteListChevalier.add( new AttackCarte("fleche_percante", 1, 3,"Fleche perçante", 15));
		ttcarteListChevalier.add( new AttackCarte("tornade_de_feu", 1, 3,"Tornade de feu", 20));
		ttcarteListChevalier.add( new AttackCarte("gear_maze", 1, 2,"Gear Maze", 25));
		ttcarteListChevalier.add( new AttackCarte("tentacule_des_enfers", 3, 2,"Tentacule des enfers", 25));
		ttcarteListChevalier.add( new AttackCarte("morsure_sismique", 1, 2,"Morsure sismique", 25));
		ttcarteListChevalier.add( new AttackCarte("haki_du_roi", 1, 3,"Haki du roi", 40));
		ttcarteListChevalier.add( new AttackCarte("revers_supernova", 1, 2,"Revers supernova", 40));

		ttcarteListMage = new ArrayList<>();
		ttcarteListMage.add( new AttackCarte("sort_de_base", 1, 2,"Sort de Base", 10));
		ttcarteListMage.add( new AttackCarte("cercle_infernale", 1, 4,"Cercle infernale", 10));
		ttcarteListMage.add( new AttackCarte("fragment_deferlant", 1, 5,"Fragment deferlant", 15));
		ttcarteListMage.add( new AttackCarte("piege_temporel", 1, 2,"Piege temporel", 15));
		ttcarteListMage.add( new AttackCarte("diamant_gelee", 1, 3,"Diamant gelee", 20));
		ttcarteListMage.add( new AttackCarte("sinistre_inevitable", 2, 3,"Sinistre inevitable", 25));
		ttcarteListMage.add( new AttackCarte("spirale_d_eole", 1, 2,"Spirale d'Eole", 25));
		ttcarteListMage.add( new AttackCarte("ligotage_catenaccio", 1, 1,"Ligotage catenaccio", 33));
		ttcarteListMage.add( new AttackCarte("trou_abyssal", 1, 3,"Trou abyssal", 40));

		if( m_player.getName().equals("Gatien")){
			m_player.getInventory().addItem(ttcarteListChevalier.getFirst());
			ttcarteListChevalier.removeFirst();
		}
		if( m_player.getName().equals("Lucie")){
			m_player.getInventory().addItem(ttcarteListMage.getFirst());
			ttcarteListMage.removeFirst();
		}


		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(m_keyH);
		this.setFocusable(true);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int mouseX = e.getX();
				int mouseY = e.getY();

				int tileCol = mouseX / TILE_SIZE;
				int tileRow = mouseY / TILE_SIZE;

				if (m_player.isSelectingAttack() && m_player.getSelectedAttack() != null) {
					int tileX = mouseX / TILE_SIZE;
					int tileY = mouseY / TILE_SIZE;

					for (Monster monster : monsterList) {
						if (monster.getTileCol() == tileX && monster.getTileRow() == tileY) {
							if (m_player.isInRange(monster, m_player.getSelectedAttack().getRange() * TILE_SIZE)) {
								m_player.getSelectedAttack().play(m_player,(Combatant) monster);
							}
						}
					}
					m_player.cancelAttackSelection();
				} else {
					m_player.moveToTile(tileCol, tileRow);
				}
			}
		});
	}
	// Fonction de RoundManager.
	public void nextRound() {
		currentRound++;
		showRoundMessage();
	}
	//l'affichage des rounds ne marchent pas trop
	public void showRoundMessage() {
		showMessage = true;
		messageStartTime = System.currentTimeMillis();
	}
	public void drawRound(Graphics2D g2, int screenWidth, int screenHeight) {
		if (showMessage) {
			long currentTime = System.currentTimeMillis();
			long elapsed = currentTime - messageStartTime;

			String text = "Round " + (currentRound - 1);

			// Choisir les paramètres en fonction de la phase
			Font font;
			int x, y;
			int paddingX, paddingY;

			if (elapsed <= centerDisplayDuration) {
				// PHASE 1 : affichage centré et grand
				font = new Font("Arial", Font.BOLD, 64);
				g2.setFont(font);
				FontMetrics fm = g2.getFontMetrics();
				int stringWidth = fm.stringWidth(text);
				int stringHeight = fm.getHeight();

				paddingX = 40;
				paddingY = 30;
				int boxWidth = stringWidth + paddingX * 2;
				int boxHeight = stringHeight + paddingY * 2;

				x = (screenWidth - boxWidth) / 2;
				y = (screenHeight - boxHeight) / 2; // centré verticalement
			} else {
				// PHASE 2 : coin supérieur droit, plus petit
				font = new Font("Arial", Font.BOLD, 32);
				g2.setFont(font);
				FontMetrics fm = g2.getFontMetrics();
				int stringWidth = fm.stringWidth(text);
				int stringHeight = fm.getHeight();

				paddingX = 20;
				paddingY = 10;
				int boxWidth = stringWidth + paddingX * 2;
				int boxHeight = stringHeight + paddingY * 2;

				x = screenWidth - boxWidth - 20; // marge à droite
				y = 20; // en haut
			}

			// Fond semi-transparent
			g2.setColor(new Color(0, 0, 0, 180));
			g2.fillRoundRect(x, y, g2.getFontMetrics().stringWidth(text) + paddingX * 2, g2.getFontMetrics().getHeight() + paddingY * 2, 30, 30);

			// Contour
			g2.setColor(Color.YELLOW);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(x, y, g2.getFontMetrics().stringWidth(text) + paddingX * 2, g2.getFontMetrics().getHeight() + paddingY * 2, 30, 30);

			// Texte
			int textX = x + paddingX;
			int textY = y + paddingY + g2.getFontMetrics().getAscent();
			g2.drawString(text, textX, textY);
			repaint();
		}
	}
	public int getCurrentRound() {
		return currentRound;
	}

	public void resetRounds() {
		currentRound = 1;
		showMessage = false;
	}


	public void startGameThread() {
		m_gameThread = new Thread(this);
		m_gameThread.start();
	}

	//fonctions pour définir les cases ou on peut aller ou non
	public List<Point> getReachableTiles(int playerCol, int playerRow, int PM) {
		List<Point> reachable = new ArrayList<>();

		for (int col = playerCol - PM; col <= playerCol + PM; col++) {
			for (int row = playerRow - PM; row <= playerRow + PM; row++) {
				if (col >= 0 && col < MAX_SCREEN_COL && row >= 0 && row < MAX_SCREEN_ROW) {
					int dist = Math.abs(col - playerCol) + Math.abs(row - playerRow);
					if (dist <= PM
							&& (m_tileM.getMapTileNum(col, row) != 0)
							&& (m_tileM.getMapTileNum(col, row) != 309)
							&& (m_tileM.getMapTileNum(col, row) != 423)
							&& (m_tileM.getMapTileNum(col, row) != 451)
							&& (m_tileM.getMapTileNum(col, row) != 12)
							&& (m_tileM.getMapTileNum(col, row) != 131)
							&& (m_tileM.getMapTileNum(col, row) != 48)
							&& (m_tileM.getMapTileNum(col, row) != 64)
							&& (m_tileM.getMapTileNum(col, row) != 65)
							&& (m_tileM.getMapTileNum(col, row) != 121)
							&& (m_tileM.getMapTileNum(col, row) != 425)
							&& (m_tileM.getMapTileNum(col, row) != 120)
							&& (m_tileM.getMapTileNum(col, row) != 33)
							&& (m_tileM.getMapTileNum(col, row) != 95)
							&& (m_tileM.getMapTileNum(col, row) != 297)
							&& (m_tileM.getMapTileNum(col, row) != 92)
							&& (m_tileM.getMapTileNum(col, row) != 5)
							&& (m_tileM.getMapTileNum(col, row) != 258)
							&& (m_tileM.getMapTileNum(col, row) != 44)
							&& (m_tileM.getMapTileNum(col, row) != 317)
							&& (m_tileM.getMapTileNum(col, row) != 316)
							&& (m_tileM.getMapTileNum(col, row) != 314)
							&& (m_tileM.getMapTileNum(col, row) != 428)
							&& (m_tileM.getMapTileNum(col, row) != 456)
							&& (m_tileM.getMapTileNum(col, row) != 9)
							&& (m_tileM.getMapTileNum(col, row) != 10)
							&& (m_tileM.getMapTileNum(col, row) != 11)
							&& (m_tileM.getMapTileNum(col, row) != 66)
							&& (m_tileM.getMapTileNum(col, row) != 96)
							&& (m_tileM.getMapTileNum(col, row) != 460)
							&& (m_tileM.getMapTileNum(col, row) != 229)
							&& (m_tileM.getMapTileNum(col, row) != 431)
							&& (m_tileM.getMapTileNum(col, row) != 432)
							&& (m_tileM.getMapTileNum(col, row) != 87)
							&& (m_tileM.getMapTileNum(col, row) != 230)
							&& (m_tileM.getMapTileNum(col, row) != 59)
							&& (m_tileM.getMapTileNum(col, row) != 3)
							&& (m_tileM.getMapTileNum(col, row) != 313)
							&& (m_tileM.getMapTileNum(col, row) != 427)
							&& (m_tileM.getMapTileNum(col, row) != 99)
							&& (m_tileM.getMapTileNum(col, row) != 122)
							&& (m_tileM.getMapTileNum(col, row) != 285)
							&& (m_tileM.getMapTileNum(col, row) != 287)
							&& (m_tileM.getMapTileNum(col, row) != 315)
							&& (m_tileM.getMapTileNum(col, row) != 343)
							&& (m_tileM.getMapTileNum(col, row) != 369)
							&& (m_tileM.getMapTileNum(col, row) != 342)
							&& (m_tileM.getMapTileNum(col, row) != 172)
							&& (m_tileM.getMapTileNum(col, row) != 171)
							&& (m_tileM.getMapTileNum(col, row) != 143)
							&& (m_tileM.getMapTileNum(col, row) != 169)
							&& (m_tileM.getMapTileNum(col, row) != 6)
							&& (m_tileM.getMapTileNum(col, row) != 34)
							&& (m_tileM.getMapTileNum(col, row) != 174)
							&& (m_tileM.getMapTileNum(col, row) != 304)
							&& (m_tileM.getMapTileNum(col, row) != 332)
							&& (m_tileM.getMapTileNum(col, row) != 360)
							&& (m_tileM.getMapTileNum(col, row) != 333)
							&& (m_tileM.getMapTileNum(col, row) != 361)
							&& (m_tileM.getMapTileNum(col, row) != 334)
							&& (m_tileM.getMapTileNum(col, row) != 362)
							&& (m_tileM.getMapTileNum(col, row) != 305)
							&& (m_tileM.getMapTileNum(col, row) != 306)
					) {
						reachable.add(new Point(col, row));
					}
				}
			}
		}
		return reachable;
	}
	//  ////////////////////////////////METHODE DRAW////////////////////////////////////////////// //

	//méthode pour afficher les PA
	private void drawPa(Graphics g2) {
		int slotSize = 48;
		int slotPadding = 4;
		int numSlots = 12;
		int totalWidth = numSlots * (slotSize + slotPadding) - slotPadding;

		int X = (SCREEN_WIDTH - totalWidth) / 2 - 50;
		int Y = SCREEN_HEIGHT - slotSize / 2 - 20;

		// On définit une police plus grande et en gras
		Font originalFont = g2.getFont(); // Pour restaurer après si besoin
		Font boldFont = new Font("Arial", Font.BOLD, 20); // Nom, Style, Taille
		g2.setFont(boldFont);

		g2.setColor(Color.WHITE);
		g2.drawString("PA : " + m_player.getPA(), X, Y);

		// Afficher le niveau juste en dessous (par exemple +30 pixels)
		g2.drawString("Niveau : " + compteur_niveau, X, Y + 30);

		// On restaure la police d'origine pour continuer à dessiner avec l'ancienne
		g2.setFont(originalFont);
	}



	//méthode pour afficher la bar d'inventaire
	private void drawInventoryBar(Graphics2D g2) {
		Inventory inventory = m_player.getInventory();
		List<Carte> items = inventory.getItems();

		int slotSize = 48;
		int slotPadding = 4;
		int numSlots = 10;
		int totalWidth = numSlots * (slotSize + slotPadding) - slotPadding;

		int startX = (SCREEN_WIDTH - totalWidth) / 2;
		int startY = SCREEN_HEIGHT - slotSize - 20;

		// Tableau des touches : 1 à 9 puis 0 pour la 10e case
		String[] keyLabels = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };

		for (int i = 0; i < numSlots; i++) {
			int x = startX + i * (slotSize + slotPadding);
			int y = startY;

			// Fond et bord de case
			g2.setColor(Color.GRAY);
			g2.fillRect(x, y, slotSize, slotSize);
			g2.setColor(Color.BLACK);
			g2.drawRect(x, y, slotSize, slotSize);

			// Afficher la touche dans un petit cercle en haut à droite
			int labelRadius = 16;
			int circleX = x + slotSize - labelRadius - 2;
			int circleY = y + 2;


			// Afficher la carte si présente
			if (i < items.size()) {
				Carte carte = items.get(i);
				BufferedImage img = null;

				if (carte instanceof AttackCarte attackCarte) {
					img = attackCarte.getImage();
				}

				if (img != null) {
					int imgSize = slotSize - 8;
					int imgX = x + (slotSize - imgSize) / 2;
					int imgY = y + (slotSize - imgSize) / 2;
					g2.drawImage(img, imgX, imgY, imgSize, imgSize, null);
				} else {
					g2.setColor(Color.WHITE);
					g2.drawString(carte.getName(), x + 5, y + slotSize / 2);
				}
			}
			// Cercle noir
			g2.setColor(Color.BLACK);
			g2.fillOval(circleX, circleY, labelRadius, labelRadius);

			// Texte blanc centré dans le cercle
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Arial", Font.BOLD, 10));
			FontMetrics fm = g2.getFontMetrics();
			int textWidth = fm.stringWidth(keyLabels[i]);
			int textHeight = fm.getAscent();
			int textX = circleX + (labelRadius - textWidth) / 2;
			int textY = circleY + (labelRadius + textHeight) / 2 - 2;
			g2.drawString(keyLabels[i], textX, textY);
		}
	}

	//méthode pour afficher la main du joueur
	private void drawHandBar(Graphics2D g2) {
		List<Carte> hand = m_player.getHand().getCartes(); //Récupère la main du joueur

		int slotSize = 48;
		int slotPadding = 4;
		int numSlots = hand.size(); // Nombre de cartes dans la main
		int totalWidth = numSlots * (slotSize + slotPadding) - slotPadding;

		int startX = (SCREEN_WIDTH - totalWidth) / 2;
		int startY = SCREEN_HEIGHT - slotSize - 20;

		for (int i = 0; i < numSlots; i++) {
			int x = startX + i * (slotSize + slotPadding);
			int y = startY;

			g2.setColor(Color.GRAY);
			g2.fillRect(x, y, slotSize, slotSize);
			g2.setColor(Color.BLACK);
			g2.drawRect(x, y, slotSize, slotSize);

			Carte carte = hand.get(i);
			BufferedImage img = null;

			if (carte instanceof AttackCarte attackCarte) {
				img = attackCarte.getImage();
			}

			if (img != null) {
				int imgSize = slotSize - 8;
				int imgX = x + (slotSize - imgSize) / 2;
				int imgY = y + (slotSize - imgSize) / 2;
				g2.drawImage(img, imgX, imgY, imgSize, imgSize, null);
			} else {
				g2.setColor(Color.WHITE);
				g2.drawString(carte.getName(), x + 5, y + slotSize / 2);
			}
		}
	}

	//méthode pour afficher toutes les entités
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		m_tileM.draw(g2);

		// Affichage de la grille.
		g2.setColor(new Color(255, 255, 255, 80));
		for (int col = 0; col <= MAX_SCREEN_COL; col++) {
			int x = col * TILE_SIZE;
			g2.drawLine(x, 0, x, SCREEN_HEIGHT);
		}
		for (int row = 0; row <= MAX_SCREEN_ROW; row++) {
			int y = row * TILE_SIZE;
			g2.drawLine(0, y, SCREEN_WIDTH, y);
		}

		int playerCol = m_player.getTileCol();
		int playerRow = m_player.getTileRow();
		int PM = m_player.getPM();

		// Affichage des cases possible pour le déplacement.
		List<Point> reachableTiles = getReachableTiles(playerCol, playerRow, PM);
		g2.setColor(new Color(93, 255, 0, 50));
		for (Point p : reachableTiles) {
			int x = p.x * TILE_SIZE;
			int y = p.y * TILE_SIZE;
			g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
		}


		if(mode_combat) {
			drawHandBar(g2);
			drawPa((g2));
			m_player.draw(g2);
			for (Monster monster : monsterList) {
				monster.draw(g2);
			}
		} else {
			m_player.draw(g2);
			drawInventoryBar(g2);
		}
		// Affichage de message pour le debut de round.
		drawRound(g2, SCREEN_WIDTH, SCREEN_HEIGHT);
		g2.dispose();
	}

	//  ////////////////////////////////////////////////////////////////////////////// //


	// //////////////////////////////////METHODE GESTION DES TOURS/////////////////////////////// //
	//méthode pour déterminer si le combat est finit
	private boolean isCombatOver() {
		if(!m_player.getisAlive()) return true;

		boolean all_monsters_ko = false;
		for(Monster e : monsterList) {
			if(e.getisAlive()){
				all_monsters_ko = true;
				break;
			}
		}
		return !all_monsters_ko;
	}

	//  ////////////////////////////////////////////////////////////////////////////// //

	//méthode pour lancer le jeu / boucle de jeu
    public void run() {
        double drawInterval = 1000000000 / m_FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        int i = 0;


        while (m_gameThread != null) {
            if (isLobby == true) {
                m_tileM.setMap("/maps/map_lobby.txt");
					this.update();
					this.repaint();
					if (m_player.getPM() < 12){
						m_player.setPM(12);

					}
					//cas ou on est en cbt
				} else {
					boucle_jeu();
				}

		}
	}

	//boucle du jeu appeler dans run
	public void boucle_jeu(){

		//si cest le début du combat
		if (!combatStarted) {
			// Initialisation du combat (une seule fois)
			m_tileM.setMap("/maps/map_fight.txt");
			m_player.setPM(6);


			for (int i = 0; i < compteur_niveau; i++) {
				if (i % 2 == 0) {
					// Goblin
					Monster m_monster1 = new Monster(this, 3, 20, "Goblin", 5);
					monsterList.add(m_monster1);
				} else {
					// Orc
					Monster m_monster2 = new Monster(this, 5, 35, "Orc", 10);
					monsterList.add(m_monster2);
				}
			}
			mode_combat = true;
			isPlayerTurn = true;
			turnActive = true;
			turnStartTime = System.currentTimeMillis();
			combatStarted = true;

			System.out.println("Début du combat, tour joueur.");
			m_player.resetPA(); //on reset les PA du joueur
			return; // on quitte pour attendre le prochain tick
		}
		//si cest le tour du joueur
		if(turnActive){
			long elapsed = System.currentTimeMillis() - turnStartTime; //on lance le chrono
			if (isPlayerTurn) {
				Inventory m_inventory = m_player.getInventory(); //on récupère l'inventaire
				if(firstTurn) { //si cest le premier tour : on créer la main
					firstTurn = false;
					m_player.getHand().piocheHand(m_inventory);
				}
				if(m_inventory.isEmpty()){ //si l'iventaire est vide alors on mélange la défausse et on remet dans l'inventaire
					m_defausse.melangeDefausse(m_inventory);
					m_player.getHand().piocheHand(m_inventory); //puis on refait une main
				}
				//si la main n'est pas pleine : on pioche UNE carte
				if(!m_player.getHand().isFull()){ //si
					m_player.getHand().piocheOneCarte(m_inventory);
				}
				//si on a plus de PA ou si on dépasse le chrono  ou bouton fin de tour-> fin de tour
				if (m_player.getPA() <= 0 || elapsed > turnDuration || m_keyH.customKeyPressed) {
					System.out.println("Fin du tour du joueur.");
					turnActive = false; // fin du tour joueur
					m_keyH.customKeyPressed = false;
				}
				//on met a jour le joueur et les graphiques

				this.update();
				this.repaint();

			} else { //si cest pas le tour du joueur cest celui des ennemies
				System.out.println("Tour des ennemis.");

				for (Monster monstre : monsterList) {
					//a modifier si jamais ca marche pas en : soit le monstre se déplace si loin du joueur sinon il attaque
					monstre.moveToPlayer(m_player); //bouge vers le joueur si possible
					monstre.attackPlayer(m_player); //attaque le jouer si possible
				}
				try {
					Thread.sleep(1000); // pause d'une seconde pour éviter que ça boucle trop vite
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Fin du tour des ennemis.");
				turnActive = false; // fin du tour ennemis
			}


		}else{ //on passe au tour suivant ou fin de combat
			//si le cbt est finit :
			if (isCombatOver()) {
				System.out.println("Combat terminé !");
				combatStarted = false;
				mode_combat = false;

				if (m_player.getisAlive()){
					compteur_niveau+=1; //on augmente le niveau
					System.out.println("Victoire !");
					monsterList.clear();
					m_player.setMaxHealth(); //on redonne la vie au joueur
					m_player.setIsAlive(true); //on dit que le joueur est re en vie


					if(Objects.equals(m_player.getName(), "Gatien")){
						m_player.getInventory().addItem(ttcarteListChevalier.getFirst());
						ttcarteListChevalier.removeFirst();
					}
					if(Objects.equals(m_player.getName(), "Lucie")){
						m_player.getInventory().addItem(ttcarteListMage.getFirst());
						ttcarteListMage.removeFirst();
					}
				} else {
					System.out.println("Défaite...");
					m_player.setMaxHealth(); //on redonne la vie au joueur
					m_player.setIsAlive(true); //on dit que le joueur est re en vie
				}
				isLobby = true;

			}else {//si le cbt n'est pas terminé
				// On prépare le tour suivant
				isPlayerTurn = !isPlayerTurn; // changement de joueur/enemies
				turnActive = true;
				turnStartTime = System.currentTimeMillis();

				if (isPlayerTurn) {
					m_player.setPM(6); // reset PM joueur au début de son tour
					System.out.println("Début du tour joueur.");
				} else {
					System.out.println("Début du tour ennemis.");
				}
			}
		}
	}
	//méthode update
	public void update() {
		m_player.update();
		// Coordonnées joueur
		int playerX = m_player.getX();
		int playerY = m_player.getY();
		if(isLobby) {

			// Distance avec PNJ
			int dx = Math.abs(playerX - npcTileX);
			int dy = Math.abs(playerY - npcTileY);

			if ((dx + dy) <= 100) { // joueur adjacent au PNJ
				if (m_keyH.interactPressed) { // touche interaction pressée
					m_keyH.interactPressed = false; // reset pour éviter multi-appuis
					openDialogWindow();
				}
			}
		}
	}
	private boolean askYesNo(String message, String title) {
		int result = javax.swing.JOptionPane.showConfirmDialog(this, message, title, javax.swing.JOptionPane.YES_NO_OPTION);

		return result == javax.swing.JOptionPane.YES_OPTION;
	}

	public void openDialogWindow() {
		if(askYesNo("Tu veux te battre ?", "Gandalf")){
			isLobby = false;
			m_player.setX(300);
			m_player.setY(233);
		}
	}



}