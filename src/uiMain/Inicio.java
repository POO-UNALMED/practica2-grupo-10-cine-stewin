package uiMain;

import baseDatos.BaseDeDatos;
import baseDatos.Escribir;
import baseDatos.Leer;
import gestorAplicacion.master.Cine;
import gestorAplicacion.master.Empleado;
import gestorAplicacion.master.Funcion;
import gestorAplicacion.master.Reserva;
import gestorAplicacion.usuario.Cliente;
import uiMain.opcionesConsola.PantallaInicio;

import java.util.Vector;

public class Inicio {
    static{
        Leer.Leer();
        Empleado empleado = new Empleado();
        empleado.enlazarFuncionesYCines();
        empleado.definirSemanaFunciones();
    }
    public static void main(String[] args) {
        BaseDeDatos.relacionar();
        //BaseDeDatos.getClientes().get(0).getCuentaBancaria().setSaldo(0);
        /*Que pro soy, arregle este error y descubri algo interesante :v*/
        //Esta linea de aca es bastante interesante, me puede simular el comportamiento de funciones, pero debo arreglar algo respecto a alas fechas
        /*
        Vector<Integer> prueba = new Vector<>();
        prueba.add(1);
            //BaseDeDatos.getClientes().get(0).agregarReserva( new Reserva(BaseDeDatos.getClientes().get(0), new Funcion("La quemona 4",1,15,20),prueba));
            //BaseDeDatos.getClientes().get(0).agregarReserva( new Reserva(BaseDeDatos.getClientes().get(0), new Funcion("La quemona 4",1,15),prueba));
        BaseDeDatos.getClientes().get(0).crearReserva(BaseDeDatos.getClientes().get(0),new Funcion("La quemona 1",1,15,30),prueba);
        //BaseDeDatos.getClientes().get(0).crearReserva(BaseDeDatos.getClientes().get(0),new Funcion("La quemona 2",1,15,20),prueba);
        //BaseDeDatos.getClientes().get(0).crearReserva(BaseDeDatos.getClientes().get(0),new Funcion("La quemona 3",1,15,21),prueba);
        BaseDeDatos.getClientes().get(0).crearReserva(BaseDeDatos.getClientes().get(0),new Funcion("La quemona 4",1,15,31),prueba);
        BaseDeDatos.getClientes().get(0).crearReserva(BaseDeDatos.getClientes().get(0),new Funcion("La quemona 9",1,15,31),prueba);
        */

        //System.out.println(BaseDeDatos.getClientes().get(0).getCartera().get(0).getFuncion().mostrarPuestos()); //prueva
        /*
        System.out.println("Clientes en la base de datos "+BaseDeDatos.getClientes().size());
        System.out.println("Cuentas bancarias en la base de datos "+BaseDeDatos.getCuentasBancarias().size());
        System.out.println("Cines en la base de datos "+BaseDeDatos.getCines().size());
        System.out.println("Funciones en la base de datos "+BaseDeDatos.getFunciones().size());
        System.out.println(BaseDeDatos.getCines().get(0).getFunciones().size());
        System.out.println(BaseDeDatos.getCines().get(0).getFunciones().get(0).get(0).getFecha());
        System.out.println(BaseDeDatos.getCines().get(0).getFunciones().get(0).get(0).getSala());
        System.out.println("==============");
        System.out.println(BaseDeDatos.getCines().get(0).getFunciones());
        System.out.println(BaseDeDatos.getCines().get(0).getFunciones().get(2).get(1).getSala());
        System.out.println(BaseDeDatos.getCines().get(0).getFunciones().get(0).get(2).getSala());
        System.out.println(BaseDeDatos.getCines().get(0).getFunciones());
        System.out.println(BaseDeDatos.getReservas().size());
        */
        //System.out.println(BaseDeDatos.getClientes().get(0).getID());

        //System.out.println(BaseDeDatos.getCines().get(0).getFunciones().size());
        /*
        Cine cine1 = new Cine("STEWIN la Estacion","Ibague","Calle 60 con Ambala");
        BaseDeDatos.addCine(cine1);
        Cine cine2 = new Cine("STWEIN el Tesoro","Medellin","calle 10");
        BaseDeDatos.addCine(cine2);
        Cine cine3 = new Cine("STWEIN DonMatias","Don Matias","Parque principal");
        BaseDeDatos.addCine(cine3);
        Cine cine4 = new Cine("STWEIN Viva Centro","Riohacha","Cl. 15 ##18-274");
        BaseDeDatos.addCine(cine4);
        Cine cine5 = new Cine("STEWIN plazas","Ibague","Calle 60 con Ambala");
        BaseDeDatos.addCine(cine5);
        Cine cine6 = new Cine("STWEIN la 10","Ibague","calle 10");
        BaseDeDatos.addCine(cine6);
        Cine cine7 = new Cine("STWEIN florida","Medellin","Parque principal");
        BaseDeDatos.addCine(cine7);
        Cine cine8 = new Cine("STWEIN la 65","Medellin","Cl. 15 ##18-274");
        BaseDeDatos.addCine(cine8);
        */
        /*
        Vector<Funcion> funciones = new Vector<Funcion>();
        funciones.add(new Funcion("Rapido y furiosos 1",1,15));
        funciones.add(new Funcion("Rapido y fuirosos 2",2,15));
        funciones.add(new Funcion("Rapido y fuirosos 3",3,15));
        funciones.add(new Funcion("Rapido y fuirosos 4",4,15));
        funciones.add(new Funcion("Rapido y fuirosos 5",1,16));
        funciones.add(new Funcion("Rapido y fuirosos 6",2,16));
        funciones.add(new Funcion("Rapido y fuirosos 7",3,16));
        funciones.add(new Funcion("Rapido y fuirosos 8",4,16));
        funciones.add(new Funcion("Rapido y fuirosos 9",1,17));
        funciones.add(new Funcion("Rapido y fuirosos 10",2,17));
        funciones.add(new Funcion("Rapido y fuirosos 11",3,17));
        funciones.add(new Funcion("Rapido y fuirosos 12",4,17));
        funciones.add(new Funcion("Rapido y fuirosos 13",1,18));
        funciones.add(new Funcion("Rapido y fuirosos 14",2,18));
        funciones.add(new Funcion("Rapido y fuirosos 15",3,18));
        funciones.add(new Funcion("Rapido y fuirosos 16",4,18));
        funciones.add(new Funcion("Rapido y fuirosos 17",1,19));
        funciones.add(new Funcion("Rapido y fuirosos 18",2,19));
        funciones.add(new Funcion("Rapido y fuirosos 19",3,19));
        funciones.add(new Funcion("Rapido y fuirosos 20",4,19));
        funciones.add(new Funcion("Rapido y fuirosos 21",1,20));
        funciones.add(new Funcion("Rapido y fuirosos 22",2,20));
        funciones.add(new Funcion("Rapido y fuirosos 23",3,20));
        funciones.add(new Funcion("Rapido y fuirosos 24",4,20));
        funciones.add(new Funcion("Rapido y fuirosos 25",1,21));
        funciones.add(new Funcion("Rapido y fuirosos 26",2,21));
        funciones.add(new Funcion("Rapido y fuirosos 27",3,21));
        funciones.add(new Funcion("Rapido y fuirosos 28",4,21));
        funciones.add(new Funcion("Rapido y fuirosos 29",1,22));
        funciones.add(new Funcion("Rapido y fuirosos 30",2,22));
        funciones.add(new Funcion("Rapido y fuirosos 31",3,22));
        funciones.add(new Funcion("Rapido y fuirosos 32",4,22));
        for(Funcion funcion : funciones){
            BaseDeDatos.addFuncion(funcion);
        }
        */
        PantallaInicio pantallaInicio = new PantallaInicio();
        pantallaInicio.ejecutar();
        Escribir.Escribir();
    }
}
