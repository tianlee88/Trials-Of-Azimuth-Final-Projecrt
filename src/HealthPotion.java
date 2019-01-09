import java.awt.*;

/**
 * This class is for HealthPotions, which are a subclass of Potion that increase the Hero's current health
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public class HealthPotion extends Potion {

	private static String image_pathname = "redpotion.png";	//the default image for HealthPotions
	private int health_boost;			//how much of a health boost this potion provides to the Hero

	/*
	 * This constructor takes in the starting x and y values, as well as the desired health boost
	 */
    public HealthPotion(int _x, int _y, int _health_boost) {
        super(image_pathname, _x, _y);
        health_boost = _health_boost;
    }

    //returns the potion's health boost
    public int getHealthBoost(){
      return health_boost;
    }
    
    //sets the potion's health boost
    public void setHealthBoost(int boost) {
    	health_boost = boost;
    }

    //collidedWith method specific to this class, which does nothing
    public void collidedWith(Sprite other){
    	//this class doesn't need to do the handling of collisions
    }

    //this HealthPotions particular update method, which does nothing
	public void update() {
		//HealthPotions need no updates
	}
}
