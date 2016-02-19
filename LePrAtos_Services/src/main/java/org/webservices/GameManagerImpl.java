package org.webservices;

import java.util.ArrayList;

import javax.jws.WebService;

import org.javaclasses.GameLobby;
import org.javaclasses.Player;

@WebService(endpointInterface = "org.webservices.GameManager")
public class GameManagerImpl implements GameManager
{
	private ArrayList<Player> PlayerList = new ArrayList<Player>();
	private static ArrayList<GameLobby> GameLobbyList = new ArrayList<GameLobby>();

	public Player login(String username)
	{
		if (username.length() > 30)
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
		GameLobby newLobby = new GameLobby(spieler);
		GameLobbyList.add(newLobby);
		return newLobby;
	}

	public GameLobby joinGameLobby(Player spieler, String GameLobbyID)
	{
		return new GameLobby(spieler);
	}

	public GameLobby getGameLobby(String GameLobbyID)
	{
		for (int zaehler = 0; zaehler < GameLobbyList.size(); zaehler++)
		{
			if (GameLobbyList.get(zaehler).getGameLobbyID() == GameLobbyID)
			{
				return GameLobbyList.get(zaehler);
			}
		}
		return null;
	}
}
