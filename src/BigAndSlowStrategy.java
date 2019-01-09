import java.util.Random;

/**
 * This subclass of EnemyStrategy is for the "Big and Slow" strategy implementation
 * @authors Tian Lee, Paul Price and Christian Kennedy
 */
public class BigAndSlowStrategy implements EnemyStrategy {
	
	private static String enemy_image_path = "dragon_down.png";	//default image
	private String up = "dragon_up.png";						//up image
	private String down = "dragon_down.png";					//down image
	private String left = "dragon_left.png";					//left image
	private String right = "dragon_right.png";					//right image
	private int speed_reduction = 3;							//the amount that this "slow" enemy should reduce its normal speed

	/*
	 * This method returns the default image for this enemy strategy
	 */
	public String getImagePath() {
		return enemy_image_path;
	}

	/*
	 * This method takes in an Enemy instance and moves that Enemy towards the Hero's x location
	 */
	public int attackHeroX(Enemy enemy) {
		int hero_location_x = Hero.x;
		int hero_y_above = Hero.y - 20;
		int hero_y_below = Hero.y + 20;
		int enemy_x = enemy.getX();
		int enemy_y = enemy.getY();
		if (enemy_y > hero_y_above && enemy_y < hero_y_below) {	//if it is in this vertical range
			if(enemy_x > hero_location_x) {
				enemy.changeImage(left);
				return -enemy.getSpeed()+speed_reduction;
			}
			else if(enemy_x< hero_location_x) {
				enemy.changeImage(right);
				return 	enemy.getSpeed()-speed_reduction;	
			}
		} else {												//otherwise
			if(enemy_x > hero_location_x) {
				return -enemy.getSpeed()+speed_reduction;
			}
			else if(enemy_x< hero_location_x) {
				return 	enemy.getSpeed()-speed_reduction;	
			}
		}
		return 1;
	}
	
	/*
	 * This method takes in an Enemy instance and moves that Enemy towards the Hero's y location
	 */
	public int attackHeroY(Enemy enemy) {
		int hero_y = Hero.y;
		int hero_x_left = Hero.x - 20;
		int hero_x_right = Hero.x + 20;
		int enemy_x = enemy.getX();
		int enemy_y = enemy.getY();
		if (enemy_x > hero_x_left && enemy_x < hero_x_right) {	//if it is in this horizontal range
			if(enemy_y> hero_y) {
				enemy.changeImage(up);
				return -enemy.getSpeed()+speed_reduction;
			}
			else if(enemy_y< hero_y) {
				enemy.changeImage(down);
				return +enemy.getSpeed()-speed_reduction;
			}
		} else {												//otherwise
			if(enemy_y> hero_y) {
				return -enemy.getSpeed()+speed_reduction;
			}
			else if(enemy_y< hero_y) {
				return +enemy.getSpeed()-speed_reduction;
			}
		}
		return 1;
	}

}
