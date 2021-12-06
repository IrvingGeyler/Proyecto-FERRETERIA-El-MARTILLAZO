
package Clases;

import java.io.Serializable;

/**
 * @author Irving Geyler Cupul Uc
 * @author Joar Honorio Ruiz Peraza
 * @author Didier Andrey Tec Ezquivel
 */

/**
 * Clase destinada para los productos que son comprados heredando de los productos,
 * teniendo de diferencia que estos tienen un costo parcial al ser comprado y las unidades
 * de producto que eran existentes ahora son de unidades compradas.
 * 
 */
public class ProductoComprado extends Producto implements Serializable{
    //atributo
    private float CostoParcial;

    /**
     * Constructor del producto comprado que solo tiene dos parametros que son
     * los proporcionados para la busqueda en los productos guardados, igual por
     * defecto tiene costo parcial 0 para ver si se logro si agregacion a la
     * compra.
     *
     * @param IDE
     * @param Unidades
     */
    public ProductoComprado(String IDE, int Unidades) {
        super(IDE, Unidades);
        this.CostoParcial = 0;
    }

    /**
     * Metodo para ingresar el costo parcial ya optendido en la compra de las
     * unidades del producto.
     *
     * @param CostoParcial
     */
    public void setCostoParcial(float CostoParcial) {
        this.CostoParcial = CostoParcial;
    }

    /**
     * Metodo para recuperar el costo parcial de la compra de la unidades del
     * producto.
     *
     * @return El valor retornado sera de tipo float.
     */
    public float getCostoParcial() {
        return CostoParcial;
    }

}
