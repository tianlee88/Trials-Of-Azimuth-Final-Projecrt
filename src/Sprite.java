import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This abstract class is for all objects that will be rendered on the screen by the AzimuthGame class
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public abstract class Sprite {
	
	private int x;						//starting x coordinate
	private int y;						//starting y coordinate
	private String image_pathname;		//the image pathname
	private BufferedImage sprite_image;	//the BufferedImage generated from the image pathname
	private int width;					//the Sprite's width
	private int height;					//the Sprite's height
	
	/*
	 * This constructor takes in the image pathname, as well as the Sprite's starting x and y location
	 * It also attempts to create a BufferedImage from the supplied image pathname
	 */
	public Sprite(String _path, int _x, int _y) {
		image_pathname = _path;
		x = _x;
		y = _y;
		try {
          sprite_image = ImageIO.read(new File(image_pathname));
          width= sprite_image.getWidth();
          height= sprite_image.getHeight();
        }
        catch(IOException e) {
          System.out.println("Error: " + e);
          System.exit(1);
        }
	}
	
	/*
	 * This method changes the BufferedImage that was saved for this sprite to the image denoted by the String image pathname
	 * @param image_pathname - the newly desired image for this sprite
	 */
	public void changeImage(String image_pathname) {
		try {
	          sprite_image = ImageIO.read(new File(image_pathname));
	          width= sprite_image.getWidth();
	          height= sprite_image.getHeight();
	        }
	        catch(IOException e) {
	          System.out.println("Error: " + e);
	          System.exit(1);
	        }
	}
	
	/*
	 * This method returns a Rectangle that represents the Sprite's area
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	/*
	 * sets x to the new_x value
	 */
    public void setX(int new_x) {
        x = new_x;
    }

    /*
     * sets y to the new_y value
     */
    public void setY(int new_y) {
        y = new_y;
    }

    /*
     * returns x
     */
    public int getX(){
      return x;
    }

    /*
     * returns y
     */
    public int getY(){
      return y;
    }
    
    /*
     * returns the height of the Sprite
     */
    public int getHeight(){
      return height;
    }

    /*
     * returns the width of the Sprite
     */
    public int getWidth(){
      return width;
    }

    /*
     * updates the x value by a value delta
     */
    public void updateX(int delta) {
        x += delta;
    }

    /*
     * updates the y value by a value delta
     */
    public void updateY(int delta) {
        y += delta;
    }
    
    /*
     * This method will draw the Sprite on the screen
     */
   void draw(Graphics g) {
       g.setColor(Color.BLACK);
       g.drawImage(sprite_image, x, y, null);
   }
    
   	/*
   	 * This abstract method will be for updating each Sprite's values as the game runs
   	 */
    public abstract void update();
    
    /*
     * This abstract method will be for determining what happens when a specific Sprite collides with other Sprites
     */
    public abstract void collidedWith(Sprite other_sprite);

}
