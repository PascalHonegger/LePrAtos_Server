package org.webservices;

import java.util.ArrayList;
import java.util.List;

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
	
//	private static ArrayList<GameLobby> gameLobbyList = new ArrayList<GameLobby>();

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

	public String logout(String playerID)
	{
//		Player currentPlayer = null;
//		
//		for (Player player : info.getActivePlayerList())
//		{
//			if (player.getPlayerID().equals(playerID)) {
//				currentPlayer = player;
//				break;
//			}
//		}
//		
//		info.getActivePlayerList().remove(currentPlayer);
		
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
		
		info.getGameLobbyList().add(newLobby);
//		info.getActivePlayerList().remove(currentPlayer);
		
		return newLobby;
	}

	public GameLobby joinGameLobby(String playerID, String GameLobbyID) throws Exception
	{
		Player currentPlayer = null;
		GameLobby currentGameLobby = null;
		
		for (Player player : info.getActivePlayerList())
		{
			if (player.getPlayerID().equals(playerID)) {
				currentPlayer = player;
				break;
			}
		}
		
		for (GameLobby gamelobby : info.getGameLobbyList())
		{
			if (gamelobby.getGameLobbyID().equals(GameLobbyID)) {
				currentGameLobby = gamelobby;
				break;
			}
		}
		
		currentGameLobby.joinGameLobby(currentPlayer);
		
		return currentGameLobby;
	}

	public GameLobby getGameLobby(String GameLobbyID)
	{
		
		GameLobby currentGameLobby = null;
		
//		for (int zaehler = 0; zaehler < gameLobbyList.size(); zaehler++)
//		{
//			if (gameLobbyList.get(zaehler).getGameLobbyID() == GameLobbyID)
//			{
//				return gameLobbyList.get(zaehler);
//			}
//		}
		
		for (GameLobby gamelobby : info.getGameLobbyList())
		{
			if (gamelobby.getGameLobbyID().equals(GameLobbyID)) {
				currentGameLobby = gamelobby;
				break;
			}
		}
		
		return currentGameLobby;
	}
	
	public List getGameLobbies()
	{
		GameLobby currentGameLobby = null;
		List<GameLobby> gameLobbies = new ArrayList<GameLobby>();
		
		for (GameLobby gamelobby : info.getGameLobbyList())
		{
			gameLobbies.add(gamelobby);
		}
		
		return gameLobbies;
	}
	
	public Player getPlayerByID(String playerID)
	{
		Player currentPlayer = null;
		
		for (Player player : info.getActivePlayerList())
		{
			if (player.getPlayerID().equals(playerID)) {
				currentPlayer = player;
				break;
			}
		}
		return currentPlayer;
	}
}
