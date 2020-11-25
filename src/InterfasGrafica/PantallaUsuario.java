package InterfasGrafica;

import InterfasGrafica.Ventanas.VentanaGenerica;
import InterfasGrafica.Ventanas.VentanaInformacion;
import ManejoExcepciones.invalidDataType_Exception;
import ManejoExcepciones.invalidData_Exception;
import ManejoExcepciones.saldo_Exception;
import baseDatos.BaseDeDatos;
import baseDatos.Escribir;
import gestorAplicacion.master.Cine;
import gestorAplicacion.master.Empleado;
import gestorAplicacion.master.Funcion;
import gestorAplicacion.usuario.Cliente;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Vector;

//Esta sera la pantalla que interactuara con el usuario
public class PantallaUsuario extends Application {

    public static Empleado empleado = new Empleado();
    public static Cliente cliente = new Cliente();

    public void init() {

    }

    /*En caso de que algunos usuarios intenten salir desde la 2 pantalla guardaremos los datos*/
    public void stop() {
        Escribir.Escribir();
    }

    /*Creo este metodo para no tener que darle estilo cada que creo un label, si no directamente pasarlo por este
    metodo y que quede definido su propio estilo*/
    public void definirEstilo(Label label, int tamanio) {
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Arial Black", tamanio));
    }

    /*Este temodo nos ayudara a que cada vez que creemos un FieldPanel podemos centrarlo en la posicion
      que queramos (Dado que al FieldPane heredar de pane, no se puede centrar dinamicamente) con este
      metodo si lo podremos hacer*/
    public GridPane centarFieldPanel(FieldPanel fieldPanel) {
        GridPane auxiliar = new GridPane();
        auxiliar.add(fieldPanel, 0, 0);
        auxiliar.setAlignment(Pos.CENTER);
        BorderPane.setMargin(auxiliar, new Insets(0, 0, 25, 0));
        return auxiliar;
    }

