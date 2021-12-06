
package ControladorInterfaces;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Irving Geyler Cupul Uc
 * @author Joar Honorio Ruiz Peraza
 * @author Didier Andrey Tec Ezquivel

 */
public class MainPuntoVenta extends Application {
    
    //metodo para proyectar la interfaz de la ventana principal
    @Override
    public void start(Stage primaryStage) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/Interfaz/VentanaPrincipal.fxml"));
            Pane ventana = (Pane) loader.load();

            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
