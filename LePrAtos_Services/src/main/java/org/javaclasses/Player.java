package org.javaclasses;

import java.util.UUID;

public class Player extends PlayerIdentification
{
	private String playerID;
	private String email;
	private String password;
	
	public Player(String email, String username, String password)
	{
		super(username);
		this.playerID = UUID.randomUUID().toString();
		this.email = email;
		this.password = password;
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

}
