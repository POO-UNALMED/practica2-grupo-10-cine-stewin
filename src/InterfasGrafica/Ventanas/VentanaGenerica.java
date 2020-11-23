package InterfasGrafica.Ventanas;

import InterfasGrafica.PantallaInicial;
import InterfasGrafica.PantallaUsuario;
import gestorAplicacion.master.Empleado;
import javafx.stage.Stage;

public class VentanaGenerica {

    /*Esta clase nos servira para que cada una de la otras ventanas emergentes tengan esta informacion (Heredada)
      y no tengamos que crearla en cada uno*/
    public static Empleado empleado = new Empleado();
    public static PantallaUsuario pantallaUsuario = new PantallaUsuario();
    public static Stage pantallaInicio = new Stage();
}
