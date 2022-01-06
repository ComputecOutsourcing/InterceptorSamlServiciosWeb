package co.computec.interceptorsaml.servicios.web.exception;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Bean para el manejo de las excepciones
 * @author jvelandia
 * @FechaCreacion 05/09/2013
 * @FechaUltimaModificacion 05/09/2013
 */
@XmlRootElement(name = "FaultBeanSaml", namespace = "http://sts.computec.experian.co/")
public class FaultBeanSaml implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5859912221270600794L;

	/**
     * detalle de la falta
     */
    private String detail;   
    
    /**
     * codigo de la falta
     */
    private String faultcode;   
  
    /**
     * String de la falta
     */
    private String faultstring;   
       
    
    public String getDetail() {   
        return detail;   
    }   
    public void setDetail(String detail) {   
        this.detail = detail;   
    }   
    public String getFaultcode() {   
        return faultcode;   
    }   
    public void setFaultcode(String faultcode) {   
        this.faultcode = faultcode;   
    }   
    public String getFaultstring() {   
        return faultstring;   
    }   
    public void setFaultstring(String faultstring) {   
        this.faultstring = faultstring;   
    }
    
}
