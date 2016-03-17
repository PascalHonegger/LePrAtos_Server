package org.javaclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection
{
	String dbUrl = "jdbc:mysql://localhost/lepratos_db";
	String dbClass = "com.mysql.jdbc.Driver";
	String query;
	String dbusername = "root";
	String dbpassword = "root";

	public void createUser(String username, String email, String password)
	{
		query = "Insert into user(username,mail,password) values('"+username+","+email+","+password+"');";
		
		try
		{
			Class.forName(dbClass);
			Connection connection = DriverManager.getConnection(dbUrl, dbusername, dbpassword);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			connection.close();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}	

	
