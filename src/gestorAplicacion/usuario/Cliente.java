package gestorAplicacion.usuario;

import baseDatos.BaseDeDatos;
import gestorAplicacion.master.Cine;
import gestorAplicacion.master.Funcion;
import gestorAplicacion.master.Reserva;

import java.time.LocalDateTime;
import java.util.Vector;

public class Cliente extends Persona {

    //Atributos de clase****
    private CuentaPuntos cuentaPuntos;
    public transient Vector<Reserva> cartera = new Vector<>();
    /*Este atributo lo usaremos para una vez se ingresa como cliente
     se pueda consultar su informacion facilmente*/
    private transient static Cliente clienteActual;

    //Constructores de clase****

    public Cliente() {
    }

    public Cliente(int identificacion, String nombre, String correo, String direccion) {
        super(identificacion, nombre, correo, direccion);
    }

    //Metodos GET and SET****

    public CuentaPuntos getCuentaPuntos() {
        return cuentaPuntos;
    }

    public void setCuentaPuntos(CuentaPuntos cuentaPuntos) {
        this.cuentaPuntos = cuentaPuntos;
    }

    public void retirarPuntos(int cantidad){}


    public Vector<Reserva> getCartera() {
        return cartera;
    }

    public void setCartera(Vector<Reserva> cartera) {
        this.cartera = cartera;
    }

    public static Cliente getClienteActual() {
        return clienteActual;
    }

    public static void setClienteActual(Cliente clienteActual) {
        Cliente.clienteActual = clienteActual;
    }



    //Metodos de clase****

    /*Metodo que le permite al usuario consultar todas las funciones del dia y cine elegido*/
    public Vector<Funcion> consultarFunciones(int dia, Cine cine) {
        return cine.getFunciones().get(dia);
    }

    /*Metodo que se encarga de mostrar todas las funciones que se encuentran en una fecha habil
    (Que aun no hay ocurrido) para que el usuario pueda elegir alguna*/
    public StringBuilder consultarFunciones(Vector<Funcion> funcionesDelDia) {
        int contador = 1;
        StringBuilder s = new StringBuilder();
        for (Funcion funcion : funcionesDelDia) {
            //Le damos un estado a las funciones
            funcion.estado();
            if (funcion.isEstado() == true) {
                s.append(contador).append(". ")
                        .append(funcion)
                        .append("\n");
                contador++;
            }
        }
        if (s.length() > 0) {
            s.delete(s.length() - 1, s.length());
            return s;
        } else {
            s.append("No hay funciones disponibles el dia de hoy");
            return s;
        }
    }

    /*Metodo que se encarga de reservas los puestos que el usuario ha elegido en la funcion seleccionada*/
    public void reservarPuestos(Vector<Integer> puestos, Funcion funcion) {
        for (Integer puesto : puestos) {
            funcion.getPuestos()[puesto] = 0;
        }
        /*
        int saldoActual = this.getCuentaBancaria().getSaldo();
        this.getCuentaBancaria().setSaldo(saldoActual - (funcion.getPrecio() * puestos.size()));
        */
        agregarPuntos((funcion.getPrecio() * puestos.size()));
        crearReserva(this, funcion, puestos);
    }

    public void reservarPuestos(Vector<Integer> puestos, Funcion funcion, int a) {
        for (Integer puesto : puestos) {
            funcion.getPuestos()[puesto] = 0;
        }
        //int saldoActual = this.getCuentaBancaria().getSaldo();
        //this.getCuentaBancaria().setSaldo(saldoActual - (funcion.getPrecio() * puestos.size()));
        //agregarPuntos((funcion.getPrecio() * puestos.size()));
        crearReserva(this, funcion, puestos);
    }

    /*Metodo que me crea una reserva*/
    public void crearReserva(Cliente cliente, Funcion funcion, Vector<Integer> puestos) {
        Reserva reserva = new Reserva(cliente, funcion, puestos);
        agregarReserva(reserva);
        BaseDeDatos.addReserva(reserva);
    }

    /*Metodo que se encarga de agregar la reserva al historial del usuario*/
    public void agregarReserva(Reserva reserva) {
        cartera.add(reserva);
    }

    /*Metodo que le permite consultar al usuario todas sus reservas hechas,
    tanto activas como vencidas*/
    public String consultarReservas() {
        StringBuilder s = new StringBuilder();
        s.append("               1. Reservas activas\n");
        Vector<Reserva> activas = new Vector<>();
        Vector<Reserva> vencidad = new Vector<>();
        LocalDateTime hoy = LocalDateTime.now();
        if (cartera.size() == 0) {
            s.append("El usuario no tiene reservas activas\n");
            s.append("               2. Reservas vencidad\n");
            s.append("El usuario no tiene reservas activas");
        } else {
            /*Ahora si me guarda correctamente en los vectores a su fecha correspondiente :,v*/
            for (Reserva reserva : cartera) {
                if (reserva.getFecha().compareTo(hoy) > 0) {
                    activas.add(reserva);
                } else {
                    vencidad.add(reserva);
                }
            }
            for (Reserva reserva : activas) {
                s.append(reserva.toString()).append("\n");
            }
            s.append("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n");
            s.append("               2. Reservas vencidas\n");
            for (Reserva reserva : vencidad) {
                s.append(reserva.toString()).append("\n");
            }
            s.delete(s.length() - 1, s.length());
        }
        return s.toString();
    }

    /*Metodo que se encarga de agregar puntos a la cuentaPuntos del usuario despues
      de hacer una compra*/
    public void agregarPuntos(int i) {
        int b = (i / 100) * 10;
        this.cuentaPuntos.setPuntos(this.cuentaPuntos.getPuntos() + b);
    }
}
