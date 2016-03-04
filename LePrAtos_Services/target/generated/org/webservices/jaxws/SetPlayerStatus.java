
package org.webservices.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "setPlayerStatus", namespace = "http://webservices.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setPlayerStatus", namespace = "http://webservices.org/", propOrder = {
    "playerID",
    "status"
})
public class SetPlayerStatus {

    @XmlElement(name = "playerID", namespace = "")
    private String playerID;
    @XmlElement(name = "status", namespace = "")
    private boolean status;

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
     *     returns boolean
     */
    public boolean isStatus() {
        return this.status;
    }

    /**
     * 
     * @param status
     *     the value for the status property
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

}
