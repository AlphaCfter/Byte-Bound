package entities;

import java.awt.image.BufferedImage;

public class Entity {
	public int worldX;
	public int worldY;
	protected int speed;
	protected String direction;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	
	protected int spriteTick = 0;
	protected int spriteNum = 1;
}
