
package ControladorInterfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Irving Geyler Cupul Uc
 * @author Joar Honorio Ruiz Peraza
 * @author Didier Andrey Tec Ezquivel
 */
public class VentanaPrincipalController implements Initializable {

    @FXML
    private Button BotonAgregarCliente;
    @FXML
    private Button BotonSalir;
    @FXML
    private Button BotonAgregarProducto;
    @FXML
    private Button BotonOperacionesCliente;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    /**
     * Accion del Boton para abrir  la ventana de crear clientes.
     * @param event 
     */
    @FXML
    private void AgregaCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/Interfaz/VentanaCrearCliente.fxml"));
            Pane ventana = (Pane) loader.load();
            VentanaCrearClienteController control = loader.getController();

            control.Inicializar();

            Scene scene = new Scene(ventana);
            Stage secundaryStage = new Stage();
            secundaryStage.setScene(scene);
            secundaryStage.setOnCloseRequest(e -> control.Cerrar());
            secundaryStage.show();
            Stage myStage = (Stage) this.BotonSalir.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Accion del Boton para abrir  la ventana de agregar producto.
     * @param event 
     */
    @FXML
    private void AgregarProducto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/Interfaz/VentanaAgregarProducto.fxml"));
            Pane ventana = (Pane) loader.load();
            VentanaAgregarProductoController control = loader.getController();
            
            control.Inicializar();
            
            Scene scene = new Scene(ventana);
            Stage secundaryStage = new Stage();
            secundaryStage.setScene(scene);
            secundaryStage.setOnCloseRequest(e -> control.Cerrar());
            secundaryStage.show();
            Stage myStage = (Stage) this.BotonSalir.getScene().getWindow();
            myStage.close();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Accion del Boton para abrir  la ventana de operaciones para los clientes
     * @param event 
     */
    @FXML
    private void RealizarOperacionesCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/Interfaz/VentanaRealizarCompra.fxml"));
            Pane ventana = (Pane) loader.load();
            VentanaRealizarCompraController control = loader.getController();
            
            control.Inicializar();
            
            Scene scene = new Scene(ventana);
            Stage secundaryStage = new Stage();
            secundaryStage.setScene(scene);
            
            secundaryStage.show();
            Stage myStage = (Stage) this.BotonSalir.getScene().getWindow();
            myStage.close();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
    /**
     * Accion del Boton para salir del programa.
     * @param event 
     */
    @FXML
    private void Salir(ActionEvent event) {
        Stage myStage = (Stage)this.BotonSalir.getScene().getWindow();
        myStage.close();
    }
    
}
