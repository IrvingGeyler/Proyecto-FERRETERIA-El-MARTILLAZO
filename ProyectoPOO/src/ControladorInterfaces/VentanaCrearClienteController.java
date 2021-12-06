
package ControladorInterfaces;

import Clases.ClienteMacizo;
import ControladorArchivos.ManejoArchivos;
import ControladorClases.ControladorClienteMacizo;
import java.io.IOException;
import java.net.URL;
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
public class VentanaCrearClienteController implements Initializable {

    @FXML
    private TextField LectorNombre;
    @FXML
    private TextField LectorApellidoPaterno;
    @FXML
    private TextField LectorIDE;
    @FXML
    private TextField LectorNumeroTelefonico;
    @FXML
    private Button BotonModificarCliente;
    @FXML
    private TextField LectorCalle;
    @FXML
    private TextField LectorColonia;
    @FXML
    private TextField LectorCP;
    @FXML
    private TextField LectorCiudad;
    @FXML
    private TextField LectorEstado;
    @FXML
    private TextField LectorNumCasa;
    @FXML
    private TableView <ClienteMacizo> TablaClientesMacizos;
    @FXML
    private TableColumn  ColumNombre;
    @FXML
    private TableColumn ColumApellidoPaterno;
    @FXML
    private TableColumn ColumNumeroTelefonico;
    @FXML
    private TableColumn ColumIDE;
    @FXML
    private TableColumn ColumCalle;
    @FXML
    private TableColumn ColumNumCasa;
    @FXML
    private TableColumn ColumColonia;
    @FXML
    private TableColumn ColumCP;
    @FXML
    private TableColumn ColumCiudad;
    @FXML
    private TableColumn ColumEstado;
    @FXML
    private Button BotonRegresarMenu;
    @FXML
    private Button BotonEliminarCliente;
    @FXML
    private Button BotonAñadirCliente;

    //atributos
    private ObservableList<ClienteMacizo> listaObservableClienteMacizos;
    private ControladorClienteMacizo registroClientes;

