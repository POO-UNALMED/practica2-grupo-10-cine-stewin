package InterfasGrafica.Ventanas;

import gestorAplicacion.master.Empleado;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Ingreso extends VentanaGenerica{
    public static void showing(Stage stage){
        //Se crean los diferentes elementos con los que se van a trabajar
        Stage ventanaIngreso = new Stage();
        BorderPane panelPrincipal = new BorderPane();
        GridPane panelSegundario = new GridPane();
        Label identificacion = new Label("Identificacion: ");
        Label titulo = new Label("Por favor ingrese su identificacion");
        TextField identificacionEntra = new TextField();
        Button ingreso = new Button("Entrar");
        Button cancelar = new Button("Cancelar");
        GridPane panelInferior = new GridPane();

        //Damos los estilos que usaremos
        identificacionEntra.setAlignment(Pos.CENTER);
        panelPrincipal.setStyle("-fx-background-color: BLACK");
        identificacion.setFont(new Font("Arial Black",15));
        identificacion.setTextFill(Color.WHITE);
        titulo.setFont(new Font("Arial Black",15));
        titulo.setTextFill(Color.WHITE);

        //Ingresamos los botones al panel inferior y los acomodamos

        panelInferior.add(ingreso,0,0);
        panelInferior.add(cancelar,1,0);
        panelInferior.setAlignment(Pos.TOP_CENTER);
        BorderPane.setMargin(panelInferior,new Insets(0,0,30,0));
        panelInferior.setHgap(10);

        //Ponemos el titulo, le damos margen y lo centramos
        panelPrincipal.setTop(titulo);
        BorderPane.setMargin(titulo,new Insets(5,0,-5,0));
        BorderPane.setAlignment(titulo, Pos.CENTER);
        panelPrincipal.setBottom(panelInferior);

        //Ponemos los componentes del panel segundario
        panelSegundario.add(identificacion,0,0);
        panelSegundario.add(identificacionEntra,1,0);
        panelSegundario.setAlignment(Pos.CENTER);
        panelPrincipal.setCenter(panelSegundario);

        //Tamaño no cambiante
        ventanaIngreso.setMinWidth(416);
        ventanaIngreso.setMinHeight(176);

        //Esto es para que no puedan interactuar con otras ventanas mientras este esta abierta
        ventanaIngreso.initModality(Modality.APPLICATION_MODAL);

        //Creamos la escena
        Scene escena = new Scene(panelPrincipal,400,130);
        ventanaIngreso.setScene(escena);
        ventanaIngreso.show();

        //Evento que me define que pasa cuando se oprime el boton ingresar
        ingreso.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Se comprueba la identificacion de la persona y dependiendo de eso, se dedice a que ventana se llevara
                if(empleado.comprobarRegistro(Integer.parseInt(identificacionEntra.getText()))){
                    ventanaIngreso.close();
                    try {
                        pantallaInicio.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Debo llemar al siguiente menu ;'v
                }else{
                    VentanaInformacion.showing("Informacion","Identificacion incorrecta","Aceptar");
                    ventanaIngreso.close();
                }
            }
        });

        cancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ventanaIngreso.close();
            }
        });



    }
}
