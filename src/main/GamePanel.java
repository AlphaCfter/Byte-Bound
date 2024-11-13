package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import tile.TileManager;
import entities.Player;
import inputs.KeyHandler;
import objects.GameObjects;

public class GamePanel extends JPanel implements Runnable {
	
	/**
	 * Sets the default title size to 16 x 16 size since its the standard being maintained
	 * Since latest computers have higher resolution, the tileSize would look small emough
	 */
	private final int originalSize = 16;
	private final int fractionalScale = 3;
	
	/**
	 * Since every bits of the pixels are being scaled 3 times, it looks about a decent size
	 * Every assets are gonna have about 16x16 images overall
	 * These pixel values are multiplied at row wise and coulnm wise onto the java frame
	 */
	public final int tileSize = originalSize * fractionalScale;
	public final int screenColumn = 16;
	public final int screenRow = 12;
	public final int screenWidth = tileSize * screenColumn;
	public final int screenHeight = tileSize * screenRow;
	
	protected TileManager tileManager = new TileManager(this);
	private Thread gameThread;
	private KeyHandler key = new KeyHandler();
	public CollisionHandler collision = new CollisionHandler(this);
	public AssetLoader assetLoad = new AssetLoader(this);
	public Player player = new Player(this,key);
	//prepare 10 slots like an inventory to place objects in the map
	public GameObjects obj[] = new GameObjects[10]; 
	
	
	//World Map Data. To change the world map default settings, change here
	public final int maxWorldColumn = 50;
	public final int maxWorldRow = 50;
	
	//World Map settigs including the TILE SIZE
	public final int worldWidth = tileSize * maxWorldColumn;
	public final int worldHeight = tileSize * maxWorldRow;
	
	//FPS fixed to 60 by default
	private int FPS = 60;
		
	public GamePanel() {
		/**
		 * <code>setDoubleBuffered</code> sets the game component to paint with buffers insted
		 * of instantiate the repaint function to improve performance. 
		 * For more info {@link javax.swing.JPanel}
		 */
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true); //focused to listen the key pressed
	}
	
	public void loadAssets() {
		assetLoad.setObjects();
	}
	
	public void beginGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		while(gameThread != null) {
			
			/**
			 * Since this 2D game is a light weight component to the CPU which operates in
			 * Billions instructions per second so, we limit the game FPS to default 60 
			 * where only 60 frames are computed in one CPU clock cycle.
			 * Right after a key is pressed, the object disappears due to high speed 
			 * CPU freqency. Game loop is built on nanoseconds for precition.
			 */
			double firstTime = 1000000000/FPS;
			double lastTime= System.nanoTime() + firstTime;
			/**
			 * Anything put into the run() method will keep updating itself since the entire object 
			 * keeps refreshing itself. Critical components like the <strong>gamePanel</strong> 
			 * components keeps updating
			 * <ol>
			 * <li>Updates the game sprites like the characters like the Main characters pos</li>
			 * <li>Draws the updating information on the screen when interrupt is requested<li>
			 * </ol>
			 * 
			 */
			update();
			repaint();
			
			/**
			 * Remaining time from the first clock cycle and the high speed CPU time is subtracted
			 * and the remaining time is left for the thread to sleep since thats the left out time.
			 * <code>Thread.sleep()</code> is typecasted due to the limitations present in the thread class
			 * @see Thread#sleep(long)
			 */
			
			try {
				double remainingTime = lastTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) remainingTime=0;
				
				Thread.sleep((long)remainingTime);
				
				//right after the first frame, reset the attributes
				lastTime += firstTime;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	/**
	 * Overrides the paintComponent in the parent class where the method performs the operation
	 * to re-draw the entire component on the panel frame. This function is called in the run() method
	 * <stong>Anything needed to be added to the frame will be declared here</strong>
	 * @param  g is the graphics refrence passes to the function where graphics is more like a pencil
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D gg = (Graphics2D)g;
		// tile assets
		tileManager.draw(gg);
		
		//object assets with nullpointer checks
		for(int i =0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(gg, this);
			}
		}
		//player assets
		player.draw(gg);
		gg.dispose();
	}
	
	/**
	 * Update method does the job to updates itself repetedly since this function is called in the run() method
	 */
	public void update() {
		player.update();
		
	}
	
}
