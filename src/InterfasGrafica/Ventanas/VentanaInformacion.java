package InterfasGrafica.Ventanas;

import gestorAplicacion.master.Empleado;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VentanaInformacion {
    public static void showing(String title, String mensaje, String nombreBoton){
        //Se crea el estague, Pane, Label y el boton
        Stage ventanaInformacion = new Stage();
        ventanaInformacion.initModality(Modality.APPLICATION_MODAL);
        BorderPane panelPrincipal = new BorderPane();
        Label titulo = new Label(mensaje);
        Button boton = new Button(nombreBoton);

        //El estilo negro no puede faltar
        panelPrincipal.setStyle("-fx-background-color: BLACK");

        //Le damos estilo
        titulo.setTextFill(Color.WHITE);
        titulo.setFont(new Font("Arial Black",15));

        //Pasamos el titulo al panel en la parte de arriba
        panelPrincipal.setTop(titulo);
        panelPrincipal.setCenter(boton);

        //Los ponemos en su posicion
        BorderPane.setMargin(titulo,new Insets(15));
        BorderPane.setAlignment(titulo, Pos.CENTER);
        BorderPane.setAlignment(boton, Pos.TOP_CENTER);

        //Pasamos el titulo
        ventanaInformacion.setTitle(title);

        //Se crea la escena
        Scene escena = new Scene(panelPrincipal,400,100);
        ventanaInformacion.setMinWidth(416);
        ventanaInformacion.setMinHeight(146);

        //Pasamos la escena y mostramos
        ventanaInformacion.setScene(escena);
        ventanaInformacion.show();

        boton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ventanaInformacion.close();
            }
        });
    }
}
