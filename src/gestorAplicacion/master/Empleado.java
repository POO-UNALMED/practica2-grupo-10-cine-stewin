package gestorAplicacion.master;

import baseDatos.BaseDeDatos;
import gestorAplicacion.usuario.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import ManejoExcepciones.blankText_Exception;
import ManejoExcepciones.invalidDataType_Exception;

/*La clase empleado sera la encargada de mostrarle muchos datos al usuario para que el pueda
  interactuar con los mismos y editar/cambiar/crear la configuracion del cine(opciones)*/
public class Empleado extends Persona {

    //Atributos de clase****
    private Vector<String> comidas = new Vector<>();
    private Vector<Integer> precioPuntos = new Vector<>();
    private Vector<Integer> precioDinero = new Vector<>();
    //Contructores de clase****
    public Empleado() {
        comidas.add("Palomitas de Maiz");
        comidas.add("Chocolatina");
        comidas.add("Recarga de Gaseosa");
        comidas.add("Perro Caliente");
        precioPuntos.add(2000);
        precioPuntos.add(1700);
        precioPuntos.add(700);
        precioPuntos.add(2300);
        precioDinero.add(6000);
        precioDinero.add(5000);
        precioDinero.add(2000);
        precioDinero.add(7000);
    }

    public Empleado(String nombre) {
        this();
        this.setNombre(nombre);
    }
    //Metodos GET and SET****

    public Vector<String> getComidas() {
        return comidas;
    }

    public void setComidas(Vector<String> comidas) {
        this.comidas = comidas;
    }

    public Vector<Integer> getPrecioPuntos() {
        return precioPuntos;
    }

    public void setPrecioPuntos(Vector<Integer> precioPuntos) {
        this.precioPuntos = precioPuntos;
    }

    public Vector<Integer> getPrecioDinero() {
        return precioDinero;
    }

    public void setPrecioDinero(Vector<Integer> precioDinero) {
        this.precioDinero = precioDinero;
    }


    //Metodos de clase****

    /*Este metodo se ejecuta al inicio del programa sera el encargado de asignarle
     a cada cine que se lea en la base de datos, sus respectivas funciones,
     todos los datos cada generados son totalmente aleatorios*/
    public void enlazarFuncionesYCines() {
        for (Cine cine : BaseDeDatos.getCines()) {
            cine.funciones = new Vector<>();
            for (int i = 0; i < 7; i++) {
                Vector<String> nombreFunciones = new Vector<>();
                nombreFunciones.add("Pelicula 1");
                nombreFunciones.add("Pelicula 2");
                nombreFunciones.add("Pelicula 3");
                nombreFunciones.add("Pelicula 4");
                nombreFunciones.add("Pelicula 5");
                nombreFunciones.add("Pelicula 6");
                nombreFunciones.add("Pelicula 7");
                nombreFunciones.add("Pelicula 8");
                nombreFunciones.add("Pelicula 9");
                nombreFunciones.add("Pelicula 10");
                Vector<Integer> numeroAleatorio = new Vector<>();
                Vector<Funcion> funcioness = new Vector<>();
                for (int j = 0; j < 5; j++) {
                    int funcion;
                    funcion = (int) Math.floor(Math.random() * nombreFunciones.size());
                    if (!(numeroAleatorio.contains(funcion))) {
                        numeroAleatorio.add(funcion);
                    }
                }
                for (Integer numeroFuncion : numeroAleatorio) {
                    funcioness.add(new Funcion(nombreFunciones.get(numeroFuncion), numeroFuncion % 4, (numeroFuncion * 14) % 23));
                }
                cine.funciones.add(funcioness);
            }
        }
    }

    /*Cuando se crean las funciones en el metodo de arriba, todas se crean con la fecha actual
    este metodo se encarga de crear las funciones disponibles de los proximos 7 dias */
    public void definirSemanaFunciones() {
        for (Cine cine : BaseDeDatos.getCines()) {
            int dia = 0;
            for (Vector<Funcion> a : cine.getFunciones()) {
                for (Funcion funcion : a) {
                    funcion.sumarFecha(dia);
                }
                dia++;
            }
        }
    }

    /*Metodo que se encarga de comprarar si el usuario que intenta ingresar esta registrado en la base de datos*/
    public boolean comprobarRegistro(int identificacion) {
        boolean confirmaContrasenia = false;
        for (Cliente cliente : BaseDeDatos.getClientes()) {
            if (cliente.getIdentificacion() == identificacion) {
                confirmaContrasenia = true;
                Cliente.setClienteActual(cliente);
            }
        }
        return confirmaContrasenia;
    }
    public void comprobarRegistro(String lol) throws Exception  {
    	if(lol.equals("")) {
    		throw new blankText_Exception("Por favor llenar todos los espacios");
    	}
    	else {
    		throw new invalidDataType_Exception("Identificacion Invalida: For input string: " + lol);
    	}
    }
    

