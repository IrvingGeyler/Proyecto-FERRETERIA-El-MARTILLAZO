
package ControladorArchivos;

import ControladorClases.ControladorClienteMacizo;
import ControladorClases.ControladorProducto;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



/**
 *
 * @author Irving Geyler Cupul Uc
 * @author Joar Honorio Ruiz Peraza
 * @author Didier Andrey Tec Ezquivel.
 */
 
/**
 * Clase destinada al menejo exclusivo de archivos para almecenar los clientes y los productos.
 * 
 */
public class ManejoArchivos {
    
    /**
    * Metodo para el guardado la clase ControladorClienteMacizo que contiene la arrayList de los productos
    * creados, al igual de ser capaza de realizar actulizaciones a la arrayList de los productos
    * ante modificaciones.
    * @param lista Este es el parametro que obligadamente tiene que ser tipo ControladorClienteMacizo el cual
    * tiene la arrayList de los clientes registrados
    */

    public static void GuardarArchivoCliente(ControladorClienteMacizo lista) {
        try(FileOutputStream file = new FileOutputStream("BaseClientesMacizo.txt")) {
            ObjectOutputStream  obj = new ObjectOutputStream(file);
            obj.writeObject(lista);
            obj.close();
        } catch (IOException e) {
            System.out.println("Hubo un problema");
        }
    }
    
    /**
     *Metodo para guardar la clase ControladorProducto la cual contiene la arrayList de los
     * productos registrados y es capaz de actulizar modificaciones de los productos.
     * @param lista Este es el parametro del tipo ControladorPorducto el cual
     * tiene la arrayList de los productos registrados.
     */
    public static void GuardarArchivoProducto(ControladorProducto lista) {
        try(FileOutputStream file = new FileOutputStream("BaseProductos.txt")) {
            ObjectOutputStream  obj = new ObjectOutputStream(file);
            obj.writeObject(lista);
            obj.close();
        } catch (IOException e) {
            System.out.println("Hubo un problema");
        }
    }
    
    
    /**
     * Metodo para recuperar el controlador de clientes guardado en un archivo.
     * @return ControladorClienteMacizo Se retornara el ControladorClienteMacizo el cual contiene 
     * la arrayList de los clientes creados.
     */
    public static ControladorClienteMacizo RecuperarArchivoCliente(){
        ControladorClienteMacizo objt = null;
        
        try (FileInputStream file= new FileInputStream("BaseClientesMacizo.txt")){
            ObjectInputStream obj = new ObjectInputStream(file);
            try {
               objt = (ControladorClienteMacizo) obj.readObject();
            } catch (ClassNotFoundException ex) {
                objt = null;
            }
            obj.close();
            return objt;
        } catch (Exception e) {
            return objt;
        }
        
    }
    
    /**
     * Metodo para recuperar el controlador de productos guardado en un archivo.
     * @return Controlador Producto Retorna la clase que contiene la lista de productos creados.
     */
    public static ControladorProducto RecuperarArchivoProducto(){
        ControladorProducto  objt = null;
        try (FileInputStream file= new FileInputStream("BaseProductos.txt")){
            ObjectInputStream obj = new ObjectInputStream(file);
            try {
               objt = (ControladorProducto) obj.readObject();  
            } catch (ClassNotFoundException ex) {
                objt = null;
            }
            obj.close();
            return objt;
            
        } catch (Exception e) {
            return objt;
        }
        
    }
    
}
