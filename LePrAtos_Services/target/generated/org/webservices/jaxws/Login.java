
package org.webservices.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "login", namespace = "http://webservices.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "login", namespace = "http://webservices.org/", propOrder = {
    "usernameEmail",
    "password"
})
public class Login {

    @XmlElement(name = "username_email", namespace = "")
    private String usernameEmail;
    @XmlElement(name = "password", namespace = "")
    private String password;

    /**
     * 
     * @return
     *     returns String
     */
    public String getUsernameEmail() {
        return this.usernameEmail;
    }

    /**
     * 
     * @param usernameEmail
     *     the value for the usernameEmail property
     */
    public void setUsernameEmail(String usernameEmail) {
        this.usernameEmail = usernameEmail;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 
     * @param password
     *     the value for the password property
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
