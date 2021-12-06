
package ControladorInterfaces;

import Clases.ClienteMacizo;
import Clases.Compra;
import Clases.Producto;
import Clases.ProductoComprado;
import ControladorArchivos.ManejoArchivos;
import ControladorClases.ControladorClienteMacizo;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Irving Geyler Cupul Uc
 * @author Joar Honorio Ruiz Peraza
 * @author Didier Andrey Tec Ezquivel
 */
public class VentanaRealizarCompraController implements Initializable {

    @FXML
    private TextField LectIDECliente;
    @FXML
    private Button BotonBuscarCliente;
    @FXML
    private TextField LectNombreMuestra;
    @FXML
    private Button BotonRegresa;
    @FXML
    private TextField LectMartiPuntosMuestra;
    @FXML
    private TableView<ProductoComprado> TablaProductosCarrito;
    @FXML
    private Button BotonRealizarCompra;
    @FXML
    private Button BotonCancelarCompra;
    @FXML
    private TextField LectIDEproductoCompra;
    @FXML
    private TextField LectUnidadesProductoCompra;
    @FXML
    private TextField LectMuestraPagoTotal;
    @FXML
    private TextField LectMartiPuntosUsar;
    @FXML
    private TextField LectImportePago;
    @FXML
    private Button BotonCargarMartiṔuntosUsar;
    @FXML
    private TableColumn ColumNombreProductoComprado;
    @FXML
    private TableColumn ColumUnidadesVendidas;
    @FXML
    private TableColumn ColumPagoParcial;
    @FXML
    private Button BotonCargarProductoCarrito;
    
    //atributos
    private ControladorClienteMacizo listaMacizo;
    private ControladorProducto listaProductos;
    private Compra carrito;//tiene la lista de productos comprados
    private ObservableList<ProductoComprado> carritoObservable;
    private ClienteMacizo Comprador;
    private ArrayList<Producto> listModifCompra;//los productos modificados por los productos comprados.

