package InterfasGrafica;

import InterfasGrafica.Ventanas.CrearMenuRegistro;
import InterfasGrafica.Ventanas.VentanaGenerica;
import InterfasGrafica.Ventanas.VentanaInformacion;
import baseDatos.Escribir;
import gestorAplicacion.usuario.Cliente;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.text.html.Option;
import java.util.Optional;

//Esta sera la pantalla que interactuara con el usuario
public class PantallaUsuario  extends Application {
    public void init(){

    }
    public void stop(){

    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Creacion de los diferentes componentes que vamos a usar
        VBox panelPrincipal = new VBox();
        BorderPane panelEstructura = new BorderPane();
        Label titulo = new Label("Hola mundo");

        //Creamos los diferentes menus que se van a usat
        MenuBar menu =  new MenuBar();
        Menu archivo = new Menu("Archivo");
        Menu procesos = new Menu("Procesos y consultas");
        Menu ayuda = new Menu("Ayuda");
        MenuItem usuario = new MenuItem("Usuario");
        MenuItem salir = new MenuItem("Salir");
        MenuItem acercaDe = new MenuItem("Acerca de");

        /*Definir demas elementos del menu procesos*/
        //Definimos los conponentes de cada menu
        menu.getMenus().add(archivo);
        menu.getMenus().add(procesos);
        menu.getMenus().add(ayuda);
        archivo.getItems().add(usuario);
        archivo.getItems().add(salir);
        ayuda.getItems().add(acercaDe);

        //Metodos para definir estilos
        panelPrincipal.setStyle("-fx-background-color: BLACK");
        titulo.setTextFill(Color.WHITE);
        titulo.setFont(new Font("Arial Black",20));
        panelPrincipal.minHeight(700);
        panelPrincipal.minWidth(700);

        //
        titulo.setAlignment(Pos.CENTER);
        panelPrincipal.getChildren().addAll(menu,titulo);
        panelPrincipal.setAlignment(Pos.CENTER);

        //Ponemos todos los elementos en el panel principal y los acomodamos en su respectiva posicion
        Label medio = new Label("Medio");
        Label abajo = new Label("Abajo");
        panelEstructura.setTop(panelPrincipal);
        panelEstructura.setCenter(medio);
        panelEstructura.setBottom(abajo);
        BorderPane.setMargin(titulo,new Insets(5,0,10,0));
        BorderPane.setAlignment(titulo, Pos.CENTER);
        BorderPane.setAlignment(medio,Pos.CENTER);
        BorderPane.setAlignment(abajo,Pos.CENTER);



        //Metodos que me permiten ajustar las caracteristicas de primaryStage
        primaryStage.setTitle("Cliente: "+ Cliente.getClienteActual().getNombre());
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        //Creamos la escena
        //panel.getChildren().add(panelPrincipal);
        Scene escenaPrincipal = new Scene(panelEstructura,700,700);

        //Pasamos la escena al stage
        primaryStage.setScene(escenaPrincipal);
        primaryStage.show();

        /*Espacio para definir cada uno de los eventos que se produzcan*/
        salir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                VentanaGenerica.pantallaInicio.show();
            }
        });
        usuario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String usuario = Cliente.getClienteActual().toString();
                VentanaInformacion.showing("Usuario",usuario,"Aceptar",500,200);
            }
        });
        acercaDe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StringBuilder mensaje = new StringBuilder();
                mensaje.append("     DESARROLLADORES\n")
                        .append("\nSepulveda Daniel Alejandro\n")
                        .append("Ospina Amilder Stewin\n")
                        .append("Moreno Gelier Esteban\n")
                        .append("Molano Kevin Andres\n");
                String usuario = Cliente.getClienteActual().toString();
                VentanaInformacion.showing("Acerda de",mensaje.toString(),"Aceptar",500,220);
            }
        });
    }
}
