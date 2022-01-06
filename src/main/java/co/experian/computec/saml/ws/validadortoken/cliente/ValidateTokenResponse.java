
package co.experian.computec.saml.ws.validadortoken.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for validateTokenResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="validateTokenResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tokenValido" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="validateTokenResponse",propOrder={"tokenValido"})
public class ValidateTokenResponse{

    protected Boolean tokenValido;

    /**
     * Gets the value of the tokenValido property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isTokenValido(){
        return tokenValido;
    }

    /**
     * Sets the value of the tokenValido property.
     * 
     * @param value allowed object is {@link Boolean }
     * 
     */
    public void setTokenValido(Boolean value){
        this.tokenValido=value;
    }

}
