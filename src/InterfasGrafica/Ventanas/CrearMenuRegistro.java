package InterfasGrafica.Ventanas;

import InterfasGrafica.interfasGrafica;
import baseDatos.BaseDeDatos;
import gestorAplicacion.master.Empleado;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import static javafx.scene.control.ButtonType.*;

public class CrearMenuRegistro {
    public static void showing(String title){
        //Almacenaremos los labeles aca para darles un poco de estilo posteriormente
        Vector<Label> labeles = new Vector<Label>();

        //Creamos el stage
        Stage ventanaRegistro = new Stage();

        //Esto es para
        ventanaRegistro.initModality(Modality.APPLICATION_MODAL);

        //Le pasamos el titulo
        ventanaRegistro.setTitle(title);

        //Definimos el tamaño minimo de la ventana
        ventanaRegistro.setMinWidth(516);
        ventanaRegistro.setMinHeight(446);

        //Texto para pedir los datos
        Label lbl = new Label("Por favor ingrese sus datos");

        //Boton para poder cerrar la aplicacion
        Button cancelar = new Button("Canelar");
        cancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ventanaRegistro.close();
            }
        });

        //Creamos los textField donde pediremos los datos
        TextField identificacionEntra = new TextField();
        TextField nombreEntra = new TextField();
        TextField correoEntra = new TextField();
        TextField direccionEntra = new TextField();

        //Boton para aceptar y enviar la informacion
        Button aceptar = new Button("Aceptar");
        aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        });

        //Definimos el panel donde tendremos los datos
        BorderPane panelPrincipal = new BorderPane();

        //Ponemos el titulo en la parte de arriba, le damos una margen y lo centramos
        panelPrincipal.setTop(lbl);
        BorderPane.setMargin(lbl,new Insets(30));
        BorderPane.setAlignment(lbl,Pos.CENTER);

        //Creamoe el panel del medio donde pediremos los datos
        GridPane panelMedio = new GridPane();


        //Creamos los label para mostrar la informacion que necesitamos
        Label identificacion = new Label("Identificacion: ");
        Label nombre = new Label("Nombre: ");
        Label correo = new Label("Correo: ");
        Label direccion = new Label("Direccion: ");


        //Hacemos que la pantalla sea negra
        panelPrincipal.setStyle("-fx-background-color: BLACK");

        //Agregamos los labeles al vector
        labeles.add(lbl);
        labeles.add(identificacion);
        labeles.add(nombre);
        labeles.add(correo);
        labeles.add(direccion);

        //Les damos un poco de estilo
        for(Label elemento: labeles){
            elemento.setTextFill(Color.WHITE);
            elemento.setFont(new Font("Arial Black",15));
            //elemento.setEffect(new Glow(1.7f));

        }

        //Agregamos los label y textField previamente creados, tambien centramos el panel
        panelMedio.add(identificacion,0,0);
        panelMedio.add(nombre,0,1);
        panelMedio.add(correo,0,2);
        panelMedio.add(direccion,0,3);
        panelMedio.add(aceptar,0,4);

        panelMedio.add(identificacionEntra,1,0);
        identificacionEntra.setAlignment(Pos.CENTER);
        panelMedio.add(nombreEntra,1,1);
        nombreEntra.setAlignment(Pos.CENTER);
        panelMedio.add(correoEntra,1,2);
        correoEntra.setAlignment(Pos.CENTER);
        panelMedio.add(direccionEntra,1,3);
        direccionEntra.setAlignment(Pos.CENTER);
        panelMedio.add(cancelar,1,4);

        //

        //panelMedio.add(cancelar,1,4);


        //Definimos los margenes
        panelMedio.setVgap(8);
        panelMedio.setHgap(8);

        panelMedio.setAlignment(Pos.TOP_CENTER);
        /*Revisar esto de aca para despues --------------------------------------*/
        panelPrincipal.setCenter(panelMedio);

        //Creamos la escena que mostraremos en el stage y pasamos el contenido
        Scene sn = new Scene(panelPrincipal,500,400);
        ventanaRegistro.setScene(sn);
        ventanaRegistro.showAndWait();
    }
}
