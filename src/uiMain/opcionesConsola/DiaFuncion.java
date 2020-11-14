package uiMain.opcionesConsola;

import gestorAplicacion.master.Cine;
import gestorAplicacion.master.Empleado;
import gestorAplicacion.master.Funcion;
import gestorAplicacion.usuario.Cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class DiaFuncion implements OpcionConsola{
    static Cine salaActiva;
    int opcion;
    Cliente clienteActual = new Cliente();
    Funcion funcion = new Funcion();
    int asientosAElegir;
    int opcionpago;
    int recarga;
    String din;

    @Override
    public void ejecutar() {
        StringBuilder s = new StringBuilder();
        clienteActual = Cliente.getClienteActual();
        /*Mostramos la semana*/
        System.out.println(empleado.mostrarSemana());
        System.out.println(separador);
        System.out.print("Elija el dia que desea reservar: ");
        opcion = dato.nextInt();

        System.out.println(separador);
        System.out.println("       Funciones del día y sus precios en:");
        System.out.println("            [dinero($) || puntos(P)]\n");
        /*Almacenamos las funciones del dia en este vector*/
        Vector<Funcion> funcionesDia = clienteActual.consultarFunciones(opcion,salaActiva);
        /*Luego con una sobrecarga de metodos tomamos ese vector y lo mostramos en forma de lista*/
        System.out.println(clienteActual.consultarFunciones(funcionesDia));
        System.out.println(separador);
        System.out.print("Elegir una funcion: ");
        opcion = dato.nextInt();
        funcion = funcionesDia.get(opcion-1);
        System.out.println(separador);
        /*Mostramos de forma dinamica */
        System.out.println("        Puedes reservar de 1 a "+ funcion.getPuestosVacios() + " asientos");
        System.out.println("         Cuantos asientos desea reservar? ");
        System.out.println(separador);
        asientosAElegir = dato.nextInt();


        //Debe elegir solo una cantidad de asientos permitida
        while ((asientosAElegir>funcion.getPuestosVacios()) || (asientosAElegir<1)){
            System.out.println(separador);
            System.out.println("     El numero de asientos que intenta elegir");
            System.out.println("               no esta disponible");
            System.out.println(separador);
            System.out.print("Elige entre 1 a "+ (funcion.getPuestosVacios()-1) +" asientos vacios: ");
            asientosAElegir = dato.nextInt();

        }

        //Selección del tipo de pago
        System.out.println(separador);
        System.out.println("    Saldo en los metodos de pagos disponibles:");
        System.out.println("  ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
        System.out.println("1. Cuenta bancaria - Saldo: $" + empleado.consultarSaldo(clienteActual));
        //System.out.println("1. Cuenta bancaria - Saldo: $" + clienteActual.getCuentaBancaria().getSaldo());
        System.out.println("2. Cuenta Puntos - Saldo: " + empleado.consultarPuntos(clienteActual)+ "P");
        //System.out.println("2. Cuenta Puntos - Saldo: " + clienteActual.getCuentaPuntos().getPuntos()+ "P");
        System.out.println(separador);
        System.out.print("Selecciona el tipo de pago que emplearas: ");
        opcionpago = dato.nextInt();
        System.out.println(separador);

        //Verificamos que el metodo de pago elegido tenga saldo disponible

        int valorreserva = funcion.getPrecio()*asientosAElegir;
        int valorreservap = funcion.getPrecioP()*asientosAElegir;
        int saldoactual = clienteActual.getCuentaBancaria().getSaldo();
        int saldoactualp = clienteActual.getCuentaPuntos().getPuntos();
        int minimo = valorreserva - saldoactual;

        //Validación de saldo en cuenta bancaria
        if (opcionpago == 1){
            if(empleado.verificarDinero(clienteActual,valorreserva)){
                opcionpago = 1;
                System.out.println("Bien. Tu saldo es de: $"+ saldoactual+","+"\nLas boletas cuestan $"+valorreserva+".");
                System.out.println(separador);
            }
            else {
                if (saldoactualp >= valorreservap) {
                    System.out.println("No cuentas con saldo disponible en tu cuenta bancaria:");
                    System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
                    System.out.println("Pero puedes:");
                    //System.out.println("1. Utilizar tus puntos, te alcanza!");
                    System.out.println("1. Recargar tu cuenta bancaria, minimo: $" + minimo);
                    System.out.println("2. Cancelar compra, y volver al menu de usuario");
                    System.out.println(separador);
                    System.out.print("¿Que deseas hacer? ");
                    opcionpago = dato.nextInt();
                    System.out.println(separador);
                    /*
                    if (opcionpago == 1) {
                        opcionpago = 2;
                    }*/
                    if (opcionpago == 1) {
                        System.out.print("¿Cuanto deseas recargarle? ");
                        recarga = dato.nextInt();
                        while ((recarga + saldoactual) < valorreserva) {
                            System.out.println("Con la recarga no alcaza para comprar las boletas");
                            System.out.println("Por favor aumenta la recarga, minimo $" + minimo);
                            System.out.println(separador);
                            recarga = dato.nextInt();
                        }
                        String saldof = empleado.recargarCuentaBancaria(clienteActual, recarga);
                        System.out.println(separador);
                        System.out.println(saldof);
                        System.out.println(separador);
                        opcionpago = 1;
                    } else {
                        volver(1);
                        salir.ejecutar();
                    }
                }
                if (saldoactualp <= valorreservap) {
                    System.out.println("No cuentas con saldo disponible en tu cuenta bancaria ni en puntos:");
                    System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
                    System.out.println("Pero puedes:");
                    System.out.println("1.Recargar tu cuenta bancaria, minimo: $" + minimo);
                    System.out.println("2.Cancelar compra, y volver al menu de usuario");
                    System.out.println(separador);
                    System.out.print("¿Que deseas hacer? ");
                    opcionpago = dato.nextInt();
                    System.out.println(separador);

                    if (opcionpago == 1) {
                        System.out.print("¿Cuanto deseas recargarle? ");
                        recarga = dato.nextInt();
                        while ((recarga + saldoactual) < valorreserva) {
                            System.out.println("Con la recarga no alcaza para comprar las boletas");
                            System.out.println("Por favor aumenta la recarga, minimo $" + minimo);
                            System.out.println(separador);
                            recarga = dato.nextInt();
                        }
                        String saldof = empleado.recargarCuentaBancaria(clienteActual, recarga);
                        System.out.println(separador);
                        System.out.println(saldof);
                        System.out.println(separador);
                        opcionpago = 1;
                    }

                    else{
                        volver(1);
                        salir.ejecutar();
                    }
                }
            }
        }
        //Validación de saldo en cuenta puntos
        if (opcionpago == 2) {
            if(empleado.verificarPuntos(clienteActual,valorreservap)){
                System.out.println("Bien. Tu saldo es de: "+ saldoactualp+"P."+"\nLas boletas cuestan "+valorreservap+"P.");
                System.out.println(separador);
                opcionpago = 2;
            }
            else{
                if( saldoactual >= valorreserva ){

                    System.out.println("No cuentas con saldo disponible en tu cuenta de puntos:");
                    System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
                    System.out.println("Pero puedes:");
                    System.out.println("1. Utilizar tu cuenta bancaria");
                    System.out.println("2. Cancelar compra, y volver al menu de usuario");
                    System.out.println(separador);
                    System.out.print("¿Que deseas hacer? ");
                    opcionpago = dato.nextInt();
                    System.out.println(separador);

                    if (opcionpago==1){
                        System.out.println("Bien. Tu saldo es de: $"+ saldoactual+" y las boletas cuestan $"+valorreserva);
                        System.out.println(separador);
                        opcionpago = 1;
                    }
                    else {
                        volver(1);
                        salir.ejecutar();
                    }

                }
                if( saldoactual <= valorreserva ){
                    System.out.println("No cuentas con saldo disponible en tu cuenta bancaria ni en puntos:");
                    System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
                    System.out.println("Pero puedes:\n");
                    System.out.println("1. Recargar tu cuenta bancaria, minimo: $" + minimo);
                    System.out.println("2. Cancelar compra, y volver al menu de usuario");
                    System.out.println(separador);
                    System.out.print("¿Que deseas hacer? ");
                    opcionpago = dato.nextInt();
                    System.out.println(separador);

                    if (opcionpago == 1) {
                        System.out.print("¿Cuanto deseas recargarle? ");
                        recarga = dato.nextInt();
                        while ((recarga + saldoactual) < valorreserva) {
                            System.out.println("Con la recarga no alcaza para comprar las boletas");
                            System.out.println("Por favor aumenta la recarga, minimo $" + minimo);
                            System.out.println(separador);
                            recarga = dato.nextInt();
                        }
                        String saldof = empleado.recargarCuentaBancaria(clienteActual, recarga);
                        System.out.println(separador);
                        System.out.println(saldof);
                        System.out.println(separador);
                        opcionpago = 1;
                    }

                    else{
                        volver(1);
                        salir.ejecutar();
                    }
                }
            }
        }


        //Mostrar los puestos a escoger, sabiendo que ya se puede pagar:
        System.out.println(funcion.mostrarPuestos());
        System.out.println(separador);
        Vector<Integer> puestos = new Vector<Integer>();
        for(int i=0; i<asientosAElegir; i++){
            System.out.print("Ingrese el # de asiento: ");
            int numeroPuesto = dato.nextInt();
            puestos.add(numeroPuesto);
        }


        if(opcionpago==1){
            clienteActual.reservarPuestos(puestos,funcion);
            System.out.println(separador);
            System.out.println(empleado.transaccionDinero(clienteActual, valorreserva));
            System.out.println(separador);
            System.out.println("1Tu nuevo saldo es: $"+empleado.consultarSaldo(clienteActual)+".");
            System.out.println("2Tus nuevos puntos son: "+ empleado.consultarPuntos(clienteActual)+"P.");
        }
        if(opcionpago==2){
            clienteActual.reservarPuestos(puestos,funcion,1);
            System.out.println(separador);
            System.out.println(empleado.transaccionPuntos(clienteActual, valorreservap));
            System.out.println(separador);
            System.out.println("3Tu nuevo saldo es: $"+empleado.consultarSaldo(clienteActual)+".");
            System.out.println("4Tus nuevos puntos son: "+ empleado.consultarPuntos(clienteActual)+"P.");
        }

        System.out.println(separador);
        System.out.println("      ¡¡¡Reserva hecha satisfactoriamente!!!");
        System.out.println(separador);
        System.out.println(mensajeVolver);
        opcion = dato.nextInt();
        volver(opcion);
    }

    @Override
    public void volver(int opcion) {
        if(opcion == 1){
            ingresoUsuario.ejecutar();
            System.exit(0);
        }else{
            salir.ejecutar();
        }
    }
}
