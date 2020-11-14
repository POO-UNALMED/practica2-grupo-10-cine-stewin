package uiMain.opcionesConsola;

import gestorAplicacion.master.Funcion;
import gestorAplicacion.usuario.Cliente;

import java.util.Vector;

public class ConsultarReservas implements OpcionConsola{
    Cliente clienteActual = new Cliente();

    @Override
    public void ejecutar() {
        clienteActual = Cliente.getClienteActual();
        System.out.println(separador);
        System.out.println(clienteActual.consultarReservas());
        //Aca----------------
        System.out.println(separador);

        //añadiendo codigo desde aca Alejandro
        //System.out.println(mensajeVolver);
        System.out.println("1. Cambiar Reserva\n"+"2. Volver\n" +"3. Salir");
        //Acaaaaaaa------------------------
        System.out.println(separador);
        System.out.print("Ingrese la opcion deseada: ");
        int opcion = dato.nextInt();

        switch(opcion){
            case 1:
                /*En caso de que el usuario no tenga reservas en su cartera, capturamos
            esa informacion y le mostramos el mensaje para volver o salir de la aplicacion*/
                if(!(empleado.comprobarSiHayReservas(clienteActual))){
                    System.out.println("          ¡¡No tienes reservas activas!!");
                    System.out.println(separador);
                    System.out.println(mensajeVolver);
                    int volver = dato.nextInt();
                    volver(volver);
                    salir.ejecutar();
                }
            /*En caso de que el usuario si tenga reservas, pero ninguna de ellas sea una,
            capturamos esa informacion y le mostramos el mensaje para volver o salir de la aplicacion*/
                if(empleado.numeroFuncionesDisponibles(clienteActual)<=0){
                    System.out.println("          !!No tienes reservas activas¡¡");
                    System.out.println(separador);
                    System.out.println(mensajeVolver);
                    int volver = dato.nextInt();
                    volver(volver);
                    salir.ejecutar();
                }
            /*En caso de que no se capture ningun estado erroneo con los dos if anteriores la aplicacion
              sigue su curso correcto*/
            /*Le mostramos al cliente todas sus reservas que tengan un estado "Activo" para poder editar
              sus puestos*/
                System.out.println(empleado.funcionesDisponibles(clienteActual));
                System.out.println(separador);
                System.out.println("Por favor ingrese el # de la reserva a cambiar:  ");
                int opcion1 = dato.nextInt();
                System.out.println(separador);
            /*Aca comprobamos que el usuario ingrese un numero que si se encuentre en el rango de funciones
              que el usuario tiene, en caso contrario, le mostraremos un mensaje para volver*/
                if(empleado.probarFuncion(opcion1, clienteActual)){
                    System.out.println("            !!Funcion no encontrada¡¡");
                    System.out.println(separador);
                    System.out.println(mensajeVolver);
                    int volver = dato.nextInt();
                    volver(volver);
                    salir.ejecutar();
            /*Este else se ejecuta cuando el usuario ha ingresado correctamente todos los datos, para poder
              cambiar sus/su asiento*/
                }else{
                    /*Le mostramos al usuario el numero de asientos que tiene, y el cual podra cambiar*/
                    System.out.println("usted cuenta con "+empleado.numeroDeAsientos(clienteActual,opcion1)+" asiento(s) para esta funcion.");
                    /*Este metodo sera el encargado de vaciar los asientos(Ponerlos en su estado original, cuando estaban al azar)*/
                    empleado.vaciarReserva(clienteActual.cartera.get(opcion1).getFuncion(),clienteActual.cartera.get(opcion1).getAsientosElegidos());
                    System.out.println(separador);
                    /*Mostramos los asientos al usuario para que pueda elegir de nuevo*/
                    System.out.println(clienteActual.cartera.get(opcion1).getFuncion().mostrarPuestos());
                    System.out.println(separador);
                    /*Aca pedimos los nuevos asientos que el usuario elija, tantos como asientos ya tenia en la reserva*/
                    Vector<Integer> puestosNuevos = new Vector<>();
                    for(int i=0; i<empleado.numeroDeAsientos(clienteActual,opcion1); i++){
                        System.out.print("Ingrese el # de asiento que desea: ");
                        int numeroPuesto = dato.nextInt();
                        puestosNuevos.add(numeroPuesto);
                    }
                    /*Llenamos los nuevos puestos en la funcion, para que la proxima vez que se llenen
                      ya esten llenos*/
                    empleado.cambiarPuestos(clienteActual.cartera.get(opcion1).getFuncion(),puestosNuevos);
                    /*Aca cambiamos los asientos viejos por los nuevos en los datos del cliente*/
                    /*Cambiamos los puestos que tenia por los nuevos***********/
                    //clienteActual.cartera.get(opcion1).setAsientosElegidos(puestosNuevos);///////
                    empleado.cambiarNuevosAsientos(clienteActual,opcion1,puestosNuevos);
                    System.out.println(separador);
                    /*Mostramos la nueva pantalla con los nuevos asientos reservados*******/
                    System.out.println(clienteActual.cartera.get(opcion1).getFuncion().mostrarPuestos());
                    System.out.println(separador);
                    System.out.println("      !!Cambios hechos satisfactoriamente¡¡");
                    System.out.println(separador);
                    System.out.println(mensajeVolver);
                    int volver = dato.nextInt();
                    volver(volver);
                    break;
                }
            case 2:
                System.out.println(separador);
                ingresoUsuario.ejecutar();
                break;
            case 3:
                salir.ejecutar();
                break;
        }
    }

    @Override
    public void volver(int opcion) {
        if(opcion == 1){
            System.out.println(separador);
            ingresoUsuario.ejecutar();
            System.exit(0);
        }else{
            salir.ejecutar();
        }
    }
}
