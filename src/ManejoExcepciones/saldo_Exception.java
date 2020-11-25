package ManejoExcepciones;
import javax.swing.*;
import gestorAplicacion.usuario.Cliente;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class saldo_Exception extends user_Exception {
	
	public static final long serialVersionUID = 701L;

	public saldo_Exception(String mensaje) {
		super(mensaje);
		
		//JOptionPane.showMessageDialog(null, mensaje, getMensaje_Error(), JOptionPane.INFORMATION_MESSAGE);
	
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(getMensaje_Error());
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
}