    /**
     * Initializes the controller class. Al abrirse la ventana debe
     * inicializarse la tabla con el tipo de dato que recibira y limpiar los
     * text field.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inicializa la observableList 
        this.listaObservableClienteMacizos = FXCollections.observableArrayList();
        this.TablaClientesMacizos.setItems(this.listaObservableClienteMacizos);

        //Especificificacion de las columnas de los atributos de los clientes
        this.ColumNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.ColumApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("ApellidoPaterno"));
        this.ColumNumeroTelefonico.setCellValueFactory(new PropertyValueFactory<>("NumeroTelefonico"));
        this.ColumIDE.setCellValueFactory(new PropertyValueFactory<>("IDE"));
        this.ColumCalle.setCellValueFactory(new PropertyValueFactory<>("Calle"));
        this.ColumNumCasa.setCellValueFactory(new PropertyValueFactory<>("NumeroCasa"));
        this.ColumColonia.setCellValueFactory(new PropertyValueFactory<>("Colonia"));
        this.ColumCP.setCellValueFactory(new PropertyValueFactory<>("CP"));
        this.ColumCiudad.setCellValueFactory(new PropertyValueFactory<>("Ciudad"));
        this.ColumEstado.setCellValueFactory(new PropertyValueFactory<>("Estado"));

        //limpiar textFields
        Limpiar_TexFields();
    }

    /**
     * Accion del Boton para cargar los datos de los textField y crear un nuevo
     * cliente, para posteriormente guardas los cambios en el archivo del
     * controlador cliente.
     *
     * @param event
     */
    @FXML
    private void AñadirCliente(ActionEvent event) {
        if (LectorIDE.getText().isEmpty()) {
            return;
        }
        //Captura de los datos de los lectores como strings

        String TextNombre = this.LectorNombre.getText();
        String TextApellidoPaterno = this.LectorApellidoPaterno.getText();
        String TextIDE = this.LectorIDE.getText();
        String TextCalle = this.LectorCalle.getText();
        String TextColonia = this.LectorColonia.getText();
        String TextCiudad = this.LectorCiudad.getText();
        String TextEstado = this.LectorEstado.getText();

        try {
            //Conversion de los datos String leidos a los datos numericos

            int NumTelefonico = Integer.parseInt(this.LectorNumeroTelefonico.getText());
            int NumCasa = Integer.parseInt(this.LectorNumCasa.getText());
            int NumCP = Integer.parseInt(this.LectorCP.getText());

            ClienteMacizo nuevo = new ClienteMacizo(TextNombre, TextApellidoPaterno, NumTelefonico, TextIDE, TextCalle, NumCasa, TextColonia, NumCP, TextCiudad, TextEstado);
            registroClientes.AgregarCliente(nuevo);
            listaObservableClienteMacizos.add(nuevo);
            TablaClientesMacizos.refresh();

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
     * Accion del Boton para cargar al cliente seleccionado de la tabla y
     * realizar modificaciones, abriendo la ventana de modificacion de los datos
     * del cliente , recuperando a un cliente con las modificaciones y guardarlo
     * en el archivo.
     *
     * @param event
     */
    @FXML
    private void ModificarCliente(ActionEvent event) {
        //selecionando al cliente que sera modificado
        ClienteMacizo seleccionado = getElemtoSeleccionado();

        if (seleccionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(this.getClass().getResource("/Interfaz/VentanaEditarCliente.fxml"));
                Pane ventana = (Pane) loader.load();
                VentanaArregloController control = loader.getController();

                control.Inicializar(seleccionado);

                Scene scene = new Scene(ventana);
                Stage secundaryStage = new Stage();
                secundaryStage.setScene(scene);
                secundaryStage.initModality(Modality.APPLICATION_MODAL);
                secundaryStage.showAndWait();

                //obtener el modificado
                ClienteMacizo modificado = control.getModificado();
                if (modificado != null) {
                    registroClientes.ModificarCliente(modificado);
                    TablaClientesMacizos.refresh();
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            //mensaje de alerta
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Aviso");
            alert.setContentText("Primero debe seleccionar un Cliente Macizo de la tabla.");
            alert.showAndWait();
        }

    }

    /**
     * Accion del Boton para regresar al menu principal.
     *
     * @param event
     */
    @FXML
    private void RegresarMenuPrincipal(ActionEvent event) {
        this.Cerrar();
    }

    /**
     * Boton para eliminar a un cliente seleccionado de la tabla y guardar este
     * cambio en el archivo.
     *
     * @param event
     */
    @FXML
    private void EliminarCliente(ActionEvent event) {
        //recoger al objeto que se selecciono en la tabla
        ClienteMacizo seleccionado = getElemtoSeleccionado();

        //comprobando si es null
        if (seleccionado != null) {
            registroClientes.EliminarCliente(getElemtoSeleccionado());
            listaObservableClienteMacizos.remove(getElemtoSeleccionado());
            TablaClientesMacizos.refresh();
        } else {
            //mensaje de alerta
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Aviso");
            alert.setContentText("Primero debe seleccionar un Cliente Macizo de la tabla.");
            alert.showAndWait();
        }
    }

    /**
     * Metodo que realiza cuando se cierre la ventana de crear cliente, regrese
     * al menu principal.
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

            Stage myStage = (Stage) this.BotonRegresarMenu.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Metodo para recuperar la clase ControladorClienteMacizo guardado en un
     * archivo, y mostrando en la tabla al iniciar la ventana los datos si es
     * que existen de los clientes ya creados y guardados.
     */
    public void Inicializar() {
        ControladorClienteMacizo otro = ManejoArchivos.RecuperarArchivoCliente();
        if (otro != null) {
            registroClientes = otro;
            listaObservableClienteMacizos.addAll(registroClientes.getListMacizos());
            TablaClientesMacizos.refresh();
        } else {
            registroClientes = new ControladorClienteMacizo();

        }
    }

    /**
     * Metodo para seleccionar a un cliente de la tabla.
     *
     * @return Retornara a un ClienteMacizo, el que fue seleccionado para poder
     * realizar algunas acciones como eliminarlo o modificarlo.
     */
    private ClienteMacizo getElemtoSeleccionado() {
        if (this.TablaClientesMacizos != null) {
            final ClienteMacizo seleccionado = (ClienteMacizo) this.TablaClientesMacizos.getSelectionModel().getSelectedItem();
            return seleccionado;
        }
        return null;
    }

    /**
     * Metodo para limpiar los textField cuando ya son recogidos los datos.
     */
    public void Limpiar_TexFields() {
        this.LectorNombre.clear();
        this.LectorApellidoPaterno.clear();
        this.LectorNumeroTelefonico.clear();
        this.LectorIDE.clear();
        this.LectorCalle.clear();
        this.LectorNumCasa.clear();
        this.LectorColonia.clear();
        this.LectorCP.clear();
        this.LectorCiudad.clear();
        this.LectorEstado.clear();
    }
}
