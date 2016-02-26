
package org.webservices.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.javaclasses.Player;

@XmlRootElement(name = "getPlayerByIDResponse", namespace = "http://webservices.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPlayerByIDResponse", namespace = "http://webservices.org/")
public class GetPlayerByIDResponse {

    @XmlElement(name = "return", namespace = "")
    private Player _return;

    /**
     * 
     * @return
     *     returns Player
     */
    public Player getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(Player _return) {
        this._return = _return;
    }

}
