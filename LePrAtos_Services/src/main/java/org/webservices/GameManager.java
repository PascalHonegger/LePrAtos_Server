package org.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.javaclasses.Player;

@WebService
public interface GameManager
{

	@WebMethod
	public Player login(String username);

}
