package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

/**
 * 
 * Gestionnaire des tiles du jeu
 *
 */
public class TileManager {
	GamePanel m_gp;			//panel du jeu principal
	Tile[] m_tile;			//tableau de toutes les tiles possibles dans le jeu
	int m_maxTiles = 461;	//nombre maximum de tiles chargeable dans le jeu
	int m_mapTileNum[][];
	String map = "/maps/map_lobby.txt";
	KeyHandler m_keyH = new KeyHandler();

	//r�partition des tiles dans la carte du jeu
	
	/**
	 * Constructeur
	 * @param gp
	 */
	public TileManager(GamePanel gp) {
		this.m_gp =  gp;
		m_tile = new Tile[m_maxTiles];
		m_mapTileNum = new int[gp.MAX_SCREEN_COL][gp.MAX_SCREEN_ROW];
		this.getTileImage();
		this.loadMap("/maps/map_lobby.txt");

	}

	public void setMap(String map) {
		this.loadMap(map);
	}

	public int getMapTileNum(int x, int y) {
		return m_mapTileNum[x][y];
	}
	/**
	 * Chargement de toutes les tuiles du jeu
	 */
	public void getTileImage() {
		try {
			for (int i = 0; i < m_maxTiles; i++) {
				m_tile[i] = new Tile();
				String fileName = String.format("/tiles/tile_%d.png", i);
				m_tile[i].m_image = ImageIO.read(getClass().getResource(fileName));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lecture du fichier txt contenant la map et chargement des tuiles correspondantes.
	 */
	public void loadMap(String filePath) {
		//charger le fichier txt de la map
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
			int col = 0;
			int row = 0;
			
			// Parcourir le fichier txt pour r�cup�rer les valeurs
			while (col < m_gp.MAX_SCREEN_COL && row < m_gp.MAX_SCREEN_ROW) {
				String line = br.readLine();
				while (col < m_gp.MAX_SCREEN_COL) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					m_mapTileNum [col][row] = num;
					col++;
				}
				if (col == m_gp.MAX_SCREEN_COL) {
					col = 0;
					row ++;
				}
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Affichage de la carte avec les diff�rentes tuiles
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while (col < m_gp.MAX_SCREEN_COL && row < m_gp.MAX_SCREEN_ROW) {
			int tileNum = m_mapTileNum[col][row];
			
			g2.drawImage(m_tile[tileNum].m_image, x, y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
			col ++;
			x += m_gp.TILE_SIZE;
			if (col == m_gp.MAX_SCREEN_COL) {
				col = 0;
				row ++;
				x = 0;
				y += m_gp.TILE_SIZE;
			}
		}
		
	}
}
