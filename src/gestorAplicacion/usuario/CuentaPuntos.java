package gestorAplicacion.usuario;

import baseDatos.BaseDeDatos;

import java.io.Serializable;

/*Clase que guardara la informacion del usuario respecto a los puntos*/
public class CuentaPuntos implements Serializable {

    //Atributos de clase****
    private int numeroCuenta;
    private transient Cliente titular;
    private int puntos;

    //Contructores de clase****
    public CuentaPuntos() {
        this.numeroCuenta = BaseDeDatos.getCuentasPuntos().size();
        this.puntos = 10000;
    }

    public CuentaPuntos(Cliente titular) {
        this();
        this.titular = titular;
    }

    //Metodos GET and SET****

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public Cliente getTitular() {
        return titular;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setTitular(Cliente tutilar) {
        this.titular = tutilar;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    //Metodos de clase****
}
