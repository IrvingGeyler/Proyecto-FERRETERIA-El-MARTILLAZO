
package Clases;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Irving Geyler Cupul Uc
 * @author Joar Honorio Ruiz Peraza
 * @author Didier Andrey Tec Ezquivel
 */

/**
 * Clase de Compra, el cual consta de los productos que son comprados por el cliente, el costo total de 
 * esa compra y un MartiPunto que tendra un valor de lo ganado en la compra.
 */
public class Compra implements Serializable{
    //atributos
    private ArrayList<ProductoComprado> carritoProductos;
    private float TotalCompra;
    private MartiPunto puntosGeneradosCompra;

    /**
     * Constructor de la compra, que al crearse este creara su arrayList de
     * productos comprado, el valor de la compra por defecto 0 y creara el
     * MartiPunto donde se obtendran los que consiguio el cliente en esa compra.
     */
    public Compra() {
        carritoProductos = new ArrayList<>();
        this.TotalCompra = 0;
        puntosGeneradosCompra = new MartiPunto();
    }

    /**
     * Metodo para agregar a la arrayList los productos comprados por el
     * cliente.
     *
     * @param productoCompra Es necesario que sean enviados los productos
     * comprados.
     */
    public void AgregarCarrito(ProductoComprado productoCompra) {
        carritoProductos.add(0, productoCompra);
    }

    /**
     * El uso del setTotalCompra es recibir el costo Total que realizo por la
     * compra.
     *
     * @param TotalCompra Necesario que sea el costo total de la compra un tipo
     * float.
     */
    public void setTotalCompra(float TotalCompra) {
        this.TotalCompra = TotalCompra;
    }

    /**
     * Metodo para tener la posibilidad de recuperar el valor de la compra.
     *
     * @return El valor de la compra en tipo float.
     */
    public float getTotalCompra() {
        return TotalCompra;
    }

    /**
     * Metodo para recoger el valor del MartiPunto que genero el el costo total
     * de la compra.
     *
     * @param ValorMatiCompra Es necesario que sea pasado el valor del
     * MartiPunto generado.
     */
    public void ValorCompra_MartiPunto(int ValorMatiCompra) {
        this.puntosGeneradosCompra.setValor(ValorMatiCompra);
    }

    /**
     * Metodo para recupera el MartiPunto de la compra.
     *
     * @return Recupera el MartiPunto.
     */
    public MartiPunto getPuntosGeneradosCompra() {
        return puntosGeneradosCompra;
    }

    /**
     * Metodo para recuperar los productos que se compraron.
     *
     * @return Retorna la ArrayList de los productos comprados.
     */
    public ArrayList<ProductoComprado> getCarritoProductos() {
        return carritoProductos;
    }
}
