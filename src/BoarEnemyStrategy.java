
/**
 * This is a subclass of EnemyStrategy that implements the BoarEnemyStrategy Enemy attack strategy
 * @authors Tian Lee, Paul Price and Christian Kennedy
 */
public class BoarEnemyStrategy implements EnemyStrategy{
	
	private static String enemy_image_path = "boar_enemy.png";	//default image
	private String right = "boar_right.png";					//right image
	private String left = "boar_left.png";						//left image
	
	/*
	 * This method returns the default image for this enemy strategy
	 */
	public String getImagePath() {
		return enemy_image_path;
	}
		
	/*
	 * This method takes in an Enemy instance and moves the Enemy towards the Hero's x location
	 */
	public int attackHeroX(Enemy enemy) {
		int hero_x = Hero.x;
		int hero_y_above = Hero.y - 20;
		int hero_y_below = Hero.y + 20;
		int enemy_x = enemy.getX();
		int enemy_y = enemy.getY();
		
		if (enemy_y > hero_y_above && enemy_y < hero_y_below) {	//if it is in this vertical range
			if (enemy_x < hero_x) {
				enemy.changeImage(right);
				return enemy.getSpeed()*3;
			} else if (enemy_x >= hero_x) {
				enemy.changeImage(left);
				return -enemy.getSpeed()*3;
			}
		} else {												//if its not in range
			if (enemy_x < hero_x) {
				enemy.changeImage(right);
				return enemy.getSpeed();
			} else if (enemy_x >= hero_x) {
				enemy.changeImage(left);
				return -enemy.getSpeed();
			} 
		}
		return 1;
	}
	
	/*
	 * This method takes in an Enemy instance and moves the Enemy towards the Hero's y location
	 */
	public int attackHeroY(Enemy enemy) {
		int hero_y = Hero.y;
		int enemy_y = enemy.getY();
		if(enemy_y > hero_y) {				//if the enemy is below the player
			if (enemy.getX() < Hero.x) {	//to the left
				enemy.changeImage(right);

			} else {						//to the right
				enemy.changeImage(left);
			}
			return -enemy.getSpeed()/5;
		}
		else if(enemy_y < hero_y) {			//if the enemy is above the player
			if (enemy.getX() < Hero.x) {	//to the left
				enemy.changeImage(right);
			} else {						//to the right
				enemy.changeImage(left);
			}
			return enemy.getSpeed()/5;
		}
		else {
			return enemy.getSpeed()/5;
		}
	}
	
}
