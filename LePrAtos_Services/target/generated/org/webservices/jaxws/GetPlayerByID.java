
package org.webservices.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getPlayerByID", namespace = "http://webservices.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPlayerByID", namespace = "http://webservices.org/")
public class GetPlayerByID {

    @XmlElement(name = "playerID", namespace = "")
    private String playerID;

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

}
