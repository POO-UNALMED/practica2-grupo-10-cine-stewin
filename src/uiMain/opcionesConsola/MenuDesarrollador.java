package uiMain.opcionesConsola;

/*Clase encargada de ajustar/editar informacion para que el profesor pueda
  ver como se comporta el programa con los cambios*/
public class MenuDesarrollador implements OpcionConsola{
    StringBuilder mensaje = new StringBuilder();
    int opcion;
    {
        mensaje.append("1. Añadir funcion\n");
        mensaje.delete(mensaje.length() -1,mensaje.length());
    }
    @Override
    public void ejecutar() {
        System.out.println(separador);
        System.out.println(mensaje);
        System.out.println(separador);
        System.out.print("Ingrese la opcion deseada: ");
        opcion = dato.nextInt();
        dato.nextLine();
        System.out.println(separador);
        switch(opcion) {
            case 1:
                //registrarCine.ejecutar();
                break;
        }
    }

    @Override
    public void volver(int opcion) {

    }
}
