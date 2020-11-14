package uiMain.opcionesConsola;

import gestorAplicacion.usuario.Cliente;

import java.sql.SQLOutput;
import java.util.Vector;

public class Puntos implements OpcionConsola {
    Cliente clienteActual = new Cliente();

    @Override
    public void ejecutar() {
        clienteActual = Cliente.getClienteActual();
        System.out.println("Cliente: " + clienteActual.getNombre());
        System.out.println("Su saldo actual es de: " + clienteActual.getCuentaBancaria().getSaldo());
        System.out.println("Su cantidad de puntos son: " + clienteActual.getCuentaPuntos().getPuntos());

        System.out.println(separador);
            System.out.println(" Opciones de comida disponibles y sus precios en");
            System.out.println("            [dinero($) || puntos(P)]\n");
            System.out.println(empleado.mostrarComidad());
            System.out.println(separador);
            /*Pedimos el numeor de la opcion que el usuario desea*/
            System.out.print("Ingrese la opcion deseada: ");
            int opcionComida = dato.nextInt();
            System.out.println(separador);
            /*Si el usuario ingresa a la opcion numero 5 se devolvera y se le mostraran de nuevo
              nuevo las opciones de este menu*/
            if(opcionComida == empleado.getComidas().size()+1){
                ingresoUsuario.ejecutar();
                System.exit(0);
            }
            /*En caso contrario seguimos y ahora pedimos la cantidad*/
            System.out.print("Cuantas unidades desea comprar: ");
            int cantidad = dato.nextInt();
            System.out.println(separador);
            /*En caso contrario(De que haya elegido alguna otra opcion, continuara a ejecutar
              este menu)*/
            System.out.println("1. Dinero");
            System.out.println("2. Puntos");
            System.out.println(separador);
            System.out.print("Eliga el método de pago deseado: ");
            int metodoPago = dato.nextInt();
            System.out.println(separador);
            /*Ejecutamos el metodo comprrarComidad que se encarga de confirmar si el usuario
              tiene el dinero/puntos suficientes para hacer la compra*/
            System.out.println(empleado.comprarComidas(clienteActual,metodoPago,cantidad,opcionComida));
            System.out.println(separador);
            ejecutar();
    }

    @Override
    public void volver(int opcion) {
        if (opcion == 1) {
            System.out.println(separador);
            ingresoUsuario.ejecutar();
            System.exit(0);
        } else {
            salir.ejecutar();
        }
    }
}
