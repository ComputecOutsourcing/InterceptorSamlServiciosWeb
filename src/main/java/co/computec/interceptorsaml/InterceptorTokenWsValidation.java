package co.computec.interceptorsaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Priority;
import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

import co.computec.interceptorsaml.servicios.web.exception.FaultBeanSaml;
import co.computec.interceptorsaml.servicios.web.exception.WsInterceptorBusinessRuleException;
import co.computec.interceptorsaml.servicios.web.exception.WsInterceptorSystemException;
import co.experian.computec.saml.ws.validadortoken.cliente.WsValidadorTokenSamlService;


/**
 * Clase que se encarga de interceptar las peticiones de los web services y validar si hay un token y validarlo 
 * @author jvelandia
 * @version 1.0
 * @FechaCreacion: 30/09/2019
 * @FechaUltimaModificacion: 01/10/2019 
 */
@Interceptor
@MyInterceptorBindingSAML
@Priority(value=1) 
public class InterceptorTokenWsValidation implements SOAPHandler<SOAPMessageContext>{
    

    private static final Logger LOG=Logger.getLogger(InterceptorTokenWsValidation.class);
    
    @Resource
    private UserTransaction userTransaction;
    
    @Resource
    private WebServiceContext wsContext;
    
    
    private static final String TOKEN="token";
    
    
    private static final String ACCESO_NO_AUTORIZADO="Acceso no autorizado";
    
    /**
     * Path de Recursos en Linux
     */
    private static final String RECURSOS_PROPERTIES = "/Datos/RecursosSAML/wsrecursos.properties";
    
    /**
     * Path de Recursos en windows
     */
    private static final String RECURSOS_PROPERTIES_WIN = "c:/Datos/RecursosSAML/wsrecursos.properties";

    
    /***
     * 
     * @param ctx
     * @return
     * @throws WsInterceptorSystemException
     * @throws WsInterceptorBusinessRuleException
     */
    @AroundInvoke
    public Object verifyAccess(InvocationContext ctx) throws WsInterceptorSystemException, WsInterceptorBusinessRuleException{
        Object result=null;
        InputStream stream = null;
        try{
            userTransaction.begin();
            LOG.debug("Metodo invocado: "+ctx.getMethod().getName());
            String token=obtenerToken();
            if(token==null||token.trim().equalsIgnoreCase("")){
                final MessageContext mc=wsContext.getMessageContext();
                final HttpServletResponse response=(HttpServletResponse)mc.get(MessageContext.SERVLET_RESPONSE);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                throw new WsInterceptorSystemException(ACCESO_NO_AUTORIZADO);
            }else{
                
                Properties props = new Properties();
                String osName = System.getProperty("os.name").toLowerCase();
                
                if(isWindows(osName)){
                    stream = new FileInputStream(new File(RECURSOS_PROPERTIES_WIN));
                    LOG.debug("Sistema Operativo windows");
                } else {
                    stream = new FileInputStream(new File(RECURSOS_PROPERTIES));
                    LOG.debug("Sistema Operativo Diferente a windows");
                }
                props.load(stream);
                
                String urlwsdlValidacionToken = props.getProperty("urlwsdValidacionTokenSAML");
                LOG.debug("URL del servicio de validacion de Token: "+urlwsdlValidacionToken);
                URL url= new URL(urlwsdlValidacionToken);
                
                WsValidadorTokenSamlService service= new WsValidadorTokenSamlService(url,new QName("http://co.experian.computec.saml.ws.validadortoken/","WsValidadorTokenSamlService"));
                Boolean valido=service.getWsValidadorTokenSamlPort().validateToken(token);
                
                LOG.debug("Token Valido: "+valido);               
                if(!valido){
                    FaultBeanSaml bean=new FaultBeanSaml();
                    bean.setFaultcode("401");
                    bean.setFaultstring(ACCESO_NO_AUTORIZADO);
                    throw new Exception(ACCESO_NO_AUTORIZADO);
                    //throw new WsInterceptorBusinessRuleException(ACCESO_NO_AUTORIZADO,bean);
                }
                
            }

            userTransaction.commit();
            result=ctx.proceed();
         
        }
        catch (WsInterceptorBusinessRuleException e) {            
            LOG.error("Error de WsInterceptorBusinessRuleException: ",e);
            throw e;
        }
        catch (WsInterceptorSystemException e) {
            LOG.error("Error de WSSystemException: ",e);
            throw e;
        }
        catch(Exception e){
            FaultBeanSaml bean=new FaultBeanSaml();
            bean.setFaultcode("401");
            bean.setFaultstring(ACCESO_NO_AUTORIZADO);
            LOG.error("Error al ejecutar interceptor InterceptorTokenWsValidation: ",e);
            throw new WsInterceptorSystemException(e.getMessage());
        }
        return result;
    }
    
    /**
     * 
     * @return
     * @throws WsInterceptorSystemException
     */
    private String obtenerToken() throws WsInterceptorSystemException{
        try{
            String token=null;
            if(wsContext!=null){
                final MessageContext mc=wsContext.getMessageContext();
                final HttpServletRequest request=(HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST);
                token=request.getHeader(TOKEN);
                LOG.debug("Token Serializado: "+ token);
            }else{
                LOG.warn("El contexto del web services es nulo interceptor");
            }
            return token;
        }
        catch(Exception e){
           LOG.error("Error al sacar token de los headers de la peticion :",e);
           throw new WsInterceptorSystemException("Error al sacar token de los headers de la peticion :",e);
        }
        
    }
    
    /**
     * 
     * @param os
     * @return
     */
    public boolean isWindows(String os) {
        return (os.indexOf("win") >= 0);
    }

    /**
     * 
     * @param os
     * @return
     */
    public boolean isMac(String os) {
        return (os.indexOf("mac") >= 0);
    }

    /**
     * 
     * @param os
     * @return
     */
    public boolean isUnix(String os) {
        return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") >= 0 );
    }

    /**
     * 
     * @param os
     * @return
     */
    public boolean isSolaris(String os) {
        return (os.indexOf("sunos") >= 0);
    }

    
    @Override
    public void close(MessageContext arg0){
        
    }

    @Override
    public boolean handleFault(SOAPMessageContext arg0){
        return true;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext arg0){      
        return true;
    }

    @Override
    public Set<QName> getHeaders(){
        return Collections.emptySet();
    }


}
