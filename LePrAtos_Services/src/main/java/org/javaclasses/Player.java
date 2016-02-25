package org.javaclasses;

import java.util.UUID;

public class Player extends PlayerIdentification
{
	private String playerID;
	
	public Player(String username)
	{
		super(username);
		this.playerID = UUID.randomUUID().toString();
	}
	
	public String getPlayerID()
	{
		return playerID;
	}

	public void setPlayerID(String playerID)
	{
		this.playerID = playerID;
	}
	
	

}
