package uiMain.opcionesConsola;

import gestorAplicacion.master.Cine;
import gestorAplicacion.master.Empleado;
import gestorAplicacion.master.Funcion;

import java.util.Vector;

public class MostrarCine implements OpcionConsola{
    Cine cine = new Cine();
    Empleado empleado = new Empleado();
    int opcion;


    @Override
    public void ejecutar() {
        System.out.println(separador);
        System.out.println(empleado.consultarCines());
        System.out.println(separador);
        System.out.println("En que ciudad desea ver nuestras salas disponibles: ");
        opcion = dato.nextInt();
        System.out.println(separador);
        System.out.println("      Salas de cine disponibles en la ciudad:");
        System.out.println("    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯  ");
        Vector<Cine> salasXCiudad = empleado.cinesPorCiudad(Cine.getCiudades().get(opcion-1));
        System.out.println(empleado.cinesPorCiudad(salasXCiudad));  /////
        System.out.println(separador);
        System.out.print("Seleccione una sala de cine: ");
        opcion = dato.nextInt();
        System.out.println(separador);
        DiaFuncion.salaActiva = salasXCiudad.get(opcion-1);
        System.out.println("       Ha elegio el cine " + salasXCiudad.get(opcion-1).getNombre());
        System.out.println("      ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
        diaFuncion.ejecutar();
        /*
        Vector<Funcion> dia =cine.funcionesDelDia(salasXCiudad.get(opcion-1),0);
        System.out.println(cine.funcionesDelDia(dia));
        System.out.println(separador);
        */

    }

    @Override
    public void volver(int opcion) {

    }
}
