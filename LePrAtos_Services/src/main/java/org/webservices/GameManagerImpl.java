package org.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.javaclasses.GameLobby;
import org.javaclasses.MyExceptions;
import org.javaclasses.PersistentInformation;
import org.javaclasses.Player;
import org.javaclasses.PlayerIdentification;


@WebService(endpointInterface = "org.webservices.GameManager")
public class GameManagerImpl implements GameManager
{
	private PersistentInformation info;

	public GameManagerImpl()
	{
		info = PersistentInformation.getInstance();
	}
	
//	private static ArrayList<GameLobby> gameLobbyList = new ArrayList<GameLobby>();

	public Player registration(String email, String username, String password)
	{
		Player spieler = new Player(email,username,password);
		info.getInactivePlayerList().add(spieler);
		info.getPlayerList().add(spieler);
		
		return spieler;
	}
	
	public boolean username_availability(String username)
	{
		for (Player player : info.getPlayerList())
		{
			if (player.getUsername().equals(username)) 
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean email_verification(String email)
	{
		for (Player player : info.getPlayerList())
		{
			if (player.getEmail().equals(email)) 
			{
				return false;
			}
		}
		return true;
	}
	
	public Player login(String username_email, String password) throws MyExceptions
	{
		boolean username_or_email = username_email.contains("@");
		
		if (username_or_email == true)
		{	
			for (Player player : info.getPlayerList())
			{
				if (player.getEmail().equals(username_email)) 
				{
					if (player.getPassword().equals(password))
					{
						return player;
					}
					else
					{
						throw new MyExceptions("Ungültiges Kennwort");
					}
				}	
			}
			
			throw new MyExceptions("User nicht vorhanden");
		}
		
		else
		{
			for (Player player : info.getPlayerList())
			{
				if (player.getUsername().equals(username_email)) 
				{
					if (player.getPassword().equals(password))
					{
						return player;
					}
					else
					{
						throw new MyExceptions("Ungültiges Kennwort");
					}
				}	
			}
			
			throw new MyExceptions("User nicht vorhanden");
		}
		
	}

	public void logout(String playerID)
	{
		Player currentPlayer = null;
		
		for (Player player : info.getPlayerList())
		{
			if (player.getPlayerID().equals(playerID)) 
			{
				info.getPlayerList().remove(player);
				currentPlayer = player;
				break;
			}
		}
		
		for (Player player : info.getInactivePlayerList())
		{
			if (player.getPlayerID().equals(playerID)) 
			{
				info.getInactivePlayerList().remove(player);
				break;
			}
		}
		
		for (GameLobby gamelobby : info.getGameLobbyList())
		{
//			GameLobby currentGameLobby = gamelobby;
			
			for (PlayerIdentification player : gamelobby.getGamePlayerList())
			{
				if (player.getUsername().equals(currentPlayer.getUsername())) 
				{
					gamelobby.getGamePlayerList().remove(player);
					
					if(gamelobby.getGameLobbyAdmin() == player)
					{
						gamelobby.setNewAdmin();
					}
					
					break;
				}
			}
		}
		
		
		
	}

	public GameLobby createGameLobby(String playerID, String gameLobbyID) throws Exception
	{
		Player currentPlayer = null;
		
			for (Player player : info.getInactivePlayerList())
			{
				if (player.getPlayerID().equals(playerID)) {
					currentPlayer = player;
					break;
				}
			}
			
		GameLobby newLobby = new GameLobby(currentPlayer, gameLobbyID);
		
		info.getGameLobbyList().add(newLobby);
		info.getInactivePlayerList().remove(currentPlayer);
		
		return newLobby;
	}

	public GameLobby joinGameLobby(String playerID, String GameLobbyID) throws Exception
	{
		Player currentPlayer = null;
		GameLobby currentGameLobby = null;
		
		for (Player player : info.getInactivePlayerList())
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
	
	public void leaveGameLobby(String playerID, String GameLobbyID)
	{
		Player currentPlayer = null;
		GameLobby currentGameLobby = null;
		
		for (Player player : info.getPlayerList())
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
		
		
		
		
		
		currentGameLobby.leaveGameLobby(currentPlayer);
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
	
	public List<GameLobby> getGameLobbies()
	{
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
		
		for (Player player : info.getPlayerList())
		{
			if (player.getPlayerID().equals(playerID)) {
				currentPlayer = player;
				break;
			}
		}
		return currentPlayer;
	}
	
	public void setPlayerStatus(String playerID, boolean status)
	{
		Player currentPlayer = null;
		
		for (Player player : info.getPlayerList())
		{	
			if (player.getPlayerID().equals(playerID)) 
			{
				currentPlayer = player;
				break;
			}
		}
		
		currentPlayer.setStatus(status);
		
	}
}
