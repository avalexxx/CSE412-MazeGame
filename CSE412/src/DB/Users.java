package DB;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Users {

	

	/**
	 * Creates a new user by getting the highest uid and incrementing it and inputs
	 * @param uname - User name
	 * @param pw - Users password
	 */
	private static void insertUser(String uname, String pw)
	{
		try {
			//set up connection and get highest ID
			Statement stmt = Tools.getConnection().createStatement();
			ResultSet idResult = stmt.executeQuery("SELECT MAX(uid)AS highestID FROM users");		
			
			//locally store highest ID
			int highestID =0;
			while(idResult.next())
				highestID = idResult.getInt("highestID");
			
			//set the next user to the highest primary key + 1
			stmt.executeUpdate("INSERT INTO users VALUES (" + (highestID+1) + ", '" + uname + "', '" + pw + "')");
		
		} catch (SQLException e) {
			System.out.println("User creation failure");
			e.printStackTrace();
		}
		
	}

	/**
	 * Prints all users in user table
	 */
	static void printUsersTable()
	{
	
		try {

			Statement stmt = Tools.getConnection().createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM users");
			
			System.out.println("---USER TABLE---");
			for(int i=1; i<res.getMetaData().getColumnCount()+1; i++)
				System.out.print(res.getMetaData().getColumnName(i) + "\t\t\t");
			
			System.out.println();
			//print every user of tables
			while(res.next())
				System.out.println(res.getInt("uid") + "\t\t\t" + res.getString("uname") + "\t\t\t" + res.getString("pw"));

			
		} catch (SQLException e) {
			System.out.println("FAILED TO PRINT USERS");
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes the user from the DB according it its uid (primary key)
	 * @param uid - userID
	 */
	private static void deleteUser(String uid)
	{
		try {
			System.out.println("DELETING USER...");
			
			//Execute deletion
			Statement stmt = Tools.getConnection().createStatement();
			stmt.executeUpdate("DELETE FROM users WHERE uid = " + uid );
			
			System.out.println("SUCCESSFUL DELETION");
			
		} catch (SQLException e) {
			System.out.println("UNSEUCCESFUL USER DELETION");
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns all users from the DB
	 * @return ResultSet users -  All users
	 */
	private static ResultSet getUsers()
	{
		try {
			Statement stmt = Tools.getConnection().createStatement();
			return stmt.executeQuery("SELECT * FROM users");
			
			
		} catch (SQLException e) {
			System.out.println("FAILED TO GET USERSs");
			e.printStackTrace();
		}
		return null;
	}
	
}
