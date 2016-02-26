
package org.webservices.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.javaclasses.GameLobby;

@XmlRootElement(name = "getGameLobbiesResponse", namespace = "http://webservices.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getGameLobbiesResponse", namespace = "http://webservices.org/")
public class GetGameLobbiesResponse {

    @XmlElement(name = "return", namespace = "")
    private List<GameLobby> _return;

    /**
     * 
     * @return
     *     returns List<GameLobby>
     */
    public List<GameLobby> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<GameLobby> _return) {
        this._return = _return;
    }

}
