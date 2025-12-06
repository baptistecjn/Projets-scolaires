package main;

import javax.swing.JFrame;

/**
 * Classe principale du jeu
 */
public class Main {
	public static void main(String[] args) {
		JFrame selectWindow = new JFrame();
		selectWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		selectWindow.setResizable(false);
		selectWindow.setTitle("Sélection du personnage");

		CharacterSelectionPanel selectPanel = new CharacterSelectionPanel(selectWindow);
		selectWindow.add(selectPanel);
		selectWindow.pack();
		selectWindow.setLocationRelativeTo(null);
		selectWindow.setVisible(true);
	}
}
