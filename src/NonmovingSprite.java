
/**
 * This abstract class is for all sprites that can not move around the map
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public abstract class NonmovingSprite extends Sprite{
	
	/*
	 * This constructor takes in the sprite's image pathname, x coordinate, and y coordinate
	 */
	public NonmovingSprite(String _path, int _x, int _y) {
		super(_path, _x, _y);
	}

}
