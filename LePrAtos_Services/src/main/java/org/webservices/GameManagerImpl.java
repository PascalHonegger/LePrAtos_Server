package org.webservices;

import java.util.ArrayList;

import javax.jws.WebService;

import org.javaclasses.GameLobby;
import org.javaclasses.PersistentInformation;
import org.javaclasses.Player;


@WebService(endpointInterface = "org.webservices.GameManager")
public class GameManagerImpl implements GameManager
{
	private PersistentInformation info;

	public GameManagerImpl()
	{
		info = PersistentInformation.getInstance();
	}
	
	private static ArrayList<GameLobby> gameLobbyList = new ArrayList<GameLobby>();

	public Player login(String username)
	{
		if (username.length() > 30)
		{
			throw new UnsupportedOperationException("Username too long!");
		}
		else
		{
			Player spieler = new Player(username);
			info.getActivePlayerList().add(spieler);

			return spieler;
		}
	}

	public String logout()
	{
		return "Logged out";
	}

	public GameLobby createGameLobby(String playerID) throws Exception
	{
		Player currentPlayer = null;
		
			for (Player player : info.getActivePlayerList())
			{
				if (player.getPlayerID().equals(playerID)) {
					currentPlayer = player;
					break;
				}
			}
			
		GameLobby newLobby = new GameLobby(currentPlayer);
		
		gameLobbyList.add(newLobby);
		return newLobby;
	}

	public GameLobby joinGameLobby(Player spieler, String GameLobbyID) throws Exception
	{
		return new GameLobby(spieler);
	}

	public GameLobby getGameLobby(String GameLobbyID)
	{
		for (int zaehler = 0; zaehler < gameLobbyList.size(); zaehler++)
		{
			if (gameLobbyList.get(zaehler).getGameLobbyID() == GameLobbyID)
			{
				return gameLobbyList.get(zaehler);
			}
		}
		return null;
	}
}
