package baseDatos;

import gestorAplicacion.master.*;
import gestorAplicacion.usuario.*;

import java.io.*;

public class Leer {

    /*Este metodo sera el encargado de pasar toda la informacion de los archivos .txt a los respectivos
      vectores de la clase BaseDeDatos*/
    static File archivo = new File("");

    public static void Leer() {

        //Leemos los clientes de la base de datos
        try {
            File usuarios = new File(archivo.getAbsolutePath() + "/src/baseDatos/temp/usuarios.txt");
            if (usuarios.length() != 0) {
                FileInputStream datosUsuarios = new FileInputStream(archivo.getAbsolutePath() + "/src/baseDatos/temp/usuarios.txt");
                ObjectInputStream objetosUsuarios = new ObjectInputStream(datosUsuarios);

                Object aux = objetosUsuarios.readObject();
                try {
                    while (aux != null) {
                        BaseDeDatos.addCliente((Cliente) aux);
                        aux = objetosUsuarios.readObject();
                    }
                } catch (java.io.IOException e) {

                }
                datosUsuarios.close();
                objetosUsuarios.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Leemos las cuentas bancarias de la base de datos
        try {
            File cuentasBancarias = new File(archivo.getAbsolutePath() + "/src/baseDatos/temp/cuentasBancarias.txt");
            if (cuentasBancarias.length() != 0) {
                FileInputStream datosCB = new FileInputStream(archivo.getAbsolutePath() + "/src/baseDatos/temp/cuentasBancarias.txt");
                ObjectInputStream objetosCB = new ObjectInputStream(datosCB);

                Object aux1 = objetosCB.readObject();
                try {
                    while (aux1 != null) {
                        BaseDeDatos.addCuentaBancaria((CuentaBancaria) aux1);
                        aux1 = objetosCB.readObject();
                    }
                } catch (java.io.IOException e) {

                }
                datosCB.close();
                objetosCB.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Leemos los cines de la base de datos
        try {
            File cines = new File(archivo.getAbsolutePath() + "/src/baseDatos/temp/cines.txt");
            if (cines.length() != 0) {
                FileInputStream datosCine = new FileInputStream(archivo.getAbsolutePath() + "/src/baseDatos/temp/cines.txt");
                ObjectInputStream objetosCine = new ObjectInputStream(datosCine);

                Object aux1 = objetosCine.readObject();
                try {
                    while (aux1 != null) {
                        BaseDeDatos.addCine((Cine) aux1);
                        aux1 = objetosCine.readObject();
                    }
                } catch (java.io.IOException e) {

                }
                datosCine.close();
                objetosCine.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Leemos las funciones de la base de datos
        try {
            File funciones = new File(archivo.getAbsolutePath() + "/src/baseDatos/temp/funciones.txt");
            if (funciones.length() != 0) {
                FileInputStream datosFunciones = new FileInputStream(archivo.getAbsolutePath() + "/src/baseDatos/temp/funciones.txt");
                ObjectInputStream objetosFunciones = new ObjectInputStream(datosFunciones);

                Object aux1 = objetosFunciones.readObject();
                try {
                    while (aux1 != null) {
                        BaseDeDatos.addFuncion((Funcion) aux1);
                        aux1 = objetosFunciones.readObject();
                    }
                } catch (java.io.IOException e) {

                }
                datosFunciones.close();
                objetosFunciones.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Leemos las reservas de la base de datos
        try {
            File reservas = new File(archivo.getAbsolutePath() + "/src/baseDatos/temp/reservas.txt");
            if (reservas.length() != 0) {
                FileInputStream datosReservas = new FileInputStream(archivo.getAbsolutePath() + "/src/baseDatos/temp/reservas.txt");
                ObjectInputStream objetosReservas = new ObjectInputStream(datosReservas);

                Object aux1 = objetosReservas.readObject();
                try {
                    while (aux1 != null) {
                        BaseDeDatos.addReserva((Reserva) aux1);
                        aux1 = objetosReservas.readObject();
                    }
                } catch (java.io.IOException e) {

                }
                datosReservas.close();
                objetosReservas.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Leemos las cuentas puntos de la base de datos
        try {
            File cuentasPuntos = new File(archivo.getAbsolutePath() + "/src/baseDatos/temp/cuentasPuntos.txt");
            if (cuentasPuntos.length() != 0) {
                FileInputStream datosCP = new FileInputStream(archivo.getAbsolutePath() + "/src/baseDatos/temp/cuentasPuntos.txt");
                ObjectInputStream objetosCP = new ObjectInputStream(datosCP);

                Object aux1 = objetosCP.readObject();
                try {
                    while (aux1 != null) {
                        BaseDeDatos.addCuentaPuntos((CuentaPuntos) aux1);
                        aux1 = objetosCP.readObject();
                    }
                } catch (java.io.IOException e) {

                }
                datosCP.close();
                objetosCP.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
