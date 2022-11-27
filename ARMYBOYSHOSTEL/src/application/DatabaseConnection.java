package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	
	public Connection databaseLink;
	
	public Connection getConnection() {
		String DatabaseName = "project";
		String DatabaseUser = "root";
		String DatabasePassword = "12345678";
		
		String url = "jdbc:mysql://localhost:3306/" + DatabaseName;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(url,DatabaseUser,DatabasePassword);
		}
		catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		
		return databaseLink;
	}
	
	
	
}