    /*Metodo encargado de registrar clientes*/
    public static void registarCliente(int identificacion, String nombre, String correo, String direccion) {
        Cliente usuario = new Cliente(identificacion, nombre, correo, direccion);
        CuentaBancaria cuentasBancaria = new CuentaBancaria(usuario);
        CuentaPuntos cuentaPuntos = new CuentaPuntos(usuario);
        usuario.setCuentaBancaria(cuentasBancaria);
        usuario.setCuentaPuntos(cuentaPuntos);
        BaseDeDatos.addCliente(usuario);
        BaseDeDatos.addCuentaBancaria(cuentasBancaria);
        BaseDeDatos.addCuentaPuntos(cuentaPuntos);
    }

    /*Metodo el cual se encarga de mostrar todas las ciudades en las cuales hay salas de cine disponibles*/
    public String consultarCines() {
        StringBuilder s = new StringBuilder();
        s.append("     Ciudades en las cuales tenemos cobertura: \n");
        s.append("    ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n");
        for (Cine cine : BaseDeDatos.getCines()) {
            if (!(Cine.getCiudades().contains(cine.getCiudad()))) {
                Cine.getCiudades().add(cine.getCiudad());
            }
        }
        int contador = 1;
        for (String ciudad : Cine.getCiudades()) {
            s.append(contador).append(". ").append(ciudad).append("\n");
            contador++;
        }
        s.delete(s.length() - 1, s.length());
        return s.toString();
    }

    /*Metodo que se encarga de mostrarle al usuario todas las salas de cine en la ciudad elegida (En un vector)*/
    public Vector<Cine> cinesPorCiudad(String ciudad) {
        Vector<Cine> cines = new Vector<>();
        for (Cine cine : BaseDeDatos.getCines()) {
            if (cine.getCiudad().equals(ciudad)) {
                cines.add(cine);
            }
        }
        return cines;
    }

    /*Metodo que se encarga de mostrarle al usuario todas las salas de cine en la ciudad elegida
    (En forma de lista para imprimir en consola)*/
    public String cinesPorCiudad(Vector<Cine> ciudades) {
        int contador = 1;
        StringBuilder s = new StringBuilder();
        for (Cine cine : ciudades) {
            s.append(contador).append(". ").append(cine.getNombre()).append("\n");
            contador += 1;
        }
        s.delete(s.length() - 1, s.length());
        return s.toString();
    }

    public String registrarCine(String nombre, String ciudad, String direccion) {
        Cine cine = new Cine(nombre, ciudad, direccion);
        BaseDeDatos.addCine(cine);
        return "            Cine agregado correctamente";
    }

    /*Metodo que se encargara de que no se registren/ingresen usuarios duplicados
      (mismo numero de identidificacion) en la base de datos*/
    public boolean comprobarIdentificacion(int identificacion) {
        boolean estado = false;
        for (Cliente cliente : BaseDeDatos.getClientes()) {
            if (cliente.getIdentificacion() == identificacion) {
                estado = true;
                break;
            }
        }
        return estado;
    }


    // desde aca es codigo para el cabio de asientos//////////////////////////////////////////


    //comprueba si el numero dado corresponde a una funcion en reserva
    public boolean probarFuncion(int numero, Cliente cliente){
        if (numero>cliente.cartera.size()){
            return true;
        }
        else{
            return false;
        }
    }
    //retorna la cantidad de asintos con los que cunta reservados para la funcion a cambiar
    public int numeroDeAsientos(Cliente cliente, int numero){
        return cliente.cartera.get(numero).getNumeroAsientos();
    }
    //vacia los puestos que se tenian reservados para poder eligir otros
    public void vaciarReserva(Funcion funcion,Vector<Integer> vector){
        for (Integer integer : vector) {
            funcion.vaciarPuesto(integer);
        }
    }
    //lena los puesos de la funcion
    public void cambiarPuestos(Funcion funcion, Vector<Integer> vector){
        funcion.reasignar(vector);
    }

