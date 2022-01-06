package co.computec.interceptorsaml.servicios.web.exception;

import javax.xml.ws.WebFault;

@WebFault(name="WSSystemException",faultBean="co.computec.interceptorsaml.servicios.web.exception.FaultBeanSaml")
public class WsInterceptorSystemException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1077703794005277784L;
	
	private FaultBeanSaml faultBean=null;

    /**
     * Instancia un nuevo system exception.
     */
    public WsInterceptorSystemException(){
        super();
    }

    /**
     * Instancia un nuevo system exception.
     * 
     * @param pErrorCode un error code
     * @param pThr un thr
     */
    public WsInterceptorSystemException(long pErrorCode,Throwable pThr){
        super(""+pErrorCode,pThr);
    }

    /**
     * Instancia un nuevo system exception.
     * 
     * @param pErrorCode un error code
     */
    public WsInterceptorSystemException(long pErrorCode){
        super(""+pErrorCode);
    }

    /**
     * Instancia un nuevo system exception.
     * 
     * @param pMsg un msg
     * @param pThr un thr
     */
    public WsInterceptorSystemException(String pMsg,Throwable pThr){
        super(pMsg,pThr);
    }

    /**
     * Instancia un nuevo system exception.
     * 
     * @param pMsg un msg
     */
    public WsInterceptorSystemException(String pMsg){
        super(pMsg);
    }

    /**
     * Instancia un nuevo system exception.
     * 
     * @param pThr un thr
     */
    public WsInterceptorSystemException(Throwable pThr){
        super(pThr);
    }

    public WsInterceptorSystemException(String message,FaultBeanSaml faultBean,Throwable cause){
        super(message,cause);
        this.faultBean=faultBean;
    }

    public WsInterceptorSystemException(String message,FaultBeanSaml faultBean){
        super(message);
        this.faultBean=faultBean;
    }

    public FaultBeanSaml getFaultInfo(){
        return faultBean;
    }

}
