package org.javaclasses;

import java.util.ArrayList;
import java.util.List;

public class PersistentInformation
{

	private static PersistentInformation instance;
	
	public static PersistentInformation getInstance() {
		if (instance == null) {
			instance = new PersistentInformation();
		}
		return instance;
	}
	
	private PersistentInformation()
	{
	}
	
	private List<Player> inactivePlayerList = new ArrayList<Player>();
	private List<Player> playerList = new ArrayList<Player>();
	private List<GameLobby> gameLobbyList = new ArrayList<GameLobby>();
	
	public List<GameLobby> getGameLobbyList() {
		return gameLobbyList;
	}

	public List<Player> getInactivePlayerList()
	{
		return inactivePlayerList;
	}

	public void setInactivePlayerList(List<Player> inactivePlayer)
	{
		this.inactivePlayerList = inactivePlayer;
	}

	public List<Player> getPlayerList()
	{
		return playerList;
	}

	public void setPlayerList(List<Player> playerList)
	{
		this.playerList = playerList;
	}

	public void setGameLobbyList(List<GameLobby> gameLobbyList)
	{
		this.gameLobbyList = gameLobbyList;
	}
	
	
	
}
