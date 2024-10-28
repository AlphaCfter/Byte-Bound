package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public boolean upArrow, downArrow, leftArrow, rightArrow;
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int k = e.getKeyCode();
		
		if (k == KeyEvent.VK_W || k == KeyEvent.VK_UP) {
			upArrow = true;
		}
		if (k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN) {
			downArrow = true;
		}
		if (k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT) {
			leftArrow = true;
		}
		if (k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT) {
			rightArrow = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_W || k == KeyEvent.VK_UP) {
			upArrow = false;
		}
		if (k == KeyEvent.VK_S || k == KeyEvent.VK_DOWN) {
			downArrow = false;
		}
		if (k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT) {
			leftArrow = false;
		}
		if (k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT) {
			rightArrow = false;
		}
	}

}
