package org.javaclasses;

import java.util.UUID;

public class Player
{
	private String uuid;
	private String username;
	
	public Player(String username)
	{
		this.uuid = UUID.randomUUID().toString();
		this.username = username;
	}
	
	public Player()
	{
		
	}

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}
	
	
}
