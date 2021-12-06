
package ControladorClases;

import Clases.ClienteMacizo;
import Clases.Compra;
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
 * Clase controladora de los cliente macizos el cual crea la arrayList de los clientes 
 * los guarda, los elimina y modifica, de la misma forma que al momento de realizar un cambio
 * en la lista se actualiza el archivo que almacena la clase.
 */
public class ControladorClienteMacizo implements Serializable{
    //atributo
    private ArrayList<ClienteMacizo> listMacizos;

    /**
     * Constructor para la creacion del controlador del clientes y la creacion
     * de la lista que contendra los clientes.
     */
    public ControladorClienteMacizo() {
        this.listMacizos = new ArrayList<>();
    }

    /**
     * Metodo para el agregar clientes a la lista y que sera actualizado el
     * archivo con el Controlador de clientes macizo.
     *
     * @param nuevoCliente Este parametro son los clientes nuevos que seran
     * agregados.
     */
    public void AgregarCliente(ClienteMacizo nuevoCliente) {
        this.listMacizos.add(nuevoCliente);
        ManejoArchivos.GuardarArchivoCliente(this);
    }

    /**
     * Metodo para modificar los datos de los clientes y sea actualizado los
     * nuevos cambios en el archivo que guarda el controlador.
     *
     * @param ClienteModif El parametro es un tipo cliente pero sus datos ya
     * fueron modificados por lo que el que se encuestra en la lista debe ser
     * arreglado con esos nuevos datos.
     */
    public void ModificarCliente(ClienteMacizo ClienteModif) {
        String IDE = ClienteModif.getIDE();

        for (int posicion = 0; posicion < listMacizos.size(); posicion++) {
            if (IDE.equals(listMacizos.get(posicion).getIDE())) {
                listMacizos.get(posicion).setNombre(ClienteModif.getNombre());
                listMacizos.get(posicion).setApellidoPaterno(ClienteModif.getApellidoPaterno());
                listMacizos.get(posicion).setNumeroTelefonico(ClienteModif.getNumeroTelefonico());
                listMacizos.get(posicion).setCalle(ClienteModif.getCalle());
                listMacizos.get(posicion).setNumeroCasa(ClienteModif.getNumeroCasa());
                listMacizos.get(posicion).setColonia(ClienteModif.getColonia());
                listMacizos.get(posicion).setCP(ClienteModif.getCP());
                listMacizos.get(posicion).setCiudad(ClienteModif.getCiudad());
                listMacizos.get(posicion).setEstado(ClienteModif.getEstado());
                ManejoArchivos.GuardarArchivoCliente(this);
                break;
            }

        }
    }

    /**
     * Metodo para eliminar a un cliente ya identificado de la lista, para luego
     * volver a actuliza el archivo que almacena el controlador.
     *
     * @param eliminaCliente Este el cliente que se desea eliminar.
     */
    public void EliminarCliente(ClienteMacizo eliminaCliente) {
        this.listMacizos.remove(eliminaCliente);
        ManejoArchivos.GuardarArchivoCliente(this);
    }

    /**
     * Metodo para buscar la busqueda de un cliente en la lista.
     *
     * @param IDE La bsuqueda del cliente es por medio del IDE por lo que este
     * es el parametro necesario.
     * @return Siendo lo retornado el cliente que deseo encontrar, pero este
     * podria existir o ser nulo.
     */
    public ClienteMacizo BusquedaClienteMacizo(String IDE) {
        for (int posicion = 0; posicion < listMacizos.size(); posicion++) {
            if (IDE.equals(listMacizos.get(posicion).getIDE())) {

                return listMacizos.get(posicion);
            }
        }
        return null;
    }

    /**
     * Metodo get para recuperar la arrayList de los clientes creados.
     *
     * @return Sera retornado la ArrayList de los clientes.
     */
    public ArrayList<ClienteMacizo> getListMacizos() {
        return listMacizos;
    }

    /**
     * Metodo para modificar el cliente que ha realizado su compra.
     *
     * @param comprador Este es el cliente que ha realizado la compra.
     * @param carrito Esta es la compra que fue hecha por el cliente y sera
     * agregado a su lista de compras hechas.
     */
    public void ModificarClienteComprador(ClienteMacizo comprador, Compra carrito) {
        String IDE = comprador.getIDE();

        for (int posicion = 0; posicion < listMacizos.size(); posicion++) {
            if (IDE.equals(listMacizos.get(posicion).getIDE())) {
                listMacizos.get(posicion).setCompras(carrito);
                ManejoArchivos.GuardarArchivoCliente(this);
                break;
            }

        }
    }
}
