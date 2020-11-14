package baseDatos;

import gestorAplicacion.master.*;
import gestorAplicacion.usuario.*;

import java.util.Vector;

/*Clase la cual se encargara de almacenar toda la informacion, en esta se guardaran los datos que se lean
  de los archivos .txt al iniciar el programa y de igual forma al finalizar el programa se guardaran
  todos los datos aca almacenados en los archivos .txt para su posterior uso*/
public class BaseDeDatos {

    //Atributos de clase****
    private static Vector<Cliente> clientes = new Vector<>(); /*Aca se guardaran todos los clientes para su posterior uso*/
    private static Vector<CuentaBancaria> cuentasBancarias = new Vector<>(); /*Vector donde tenemos la informacion de todas las cuentas bancarias*/
    private static Vector<CuentaPuntos> cuentasPuntos = new Vector<>(); /*Vector donde tenemos toda la informacion de las cuentas puntos*/
    private static Vector<Cine> cines = new Vector<>(); /*Vector donde se guardaran los cines*/
    private static Vector<Funcion> funciones = new Vector<>(); /*Vector donde se guardaran las funciones*/
    private static Vector<Reserva> reservas = new Vector<>(); /*Vector donde se guardan las reservas*/

    //Contructores de clase****
    //Solo me interesa el constructores por defecto (Por el momento)

    //Metodos GET and SET****

    public static Vector<CuentaPuntos> getCuentasPuntos() {
        return cuentasPuntos;
    }

    public static Vector<Reserva> getReservas() {
        return reservas;
    }

    public static Vector<Cliente> getClientes() {
        return clientes;
    }

    public static Vector<CuentaBancaria> getCuentasBancarias() {
        return cuentasBancarias;
    }

    public static Vector<Cine> getCines() {
        return cines;
    }

    public static Vector<Funcion> getFunciones() {
        return funciones;
    }

    //Metodos de clase****

    //Metodo para agregar clientes en el atributo *clientes*
    public static void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    //Metodo para agregar cuentaBancaria al atributo *cuentasBancarias*
    public static void addCuentaBancaria(CuentaBancaria cuentaBancaria) {
        cuentasBancarias.add(cuentaBancaria);
    }

    //Metodo para agregar cines al atributo *cines*
    public static void addCine(Cine cine) {
        cines.add(cine);
    }

    //Metodo para agregar funciones al atributo *funciones*
    public static void addFuncion(Funcion funcion) {
        funciones.add(funcion);
    }

    //Metodo para agregar reservas al atributo *reservas*
    public static void addReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    //Metodo para agregar cuentaPuntos al atributo *cuentasPuntos*
    public static void addCuentaPuntos(CuentaPuntos cuentaPuntos) {
        cuentasPuntos.add(cuentaPuntos);
    }

    /*Metodo que se ejecutara al inicio del programa, este se encargara como
      su nombre lo dice de relacionar todos los datos, es decir a cada cliente
      asociarle tu respectiva cuenta bancaria y cuenta de puntos, de igual forma
      a la cuenta puntos y cuenta bancaria asociarle su respectivo cliente*/
    public static void relacionar() {
        for (Cliente cliente : clientes) {
            cliente.cartera = new Vector<>();
        }
        for (int i = 0; i < BaseDeDatos.getClientes().size(); i++) {
            //Al cliente i lo asocio a la cuenta i
            clientes.get(i).setCuentaBancaria(cuentasBancarias.get(i));
            clientes.get(i).setCuentaPuntos(cuentasPuntos.get(i));
            //A la cuenta i la asocio al cliente i
            cuentasBancarias.get(i).setTitular(clientes.get(i));
            /*Se supone que aca no estoy relacionando la cuentaPuntos con el usuario,
              no se porque funciona el codigo entonces :v*/
        }
        /*Verificamos que el vector reservas no sea nulo, y por cada componente (Reserva)
          que se encuentre en este, la asociamos con el cliente correspondiente*/
        if (reservas.size() != 0) {
            for (Reserva reserva : reservas) {
                clientes.get(reserva.getCodigoReserva()).agregarReserva(reserva);
            }
        }
    }


}
