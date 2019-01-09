import java.util.ArrayList;

/**
 * This class builds and populates the map to be rendered by the AzimuthGame class
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public class MapBuilder {

	private int width = 12;			//width of 2d array that represents the map
	private int height = 12;		//height of the 2d array that represents the map
	private int far_left = -1860;	//x coordinate of the farthest left walls on the map
	private int far_right = 1820;	//x coordinate of the farthest right walls on the map
	private int top = -1260;		//y coordinate of the highest walls on the map
	private int bottom = 1220;		//y coordinate of the lowest walls on the map
	
	public static int[][] map = {		//the 2d array representation of the game map
			{1,1,1,1,1,1,1,1,1,1,1,1},
			{3,0,0,0,0,0,0,0,0,0,0,4},
			{3,0,5,0,0,0,0,0,0,5,0,4},
			{3,0,0,0,0,0,0,0,0,0,0,4},
			{3,0,0,0,0,0,0,0,0,0,0,4},
			{3,0,0,0,0,0,0,0,0,0,0,4},
			{3,0,0,0,0,0,0,0,0,0,0,4},
			{3,0,0,0,0,0,0,0,0,0,0,4},
			{3,0,0,0,0,0,0,0,0,0,0,4},
			{3,0,5,0,0,0,0,0,0,5,0,4},
			{3,0,0,0,0,0,0,0,0,0,0,4},
			{2,2,2,2,2,2,2,2,2,2,2,2}
		};
	
	/**
	 * This method processes the map 2d array and fills an ArrayList with NonmovingSprite objects
	 * @return - the ArrayList containing the NonmovingSprites that are the floor and walls of the map
	 **/
	public ArrayList<NonmovingSprite> buildMap() {
		ArrayList<NonmovingSprite> room = new ArrayList<NonmovingSprite>();
		Floor floor = new Floor();
		room.add(floor);
		int x_coordinate;
		int y_coordinate;
		int x_mult = 310;
		int y_mult = 210;
		Wall top_left = new Wall("40x210.png", far_left, top, Wall.LEFT);	//makes sure to add a vertical wall at the top left
		Wall top_right = new Wall("40x210.png", far_right, top, Wall.RIGHT);	//makes sure to add a vertical wall at the top right
		room.add(top_left);
		room.add(top_right);
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				x_coordinate = (x-6) * x_mult;
				y_coordinate = (y-6) * y_mult;
				if (map[y][x] == 1 || map[y][x] == 2) {	//adds a horizontal walls
					if (map[y][x] == 1) {
						Wall this_wall = new Wall("310x40.png", x_coordinate, y_coordinate, Wall.UP);
						room.add(this_wall);
					} else {
						Wall this_wall = new Wall("310x40.png", x_coordinate, y_coordinate, Wall.DOWN);
						room.add(this_wall);
					}
				}
				else if (map[y][x] == 3 || map[y][x] == 4) {	//adds a vertical wall
					if (x > 0) {
						x_coordinate += 270;
					}
					if (map[y][x] == 3) {
						Wall this_wall = new Wall("40x210.png", x_coordinate, y_coordinate, Wall.LEFT);
						room.add(this_wall);

					} else {
						Wall this_wall = new Wall("40x210.png", x_coordinate, y_coordinate, Wall.RIGHT);
						room.add(this_wall);
					}
				}
			}
		}
		return room;
	}
	
	/**
	 * This method adds the initial Enemy objects to the map
	 * @return - an ArrayList of Enemies
	 */
	public ArrayList<MovingSprite> populateMap(){
		ArrayList<MovingSprite> moving_sprites = new ArrayList<MovingSprite>();
		
		Enemy enemy1 = EnemyFactory.useFactory(1, 600, -400);
		Enemy enemy2 = EnemyFactory.useFactory(2, -640, 400);
		Enemy enemy3 = EnemyFactory.useFactory(1, 600, -1240);
		Enemy enemy4 = EnemyFactory.useFactory(2, 1840, 400);
		Enemy enemy5 = EnemyFactory.useFactory(1, -640, -400);
		moving_sprites.add(enemy1);
		moving_sprites.add(enemy2);
		moving_sprites.add(enemy3);
		moving_sprites.add(enemy4);
		moving_sprites.add(enemy5);
		return moving_sprites;
	}
	
	/**
	 * This method adds the enemy spawn locations to the map
	 * @return - an ArrayList of enemy spawn locations
	 */
	public ArrayList<NonmovingSprite> addEnemySpawners(){
		ArrayList<NonmovingSprite> enemy_spawns = new ArrayList<NonmovingSprite>();
		int x_coordinate = 0;
		int y_coordinate = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				x_coordinate = (x-6) * 310;
				y_coordinate = (y-6) * 210;
				if (map[y][x] == 5) {
					enemy_spawns.add(new EnemySpawner("enemy_spawn.png", x_coordinate, y_coordinate));
				}
			}
		}
		return enemy_spawns;
	}
	
	/**
	 * This method adds all items (currently just health potions) to the map
	 * @return - an ArrayList of items (healthpotions)
	 */
	public ArrayList<NonmovingSprite> addItems(){
		ArrayList<NonmovingSprite> items = new ArrayList<NonmovingSprite>();
		int potion_left = -1300;
		int potion_right = 1300;
		int potion_up = 700;
		int potion_down = -900;
		int health_boost = 250;
		HealthPotion hp1 = new HealthPotion(potion_left, potion_down, health_boost);
		HealthPotion hp2 = new HealthPotion(potion_left, potion_up, health_boost);
		HealthPotion hp3 = new HealthPotion(potion_right, potion_down, health_boost);
		HealthPotion hp4 = new HealthPotion(potion_right, potion_up, health_boost);
		items.add(hp1);
		items.add(hp2);
		items.add(hp3);
		items.add(hp4);
		return items;
	}

}
