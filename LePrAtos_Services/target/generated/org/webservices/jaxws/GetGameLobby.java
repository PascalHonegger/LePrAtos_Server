
package org.webservices.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getGameLobby", namespace = "http://webservices.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getGameLobby", namespace = "http://webservices.org/")
public class GetGameLobby {

    @XmlElement(name = "GameLobbyID", namespace = "")
    private String gameLobbyID;

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

}
