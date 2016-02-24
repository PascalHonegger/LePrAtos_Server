package org.javaclasses;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.ValidationException;

public class GameLobby
{
	private String gameLobbyID;
	private String gameLobbyAdminID;
	private ArrayList<PlayerIdentification> gamePlayerList = new ArrayList<PlayerIdentification>();
	
	
	public GameLobby(Player currentPlayer) throws ValidationException
	{
		MyAssert.NotNull(currentPlayer, "Spieler muss gesetzt sein");
		gameLobbyAdminID = currentPlayer.getPlayerID();
		gamePlayerList.add(currentPlayer);
		gameLobbyID = UUID.randomUUID().toString();
	}
	
	public GameLobby()
	{
		
	}
	
	public void joinGameLobby(Player spieler)
	{
		gamePlayerList.add(spieler);
	}

	public String getGameLobbyID()
	{
		return gameLobbyID;
	}

	public void setGameLobbyID(String gameLobbyID)
	{
		this.gameLobbyID = gameLobbyID;
	}
	
	

}
