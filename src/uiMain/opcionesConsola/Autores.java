package uiMain.opcionesConsola;

import uiMain.Inicio;

/*Clase encargada de mostrar una pantalla con los miembros del grupo*/
public class Autores implements OpcionConsola{
    //Que esta pasandaaaaaaa
    int opcion;
    StringBuilder mensaje = new StringBuilder();
    {
        mensaje.append(separador + "\n")
                .append("             Proyecto realizado por\n")
                .append("           ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n")
                .append("Molano Gamarra Kevin Andres\n")
                .append("Moreno Gómez Gelier Esteban\n")
                .append("Ospina Tobon Amilder Stewin\n")
                .append("Sepulveda Ochoa Daniel Alejandro\n")
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
            System.exit(0);
        }else{
            salir.ejecutar();
        }
    }
}
