import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This State class is for when a sprite is moving up/away from the bottom of the screen - implementation currently incomplete
 * @author Christian
 *
 */

public class SpriteStateUp implements SpriteState{
	
	String image_pathname;
	BufferedImage state_image;
	int width;
	int height;
	
	public SpriteStateUp(String pathname) {
		image_pathname = pathname;
		try {
	          state_image = ImageIO.read(new File(image_pathname));
	          width= state_image.getWidth();
	          height= state_image.getHeight();
		}
        catch(IOException e) {
          System.out.println("Error: " + e);
          System.exit(1);
        }
	}
	
	public BufferedImage getStateImage() {
		return state_image;
	}
	
}
