package org.javaclasses;

public class PlayerIdentification
{
	private String username;
	private boolean status;
	
	public PlayerIdentification(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public boolean isStatus()
	{
		return status;
	}

	public void setStatus(boolean status)
	{
		this.status = status;
	}
	
	
}
