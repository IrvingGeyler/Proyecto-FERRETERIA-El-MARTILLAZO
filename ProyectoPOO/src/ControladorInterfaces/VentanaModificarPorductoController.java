
package ControladorInterfaces;

import Clases.Producto;
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
public class VentanaModificarPorductoController implements Initializable {

    @FXML
    private Button BotonActualizarDatosProducto;
    @FXML
    private Button BotonCancelarCambiosProducto;
    @FXML
    private TextField LectorCambioNombreProducto;
    @FXML
    private TextField LectorCambioPrecioPublico;
    @FXML
    private TextField LectorCambioMarcaProducto;
    @FXML
    private TextField LectorCambioModeloProducto;
    @FXML
    private TextField LectorCambioUnidadesProducto;
    @FXML
    private Label LabelIDEProducto;
    
    //atributo
    private Producto productoModifi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Accion del Boton para actualizar los datos ya recogidos en los text
     * fields y realizar las modificaciones al producto.
     *
     * @param event
     */
    @FXML
    private void ActualizarCambiosProducto(ActionEvent event) {
        //recoleccion de datos
        String TextNombreProducto = this.LectorCambioNombreProducto.getText();
        String TextMarcaProducto = this.LectorCambioMarcaProducto.getText();
        String TextModeloProducto = this.LectorCambioModeloProducto.getText();

        try {
            //conversion de Strings a numericos
            float CambioPrecioPublico = Float.parseFloat(this.LectorCambioPrecioPublico.getText());
            int CambioUnidades = Integer.parseInt(this.LectorCambioUnidadesProducto.getText());

            this.productoModifi.setNombreProducto(TextNombreProducto);
            this.productoModifi.setMarca(TextMarcaProducto);
            this.productoModifi.setModelo(TextModeloProducto);
            this.productoModifi.setPrecioPublico(CambioPrecioPublico);
            this.productoModifi.setUnidades(CambioUnidades);

            //cerrar modificacion
            Stage stage = (Stage) this.BotonCancelarCambiosProducto.getScene().getWindow();
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
     * Boton para cancelar los cambios y regresar un cambio producto con cambio
     * nulo.
     *
     * @param event
     */
    @FXML
    private void CancelarCambiosProducto(ActionEvent event) {
        this.productoModifi = null;
        Stage stage = (Stage) this.BotonCancelarCambiosProducto.getScene().getWindow();
        stage.close();
    }

    /**
     * Metodo para inicializar, recogiendo al producto que sera modificado y
     * presentando sus datos.
     *
     * @param seleccionado El parametro es un producto para que este metodo
     * muestre los datos.
     */
    public void Inicializar(Producto seleccionado) {
        if (seleccionado != null) {
            this.productoModifi = seleccionado;

            //colocando datos
            this.LabelIDEProducto.setText(productoModifi.getIDEProducto());
            this.LectorCambioNombreProducto.setText(productoModifi.getNombreProducto());
            this.LectorCambioPrecioPublico.setText(String.valueOf(productoModifi.getPrecioPublico()));
            this.LectorCambioMarcaProducto.setText(productoModifi.getMarca());
            this.LectorCambioModeloProducto.setText(productoModifi.getModelo());
            this.LectorCambioUnidadesProducto.setText(String.valueOf(productoModifi.getUnidades()));
        }
    }

    /**
     * Metodo par retornar el cliente ya modificado.
     *
     * @return Producto ya modificado.
     */
    public Producto getProductoModifi() {
        return this.productoModifi;
    }

}
