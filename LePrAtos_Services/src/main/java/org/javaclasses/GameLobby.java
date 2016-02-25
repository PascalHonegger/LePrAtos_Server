package org.javaclasses;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.ValidationException;

public class GameLobby
{
	private String gameLobbyID;
	private String gameLobbyAdmin;
	private ArrayList<PlayerIdentification> gamePlayerList = new ArrayList<PlayerIdentification>();
	private ArrayList<String> gamePlayerListPublic = new ArrayList<String>();
	
	
	public GameLobby(Player currentPlayer) throws ValidationException
	{
		MyAssert.NotNull(currentPlayer, "Spieler muss gesetzt sein");
		gameLobbyAdmin = currentPlayer.getUsername();
		gamePlayerList.add((PlayerIdentification)currentPlayer);
		gamePlayerListPublic.add(currentPlayer.getUsername());
		gameLobbyID = UUID.randomUUID().toString();
	}
	
	public GameLobby()
	{
		
	}
	
	public void joinGameLobby(Player spieler)
	{
		gamePlayerList.add((PlayerIdentification)spieler);
		gamePlayerListPublic.add(spieler.getUsername());
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
	
	
	
	
	

}
