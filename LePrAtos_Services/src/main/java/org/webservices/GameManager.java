package org.webservices;

import java.util.ArrayList;

import org.javaclasses.GameLobby;
import org.javaclasses.Player;

public class GameManager
{	
	private ArrayList<Player> PlayerList = new ArrayList<Player>();
	
	public Player login(String username)
	{		
		if(username.length()>30)
		{
			throw new UnsupportedOperationException("Username too long!");
		}
		else
		{
			Player spieler = new Player(username);
			PlayerList.add(spieler);
			
			return spieler;
		}	
	}
	
	public String logout()
	{
		return "Logged out";
	}
	
	public GameLobby createGameLobby(Player spieler)
	{
		return new GameLobby(spieler);
	}
	
	public GameLobby joinGameLobby(Player spieler,String GameLobbyID)
	{
		return new GameLobby(spieler);
	}
	
	public GameLobby getGameLobby(String GameLobbyID)
	{
		return new GameLobby();
	}
}
