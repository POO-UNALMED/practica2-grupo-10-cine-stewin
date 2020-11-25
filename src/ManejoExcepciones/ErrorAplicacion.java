package ManejoExcepciones;

public class ErrorAplicacion extends Exception {
	
	public static final long serialVersionUID = 700L;
	
	private static String mensaje_Error = "Manejo de Errores de la Aplicacion: Cine Stewin";

	public ErrorAplicacion(String mensaje) {
		super(mensaje);
	}

	public String getMensaje_Error() {
		return mensaje_Error;
	}
}
