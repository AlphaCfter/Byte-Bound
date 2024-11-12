package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	private int numOfTiles = 8;
	
	private GamePanel panel;
	public Tile[] tile;
	public int mapTileID[][];
	
	public TileManager(GamePanel panel) {
		this.panel = panel;
		tile = new Tile[numOfTiles];
		mapTileID = new int[panel.maxWorldColumn][panel.maxWorldRow];
		setTile();
		loadMap("/maps/test.txt");
	}
	
	/**
	 * Method which recieves image buffer from the tile package and store them into an array.
	 * These are stored and retreved back on tho the java swinp panel
	 */
	public void setTile() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/sand.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass2.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/tree.png"));
			tile[4].collision=true;
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/earth.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tile/water.png"));
			tile[6].collision = true;
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tile/water2.png"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * The loadMap method gets input from the bffer stream via Input stream and reads the map
	 * data line by line and removes spaces between them and puts them onto the game panel
	 * @param mapPath accepts the map path set relative on maps package
	 * @see InputStream
	 * @see BufferedReader
	 */
	private void loadMap(String mapPath){	
		
			try {
				InputStream is = getClass().getResourceAsStream(mapPath);
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				int col=0, row=0;
				
				while(col < panel.maxWorldColumn && row < panel.maxWorldRow) {
				
					String line = br.readLine();
					
					while( col < panel.maxWorldColumn) {
							String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileID[col][row] = num;
					col++;
				}
				if( col == panel.maxWorldColumn) {
					col =0; 
					row++;
				}
				}
				br.close();
			}	
			catch (IOException e) {
				e.printStackTrace();
			}	
	}
	/**
	 * Uses the draw method to paint the conponent onto the game panel where this method is called
	 * in the game panel. It retreves indexes of the array stored in the previous method and calls
	 * the Paint component to paint the respective image
	 * @param g accepts a Graphics2D argument
	 * @see GamePanel
	 * @see Graphics2D
	 */
	
	//CAMERA FUNCTION
	public void draw(Graphics2D g) {
		
		int worldColumn=0;
		int worldRow=0;

		
		
		while(worldColumn < panel.maxWorldColumn && worldRow < panel.maxWorldRow) {
			int tileNum = mapTileID[worldColumn][worldRow];
			
			
			//WorldX and worldY is each pixel on the world map. By default the counting starts from 0,0
			//all the way till the world coloum(50)
			int worldX = worldColumn * panel.tileSize;
			int worldY = worldRow * panel.tileSize;
			
			//screenX and screenY is the position of the player over the map.
			//Since by-default its at the centre, the world coordinates update as input is given
			//Panel.player.screenX offsets the player value from the world boundaries
			int screenX = worldX - panel.player.worldX + panel.player.screenX;
			int screenY = worldY - panel.player.worldY + panel.player.screenY;
			
			/**drawImage overrides the repaint componeent and paints the values in
			 * @param screenX Current position of the payer on the world coordinates
			 */
			g.drawImage(tile[tileNum].image, screenX,screenY,panel.tileSize,panel.tileSize,null);
			worldColumn++;

			//when the count reaches the world column, returns the index value of worldColumn as 0
			//and start rendering the row values onto the screen
			if(worldColumn == panel.maxWorldColumn) {
				worldColumn = 0;

				worldRow++;

			}
		}
	}
}
