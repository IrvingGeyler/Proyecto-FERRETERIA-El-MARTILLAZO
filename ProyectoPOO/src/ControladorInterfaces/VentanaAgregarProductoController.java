
package ControladorInterfaces;

import Clases.Producto;
import ControladorArchivos.ManejoArchivos;
import ControladorClases.ControladorProducto;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Irving Geyler Cupul Uc
 * @author Joar Honorio Ruiz Peraza
 * @author Didier Andrey Tec Ezquivel

 */
public class VentanaAgregarProductoController implements Initializable {

    @FXML
    private TextField LectorNombreProducto;
    @FXML
    private TextField LectorIDEProducto;
    @FXML
    private TextField LectorPrecioPublicoProducto;
    @FXML
    private TextField LectorMarcaProducto;
    @FXML
    private TextField LectorModeloProducto;
    @FXML
    private TextField LectorUnidadesProducto;
    @FXML
    private Button BotonAgregarDatosProducto;
    @FXML
    private Button BotonModificarDatosProducto;
    @FXML
    private Button BotonRegresarMenuPrincipal;
    @FXML
    private Button BotonEliminarProducto;
    @FXML
    private TableView <Producto> TablaProductos;
    @FXML
    private TableColumn ColumNombreProducto;
    @FXML
    private TableColumn ColumIDEProducto;
    @FXML
    private TableColumn ColumPrecioProducto;
    @FXML
    private TableColumn ColumMarcaProducto;
    @FXML
    private TableColumn ColumModeloProducto;
    @FXML
    private TableColumn ColumUnidadesProducto;
    @FXML
    private Button BotonImprimirListaProductos;
    
    //atributos
    private ObservableList<Producto> listaObservableProducto;
    private ControladorProducto registroProductos;
    

    /**
     * Initializes the controller class.
     * Inicializando que al abrir esta ventana presente los datos de los productos ya 
     * guardados.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //inicializa la observableList 
        this.listaObservableProducto = FXCollections.observableArrayList();
        this.TablaProductos.setItems(this.listaObservableProducto);
        
        //Especificacion de columnas
        this.ColumIDEProducto.setCellValueFactory(new PropertyValueFactory<>("IDEProducto"));
        this.ColumNombreProducto.setCellValueFactory(new PropertyValueFactory<>("NombreProducto"));
        this.ColumPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("PrecioPublico"));
        this.ColumMarcaProducto.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        this.ColumModeloProducto.setCellValueFactory(new PropertyValueFactory<>("Modelo"));
        this.ColumUnidadesProducto.setCellValueFactory(new PropertyValueFactory<>("Unidades"));
        
        //limpiar textfields
        Limpiar_TexFields();
    }    
    
    
    /**
     * Accion del Boton para eliminar un producto seleccionado en la tabla y que este cambio sea
     * guardado en el archivo.
     * @param event 
     */
    @FXML
    private void EliminarProducto(ActionEvent event) {
        //recoger el objeto que se selecciona de la tabla
        Producto seleccionado = getElemtoSeleccionado();
        
        //comprobando si es null
        if (seleccionado !=null) {
            registroProductos.EliminarProducto(getElemtoSeleccionado());
            listaObservableProducto.remove(getElemtoSeleccionado());
            TablaProductos.refresh();
        }else{
            //mensaje de alerta
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Aviso");
            alert.setContentText("Primero debe seleccionar un producto de la tabla.");
            alert.showAndWait();
        } 
    }
    
    /**
     * Accion del Boton para guardar datos de los text field y crear un nuevo producto, y este
     * guardarlo en el archivo donde se encuentra el controlador de clinetes.
     * @param event 
     */
    @FXML
    private void AgregarDatosProducto(ActionEvent event) {
        if (LectorIDEProducto.getText().isEmpty()) {
            return;
        }
        
        //Captura de los datos de los lectores como strings
        String TextIDEProducto = this.LectorIDEProducto.getText();
        String TextNombreProducto = this.LectorNombreProducto.getText();
        String TextMarcaProducto = this.LectorMarcaProducto.getText();
        String TextModeloProducto = this.LectorModeloProducto.getText();

        try {           
            //Conversion de los datos string a datos numericos
            float PrecioPublicoProducto = Float.parseFloat(this.LectorPrecioPublicoProducto.getText());
            int Unidades = Integer.parseInt(this.LectorUnidadesProducto.getText());
            
            //creacion y guardado del producto
            Producto nuevo = new Producto(TextNombreProducto, TextIDEProducto, PrecioPublicoProducto, TextMarcaProducto, TextModeloProducto, Unidades);
            registroProductos.AgregarProducto(nuevo);
            listaObservableProducto.add(nuevo);
            TablaProductos.refresh();
            
            //limpiar los textfield
            Limpiar_TexFields();
            
        } catch (NumberFormatException e) {
            // Lanzamiento de una ventana por si en los lectores de numeros no solo se encontraban numeros
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("");
            alerta.setTitle("Error en la escritura del numero");
            alerta.setContentText("El numero no debe contener caracteres especiales como : - ; _ + - * /");
        }
         
    }
    
