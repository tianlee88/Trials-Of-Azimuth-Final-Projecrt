import static org.junit.Assert.*;

import org.junit.Test;

public class TestGameDatabase {

	GameDatabase us = new GameDatabase();
	
	@Test
	public void checkAddUser() {
		assertTrue(us.addUser("Floop", 10));
		assertTrue(us.findUser("Floop"));
		us.removeUser("Floop");
	}
	
	@Test
	public void checkRemoveUser() {
		us.addUser("Doop", 1);
		us.addUser("Soop", 3);
		assertTrue(us.removeUser("Doop"));
		assertTrue(us.removeUser("Soop"));
	}
	
	@Test
	public void checkUpdateUser() {
		us.addUser("Woop", 5);
		assertTrue(us.updateUser("Woop", 10));
		us.removeUser("Woop");
	}

}
