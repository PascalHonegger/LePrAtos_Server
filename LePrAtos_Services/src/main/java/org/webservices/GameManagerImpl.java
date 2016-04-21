package org.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
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

	public Player registration(String email, String username, String password) throws MyExceptions
	{
		return Player.registration(email, username, password);
	}
	
	public boolean username_availability(String username)
	{
		return Player.username_availability(username);
	}
	
	public boolean email_verification(String email)
	{
		return Player.email_verification(email);
	}
	
	public Player login(String username_email, String password) throws MyExceptions
	{
		return Player.login(username_email, password);
	}

	public void logout(String playerID) throws MyExceptions
	{
		Player currentPlayer = Player.getPlayerByID(playerID);
		
		try
		{
			Player.removePlayerFromInactivePlayerList(playerID);
		}
		catch(Exception e)
		{
			
		}
		
		GameLobby gameLobbyFromPlayer = GameLobby.getGameLobbyFromPlayer(currentPlayer);
		
		if(gameLobbyFromPlayer != null)
		{
			gameLobbyFromPlayer.leaveGameLobby(currentPlayer);
		}
		
		Player.removePlayerFromOnlinePlayerList(playerID);
	}

	public GameLobby createGameLobby(String playerID, String gameLobbyName) throws Exception
	{
		Player currentPlayer = Player.getInactivePlayer(playerID);
			
		return GameLobby.createGameLobby(currentPlayer, gameLobbyName);
	}
	
	public void setGameLobbyName(String playerID, String GameLobbyID, String gameLobbyName) throws MyExceptions
	{
		Player currentPlayer = Player.getPlayerByID(playerID);
		GameLobby currentGameLobby = GameLobby.getGameLobbyByID(GameLobbyID);
		
		currentGameLobby.setGameLobbyNameByPlayer(currentPlayer, gameLobbyName);
	};
	
	public void setGameLobbyPassword(String playerID, String GameLobbyID, String gameLobbyPassword) throws MyExceptions
	{
		Player currentPlayer = Player.getPlayerByID(playerID);
		GameLobby currentGameLobby = GameLobby.getGameLobbyByID(GameLobbyID);
		
		currentGameLobby.setGameLobbyPasswordByPlayer(currentPlayer, gameLobbyPassword);
	};
	
	public void resetGameLobbyPassword(String playerID, String GameLobbyID) throws MyExceptions
	{
		Player currentPlayer = Player.getPlayerByID(playerID);
		GameLobby currentGameLobby = GameLobby.getGameLobbyByID(GameLobbyID);
		
		currentGameLobby.resetGameLobbyPasswordByPlayer(currentPlayer);
	}
	
	public void setPlayerLimit(String playerID, String GameLobbyID, int playerLimit) throws MyExceptions
	{
		Player currentPlayer = Player.getPlayerByID(playerID);
		GameLobby currentGameLobby = GameLobby.getGameLobbyByID(GameLobbyID);
		
		currentGameLobby.setPlayerLimitByPlayer(currentPlayer, playerLimit);
	};
	
	public void kickPlayer(String playerID, String GameLobbyID, String username) throws MyExceptions
	{
		Player currentPlayer = Player.getPlayerByID(playerID);
		Player otherPlayer = Player.getPlayerByUsername(username);
		GameLobby currentGameLobby = GameLobby.getGameLobbyByID(GameLobbyID);
		
		currentGameLobby.kickPlayer(currentPlayer, otherPlayer);
	};

	public GameLobby joinGameLobby(String playerID, String GameLobbyID, String gameLobbyPassword) throws Exception
	{
		Player currentPlayer = Player.getInactivePlayer(playerID);
		GameLobby currentGameLobby = GameLobby.getGameLobbyByID(GameLobbyID);
		
		currentGameLobby.joinGameLobby(currentPlayer, gameLobbyPassword);
		
		return currentGameLobby;
	}
	
	public void leaveGameLobby(String playerID, String GameLobbyID) throws MyExceptions
	{
		Player currentPlayer = Player.getPlayerByID(playerID);
		GameLobby currentGameLobby = GameLobby.getGameLobbyByID(GameLobbyID);
		
		currentGameLobby.leaveGameLobby(currentPlayer);
		
		return;
	}

	public GameLobby getGameLobby(String GameLobbyID) throws MyExceptions
	{
		return GameLobby.getGameLobbyByID(GameLobbyID);
	}
	
	public List<GameLobby> getGameLobbies()
	{
		return GameLobby.getGameLobbies();
	}
	
	public Player getPlayerByID(String playerID) throws MyExceptions
	{
		return Player.getPlayerByID(playerID);
	}
	
	public void setPlayerStatus(String playerID, boolean status) throws MyExceptions
	{
		Player.getPlayerByID(playerID).setStatus(status);
	}
}
