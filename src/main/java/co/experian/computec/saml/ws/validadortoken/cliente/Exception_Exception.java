
package co.experian.computec.saml.ws.validadortoken.cliente;

import javax.xml.ws.WebFault;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390- Generated source version: 2.0
 * 
 */
@WebFault(name="Exception",targetNamespace="http://co.experian.computec.saml.ws.validadortoken/")
public class Exception_Exception extends java.lang.Exception{

    /**
     * 
     */
    private static final long serialVersionUID=209018754181526668L;
    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private co.experian.computec.saml.ws.validadortoken.cliente.Exception faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public Exception_Exception(String message,co.experian.computec.saml.ws.validadortoken.cliente.Exception faultInfo){
        super(message);
        this.faultInfo=faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public Exception_Exception(String message,co.experian.computec.saml.ws.validadortoken.cliente.Exception faultInfo,Throwable cause){
        super(message,cause);
        this.faultInfo=faultInfo;
    }

    /**
     * 
     * @return returns fault bean: co.experian.computec.saml.ws.validadortoken.cliente.Exception
     */
    public co.experian.computec.saml.ws.validadortoken.cliente.Exception getFaultInfo(){
        return faultInfo;
    }

}
