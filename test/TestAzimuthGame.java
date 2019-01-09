import static org.junit.Assert.*;

import org.junit.Test;

public class TestAzimuthGame {
	
	AzimuthGame test_game = new AzimuthGame();
	
	/*
	 * Tests that the spritesCollide method returns the proper value
	 */
	@Test
	public void testSpritesCollide() {
		Floor f = new Floor();
		Enemy e1 = EnemyFactory.useFactory(1, 0, 0);
		Enemy e2 = EnemyFactory.useFactory(1, 1000, 1000);
		assertTrue(test_game.spritesCollide(f, e1));	//these two sprites are colliding
		assertFalse(test_game.spritesCollide(e1, e2));	//these two sprites are not colliding
	}
	
	/*
	 * Tests that the Hero's health always starts at the max_health value of 1000
	 */
	@Test
	public void testHeroHealth() {
		Hero h = new Hero(0, 0);
		assertEquals(h.getHealth(), 1000);
	}
	
	/*
	 * Tests that the Hero sprite is placed in the correct location on the map
	 */
	@Test
	public void testLocation() {
		Hero h = new Hero(0, 0);
		assertEquals(h.getX(), 0);
		assertEquals(h.getY(), 0);
	}
	
	/*
	 * Tests that increasing the player's score properly functions and returns the correct value
	 */
	@Test
	public void testScore() {
		Hero h = new Hero(0,0);
		h.increaseScore(3);
		assertEquals(h.getScore(), 3);
	}

}
