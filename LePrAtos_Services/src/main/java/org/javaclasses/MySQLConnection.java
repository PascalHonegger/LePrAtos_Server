package org.javaclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQLConnection
{
	final static Logger logger = Logger.getLogger(MySQLConnection.class);
	
	MysqlDataSource dataSource = new MysqlDataSource();
	Connection connection = null;
	
	public MySQLConnection()
	{
		try
		{
			dataSource.setUser("LePrAtos_User");
			dataSource.setPassword("LePrAtos_Sicheres_Passort_2015");
			dataSource.setURL("jdbc:mysql://localhost:3306/lepratos_db");
			connection = dataSource.getConnection();
		}
		catch (SQLException e)
		{
			logger.error("An error has occurred!", e);
			throw new IllegalStateException("Cannot connect to database!", e);
		}
	}
	
	public void closeConnection()
	{
		try 
		{
			this.connection.close();
		} 
		catch (SQLException e) 
		{
			logger.error("An error has occurred!", e);
			e.printStackTrace();
		}
	}
	
	public void writeToDatabase(PreparedStatement ps)
	{
		try
		{
			ps.executeUpdate();
		}
		catch (SQLException e) 
		{
			logger.error("An error has occurred!", e);
		    throw new IllegalStateException("Cannot execute update!", e);
		}	
	}
	
	public ResultSet readFromDatabase(PreparedStatement ps)
	{
		ResultSet rs = null;
		
		try
		{
			rs = ps.executeQuery();
		}
		catch (SQLException e) 
		{
			logger.error("An error has occurred!", e);
		    throw new IllegalStateException("Cannot execute update!", e);
		}	
		
		return rs;
	}
	
	public void createUser(String playerID, String username, String email, String password)
	{
		String query = "Insert into user(ID_User,username,mail,password) values(?,?,?,?);";
		
		try
		{
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, playerID);
			ps.setString(2, username);
			ps.setString(3, email);
			ps.setString(4, password);
			
			this.writeToDatabase(ps);
			
		}
		catch (SQLException e)
		{
			logger.error("An error has occurred!", e);
			throw new IllegalStateException("Cannot create Statement!", e);
		}		
	}
	
	public String getUserPasswordFromEmail(String email)
	{
		String query = "Select password from user where mail = ?";
		
		try
		{
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
			
			ResultSet result = this.readFromDatabase(ps);
			
			result.next();
			
			return result.getString(1);
		}
		catch (SQLException e)
		{
			logger.error("An error has occurred!", e);
			throw new IllegalStateException("Cannot create Statement!", e);
		}		
	}
	
	public boolean username_availability(String username)
	{
		String query = "Select * from user where username = ?;";
		ResultSet rs = null;
		
		try
		{
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
		
			rs = this.readFromDatabase(ps);
		}
		
		catch (SQLException e)
		{
			logger.error("An error has occurred!", e);
			throw new IllegalStateException("Cannot create Statement!", e);
		}
		
		try
		{
			if(!rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		catch (SQLException e)
		{
			logger.error("An error has occurred!", e);
			throw new IllegalStateException("Irgend en Error", e);
		}
	}
	
	public boolean email_verification(String email)
	{
		String query = "Select * from user where mail = ?;";
		ResultSet rs = null;
		
		try
		{
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
		
			rs = this.readFromDatabase(ps);
		}
		
		catch (SQLException e)
		{
			logger.error("An error has occurred!", e);
			throw new IllegalStateException("Cannot create Statement!", e);
		}
		
		try
		{
			if(!rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		catch (SQLException e)
		{
			logger.error("An error has occurred!", e);
			throw new IllegalStateException("Irgend en Error", e);
		}
		
		
	}
	
	public Player loginPlayerByEmail(String email, String password)
	{
		String query = "Select ID_User,username,mail from user where mail = ? and password = ?;";
		
		Player currentPlayer = null;
		
		String playerID = null;
		String username = null;
		
		ResultSet rs = null;
		
		try
		{
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
		
			rs = this.readFromDatabase(ps);
		}
		
		catch (SQLException e)
		{
			logger.error("An error has occurred!", e);
			throw new IllegalStateException("Cannot create Statement!", e);
		}
		
		try
		{
			if(!rs.next())
			{
				return null;	
			}
			else
			{
				playerID = rs.getString(1);
				username = rs.getString(2);
				email = rs.getString(3);
			}
			
				currentPlayer = new Player(playerID,email,username);
		}
		catch (SQLException e)
		{
			logger.error("An error has occurred!", e);
			throw new IllegalStateException("It doesn't work :/", e);
		}
		
		return currentPlayer;
	}
	
	public boolean checkUserPasswordByEmail(String email, String password)
	{
		String query = "Select ID_User,username,mail from user where mail = ? and password = ?;";
		
		Boolean passwordCorrect;
		
		String playerID = null;
		String username = null;
		
		ResultSet rs = null;
		
		try
		{
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
		
			rs = this.readFromDatabase(ps);
		}
		
		catch (SQLException e)
		{
			logger.error("An error has occurred!", e);
			throw new IllegalStateException("Cannot create Statement!", e);
		}
		
		try
		{
			if(!rs.next())
			{
				return false;	
			}
			else
			{
				playerID = rs.getString(1);
				username = rs.getString(2);
				email = rs.getString(3);
			}
			
				passwordCorrect = true;
		}
		catch (SQLException e)
		{
			logger.error("An error has occurred!", e);
			throw new IllegalStateException("It doesn't work :/", e);
		}
		
		return passwordCorrect;
	}
	
	public Player loginPlayerByUsername(String username, String password)
	{
		String query = "Select ID_User,username,mail from user where username = ? and password = ?;";
		
		Player currentPlayer = null;
		
		String playerID = null;
		String email = null;
		
		ResultSet rs = null;
		
		try
		{
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
		
			rs = this.readFromDatabase(ps);
		}
		
		catch (SQLException e)
		{
			logger.error("An error has occurred!", e);
			throw new IllegalStateException("Cannot create Statement!", e);
		}
		
		try
		{
			if(!rs.next())
			{
				return null;	
			}
			else
			{
				
				playerID = rs.getString(1);
				username = rs.getString(2);
				email = rs.getString(3);
			}
			
				currentPlayer = new Player(playerID,email,username);
		}
		catch (SQLException e)
		{
			logger.error("An error has occurred!", e);
			throw new IllegalStateException("It doesn't work :/", e);
		}
		
		return currentPlayer;
	}
	
	public void setNewUserPassword(String mail, String newPassword)
	{
		String query = "Update user Set password = ? Where mail = ?";
		
		try
		{
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, newPassword);
			ps.setString(2, mail);
			
			this.writeToDatabase(ps);
			
		}
		catch (SQLException e)
		{
			logger.error("An error has occurred!", e);
			throw new IllegalStateException("Cannot create Statement!", e);
		}		
	}
}	
