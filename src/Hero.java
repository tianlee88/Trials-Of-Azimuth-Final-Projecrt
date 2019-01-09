
/**
 * This class is for the Hero, which is the character that the user controls with the WASD keys
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public class Hero extends MovingSprite{
	
	private static String hero_image_path = "player1_down.png";	//default image
	public static int x;										//Hero's current x location; public so its accessible from all over
	public static int y;										//Hero's current y location; public so its accessible from all over
	private final static int max_health = 1000;					//Hero's max health
	public static final int SPEED = 15;							//Hero's moving speed
	private int score	;										//Hero's score
	/**
	 * The constructor for the Hero takes in the desired x and y value to place the Hero
	 * @param _x - starting x value
	 * @param _y - starting y value
	 */
	public Hero(int _x, int _y) {
		super(hero_image_path, _x, _y, SPEED, max_health);
		x = _x;
		y = _y;
		score = 0;
	}
		
	/*
	 * returns the Hero's max possible health
	 */
	public int getMaxHealth() {
		return max_health;
	}
	
	/*
	 * Increases the Hero's score
	 */
	public void increaseScore(int score_increase) {
		score += score_increase;
	}
	
	/*
	 * returns the Hero's score
	 */
	public int getScore() {
		return score;
	}	
	
	/*
	 * This method dictates how the Hero reacts to collisions with specific sprites
	 */
	public void collidedWith(Sprite other_sprite) {
		if (other_sprite instanceof Wall) {
			Wall this_wall = (Wall) other_sprite;
			if (this_wall.getPlacement() == 1) {
				//then its an up wall
				moveDown();
			} else if (this_wall.getPlacement() == 2) {
				//then its a down wall
				moveUp();
			} else if (this_wall.getPlacement() == 3) {
				//then its a left wall
				moveRight();
			} else if (this_wall.getPlacement() == 4) {
				//then its a right wall
				moveLeft();
			}
		} else if (other_sprite instanceof Enemy) {
			Enemy this_enemy = (Enemy) other_sprite;
			if (getHealth() > 0) {
				reduceHealth(this_enemy.getDamage());
			}
		} else if (other_sprite instanceof HealthPotion) {
			HealthPotion hp = (HealthPotion) other_sprite;
			if (getHealth() < max_health) {
				if (max_health - getHealth() < hp.getHealthBoost()) {
					setHealth(max_health);
				} else {
					increaseHealth(hp.getHealthBoost());
				}
			}
			hp.setHealthBoost(0);
		}
	}


	/*
	 * This method is the Hero's personal update method
	 */
	public void update() {
		x = this.getX();
		y = this.getY();
	}

}
