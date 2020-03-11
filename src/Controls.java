import java.awt.event.*;

public class Controls implements KeyListener {

	private Game gm;

	public Controls(Game game) {
		gm = game;
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