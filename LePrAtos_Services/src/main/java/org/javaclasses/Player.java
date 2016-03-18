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
			if (player.getPlayerID().equals(playerID)) {
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
			if (player.getPlayerID().equals(playerID)) {
				currentPlayer = player;
				break;
			}
		}
		
		return currentPlayer;
	}

}
