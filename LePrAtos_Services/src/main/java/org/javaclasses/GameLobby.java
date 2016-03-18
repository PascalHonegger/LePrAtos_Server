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
//		gamePlayerList.add((PlayerIdentification)currentPlayer);
		info.getInactivePlayerList().remove(currentPlayer);
		gameLobbyID = UUID.randomUUID().toString();
		
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
				throw new MyExceptions("Lobby ist voll"); 
			}
		}
		else
		{
			throw new MyExceptions("Lobby-Kennwort ist falsch");
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
			if(gameLobbyAdmin == spieler)
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

	public int getPlayerLimit()
	{
		return playerLimit;
	}

	public void setPlayerLimit(int playerLimit)
	{
		this.playerLimit = playerLimit;
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

	public static GameLobby getGameLobbyByID(String GameLobbyID)
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
		
		return currentGameLobby;
	}
	
	
	
	
	
	
	
	
	
	
	

}
