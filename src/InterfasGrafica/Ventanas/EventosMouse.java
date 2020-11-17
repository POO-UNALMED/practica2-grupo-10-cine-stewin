package InterfasGrafica.Ventanas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

class EventosMouse implements EventHandler<MouseEvent> {
	
	// Esta clase por ahora no se está utiliando.

    public void handle(MouseEvent event) {
   		Object control = event.getSource();
   		if(control instanceof Text) {
    			
   		}
   	}
}
