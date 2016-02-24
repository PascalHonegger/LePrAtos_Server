package org.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.ValidationException;

import org.javaclasses.GameLobby;
import org.javaclasses.Player;

@WebService
public interface GameManager
{

	@WebMethod
	public Player login(String username);
	
	@WebMethod
	public String logout();
	
	@WebMethod
	public GameLobby createGameLobby(@WebParam(name = "playerID") String playerID) throws Exception;
	
	@WebMethod
	public GameLobby joinGameLobby(Player spieler, String GameLobbyID) throws Exception;
	
	@WebMethod
	public GameLobby getGameLobby(String GameLobbyID);
}
