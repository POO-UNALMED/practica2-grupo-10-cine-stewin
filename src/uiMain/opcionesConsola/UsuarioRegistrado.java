package uiMain.opcionesConsola;

import gestorAplicacion.usuario.Cliente;
import uiMain.Inicio;

/*Clase encargada de mostrar la pantalla para que el usuario pueda acceder*/
public class UsuarioRegistrado implements OpcionConsola{
    int opcion;
    int identificacion;
    Cliente clienteActual = new Cliente();

    @Override
    public void ejecutar() {
        System.out.println(separador);
        System.out.println("Por favor ingrese su identificacion: ");
        identificacion = dato.nextInt();
        dato.nextLine();
        System.out.println(separador);
        /*Comprobamos que el numero de identificacion se encuentre en la base de datos
          si es el caso iremos a la pantalla que me muestra todas las opciones que
          puede tener un cliente*/
        if(empleado.comprobarRegistro(identificacion)){
            //System.out.println("          Ingreso satisfactorio");
            //System.out.println("                Bienvenido");
            clienteActual = Cliente.getClienteActual();
            System.out.println("            ¡¡Ingreso satisfactorio!!");
            System.out.println("Bienvenido: " + clienteActual.getNombre());
            System.out.println(separador);
            /*Entramos a la pantalla que me mostrara las opciones disponibles para un usuario*/
            ingresoUsuario.ejecutar();
        /*Este else se ejecuta en caso que el usuario no se encuentre en la base de datos,
          lo cual muestra una pantalla para que el usuario ingrese de nuevo*/
        }else{
            System.out.println("   Usuario no identificado en la base de datos");
            System.out.println(separador);
            System.out.println(mensajeVolver);
            opcion = dato.nextInt();
            volver(opcion);
        }

    }

    @Override
    public void volver(int opcion) {
        if(opcion == 1){
            Inicio.main(null);
        }else{
            salir.ejecutar();
        }
    }
}
