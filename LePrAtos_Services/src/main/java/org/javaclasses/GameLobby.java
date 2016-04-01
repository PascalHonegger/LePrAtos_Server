package org.javaclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.ValidationException;

public class GameLobby
{
	private static PersistentInformation info;

	private String gameLobbyID;
	private String gameLobbyName;
	private boolean passwordProtected;
	private String gameLobbyPassword;
	private int playerLimit;
	private PlayerIdentification gameLobbyAdmin;
	private ArrayList<PlayerIdentification> gamePlayerList = new ArrayList<PlayerIdentification>();
	
	
	public GameLobby(Player currentPlayer, String gameLobbyName) throws ValidationException
	{
		info = PersistentInformation.getInstance();
		
		MyAssert.NotNull(currentPlayer, "Spieler muss gesetzt sein");
		gameLobbyAdmin = (PlayerIdentification)currentPlayer;
		gameLobbyAdmin.setStatus(true);
		this.gameLobbyName = gameLobbyName;
		this.passwordProtected = false;
		this.gameLobbyPassword = "";
		this.playerLimit = 4;
		gameLobbyID = UUID.randomUUID().toString();
		info.getInactivePlayerList().remove(currentPlayer);	
	}
	
	public GameLobby()
	{
		
	}
	
	public void joinGameLobby(Player spieler, String gameLobbyPassword) throws MyExceptions
	{
		if("".equals(this.gameLobbyPassword) || this.gameLobbyPassword.equals(gameLobbyPassword))
		{
			if(gamePlayerList.stream().count() < (this.playerLimit - 1))
			{
				gamePlayerList.add((PlayerIdentification)spieler);
				info.getInactivePlayerList().remove(spieler);
			}
			else
			{
				throw new MyExceptions("Lobby is full"); 
			}
		}
		else
		{
			throw new MyExceptions("Invalid lobby password");
		}
	}
	
	public void leaveGameLobby(Player spieler)
	{
		if(gamePlayerList.stream().count() == 0)
		{
			gameLobbyAdmin.setStatus(false);
			info.getGameLobbyList().remove(this);
		}
		else
		{
			if(this.playerIsAdmin(spieler))
			{
				setNewAdmin();
			}
			else
			{
				gamePlayerList.remove(spieler);
			}
			
			spieler.setStatus(false);
			info.getInactivePlayerList().add(spieler);	
		}
		
	}
	
	public void setNewAdmin()
	{
		gameLobbyAdmin = gamePlayerList.get(0);
		this.gamePlayerList.remove(gameLobbyAdmin);
		gameLobbyAdmin.setStatus(true);
	}
	
	public void kickPlayer(Player spieler, Player spieler2)
	{
		if(this.playerIsAdmin(spieler))
		{
			this.getGamePlayerList().remove(spieler2);
		}
	}

	public String getGameLobbyID()
	{
		return gameLobbyID;
	}

	public PlayerIdentification getGameLobbyAdmin()
	{
		return gameLobbyAdmin;
	}

	public void setGameLobbyID(String gameLobbyID)
	{
		this.gameLobbyID = gameLobbyID;
	}

	public String getGameLobbyName()
	{
		return gameLobbyName;
	}

	public void setGameLobbyName(String gameLobbyName)
	{
		this.gameLobbyName = gameLobbyName;
	}
	
	public void setGameLobbyNameByPlayer(Player spieler, String gameLobbyName) throws MyExceptions
	{
		if(this.playerIsAdmin(spieler))
		{
			this.setGameLobbyName(gameLobbyName);
		}
		else
		{
			throw new MyExceptions("Access denied");
		}
	}

	public void setGameLobbyAdmin(PlayerIdentification gameLobbyAdmin)
	{
		this.gameLobbyAdmin = gameLobbyAdmin;
	}

	public ArrayList<PlayerIdentification> getGamePlayerList()
	{
		return gamePlayerList;
	}

