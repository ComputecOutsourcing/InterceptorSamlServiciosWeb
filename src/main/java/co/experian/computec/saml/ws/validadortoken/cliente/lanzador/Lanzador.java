package co.experian.computec.saml.ws.validadortoken.cliente.lanzador;

import java.net.URL;

import javax.xml.namespace.QName;

import co.experian.computec.saml.ws.validadortoken.cliente.WsValidadorTokenSamlService;

public class Lanzador{

    public static void main(String[] args){
        try{
            URL url= new URL("http://127.0.0.1:8080/WsValidationTokenSAML-1.0/WsValidadorTokenSamlService?wsdl");
            WsValidadorTokenSamlService service= new WsValidadorTokenSamlService(url,new QName("http://co.experian.computec.saml.ws.validadortoken/","WsValidadorTokenSamlService"));
            Boolean valido=service.getWsValidadorTokenSamlPort().validateToken("rO0ABXQAGmZkc2p0cTlxajFyY3RtM2xkMW45YmgwbjRp");
            System.out.println("Token validio: " +valido);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
