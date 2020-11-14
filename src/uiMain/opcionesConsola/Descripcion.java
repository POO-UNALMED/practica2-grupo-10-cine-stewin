package uiMain.opcionesConsola;

import uiMain.Inicio;

/*Clase encargada de mostrar la pantalla de la descripcion del proyecto*/
public class Descripcion implements OpcionConsola{
    StringBuilder mensaje = new StringBuilder();
    int opcion;
    {
        mensaje.append(separador+ "\n")
                .append("                 Cine STEWIN\n")
                .append("               ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯"+"\n")
                .append(" Bienvendios! esta aplicacion le permitira hacer \n")
                .append(" una gestion completa de los servicios que presta\n")
                .append(" un cine a travez de las diferentes opciones que \n")
                .append(" se emplean durante su uso.\n")
                .append(separador + "\n").append(mensajeVolver);
    }
    @Override
    public void ejecutar() {
        System.out.println(mensaje.toString());
        opcion = dato.nextInt();
        volver(opcion);
    }

    @Override
    public void volver(int opcion) {
        if(opcion == 1){
            Inicio.main(null);
            System.exit(1);
        }else{
            salir.ejecutar();
        }
    }
}
