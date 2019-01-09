/**
 * This class is for all Enemies in the game
 * @author Tian Lee, Paul Price, and Christian Kennedy
 *
 */
public class Enemy extends MovingSprite{
	
	private int damage = 25;					//damage that the Enemy does to the Hero
	private static int speed = 5;				//speed that the Enemy moves
	private int max_health;						//the Enemy's starting health
	private EnemyStrategy enemy_strategy; 		//the EnemyStrategy for this specific Enemy
	
	/*
	 * This constructor takes in the starting x value, starting y value, starting health, and EnemyStrategy for this Enemy
	 */
	public Enemy(int _x, int _y, int max_health, EnemyStrategy enemy_strategy) {
		super(enemy_strategy.getImagePath(), _x, _y, speed, max_health);
		this.enemy_strategy = enemy_strategy;
		this.max_health = max_health;
	}
	
	/*
	 * returns the damage the Enemy does to the Hero
	 */
	public int getDamage() {
		return damage;
	}
	
	/*
	 * sets the damage the Enemy does to the Hero
	 */
	public void setDamage(int _damage) {
		damage = _damage;
	}
	
	/*
	 * returns the Enemy's max health
	 */
	public int getMaxHealth() {
		return max_health;
	}
	
	/*
	 * sets the Enemy's max health
	 */
	public void setMaxHealth(int max) {
		max_health = max;
	}
	
	/*
	 * This method is for the specific interactions between the Enemy and other sprites
	 */
	public void collidedWith(Sprite other_sprite) {
		if (other_sprite instanceof Fireball) {
			Fireball fb = (Fireball) other_sprite;
			reduceHealth(fb.getDamage());
		}
		else if (other_sprite instanceof Enemy) {
			Enemy enemy = (Enemy) other_sprite;
			if (enemy.getX() < this.getX()) {
				//if the enemy colliding with is to the left
				enemy.setX(enemy.getX() - (enemy.getWidth()/10));
				this.setX(this.getX() + (this.getWidth()/10));
			} else {
				//if the enemy colliding is to the right
				enemy.setX(enemy.getX() + (enemy.getWidth()/10));
				this.setX(this.getX() - (this.getWidth()/10));
			}
		}
	}

	/*
	 * This is the Enemy's personal update method
	 */
	public void update() {
		updateX(enemy_strategy.attackHeroX(this));
		updateY(enemy_strategy.attackHeroY(this));
	}
	
}
