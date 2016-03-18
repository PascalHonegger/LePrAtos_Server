package org.javaclasses;

import java.util.UUID;

public class Player extends PlayerIdentification
{
	private static PersistentInformation info;
	
	private String playerID;
	private String email;
	private String password;
	
	public Player(String email, String username, String password)
	{	
		super(username);
		this.playerID = UUID.randomUUID().toString();
		this.email = email;
		this.password = password;
		
//		MySQLConnection dbconnection = new MySQLConnection();
//		dbconnection.createUser(username, email, password);
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

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public static Player getPlayerByID(String playerID)
	{
		info = PersistentInformation.getInstance();
		
		Player currentPlayer = null;
		
		for (Player player : info.getPlayerList())
		{
			if (player.getPlayerID().equals(playerID)) 
			{
				currentPlayer = player;
				break;
			}
		}
		
		return currentPlayer;
	}
	
	public static Player getPlayerByUsername(String username)
	{
		info = PersistentInformation.getInstance();
		Player currentPlayer = null;
		
		for (Player player : info.getPlayerList())
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
		info = PersistentInformation.getInstance();
		
		for (Player player : info.getPlayerList())
		{
			if (player.getUsername().equals(username)) 
			{
				return false;
			}
		}
		return true;
	}
	
	public static boolean email_verification(String email)
	{
		info = PersistentInformation.getInstance();
		
		for (Player player : info.getPlayerList())
		{
			if (player.getEmail().equals(email)) 
			{
				return false;
			}
		}
		return true;
	}
	
	public static Player registration(String email, String username, String password)
	{
		info = PersistentInformation.getInstance();
		
		Player spieler = new Player(email,username,password);
		info.getInactivePlayerList().add(spieler);
		info.getPlayerList().add(spieler);
		
		return spieler;
	}
	
	public static Player login(String username_email, String password) throws MyExceptions
	{
		info = PersistentInformation.getInstance();
		
		boolean username_or_email = username_email.contains("@");
		
		if (username_or_email == true)
		{	
			for (Player player : info.getPlayerList())
			{
				if (player.getEmail().equals(username_email)) 
				{
					if (player.getPassword().equals(password))
					{
						return player;
					}
					else
					{
						throw new MyExceptions("Invalid password");
					}
				}	
			}
			
			throw new MyExceptions("User not found");
		}
		
		else
		{
			for (Player player : info.getPlayerList())
			{
				if (player.getUsername().equals(username_email)) 
				{
					if (player.getPassword().equals(password))
					{
						return player;
					}
					else
					{
						throw new MyExceptions("Invalid password");
					}
				}	
			}
			
			throw new MyExceptions("User not found");
		}
		
	}
	
	public static void removePlayerFromPlayerList(String playerID)
	{
		info = PersistentInformation.getInstance();
		
		info.getPlayerList().remove(Player.getPlayerByID(playerID));
	}
	
	public static void removePlayerFromInactivePlayerList(String playerID)
	{
		info = PersistentInformation.getInstance();
	
		info.getInactivePlayerList().remove(Player.getInactivePlayer(playerID));
	}

}
