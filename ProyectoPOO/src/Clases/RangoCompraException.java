
package Clases;

/**
 *
 * @author Irving Geyler Cupul Uc
 */
public class RangoCompraException extends Exception{
    
    /**
     * Constructor de la Exception.
     */
    public RangoCompraException() {
    }
    
    /**
     * Metodo imprimir un mensaje 
     */
    public void mensaje(){
        System.out.println("No puede aumentar su valor de MartiPuntos.");
    }
    
   
}
