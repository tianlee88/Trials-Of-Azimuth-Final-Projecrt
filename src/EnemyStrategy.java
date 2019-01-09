
/**
 * This interface is for using the Strategy pattern to implement different EnemyStrategy subclasses
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public interface EnemyStrategy{
	
	/*
	 * returns the image for this specific enemy
	 */
	public String getImagePath();
	
	
	/*
	 * This method tracks the Hero's x coordinate and moves the Enemy according to the specific strategy
	 */
	public int attackHeroX(Enemy enemy);
	
	/*
	 * This method tracks the Hero's y coordinate and moves the Enemy according to the specific strategy
	 */
	public int attackHeroY(Enemy enemy);
	
}
