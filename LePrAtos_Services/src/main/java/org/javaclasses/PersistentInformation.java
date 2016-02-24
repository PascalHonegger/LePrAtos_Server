package org.javaclasses;

import java.util.ArrayList;
import java.util.List;

public class PersistentInformation
{

	private static PersistentInformation instance;
	
	public static PersistentInformation getInstance() {
		if (instance == null) {
			instance = new PersistentInformation();
		}
		return instance;
	}
	
	private PersistentInformation()
	{
	}
	
	private List<Player> _activePlayers = new ArrayList<Player>();
	
	public List<Player> getActivePlayerList() {
		return _activePlayers;
	}
	
}
