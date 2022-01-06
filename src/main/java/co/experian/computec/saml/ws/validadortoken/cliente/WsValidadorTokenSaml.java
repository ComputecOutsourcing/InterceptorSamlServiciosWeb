
package co.experian.computec.saml.ws.validadortoken.cliente;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390- Generated source version: 2.0
 * 
 */
@WebService(name="WsValidadorTokenSaml",targetNamespace="http://co.experian.computec.saml.ws.validadortoken/")
public interface WsValidadorTokenSaml{

    /**
     * 
     * @param token
     * @return returns java.lang.Boolean
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(name="tokenValido",targetNamespace="")
    @RequestWrapper(localName="validateToken",targetNamespace="http://co.experian.computec.saml.ws.validadortoken/",className="co.experian.computec.saml.ws.validadortoken.cliente.ValidateToken")
    @ResponseWrapper(localName="validateTokenResponse",targetNamespace="http://co.experian.computec.saml.ws.validadortoken/",className="co.experian.computec.saml.ws.validadortoken.cliente.ValidateTokenResponse")
    public Boolean validateToken(@WebParam(name="token",targetNamespace="") String token) throws Exception_Exception;

}
