
/**
 * This abstract class is the parent class for all Potion implementations
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public abstract class Potion extends NonmovingSprite {
	
	/*
	 * This constructor takes in the Potion's image pathname, as well as the starting x and y values
	 */
    public Potion(String path, int _x, int _y) {
        super(path, _x, _y);
    }

}
