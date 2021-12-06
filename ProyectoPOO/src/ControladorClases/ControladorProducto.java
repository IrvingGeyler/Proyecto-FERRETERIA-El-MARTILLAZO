package ControladorClases;

import Clases.Producto;
import ControladorArchivos.ManejoArchivos;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Irving Geyler Cupul Uc
 * @author Joar Honorio Ruiz Peraza
 * @author Didier Andrey Tec Ezquivel
 */

/**
 *
 * Clase controladora de los productos, donde es posible registrarlos,
 * eliminarlos o modificarlos, al igual de que esta clase es guardada con
 * la lista de producto en un archivo donde es posible actualizar cambios en 
 * esta clase.
 */
public class ControladorProducto implements Serializable {
    
    private ArrayList<Producto> listProductos;

    /**
     * Constructor que creara la lista de los productos.
     */
    public ControladorProducto() {
        this.listProductos = new ArrayList<>();
    }

    /**
     * Metodo para el agregado de los productos a la lista y es actulizado de
     * igual forma la clase en el archivo.
     *
     * @param nuevoProducto Este es el producto que sera agregado
     */
    public void AgregarProducto(Producto nuevoProducto) {
        this.listProductos.add(nuevoProducto);
        ManejoArchivos.GuardarArchivoProducto(this);
    }

    /**
     * Metodo para modificar los datos de los productos y guardar los cambios de
     * esta clase en el archivo.
     *
     * @param ProductoModif El parametro necesario es el producto modificado y
     * que temdra los cambios que seran agregados al producto que esta en la
     * lista y luego esta clase de nuevo es actualizada en el archivo
     */
    public void ModificarProducto(Producto ProductoModif) {
        String IDE = ProductoModif.getIDEProducto();

        for (int i = 0; i < listProductos.size(); i++) {
            if (IDE.equals(listProductos.get(i).getIDEProducto())) {
                listProductos.get(i).setNombreProducto(ProductoModif.getNombreProducto());
                listProductos.get(i).setMarca(ProductoModif.getMarca());
                listProductos.get(i).setModelo(ProductoModif.getModelo());
                listProductos.get(i).setPrecioPublico(ProductoModif.getPrecioPublico());
                listProductos.get(i).setUnidades(ProductoModif.getUnidades());
                ManejoArchivos.GuardarArchivoProducto(this);
                break;
            }

        }
    }

    /**
     * Metodo para eliminar los datos de los clientes y guardarlos los cambios
     * en el archivo
     *
     * @param eliminaProducto El parametro necesario es tener al producto que
     * sera eliminado.
     */
    public void EliminarProducto(Producto eliminaProducto) {
        this.listProductos.remove(eliminaProducto);
        ManejoArchivos.GuardarArchivoProducto(this);
    }

    /**
     * Metodo para recuperar la lista de los productos ya registrados.
     *
     * @return ArrayList de los productos
     */
    public ArrayList<Producto> getListProductos() {
        return listProductos;
    }

    
    /**
     * Modificar la cantidad de unidades de el producto que esta en la lista con
     * el que se modifico en la venta y finalizando actualizando el archivo con
     * las modificaciones hechas.
     *
     * @param ProducModif Es neceario una array con los productos modificados
     * durante la compra.
     */
    public void Modificar_ProductoActualVenta(ArrayList<Producto> ProducModif) {
        for (int posi = 0; posi < listProductos.size(); posi++) {

            if (ProducModif.get(posi).getIDEProducto().equals(listProductos.get(posi).getIDEProducto())) {

                listProductos.get(posi).setUnidades(ProducModif.get(posi).getUnidades());
            }
        }
        ManejoArchivos.GuardarArchivoProducto(this);
    }

    /**
     * Metodo para la busqueda de un producto en la lista.
     *
     * @param IDE Es necesario para la busqueda el IDE como parametro.
     * @return Lo que se retornara sera el producto ya sea uno que se encontro
     * en la lista o nulo.
     */
    public Producto BusquedaProducto(String IDE) {
        for (int posicion = 0; posicion < listProductos.size(); posicion++) {
            if (IDE.equals(listProductos.get(posicion).getIDEProducto())) {
                return listProductos.get(posicion);
            }
        }
        return null;
    }
    
}
