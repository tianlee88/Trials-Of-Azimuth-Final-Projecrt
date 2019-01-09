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
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * This class runs the actual game, The Trials of Azimuth
 * @authors Tian Lee, Paul Price and Christian Kennedy
 */
public class AzimuthGame extends JFrame implements Runnable{
	
	
	private boolean isRunning;			//boolean to ensure the game is still running
	private int fps = 60;				//the current frames per second for the game
	private int windowWidth = 1240;		//the width of the window
	private int windowHeight = 840;		//the height of the window
	private Insets insets;				//the insets for this frame
	
	private ArrayList<Sprite> all_sprites = new ArrayList<Sprite>();	//list of all sprites currently in the game
	private ArrayList<NonmovingSprite> enemy_spawns; 					//list of all enemy spawning locations
	private Hero hero;					//the Hero character
	private Fireball fireball;			//the Hero's Fireball attack
	
	private int screenWidth = 1240;		//the width of the game screen
	private int screenHeight = 840;		//the height of the game screen
		
	private int center_x = 582;			//used to send the Hero to the center of the screen
	private int center_y = 420;			//used to send the Hero to the center of the screen
	private int off_map = 5000;			//used for sending the Fireball off the map when the Hero is out of health
	
	private Thread thread;				//the Thread needed to play this game from the GUI
	private BufferedImage backBuffer;	//the image for the background of the frame
	private InputHandler input;			//the InputHandler for this frame
	
	private String hero_up = "player1_up.png";			//Hero up animation
	private String hero_down = "player1_down.png";		//Hero down animation
	private String hero_left = "player1_left.png";		//Hero left animation
	private String hero_right = "player1_right.png";	//Hero right animation

	/**
	 * This constructor sets up the necessary parts of the game, and then begins running the game
	 */
	public AzimuthGame() {

		initialize();
		MapBuilder map = new MapBuilder();		//the map for the game
		all_sprites.addAll(map.buildMap());		//adds the maps floor and walls to the list of sprites
		all_sprites.addAll(map.addItems());		//adds all items to the list of sprites
		all_sprites.addAll(map.populateMap());	//adds all starting enemies to the list of sprites

		enemy_spawns = map.addEnemySpawners();	//adds enemy spawn points to the map
		  
		hero = new Hero(center_x, center_y);	//creates the Hero for this game
		fireball = new Fireball();				//creates the Hero's Fireball
		all_sprites.add(fireball);			//adds the Hero to the list of moving sprites
		all_sprites.add(hero);				//adds the Fireball to the list of moving sprites
		//start();
	}
	
