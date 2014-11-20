package DB;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mazes {

	static void printMazesTable()
	{
	
		try {

			Statement stmt = Tools.getConnection().createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM mazes");
			
			System.out.println("---MAZES TABLE---");
			for(int i=1; i<res.getMetaData().getColumnCount()+1; i++)
				System.out.print(res.getMetaData().getColumnName(i) + "\t\t\t");
			
			System.out.println();
			//print every user of tables
			while(res.next())
				System.out.println(res.getInt("mid") + "\t\t\t" + res.getString("mname") + "\t\t\t" + res.getString("diff")+ "\t\t\t" + res.getString("mobj"));

			
		} catch (SQLException e) {
			System.out.println("FAILED TO PRINT MAZES");
			e.printStackTrace();
		}
	}
	
	static void insertMaze(String mname, String diff, File mobj)
	{
		try {
			//set up connection and get highest ID
			Statement stmt = Tools.getConnection().createStatement();
			ResultSet idResult = stmt.executeQuery("SELECT MAX(mid)AS highestID FROM mazes");		
			
			//locally store highest ID
			int highestID =0;
			while(idResult.next())
				highestID = idResult.getInt("highestID");
			
			//set the next user to the highest primary key + 1
			stmt.executeUpdate("INSERT INTO mazes VALUES (" + (highestID+1) + ", '" + mname + "', '" + diff + "')");
		
		} catch (SQLException e) {
			System.out.println("User creation failure");
			e.printStackTrace();
		}
	}
	
	static void getMazes()
	{
		
	}
}
