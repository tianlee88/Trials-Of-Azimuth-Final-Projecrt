
/**
 * This abstract class is the parent of all Projectiles that the Hero can collect and use
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public abstract class Projectile extends MovingSprite{
	
	private boolean lockOnHero = true;		//whether the Projectile is currently locked to the Hero
	private boolean up = false;				//if the Projectile was shot up
	private boolean down = false;			//if the Projectile was shot down
	private boolean left = false;			//if the Projectile was shot left
	private boolean right = false;			//if the Projectile was shot right
	private static final int DAMAGE = 25;	//the damage that all projectiles do
	private static final int SPEED = 25;	//the speed that all projectiles move
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	
	/*
	 * This constructor takes in the image pathname, starting x, and starting y location of the Projectile
	 */
	public Projectile(String _path, int _x, int _y) {
		super(_path, _x, _y, SPEED, 1);
	}
	
	/*
	 * returns the damage the Projectile does to Enemy sprites
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
	 * This method registers what direction the Projectile was shot and sets the appropriate boolean to true
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
	 * This method is for determining what happens when the Projectile collides with other sprites
	 */
	public void collidedWith(Sprite other_sprite) {
		if (other_sprite instanceof Wall) {
			lockOnHero = true;
		} else if (other_sprite instanceof Enemy) {
			lockOnHero = true;
		}
	}
	
	/*
	 * This is the Projectile's personal update method
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