    /*Metodo que me ayuda a definir el estilo de los botones*/
    public void definirBotones(Button boton) {
        boton.setMinHeight(40);
        boton.setMinWidth(60);
        boton.setAlignment(Pos.CENTER);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        /*Creacion de los diferentes componentes que vamos a usar*/
        VBox panelPrincipal = new VBox();
        BorderPane panelEstructura = new BorderPane();
        Label arriba = new Label("Nombre del proceso o consulta");

        /*Creamos los diferentes menus que se van a usar*/
        MenuBar menu = new MenuBar();
        Menu archivo = new Menu("Archivo");
        Menu procesos = new Menu("Procesos y consultas");
        Menu ayuda = new Menu("Ayuda");
        MenuItem usuario = new MenuItem("Usuario");
        MenuItem salir = new MenuItem("Salir");
        MenuItem acercaDe = new MenuItem("Acerca de");
        MenuItem comprarBoleto = new MenuItem("Comprar boletos");
        MenuItem consultarReservas = new MenuItem("Consultar reservas");
        MenuItem cambiarReservas = new MenuItem("Cambiar reserva");
        MenuItem comprarComidas = new MenuItem("Comprar comida");

        /*Definimos los conponentes de cada menu*/
        menu.getMenus().addAll(archivo, procesos, ayuda);
        archivo.getItems().addAll(usuario, new SeparatorMenuItem(), salir);
        procesos.getItems().addAll(comprarBoleto, new SeparatorMenuItem(), consultarReservas, new SeparatorMenuItem(), cambiarReservas, new SeparatorMenuItem(), comprarComidas);
        ayuda.getItems().add(acercaDe);

        /*Ponemos el titulo por defecto a la escena y le pasamos el menu*/
        definirEstilo(arriba, 25);
        arriba.setAlignment(Pos.CENTER);
        panelPrincipal.getChildren().addAll(menu, arriba);
        panelPrincipal.setAlignment(Pos.CENTER);

        /*Ponemos todos los elementos en el panel estructura y los acomodamos en su respectiva posicion
          tambien les damos un poco de estilo*/
        Label medio = new Label("*Espacio donde se realizaran las consultas*");
        Label abajo = new Label("En este espacio el usuario tendra la capacidad de comprar boletos, cancelar \n                                  reservas hechas y canjear sus puntos.");
        definirEstilo(medio, 20);
        definirEstilo(abajo, 15);
        panelEstructura.setTop(panelPrincipal);
        panelEstructura.setCenter(medio);
        panelEstructura.setBottom(abajo);
        panelEstructura.setStyle("-fx-background-color: BLACK");
        BorderPane.setMargin(arriba, new Insets(500, 0, 0, 0)); //Pero este no :(
        BorderPane.setMargin(abajo, new Insets(0, 0, 25, 0)); //Esto si funciona
        BorderPane.setAlignment(arriba, Pos.CENTER);
        BorderPane.setAlignment(medio, Pos.CENTER);
        BorderPane.setAlignment(abajo, Pos.CENTER);

        /*Metodos que me permiten ajustar las caracteristicas de primaryStage*/
        primaryStage.setTitle("Cliente: " + Cliente.getClienteActual().getNombre());
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        /*Creamos la escena*/
        Scene escenaPrincipal = new Scene(panelEstructura, 700, 700);

        /*Pasamos la escena al stage*/
        primaryStage.setScene(escenaPrincipal);
        primaryStage.show();

        /*Espacio para definir cada uno de los eventos que se produzcan*/

        /*Aca defimos que pasa cuando el usuario interactua con el menuItem Salir*/
        salir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                VentanaGenerica.pantallaInicio.show();
            }
        });

        /*Aca definimos que pasa cuando el usuario interactua con el menuItem Usuario*/
        usuario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String usuario = Cliente.getClienteActual().toString();
                /*Mostramos todos los datos del usario que ha entrado al siste,a*/
                VentanaInformacion.showing("Usuario", usuario, "Aceptar", 500, 200);
            }
        });

        /*Aca definimos que pasa cuando el usuario interactua con el menuItem Acerda de*/
        acercaDe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StringBuilder mensaje = new StringBuilder();
                mensaje.append("     DESARROLLADORES\n")
                        .append("\nSepulveda Daniel Alejandro\n")
                        .append("Ospina Amilder Stewin\n")
                        .append("Moreno Gelier Esteban\n")
                        .append("Molano Kevin Andres\n");
                String usuario = Cliente.getClienteActual().toString();
                /*Mostramos la informacion de los desarrolladores en una ventana emergente*/
                VentanaInformacion.showing("Acerda de", mensaje.toString(), "Aceptar", 500, 220);
            }
        });

        /*Aca definimos que pasa cuando el usuario interactua con el menuItem Compro boleto*/
        comprarBoleto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //Definimos lo que sera el nuevo titulo
                Label titulo = new Label("Compra de boletos");
                definirEstilo(titulo, 25);

                /*Removemos los dos elementos que se tiene y ponemos los que necesitamos ahora*/
                panelPrincipal.getChildren().remove(0);
                panelPrincipal.getChildren().remove(0);
                panelPrincipal.getChildren().addAll(menu, titulo);

                /*Definimos lo que sera el nuevo centro del Panel*/
                Label ciudad = new Label(empleado.consultarCines());
                definirEstilo(ciudad, 15);
                panelEstructura.setCenter(ciudad);

                /*Pedimos los datos con el FieldPanel*/
                String[] criterios = new String[]{"Seleccione la ciudad deseada"};
                String[] valores = new String[]{""};
                boolean[] habilitados = new boolean[]{};
                FieldPanel probando = new FieldPanel("", criterios, "", valores, habilitados);

                /*Con esto podemos centrar el fieldPanel*/
                GridPane auxiliar = centarFieldPanel(probando);
                panelEstructura.setBottom(auxiliar);

                /*Con esto recibimos la ciudad en la que el usuario quiere ver las funciones*/
                probando.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            probando.GuardarDatos();
                        } catch (Exception e) {
                            if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                        		alert.setHeaderText(null);
                        		alert.setContentText(e.getMessage());
                        		alert.showAndWait();
                            }
                            else if(e.getMessage().contains("Array index out of range:"))  {
                            	invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                        		alert.setHeaderText(null);
                        		alert.setContentText(a.getMessage());
                        		alert.showAndWait();
                            }
                            
                        }

                        /*Aca conseguimos todas las salas de cine en la ciudad que el usuario ha elegido y se las mostramos*/
                        
                        try {
                        	Vector<Cine> salasXCiudad = empleado.cinesPorCiudad(Cine.getCiudades().get(Integer.parseInt(valores[0]) - 1));
                        	Label cinesPorCiudad = new Label(" Salas de cine disponibles en la ciudad:\n" + "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯  \n" + empleado.cinesPorCiudad(salasXCiudad));
                            definirEstilo(cinesPorCiudad, 15);
                            panelEstructura.setCenter(cinesPorCiudad);

                            /*Ahora debemos crear un nuevo FieldPanel y volver a repetir el proceso
                              para pedir los datos de la sala:'v*/
                            String[] criteriosSala = new String[]{"Seleccione la sala deseada"};
                            String[] valoresSala = new String[]{""};
                            boolean[] habilitadosSala = new boolean[]{};
                            FieldPanel sala = new FieldPanel("", criteriosSala, "", valoresSala, habilitadosSala);

                            /*GridPanel auxiliar para poder centra*/
                            GridPane auxiliarSala = centarFieldPanel(sala);
                            panelEstructura.setBottom(auxiliarSala);

                            /*Ahora seguimos con la opcion de aceptar cuando igresa el usuario la ciudad*/
                            sala.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    try {
                                        sala.GuardarDatos();
                                    } catch (Exception e) {
                                    	if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                                        	Alert alert = new Alert(Alert.AlertType.WARNING);
                                    		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                    		alert.setHeaderText(null);
                                    		alert.setContentText(e.getMessage());
                                    		alert.showAndWait();
                                        }
                                        else if(e.getMessage().contains("Array index out of range:"))  {
                                        	invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                                        	Alert alert = new Alert(Alert.AlertType.WARNING);
                                    		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                    		alert.setHeaderText(null);
                                    		alert.setContentText(a.getMessage());
                                    		alert.showAndWait();
                                        }
                                    }
                                    /*Mostramos la semana al usuario para que pueda elegir un dia*/
                                    Label mostrarSemana = new Label("  Ha elegio el cine " + salasXCiudad.get(Integer.parseInt(valoresSala[0]) - 1).getNombre() + "\n" + "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯" + empleado.mostrarSemana().toString());
                                    definirEstilo(mostrarSemana, 15);
                                    panelEstructura.setCenter(mostrarSemana);

                                    /*Creamos el nuevo FieldPanel para poder pedir el dia de la funcion*/
                                    String[] criteriosDia = new String[]{"Elija el dia que desea reservar"};
                                    String[] valoresDia = new String[]{""};
                                    boolean[] habilitadosDia = new boolean[]{};
                                    FieldPanel diaFuncion = new FieldPanel("", criteriosDia, "", valoresDia, habilitadosDia);

                                    /*Panel auxiliar para poder centrar lo que necesitamos*/
                                    GridPane auxiliarDia = centarFieldPanel(diaFuncion);
                                    panelEstructura.setBottom(auxiliarDia);

                                    /*Aca pediremos el dia*/
                                    diaFuncion.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            try {
                                                diaFuncion.GuardarDatos();
                                            } catch (Exception e) {
                                            	if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                                                	Alert alert = new Alert(Alert.AlertType.WARNING);
                                            		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                            		alert.setHeaderText(null);
                                            		alert.setContentText(e.getMessage());
                                            		alert.showAndWait();
                                                }
                                                else if(e.getMessage().contains("Array index out of range:"))  {
                                                	invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                                                	Alert alert = new Alert(Alert.AlertType.WARNING);
                                            		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                            		alert.setHeaderText(null);
                                            		alert.setContentText(a.getMessage());
                                            		alert.showAndWait();
                                                }
                                            }

                                            /*En este vector estan guardadas todas las funciones del dia que se selecciono*/
                                            Vector<Funcion> funcionesDia = Cliente.getClienteActual().consultarFunciones(Integer.parseInt(valoresDia[0]), salasXCiudad.get(Integer.parseInt(valoresSala[0]) - 1));
                                            Label funcionesDelDia = new Label("Funciones del día y sus precios en\n" + "           [dinero($) || puntos(P)]\n" + "         ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" + Cliente.getClienteActual().consultarFunciones(funcionesDia).toString());
                                            definirEstilo(funcionesDelDia, 15);

                                            /*Ponemos todas las funciones del dia elegido en el centro de la escena*/
                                            panelEstructura.setCenter(funcionesDelDia);

                                            /*Definimos un nuevo FieldPanel para pedir los datos de la funcion que se quiere ver*/
                                            String[] criteriosFuncion = new String[]{"Elija la funcion que desea"};
                                            String[] valoresFuncion = new String[]{""};
                                            boolean[] habilitadosFuncion = new boolean[]{};
                                            FieldPanel funcion = new FieldPanel("", criteriosFuncion, "", valoresFuncion, habilitadosFuncion);
                                            GridPane auxiliarFuncion = centarFieldPanel(funcion);

                                            /*Centramos el FieldPanel*/
                                            panelEstructura.setBottom(auxiliarFuncion);

                                            /*Aca pediremos la informacion sobre la funcion que se desea ver*/
                                            funcion.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                @Override
                                                public void handle(MouseEvent event) {
                                                    try {
                                                        funcion.GuardarDatos();
                                                    } catch (Exception e) {
                                                    	if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                                                        	Alert alert = new Alert(Alert.AlertType.WARNING);
                                                    		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                    		alert.setHeaderText(null);
                                                    		alert.setContentText(e.getMessage());
                                                    		alert.showAndWait();
                                                        }
                                                        else if(e.getMessage().contains("Array index out of range:"))  {
                                                        	invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                                                        	Alert alert = new Alert(Alert.AlertType.WARNING);
                                                    		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                    		alert.setHeaderText(null);
                                                    		alert.setContentText(a.getMessage());
                                                    		alert.showAndWait();
                                                        }
                                                    }
                                                    /*Mostramos los asientos disponibles en el centro de la pantalla*/
                                                    Label asientos = new Label(funcionesDia.get(Integer.parseInt(valoresFuncion[0]) - 1).mostrarPuestos());
                                                    definirEstilo(asientos, 15);
                                                    panelEstructura.setCenter(asientos);

                                                    /*Hacemos otro FieldPanel para pedir el asiento(Please stop :'v)*/
                                                    String[] criteriosPuesto = new String[]{"Elija su puesto"};
                                                    String[] valoresPuesto = new String[]{""};
                                                    boolean[] habilitadosPuesto = new boolean[]{};
                                                    FieldPanel puestoElegir = new FieldPanel("", criteriosPuesto, "", valoresPuesto, habilitadosPuesto);

                                                    /*Lo centramos y lo acomodamos*/
                                                    GridPane auxiliarPuesto = centarFieldPanel(puestoElegir);
                                                    panelEstructura.setBottom(auxiliarPuesto);

                                                    /*Aca elegiremos el dia*/
                                                    puestoElegir.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                        @Override
                                                        public void handle(MouseEvent event) {
                                                            try {
                                                                puestoElegir.GuardarDatos();
                                                            } catch (Exception e) {
                                                            	if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                                                                	Alert alert = new Alert(Alert.AlertType.WARNING);
                                                            		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                            		alert.setHeaderText(null);
                                                            		alert.setContentText(e.getMessage());
                                                            		alert.showAndWait();
                                                                }
                                                                else if(e.getMessage().contains("Array index out of range:"))  {
                                                                	invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                                                                	Alert alert = new Alert(Alert.AlertType.WARNING);
                                                            		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                            		alert.setHeaderText(null);
                                                            		alert.setContentText(a.getMessage());
                                                            		alert.showAndWait();
                                                                }
                                                            }
                                                            /*Le mostramos al usuario su saldo*/
                                                            Label frase = new Label("Por favor elija su medio de pago\n" + "Saldo en dinero: " + Cliente.getClienteActual().getCuentaBancaria().getSaldo() + "\n" +
                                                                    "Saldo en puntos: " + Cliente.getClienteActual().getCuentaPuntos().getPuntos());
                                                            definirEstilo(frase, 15);
                                                            panelEstructura.setCenter(frase);

                                                            /*Mostramos al usuario los botones que corresponden a los medios de pago disponibles
                                                             los centramos, damos margenes y estilos*/
                                                            Button pagarDinero = new Button("Pagar con dinero");
                                                            Button pagarPuntos = new Button("Pagar con puntos");
                                                            GridPane menuPago = new GridPane();
                                                            menuPago.add(pagarDinero, 0, 0);
                                                            menuPago.add(pagarPuntos, 1, 0);
                                                            menuPago.setVgap(8);
                                                            menuPago.setHgap(8);
                                                            menuPago.setAlignment(Pos.CENTER);
                                                            definirBotones(pagarDinero);
                                                            definirBotones(pagarPuntos);
                                                            panelEstructura.setBottom(menuPago);
                                                            BorderPane.setMargin(menuPago, new Insets(0, 0, 25, 0));

                                                            /*Este evento se desarrola cuando el usaurio ha elegido pagar con dinero*/
                                                            pagarDinero.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                                @Override
                                                                public void handle(MouseEvent event) {
                                                                	
                                                                    if (Cliente.getClienteActual().getCuentaBancaria().getSaldo() >= 20000) {

                                                                        /*En este vector almacenamos el numero del puesto que se ha elegido*/
                                                                        Vector<Integer> puestoElegido = new Vector<Integer>();
                                                                        puestoElegido.add(Integer.parseInt(valoresPuesto[0]));

                                                                        /*Reservamos el puesto*/
                                                                        Cliente.getClienteActual().reservarPuestos(puestoElegido, funcionesDia.get(Integer.parseInt(valoresFuncion[0]) - 1));

                                                                        /*Sacamos el dinero de la cuenta de la persona*/
                                                                        empleado.transaccionDinero(Cliente.getClienteActual(), 20000);

                                                                        /*Con esto cerramos el stage actual, lo volvemos a ejecutar desde el princio y mostramos una ventana
                                                                         de confirmacion*/
                                                                        primaryStage.close();
                                                                        try {
                                                                            start(new Stage());
                                                                        } catch (Exception e) {
                                                                        	if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                                                                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                                                                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                                        		alert.setHeaderText(null);
                                                                        		alert.setContentText(e.getMessage());
                                                                        		alert.showAndWait();
                                                                            }
                                                                            else if(e.getMessage().contains("Array index out of range:"))  {
                                                                            	invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                                                                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                                                                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                                        		alert.setHeaderText(null);
                                                                        		alert.setContentText(a.getMessage());
                                                                        		alert.showAndWait();
                                                                            }
                                                                        }
                                                                        VentanaInformacion.showing("Confirmar compra", "Reserva hecha satisfacctoriamente", "Aceptar", 400, 100);

                                                                        /*En caso de que no cuente con el saldo suficiente, lo devolvemos al princio y mostramos una ventana de error*/
                                                                    } else {
                                                                        primaryStage.close();
                                                                        try {
                                                                            start(new Stage());
                                                                        } catch (Exception e) {
                                                                        	if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                                                                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                                                                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                                        		alert.setHeaderText(null);
                                                                        		alert.setContentText(e.getMessage());
                                                                        		alert.showAndWait();
                                                                            }
                                                                            else if(e.getMessage().contains("Array index out of range:"))  {
                                                                            	invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                                                                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                                                                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                                        		alert.setHeaderText(null);
                                                                        		alert.setContentText(a.getMessage());
                                                                        		alert.showAndWait();
                                                                            }
                                                                        }
                                                                        
                    
                                                                    }
                                                                }
                                                            });

                                                            /*Este evento se desarrolla cuando el usuario ha elegido pagar con puntos
                                                              es exactamente igual al de arriba solo que ahora se sacan puntos en vez
                                                              de dinero*/
                                                            pagarPuntos.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                                @Override
                                                                public void handle(MouseEvent event) {
                                                                    if (Cliente.getClienteActual().getCuentaPuntos().getPuntos() >= 3000) {
                                                                        Vector<Integer> puestoElegido = new Vector<Integer>();
                                                                        puestoElegido.add(Integer.parseInt(valoresPuesto[0]));
                                                                        Cliente.getClienteActual().reservarPuestos(puestoElegido, funcionesDia.get(Integer.parseInt(valoresFuncion[0]) - 1), true);
                                                                        empleado.transaccionPuntos(Cliente.getClienteActual(), 3000);
                                                                        primaryStage.close();
                                                                        try {
                                                                            start(new Stage());
                                                                        } catch (Exception e) {
                                                                        	if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                                                                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                                                                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                                        		alert.setHeaderText(null);
                                                                        		alert.setContentText(e.getMessage());
                                                                        		alert.showAndWait();
                                                                            }
                                                                            else if(e.getMessage().contains("Array index out of range:"))  {
                                                                            	invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                                                                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                                                                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                                        		alert.setHeaderText(null);
                                                                        		alert.setContentText(a.getMessage());
                                                                        		alert.showAndWait();
                                                                            }
                                                                        }
                                                                        VentanaInformacion.showing("Confirmar compra", "Reserva hecha satisfacctoriamente", "Aceptar", 400, 100);
                                                                    } else {
                                                                        primaryStage.close();
                                                                        try {
                                                                            start(new Stage());
                                                                        } catch (Exception e) {
                                                                        	if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                                                                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                                                                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                                        		alert.setHeaderText(null);
                                                                        		alert.setContentText(e.getMessage());
                                                                        		alert.showAndWait();
                                                                            }
                                                                            else if(e.getMessage().contains("Array index out of range:"))  {
                                                                            	invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                                                                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                                                                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                                        		alert.setHeaderText(null);
                                                                        		alert.setContentText(a.getMessage());
                                                                        		alert.showAndWait();
                                                                            }
                                                                        }
                                                                        new saldo_Exception("Saldo Insuficiente");
                                                                    }
                                                                }
                                                            });
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        } catch(Exception e) {
                        	System.out.println("si funciona");
                        	if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                        		alert.setHeaderText(null);
                        		alert.setContentText(e.getMessage());
                        		alert.showAndWait();
                            }
                            else if(e.getMessage().contains("Array index out of range:"))  {
                            	invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                        		alert.setHeaderText(null);
                        		alert.setContentText(a.getMessage());
                        		alert.showAndWait();
                            }
                            else {
                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                            	invalidDataType_Exception a = new invalidDataType_Exception("Tipo de dato invalido");
                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                        		alert.setHeaderText(null);
                        		alert.setContentText("Tipo de dato invalido: " + e.getMessage());
                        		alert.showAndWait();
                            }
                        }
                    }
                });
            }
        });

        /*200 lineas solo para comprar un boleto que sap*/

        /*Evento que se desarrolla cuando el usuario intenta consultar sus reservas*/
        consultarReservas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*Cambiamos el el titulo*/
                Label tituloReserva = new Label("Consultar reservas");
                panelPrincipal.getChildren().remove(0);
                panelPrincipal.getChildren().remove(0);
                definirEstilo(tituloReserva, 25);
                panelPrincipal.getChildren().addAll(menu, tituloReserva);

                /*Mostramos las reservas activas y vencidad que tenga el usuario*/
                Label reservasConsulta = new Label(Cliente.getClienteActual().consultarReservas());
                definirEstilo(reservasConsulta, 15);
                panelEstructura.setCenter(reservasConsulta);

                /*Cremos un boton para poder volver y lo ponemos en su respectiva posicion*/
                Button volverReserva = new Button("Volver");
                definirBotones(volverReserva);
                panelEstructura.setBottom(volverReserva);
                BorderPane.setAlignment(volverReserva, Pos.CENTER);
                BorderPane.setMargin(volverReserva, new Insets(0, 0, 25, 0));

                /*Evento que se desarrola cuando el usuario quiere volver al menu principal*/
                volverReserva.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        primaryStage.close();
                        try {
                            start(new Stage());
                        } catch (Exception e) {
                        	if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                        		alert.setHeaderText(null);
                        		alert.setContentText(e.getMessage());
                        		alert.showAndWait();
                            }
                            else if(e.getMessage().contains("Array index out of range:"))  {
                            	invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                            	Alert alert = new Alert(Alert.AlertType.WARNING);
                        		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                        		alert.setHeaderText(null);
                        		alert.setContentText(a.getMessage());
                        		alert.showAndWait();
                            }
                        }
                    }
                });

            }
        });

        /*Evento que se desarrolla cuando el usuario quiere cambiar su reserva*/
        cambiarReservas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*Cambiamos el titulo*/
                Label tituloCambioPuesto = new Label("Cambio de puesto");
                definirEstilo(tituloCambioPuesto, 25);
                panelPrincipal.getChildren().remove(0);
                panelPrincipal.getChildren().remove(0);
                panelPrincipal.getChildren().addAll(menu, tituloCambioPuesto);

                /*En caso de que el usuario no tenga reservas activas mostramos este mensaje y el usuario no podra
                  hacer nada en este menu*/
                if (empleado.numeroFuncionesDisponibles(Cliente.getClienteActual()) <= 0) {
                    Label noFunciones = new Label("No reservas activas!!");
                    definirEstilo(noFunciones, 15);
                    panelEstructura.setCenter(noFunciones);

                    /*En Caso de que si se tengan reservas activas seguimos con esta opcion*/
                } else {
                    /*Le mostramos al usuario sus reservas activas*/
                    Label funcionesDisponibles = new Label(empleado.funcionesDisponibles(Cliente.getClienteActual()).toString());
                    definirEstilo(funcionesDisponibles, 15);
                    panelEstructura.setCenter(funcionesDisponibles);

                    /*Ahora con un FieldPanel pediremos el dato(Numero) de la reserva que desea cambiar*/
                    String[] criteriosCambio = new String[]{"Elija el numero de la reserva a cambiar"};
                    String[] valoresCambio = new String[]{""};
                    boolean[] habilitadosCambio = new boolean[]{};
                    FieldPanel puestoCambio = new FieldPanel("", criteriosCambio, "", valoresCambio, habilitadosCambio);
                    /*Centramos el FieldPanel*/
                    GridPane auxiliarCambio = centarFieldPanel(puestoCambio);
                    panelEstructura.setBottom(auxiliarCambio);

                    /*Con este evento podremos pedir ese numero de funcion a cambiar*/
                    puestoCambio.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            /*Pedimos los datos*/
                            try {
                                puestoCambio.GuardarDatos();
                                /*Con esto vaciamos los puestos para mostrarlos de nuevo*/
                                empleado.vaciarReserva(Cliente.getClienteActual().getCartera().get(Integer.parseInt(valoresCambio[0])).getFuncion(), Cliente.getClienteActual().getCartera().get(Integer.parseInt(valoresCambio[0])).getAsientosElegidos());

                                /*Mostramos los nuevos asientos para que se pueda elegir*/
                                Label nuevosAsiento = new Label(Cliente.getClienteActual().getCartera().get(Integer.parseInt(valoresCambio[0])).getFuncion().mostrarPuestos());
                                definirEstilo(nuevosAsiento, 15);
                                panelEstructura.setCenter(nuevosAsiento);

                                /*Con un FieldPanel vamos a pedir el nuevos asientos*/
                                String[] criteriosCambio1 = new String[]{"Elija el nuevo asiento"};
                                String[] valoresCambio1 = new String[]{""};
                                boolean[] habilitadosCambio1 = new boolean[]{};
                                Vector<Integer> puestosNuevos = new Vector<Integer>();
                                FieldPanel puestoCambio1 = new FieldPanel("", criteriosCambio1, "", valoresCambio1, habilitadosCambio1);
                                /*Centramos*/
                                GridPane auxiliarCambio1 = centarFieldPanel(puestoCambio1);
                                panelEstructura.setBottom(auxiliarCambio1);

                                /*Con este evento pediremos la informacion del asiento a cambiar*/
                                puestoCambio1.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        try {
                                            puestoCambio1.GuardarDatos();
                                            /*Obtenemos el numero del puesto que vamos a cambiar*/
                                            puestosNuevos.add(Integer.parseInt(valoresCambio1[0]));

                                            /*Metodo que me permite cambiar los puestos en la resetva*/
                                            empleado.cambiarPuestos(Cliente.getClienteActual().cartera.get(Integer.parseInt(valoresCambio[0])).getFuncion(), puestosNuevos);
                                            /*Metodo que me pone los asientos de la funcion como ocupados*/
                                            empleado.cambiarNuevosAsientos(Cliente.getClienteActual(), Integer.parseInt(valoresCambio[0]), puestosNuevos);

                                            /*Cerramos el stage actual para abrir uno desde 0 y mostramos una ventana de confirmacion de cambio*/
                                            primaryStage.close();
                                            try {
                                                start(new Stage());
                                            } catch (Exception e) {
                                                if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                                    alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText(e.getMessage());
                                                    alert.showAndWait();
                                                }
                                                else if(e.getMessage().contains("Array index out of range:"))  {
                                                    invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                                    alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                    alert.setHeaderText(null);
                                                    alert.setContentText(a.getMessage());
                                                    alert.showAndWait();
                                                }
                                            }
                                            VentanaInformacion.showing("Confirmar cambio de reserva", "Ha cambio su reserva correctamente!!", "Aceptar", 400, 100);
                                        } catch (Exception e) {
                                            if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                                alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                alert.setHeaderText(null);
                                                alert.setContentText(e.getMessage());
                                                alert.showAndWait();
                                            }
                                            else if(e.getMessage().contains("Array index out of range:"))  {
                                                invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                                alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                alert.setHeaderText(null);
                                                alert.setContentText(a.getMessage());
                                                alert.showAndWait();
                                            }
                                            else{
                                                System.out.println("Elegir el numero de asiento mal");  ///----------------
                                            }
                                        }

                                    }
                                });
                            } catch (Exception e) {
                            	if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                                	Alert alert = new Alert(Alert.AlertType.WARNING);
                            		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                            		alert.setHeaderText(null);
                            		alert.setContentText(e.getMessage());
                            		alert.showAndWait();
                                }
                                else if(e.getMessage().contains("Array index out of range:"))  {
                                	invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                                	Alert alert = new Alert(Alert.AlertType.WARNING);
                            		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                            		alert.setHeaderText(null);
                            		alert.setContentText(a.getMessage());
                            		alert.showAndWait();
                                }
                                else{
                                    System.out.println("Error en el tipo de dato de la reserva");  //------------------
                                }
                            }

                        }
                    });
                }

            }
        });

        /*Evento que se desarrolla cuando el usuario quiere comprar comida*/
        comprarComidas.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                /*Cambiamos el titulo de la escena*/
                Label tituloComida = new Label("Comprar comida");
                definirEstilo(tituloComida, 25);
                panelPrincipal.getChildren().remove(0);
                panelPrincipal.getChildren().remove(0);
                panelPrincipal.getChildren().addAll(menu, tituloComida);
                Label panelCentralComida = new Label(" Opciones de comida disponibles y sus precios en\n" + "                      [dinero($) || puntos(P)]\n\n" + empleado.mostrarComidad());
                definirEstilo(panelCentralComida, 15);
                panelEstructura.setCenter(panelCentralComida);

                /*Agregamos dos botones a la escena para que el usuario pueda elegir si pagar con dinero o con puntos*/
                Button pagoEnPuntos = new Button("Pagar con puntos");
                Button pagoEnDinero = new Button("Pagar con dinero");
                GridPane metodoPago = new GridPane();
                metodoPago.add(pagoEnDinero, 0, 0);
                metodoPago.add(pagoEnPuntos, 1, 0);
                metodoPago.setVgap(8);
                metodoPago.setHgap(8);
                metodoPago.setAlignment(Pos.CENTER);
                BorderPane.setMargin(metodoPago, new Insets(0, 0, 65, 0));
                panelEstructura.setBottom(metodoPago);

                /*Ahora con un FieldPanel vamos a pedir los datos de la opcion de comida y la cantidad
                  que se sea elegir*/
                String[] criteriosComida = new String[]{"Elija la opcion de comida", "Elija la cantidad deseada"};
                String[] valoresComida = new String[]{"", ""};
                boolean[] habilitadosComida = new boolean[]{};
                FieldPanel puestoComida = new FieldPanel("", criteriosComida, "", valoresComida, habilitadosComida);
                /*Centramos la informacion*/
                GridPane auxiliarComida = centarFieldPanel(puestoComida);

                /*Evento que se inicia cuando el usuario desea pagar con puntos*/
                pagoEnPuntos.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        panelEstructura.setBottom(auxiliarComida);
                        puestoComida.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                try {
                                    puestoComida.GuardarDatos();
                                } catch (Exception e) {
                                	if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                                    	Alert alert = new Alert(Alert.AlertType.WARNING);
                                		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                		alert.setHeaderText(null);
                                		alert.setContentText(e.getMessage());
                                		alert.showAndWait();
                                    }
                                    else if(e.getMessage().contains("Array index out of range:"))  {
                                    	invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                                    	Alert alert = new Alert(Alert.AlertType.WARNING);
                                		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                		alert.setHeaderText(null);
                                		alert.setContentText(a.getMessage());
                                		alert.showAndWait();
                                    }
                                }

                                try {
                                    /*Verificamos que el usuario tenga los puntos suficientes para poder comprar la cantidad que ha elegido*/
                                    if (Cliente.getClienteActual().getCuentaPuntos().getPuntos() >= empleado.getPrecioPuntos().get(Integer.parseInt(valoresComida[0]) - 1) * Integer.parseInt(valoresComida[1])) {
                                        /*Si el usuario si cuenta con los puntos suficientes ejecutamos un metodo que nos permite descontarle los puntos necesarios*/
                                        empleado.comprarComidas(Cliente.getClienteActual(), 0, Integer.parseInt(valoresComida[1]), Integer.parseInt(valoresComida[0]));

                                        /*Cerramos el stage actual, abrimos uno nuevo y mostramos una ventana de confirmacion de compra*/
                                        primaryStage.close();
                                        try {
                                            start(new Stage());
                                        } catch (Exception e) {
                                            if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                                alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                alert.setHeaderText(null);
                                                alert.setContentText(e.getMessage());
                                                alert.showAndWait();
                                            }
                                            else if(e.getMessage().contains("Array index out of range:"))  {
                                                invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                                alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                                alert.setHeaderText(null);
                                                alert.setContentText(a.getMessage());
                                                alert.showAndWait();
                                            }
                                        }
                                        VentanaInformacion.showing("Confirmar compra comida", "Ha comprado su comida correctamente!!", "Aceptar", 400, 100);

                                /*En caso de que el usuario no tenga los puntos suficientes, cerramos el stage, abrimos uno nuevo y
                                  mostramos una ventana de compra fallida*/
                                    } else {
                                        primaryStage.close();
                                        try {
                                            start(new Stage());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        new saldo_Exception("Saldo Insuficiente");
                                    }
                                }catch (Exception e) {
                                	Alert alert = new Alert(Alert.AlertType.WARNING);
                                	invalidDataType_Exception a = new invalidDataType_Exception("Tipo de dato invalido");
                            		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                            		alert.setHeaderText(null);
                            		alert.setContentText("Tipo de dato invalido: " + e.getMessage());
                            		alert.showAndWait();
                                }
                            }
                        });
                    }
                });

                /*Evento que se ejecuta cuando se intenta pagar con dinero, igual al ya explicado de pagar con puntos*/
                pagoEnDinero.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        panelEstructura.setBottom(auxiliarComida);
                        puestoComida.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                try {
                                    puestoComida.GuardarDatos();
                                } catch (Exception e) {
                                	if(e.getMessage().equals("Por favor llenar todos los espacios")) {
                                    	Alert alert = new Alert(Alert.AlertType.WARNING);
                                		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                		alert.setHeaderText(null);
                                		alert.setContentText(e.getMessage());
                                		alert.showAndWait();
                                    }
                                    else if(e.getMessage().contains("Array index out of range:"))  {
                                    	invalidData_Exception a = new invalidData_Exception("La opcion ingresada no existe");
                                    	Alert alert = new Alert(Alert.AlertType.WARNING);
                                		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                                		alert.setHeaderText(null);
                                		alert.setContentText(a.getMessage());
                                		alert.showAndWait();
                                    }
                                }
                                try {
                                    if (Cliente.getClienteActual().getCuentaBancaria().getSaldo() >= empleado.getPrecioDinero().get(Integer.parseInt(valoresComida[0]) - 1) * Integer.parseInt(valoresComida[1])) {
                                        empleado.comprarComidas(Cliente.getClienteActual(), 1, Integer.parseInt(valoresComida[1]), Integer.parseInt(valoresComida[0]));
                                        primaryStage.close();
                                        try {
                                            start(new Stage());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        VentanaInformacion.showing("Confirmar compra comida", "Ha comprado su comida correctamente!!", "Aceptar", 400, 100);
                                    } else {
                                        primaryStage.close();
                                        try {
                                            start(new Stage());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        new saldo_Exception("Saldo Insuficiente");
                                    }
                                }catch (Exception e) {
                                	Alert alert = new Alert(Alert.AlertType.WARNING);
                                	invalidDataType_Exception a = new invalidDataType_Exception("Tipo de dato invalido");
                            		alert.setTitle("Manejo de Errores de la Aplicacion: Cine Stewin");
                            		alert.setHeaderText(null);
                            		alert.setContentText("Tipo de dato invalido: " + e.getMessage());
                            		alert.showAndWait();
                                }

                            }
                        });
                    }
                });
            }
        });
    }
}
