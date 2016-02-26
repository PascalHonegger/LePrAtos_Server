package org.javaclasses;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.ValidationException;

public class GameLobby
{
	private PersistentInformation info;

	private String gameLobbyID;
	private String gameLobbyName;
	private String gameLobbyAdmin;
	private ArrayList<PlayerIdentification> gamePlayerList = new ArrayList<PlayerIdentification>();
	private ArrayList<String> gamePlayerListPublic = new ArrayList<String>();
	
	
	public GameLobby(Player currentPlayer, String gameLobbyName) throws ValidationException
	{
		info = PersistentInformation.getInstance();
		
		MyAssert.NotNull(currentPlayer, "Spieler muss gesetzt sein");
		gameLobbyAdmin = currentPlayer.getUsername();
		this.gameLobbyName = gameLobbyName;
		gamePlayerList.add((PlayerIdentification)currentPlayer);
		gamePlayerListPublic.add(currentPlayer.getUsername());
		info.getInactivePlayerList().remove(currentPlayer);
		gameLobbyID = UUID.randomUUID().toString();
	}
	
	public GameLobby()
	{
		
	}
	
	public void joinGameLobby(Player spieler)
	{
		gamePlayerList.add((PlayerIdentification)spieler);
		gamePlayerListPublic.add(spieler.getUsername());
		info.getInactivePlayerList().remove(spieler);
	}
	
	public void leaveGameLobby(Player spieler)
	{
		gamePlayerList.remove(spieler);
		gamePlayerListPublic.remove(spieler.getUsername());
		info.getInactivePlayerList().add(spieler);
	}

	public String getGameLobbyID()
	{
		return gameLobbyID;
	}

	public String getGameLobbyAdmin()
	{
		return gameLobbyAdmin;
	}

	public void setGameLobbyAdmin(String gameLobbyAdmin)
	{
		this.gameLobbyAdmin = gameLobbyAdmin;
	}

	public void setGameLobbyID(String gameLobbyID)
	{
		this.gameLobbyID = gameLobbyID;
	}

	public ArrayList<String> getGamePlayerListPublic()
	{
		return gamePlayerListPublic;
	}

	public void setGamePlayerListPublic(ArrayList<String> gamePlayerListPublic)
	{
		this.gamePlayerListPublic = gamePlayerListPublic;
	}

	public String getGameLobbyName()
	{
		return gameLobbyName;
	}

	public void setGameLobbyName(String gameLobbyName)
	{
		this.gameLobbyName = gameLobbyName;
	}
	
	
	
	
	
	
	

}
