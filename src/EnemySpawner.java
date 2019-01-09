/**
 * This class is for all Enemy spawn locations in the game, which never move or react to collisions
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public class EnemySpawner extends NonmovingSprite {
	
	/*
	 * This constructor takes in the image pathname, x location, and y location for this EnemySpawner
	 */
	public EnemySpawner(String path, int _x, int _y) {
		super(path, _x, _y);
	}
	
	//the EnemySpawner has nothing to update
	public void update() {		
	}

	//the EnemySpawner doesn't react to collisions
	public void collidedWith(Sprite other_sprite) {		
	}
}
