package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Gestionnaire d'événements clavier
 */
public class KeyHandler implements KeyListener {

	public boolean attackKeyPressed;
	public boolean interactPressed = false;


	// Touche 1 à 12 pour les sorts (1 à =)
	public boolean[] spellKeysPressed = new boolean[12];
	public boolean customKeyPressed = false;

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		switch (code) {
			case KeyEvent.VK_A -> attackKeyPressed = true;


			case KeyEvent.VK_Z -> customKeyPressed = true;
			case KeyEvent.VK_Q -> spellKeysPressed[0] = true;
			case KeyEvent.VK_S -> spellKeysPressed[1] = true;
			case KeyEvent.VK_D -> spellKeysPressed[2] = true;
			case KeyEvent.VK_4 -> spellKeysPressed[3] = true;
			case KeyEvent.VK_5 -> spellKeysPressed[4] = true;
			case KeyEvent.VK_6 -> spellKeysPressed[5] = true;
			case KeyEvent.VK_7 -> spellKeysPressed[6] = true;
			case KeyEvent.VK_8 -> spellKeysPressed[7] = true;
			case KeyEvent.VK_9 -> spellKeysPressed[8] = true;
			case KeyEvent.VK_0 -> spellKeysPressed[9] = true;
			case KeyEvent.VK_MINUS -> spellKeysPressed[10] = true;   // touche "-"
			case KeyEvent.VK_EQUALS -> spellKeysPressed[11] = true;
			case KeyEvent.VK_E -> interactPressed = true;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		switch (code) {
			case KeyEvent.VK_A -> attackKeyPressed = false;
			case KeyEvent.VK_Z -> customKeyPressed = false;
			case KeyEvent.VK_Q -> spellKeysPressed[0] = false;
			case KeyEvent.VK_S -> spellKeysPressed[1] = false;
			case KeyEvent.VK_D -> spellKeysPressed[2] = false;
			case KeyEvent.VK_4 -> spellKeysPressed[3] = false;
			case KeyEvent.VK_5 -> spellKeysPressed[4] = false;
			case KeyEvent.VK_6 -> spellKeysPressed[5] = false;
			case KeyEvent.VK_7 -> spellKeysPressed[6] = false;
			case KeyEvent.VK_8 -> spellKeysPressed[7] = false;
			case KeyEvent.VK_9 -> spellKeysPressed[8] = false;
			case KeyEvent.VK_0 -> spellKeysPressed[9] = false;
			case KeyEvent.VK_MINUS -> spellKeysPressed[10] = false;
			case KeyEvent.VK_EQUALS -> spellKeysPressed[11] = false;
			case KeyEvent.VK_E -> interactPressed = false;

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Non utilisé
	}
}