    /**
     * Accion del Boton para recuperar un producto seleccionado de la tabla y abrir la ventana
     * para modicar el producto y recuperarlo con las modificaciones y guardar los cambios en 
     * el archivo.
     * @param event 
     */
    @FXML
    private void ModificarDatosProducto(ActionEvent event) {
        //recoger al producto de la tabla
        Producto seleccionado = getElemtoSeleccionado();
         if(seleccionado != null){
                try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(this.getClass().getResource("/Interfaz/VentanaModificarPorducto.fxml"));
                Pane ventana = (Pane) loader.load();
                VentanaModificarPorductoController control = loader.getController();

                control.Inicializar(seleccionado);

                Scene scene = new Scene(ventana);
                Stage secundaryStage = new Stage();
                secundaryStage.setScene(scene);
                secundaryStage.initModality(Modality.APPLICATION_MODAL);
                secundaryStage.showAndWait();
                
                //obtener el modificado
                Producto modificado = control.getProductoModifi();
                    if ( modificado!= null) {
                        registroProductos.ModificarProducto(modificado);
                        TablaProductos.refresh();
                    } 

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }else{
            //mensaje de alerta
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Aviso");
            alert.setContentText("Primero debe seleccionar un producto de la tabla.");
            alert.showAndWait();
        }
        
    }
    
    
     /**
     * Accion del  Boton para imprimir los productos disponibles.
     * @param event 
     */
    @FXML
    private void ImprimirListaProductos(ActionEvent event) {
        ArrayList<Producto> listImprimir= this.registroProductos.getListProductos();
        System.err.println("**Inventario productos**");
        for (int i = 0; i < listImprimir.size(); i++) {
            System.out.println(listImprimir.get(i).toString());
        }
    }
    
    
    /**
     * Accion del Boton para regresar al menu principal.
     * @param event 
     */
    @FXML
    private void RegresarMenuPrincipal(ActionEvent event) {
        this.Cerrar();
    }
    
    /**
     * Metodo que cuando se cierre la ventana de crear cliente, regrese al menu principal.
     */
    public void Cerrar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaz/VentanaPrincipal.fxml"));
            Parent root = loader.load();
            VentanaPrincipalController control = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.BotonRegresarMenuPrincipal.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Metodo para inicializar que recupera el controlador de porductos del archivo 
     * y son mostrados los datos de los producto en la tabla.
     */
    public void Inicializar() {
        ControladorProducto otro = ManejoArchivos.RecuperarArchivoProducto();
        if(otro!=null){
            registroProductos = otro;
            listaObservableProducto.addAll(registroProductos.getListProductos());
            TablaProductos.refresh();
        }else{
            registroProductos = new ControladorProducto();
           
        }
    }
    
    
     /**
     * Metodo para seleccionar a un cliente de la tabla.
     * @return Retornara un producto.
     */
    private Producto getElemtoSeleccionado(){
        if(this.TablaProductos != null){
            final Producto seleccionado = (Producto) this.TablaProductos.getSelectionModel().getSelectedItem();
            return seleccionado;
        }
        return null;
    }
    
     /**
     * Metodo para limpiar los textField.
     */
    public void Limpiar_TexFields(){
        this.LectorNombreProducto.clear();
        this.LectorIDEProducto.clear();
        this.LectorMarcaProducto.clear();
        this.LectorModeloProducto.clear();
        this.LectorUnidadesProducto.clear();
        this.LectorPrecioPublicoProducto.clear();
    }
    
   
}
