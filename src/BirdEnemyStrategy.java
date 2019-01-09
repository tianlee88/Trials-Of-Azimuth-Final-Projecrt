
/**
 * This is a subclass of EnemyStrategy that implements the BirdEnemyStrategy Enemy attack strategy
 * @authors Tian Lee, Paul Price and Christian Kennedy
 */
public class BirdEnemyStrategy implements EnemyStrategy{
	
	private static final String enemy_image_path = "standard_enemy.png";	//default image
	private String right = "bird_right.png";								//right image
	private String left = "bird_left.png";									//left image
	
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
		int enemy_location_x = enemy.getX();
		if(enemy_location_x > hero_location_x) {	//if to the right
			enemy.changeImage(left);
			return -enemy.getSpeed();
		}										
		else if(enemy_location_x< hero_location_x) {//if to the left
			enemy.changeImage(right);
			return 	enemy.getSpeed();	
		}
		else {										//otherwise
			return 1;
		}
	}
	
	/*
	 * This method takes in an Enemy instance and moves that Enemy towards the Hero's y location
	 */
	public int attackHeroY(Enemy enemy) {
		int hero_location_y = Hero.y;
		int enemy_location_y = enemy.getY();
		if(enemy_location_y> hero_location_y) {		//if below
			return -enemy.getSpeed();
		}
		else if(enemy_location_y< hero_location_y) {//if above
			return enemy.getSpeed();
		}		
		else {										//otherwise
			return 1;
		}
	}
	
}
