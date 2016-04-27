package org.javaclasses;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.apache.log4j.Logger;

public class Player extends PlayerIdentification
{
	final static Logger logger = Logger.getLogger(Player.class);
	
	private static PersistentInformation info;
	
	private String playerID;
	private String email;
	
	public Player(String email, String username)
	{	
		super(username);
		this.playerID = UUID.randomUUID().toString();
		this.email = email;
	}
	
	public Player(String playerID, String email, String username)
	{
		super(username);
		this.playerID = playerID;
		this.email = email;
	}
	
	public String getPlayerID()
	{
		return playerID;
	}

	public void setPlayerID(String playerID)
	{
		this.playerID = playerID;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public static Player getPlayerByID(String playerID) throws MyExceptions
	{
		info = PersistentInformation.getInstance();
		
		Player currentPlayer = null;
		
		for (Player player : info.getOnlinePlayerList())
		{
			if (player.getPlayerID().equals(playerID)) 
			{
				currentPlayer = player;
				break;
			}
		}
		
		if(currentPlayer != null)
		{
			return currentPlayer;
		}
		else
		{
			MyExceptions newException = new MyExceptions("Player not found");
			PropertyConfigurator.configure("log4j.properties");
			logger.error("An error has occurred!", newException);
			throw newException;
		}
	}
	
	public static Player getPlayerByUsername(String username)
	{
		info = PersistentInformation.getInstance();
		Player currentPlayer = null;
		
		for (Player player : info.getOnlinePlayerList())
		{
			if (player.getUsername().equals(username)) 
			{
				currentPlayer = player;
				break;
			}
		}
		
		return currentPlayer;
	
	}
	
	public static Player getInactivePlayer(String playerID)
	{
		info = PersistentInformation.getInstance();
		Player currentPlayer = null;
		
		for (Player player : info.getInactivePlayerList())
		{
			if (player.getPlayerID().equals(playerID)) 
			{
				currentPlayer = player;
				break;
			}
		}
		
		return currentPlayer;
	}
	
	public static boolean username_availability(String username)
	{
		MySQLConnection dbconnection = new MySQLConnection();
		
		Boolean result = dbconnection.username_availability(username);
		dbconnection.closeConnection();
		
		return result;
	}
	
	public static boolean email_verification(String email)
	{
		MySQLConnection dbconnection = new MySQLConnection();
		
		Boolean result = dbconnection.email_verification(email);
		dbconnection.closeConnection();
		
		return result;
	}
	
	public static Player registration(String email, String username, String password) throws MyExceptions
	{
		info = PersistentInformation.getInstance();
		
		MySQLConnection dbconnection = new MySQLConnection();
		
		Player spieler = null;
		
		if(dbconnection.email_verification(email))
		{
			if(dbconnection.username_availability(username))
			{
				spieler = new Player(email,username);
				
				
				dbconnection.createUser(spieler.playerID,username, email, password);
				dbconnection.closeConnection();
				
				info.getOnlinePlayerList().add(spieler);
				info.getInactivePlayerList().add(spieler);
			}
			else
			{
				MyExceptions newException = new MyExceptions("RegisterView_BadRegister"); //Username already taken
				PropertyConfigurator.configure("log4j.properties");
				logger.error("An error has occurred!", newException);
				throw newException;
			}
		}
		else
		{
			MyExceptions newException = new MyExceptions("RegisterView_BadRegister"); //Email address is already in use
			PropertyConfigurator.configure("log4j.properties");
			logger.error("An error has occurred!", newException);
			throw newException;
		}
		
		return spieler;
	}
	
	public static Player login(String username_email, String password) throws MyExceptions
	{
		info = PersistentInformation.getInstance();
		
		Player currentPlayer = null;
		
		boolean username_or_email = username_email.contains("@");
		
		if (username_or_email == true)
		{	
			for (Player player : info.getOnlinePlayerList())
			{
				if (player.getEmail().equals(username_email)) 
				{
					currentPlayer = player;
					break;
				}
			}
			
			if(currentPlayer != null)
			{
				MyExceptions newException = new MyExceptions("LoginView_AlreadyLoggedIn");
				PropertyConfigurator.configure("log4j.properties");
				logger.error("An error has occurred!", newException);
				throw newException;
			}
			else
			{
				MySQLConnection dbconnection = new MySQLConnection();
				currentPlayer = dbconnection.loginPlayerByEmail(username_email, password);
			
				if(currentPlayer != null)
				{
					info.getOnlinePlayerList().add(currentPlayer);
					info.getInactivePlayerList().add(currentPlayer);
				
					return currentPlayer;
				}
				else
				{	
					MyExceptions newException = new MyExceptions("LoginView_BadLogin");
					PropertyConfigurator.configure("log4j.properties");
					logger.error("An error has occurred!", newException);
					throw newException;
				}
			}
		}
		
		else
		{
			for (Player player : info.getOnlinePlayerList())
			{
				if (player.getUsername().equals(username_email)) 
				{
					currentPlayer = player;
					break;
				}
			}
			
			if(currentPlayer != null)
			{
				MyExceptions newException = new MyExceptions("LoginView_AlreadyLoggedIn");
				PropertyConfigurator.configure("log4j.properties");
				logger.error("An error has occurred!", newException);
				throw newException;
			}
			else
			{
				MySQLConnection dbconnection = new MySQLConnection();
				currentPlayer = dbconnection.loginPlayerByUsername(username_email, password);
			
				if(currentPlayer != null)
				{
					info.getOnlinePlayerList().add(currentPlayer);
					info.getInactivePlayerList().add(currentPlayer);
				
					return currentPlayer;
				}
				else
				{
					MyExceptions newException = new MyExceptions("LoginView_BadLogin");
					PropertyConfigurator.configure("log4j.properties");
					logger.error("An error has occurred!", newException);
					throw newException;
				}
			}
		}
	}
	
	public static void removePlayerFromInactivePlayerList(String playerID) throws MyExceptions
	{
		info = PersistentInformation.getInstance();
	
		try
		{
			info.getInactivePlayerList().remove(Player.getInactivePlayer(playerID));
		}
		catch (Exception e)
		{
			MyExceptions newException = new MyExceptions("Cannot delete player");
			PropertyConfigurator.configure("log4j.properties");
			logger.error("An error has occurred!", newException);
			throw newException;
		}
		
	}
	
	public static void removePlayerFromOnlinePlayerList(String playerID) throws MyExceptions
	{
		info = PersistentInformation.getInstance();
	
		try
		{
			info.getOnlinePlayerList().remove(Player.getPlayerByID(playerID));
		}
		catch (Exception e)
		{
			MyExceptions newException = new MyExceptions("Cannot delete player");
			PropertyConfigurator.configure("log4j.properties");
			logger.error("An error has occurred!", newException);
			throw newException;
		}
	}

	public static String getPasswordFromEmail(String email) 
	{
		return new MySQLConnection().getUserPasswordFromEmail(email);
	}
	
	public static void setPasswordFromReset(String mail, String currentPassword, String newPassword) throws MyExceptions
	{
		if(new MySQLConnection().checkUserPasswordByEmail(mail, currentPassword))
		{
			new MySQLConnection().setNewUserPassword(mail,newPassword);
		}
		else
		{
			MyExceptions newException = new MyExceptions("Cannot set new password");
			PropertyConfigurator.configure("log4j.properties");
			logger.error("An error has occurred!", newException);
			throw newException;
		}
	}
	
	

}
