
package org.webservices.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "joinGameLobby", namespace = "http://webservices.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "joinGameLobby", namespace = "http://webservices.org/", propOrder = {
    "playerID",
    "gameLobbyID",
    "gameLobbyPassword"
})
public class JoinGameLobby {

    @XmlElement(name = "playerID", namespace = "")
    private String playerID;
    @XmlElement(name = "GameLobbyID", namespace = "")
    private String gameLobbyID;
    @XmlElement(name = "gameLobbyPassword", namespace = "")
    private String gameLobbyPassword;

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
    public String getGameLobbyID() {
        return this.gameLobbyID;
    }

    /**
     * 
     * @param gameLobbyID
     *     the value for the gameLobbyID property
     */
    public void setGameLobbyID(String gameLobbyID) {
        this.gameLobbyID = gameLobbyID;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getGameLobbyPassword() {
        return this.gameLobbyPassword;
    }

    /**
     * 
     * @param gameLobbyPassword
     *     the value for the gameLobbyPassword property
     */
    public void setGameLobbyPassword(String gameLobbyPassword) {
        this.gameLobbyPassword = gameLobbyPassword;
    }

}
