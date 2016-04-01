package org.javaclasses;

import java.util.UUID;

public class Player extends PlayerIdentification
{
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
			throw new MyExceptions("Player not found");
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
				throw new MyExceptions("Username already taken");
			}
		}
		else
		{
			throw new MyExceptions("Email address is already in use");
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
				throw new MyExceptions("Player already logged in");
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
					throw new MyExceptions("Incorrect data");
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
				throw new MyExceptions("Player already logged in");
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
					throw new MyExceptions("Incorrect data");
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
			throw new MyExceptions("Cannot delete player");
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
			throw new MyExceptions("Cannot delete player");
		}
	}
	
	

}
