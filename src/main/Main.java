package main;

import javax.swing.JFrame;
/**
 * 2D Adventure game using Java Swing
 * 
 * @author Ajith Kumar
 * 
 */
public class Main {

	public static void main(String[] args) {
		
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setTitle("2DAdventure");
			
			GamePanel gamePanel = new GamePanel();
			frame.add(gamePanel);
			frame.pack();
			
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
			gamePanel.loadAssets();
			gamePanel.beginGameThread();
			
	}

}
