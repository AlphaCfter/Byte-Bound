package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Key extends GameObjects{
	public Key() {
		name = "Key";
		try {
			img = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision=true;
	}
}
