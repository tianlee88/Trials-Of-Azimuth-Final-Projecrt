
/**
 * This class is for the Floor object, which reacts to no collisions and always starts in the same location
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public class Floor extends NonmovingSprite{

	private static final String image_path = "big_floor7.png";	//the default image pathname
	private static final int start_x = -1860;					//the default x value
	private static final int start_y = -1260;					//the default y value
	
	/*
	 * This constructor takes in nothing, since the Floor always generates in the same place with the same image
	 */
	public Floor() {
		super(image_path, start_x, start_y);
	}
	
	/*
	 * This is the Floor's personal update method, which does nothing
	 */
	public void update() {
		//floor does nothing
	}

	/*
	 * This is the Floor's personal collidedWith method, which does nothing
	 */
	public void collidedWith(Sprite other_sprite) {
		//floor does nothing
	}
	
	

}
