
package org.webservices.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "createGameLobby", namespace = "http://webservices.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createGameLobby", namespace = "http://webservices.org/", propOrder = {
    "playerID",
    "gameLobbyName"
})
public class CreateGameLobby {

    @XmlElement(name = "playerID", namespace = "")
    private String playerID;
    @XmlElement(name = "gameLobbyName", namespace = "")
    private String gameLobbyName;

    /**
     * 
     * @return
     *     returns String
     */
    public String getPlayerID() {
        return this.playerID;
    }

    /**
     * 
     * @param playerID
     *     the value for the playerID property
     */
    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getGameLobbyName() {
        return this.gameLobbyName;
    }

    /**
     * 
     * @param gameLobbyName
     *     the value for the gameLobbyName property
     */
    public void setGameLobbyName(String gameLobbyName) {
        this.gameLobbyName = gameLobbyName;
    }

}
