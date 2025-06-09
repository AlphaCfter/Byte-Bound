package main;

import entities.Entity;

public class CollisionHandler {
	
	private GamePanel gp;
	
	public CollisionHandler(GamePanel gp) {
		this.gp=gp;
	}
	
	public void checkTile(Entity entity) {
		
		/**
		 * These values helps finding out the exact coordinates of the 4 points of a rectangle
		 * we have made to detect collision with respective the main player collition area
		 */
		int entityLeft = entity.worldX + entity.spriteAreaRectangle.x;
		int entityRight = entity.worldX + entity.spriteAreaRectangle.x + entity.spriteAreaRectangle.width;
		int entityTop = entity.worldY + entity.spriteAreaRectangle.y;
		int entityBottom = entity.worldY + entity.spriteAreaRectangle.y + entity.spriteAreaRectangle.height;
		
		/**
		 * Since corrdinates of the collision area with respective a player is found, now we
		 * scale back to the original size as per the original tile size is (default 16)
		 */
		int entityLeftCol = entityLeft / gp.tileSize;
		int entityRightCol = entityRight / gp.tileSize;
		int entityTopRow = entityTop / gp.tileSize;
		int entityBottomRow = entityBottom / gp.tileSize;
		
		int tile1, tile2;
		
		switch (entity.direction) {
	    case "up":
	        entityTopRow = (entityTop - entity.speed) / gp.tileSize;
	        tile1 = gp.tileManager.mapTileID[entityLeftCol][entityTopRow];
	        tile2 = gp.tileManager.mapTileID[entityRightCol][entityTopRow];
	        if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision) {
	            entity.collisionStatus = true;
	        }
	        break;
	    case "down":
	        entityBottomRow = (entityBottom + entity.speed) / gp.tileSize;
	        tile1 = gp.tileManager.mapTileID[entityLeftCol][entityBottomRow];
	        tile2 = gp.tileManager.mapTileID[entityRightCol][entityBottomRow];
	        if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision) {
	            entity.collisionStatus = true;
	        }
	        break;
	    case "left":
	        entityLeftCol = (entityLeft - entity.speed) / gp.tileSize;
	        tile1 = gp.tileManager.mapTileID[entityLeftCol][entityTopRow];
	        tile2 = gp.tileManager.mapTileID[entityLeftCol][entityBottomRow];
	        if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision) {
	            entity.collisionStatus = true;
	        }
	        break;
	    case "right":
	        entityRightCol = (entityRight + entity.speed) / gp.tileSize;
	        tile1 = gp.tileManager.mapTileID[entityRightCol][entityTopRow];
	        tile2 = gp.tileManager.mapTileID[entityRightCol][entityBottomRow];
	        if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision) {
	            entity.collisionStatus = true;
	        }
	        break;
	}
		
	}
	
	public int checkIfPlayer(Entity entity, boolean player) {
		
		int index = Integer.MAX_VALUE;
		
		//loops through the array and finds if the player interracts with the solidArea Reactangle
		for(int i=0; i<gp.obj.length; i++) {
			
			if(gp.obj[i] != null) {
				
				//Determining the entity position in the world
				entity.spriteAreaRectangle.x = entity.worldX + entity.spriteAreaRectangle.x;
				entity.spriteAreaRectangle.y = entity.worldY + entity.spriteAreaRectangle.y;
				//Determining the object position in the world
				gp.obj[i].solidAreaRectangle.x = gp.obj[i].posX + gp.obj[i].solidAreaRectangle.x;
				gp.obj[i].solidAreaRectangle.y = gp.obj[i].posY + gp.obj[i].solidAreaRectangle.y;
				
				/**
				 * Checks if the sprite area is intersecing with the solid Area space and if they
				 * are intersecting, set collision status of the object as true and its its a player
				 * then defenetly it would return the index of the scanned array
				 */
				switch(entity.direction) {
				case "up":
					entity.spriteAreaRectangle.y -= entity.speed;
					if(entity.spriteAreaRectangle.intersects(gp.obj[i].solidAreaRectangle)) {
						if(gp.obj[i].collision ==true) { //to check for solid status
							entity.collisionStatus = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.spriteAreaRectangle.y += entity.speed;
					if(entity.spriteAreaRectangle.intersects(gp.obj[i].solidAreaRectangle)) {
						if(gp.obj[i].collision ==true) {
							entity.collisionStatus = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "left":
					entity.spriteAreaRectangle.x -= entity.speed;
					if(entity.spriteAreaRectangle.intersects(gp.obj[i].solidAreaRectangle)) {
						if(gp.obj[i].collision ==true) {
							entity.collisionStatus = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "right":
					entity.spriteAreaRectangle.x += entity.speed;
					if(entity.spriteAreaRectangle.intersects(gp.obj[i].solidAreaRectangle)) {
						if(gp.obj[i].collision ==true) {
							entity.collisionStatus = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				}
				entity.spriteAreaRectangle.x = entity.defaultSolidAreaX;
				entity.spriteAreaRectangle.y = entity.defaultSolidAreaY;
				gp.obj[i].solidAreaRectangle.x = gp.obj[i].defaultSolidAreaX;
				gp.obj[i].solidAreaRectangle.y = gp.obj[i].defaultSolidAreaY;
			}
			

			
		}
		
		return index;
	}
}
