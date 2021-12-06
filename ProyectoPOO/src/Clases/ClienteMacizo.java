
package Clases;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Irving Geyler Cupul Uc.
 * @author Joar Honorio Ruiz Peraza.
 * @author Didier Andrey Tec Ezquivel.
 */

/**
 * Clase de los clientes registrados, tiene como propiedad esl tener un Martipunto que aumenta su valor y 
 * una ArrayLsis de sus compras.
 */
public class ClienteMacizo extends Persona implements Serializable{
    //atributos
    private MartiPunto punto;
    private ArrayList<Compra> carritosCompras;

    /**
     * Constructor de ClienteMacizo que cuando se crea un cliente por defecto
     * tiene un martipunto con un valor inicial 0 y se crea una arrayList
     * guardar ahi sus compras.
     *
     * @param Nombre
     * @param ApellidoPaterno
     * @param NumeroTelefonico
     * @param IDE
     * @param Calle
     * @param NumeroCasa
     * @param Colonia
     * @param CP
     * @param Ciudad
     * @param Estado
     */
    public ClienteMacizo(String Nombre, String ApellidoPaterno, Integer NumeroTelefonico, String IDE, String Calle, Integer NumeroCasa, String Colonia, Integer CP, String Ciudad, String Estado) {
        super(Nombre, ApellidoPaterno, NumeroTelefonico, IDE, Calle, NumeroCasa, Colonia, CP, Ciudad, Estado);
        this.punto = new MartiPunto();
        carritosCompras = new ArrayList<>();
    }

    /**
     * Metodo para recuperar el MartiPunto del cliente
     *
     * @return Retorna el MartiPunto del Cliente para que se le realicen las
     * operaciones aumentar o disminuir el valor que tenga.
     */
    public MartiPunto getPunto() {
        return punto;
    }

    /**
     * Metodo para agregar las compras que haya realizado el cliente a su
     * ArryList de compras.
     *
     * @param carritoRealizado Es necesario que se recojan solo compras
     * realizadas.
     */
    public void setCompras(Compra carritoRealizado) {
        carritosCompras.add(0, carritoRealizado);
    }

    /**
     * toString para proporcionar los datosmas importantes del cliente.
     *
     * @return Retorna en String los datos del cliente.
     */
    @Override
    public String toString() {
        return "Nombre: " + getNombre() + " Apellido paterno: " + getApellidoPaterno() + " Num.Telefonico: " + getNumeroTelefonico()
                + " IDE: " + getIDE() + " MartiPunto: " + getPunto().getValor();
    }

}
