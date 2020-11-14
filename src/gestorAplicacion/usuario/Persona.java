package gestorAplicacion.usuario;

import baseDatos.BaseDeDatos;

import java.io.Serializable;

/*Metodo abstracta el cual me define la "estructura de las clases usuario y empleado"*/
public abstract class Persona implements Serializable {

    //Atributos de clase****
    private int ID;
    private int identificacion;
    private transient CuentaBancaria cuentaBancaria;
    private String nombre;
    private String correo;
    private String historial;
    private String direccion;

    //Contructores de clase****
    public Persona() {
        this.ID = BaseDeDatos.getClientes().size();

    }

    public Persona(int identificacion, String nombre, String correo, String direccion) {
        this();
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
    }

    //Metodos GET and SET****
    public int getID() {
        return ID;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }



    //Metodos de clase****

    /*Metodo ToString que use para ver que si se estaban creando los
    ID correctamente(Se puede/debera editar mas adelante)*/
    @Override
    public String toString() {
        return "-Soy la persona " + nombre;
    }

    public void retirar(int pago){}


}
