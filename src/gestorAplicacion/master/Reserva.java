package gestorAplicacion.master;

import baseDatos.BaseDeDatos;
import gestorAplicacion.usuario.Cliente;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

/*Esta clase sirve como intermedio/relacion entre un Cliente y una funcion
  es la encargada de mostrar y almacenar en el cliente(Cartera) la informacion
  sobre las funciones con que se esta relacionado*/
public class Reserva implements Serializable {

    //Attributes****
    private int codigo;
    private int codigoPersona;
    private String persona;
    private int numeroFuncion;
    private String nombreFuncion;
    private LocalDateTime fecha;
    private int numeroAsientos;
    private Funcion funcion;
    private Vector<Integer> asientosElegidos = new Vector<>();

    // Constructor.
    public Reserva() {
    }

    public Reserva(Cliente cliente, Funcion funcion, Vector<Integer> asientos) {
        this();
        this.codigo = cliente.getCartera().size();
        this.codigoPersona = cliente.getID();
        this.persona = cliente.getNombre();
        this.numeroFuncion = funcion.getNumeroFuncion();
        this.nombreFuncion = funcion.getNombre();
        this.fecha = funcion.getFecha();
        this.numeroAsientos = asientos.size();
        this.asientosElegidos = asientos;
        this.funcion=funcion;
    }

    //Metodos GET and SET****

    public int getCodigoReserva() {
        return codigoPersona;
    }

    public void setCodigoReserva(int codigoReserva) {
        this.codigoPersona = codigoReserva;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public int getNumeroFuncion() {
        return numeroFuncion;
    }

    public void setNumeroFuncion(int numeroFuncion) {
        this.numeroFuncion = numeroFuncion;
    }

    public String getNombreFuncion() {
        return nombreFuncion;
    }

    public void setNombreFuncion(String nombreFuncion) {
        this.nombreFuncion = nombreFuncion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getNumeroAsientos() {
        return numeroAsientos;
    }

    public void setNumeroAsientos(int numeroAsientos) {
        this.numeroAsientos = numeroAsientos;
    }
    public Vector<Integer> getAsientosElegidos(){
        return asientosElegidos;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }
    public void setAsientosElegidos(Vector<Integer> asientosElegidos) {
        this.asientosElegidos = asientosElegidos;
    }
    //Metodos de clase****


    @Override
    /*Este toString se va a encargar de mostrar las reservas al usuario en este formato*/
    /*Ahora estoy imprimiendo los aientos revisar esto ...........................*/
    public String toString() {
        return "Reserva # " + codigo + ", " + numeroAsientos + " asientos para la funcion: \n"
                + "     " + nombreFuncion + " \n" + " numero de asientos " + asientosElegidos
                + "     el " + fecha.format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm"));
    }
}
