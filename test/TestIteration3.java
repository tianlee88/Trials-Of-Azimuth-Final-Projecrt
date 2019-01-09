import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This JUnit Test was written to test classes and methods we implemented for Iteration 3
 * @authors Tian Lee, Paul Price and Christian Kennedy
 *
 */
public class TestIteration3 {
	
	AzimuthGame ag = new AzimuthGame();

	@Test
	public void TestMapBuilder() {
		MapBuilder map = new MapBuilder();
		assertEquals(map.buildMap().size(), 47);
		assertEquals(map.addItems().size(), 4);
	}
	
	@Test
	public void TestHighscoreBuilder() {
		GameDatabase gd = new GameDatabase();
		assertEquals(gd.createHighscoreList().length, 5);
	}
	
	@Test
	public void TestEnemyFactory() {
		assertEquals(EnemyFactory.useFactory(1, 0, 0).getHealth(), 100);
		assertEquals(EnemyFactory.useFactory(2, 0, 0).getHealth(), 100);
		assertEquals(EnemyFactory.useFactory(3, 0, 0).getHealth(), 1000);
	}

}
