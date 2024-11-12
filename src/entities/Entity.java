package entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int worldX;
	public int worldY;
	public int speed;
	public String direction;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	
	protected int spriteTick = 0;
	protected int spriteNum = 1;
	
	// A rectangle class is made to create an area within the ENTITY sprite where area within
	// cannot be collided with any tile marked for SOLID area
	public Rectangle spriteArea;
	
	// A shared variable where the collision handler updates the status based on the current
	// key combination as well as the tile if set to a solid state
	public boolean collisionStatus = false;
}
