package org.javaclasses;

import java.util.UUID;

public class Player
{
	String uuid;
	String username;
	
	public Player(String username)
	{
		uuid = UUID.randomUUID().toString();
		this.username = username;
	}
	
	public Player()
	{
		
	}
}