	public void initialize() {				//sets up the game and its frame
		thread = new Thread(this);
		backBuffer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
		input = new InputHandler(this);
		addKeyListener(input);
		setSize(screenWidth, screenHeight);
		setResizable(false);
		setTitle("The Trials of Azimuth");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.black);
		setLocationRelativeTo(null);
		setVisible(true);
		insets = getInsets();
	}
	
	public synchronized void start() {	//needed to run the thread
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {	//needed to stop the thread
		isRunning = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	public void run() {					//starts running the game
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
	
	  
	  /**
	   * This method will check for input, move things around if needed,
	   *  and check for if sprites are out of health
	   */
	 void update() {
		//these statements detect if WASD are being pressed
	    if (input.isKeyDown(KeyEvent.VK_A)) {
	        hero.moveLeft();
	        hero.changeImage(hero_left);	//changes the Hero's current picture to left
	    }
	    if (input.isKeyDown(KeyEvent.VK_D)) {
	        hero.moveRight();
	        hero.changeImage(hero_right);	//changes the Hero's current picture to right
	    }
	    if (input.isKeyDown(KeyEvent.VK_W)) {
	        hero.moveUp();
	        hero.changeImage(hero_up);		//changes the Hero's current picture to up
	    }
	    if (input.isKeyDown(KeyEvent.VK_S)) {
	        hero.moveDown();
	        hero.changeImage(hero_down);	//changes the Hero's current picture to down
	    }
	   // these statements detect if the ARROW keys are being pressed
	    if (input.isKeyDown(KeyEvent.VK_UP)) {
	    	fireball.shoot(Fireball.UP);	//shoots fireball up
	     }
	    if (input.isKeyDown(KeyEvent.VK_DOWN)) {
	    	fireball.shoot(Fireball.DOWN);	//shoots fireball down
	    }
	    if (input.isKeyDown(KeyEvent.VK_LEFT)) {
	    	fireball.shoot(Fireball.LEFT);	//shoots fireball left
	    }
	    if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
	    	fireball.shoot(Fireball.RIGHT);	//shoots fireball right
	    }
	 
		if (hero.getHealth() <= 0) {//locks the Hero to the center of the screen if out of health
			hero.setX(center_x);
			hero.setY(center_y);
			fireball.setLock(false);
			fireball.setX(off_map);
			fireball.setY(off_map);
		}
	 
		if (fireball.getLock() == true) {	//checks if the fireball is locked to the Hero's location
			int fireball_shift_x = 11;
			int fireball_shift_y = 20;
			fireball.setX(hero.getX() + fireball_shift_x);
			fireball.setY(hero.getY() + fireball_shift_y);
		}
	 
		if (input.isKeyDown(KeyEvent.VK_E)) {	//locks the fireball to the Hero's location when E is pressed
			fireball.setLock(true);
		}
	
		if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {	// exits the game when the ESC key is pressed
			System.exit(0);
		}
	 
		if (input.isKeyDown(KeyEvent.VK_P)) { 	 // ends the game when the P key is pressed and takes you to the PostGameScreen
			PostGameScreen pgs = new PostGameScreen(hero.getScore());
			this.dispose();
			stop();
		}
	 
		checkCollisions();		//check for collisions between sprites as often as possible
	 
	 
		// Perform all Sprite updates after collisions check
		int number_enemies = 0;
		for (int i = 0; i < all_sprites.size(); i++) {
			Sprite current_sprite = all_sprites.get(i);
			current_sprite.update();
			if(current_sprite instanceof MovingSprite) {
				MovingSprite moving_sprite = (MovingSprite) current_sprite;
				if (moving_sprite instanceof Enemy) {
					number_enemies++;
					if (moving_sprite.getHealth() <= 0) {
						hero.increaseScore(1);
						all_sprites.remove(i);
						number_enemies--;
					}
				} else if (moving_sprite instanceof Hero) {
					if (hero.getHealth() <= 0) {
						hero.setX(center_x);
						hero.setY(center_y);
					}
				}
			} else if (current_sprite instanceof HealthPotion) {
				HealthPotion hp = (HealthPotion) current_sprite;
				if (hp.getHealthBoost() == 0) {
					all_sprites.remove(i);
				}
			}
		}
		
		if (number_enemies < 1) {	//this checks if more enemies need to be spawned on the map
			Random random = new Random();
			int spawnlocation = random.nextInt(4);
			int enemy_x = enemy_spawns.get(spawnlocation).getX();
			int enemy_y = enemy_spawns.get(spawnlocation).getY();
			Enemy enemy = EnemyFactory.useFactory(3, enemy_x, enemy_y);	//adds one dragon per round of new enemies in a random location
			all_sprites.add(enemy);
			for (int i = 0; i < 4; i++) {				//generates 4 enemies in 4 random locations
				spawnlocation = random.nextInt(4);
				enemy_x = enemy_spawns.get(spawnlocation).getX();
				enemy_y = enemy_spawns.get(spawnlocation).getY();
				int enemy_type = random.nextInt(2);
				enemy = EnemyFactory.useFactory(enemy_type+1, enemy_x, enemy_y);
				all_sprites.add(enemy);
			}
		}
		
		//this if statement shifts everything in the window's x value so that the Hero is always in the center of the screen
		if (hero.getX() > center_x) {
			for (Sprite sprite : all_sprites){
			    sprite.setX(sprite.getX() - Hero.SPEED);
			}
			for (Sprite spawn_location : enemy_spawns) {
			   	spawn_location.setX(spawn_location.getX() - Hero.SPEED);
			}
		} else if (hero.getX() < center_x) {
			for (Sprite sprite : all_sprites){
			    sprite.setX(sprite.getX() + Hero.SPEED);
			}
			for (Sprite spawn_location : enemy_spawns) {
			   	spawn_location.setX(spawn_location.getX() + Hero.SPEED);
			}
		}
		
		//this if statement shifts everything in the window's y value so that the Hero is always in the center of the screen
		if (hero.getY() > center_y) {
			for (Sprite sprite : all_sprites){
			    sprite.setY(sprite.getY() - Hero.SPEED);
			}
			for (Sprite spawn_location : enemy_spawns) {
			   	spawn_location.setY(spawn_location.getY() - Hero.SPEED);
			}
		} else if (hero.getY() < center_y) {
			for (Sprite sprite : all_sprites){
			    sprite.setY(sprite.getY() + Hero.SPEED);
			}
			for (Sprite spawn_location : enemy_spawns) {
			   	spawn_location.setY(spawn_location.getY() + Hero.SPEED);
			}
		}
	     
	 }
	
	 /**
	  * This method will draw everything onto the frame
	  */
	 void draw() {
	     Graphics g = getGraphics();
	     Graphics bbg = backBuffer.getGraphics();
	     bbg.setColor(Color.BLACK);
	     bbg.fillRect(0, 0, windowWidth, windowHeight);
	 
		 //following loops draw all of the sprites in the lists of sprites currently on the map
	     all_sprites.get(0).draw(bbg);	//draws the floor underneath everything else
	     
	     for (NonmovingSprite spawn : enemy_spawns) {
	    	 spawn.draw(bbg);
	     }
		 for (Sprite sprite : all_sprites) {
			 if (sprite instanceof Floor == false) {
				 sprite.draw(bbg);
				 
				 int healthbar_width = 100;		//defines the width of the healthbar in pixels
				 int healthbar_height = 10;		//defines the height of the healthbar in pixels
				 int shift_x = 25;				//the typical amount for shifting the healthbar's x over the sprite
				 int shift_y = 20;				//the typical amount for shifting the healthbar's y over the sprite
			     bbg.setColor(Color.RED);	
			     
			   //the following if statements draw health bars for the enemies and the Hero on the screen
				 if (sprite instanceof Enemy) {
					 Enemy e = (Enemy) sprite;
					 int e_x = e.getX() + (e.getWidth() - healthbar_width)/2;
					 int e_y = e.getY() - (healthbar_height*2);
					 bbg.drawRect(e_x, e_y, healthbar_width, healthbar_height);
				     bbg.fillRect(e_x, e_y, e.getHealth() / (e.getMaxHealth()/healthbar_width), healthbar_height);
				     String s = Integer.toString(e.getHealth()) + "/" + Integer.toString(e.getMaxHealth());
				     bbg.drawString(s, e_x, e_y-5);
				 } else if (sprite instanceof Hero) {
					 int h_x = hero.getX();
					 int h_y = hero.getY();
					 bbg.drawRect(h_x-shift_x, h_y-shift_y, healthbar_width, healthbar_height);
				     bbg.fillRect(h_x-shift_x, h_y-shift_y, hero.getHealth() / (hero.getMaxHealth()/healthbar_width), healthbar_height);
				     String s = "Health = " + Integer.toString(hero.getHealth()) + "/" + Integer.toString(hero.getMaxHealth());
				     String score = "Current Score = " + Integer.toString(hero.getScore());
				     bbg.drawString(s, h_x-shift_x-2, h_y-shift_y-5);
				     bbg.drawString(score, h_x-shift_x, h_y+shift_y*4);
				     if (hero.getHealth() <= 0) {
				    	 int x1 = 495;
				    	 int x2 = 220;
				    	 int y1 = 200;
				    	 int y2 = 600;
				    	 try {
				             BufferedImage game_over = ImageIO.read(new File("gameover.png"));
				             BufferedImage press_p = ImageIO.read(new File("press_p.png"));
					    	 bbg.drawImage(game_over, x1, y1, null);
					    	 bbg.drawImage(press_p, x2, y2, null);
				           }
				           catch(IOException e) {
				             System.out.println("Error: " + e);
				             System.exit(1);
				           }
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
		 for (Sprite s1 : all_sprites) {
			 for (Sprite s2 : all_sprites) {
				 if (s1 instanceof Floor == false) {
					 if (s2 instanceof Floor == false) {
						if (spritesCollide(s1, s2)) {
							s1.collidedWith(s2);
							s2.collidedWith(s1);
						}
					 }
				 }
			 }
		 }
	 }

	
//	 /**
//	  * This main method starts the game by creating a new AzimuthGame object, and then starting the game
//	  * @param args
//	  */
//	 public static void main(String[] args) {
//		 AzimuthGame newgame = new AzimuthGame();
//		 newgame.start();
//	 }
	  
}
