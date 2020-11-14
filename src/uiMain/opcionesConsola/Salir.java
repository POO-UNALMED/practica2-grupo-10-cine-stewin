package uiMain.opcionesConsola;

import baseDatos.Escribir;

/*Clase que se ejecuta cuando se decide cerrar el programa*/
public class Salir implements OpcionConsola{

    @Override
    public void ejecutar() {
        System.out.println(separador);
        System.out.println("              Se ha finalizado sesion");
        System.out.println(separador);
        /*Es importante guardar la informacion en los archivos .txt antes de salir*/
        Escribir.Escribir();
        System.exit(1);
    }
    @Override
    public void volver(int opcion) {}
}
