package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Door extends GameObjects {
	public Door() {
		
		name = "Door";
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/objects/door"));
		}catch(IOException e){
			e.printStackTrace();
		}
		collision = true;
	}
}
