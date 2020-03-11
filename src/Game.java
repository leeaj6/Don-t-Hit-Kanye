/*
Author: Alex Lee

***Don't Hit Kanye!***
Summary:
make your way through the busy Kanye traffic on the highway
and avoid crashing into any Kanyes!
_____________________________________________________

Directions:
Left - Left arrow key
Right - Right arrow key
*/

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.text.DecimalFormat;

public class Game extends JPanel {
	//Size of screen x
	final int XSIZE = 1000;
	//Size of screen y
	final int YSIZE = 1100;
	// Eclipse Serial Version UID
	private static final long serialVersionUID = 4426838373284817839L;
	// Variable is true when player chooses to play again
	boolean playAgain = false;
	// Frames per second: 24, 30, 60, 120
	final int FPS = 120;
	// Number of Kanye faces on the road - 3 or 4 recommended
	int numKanyesOnTheRoad = 3;
	// road texture and user's car images - will be defined in the constructor
	Image roadText, userCar, kanyeStart, album1, album2, album3, album4, album5, album6, album7;
	// user's score
	int score = 0;
	// when true game ends
	boolean gameOver = false;
	// make an array for instances of the Kanye class
	Kanye[] road = new Kanye[numKanyesOnTheRoad];
	// player's starting x coordinate on the JPanel
	int x = 200;
	// player's y coordinate on the JPanel - Stays constant
	int y = 400;
	// Buttons for the menu screen
	public Rectangle playButton = new Rectangle(400, 510, 200, 100);
	public Rectangle quitButton = new Rectangle(400, 640, 200, 100);
	// boolean to see if we should be at the start menu
	boolean startMenu = true;
	// player's username
	String username;
	// Image variable to handle change in Backgrounds
	Image backG;

	public Game() {
		// Some Image files used in the Game class
		try {
			// Image of the road
			roadText = ImageIO.read(new File("road.png"));
			// Images of album covers in chronological order
			album1 = ImageIO.read(new File("college-dropout.jpg"));
			album2 = ImageIO.read(new File("late-reg.jpg"));
			album3 = ImageIO.read(new File("graduation.jpg"));
			// Not an album but how could I pass this moment up lol
			album4 = ImageIO.read(new File("greatest.jpg"));
			album5 = ImageIO.read(new File("wtt.jpg"));
			album6 = ImageIO.read(new File("yeezus.jpg"));
			album7 = ImageIO.read(new File("TLOP.jpg"));
			// Start screen Image
			kanyeStart = ImageIO.read(new File("start-kanye.png"));
		}
		// catch the exception
		catch (IOException e) {
			e.printStackTrace();
		}
		// initialize Kanye objects in the array
		for (int i = 0; i < road.length; i++)
			road[i] = new Kanye();
	}

	public void paint(Graphics g) {
		// Check if we are at the start screen
		if (startMenu) {
			// Background of start screen
			g.drawImage(kanyeStart, 0, 0, 1000, 1100, null);

			Graphics2D g2d = (Graphics2D) g;

			g.setFont(new Font("arial", Font.BOLD, 100));
			// Set color to red 
			g.setColor(Color.RED);
			// Put title on the screen
			g.drawString("DON'T HIT KANYE!", 40, 400);

			Font fnt1 = new Font("arial", Font.BOLD, 60);
			g.setFont(fnt1);
			// show play button
			g.drawString("Play", playButton.x + 38, playButton.y + 60);
			g2d.draw(playButton);
			// show quit button
			g.drawString("Quit", quitButton.x + 38, quitButton.y + 60);
			// Show highscore
			g.drawString("High Score To Beat!", 200, 800);
			String rS = readScores();
			g.drawString(rS, ((1000 / 2) - (rS.length() * 15)), 900);
			g2d.draw(quitButton);
		} else {
			// change backgrounds depending on score
			if (score < 5000) {
				backG = roadText;
			} else if (score >= 5000 && score <= 10000) {
				backG = album1;
			} else if (score > 10000 && score < 15000) {
				backG = album2;
			} else if (score > 15000 && score < 20000) {
				backG = album3;
			} else if (score > 20000 && score < 25000) {
				backG = album4;
			} else if (score > 25000 && score < 30000) {
				backG = album5;
			} else if (score > 30000 && score < 35000) {
				backG = album6;
			} else if (score > 35000 && score < 40000) {
				backG = album7;
			}
			g.drawImage(backG, 0, 0, 1000, 1100, null);
			g.drawImage(userCar, x, y, 200, 200, null);
			// Put Kanye objects on the panel
			for (Kanye i : road) {
				i.paint(g);
			}
			// Score is updated on the panel and shown in upper left corner
			g.setFont(new Font("Stencil", Font.BOLD, 36));
			g.setColor(Color.WHITE);
			g.drawString("Score: " + score, 20, 40);
			// Display GAME OVER when the game ends
			if (gameOver) {
				g.setFont(new Font("Stencil", Font.BOLD, 150));
				g.setColor(Color.RED);
				g.drawString("GAME OVER", 40, 500);
				g.setFont(new Font("Stencil", Font.BOLD, 60));
			}
		}
	}

