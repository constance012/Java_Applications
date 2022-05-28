package Snak3

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	// Constructor.
	GameFrame(){
		// Shortcut to create an instance of JPanel.
		add(new GamePanel());
		
		// Configure the frame.
		setTitle("Snak3: Classic");  // Set the title of the window.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Set the exit method to [X] mark on the window.
		setResizable(false);  // Set if the window could be resized or not.
		pack();  // Fit the JFrame around all of the components that we added.
		setVisible(true);  // Set if the window is visible or not.
		setLocationRelativeTo(null);  // Put the window in the middle of the screen.
	}
}