    //descuenta  dinero o puntos del usuario por compras etc.
    public String transaccionDinero(Cliente cliente, int cantidad){
        if(cliente.getCuentaBancaria().getSaldo() >= cantidad){
            cliente.getCuentaBancaria().setSaldo(cliente.getCuentaBancaria().getSaldo() - cantidad);
            return("               ¡Transaccion exitosa!");
        }
        else{
            return("                ¡Saldo insuficiente!");
        }
    }
    public String transaccionPuntos(Cliente cliente, int cantidad){
        if(cliente.getCuentaPuntos().getPuntos() >= cantidad){
            cliente.getCuentaPuntos().setPuntos(cliente.getCuentaPuntos().getPuntos() - cantidad);
            return("               ¡Transaccion exitosa!");
        }
        else{
            return("                Saldo insuficiente");
        }
    }

    /*Metodo para verificar si el cliente tiene el dinero suficiente*/
    public boolean verificarDinero(Cliente cliente,int dinero){
        boolean tieneDinero = false;
        if(cliente.getCuentaBancaria().getSaldo()>=dinero){
            tieneDinero = true;
        }
        return tieneDinero;
    }

    /*Metodo para verificar si el cliente tiene puntos suficientes*/
    public boolean verificarPuntos(Cliente cliente,int puntos){
        boolean tienePuntos = false;
        if(cliente.getCuentaPuntos().getPuntos()>=puntos){
            tienePuntos = true;
        }
        return tienePuntos;
    }

    public String mostrarComidad(){
        StringBuilder s = new StringBuilder();
        for(int i= 0; i < comidas.size(); i++){
            s.append(i+1).append(". ").append(getComidas().get(i))
                    .append(" || $").append(precioDinero.get(i))
                    .append(" || ").append(precioPuntos.get(i)).append("P ||").append("\n");
        }
        return s.toString();
    }

    public String comprarComidas(Cliente cliente,int metodoPago, int cantidad, int opcionComida){
        String s;
        if(metodoPago == 1){
            int precioActualDinero = cantidad*precioDinero.get(opcionComida-1);
            s = transaccionDinero(cliente,precioActualDinero);
        }else{
            int precioActualActualPuntos = cantidad*precioPuntos.get(opcionComida-1);
            s = transaccionPuntos(cliente,precioActualActualPuntos);
        }
        return s;
    }

    public StringBuilder funcionesDisponibles(Cliente cliente){
        LocalDateTime hoy = LocalDateTime.now();
        StringBuilder s = new StringBuilder();
        for(Reserva reserva: cliente.getCartera()){
            if(reserva.getFecha().compareTo(hoy)>0){
                s.append(reserva).append("\n");
                s.append("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n");
            }
        }
        s.delete(s.length() - 1, s.length());
        return s;
    }

    public int numeroFuncionesDisponibles(Cliente cliente){
        LocalDateTime hoy = LocalDateTime.now();
        int cantidadFuncionesActivas = 0;
        for(Reserva reserva: cliente.getCartera()){
            if(reserva.getFecha().compareTo(hoy)>0){
                cantidadFuncionesActivas++;
            }
        }
        return cantidadFuncionesActivas;
    }


    public String recargarCuentaBancaria(Cliente clienteActual, int recarga){
        String s;
        recarga = recarga + clienteActual.getCuentaBancaria().getSaldo();
        clienteActual.getCuentaBancaria().setSaldo(recarga);
        return s = "Recarga hecha con exito, tu nuevo saldo es: $"+ clienteActual.getCuentaBancaria().getSaldo();

    }

    public boolean comprobarSiHayReservas(Cliente cliente){
        boolean tieneReservas = false;
        if(cliente.getCartera().size()>0){
            tieneReservas = true;
        }
        return tieneReservas;
    }

    public void cambiarNuevosAsientos(Cliente cliente, int opcion,Vector<Integer> puestosNuevos){
        cliente.cartera.get(opcion).setAsientosElegidos(puestosNuevos);
    }

    public StringBuilder mostrarSemana(){
        LocalDate fechaHoy = LocalDate.now();
        fechaHoy.plusDays(1);
        int contador = 0;
        StringBuilder s = new StringBuilder();
        for(int i = 0; i <7;i++){
            s.append(contador+". ").append(fechaHoy.format(DateTimeFormatter.ofPattern("d/M/yyyy")) + "\n");
            contador++;
            fechaHoy = fechaHoy.plusDays(1);
        }
        s.delete(s.length()-1,s.length());
        s.delete(0,13);
        return s;
    }

    public StringBuilder consultarSaldo(Cliente cliente){
        StringBuilder s = new StringBuilder();
        s.append(cliente.getCuentaBancaria().getSaldo());
        return s;
    }

    public StringBuilder consultarPuntos(Cliente cliente){
        StringBuilder s = new StringBuilder();
        s.append(cliente.getCuentaPuntos().getPuntos());
        return s;
    }


}
