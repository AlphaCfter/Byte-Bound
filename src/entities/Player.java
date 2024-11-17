package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import inputs.KeyHandler;
import main.GamePanel;

public class Player extends Entity{
	
	private GamePanel panel;
	private KeyHandler key;
	
	public final int screenX;
	public final int screenY;
	private int hasKey;
	
	public Player(GamePanel panel, KeyHandler key) {
		this.panel = panel;
		this.key = key;
		/**
		 * screenX and screenY is the players position which is fixed at all cost while the 
		 * world map rotates and makes the player move with Keyboard inputs
		 */
		screenX = panel.screenWidth/2 - (panel.tileSize/2);
		screenY = panel.screenHeight/2 - (panel.tileSize/2);
		
		spriteAreaRectangle = new Rectangle();
		spriteAreaRectangle.x=5;
		spriteAreaRectangle.y=16;
		defaultSolidAreaX = spriteAreaRectangle.x;
		defaultSolidAreaY = spriteAreaRectangle.y;
		spriteAreaRectangle.width = 38;
		spriteAreaRectangle.height = 29;
		
		setDefaultValues();
		setPlayerPos();
	}
	
	public void setDefaultValues() {
		worldX =panel.tileSize * 9;
		worldY =panel.tileSize * 7;
		speed =4;
		direction = "down"; //sets player's default position
	}
	
	private void setPlayerPos() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up-1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up-2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down-1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down-2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right-1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right-2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left-1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left-2.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if (key.upArrow == true || key.downArrow == true || key.rightArrow == true || key.leftArrow == true) {
		if(key.upArrow == true) {
			direction = "up";
		}
		else if(key.downArrow == true) {
			direction = "down";
			
		}
		else if(key.leftArrow == true) {
			direction = "left";
			
		}
		else if(key.rightArrow == true) {
			direction = "right";
			
		}

		// shared Variable which checks for SOLID blocks
		collisionStatus = false;
		panel.collision.checkTile(this);
		
		int index = panel.collision.checkIfPlayer(this, true);
		pickUpObject(index);
		
		// move only of its a non SOLID block
		if(collisionStatus == false) {
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "left": worldX -= speed; break;
			case "right": worldX += speed; break;
			}
		}
		
		/**
		 * A simple logic to implement animation when a key event is pressed. The spriteTick
		 * is inititally incremented then when ticks reach about a count of <stong>10 frames</strong>
		 * the animation changes to 2nd frame and the ticks are broght to zero at initial stage itself.
		 * All these variables are inherited from the entity class.
		 * @see Entity
		 */
		
		spriteTick++;
		if(spriteTick > 10) {
			if(spriteNum == 1) spriteNum = 2;
			else if(spriteNum == 2) spriteNum = 1;
			spriteTick = 0;
		}
	}
}
	
	public void pickUpObject(int index) {
		if(index != Integer.MAX_VALUE) {
			String objectName = panel.obj[index].name;
			switch(objectName) {
			case "Key":
				hasKey++;
				panel.obj[index]=null;
				break;
			case "Door":
				if(hasKey > 0) {
					panel.obj[index] = null;
					hasKey--;
				}
				break;
			}
		}
	}
	
	public void draw(Graphics2D gg) {
		//gg.setColor(Color.white);
		//gg.fillRect(x, y, panel.tileSize, panel.tileSize);
		
		BufferedImage img = null;
		switch(direction) {
		case "up": {
			if(spriteNum == 1) img = up1; 
			if(spriteNum == 2) img = up2;
			break;
		}
		case "down": {
			if(spriteNum == 1) img = down1; 
			if(spriteNum == 2) img = down2;
			break;
		}
		case "left": {
			if(spriteNum == 1) img = left1; 
			if(spriteNum == 2) img = left2; 
			break;
		}
		case "right": {
			if(spriteNum == 1) img = right1; 
			if(spriteNum == 2) img = right2; 
			break;
		}
	}
		
		gg.drawImage(img,screenX,screenY,panel.tileSize,panel.tileSize,null);
	}
}
