package InterfasGrafica.Ventanas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class VentanaInicial {

    private Scene escenaSaludo;
    public static Insets padding = new Insets(10,10,10,10);
    static ArrayList<String> info_desarrolladores = new ArrayList<String>();
    private int contador = 0;
    Text txtp5 = new Text();
    
    static {
    	// Agregamos la hoja de vida de cada integrante del grupo a la LinkedList info_desarrolladores.
    	info_desarrolladores.add("Informacion de los Desarrolladores" + "\n" + "                 - Click Aqui -");
    	info_desarrolladores.add("Desarrollador: Amilder Ospina." + "\n" + "Edad: 20." + "\n" + "Ocupacion: Estudiante de Ingenieria de Sistemas" + "\n" + "Institucion: Universidad Nacional de Colombia");
    	info_desarrolladores.add("Desarrollador: Andres Molano." + "\n" + "Edad: 20." + "\n" + "Ocupacion: Estudiante de Ingenieria de Sistemas" + "\n" + "Institucion: Universidad Nacional de Colombia");
    	info_desarrolladores.add("Desarrollador: Alejandro Sepulveda." + "\n" + "Edad: 20." + "\n" + "Ocupacion: Estudiante de Ingenieria de Sistemas" + "\n" + "Institucion: Universidad Nacional de Colombia");
    	info_desarrolladores.add("Desarrollador: Gelier Moreno." + "\n" + "Edad: 23." + "\n" + "Ocupacion: Estudiante de Ingenieria de Sistemas" + "\n" + "Institucion: Universidad Nacional de Colombia");
    }
    
    

    public VentanaInicial(){
    
    	
    	// Creacion de las zonas (VBox) p1, p2.
    	VBox p1 = new VBox();
   		p1.setPadding(padding);
    
   		VBox p2 = new VBox();
   		p2.setPadding(padding);
   		
   		// Creacion de las zonas (BorderPane) p3 - p6.
   		BorderPane p3 = new BorderPane();
		BorderPane p4 = new BorderPane();
		BorderPane p5 = new BorderPane();
		BorderPane p6 = new BorderPane();

		
		// Creacion de la barra menu y sus componentes.
    	MenuBar barra_menu = new MenuBar();
    	SeparatorMenuItem separator = new SeparatorMenuItem();
    	Menu menu_inicio = new Menu("Inicio");
    	MenuItem descripcion = new MenuItem("Descripcion");
    	MenuItem salir = new MenuItem("Salir");
    	barra_menu.getMenus().add(menu_inicio);
    	menu_inicio.getItems().addAll(descripcion, separator, salir);
    	
    	// Agrgamos el menu a la escena.
    	// El layout principal de la escena.
    	VBox escena = new VBox(barra_menu);
    	HBox base = new HBox();
    	escena.getChildren().add(base);
    	
		// Esto mantiene de forma consistente los margenes, etc.
		VBox.setVgrow(p4, Priority.ALWAYS);
		VBox.setVgrow(p5, Priority.ALWAYS);
		VBox.setVgrow(p6, Priority.ALWAYS);
		VBox.setVgrow(base, Priority.ALWAYS);
		
		// Agregamos p3 - p6 a tanto p1 como p2.
		p1.getChildren().addAll(p3,p4);
		p2.getChildren().addAll(p5,p6);
		
		// Esto mantiene de forma consistente los margenes, etc.
		HBox.setHgrow(p1, Priority.ALWAYS);
		HBox.setHgrow(p2, Priority.ALWAYS);
		
		// Agregamos p1 y p2 a la escena.
		base.getChildren().addAll(p1,p2);
		
		// Seccion P3
		p3.setStyle("-fx-border-color: black");
		p3.setPadding(padding);
		p3.setCenter(new Text("Bienvenidos a la Aplicacion" + "\n" +  " de Escritorio de Cine Stewin"));
		
				
		// Seccion P4 
		p4.setStyle("-fx-border-color: red");
		
		// Seccion P5 
		p5.setStyle("-fx-border-color: blue");
		p5.setPadding(padding);
		txtp5.setText(info_desarrolladores.get(0));
		p5.setCenter(txtp5);
		txtp5.setOnMouseClicked( new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if(contador == 5) {
					contador = 0;
					txtp5.setText(info_desarrolladores.get(contador));
					contador ++;
					p5.setCenter(txtp5);
				}
				else {
					txtp5.setText(info_desarrolladores.get(contador));
					p5.setCenter(txtp5);
					contador ++;
				}
			}
		});
		
		
		// Seccion P6
		p6.setStyle("-fx-border-color: green");
		


        escenaSaludo = new Scene(escena,700,700);
    }

    public Scene getEscenaSaludo(){
        return escenaSaludo;
    }

}
