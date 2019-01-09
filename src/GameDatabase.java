
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class handles all of the Database communication required for our software to function
 * @authors Christian Kennedy, Tian Lee and Paul Price
 *
 */
public class GameDatabase {
	
	private static final String PORT_NUMBER = "3306";				//the port number for our database
	private static String database_name = "TheLegendOfAzimuth";		//Title of our database
	
	
	/**
	 * This method creates a new database if one doesn't already exist, adds a Users table to that database if it doesn't
	 * already exist, and then adds the desired user to the Users table
	 * @param username - the username of the user to be added
	 * @param highscore - default sets the user's highscore to 0
	 * @return - true if the user was successfully added, otherwise false
	 */
	public boolean addUser(String username, int highscore) {
		boolean user_added = false;		
		//step 1: Build the new database
		try (
				// Allocate a database "Connection" object
				Connection database_conn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/", 
						"root", "root"); // MySQL	
				// Allocate a "Statement" object in the Connection
				Statement database_stmt = database_conn.createStatement();
				) {
				// create our database
				String sql = "create database if not exists " + database_name;
				database_stmt.execute(sql);	
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		try(
		//step 2: Add tables to the database
				
			// Allocate a database "Connection" object
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:" + PORT_NUMBER + "/" + database_name + "?user=root&password=root"); // MySQL
			// Allocate a "Statement" object in the Connection
			Statement stmt = conn.createStatement();
			) {
			// create our new tables
			String sql = "create table if not exists Users ( " +
					"Username varchar(50), " +
					"Highscore int, " +
					"primary key (Username));";
			stmt.execute(sql);

		} catch(SQLException ex) {
			ex.printStackTrace();
		}
				
		try(
		//step 3: Add the user to the Users table
				// Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/" + database_name + "?user=root&password=root"); // MySQL
				// Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String sqlInsert = "insert into Users value "
					+ "('" + username + "', " + highscore + ");";
			int countInserted = stmt.executeUpdate(sqlInsert);
			if (countInserted == 1) {
				user_added = true;
			}
			conn.close();
			return user_added;
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return user_added;
	}
	
	/**
	 * This method removes the specified user from the table
	 * @param username - the username of the user to be deleted
	 * @return - true if the user was successfully removed, otherwise false
	 */
	public boolean removeUser(String username) {
		boolean user_removed = false;
		try(
				// Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/" + database_name + "?user=root&password=root"); // MySQL
				// Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			String query = "delete from Users where Username = '" + username + "'";
		    PreparedStatement preparedStmt = conn.prepareStatement(query);
		    preparedStmt.execute();
		    user_removed = true;
			conn.close();
			return user_removed;
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return user_removed;
	}
	
	/**
	 * This method updates the desired user's highscore with their new highscore
	 * @param username - the username of the user whose score is being updated
	 * @param new_highscore - the new highscore of the user
	 * @return - true if the highscore was successfully updated, otherwise false
	 */
	public boolean updateUser(String username, int new_highscore) {
		boolean user_updated = false;
		int recorded_highscore = getUserHighscore(username);
		//this is the highscore in the database for this user
		
		try(
			// Allocate a database "Connection" object
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:" + PORT_NUMBER + "/" + database_name + "?user=root&password=root"); // MySQL
			// Allocate a "Statement" object in the Connection
			Statement stmt = conn.createStatement();
			) {
			Statement res_stmt = conn.createStatement();
			String strSelect = "select * from Users";
			ResultSet rset = res_stmt.executeQuery(strSelect);
						
			if (recorded_highscore < new_highscore) {
				String query = "update Users set Highscore = " + new_highscore + " where Username = '" + username + "'";
			    PreparedStatement preparedStmt = conn.prepareStatement(query);
			    preparedStmt.executeUpdate();
			    user_updated = true;
			}
			conn.close();
			return user_updated;
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return user_updated;
	}
	
	/**
	 * This method searches for the specified user in the database
	 * @param username - the username of the user to search for
	 * @return  - true if the user was found, otherwise false
	 */
	public boolean findUser(String username) {
		boolean user_exists = false;
		try(
				// Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/" + database_name + "?user=root&password=root"); // MySQL
				// Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
				Statement res_stmt = conn.createStatement();
				String strSelect = "select * from Users";
				ResultSet rset = res_stmt.executeQuery(strSelect);		
				String current_username = "";
				while (rset.next() && user_exists == false) {
					current_username = rset.getString("Username");
					if (current_username.equals(username)) {
						user_exists = true;
					}
				}
				conn.close();
				return user_exists;
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		return user_exists;
	}
	
	/**
	 * This method gets the highscore of the specified user
	 * @param username - the username of the user whose highscore we are retrieving
	 * @return - an int which is the user's current highscore
	 */
	public int getUserHighscore(String username) {
		int user_highscore = 0;
		if (!findUser(username)) {
			System.out.println("Tried to get highscore but no user " + username + " exists");
			return 0;
		}
		try(
				// Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/" + database_name + "?user=root&password=root"); // MySQL
				// Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
		
			Statement res_stmt = conn.createStatement();
			String strSelect = "select * from Users";
			ResultSet rset = res_stmt.executeQuery(strSelect);			
			String current_username = "";
			boolean correct_user = false;
			while (rset.next() && correct_user == false) {
				current_username = rset.getString("Username");
				if (current_username.equals(username)) {
					user_highscore = rset.getInt("Highscore");
					correct_user = true;
				}
			}
			conn.close();
			return user_highscore;
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return user_highscore;
		
	}
	
	public String[] createHighscoreList() {
		ArrayList<Integer> highscore_list = new ArrayList<Integer>();
		ArrayList<String> user_list = new ArrayList<String>();
		String[] final_list = new String[5];		
		try(
				// Allocate a database "Connection" object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/" + database_name + "?user=root&password=root"); // MySQL
				// Allocate a "Statement" object in the Connection
				Statement stmt = conn.createStatement();
				) {
			Statement res_stmt = conn.createStatement();
			String strSelect = "select * from Users";
			ResultSet rset = res_stmt.executeQuery(strSelect);
			while(rset.next()) {
				highscore_list.add(rset.getInt("Highscore"));
			}
			//now the list of scores has been made
			Collections.sort(highscore_list);
			Collections.reverse(highscore_list);
			//now the highest scores are at the front
			while (highscore_list.size() < 5) {
				highscore_list.add(0);
			}
			//now the list definitely has at least 5 entries
			for (int this_score : highscore_list) {
				rset = res_stmt.executeQuery(strSelect);
				boolean found_user = false;
				while (rset.next() && found_user == false) {
					String user = rset.getString("Username");
					int score = rset.getInt("Highscore");
					if (this_score == score) {
						if (!user_list.contains(user)) {
							found_user = true;
							user_list.add(user);
						}
					}
				}
				if (found_user == false) {
					user_list.add("N/A");
				}
			}
			//now the highscore list contains the scores and the user_list contains the corresponding users
			for (int i = 0; i < 5; i++) {
				String column = user_list.get(i) + " : " + Integer.toString(highscore_list.get(i)) + "\n";
				final_list[i] = column;
			}
			conn.close();
			return final_list;
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return final_list;
	}
	

}
