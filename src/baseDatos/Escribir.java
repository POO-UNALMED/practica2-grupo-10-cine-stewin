package baseDatos;

import gestorAplicacion.master.*;
import gestorAplicacion.usuario.*;

import java.io.*;

public class Escribir {
    static File fichero = new File("");

    /*Este metodo sera el encargado de guardar toda la informacion de clientes/reservas
      cines/cuentasBancarias/cuentasPuntos en los archivos.txt */
    public static void Escribir() {

        //Guardamos los clientes en la base de datos
        if (BaseDeDatos.getClientes().size() != 0) {
            try {
                //Creamos el archivo
                FileOutputStream fs = new FileOutputStream(fichero.getAbsolutePath() + "/src/baseDatos/temp/usuarios.txt");
                //Esta clase tiene el método writeObject() que necesitamos
                ObjectOutputStream os = new ObjectOutputStream(fs);
                for (Cliente i : BaseDeDatos.getClientes()) {
                    os.writeObject(i);
                }
                //Hay que cerrar siempre el archivo
                os.close();
                fs.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Guardamos las cuentas bancarias en la base de datos
        if (BaseDeDatos.getCuentasBancarias().size() != 0) {
            try {
                FileOutputStream fs = new FileOutputStream(fichero.getAbsolutePath() + "/src/baseDatos/temp/cuentasBancarias.txt");
                ObjectOutputStream os = new ObjectOutputStream(fs);
                for (CuentaBancaria i : BaseDeDatos.getCuentasBancarias()) {
                    os.writeObject(i);
                }
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Guardamos los cines en la base de datos
        if (BaseDeDatos.getCines().size() != 0) {
            try {
                FileOutputStream fs = new FileOutputStream(fichero.getAbsolutePath() + "/src/baseDatos/temp/cines.txt");
                ObjectOutputStream os = new ObjectOutputStream(fs);
                for (Cine i : BaseDeDatos.getCines()) {
                    os.writeObject(i);
                }
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Guardamos las funciones en la base de datos
        if (BaseDeDatos.getFunciones().size() != 0) {
            try {
                FileOutputStream fs = new FileOutputStream(fichero.getAbsolutePath() + "/src/baseDatos/temp/funciones.txt");
                ObjectOutputStream os = new ObjectOutputStream(fs);
                for (Funcion i : BaseDeDatos.getFunciones()) {
                    os.writeObject(i);
                }
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Guardamos las reservas en la base de datos
        if (BaseDeDatos.getReservas().size() != 0) {
            try {
                FileOutputStream fs = new FileOutputStream(fichero.getAbsolutePath() + "/src/baseDatos/temp/reservas.txt");
                ObjectOutputStream os = new ObjectOutputStream(fs);
                for (Reserva i : BaseDeDatos.getReservas()) {
                    os.writeObject(i);
                }
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Guardamos las cuenta puntos en la base de datos
        if (BaseDeDatos.getCuentasPuntos().size() != 0) {
            try {
                FileOutputStream fs = new FileOutputStream(fichero.getAbsolutePath() + "/src/baseDatos/temp/cuentasPuntos.txt");
                ObjectOutputStream os = new ObjectOutputStream(fs);
                for (CuentaPuntos i : BaseDeDatos.getCuentasPuntos()) {
                    os.writeObject(i);
                }
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