    /**
     * Initializes the controller class.
     * Al abrir la ventana se cree prepare la tabla para mostrar los productos comprados
     * y lo text fields limpios.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inicializar observable list
        this.carritoObservable = FXCollections.observableArrayList();
        this.TablaProductosCarrito.setItems(carritoObservable);
        
        //dar valores a la tabla
        this.ColumNombreProductoComprado.setCellValueFactory(new PropertyValueFactory<>("NombreProducto"));
        this.ColumUnidadesVendidas.setCellValueFactory(new PropertyValueFactory<>("Unidades"));
        this.ColumPagoParcial.setCellValueFactory(new PropertyValueFactory<>("CostoParcial"));
        
        //limpiar texts y bloquear lo que son indispensables
        Limpiar_TexFields();
        this.LectNombreMuestra.setEditable(false);
        this.LectMartiPuntosMuestra.setEditable(false);
        this.LectMuestraPagoTotal.setEditable(false);
    }   
    
    /**
     * Accion del Boton para cargar el IDE dado para buscar al cliente/comprador.
     * @param event 
     */
    @FXML
    private void BuscarClienteComprador(ActionEvent event) {
        //Captura el IDE
        String IDECliente = LectIDECliente.getText();
        this.Comprador = listaMacizo.BusquedaClienteMacizo(IDECliente);
        
        if (Comprador != null) {
            //colocando los datos en ventana
            this.LectNombreMuestra.setText(Comprador.getNombre());
            this.LectMartiPuntosMuestra.setText(String.valueOf(Comprador.getPunto().getValor()));
            this.carrito = new Compra();
            this.listModifCompra = new ArrayList<>();

        }else{
            this.LectIDECliente.clear();
            this.LectNombreMuestra.clear();
            this.LectMartiPuntosMuestra.clear();
           //mensaje de alerta
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Aviso");
            alert.setContentText("No se encuntra el cliente que solicito.");
            alert.showAndWait();
            
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
     * Accion del Boton para realizar la compra, cargando todos los datos de los productos 
     * añadidos al carrtito y haber dado el importe.
     * @param event 
     */
    @FXML
    private void RealizarCompra(ActionEvent event) {
        if(Comprador != null){
            float Cambio = 0, PagoTotal = carrito.getTotalCompra();
            try {

                float Importe = Float.parseFloat(this.LectImportePago.getText());
                Cambio = Importe - PagoTotal;
                Imprimir_Ticket(Comprador, carrito, Cambio, PagoTotal, Importe);
                listaMacizo.ModificarClienteComprador(Comprador, carrito);
                listaProductos.Modificar_ProductoActualVenta(listModifCompra);
                this.carrito = null;
                this.listModifCompra = null;
                this.Comprador = null;
                this.Comprador = null;
                Limpiar_TexFields();
            } catch (NumberFormatException e) {
                // Lanzamiento de una ventana por si en los lectores de numeros no solo se encontraban numero
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setHeaderText("");
                alerta.setTitle("Error en la escritura del numero");
                alerta.setContentText("El numero no debe contener caracteres especiales como : - ; _ + - * /");
            }
        }else
        {
            //mensaje de alerta
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Aviso");
            alert.setContentText("No puede hacer eso.");
            alert.showAndWait();

        }
    }
    
    
    /**
     * Accion del Boton para cancelar la compra.
     * @param event 
     */
    @FXML
    private void CancelarCompra(ActionEvent event) {
        if (Comprador !=null) {
            this.carrito = null;
            this.carritoObservable.removeAll();
            this.TablaProductosCarrito.refresh();
            this.listModifCompra.removeAll(listModifCompra);
            this.Comprador = null;
            Limpiar_TexFields();
        }else{
            //mensaje de alerta
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Aviso");
            alert.setContentText("No puede hacer eso.");
            alert.showAndWait();
        } 
    }
    
    /**
     * Accion del Boton para cargar los MartiPuntos para relizar descuento en la compra.
     * @param event 
     */
    @FXML
    private void CargarMartiPuntosUsar(ActionEvent event) {
    }
    
    /**
     * Accion del Boton para cargar el IDE del producto que quiere comprar y las unidades, y este 
     * agregarse productos comprados al carrito.
     * @param event 
     */
    @FXML
    private void CargarProductoCarrito(ActionEvent event) {
        
        if (Comprador != null) {
            //captura de datos
            String IDEProCompra = this.LectIDEproductoCompra.getText();
            
            try {
                int ProducPedidos = Integer.parseInt(this.LectUnidadesProductoCompra.getText());
                ProductoComprado Compra = new ProductoComprado(IDEProCompra, ProducPedidos); //crear producto comprado que quiere 
                Producto ComunCompra = listaProductos.BusquedaProducto(IDEProCompra);//ver si hay alguno igual al que quiero comprar
                if (ComunCompra != null) {                  
                    float CostoParcial = Compra_Parcial(Compra, ComunCompra);//recupera el posible costo parcial si hay comun compra
                    if (CostoParcial != 0) {
                        int restantes = ComunCompra.Comprobar_Ventas_Unidades(Compra);//recupera las unidades restantes
                        ComunCompra.setUnidades(restantes);//se da el nuevo valor al comun compra
                        setListModifCompra(ComunCompra);//guardar los modificados en una array para luego modificar el archivo
                        Compra.setCostoParcial(CostoParcial);//darle precio parcial al producto comprado por las unidades
                        
                        ProductoComprado nuevaCompra = ComunCompra.Dar_Forma_Producto(Compra);//se recupera con forma
                        this.carrito.AgregarCarrito(nuevaCompra);//se manda a la compra que se esta armando
                        Compra_Total(carrito);
                        this.carritoObservable.add(nuevaCompra);//se muestra el producto comprado
                        this.TablaProductosCarrito.refresh();
                        this.LectIDEproductoCompra.clear();
                        this.LectUnidadesProductoCompra.clear();
                    }else{
                        //mensaje de alerta
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Aviso");
                        alert.setContentText("No hay producto.");
                        alert.showAndWait();
                    }
                }else{
                    //mensaje de alerta
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Aviso");
                    alert.setContentText("No se encontro el producto.");
                    alert.showAndWait();
                }
                
            } catch (NumberFormatException e) {
                // Lanzamiento de una ventana por si en los lectores de numeros no solo se encontraban numeros
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setHeaderText("");
                alerta.setTitle("Error en la escritura del numero");
                alerta.setContentText("El numero no debe contener caracteres especiales como : - ; _ + - * /");
            }
        } else {
            //mensaje de alerta
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Aviso");
            alert.setContentText("No hay cliente que realice la compra.");
            alert.showAndWait();
        }
    
    }
    
    /**
     * Metodo para Inicializar, el cual recupera de los dos archivos, los controladores
     * con la informacion de los productos y clientes.
     */
    public void Inicializar(){
        ControladorProducto otro = ManejoArchivos.RecuperarArchivoProducto();
        ControladorClienteMacizo otro2 = ManejoArchivos.RecuperarArchivoCliente();
        
        if ((otro != null)&&(otro2!= null)) {
            this.listaMacizo =otro2;
            this.listaProductos = otro;
           
        } else {
            listaMacizo = new ControladorClienteMacizo();
            listaProductos = new ControladorProducto();
        }
        
    }
    
    /**
     * Metodo para agregar los productos ya modificados a una ArrayList.
     * @param ProducModif Es necesario que sea el producto ya modificado sus unidades.
     */
    public void setListModifCompra(Producto ProducModif){
        this.listModifCompra.add(0,ProducModif);
    }
    
    /**
     * Metodo para recuperar la ArraList productos modificados para 
     * modificar los datos de las unidades actuales en los archivos de productos.
     * @return ArrayLis de productos que ya son modificados sus unidades.
     */
    public ArrayList<Producto> getListModifCompra() {    
        return this.listModifCompra;
    }

    /**
     * Metodo para limpiar los textField.
     */
    public void Limpiar_TexFields() {
        this.LectIDECliente.clear();
        this.LectNombreMuestra.clear();
        this.LectMartiPuntosMuestra.clear();
        this.LectIDEproductoCompra.clear();
        this.LectUnidadesProductoCompra.clear();
        this.LectMuestraPagoTotal.clear();
        this.LectMartiPuntosUsar.clear();
        this.LectImportePago.clear();
    }
    
    /**
     * Metodo para realizar compra parcial  y comprobar si este resulta darse devolviendo el valor 
     * de lo que podria se la compra de asi realizarse.
     * @param Compra El producto comprado que queremos con sus unidades.
     * @param ComunCompra El producto que tenemos en los datos y desea el comprador.
     * @return valor de la compra parcial si llega a relizarse , si no esta sera 0.
     */
    private float Compra_Parcial(ProductoComprado Compra,Producto ComunCompra){
        float CompraParcial = 0;
        if (ComunCompra.getUnidades() != 0) {
            int restantes = ComunCompra.Comprobar_Ventas_Unidades(Compra);//ver si fue posible reducir unidades
            if (restantes != ComunCompra.getUnidades()) {
                int comprados = Compra.getUnidades();//recuperado unidades se desea comprar
                CompraParcial = ComunCompra.Costo_Parcial(comprados);//lanzado el costo parcial
                return CompraParcial;
            } else {
                return CompraParcial;
            }
        } else {
            return CompraParcial;
        }
       
    }
    
    /**
     * Metodo para sumar costos parciales y calcular el costo total de la compra.
     * @param carrito Es necesario que sea pasada la compra para ver los productos y el 
     * costo parcial de las unidades que se llevaron.
     */
    private void Compra_Total(Compra carrito){
        float CompraTotal =0;
        for (int i = 0; i <carrito.getCarritoProductos().size(); i++) {
           CompraTotal += carrito.getCarritoProductos().get(i).getCostoParcial();
        }
        this.carrito.getPuntosGeneradosCompra().Suma_ValorMartipuntos(CompraTotal);
        this.carrito.setTotalCompra(CompraTotal);
        this.LectMuestraPagoTotal.setText(String.valueOf(CompraTotal));
    }
    
   
    
    /**
     * Metodo que cuando se cierre la ventana de compra y regrese al menu principal.
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

            Stage myStage = (Stage) this.BotonRegresa.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Metodo para imprimiendo el ticket con los datos dados.
     * @param Comprador El que cliente que realizo la compra.
     * @param carrito La compra con los articulos que compro.
     * @param Cambio El cambio que resulto de su pago.
     * @param Importe El importe que realizo para pagar.
     * @param PagoTotal El pago por el total de la compra.
     */
   private void Imprimir_Ticket(ClienteMacizo Comprador,Compra carrito,float Cambio,float PagoTotal,float Importe){
       System.out.println("***Ticket de compra***");
       System.out.println(Comprador.getIDE()+" "+Comprador.getNombre());
       for(int i=0;i<carrito.getCarritoProductos().size();i++){
           System.out.print("Producto: "+carrito.getCarritoProductos().get(i).getNombreProducto());
           System.out.print(" Catidad: "+carrito.getCarritoProductos().get(i).getUnidades());
           System.out.println(" Costo parcial: $"+carrito.getCarritoProductos().get(i).getCostoParcial());
       }
       System.out.println("MartiPuntos obtenidos en compra: "+carrito.getPuntosGeneradosCompra().getValor());
       System.out.println("Pago total: $"+PagoTotal);
       System.out.println("Importe: $"+Importe);
       System.out.println("Cambio: $"+Cambio);
    }
   
}