	public void setGamePlayerList(ArrayList<PlayerIdentification> gamePlayerList)
	{
		this.gamePlayerList = gamePlayerList;
	}

	public void setGameLobbyPassword(String gameLobbyPassword) 
	{
		this.gameLobbyPassword = gameLobbyPassword;
		this.passwordProtected = true;
	}
	
	public void setGameLobbyPasswordByPlayer(Player spieler, String gameLobbyPassword) throws MyExceptions
	{
		if(this.playerIsAdmin(spieler))
		{
			this.setGameLobbyPassword(gameLobbyPassword);
		}
		else
		{
			throw new MyExceptions("Access denied");
		}
	}

	public int getPlayerLimit()
	{
		return playerLimit;
	}

	public void setPlayerLimit(int playerLimit)
	{
		this.playerLimit = playerLimit;
	}
	
	public void setPlayerLimitByPlayer(Player spieler, int playerLimit) throws MyExceptions
	{
		if(this.playerIsAdmin(spieler))
		{
			this.setPlayerLimit(playerLimit);
		}
		else
		{
			throw new MyExceptions("Access denied");
		}
	}
	
	public boolean isPasswordProtected() {
		return passwordProtected;
	}

	public void setPasswordProtected(boolean passwordProtected) {
		this.passwordProtected = passwordProtected;
	}
	
	public void resetGameLobbyPassword()
	{
		this.gameLobbyPassword = "";
		this.passwordProtected = false;
	}
	
	public void resetGameLobbyPasswordByPlayer(Player spieler) throws MyExceptions
	{
		if(this.playerIsAdmin(spieler))
		{
			this.resetGameLobbyPassword();
		}
		else
		{
			throw new MyExceptions("Access denied");
		}
	}

	public static GameLobby getGameLobbyByID(String GameLobbyID) throws MyExceptions
	{
		info = PersistentInformation.getInstance();
		
		GameLobby currentGameLobby = null;
		
		for (GameLobby gamelobby : info.getGameLobbyList())
		{
			if (gamelobby.getGameLobbyID().equals(GameLobbyID)) {
				currentGameLobby = gamelobby;
				break;
			}
		}
		
		if(currentGameLobby != null)
		{
			return currentGameLobby;
		}
		else
		{
			throw new MyExceptions("Gamelobby not found");
		}
	}
	
	public static GameLobby getGameLobbyFromPlayer(Player spieler)
	{
		info = PersistentInformation.getInstance();
		
		GameLobby currentGameLobby = null;
		
		for (GameLobby gamelobby : info.getGameLobbyList())
		{
			for (PlayerIdentification player : gamelobby.getGamePlayerList())
			{
				if (player.getUsername().equals(spieler.getUsername())) 
				{
					currentGameLobby = gamelobby;
					break;
				}
			}
		}
		
		return currentGameLobby;
	}
	
	public static GameLobby createGameLobby(Player spieler, String gameLobbyName) throws Exception
	{
		info = PersistentInformation.getInstance();
		
		GameLobby newLobby = new GameLobby(spieler, gameLobbyName);
		
		info.getGameLobbyList().add(newLobby);
		
		try
		{
			info.getInactivePlayerList().remove(spieler);
		}
		catch (Exception e)
		{
			throw new MyExceptions("Cannot create gamelobby");
		}
		
		return newLobby;
	}
	
	public boolean playerIsAdmin(Player spieler)
	{
		if(this.getGameLobbyAdmin() == spieler)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static List<GameLobby> getGameLobbies()
	{
		info = PersistentInformation.getInstance();
		
		List<GameLobby> gameLobbies = new ArrayList<GameLobby>();
		
		for (GameLobby gamelobby : info.getGameLobbyList())
		{
			gameLobbies.add(gamelobby);
		}
		
		return gameLobbies;
	}
	
	
	
	
	
	
	
	
	
	
	

}
