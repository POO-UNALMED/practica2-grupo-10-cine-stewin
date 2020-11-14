package uiMain.opcionesConsola;

import baseDatos.BaseDeDatos;
import gestorAplicacion.master.Cine;
import gestorAplicacion.master.Funcion;

/*Esta clase sera la encargada de mostrar la pantalla de inicio del programa
  para que el usuario pueda nevagar a la misma*/
public class PantallaInicio implements OpcionConsola{
    StringBuilder mensaje = new StringBuilder();
    {
        mensaje.append(separador+"\n").append("        ¡¡Bienvenido a Cine STEWIN!!\n")
                .append(separador+ "\n").append("1. Usuario registrado\n")
                .append("2. Usuario no registrado\n").append("3. Autores\n")
                .append("4. Descripcion\n").append("5. Salir\n")
                .append(separador);
    }
    @Override
    public void ejecutar() {
        System.out.println(mensaje);
        datosInicio.ejecutar();
    }
    @Override
    public void volver(int opcion) {}
}
