package org.javaclasses;

import java.util.ArrayList;
import java.util.UUID;

public class GameLobby
{
	public String GameLobbyID;
	private ArrayList<Player> GamePlayerList = new ArrayList<Player>();
	
	public GameLobby(Player spieler)
	{
		GamePlayerList.add(spieler);
		GameLobbyID = UUID.randomUUID().toString();
	}
	
	public GameLobby()
	{
		
	}
	
	public void joinGameLobby(Player spieler)
	{
		GamePlayerList.add(spieler);
	}
}
