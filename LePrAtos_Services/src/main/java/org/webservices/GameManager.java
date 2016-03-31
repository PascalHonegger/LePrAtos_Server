package org.webservices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.javaclasses.GameLobby;
import org.javaclasses.MyExceptions;
import org.javaclasses.Player;

@WebService
public interface GameManager
{
	@WebMethod
	public Player registration(@WebParam(name="email") String email, @WebParam(name="username") String username, @WebParam(name="password") String password) throws MyExceptions;
	
	@WebMethod
	public boolean username_availability(@WebParam(name="username") String username);
	
	@WebMethod
	public boolean email_verification(@WebParam (name="email") String email);
	
	@WebMethod
	public Player login(@WebParam(name="username_email") String username, @WebParam(name="password") String password) throws MyExceptions;
	
	@WebMethod
	public void logout(@WebParam(name="playerID") String playerID);
	
	@WebMethod
	public GameLobby createGameLobby(@WebParam(name = "playerID") String playerID, @WebParam(name="gameLobbyName") String gameLobbyName) throws Exception;
	
	@WebMethod
	public void setGameLobbyName(@WebParam(name = "playerID") String playerID, @WebParam(name = "GameLobbyID") String GameLobbyID, @WebParam(name = "gameLobbyName") String gameLobbyName) throws MyExceptions;
	
	@WebMethod
	public void setGameLobbyPassword(@WebParam(name = "playerID") String playerID, @WebParam(name = "GameLobbyID") String GameLobbyID, @WebParam(name = "gameLobbyPassword") String gameLobbyPassword) throws MyExceptions;
	
	@WebMethod
	public void resetGameLobbyPassword(@WebParam(name = "playerID") String playerID, @WebParam(name = "GameLobbyID") String GameLobbyID) throws MyExceptions;
	
	@WebMethod
	public void setPlayerLimit(@WebParam(name = "playerID") String playerID, @WebParam(name = "GameLobbyID") String GameLobbyID, @WebParam(name = "playerLimit") int playerLimit) throws MyExceptions;
	
	@WebMethod
	public void kickPlayer(@WebParam(name = "playerID") String playerID, @WebParam(name = "GameLobbyID") String GameLobbyID, @WebParam(name = "username") String username);
	
	@WebMethod
	public GameLobby joinGameLobby(@WebParam(name = "playerID") String playerID, @WebParam(name = "GameLobbyID") String GameLobbyID, @WebParam(name = "gameLobbyPassword") String gameLobbyPassword) throws Exception;
	
	@WebMethod
	public void leaveGameLobby(@WebParam(name="playerID") String playerID, @WebParam(name="GameLobbyID") String GameLobbyID);
	
	@WebMethod
	public GameLobby getGameLobby(@WebParam(name="GameLobbyID") String GameLobbyID);
	
	@WebMethod
	public List<GameLobby> getGameLobbies();
	
	@WebMethod
	public Player getPlayerByID(@WebParam(name="playerID") String playerID);
	
	@WebMethod
	public void setPlayerStatus(@WebParam(name="playerID") String playerID, @WebParam(name="status") boolean status);
}
