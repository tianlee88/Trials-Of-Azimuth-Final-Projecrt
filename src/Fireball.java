
/**
 * This class is for the Fireball, the Hero's weapon
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public class Fireball extends MovingSprite{
	
	private boolean lockOnHero = true;		//whether the Fireball is currently locked to the Hero
	private boolean up = false;				//if the Fireball was shot up
	private boolean down = false;			//if the Fireball was shot down
	private boolean left = false;			//if the Fireball was shot left
	private boolean right = false;			//if the Fireball was shot right
	private static final int DAMAGE = 25;	//the damage that all Fireballs do
	private static final int SPEED = 25;	//the speed that all Fireballs move
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	
	private static String fireball_image_path = "fireballsmall.png";	//the Fireball image path

	/*
	 * This constructor doesn't need to take anything in
	 */
	public Fireball() {
		super(fireball_image_path, Hero.x, Hero.y, SPEED, 1);
	}
	
	/*
	 * returns the damage the Fireball does to Enemy sprites
	 */
	public int getDamage() {
		return DAMAGE;
	}
	
	/*
	 * sets the lockOnHero boolean
	 */
	public void setLock(boolean lock) {
		lockOnHero = lock;
	}
	
	/*
	 * returns the lockOnHero boolean
	 */
	public boolean getLock() {
		return lockOnHero;
	}
	
	/*
	 * This method registers what direction the Fireball was shot and sets the appropriate boolean to true
	 */
	public void shoot(int direction) {
		lockOnHero = false;
		if (up == false && down == false && left == false && right == false) {
			if (direction == UP) {
				up = true;
			} else if (direction == DOWN) {
				down = true;
			} else if (direction == LEFT) {
				left = true;
			} else if (direction == RIGHT) {
				right = true;
			}
		}
	}
	
	/*
	 * This method is for determining what happens when the Fireball collides with other sprites
	 */
	public void collidedWith(Sprite other_sprite) {
		if (other_sprite instanceof Wall) {
			lockOnHero = true;
		} else if (other_sprite instanceof Enemy) {
			setX(Hero.x);	//this prevents the Fireball from not resetting to the Hero on collision with an Enemy
			setY(Hero.y);
			lockOnHero = true;
		}
	}
	
	/*
	 * This is the Fireball's personal update method
	 */
	public void update() {
		if (lockOnHero == true) {
			up = false;
			down = false;
			left = false;
			right = false;
		}
		if (up) {
			this.setY(this.getY() - SPEED);
		} else if (down) {
			this.setY(this.getY() + SPEED);
		} else if (left) {
			this.setX(this.getX() - SPEED);
		} else if (right) {
			this.setX(this.getX() + SPEED);
		}
	}
		
}
