package gestorAplicacion.master;

import java.io.Serializable;
import java.util.Vector;

/*Esta clase es el cine, en donde se llevaran a cabo todas las acciones*/
public class Cine implements Serializable {

    //Atributos de clase****
    private String nombre;
    private static Vector<String> ciudades = new Vector<>();
    private String ciudad;
    private String direccion;
    private int cantidadSalas = 4;
    public transient Vector<Vector<Funcion>> funciones = new Vector<>();

    //Constructores de clase****
    public Cine() {

    }

    public Cine(String nombre, String ciudad, String direccion) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }

    //Metodos GET and SET****
    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getCantidadSalas() {
        return cantidadSalas;
    }

    public Vector<Vector<Funcion>> getFunciones() {
        return funciones;
    }

    public static Vector<String> getCiudades() {
        return ciudades;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCantidadSalas(int cantidadSalas) {
        this.cantidadSalas = cantidadSalas;
    }

    //Metodos de clase****

    /*Metodo que agrega un vector de funciones, notar que el vector de vectores funciones
      tiene 7 posiciciones, cada una de las cuales representa las funciones correspondientes
      a su dia de la semana*/
    public void addFunciones(Vector<Funcion> funciones) {
        this.funciones.add(funciones);
    }

    @Override
    //ToString para ver que si se esten guardando los cines de la forma correcta --
    public String toString() {
        return "Cine{" +
                "nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", direccion='" + direccion + '\'' +
                ", cantidadSalas=" + cantidadSalas +
                '}';
    }
}