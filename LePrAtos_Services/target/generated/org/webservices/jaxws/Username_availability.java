
package org.webservices.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "username_availability", namespace = "http://webservices.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "username_availability", namespace = "http://webservices.org/")
public class Username_availability {

    @XmlElement(name = "username", namespace = "")
    private String username;

    /**
     * 
     * @return
     *     returns String
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * 
     * @param username
     *     the value for the username property
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
