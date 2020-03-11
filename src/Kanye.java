/*
Author: Alex Lee

This Java file handles the updating of the Kanye Objects
within the road Array in the Game class
*/

import java.awt.*;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.File;

public class Kanye {
	// Kanye object's x pos
	private int kanyeX;
	// Kanye image to be imported in the constructor
	private Image kanye;
	//// Kanye object's y pos
	private int kanyeY;
	// speed of the Kanye objects
	private int kanyeSpeed = 8;
	// Happy Kanye image
	String file = "kanye_head.png";
	// Used for random generation
	Random rand = new Random();

	// Constructor to set the speed and image of the Car object for the road
	// array in the Game class
	public Kanye() {

		// Using ImageIO to read in an image of kanye's head
		try {
			kanye = ImageIO.read(new File(file));
		}
		// Exception required when using ImageIO
		catch (IOException e) {
			e.printStackTrace();
		}
		// get a starting X position
		getX();
	}

	// Generate a random X location to start the Kanye Object at
	public int getX() {
		// returns an int between 0 and 4 then multiplies it by 100 for the X
		return kanyeX = (rand.nextInt((4 - 0) + 1) + 0) * 200;
	}

	// Draws the image of the Kanye object
	public void paint(Graphics g) {
		// draw kanye image on screen in the x and y cords assigned
		g.drawImage(kanye, kanyeX, kanyeY, 200, 200, null);
	}

	public void update(Game gm) {

		// change speed of kanye faces - intervals of 5000
		if (gm.score >= 5000 && gm.score <= 10000) {
			kanyeSpeed = 9;
		} else if (gm.score >= 10000 && gm.score <= 20000) {
			kanyeSpeed = 10;
		} else if (gm.score >= 20000) {
			kanyeSpeed = 11;
		}

		// Updated way of checking if game is over
		int i = kanyeY + 200 - gm.y;
		int i2 = kanyeX + 200 - gm.x;
		int i3 = kanyeY - gm.y;
		if ((((i) > 0 && (i) <= 200) && ((i2) > 0 && (i2) <= 200))
				|| (((i3) > 0 && (i3) <= 200) && ((i2) > 0 && (i2) <= 200))) {
			// End Game
			gm.gameOver = true;
			// Kanye is not happy switch his face to angry_kanye.png
			file = "angry_kanye.png";
			// Using ImageIO to read in an image of kanye's head
			try {
				kanye = ImageIO.read(new File(file));
			}
			// Exception required when using ImageIO
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Kanye object reached bottom - move back to top
		else if (kanyeY >= 1000) {
			// reset X position
			getX();
			// fix for overlapping kanye faces - starts the face's y pos at
			// different spots ranging from -100 to -400
			kanyeY = -((rand.nextInt((4 - 1) + 1) + 1) * 200);
		}
		// Moves Kanye object down 100 in the 500x550 frame
		else if (kanyeY <= 1000) {
			// Move Kanye down the panel
			kanyeY += kanyeSpeed;
		}
	}
}