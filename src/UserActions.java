/*
Author: Alex Lee

This Java file handles actions from the user
to control their car with the keyboard
*/

import java.awt.event.*;

public class UserActions implements KeyListener {

	private Game gm;

	public UserActions(Game g) {
		gm = g;
	}

	public void keyPressed(KeyEvent e) {
		// check if left arrow key was pressed
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			gm.goLeft();
		}
		// check if right arrow key was pressed
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gm.goRight();
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}
}