
package org.webservices.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.javaclasses.GameLobby;

@XmlRootElement(name = "joinGameLobbyResponse", namespace = "http://webservices.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "joinGameLobbyResponse", namespace = "http://webservices.org/")
public class JoinGameLobbyResponse {

    @XmlElement(name = "return", namespace = "")
    private GameLobby _return;

    /**
     * 
     * @return
     *     returns GameLobby
     */
    public GameLobby getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(GameLobby _return) {
        this._return = _return;
    }

}
