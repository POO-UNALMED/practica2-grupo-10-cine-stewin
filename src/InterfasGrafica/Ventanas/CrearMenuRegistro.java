package InterfasGrafica.Ventanas;

import InterfasGrafica.FieldPanel;
import ManejoExcepciones.invalidDataType_Exception;
import baseDatos.BaseDeDatos;
import gestorAplicacion.master.Empleado;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*Esta sera la ventana encargada de mostrarse al intentar registrarse*/
public class CrearMenuRegistro extends VentanaGenerica {
    public static void showing(String title) {
        /*Creamos el stage*/
        Stage ventanaRegistro = new Stage();

        /*Esto es para que no puedan interactuar con otras ventanas mientras este esta abierta*/
        ventanaRegistro.initModality(Modality.APPLICATION_MODAL);

        /*Le pasamos el titulo*/
        ventanaRegistro.setTitle(title);

        /*Definimos el tamaño minimo de la ventana*/
        ventanaRegistro.setMinWidth(516);
        ventanaRegistro.setMinHeight(446);

        /*Texto para pedir los datos*/
        Label lbl = new Label("Por favor ingrese sus datos");
        lbl.setTextFill(Color.WHITE);
        lbl.setFont(new Font("Arial Black", 15));

        /*Boton para poder cerrar la ventana y lo ponemos en su posicion*/
        Button cancelar = new Button("Cancelar");
        cancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ventanaRegistro.close();
            }
        });

        /*Definimos el panel donde tendremos los datos*/
        BorderPane panelPrincipal = new BorderPane();

        /*Ponemos el titulo en la parte de arriba, le damos una margen y lo centramos*/
        panelPrincipal.setTop(lbl);
        BorderPane.setMargin(lbl, new Insets(30));
        BorderPane.setAlignment(lbl, Pos.CENTER);

        /*Hacemos que la pantalla sea negra*/
        panelPrincipal.setStyle("-fx-background-color: BLACK");

        /*Con FieldPanel vamos a pedir todos nuestros datos*/
        String[] criterios = new String[]{"ID","Identificacion", "Nombre", "Correo", "Direccion"};
        String[] valores = new String[]{String.valueOf(BaseDeDatos.getClientes().size()),"", "", "", "", ""};
        boolean[] habilitados = new boolean[]{false};
        FieldPanel probando = new FieldPanel("Criterios", criterios, "Valores", valores, habilitados);
        probando.setStyle("-fx-background-color: BLACK");

        /*hora pasamos el panel lo centramos y demas*/
        GridPane poniendoCentro = new GridPane();
        poniendoCentro.add(probando, 0, 0);
        poniendoCentro.add(cancelar, 0, 1);
        poniendoCentro.setVgap(8);
        poniendoCentro.setHgap(8);
        poniendoCentro.setAlignment(Pos.TOP_CENTER);

        panelPrincipal.setCenter(poniendoCentro);

        /*Boton para aceptar y enviar la informacion*/
        probando.aceptar.setMinWidth(62);
        /*Evento del boton aceptar*/
        probando.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    probando.GuardarDatos();
                } catch (Exception e) {
                	if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                    	Alert alert = new Alert(Alert.AlertType.WARNING);
                		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                		alert.setHeaderText(null);
                		alert.setContentText(e.getMessage());
                		alert.showAndWait();
                    }
                	else if(e.getMessage().equals("Identificacion Invalida: " + e.getLocalizedMessage())) {
                		Alert alert = new Alert(Alert.AlertType.WARNING);
                		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                		alert.setHeaderText(null);
                		alert.setContentText(e.getMessage());
                		alert.showAndWait();
                	}
                }
                /*Comprobamos que el usuario no este registrado en la base de datos y dependiendo del caso, imprimimos el mensaje*/
                try {
                	if (empleado.comprobarRegistro(Integer.parseInt(valores[1]))) {
                        ventanaRegistro.close();
                        /*Si el usuario ya se encuentra registrado cerramos la ventana de registro y se lo avisamos*/
                        VentanaInformacion.showing("Informacion", "Usuario ya registrado", "Aceptar", 400, 100);
                    } else {
                        /*Si el usuario no se encuentra registrado le mostramos un mensaje sobre su registro correcto*/
                        Empleado.registarCliente(Integer.parseInt(valores[1]), valores[2], valores[3], valores[4]);
                        ventanaRegistro.close();
                        VentanaInformacion.showing("Informacion", "Usuario registrado correctamente", "Aceptar", 400, 100);
                    }
                } catch(Exception e) {
                	
                	Alert alert = new Alert(Alert.AlertType.WARNING);
            		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
            		alert.setHeaderText(null);
            		alert.setContentText("Identificacion Invalida: " + e.getMessage());
            		alert.showAndWait();
                }
                
            }
        });

        /*Creamos la escena que mostraremos en el stage y pasamos el contenido*/
        Scene sn = new Scene(panelPrincipal, 500, 400);
        ventanaRegistro.setScene(sn);
        ventanaRegistro.showAndWait();
    }
}
