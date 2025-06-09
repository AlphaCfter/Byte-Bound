package main;

import objects.Key;

public class AssetLoader {
	
	private GamePanel gp;
	
	public AssetLoader(GamePanel gp) {
		this.gp=gp;
	}
	
	public void setObjects() {
		gp.obj[0] = new Key();
		gp.obj[0].posX = 23 * gp.tileSize;
		gp.obj[0].posY = 7 * gp.tileSize;
		
		gp.obj[1] = new Key();
		gp.obj[1].posX = 23 * gp.tileSize;
		gp.obj[1].posY = 40 * gp.tileSize;
	}
}
