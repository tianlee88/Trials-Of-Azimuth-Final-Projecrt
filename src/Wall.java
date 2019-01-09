
/**
 * This class is for all Walls in the game, which never move or react to collisions
 * @authors Tian Lee, Paul Price and Christian Kennedy
 */
public class Wall extends NonmovingSprite{
	
	public static final int UP = 1;				//needed for wall placement and collision detection
	public static final int DOWN = 2;			//needed for wall placement and collision detection
	public static final int LEFT = 3;			//needed for wall placement and collision detection
	public static final int RIGHT = 4;			//needed for wall placement and collision detection
	private int wall_placement;					//field that tells you where the wall is placed in relation to the Hero
	
	/*
	 * This constructor takes in the image pathname, as well as the x and y coordinates for the Wall
	 */
	public Wall(String path, int _x, int _y, int wall_placement) {
		super(path, _x, _y);
		this.wall_placement = wall_placement;
	}
	
	/*
	 * returns the wall's placement as an integer
	 */
	public int getPlacement() {
		return wall_placement;
	}
	
	/*
	 * This is the Wall's personal update method, which does nothing
	 */
	public void update() {
		//walls never move
	}
	
	/*
	 * This is the Wall's personal collidedWith method, which does nothing
	 */
	public void collidedWith(Sprite other_sprite) {
		//walls don't react to collisions
	}

}
