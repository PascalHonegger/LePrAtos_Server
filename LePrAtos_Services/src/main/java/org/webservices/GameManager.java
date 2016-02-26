package org.webservices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.javaclasses.GameLobby;
import org.javaclasses.Player;

@WebService
public interface GameManager
{

	@WebMethod
	public Player login(@WebParam(name="username") String username);
	
	@WebMethod
	public String logout(@WebParam(name="playerID") String playerID);
	
	@WebMethod
	public GameLobby createGameLobby(@WebParam(name = "playerID") String playerID, @WebParam(name="gameLobbyName") String gameLobbyName) throws Exception;
	
	@WebMethod
	public GameLobby joinGameLobby(@WebParam(name="playerID") String playerID, @WebParam(name="GameLobbyID") String GameLobbyID) throws Exception;
	
	@WebMethod
	public void leaveGameLobby(@WebParam(name="playerID") String playerID, @WebParam(name="GameLobbyID") String GameLobbyID);
	
	@WebMethod
	public GameLobby getGameLobby(@WebParam(name="GameLobbyID") String GameLobbyID);
	
	@WebMethod
	public List getGameLobbies();
	
	@WebMethod
	public Player getPlayerByID(@WebParam(name="playerID") String playerID);
}
