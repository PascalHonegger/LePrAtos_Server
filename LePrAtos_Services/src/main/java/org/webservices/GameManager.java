package org.webservices;

import java.util.UUID;

public class GameManager
{
	public String login(String username)
	{
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	public String logout()
	{
		return "Logged out";
	}
}
