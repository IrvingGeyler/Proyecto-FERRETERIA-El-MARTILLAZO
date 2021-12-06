
package Clases;

import java.io.Serializable;

/**
 *
 * @author Irving Geyler Cupul Uc
 * @author Joar Honorio Ruiz Peraza
 * @author Didier Andrey Tec Ezquivel
 */

/**
 * Clase abstracta Persona como base con los datos para la clase ClienteMacizo.
 * 
 */
public abstract class Persona implements Serializable{
    //atributos
    private String IDE;
    private String Nombre;
    private String ApellidoPaterno;
    private String Calle;
    private Integer NumeroTelefonico;
    private Integer NumeroCasa;
    private String Colonia;
    private Integer CP;
    private String Ciudad;
    private String Estado;

    /**
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
    public Persona(String Nombre, String ApellidoPaterno, Integer NumeroTelefonico, String IDE, String Calle, Integer NumeroCasa, String Colonia, Integer CP, String Ciudad, String Estado) {
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.NumeroTelefonico = NumeroTelefonico;
        this.IDE = IDE;
        this.Calle = Calle;
        this.NumeroCasa = NumeroCasa;
        this.Colonia = Colonia;
        this.CP = CP;
        this.Ciudad = Ciudad;
        this.Estado = Estado;
    }

    //setter y gettters
    //IDE
    public void setIDE(String IDE) {
        this.IDE = IDE;
    }

    public String getIDE() {
        return IDE;
    }

    //Numero de celular
    public void setNumeroTelefonico(Integer NumeroTelefonico) {
        this.NumeroTelefonico = NumeroTelefonico;
    }

    public Integer getNumeroTelefonico() {
        return NumeroTelefonico;
    }

    //nombre
    public void setNombre(String Nombre) {

        this.Nombre = Nombre;
    }

    public String getNombre() {

        return this.Nombre;
    }

    //Apellido Paterno 
    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    //calle 
    public void setCalle(String Calle) {

        this.Calle = Calle;
    }

    public String getCalle() {

        return this.Calle;
    }

    //Numero de casa
    public void setNumeroCasa(Integer NumeroCasa) {

        this.NumeroCasa = NumeroCasa;
    }

    public Integer getNumeroCasa() {

        return this.NumeroCasa;
    }

    //Colonia
    public void setColonia(String Colonia) {

        this.Colonia = Colonia;
    }

    public String getColonia() {

        return this.Colonia;
    }

    //CP
    public void setCP(Integer CP) {

        this.CP = CP;
    }

    public Integer getCP() {

        return this.CP;
    }

    //Ciudad
    public void setCiudad(String Ciudad) {

        this.Ciudad = Ciudad;
    }

    public String getCiudad() {

        return this.Ciudad;
    }

    //Estado
    public void setEstado(String Estado) {

        this.Estado = Estado;
    }

    public String getEstado() {

        return this.Estado;
    }

}
