package co.computec.interceptorsaml.servicios.web.exception;

import java.io.Serializable;

import javax.xml.ws.WebFault;

@WebFault(name="WsInterceptorSystemException",faultBean="co.computec.interceptorsaml.servicios.web.exception.FaultBeanSaml")
public class WsInterceptorBusinessRuleException extends Exception implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1421298467937471750L;
	
	/**
     * Bean que define una excepcion 
     */
    private FaultBeanSaml faultBean=null;

    /**
     * Instancia un nuevo system exception.
     */
    public WsInterceptorBusinessRuleException(){
        super();

    }

    /**
     * Instancia un nuevo system exception.
     * 
     * @param pErrorCode un error code
     * @param pThr un thr
     */
    public WsInterceptorBusinessRuleException(long pErrorCode,Throwable pThr){
        super(""+pErrorCode,pThr);
    }

    /**
     * Instancia un nuevo system exception.
     * 
     * @param pErrorCode un error code
     */
    public WsInterceptorBusinessRuleException(long pErrorCode){
        super(""+pErrorCode);
    }

    /**
     * Instancia un nuevo system exception.
     * 
     * @param pMsg un msg
     * @param pThr un thr
     */
    public WsInterceptorBusinessRuleException(String pMsg,Throwable pThr){
        super(pMsg,pThr);
    }

    /**
     * Instancia un nuevo system exception.
     * 
     * @param pMsg un msg
     */
    public WsInterceptorBusinessRuleException(String pMsg){
        super(pMsg);
    }

    /**
     * Instancia un nuevo system exception.
     * 
     * @param pThr un thr
     */
    public WsInterceptorBusinessRuleException(Throwable pThr){
        super(pThr);
    }

    public WsInterceptorBusinessRuleException(String message,FaultBeanSaml faultBean,Throwable cause){
        super(message,cause);
        this.faultBean=faultBean;
    }

    public WsInterceptorBusinessRuleException(String message,FaultBeanSaml faultBean){
        super(message);
        this.faultBean=faultBean;
    }

    public FaultBeanSaml getFaultInfo(){
        return faultBean;
    }

}
