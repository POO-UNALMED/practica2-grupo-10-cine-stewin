package InterfasGrafica;

import InterfasGrafica.Ventanas.VentanaSaludo;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class interfasGrafica extends Application{



    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cine Stewin");
        primaryStage.setScene(new VentanaSaludo().getEscenaSaludo());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}