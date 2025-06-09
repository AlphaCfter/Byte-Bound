package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class GameObjects {
	
	public BufferedImage img;
	public String name;
	public boolean collision = false;
	public int posX, posY;
	public Rectangle solidAreaRectangle = new Rectangle(0,0,48,48);
	public int defaultSolidAreaX =0;
	public int defaultSolidAreaY=0;
	
	
	public void draw(Graphics2D g, GamePanel panel) {
		int screenX = posX - panel.player.worldX + panel.player.screenX;
		int screenY = posY - panel.player.worldY + panel.player.screenY;
		
		
		/**drawImage overrides the repaint component and paints the values in
		 * @param screenX Current position of the payer on the world coordinates
		 */
		if(posX + panel.tileSize > panel.player.worldX - panel.player.screenX &&
				posX - panel.tileSize < panel.player.worldX + panel.player.screenX &&
				posY + panel.tileSize > panel.player.worldY - panel.player.screenY &&
				posY - panel.tileSize < panel.player.worldY + panel.player.screenY) {
			
		g.drawImage(img, screenX,screenY,panel.tileSize,panel.tileSize,null);
		
		}
	}
}

