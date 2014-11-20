package DB;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Tools {
	
	static Connection getConnection()  
	{
		try {
				URI dbUri = new URI( "mysql://bbfc6fd35ea45e:9d0adc31@us-cdbr-iron-east-01.cleardb.net/heroku_cd30dea39f04c7e?reconnect=true");
				Class.forName("com.mysql.jdbc.Driver");
	
				String username = dbUri.getUserInfo().split(":")[0];
				String password = dbUri.getUserInfo().split(":")[1];
				String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
	
				return DriverManager.getConnection(dbUrl, username, password);

		}catch (URISyntaxException e2) {
			System.out.println("Unable to parse address");
			e2.printStackTrace();
		}
		catch (ClassNotFoundException e1) {
			System.out.println("You didn't add the jdbc to sql driver to your environment as recommended");
			e1.printStackTrace();
		}

		catch (SQLException e) {
			System.out.println("Unable to connect to DB");
			e.printStackTrace();
		}
		
		return null;

	}
}
