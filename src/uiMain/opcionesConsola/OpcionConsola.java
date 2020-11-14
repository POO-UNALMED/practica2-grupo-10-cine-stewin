package uiMain.opcionesConsola;

import gestorAplicacion.master.Empleado;

import java.util.Scanner;

/*Esta interfaz sera la encargada de definir la estructura de todas las pantallas
  las cuales seran las encargadas de mostrar informacion por consola, cada
  opcion del menu, sera una clase la cual se encarga de mostrar su propia pantalla
  para interactuar con el usuario*/
public interface OpcionConsola {
    Empleado empleado = new Empleado();
    Autores autores = new Autores();
    ConsultarReservas consultarReservas = new ConsultarReservas();
    DatosInicio datosInicio = new DatosInicio();
    Descripcion descripcion = new Descripcion();
    DiaFuncion diaFuncion = new DiaFuncion();
    IngresoUsuario ingresoUsuario = new IngresoUsuario();
    MostrarCine mostrarCine = new MostrarCine();
    PantallaInicio pantallaInicio = new PantallaInicio();
    Puntos puntos = new Puntos();
    MenuDesarrollador desarrollador = new MenuDesarrollador();
    Salir salir = new Salir();
    UsuarioNoRegistrado usuarioNoRegistrado = new UsuarioNoRegistrado();
    UsuarioRegistrado usuarioRegistrado = new UsuarioRegistrado();

    /*Esto nos servira para poder tener este mensaje de volver en cada una de las pantallas*/
    String mensajeVolver = "         ¿Desea volver al menu alterior?\n"+
            "1. Si\n"+"2. No\n" + "==================================================";
    /*De esta forma tenemos el scanner en todas las clases que implementen esta interfaz*/
    Scanner dato = new Scanner(System.in);
    /*Este String es el encargado de separar los diferentes menus/pantallas con las cuales
      el usuario interactuara*/
    String separador = "==================================================";

    /*Este metodo, sera el principal de cada pantalla, encargado de definirme que acciones
      tendra cada clase/menu */
    void ejecutar();

    /*Metodo que define cada clase que implemente la interfaz para vollver al menu inmediatamente anterior*/
    void volver(int opcion);
}
