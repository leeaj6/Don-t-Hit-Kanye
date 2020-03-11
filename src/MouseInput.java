/*
Author: Alex Lee

The MouseInput class is to utilize the MouseListener interface
and handle all the Game's mouse driven actions.
*/

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;

public class MouseInput implements MouseListener {

	// needed to change the startMenu variable in the Game class
	private Game gm;
	// variables for name and car type
	String name = null, car = null;

	// Constructor
	public MouseInput(Game g) {
		gm = g;
	}

	public void mousePressed(MouseEvent e) {
		/*
		 * public Rectangle playButton = new Rectangle(200 , 250, 100, 50);
		 * public Rectangle quitButton = new Rectangle(200 , 320, 100, 50);
		 */

		// get x cordinate of the mouse
		int mx = e.getX();
		// get y cordinate of the mouse
		int my = e.getY();

		if (mx >= 400 && mx <= 600) {
			if (my >= 540 && my <= 640) {
				String path = "";
				// prompt user to enter their username
				name = JOptionPane.showInputDialog("Enter Your Username: ");
				// Check if user has entered a username
				if (name != null) {
					gm.username = new String(name);
					car = JOptionPane.showInputDialog(
							"Choose a car:\n1=Lamborghini Murcielago LP 670-4 SuperVeloce\n2=Ferrari Testarossa\n3=Dodge Challenger");
					// Car images from google search for "car above png"
					switch (new Integer(car)) {
					case 1:
						path = "SV.png";
						break;
					case 2:
						path = "ferrari.png";
						break;
					case 3:
						path = "challenger.png";
						break;
					// Easter Egg- Here Come Dat Boi Meme
					case 420:
						path = "HCDB.png";
						break;
					}
					// Import user's selected car image
					try {
						gm.userCar = ImageIO.read(new File(path));
					} catch (IOException i) {
						i.printStackTrace();
					}
					// pressed play
					gm.startMenu = false;
				}
			} else if (my >= 680 && my <= 780) {
				// pressed quit
				System.exit(0);
			}
		}
	}

	// These are methods that are part of the MouseListener interface and must
	// be in the MouseInput class
	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}
}