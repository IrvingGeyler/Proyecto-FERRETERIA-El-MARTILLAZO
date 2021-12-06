
package Clases;

import java.io.Serializable;

/**
 *
 * @author Irving Geyler Cupul Uc
 * @author Joar Honorio Ruiz Peraza
 * @author Didier Andrey Tec Ezquivel

 */

/**
 * Clase de los Martipuntos los cuales poseen un valor
 * 
 */
public class MartiPunto implements Serializable{
   //atributo
    private int Valor;

    /**
     * constructor dando un valor 0 por defecto cuando se inicialize
     *
     */
    public MartiPunto() {
        this.Valor = 0;
    }

    //setter y getters
    public void setValor(int Valor) {
        this.Valor = Valor;
    }

    public int getValor() {
        return Valor;
    }

    /**
     * Metodo para el aumento del valor del MartiPuntos en la compra.
     *
     * @param CostoCompraTotal
     */
    public void Suma_ValorMartipuntos(float CostoCompraTotal) {
        int ValorMartiPunto =0;
        try {
            rango(CostoCompraTotal);
            ValorMartiPunto =Calculo_Valor(CostoCompraTotal);
            if (ValorMartiPunto !=0) {
                setValor(ValorMartiPunto);
            } else {
                setValor(0);
            }
        } catch (RangoCompraException e) {
            e.mensaje();
        }
        
    }

    /**
     *
     * Metodo para la disminucion del martipunto
     */
    public void Resta_ValorMartiPuntos() {

    }
    
    /**
     * Metodo para comprobar si el costo de la compra esta en el rango o lanzar una excepcion.
     * @param CostoTotal Este es el costo total.
     * @throws RangoCompraException Excepcion del rango.
     */
    public void rango(float CostoTotal) throws RangoCompraException {

        if (CostoTotal < 450) {
            throw new RangoCompraException(); //lanza un Exception 
        }
    }
    
    /**
     * Metodo para calcular el valor del MartiPunto obtenido en la compra.
     * @param CostoTotal este es el costo total de la compra
     * @return El valor de los MartiPuntos Obtenidos.
     */
    public int Calculo_Valor(float CostoTotal){
        int ValorMartiPunto = 0,cocienteEntero =0;
        float cociente =0;
        cociente = CostoTotal / 450;
        
        if (cociente!=0) {
           cocienteEntero = Math.round(cociente);
           ValorMartiPunto = cocienteEntero;
        } 
        return ValorMartiPunto;
    }
    
}
