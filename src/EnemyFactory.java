/**
 *This class is a Factory that creates Enemy instances with specific EnemyStrategy implementations
 * @author Tian Lee, Paul Price and Christian Kennedy
 */

public class EnemyFactory {
	
	/**
	 * This method takes in an integer as criteria, as well as the starting x and starting y coordinates
	 * of the Enemy to be instantiated. It uses the criteria to assign an EnemyStrategy to the Enemy instantiated
	 * @param criteria - the criteria for the Strategy to be implemented
	 * @param x_position - the Enemy's starting x value
	 * @param y_position - the Enemy's starting y value
	 * @return - the desired Enemy instance
	 */
	  public static Enemy useFactory(int criteria, int x_position, int y_position){
		  int max_health;
	    if ( criteria==1 ){	
	    	max_health = 100;
	    	EnemyStrategy standard_enemy_strategy = new BirdEnemyStrategy();
	    	Enemy standard_enemy =  new Enemy(x_position,y_position, max_health, standard_enemy_strategy);
	    	return standard_enemy;
	    } 
	    if ( criteria==2 ){	
	    	max_health = 100;
	    	EnemyStrategy boar_enemy_strategy = new BoarEnemyStrategy();
	    	Enemy boar_enemy =  new Enemy(x_position,y_position, max_health, boar_enemy_strategy);
	    	return boar_enemy;
	    } 
	    if(criteria==3) {
	    	max_health = 1000;
	    	EnemyStrategy RandomSpeedStrategy = new BigAndSlowStrategy();
	    	Enemy slow_enemy = new Enemy(x_position,y_position, max_health, RandomSpeedStrategy);
	    	return slow_enemy;
	    }
	    else {
	    	return null;
	    }
	  }
}
