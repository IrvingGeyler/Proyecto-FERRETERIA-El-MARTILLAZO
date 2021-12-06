
package Clases;

import java.io.Serializable;

/**
 *
 * @author Irving Geyler Cupul Uc
 * @author Joar Honorio Ruiz Peraza
 * @author Didier Andrey Tec Ezquivel
 */

/**
 * Clase para definir los producto que pueden se
 * 
 */
public class Producto implements Serializable{
    //atributos
    private String NombreProducto;
    private String IDEProducto;
    private float PrecioPublico;
    private String Marca;
    private String Modelo;
    private int Unidades;

    /**
     * Constructor para la creacion del nuevo producto.
     *
     * @param NombreProducto 
     * @param IDEProducto
     * @param PrecioPublico
     * @param Marca
     * @param Modelo
     * @param Unidades
     */
    public Producto(String NombreProducto, String IDEProducto, float PrecioPublico, String Marca, String Modelo, int Unidades) {
        this.NombreProducto = NombreProducto;
        this.IDEProducto = IDEProducto;
        this.PrecioPublico = PrecioPublico;
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.Unidades = Unidades;
    }

    /**
     * Construtor que sera necesario para la creacion de un producto que sera
     * comprado.
     *
     * @param IDE
     * @param Unidades
     */
    public Producto(String IDE, int Unidades) {
        this.NombreProducto = "";
        this.IDEProducto = IDE;
        this.PrecioPublico = 0;
        this.Marca = "";
        this.Modelo = "";
        this.Unidades = Unidades;
    }

    //setter y getters
    //Nombre producto
    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    //IDEProducto
    public void setIDEProducto(String IDEProducto) {
        this.IDEProducto = IDEProducto;
    }

    public String getIDEProducto() {
        return IDEProducto;
    }

    //PrecioPublico
    public void setPrecioPublico(float PrecioPublico) {
        this.PrecioPublico = PrecioPublico;
    }

    public float getPrecioPublico() {
        return PrecioPublico;
    }

    //Marca
    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getMarca() {
        return Marca;
    }

    //Modelo
    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getModelo() {
        return Modelo;
    }

    //Unidades
    public void setUnidades(int Unidades) {
        this.Unidades = Unidades;
    }

    public int getUnidades() {
        return Unidades;
    }

    /**
     * Metodo para ver si la cantidad de unidades que desee comprar de este
     * producto son posibles.
     *
     * @param Comprado El parametro es un ProductoComprado con una cantidad que
     * de productos que quiere.
     * @return Retornara la cantidad si es haci de los productos que serian
     * restantes para el producto o si no son suficinetes retornara las unidades
     * restantes sin modificar.
     */
    public int Comprobar_Ventas_Unidades(ProductoComprado Comprado) {
        int restantes = getUnidades(), unidadesCompradas = Comprado.getUnidades();

        if (restantes >= unidadesCompradas) {
            //resta de las unidades solicitadas
            restantes = restantes - unidadesCompradas;
            return restantes;
        } else {
            return restantes;
        }
    }

    /**
     * Metodo para calcular el costo parcial con respecto a las unidades que se
     * desea comprar.
     *
     * @param Comprados El parametro indispensable es la cantidad unidades que
     * deseas comprar.
     * @return Retornara el costo parcial.
     */
    public float Costo_Parcial(int Comprados) {
        float costoParcial = 0;
        costoParcial = Comprados * getPrecioPublico();

        return costoParcial;
    }

    /**
     * Metodo para dar los datos del producto al del producto comprado para que
     * este logre tener la forma de un producto, salvo el costo publico.
     *
     * @param Comprado Como parametro necesitamos un ProductoComprado para que
     * este reciba los datos
     * @return Retornara al ProductoComprado ya con los datos del producto.
     */
    public ProductoComprado Dar_Forma_Producto(ProductoComprado Comprado) {
        Comprado.setNombreProducto(getNombreProducto());
        Comprado.setMarca(getMarca());
        Comprado.setModelo(getModelo());
        return Comprado;
    }

    /**
     * Metodo toString para mandar los daros del producto
     *
     * @return Retornara los datos en un String.
     */
    @Override
    public String toString() {
        return "IDE: " + getIDEProducto() + " Nombre: " + getNombreProducto() + " Marca: " + getMarca() + " Modelo: " + getModelo()
                + " Precio publico: $" + getPrecioPublico() + " Unidades: " + getUnidades();
    }

    
}