	// Move user's car left
	public void goLeft() {
		// Limited within the frame's bounds
		if (x != 0)
			x -= 200;
	}

	// Move user's car right
	public void goRight() {
		// Limited within the frame's bounds
		if (x != 800)
			x += 200;
	}

	public void update(Game gm) {
		// Update the positions of the Kanye objects
		for (Kanye i : road)
			i.update(gm);
	}

	// Game loop
	public void loopGame(Game gm) {
		while (!gameOver) {
			try {
				gm.update(gm);
				gm.repaint();
			} catch (Exception e) {
				System.err.println("Panel Failed to Update Graphics");
			}
			// increase score
			score += 5;
			try {
				// calculate FPS for the game (1000 / fps) = x ms
				Thread.sleep(1000 / FPS);
			} catch (InterruptedException e) {
				System.err.println("Thread.Sleep Error");
			}
		}
		gm.writeScore();
		if (JOptionPane.showConfirmDialog(null, "Play Again?") == JOptionPane.YES_OPTION) {
			playAgain = true;
			// Give the user 2 seconds to get back to the game window
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				System.err.println("Thread.Sleep Error");
			}
		} else {
			playAgain = false;
			startMenu = true;
			gm.menuScreen(gm);
		}
		if (playAgain) {
			gm.reset(gm);
		}
	}

	public void reset(Game gm) {
		score = 0;
		x = 400;
		y = 800;
		road = new Kanye[numKanyesOnTheRoad];
		// initialize Kanye objects in the array
		for (int i = 0; i < road.length; i++) {
			road[i] = new Kanye();
		}
		gm.update(gm);
		gm.repaint();
		playAgain = false;
		gameOver = false;
		gm.loopGame(gm);
	}

	// loop for menu screen to check for any changes
	public void menuScreen(Game gm) {
		while (startMenu) {
			gm.repaint();
			try {
				Thread.sleep(1000 / 24);
			} catch (InterruptedException e) {
				System.err.println("Thread.Sleep Interruption");
			}
		}
		gm.reset(gm);
	}

	// Write the player's score to a file
	public void writeScore() {
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter("highscores.txt", true));
			output.newLine();
			output.append(username + ":" + score);
			output.close();

		} catch (IOException ex1) {
			System.err.printf("ERROR writing score to file");
		}
	}

	// Read in the scores and find the highest to set as the top player
	public String readScores() {
		int highest = 0;
		DecimalFormat formatter = new DecimalFormat("#,###");
		String topPlayer = "";
		String player = "";
		try {
			BufferedReader r = new BufferedReader(new FileReader("highscores.txt"));
			String line = "";
			line = r.readLine();
			while (line != null) {
				player = line.substring(0, line.indexOf(":"));
				line = line.substring(line.indexOf(":") + 1, line.length());
				try {
					int s = Integer.parseInt(line.trim());
					if (s > highest) {
						highest = s;
						topPlayer = player;
					}
				} catch (NumberFormatException e1) {
				}
				line = r.readLine();
			}
			r.close();

		} catch (IOException ex) {
			System.err.println("ERROR reading scores from file");
		}
		if (topPlayer == "" && highest == 0)
			return "NO SCORES";
		return topPlayer + ": " + formatter.format(highest);
	}

	public static void main(String[] args) throws Exception {
		Game game = new Game();
		JFrame frame = new JFrame();
		UserActions actions = new UserActions(game);
		MouseInput mu = new MouseInput(game);
		// try to play audio - mute the system for no music
		try {
			// instantiate Clip object
			Clip clip = AudioSystem.getClip();
			// plays the chorus of Ultra Light Beam - a Kanye Song
			clip.open(AudioSystem.getAudioInputStream(new File("ulb_short.wav")));
			// Loop this jam until the user closes the game
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		// catch the exception
		catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
		frame.add(game);
		frame.setVisible(true);
		frame.setSize(1000, 1100);
		frame.setTitle("Don't Hit Kanye!");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.addKeyListener(actions);
		frame.addMouseListener(mu);
		game.menuScreen(game);

	}

}