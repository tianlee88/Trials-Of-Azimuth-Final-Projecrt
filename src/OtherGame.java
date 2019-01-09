import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class OtherGame extends JFrame implements Runnable{
	
	
	 private boolean isRunning;		//boolean to ensure the game is still running
	  private int fps = 60;					//the current frames per second for the game
	  private int windowWidth = 1240;		//the width of the window
	  private int windowHeight = 840;		//the height of the window
	  private Insets insets;				//the insets for this frame
	  private ArrayList<NonmovingSprite> room_sprites;	//list of all non-moving sprites in the room
	  private ArrayList<MovingSprite> moving_sprites;	//list of all moving sprites in the room
	  
	  private Hero hero;
	  private Fireball fireball;
	
	private static final long serialVersionUID = 1L;
	public int screenWidth = 1240;
	public int screenHeight = 840;
	private Thread thread;
	//private boolean running;
	private BufferedImage backBuffer;
	InputHandler input;

	public OtherGame() {
		thread = new Thread(this);
		backBuffer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);

		input = new InputHandler(this);
		//screen = new Screen(map, mapWidth, mapHeight, textures, 640, 480);
		addKeyListener(input);
		setSize(screenWidth, screenHeight);
		setResizable(false);
		setTitle("The Trials of Azimuth");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.black);
		setLocationRelativeTo(null);
		setVisible(true);
		
		  MapBuilder map = new MapBuilder();
		  room_sprites = map.buildMap();
		  moving_sprites = map.populateMap();
		  
		  hero = new Hero(620, 420);
		  fireball = new Fireball();
		  moving_sprites.add(hero);
		  moving_sprites.add(fireball);
		  
		  //want to add 4 health potions to the map
		  HealthPotion hp1 = new HealthPotion(-1300, -900, 100);
		  HealthPotion hp2 = new HealthPotion(-1300, 700, 100);
		  HealthPotion hp3 = new HealthPotion(1500, -800, 100);
		  HealthPotion hp4 = new HealthPotion(1500, 700, 100);
		  room_sprites.add(hp1);
		  room_sprites.add(hp2);
		  room_sprites.add(hp3);
		  room_sprites.add(hp4);
		  insets = getInsets();
		
		
		start();
	}
	
	private synchronized void start() {
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;//60 times per second
		double delta = 0;
		requestFocus();
		while(isRunning) {
	          long time = System.currentTimeMillis();
		          update();	//check for updates
		          draw();	//redraw the screen after the update
		          //  delay for each frame  -   time it took for one frame
		          time = (1000 / fps) - (System.currentTimeMillis() - time);
		          if (time > 0) {
		        	  try {
		        		  Thread.sleep(time);
		        	  }
		        	  catch(Exception e){
		        		  System.out.println("Error: " + e);
		              }
		          }
	          }
		setVisible(true);
	}
	
	//these variables are used in the update() method to shift the window's x and y coordinates on the map
	  int increase_window_x = 0;
	  int decrease_window_x = 0;
	  int increase_window_y = 0;
	  int decrease_window_y = 0;
	  
	  /**
	   * This method will check for input, move things
	   * around and check for if sprites are out of health
	   */
	 void update() {
		//these statements detect is WASD are being pressed
	     if (input.isKeyDown(KeyEvent.VK_A)) {
	         hero.moveLeft();
	     }
	     if (input.isKeyDown(KeyEvent.VK_D)) {
	         hero.moveRight();
	     }
	     if (input.isKeyDown(KeyEvent.VK_W)) {
	         hero.moveUp();
	     }
	     if (input.isKeyDown(KeyEvent.VK_S)) {
	         hero.moveDown();
	     }
	   // these statements detect if the ARROW keys are being pressed
	 if (input.isKeyDown(KeyEvent.VK_UP)) {
	     fireball.shoot(Fireball.UP);
	 }
	 if (input.isKeyDown(KeyEvent.VK_DOWN)) {
	     fireball.shoot(Fireball.DOWN);
	 }
	 if (input.isKeyDown(KeyEvent.VK_LEFT)) {
	   fireball.shoot(Fireball.LEFT);
	 }
	 if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
	   fireball.shoot(Fireball.RIGHT);
	 }
	 
	 //locks the fireball to the Hero
	 if (fireball.getLock() == true) {
		 fireball.setX(hero.getX() + 30);
		 fireball.setY(hero.getY() + 31);
	 }
	 
	 //this if statement locks the Fireball's (x, y) back to the Hero's (x,y) when the E key is pressed
   if (input.isKeyDown(KeyEvent.VK_E)) {
     fireball.setLock(true);
   }
	
	 // exits the game when the ESC key is pressed
	 if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {
	     System.exit(0);
	 }
	 
	 checkCollisions();		//check for collisions as often as possible
	 
	 
		// Perform all Sprite updates after collisions check
		for (int i = 0; i < moving_sprites.size(); i++) {
			Sprite current_sprite = moving_sprites.get(i);
			current_sprite.update();
			if(current_sprite instanceof MovingSprite) {
				MovingSprite moving_sprite = (MovingSprite) current_sprite;
				if (moving_sprite instanceof Enemy) {
					if (moving_sprite.getHealth() <= 0) {
						hero.increaseScore(1);
						moving_sprites.remove(i);
					}
				} else if (moving_sprite instanceof Hero) {
					if (moving_sprite.getHealth() <= 0) {
						moving_sprite.setX(620);
						moving_sprite.setY(420);
					}
				}
			}
		}

	 //THE FOLLOWING CODE IS FOR THE WINDOW SHIFTING
	  if (hero.getX() > (windowWidth/2)){
	    increase_window_x += 1;
	  }
	 
	  if (increase_window_x > 0){
	    for (Sprite a_sprite : moving_sprites){
	      a_sprite.setX(a_sprite.getX() - Hero.SPEED);
	    }
	    for (Sprite a_wall : room_sprites) {
	        a_wall.setX(a_wall.getX() - Hero.SPEED);
	    }
	    increase_window_x = 0;
	  }
	 
	  if (hero.getX() < (windowWidth/2)){
	    decrease_window_x += 1;
	  }
	 
	  if (decrease_window_x > 0){
	      for (Sprite a_sprite : moving_sprites){
	          a_sprite.setX(a_sprite.getX() + Hero.SPEED);
	        }
	        for (Sprite a_wall : room_sprites) {
	            a_wall.setX(a_wall.getX() + Hero.SPEED);
	        }
	    decrease_window_x = 0;
	  }
	 
	  if (hero.getY() > (windowHeight/2)){
	    increase_window_y += 1;
	  }
	 
	  if (increase_window_y > 0){
	      for (Sprite a_sprite : moving_sprites){
	          a_sprite.setY(a_sprite.getY() - Hero.SPEED);
	        }
	        for (Sprite a_wall : room_sprites) {
	            a_wall.setY(a_wall.getY() - Hero.SPEED);
	        }
	    increase_window_y = 0;
	  }
	 
	  if (hero.getY() < (windowHeight/2)){
	    decrease_window_y += 1;
	  }
	 
	  if (decrease_window_y > 0){
	      for (Sprite a_sprite : moving_sprites){
	          a_sprite.setY(a_sprite.getY() + Hero.SPEED);
	        }
	        for (Sprite a_wall : room_sprites) {
	            a_wall.setY(a_wall.getY() + Hero.SPEED);
	        }
	    decrease_window_y = 0;
	  }
	  
	  //END SHIFT CODE
	     
	     
	 }
	
	 /**
	  * This method will draw everything onto the frame
	  */
	 void draw() {
	     Graphics g = getGraphics();
	     Graphics bbg = backBuffer.getGraphics();
	     bbg.setColor(Color.BLACK);
	     bbg.fillRect(0, 0, windowWidth, windowHeight);
	     //all_sprites.get(0).draw(bbg);
	 
		 //following loop draws all of the sprites in the list of sprites currently in the game
		 for (int i = 0; i < room_sprites.size(); i++) {
			 room_sprites.get(i).draw(bbg);
		 }
		 for(int j = 0; j < moving_sprites.size();j++) {
			 moving_sprites.get(j).draw(bbg);
			 
			 int healthbar_width = 100;
			 int healthbar_height = 10;
			 int shift_x = 25;
			 int shift_y = 20;
		     bbg.setColor(Color.RED);
		     
			 if (moving_sprites.get(j) instanceof Enemy) {
				 Enemy e = (Enemy) moving_sprites.get(j);
				 bbg.drawRect(e.getX()-shift_x, e.getY() -shift_y, healthbar_width, healthbar_height);
			     bbg.fillRect(e.getX()-shift_x, e.getY() -shift_y, e.getHealth(), healthbar_height);
			     String s = Integer.toString(e.getHealth()) + "/100";
			     bbg.drawString(s, e.getX(), e.getY() -25);
			 } else if (moving_sprites.get(j) instanceof Hero) {
				 bbg.drawRect(hero.getX()-shift_x, hero.getY()-shift_y, healthbar_width, healthbar_height);
			     bbg.fillRect(hero.getX()-shift_x, hero.getY()-shift_y, hero.getHealth() / (hero.getMaxHealth()/100), healthbar_height);
			     String s = "Health = " + Integer.toString(hero.getHealth()) + "/" + Integer.toString(hero.getMaxHealth());
			     String score = "Current Score = " + Integer.toString(hero.getScore());
			     bbg.drawString(s, hero.getX()-shift_x-2, hero.getY()-shift_y-5);
			     bbg.drawString(score, hero.getX()-shift_x, hero.getY()+shift_y*4);
			     if (moving_sprites.get(j).getHealth() <= 0) {
			    	 BufferedImage game_over;
			    	 try {
			             game_over = ImageIO.read(new File("gameover.png"));
				    	 bbg.drawImage(game_over, 495, 200, null);
			           }
			           catch(IOException e) {
			             System.out.println("Error: " + e);
			             System.exit(1);
			           }
			     } else if (hero.getScore() == 4) {
			    	 BufferedImage you_win;
			    	 try {
			             you_win = ImageIO.read(new File("youwin.png"));
				    	 bbg.drawImage(you_win, 420, 200, null);
			           }
			           catch(IOException e) {
			             System.out.println("Error: " + e);
			             System.exit(1);
			           } 
			     }
			 }
	     }
	     g.drawImage(backBuffer, insets.left, insets.top, this);
	  }
	 
	 /**
	  * This method detects collisions between Sprites using the intersects method for Rectangles
	  * @param s1 - the first sprite
	  * @param s2 - the second sprite
	  * @return - true if a collision occurred, otherwise false
	  */
	 public boolean spritesCollide(Sprite s1, Sprite s2) {
		 return s1.getBounds().intersects(s2.getBounds());
	 }
	 
	 /**
	  * This method will check for collisions between all Sprites
	  */
	 void checkCollisions() {
		 for (Sprite s1 : moving_sprites) {
			 for (Sprite s2 : moving_sprites) {
				 if (spritesCollide(s1, s2)) {
					 s1.collidedWith(s2);
					 s2.collidedWith(s1);
				 }
			 }
			 for (Sprite s2 : room_sprites) {
				 if (spritesCollide(s1, s2)) {
					 s1.collidedWith(s2);
					 s2.collidedWith(s1);
				 }
			 }
		 }
	 }
	
//	 /**
//	  * This main method starts the game by creating a new AzimuthGame object, which starts the game
//	  * @param args
//	  */
//	 public static void main(String[] args) {
//		 AzimuthGame newgame = new AzimuthGame();
//		 newgame.run();
//	 }
	  
}
