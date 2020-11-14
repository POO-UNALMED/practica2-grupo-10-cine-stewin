package uiMain.opcionesConsola;
import gestorAplicacion.master.Empleado;
import uiMain.Inicio;

/*Esta clase es la encargada de mostrar la pantalla para registrar usuarios*/
public class UsuarioNoRegistrado implements OpcionConsola{
    int opcion;

    @Override
    public void ejecutar() {
        System.out.println(separador);
        System.out.print("Por favor ingrese su numero de identificacion: ");
        int identificacion = dato.nextInt();
        dato.nextLine();
        /*Comprobamos que el numero de identificacion no exista ya en la base de datos*/
        if((empleado.comprobarIdentificacion(identificacion))){
            System.out.println(separador);
            System.out.println("               El usuario ya existe");
            System.out.println(separador);
            System.out.println(mensajeVolver);
            /*Pedimos la opcion, ejecutamos el metodo volver y hacemos un sistemas.exit
            para que no siga ejecutando lo de abajo*/
            int volver = dato.nextInt();
            volver(volver);
            System.exit(0);
        }
        /*Si el if anterior no se ejecuta significa que el usuario no esta en la base de
          datos por lo cual le permite seguir llenando sus datos*/
        System.out.print("Por favor ingrese su nombre: ");
        String nombre = dato.nextLine();
        System.out.print("Por favor ingrese su correo: ");
        String correo = dato.nextLine();
        System.out.print("Por favor ingrese su direccion: ");
        String direccion = dato.nextLine();
        System.out.println(separador);
        /*Aca registramos al usuario, le asosiamos su cuenta bancaria y de puntos
          respectiva*/
        Empleado.registarCliente(identificacion, nombre, correo, direccion);
        System.out.println("       Usuario registrado satisfactoriamente");
        System.out.println(separador);
        System.out.println(mensajeVolver);
        opcion = dato.nextInt();
        /*Al terminar de registrar al usuario tendremos la opcion de volver al menu
          anterior o salir del programa*/
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
