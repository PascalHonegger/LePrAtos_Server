package org.javaclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.ValidationException;

public class GameLobby
{
	private PersistentInformation info;

	private String gameLobbyID;
	private String gameLobbyName;
	private String gameLobbyPassword;
	private int playerLimit = 10;
	private PlayerIdentification gameLobbyAdmin;
	private ArrayList<PlayerIdentification> gamePlayerList = new ArrayList<PlayerIdentification>();
	
	
	public GameLobby(Player currentPlayer, String gameLobbyName) throws ValidationException
	{
		info = PersistentInformation.getInstance();
		
		MyAssert.NotNull(currentPlayer, "Spieler muss gesetzt sein");
		gameLobbyAdmin = (PlayerIdentification)currentPlayer;
		gameLobbyAdmin.setStatus(true);
		this.gameLobbyName = gameLobbyName;
//		gamePlayerList.add((PlayerIdentification)currentPlayer);
		info.getInactivePlayerList().remove(currentPlayer);
		gameLobbyID = UUID.randomUUID().toString();
	}
	
	public GameLobby()
	{
		
	}
	
	public void joinGameLobby(Player spieler) throws MyExceptions
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

	public String getGameLobbyPassword()
	{
		return gameLobbyPassword;
	}

	public void setGameLobbyPassword(String gameLobbyPassword)
	{
		this.gameLobbyPassword = gameLobbyPassword;
	}

	public int getPlayerLimit()
	{
		return playerLimit;
	}

	public void setPlayerLimit(int playerLimit)
	{
		this.playerLimit = playerLimit;
	}
	
	
	
	
	
	
	
	
	
	
	

}
