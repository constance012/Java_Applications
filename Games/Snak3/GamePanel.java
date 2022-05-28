package Snak3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
	// Declare constants, attributes.
	static final int SCREEN_WIDTH = 500;
	static final int SCREEN_HEIGHT = 500;
	static final int UNIT_SIZE = 25;  // The size of the object in game.
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;  // Maximum length of the snake.
	static final int DELAY_RATE = 100;  // Similar to FPS
	final int x[] = new int[GAME_UNITS];  // Hold the x coordinates of the snake
	final int y[] = new int[GAME_UNITS];  // Hold the y coordinates of the snake
	int bodyParts = 5;  // Number of initial body parts
	int score;  // Score
	int foodX, foodY;  // Coordinate of the food
	char direction = 'R';  // Starting direction
	boolean running = false;  // The current state of the game
	Timer timer;  // Timer object.
	Random rd;  // Random object.
	
	// Constructor.
	GamePanel(){
		rd = new Random();
		
		// Configure the panel.
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));  // Set the preferred size of the window.
		setBackground(Color.WHITE);  // Background color.
		setFocusable(true);  // Focus state.
		addKeyListener(new MyKeyAdapter());  // Add specified key listener.
		startGame();
	}
	
	public void startGame() {
		newFood();
		running = true;
		timer = new Timer(DELAY_RATE, this);  // Set the "FPS" of the game. 
		timer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if (running) {
			// Drawing the grid for visualize things, OPTIONAL.
			/*
			for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);  // X axis
				g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);  // Y axis
			}*/
			
			// Draw the food.
			g.setColor(Color.RED);
			g.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);
			
			// Draw the snake
			for (int i = 0; i < bodyParts; i++) {
				if (i == 0) {  // Head
					g.setColor(Color.BLACK);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				
				else {  // Body
					g.setColor(new Color(80, 80, 80));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			
			// Display the score
			g.setColor(Color.BLACK);  // Text color
			g.setFont(new Font("DS-Digital", Font.PLAIN, 30));  // Font style
			
			// Font metrics for placing text
			FontMetrics met = getFontMetrics(g.getFont());
			g.drawString("Scores: " + score, (SCREEN_WIDTH - met.stringWidth("Scores: " + score)) / 2, 30);
		}
		
		// If not running, then the game is over.
		else
			gameOver(g);
	}
	
	public void newFood() {
		// Generate random food position.
		foodX = rd.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		foodY = rd.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
	}
	
	public void move() {
		// Move the snake body by shifting each segment toward the head.
		for (int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		
		// Move the head based on the direction.
		switch(direction) {
			case 'U':
				y[0] -= UNIT_SIZE;
				break;
			case 'D':
				y[0] += UNIT_SIZE;
				break;
			case 'L':
				x[0] -= UNIT_SIZE;
				break;
			case 'R':
				x[0] += UNIT_SIZE;
				break;
		}
	}
	
	public void checkFood() {
		if (x[0] == foodX && y[0] == foodY) {
			bodyParts++;
			score++;
			newFood();
		}
	}
	
	public void collisionsTest() {
		// Check if the head collides with the body.
		for (int i = bodyParts; i > 0; i--) {
			if (x[0] == x[i] && y[0] == y[i])
				running = false;
		}
		
		// Check if the head touches the left border.
		if (x[0] <= -10)
			running = false;
		
		// Check if the head touches the right border.
		if (x[0] >= SCREEN_WIDTH)
			running = false;
		
		// Check if the head touches the top border.
		if (y[0] <= -10)
			running = false;
		
		// Check if the head touches the bottom border.
		if (y[0] >= SCREEN_HEIGHT)
			running = false;
		
		if (!running)
			timer.stop();
	}
	
	public void gameOver(Graphics g) {
		// Set up texts
		g.setColor(Color.BLACK);  // Text color
		g.setFont(new Font("DS-Digital", Font.PLAIN, 70));  // Font style
		
		// Font metrics for placing text
		FontMetrics met1 = getFontMetrics(g.getFont());
		g.drawString("GAME OVER", (SCREEN_WIDTH - met1.stringWidth("GAME OVER")) / 2, (SCREEN_HEIGHT / 2) - 30);
		
		// Display the score
		g.setColor(Color.BLACK);  // Text color
		g.setFont(new Font("DS-Digital", Font.BOLD, 30));  // Font style
		
		// Font metrics for placing text
		FontMetrics met2 = getFontMetrics(g.getFont());
		g.drawString("Scores: " + score, (SCREEN_WIDTH - met2.stringWidth("Scores: " + score)) / 2, (SCREEN_HEIGHT / 2) + 30);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {  // When the game is running.
			move();
			checkFood();
			collisionsTest();
		}
		
		// Otherwise.
		repaint();
	}
	
	// Inner class
	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				// If the direction isn't RIGHT then can go LEFT, prevent 180 degree turns, similar with other cases
				case KeyEvent.VK_A:
					if (direction != 'R')
						direction = 'L';
					break;
				
				// RIGHT turns.
				case KeyEvent.VK_D:
					if (direction != 'L')
						direction = 'R';
					break;
				
				// UP turns.
				case KeyEvent.VK_W:
					if (direction != 'D')
						direction = 'U';
					break;
					
				// DOWN turns.
				case KeyEvent.VK_S:
					if (direction != 'U')
						direction = 'D';
					break;
			}
		}
	}
}
