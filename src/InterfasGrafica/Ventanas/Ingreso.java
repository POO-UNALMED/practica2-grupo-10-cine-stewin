package InterfasGrafica.Ventanas;

import InterfasGrafica.FieldPanel;
import ManejoExcepciones.ErrorAplicacion;
import ManejoExcepciones.invalidDataType_Exception;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Ingreso extends VentanaGenerica{
    protected static final String Exception = null;

	public static void showing(Stage stage){
        pantallaInicio = stage;
        /*/Se crean los diferentes elementos con los que se van a trabajar*/
        Stage ventanaIngreso = new Stage();
        BorderPane panelPrincipal = new BorderPane();
        Label titulo = new Label("Por favor ingrese su identificacion");
        Button cancelar = new Button("Cancelar");

        /*Damos los estilos que usaremos*/
        panelPrincipal.setStyle("-fx-background-color: BLACK");
        titulo.setFont(new Font("Arial Black",15));
        titulo.setTextFill(Color.WHITE);

        /*Ponemos el titulo, le damos margen y lo centramos*/
        panelPrincipal.setTop(titulo);
        BorderPane.setMargin(titulo,new Insets(5,0,15,0));
        BorderPane.setAlignment(titulo, Pos.CENTER);

        /*Tamanio no cambiante*/
        ventanaIngreso.setMinWidth(416);
        ventanaIngreso.setMinHeight(196);

        /*Esto es para que no puedan interactuar con otras ventanas mientras este esta abierta*/
        ventanaIngreso.initModality(Modality.APPLICATION_MODAL);

        /*Usamos FieldPanel para pedir la informacion y trabajar con ella*/
        String[] criterios = new String[] {"Identificacion"};
        String[] valores = new String[] {""};
        boolean[] habilitados = new boolean[] {};
        FieldPanel fieldPane = new FieldPanel("Criterios",criterios,"Valores",valores,habilitados);
        fieldPane.setStyle("-fx-background-color: BLACK");

        /*Un gridpane para poder centrar nuestro FieldPane*/
        GridPane auxiliar = new GridPane();
        auxiliar.add(fieldPane,0,0);
        auxiliar.add(cancelar,0,1);
        auxiliar.setVgap(8);
        auxiliar.setHgap(8);
        auxiliar.setAlignment(Pos.TOP_CENTER);
        panelPrincipal.setCenter(auxiliar);

        /*Creamos la escena*/
        Scene escena = new Scene(panelPrincipal,400,130);
        ventanaIngreso.setScene(escena);
        ventanaIngreso.show();

        /*Evento que me define que pasa cuando se oprime el boton ingresar*/
        fieldPane.aceptar.setMinWidth(62);
        fieldPane.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    fieldPane.GuardarDatos();
                } catch ( Exception e ) {
                    if(e.getMessage() == "Por favor llenar todos los espacios") {
                    	Alert alert = new Alert(Alert.AlertType.WARNING);
                		alert.setTitle(e.getLocalizedMessage());
                		alert.setHeaderText(null);
                		alert.setContentText(e.getMessage());
                		alert.showAndWait();
                    }
                    
                }
                /*Se comprueba la identificacion de la persona y dependiendo de eso, se dedice a que ventana se llevara*/
                try {
                	if(empleado.comprobarRegistro(Integer.parseInt(valores[0]))){
                        /*En caso de que la identidad sea confirmada, cerramos los stage actuales*/
                        ventanaIngreso.close();
                        stage.close();
                        try {
                            /*Y lo llevamos a la ventana de usuario*/
                            pantallaUsuario.start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        /*En caso de que no se pueda confirmar su identidad, le mostramos una ventan diciendo que su identidad
                          es incorrecta*/
                        VentanaInformacion.showing("Informacion","Identificacion incorrecta","Aceptar",400,100);
                        ventanaIngreso.close();
                    }
                } catch (Exception e) {
                	if(!(e.getLocalizedMessage().equals("For input string: " + (char)34 + (char)34))) {
                		invalidDataType_Exception a = new invalidDataType_Exception("Identificacion Invalida");
                    	Alert alert = new Alert(Alert.AlertType.WARNING);
                		alert.setTitle(a.getMensaje_Error());
                		alert.setHeaderText(null);
                		alert.setContentText("Identificacion Invalida: " + e.getLocalizedMessage());
                		alert.showAndWait();
                	}
                	
                }
            }
        });

        /*Evento de cancelar, que me cierra la ventana*/
        cancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ventanaIngreso.close();
            }
        });
    }
}
