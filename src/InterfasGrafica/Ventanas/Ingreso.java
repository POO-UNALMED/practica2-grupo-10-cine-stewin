package InterfasGrafica.Ventanas;
//105
import InterfasGrafica.FieldPanel;
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
        pantallaInicio = stage;
        //Se crean los diferentes elementos con los que se van a trabajar
        Stage ventanaIngreso = new Stage();
        BorderPane panelPrincipal = new BorderPane();
        Label titulo = new Label("Por favor ingrese su identificacion");
        Button cancelar = new Button("Cancelar");

        //Damos los estilos que usaremos
        panelPrincipal.setStyle("-fx-background-color: BLACK");
        titulo.setFont(new Font("Arial Black",15));
        titulo.setTextFill(Color.WHITE);

        //Ponemos el titulo, le damos margen y lo centramos
        panelPrincipal.setTop(titulo);
        BorderPane.setMargin(titulo,new Insets(5,0,15,0));
        BorderPane.setAlignment(titulo, Pos.CENTER);

        //Tamaño no cambiante
        ventanaIngreso.setMinWidth(416);
        ventanaIngreso.setMinHeight(196);

        //Esto es para que no puedan interactuar con otras ventanas mientras este esta abierta
        ventanaIngreso.initModality(Modality.APPLICATION_MODAL);

        //Usamos FieldPanel para pedir la informacion y trabajar con ella
        String[] criterios = new String[] {"Identificacion"};
        String[] valores = new String[] {""};
        boolean[] habilitados = new boolean[] {};
        FieldPanel fieldPane = new FieldPanel("Criterios",criterios,"Valores",valores,habilitados);
        fieldPane.setStyle("-fx-background-color: BLACK");

        //Un gridpane para poder centrar nuestro FieldPane
        GridPane auxiliar = new GridPane();
        auxiliar.add(fieldPane,0,0);
        auxiliar.add(cancelar,0,1);
        auxiliar.setVgap(8);
        auxiliar.setHgap(8);
        auxiliar.setAlignment(Pos.TOP_CENTER);
        panelPrincipal.setCenter(auxiliar);

        //Creamos la escena
        Scene escena = new Scene(panelPrincipal,400,130);
        ventanaIngreso.setScene(escena);
        ventanaIngreso.show();

        //Evento que me define que pasa cuando se oprime el boton ingresar
        fieldPane.aceptar.setMinWidth(62);
        fieldPane.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    fieldPane.GuardarDatos();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Se comprueba la identificacion de la persona y dependiendo de eso, se dedice a que ventana se llevara
                if(empleado.comprobarRegistro(Integer.parseInt(valores[0]))){
                    ventanaIngreso.close();
                    stage.close();
                    try {
                        pantallaUsuario.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    VentanaInformacion.showing("Informacion","Identificacion incorrecta","Aceptar",400,100);
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
