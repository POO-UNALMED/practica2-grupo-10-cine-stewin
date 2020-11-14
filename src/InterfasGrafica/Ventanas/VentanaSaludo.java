package InterfasGrafica.Ventanas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class VentanaSaludo {

    private Scene escenaSaludo;
    Button ingreso = new Button("Ingresar al sistema.");
    TextField bienvebida = new TextField("bienvenidos.");
    TextField imagenes = new TextField("Aqui van las imagenes");
    TextField hojadevida = new TextField("Aqui van las hojas de vida");

    public VentanaSaludo(){

        BorderPane p1 = new BorderPane();
        BorderPane p2 = new BorderPane();
        GridPane p3 = new GridPane();
        GridPane p4 = new GridPane();
        GridPane p5 = new GridPane();
        GridPane p6 = new GridPane();

        p3.setPadding(new Insets(10,10,10,10));
        p3.setAlignment(Pos.CENTER);
        p3.add(bienvebida,0,0);

        p4.setPadding(new Insets(10,10,10,10));
        p4.setAlignment(Pos.CENTER);
        p4.add(ingreso,0,0);

        p5.setPadding(new Insets(10,10,10,10));
        p5.setAlignment(Pos.CENTER);
        p5.add(hojadevida,0,0);

        p6.setPadding(new Insets(10,10,10,10));
        p6.setAlignment(Pos.CENTER);
        p6.add(imagenes,0,0);

        p1.setTop(p3);
        p1.setCenter(p4);
        p2.setTop(p5);
        p2.setCenter(p6);
        BorderPane escena = new BorderPane();
        escena.setLeft(p1);
        escena.setRight(p2);

        //eventos
        ingreso.setOnAction(new ManejoDeEventos());


        escenaSaludo = new Scene(escena,500,500);
    }

    public Scene getEscenaSaludo(){
        return escenaSaludo;
    }

}
