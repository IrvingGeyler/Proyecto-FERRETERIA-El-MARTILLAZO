
package ControladorInterfaces;

import Clases.ClienteMacizo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Irving Geyler Cupul Uc
 * @author Joar Honorio Ruiz Peraza
 * @author Didier Andrey Tec Ezquivel
 */
public class VentanaArregloController implements Initializable {

    @FXML
    private Button BotonActualizarDatosCliente;
    @FXML
    private Button BotonCancelar;
    @FXML
    private TextField LectorCorrecNombre;
    @FXML
    private TextField LectorCorrecApellido;
    @FXML
    private TextField LectorCorrecNumeroTele;
    @FXML
    private TextField LectorCorrecCalle;
    @FXML
    private TextField LectorCorrecNumCasa;
    @FXML
    private TextField LectorCorrecColonia;
    @FXML
    private TextField LectorCorrecCP;
    @FXML
    private TextField LectorCorrecCiudad;
    @FXML
    private TextField LectorCorrecEstado;
    @FXML
    private Label LabelIdentificadorCliente;
    
    //atributo
    private ClienteMacizo clienteModifi;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       
    }    
    
    /**
     * Metodo para inicializar la ventana con los datos del cliente en text fields que se desea modificar.
     * @param seleccionado El parametro para usar este metodo es que reciba el cliente que se desea modificar 
     * para que sea posible recuperar y mostrar su datos.
     */
    public void Inicializar(ClienteMacizo seleccionado) {
       if(seleccionado !=null){
           //designar al cliente seleccionado
            this.clienteModifi = seleccionado;
            
            //colocando datos
            this.LabelIdentificadorCliente.setText(clienteModifi.getIDE());
            this.LectorCorrecNombre.setText(clienteModifi.getNombre());
            this.LectorCorrecApellido.setText(clienteModifi.getApellidoPaterno());
            this.LectorCorrecNumeroTele.setText(String.valueOf(clienteModifi.getNumeroTelefonico()));
            this.LectorCorrecCalle.setText(clienteModifi.getCalle());
            this.LectorCorrecNumCasa.setText(String.valueOf(clienteModifi.getNumeroCasa()));
            this.LectorCorrecColonia.setText(clienteModifi.getColonia());
            this.LectorCorrecCP.setText(String.valueOf(clienteModifi.getCP()));
            this.LectorCorrecCiudad.setText(clienteModifi.getCiudad());
            this.LectorCorrecEstado.setText(clienteModifi.getEstado());
        }
    }
    
    /**
     * Metodo para retornar al cliente ya modificado.
     * @return Cliente Macizo  es retornado ya con su modificaciones.
     */
    public ClienteMacizo getModificado() {
        return this.clienteModifi;
    }
    
    /**
     * Boton para actualizar los datos recogidos en los text fields al cliente modificado y cerrar la ventanta 
     * de modificacion.
     * @param event 
     */
    @FXML
    private void ActualizarDatosCliente(ActionEvent event) {
        //recoleccion de los datos de los texfield
        String TextNombre = this.LectorCorrecNombre.getText();
        String TextApellidoPaterno = this.LectorCorrecApellido.getText();
        String TextCalle = this.LectorCorrecCalle.getText();
        String TextColonia = this.LectorCorrecColonia.getText();
        String TextCiudad = this.LectorCorrecCiudad.getText();
        String TextEstado = this.LectorCorrecEstado.getText();
        try {
            //conversion de los datos recopilados
            int NumTelefonico = Integer.parseInt(this.LectorCorrecNumeroTele.getText());
            int NumCasa = Integer.parseInt(this.LectorCorrecNumCasa.getText());
            int NumCP = Integer.parseInt(this.LectorCorrecCP.getText());
            
            //designando los nuevos datos al producto que sera modificado
            this.clienteModifi.setNombre(TextNombre);
            this.clienteModifi.setApellidoPaterno(TextApellidoPaterno);
            this.clienteModifi.setNumeroTelefonico(NumTelefonico);
            this.clienteModifi.setCalle(TextCalle);
            this.clienteModifi.setNumeroCasa(NumCasa);
            this.clienteModifi.setColonia(TextColonia);
            this.clienteModifi.setCP(NumCP);
            this.clienteModifi.setCiudad(TextCiudad);
            this.clienteModifi.setEstado(TextEstado);
            
            //cerrar modificacion
            Stage stage = (Stage)this.BotonCancelar.getScene().getWindow();
            stage.close();
            
        } catch (NumberFormatException e) {
            //Lanzamiento de una ventana por si en los lectores de numeros no solo se encontraban numeros
            
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("");
            alerta.setTitle("Error en la escritura del numero");
            alerta.setContentText("El numero no debe contener caracteres especiales como : - ; _ + - * /");
        }
    }
    /**
     * Accion del Boton para cancelar la modificacion y que la modificacion del cliente sea nulo.
     * @param event 
     */
    @FXML
    private void CancelarCorrecionDatos(ActionEvent event) {
        this.clienteModifi = null;
        Stage stage = (Stage)this.BotonCancelar.getScene().getWindow();
        stage.close();
        
    }

}
