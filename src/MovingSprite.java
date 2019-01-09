
/**
 * This abstract class is for all sprites that can move around the map
 * @author Tian Lee, Paul Price, and Christian Kennedy
 *
 */
public abstract class MovingSprite extends Sprite{
	
	private int speed;	//moving speed
	private int health;	//health

	/*
	 * This constructor takes in the image path, starting x, starting y, speed, and health for the instantiated MovingSprite
	 */
	public MovingSprite(String _path, int _x, int _y, int speed, int health) {
		super(_path, _x, _y);
		this.speed = speed;
		this.health = health;
	}
	
	/*
	 * returns the sprite's speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/*
	 * sets the sprite's speed
	 */
	public void setSpeed(int _speed) {
		speed = _speed;
	}
	
	/*
	 * returns the sprite's current health
	 */
	public int getHealth() {
		return health;
	}
	
	/*
	 * sets the sprite's current health
	 */
	public void setHealth(int _health) {
		health = _health;
	}
	
	/*
	 * reduces the sprite's current health
	 */
	public void reduceHealth(int health_reduction) {
		health -= health_reduction;
	}
	
	/*
	 * increases the sprite's current health
	 */
	public void increaseHealth(int health_increase) {
		health += health_increase;
	}	
	
	
	/*
	 * moves the sprite up in the frame and sets the last direction
	 */
	public void moveUp() {
		this.setY(this.getY() - speed);
	}
	
	/*
	 * moves the sprite down in the frame and sets the last direction
	 */
	public void moveDown() {
		this.setY(this.getY() + speed);
	}
	
	/*
	 * moves the sprite left in the frame and sets the last direction
	 */
	public void moveLeft() {
		this.setX(this.getX() - speed);
	}
	
	/*
	 * moves the sprite right in the frame and sets the last direction
	 */
	public void moveRight() {
		this.setX(this.getX() + speed);
	}
	
}
