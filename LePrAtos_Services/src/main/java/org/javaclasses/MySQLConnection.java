package org.javaclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection
{
	public void dbConnect()
	{
		String dbUrl = "jdbc:mysql://localhost/lepatos_db";
		String dbClass = "com.mysql.jdbc.Driver";
		String query = "Select * from user";
		String username = "root";
		String password = "root";
	}
}
