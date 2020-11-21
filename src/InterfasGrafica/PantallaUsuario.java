package InterfasGrafica;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PantallaUsuario extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane panelPrincipal = new BorderPane();
        panelPrincipal.setStyle("-fx-background-color: BLACK");
        Scene escenaPrincipal = new Scene(panelPrincipal,700,700);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(escenaPrincipal);
        primaryStage.show();
    }
}
