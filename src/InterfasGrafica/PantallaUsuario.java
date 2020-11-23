package InterfasGrafica;

import InterfasGrafica.Ventanas.Ingreso;
import InterfasGrafica.Ventanas.VentanaGenerica;
import InterfasGrafica.Ventanas.VentanaInformacion;
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
public class PantallaUsuario  extends Application {
    public static Empleado empleado = new Empleado();
    public static Cliente cliente = new Cliente();
    public void init(){

    }
    public void stop(){

    }
    /*Creo este metodo para no tener que darle estilo cada que creo un label, si no directamente pasarlo por este
    metodo y que quede definido su propio estilo*/
    public void definirEstilo(Label label, int tamanio){
        label.setTextFill(Color.WHITE);
        label.setFont(new Font("Arial Black",tamanio));
    }
    /*Este temodo nos ayudara a que cada vez que creemos un FieldPanel podemos centrarlo en la posicion
      que queramos (Dado que al FieldPane heredar de pane, no se puede centrar dinamicamente) con este
      metodo si lo podremos hacer*/
    public GridPane centarFieldPanel(FieldPanel fieldPanel){
        GridPane auxiliar = new GridPane();
        auxiliar.add(fieldPanel,0,0);
        auxiliar.setAlignment(Pos.CENTER);
        BorderPane.setMargin(auxiliar,new Insets(0,0,25,0));
        return auxiliar;
    }
    /*Metodo que me ayuda a definir el estilo de los botones*/
    public void definirBotones(Button boton){
        boton.setMinHeight(40);
        boton.setMinWidth(60);
        boton.setAlignment(Pos.CENTER);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Creacion de los diferentes componentes que vamos a usar
        VBox panelPrincipal = new VBox();
        BorderPane panelEstructura = new BorderPane();
        Label arriba = new Label("Nombre del proceso o consulta");

        //Creamos los diferentes menus que se van a usar
        MenuBar menu =  new MenuBar();
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

        /*Definir demas elementos del menu procesos*/
        //Definimos los conponentes de cada menu
        menu.getMenus().addAll(archivo,procesos,ayuda);
        archivo.getItems().addAll(usuario,new SeparatorMenuItem(),salir);
        procesos.getItems().addAll(comprarBoleto,new SeparatorMenuItem(),consultarReservas,new SeparatorMenuItem(),cambiarReservas,new SeparatorMenuItem(),comprarComidas);
        ayuda.getItems().add(acercaDe);

        //Metodos para definir estilos
        panelPrincipal.setStyle("-fx-background-color: BLACK");
        definirEstilo(arriba,25);
        panelPrincipal.minHeight(700);
        panelPrincipal.minWidth(700);

        //
        arriba.setAlignment(Pos.CENTER);
        panelPrincipal.getChildren().addAll(menu,arriba);
        panelPrincipal.setAlignment(Pos.CENTER);

        //Ponemos todos los elementos en el panel principal y los acomodamos en su respectiva posicion
        //Tambien les damos un poco de estilo
        Label medio = new Label("*Espacio donde se realizaran las consultas*");
        Label abajo = new Label("En este espacio el usuario tendra la capacidad de comprar boletos, cancelar \n                                  reservas hechas y canjear sus puntos.");
        definirEstilo(medio,20);
        definirEstilo(abajo,15);
        panelEstructura.setTop(panelPrincipal);
        panelEstructura.setCenter(medio);
        panelEstructura.setBottom(abajo);
        panelEstructura.setStyle("-fx-background-color: BLACK");
        BorderPane.setMargin(arriba,new Insets(500,0,0,0)); //Pero este no :(
        BorderPane.setMargin(abajo,new Insets(0,0,25,0)); //Esto si funciona
        BorderPane.setAlignment(arriba, Pos.CENTER);
        BorderPane.setAlignment(medio,Pos.CENTER);
        BorderPane.setAlignment(abajo,Pos.CENTER);

        //Metodos que me permiten ajustar las caracteristicas de primaryStage
        primaryStage.setTitle("Cliente: "+ Cliente.getClienteActual().getNombre());
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        //Creamos la escena
        //panel.getChildren().add(panelPrincipal);
        Scene escenaPrincipal = new Scene(panelEstructura,700,700);

        //Pasamos la escena al stage
        primaryStage.setScene(escenaPrincipal);
        primaryStage.show();

        /*Espacio para definir cada uno de los eventos que se produzcan*/
        salir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                VentanaGenerica.pantallaInicio.show();
            }
        });
        usuario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String usuario = Cliente.getClienteActual().toString();
                VentanaInformacion.showing("Usuario",usuario,"Aceptar",500,200);
            }
        });
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
                VentanaInformacion.showing("Acerda de",mensaje.toString(),"Aceptar",500,220);
            }
        });
        //Este evento es para definir lo que se hara cuando de vayan a comprar los boletos *Tengo miedo*
        comprarBoleto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Definimos lo que sera el nuevo titulo
                Label titulo = new Label("Compra de boletos");
                definirEstilo(titulo,25);

                /*Removemos los dos elementos que se tiene y ponemos los que necesitamos ahora*/
                panelPrincipal.getChildren().remove(0);
                panelPrincipal.getChildren().remove(0);
                panelPrincipal.getChildren().addAll(menu,titulo);
                /*Definimos lo que sera el nuevo centro del Panel*/
                Label ciudad = new Label(empleado.consultarCines());
                definirEstilo(ciudad,15);
                panelEstructura.setCenter(ciudad);
                /*Pedimos los datos con el FieldPanel*/
                String[] criterios = new String[] {"Seleccione la ciudad deseada"};
                String[] valores = new String[] {""};
                boolean[] habilitados = new boolean[] {};
                FieldPanel probando = new FieldPanel("",criterios,"",valores,habilitados);
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
                            e.printStackTrace();
                        }
                        /*Aca conseguimos todas las salas de cine en la ciudad seleccionada*/
                        Vector<Cine> salasXCiudad = empleado.cinesPorCiudad(Cine.getCiudades().get(Integer.parseInt(valores[0])-1));
                        Label cinesPorCiudad = new Label(" Salas de cine disponibles en la ciudad:\n"+"¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯  \n"+empleado.cinesPorCiudad(salasXCiudad));
                        definirEstilo(cinesPorCiudad,15);
                        panelEstructura.setCenter(cinesPorCiudad);
                        /*Ahora debemos crear un nuevo FieldPanel y volver a repetir el proceso :'v*/
                        String[] criteriosSala = new String[] {"Seleccione la sala deseada"};
                        String[] valoresSala = new String[] {""};
                        boolean[] habilitadosSala = new boolean[] {};
                        FieldPanel sala = new FieldPanel("",criteriosSala,"",valoresSala,habilitadosSala);
                        /*GridPanel auxiliar para poder centra*/
                        GridPane auxiliarSala = centarFieldPanel(sala);
                        panelEstructura.setBottom(auxiliarSala);
                        /*Ahora seguimos con la opcion de aceptar cuando igresas la ciudad*/
                        sala.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                try {
                                    sala.GuardarDatos();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Label mostrarSemana = new Label("  Ha elegio el cine " + salasXCiudad.get(Integer.parseInt(valoresSala[0])-1).getNombre() +"\n"+"¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯"+ empleado.mostrarSemana().toString());
                                definirEstilo(mostrarSemana,15);
                                panelEstructura.setCenter(mostrarSemana);
                                /*Creamos el nuevo FieldPanel para poder pedir el dia de la funcion*/
                                String[] criteriosDia = new String[] {"Elija el dia que desea reservar"};
                                String[] valoresDia = new String[] {""};
                                boolean[] habilitadosDia = new boolean[] {};
                                FieldPanel diaFuncion = new FieldPanel("",criteriosDia,"",valoresDia,habilitadosDia);
                                /*Panel auxiliar para poder centrar lo que necesitamos*/
                                GridPane auxiliarDia = centarFieldPanel(diaFuncion);
                                panelEstructura.setBottom(auxiliarDia);
                                diaFuncion.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        try {
                                            diaFuncion.GuardarDatos();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        /*En este vector estan guardadas todas las funciones del dia que se selecciono*/
                                        Vector<Funcion> funcionesDia = Cliente.getClienteActual().consultarFunciones(Integer.parseInt(valoresDia[0]),salasXCiudad.get(Integer.parseInt(valoresSala[0])-1));
                                        Label funcionesDelDia = new Label("Funciones del día y sus precios en\n"+"           [dinero($) || puntos(P)]\n"+"         ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n"+Cliente.getClienteActual().consultarFunciones(funcionesDia).toString());
                                        definirEstilo(funcionesDelDia,15);
                                        panelEstructura.setCenter(funcionesDelDia);
                                        /*Definimos un nuevo FieldPanel para pedir los datos*/
                                        String[] criteriosFuncion = new String[] {"Elija la funcion que desea"};
                                        String[] valoresFuncion = new String[] {""};
                                        boolean[] habilitadosFuncion = new boolean[] {};
                                        FieldPanel funcion = new FieldPanel("",criteriosFuncion,"",valoresFuncion,habilitadosFuncion);
                                        GridPane auxiliarFuncion = centarFieldPanel(funcion);
                                        panelEstructura.setBottom(auxiliarFuncion);

                                        funcion.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent event) {
                                                try {
                                                    funcion.GuardarDatos();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                Label asientos = new Label(funcionesDia.get(Integer.parseInt(valoresFuncion[0])-1).mostrarPuestos());
                                                definirEstilo(asientos,15);
                                                panelEstructura.setCenter(asientos);
                                                /*Hacemos otro FieldPanel para pedir el asiento(Please stop :'v)*/
                                                String[] criteriosPuesto = new String[] {"Elija su puesto"};
                                                String[] valoresPuesto = new String[] {""};
                                                boolean[] habilitadosPuesto = new boolean[] {};
                                                FieldPanel puestoElegir = new FieldPanel("",criteriosPuesto,"",valoresPuesto,habilitadosPuesto);
                                                /*Lo centramos y lo acomodamos*/
                                                GridPane auxiliarPuesto = centarFieldPanel(puestoElegir);
                                                panelEstructura.setBottom(auxiliarPuesto);
                                                puestoElegir.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                    @Override
                                                    public void handle(MouseEvent event) {
                                                        try {
                                                            puestoElegir.GuardarDatos();
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                        Label frase = new Label("Por favor elija su medio de pago\n"+"Saldo en dinero: " + Cliente.getClienteActual().getCuentaBancaria().getSaldo()+"\n"+
                                                                "Saldo en puntos: " + Cliente.getClienteActual().getCuentaPuntos().getPuntos());
                                                        definirEstilo(frase,15);
                                                        panelEstructura.setCenter(frase);
                                                        Button pagarDinero = new Button("Pagar con dinero");
                                                        Button pagarPuntos = new Button("Pagar con puntos");
                                                        GridPane menuPago = new GridPane();
                                                        menuPago.add(pagarDinero,0,0);
                                                        menuPago.add(pagarPuntos,1,0);
                                                        menuPago.setVgap(8);
                                                        menuPago.setHgap(8);
                                                        menuPago.setAlignment(Pos.CENTER);
                                                        definirBotones(pagarDinero);
                                                        definirBotones(pagarPuntos);
                                                        panelEstructura.setBottom(menuPago);
                                                        BorderPane.setMargin(menuPago,new Insets(0,0,25,0));
                                                        pagarDinero.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                            @Override
                                                            public void handle(MouseEvent event) {
                                                                if(Cliente.getClienteActual().getCuentaBancaria().getSaldo() >= 20000){
                                                                    /*En este vector almacenamos el numero del puesto que se ha elegido*/
                                                                    Vector<Integer> puestoElegido = new Vector<Integer>();
                                                                    puestoElegido.add(Integer.parseInt(valoresPuesto[0]));
                                                                    Cliente.getClienteActual().reservarPuestos(puestoElegido,funcionesDia.get(Integer.parseInt(valoresFuncion[0])-1));
                                                                    empleado.transaccionDinero(Cliente.getClienteActual(),20000);
                                                                    primaryStage.close();
                                                                    try {
                                                                        start(new Stage());
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                    VentanaInformacion.showing("Confirmar compra","Reserva hecha satisfacctoriamente","Aceptar",400,100);
                                                                }else{
                                                                    primaryStage.close();
                                                                    try {
                                                                        start(new Stage());
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                    VentanaInformacion.showing("Compra rechazada","No tienes el saldo suficiente","Aceptar",400,100);
                                                                }
                                                            }
                                                        });
                                                        pagarPuntos.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                                            @Override
                                                            public void handle(MouseEvent event) {
                                                                if(Cliente.getClienteActual().getCuentaPuntos().getPuntos() >= 3000){
                                                                    /*En este vector almacenamos el numero del puesto que se ha elegido*/
                                                                    Vector<Integer> puestoElegido = new Vector<Integer>();
                                                                    puestoElegido.add(Integer.parseInt(valoresPuesto[0]));
                                                                    Cliente.getClienteActual().reservarPuestos(puestoElegido,funcionesDia.get(Integer.parseInt(valoresFuncion[0])-1),true);
                                                                    empleado.transaccionPuntos(Cliente.getClienteActual(),3000);
                                                                    primaryStage.close();
                                                                    try {
                                                                        start(new Stage());
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                    VentanaInformacion.showing("Confirmar compra","Reserva hecha satisfacctoriamente","Aceptar",400,100);
                                                                }else{
                                                                    primaryStage.close();
                                                                    try {
                                                                        start(new Stage());
                                                                    } catch (Exception e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                    VentanaInformacion.showing("Compra rechazada","No tienes el saldo suficiente","Aceptar",400,100);
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

                    }
                });
            }
        });

        /*200 lineas solo para comprar un boleto :v*/
        consultarReservas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label tituloReserva = new Label("Consultar reservas");
                panelPrincipal.getChildren().remove(0);
                panelPrincipal.getChildren().remove(0);
                definirEstilo(tituloReserva,25);
                panelPrincipal.getChildren().addAll(menu,tituloReserva);

                Label reservasConsulta = new Label(Cliente.getClienteActual().consultarReservas());
                definirEstilo(reservasConsulta,15);
                panelEstructura.setCenter(reservasConsulta);
                Button volverReserva = new Button("Volver");

                definirBotones(volverReserva);
                panelEstructura.setBottom(volverReserva);
                BorderPane.setAlignment(volverReserva,Pos.CENTER);
                BorderPane.setMargin(volverReserva,new Insets(0,0,25,0));
                volverReserva.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        primaryStage.close();
                        try {
                            start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

        cambiarReservas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label tituloCambioPuesto = new Label("Cambio de puesto");
                definirEstilo(tituloCambioPuesto,25);
                panelPrincipal.getChildren().remove(0);
                panelPrincipal.getChildren().remove(0);
                panelPrincipal.getChildren().addAll(menu,tituloCambioPuesto);
                if(empleado.numeroFuncionesDisponibles(Cliente.getClienteActual())<=0){
                    Label noFunciones = new Label("No reservas activas!!");
                    definirEstilo(noFunciones,15);
                    panelEstructura.setCenter(noFunciones);
                }else{
                    Label funcionesDisponibles = new Label(empleado.funcionesDisponibles(Cliente.getClienteActual()).toString());
                    definirEstilo(funcionesDisponibles,15);
                    panelEstructura.setCenter(funcionesDisponibles);
                    String[] criteriosCambio = new String[] {"Elija el numero de la reserva a cambiar"};
                    String[] valoresCambio = new String[] {""};
                    boolean[] habilitadosCambio = new boolean[] {};
                    FieldPanel puestoCambio = new FieldPanel("",criteriosCambio,"",valoresCambio,habilitadosCambio);
                    GridPane auxiliarCambio = centarFieldPanel(puestoCambio);
                    panelEstructura.setBottom(auxiliarCambio);
                    puestoCambio.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            /*Pedimos los datos*/
                            try {
                                puestoCambio.GuardarDatos();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            /*Con esto vaciamos los puestos*/
                            empleado.vaciarReserva(Cliente.getClienteActual().getCartera().get(Integer.parseInt(valoresCambio[0])).getFuncion(),Cliente.getClienteActual().getCartera().get(Integer.parseInt(valoresCambio[0])).getAsientosElegidos());
                            /*Mostramos los nuevos asientos para que se pueda elegir*/
                            Label nuevosAsiento = new Label(Cliente.getClienteActual().getCartera().get(Integer.parseInt(valoresCambio[0])).getFuncion().mostrarPuestos());
                            definirEstilo(nuevosAsiento,15);
                            panelEstructura.setCenter(nuevosAsiento);
                            /*Con un FieldPanel vamos a pedir el nuevos asientos*/
                            String[] criteriosCambio1 = new String[] {"Elija el nuevo asiento"};
                            String[] valoresCambio1 = new String[] {""};
                            boolean[] habilitadosCambio1 = new boolean[] {};
                            Vector<Integer> puestosNuevos = new Vector<Integer>();
                            FieldPanel puestoCambio1 = new FieldPanel("",criteriosCambio1,"",valoresCambio1,habilitadosCambio1);
                            GridPane auxiliarCambio1 = centarFieldPanel(puestoCambio1);
                            panelEstructura.setBottom(auxiliarCambio1);
                            puestoCambio1.aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    try {
                                        puestoCambio1.GuardarDatos();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    puestosNuevos.add(Integer.parseInt(valoresCambio1[0]));
                                    empleado.cambiarPuestos(Cliente.getClienteActual().cartera.get(Integer.parseInt(valoresCambio[0])).getFuncion(),puestosNuevos);
                                    empleado.cambiarNuevosAsientos(Cliente.getClienteActual(),Integer.parseInt(valoresCambio[0]),puestosNuevos);
                                    primaryStage.close();
                                    try {
                                        start(new Stage());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    VentanaInformacion.showing("Confirmar cambio de reserva","Ha cambio su reserva correctamente!!","Aceptar",400,100);
                                }
                            });
                        }
                    });
                }

            }
        });

        comprarComidas.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Label tituloComida = new Label("Comprar comida");
                definirEstilo(tituloComida, 25);
                panelPrincipal.getChildren().remove(0);
                panelPrincipal.getChildren().remove(0);
                panelPrincipal.getChildren().addAll(menu,tituloComida);
                Label panelCentralComida = new Label(" Opciones de comida disponibles y sus precios en\n"+"                      [dinero($) || puntos(P)]\n\n" + empleado.mostrarComidad());
                definirEstilo(panelCentralComida,15);
                panelEstructura.setCenter(panelCentralComida);
                Button pagoEnPuntos = new Button("Pagar con puntos");
                Button pagoEnDinero = new Button("Pagar con dinero");
                GridPane metodoPago = new GridPane();
                metodoPago.add(pagoEnDinero,0,0);
                metodoPago.add(pagoEnPuntos,1,0);
                metodoPago.setVgap(8);
                metodoPago.setHgap(8);
                metodoPago.setAlignment(Pos.CENTER);
                BorderPane.setMargin(metodoPago,new Insets(0,0,65,0));
                //BorderPane.setAlignment(metodoPago,Pos.CENTER);
                panelEstructura.setBottom(metodoPago);
                String[] criteriosComida = new String[] {"Elija la opcion de comida","Elija la cantidad deseada"};
                String[] valoresComida = new String[] {"",""};
                boolean[] habilitadosComida = new boolean[] {};
                FieldPanel puestoComida = new FieldPanel("",criteriosComida,"",valoresComida,habilitadosComida);
                GridPane auxiliarComida = centarFieldPanel(puestoComida);
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
                                    e.printStackTrace();
                                }
                                if(Cliente.getClienteActual().getCuentaPuntos().getPuntos()>= empleado.getPrecioPuntos().get(Integer.parseInt(valoresComida[0])-1)*Integer.parseInt(valoresComida[1])){
                                    empleado.comprarComidas(Cliente.getClienteActual(),0,Integer.parseInt(valoresComida[1]),Integer.parseInt(valoresComida[0]));
                                    primaryStage.close();
                                    try {
                                        start(new Stage());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    VentanaInformacion.showing("Confirmar compra comida","Ha comprado su comida correctamente!!","Aceptar",400,100);
                                }else{
                                    primaryStage.close();
                                    try {
                                        start(new Stage());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    VentanaInformacion.showing("Compra fallida","No cuenta con los suficientes puntos!!","Aceptar",400,100);
                                }
                            }
                        });
                    }
                });
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
                                    e.printStackTrace();
                                }
                                if(Cliente.getClienteActual().getCuentaBancaria().getSaldo() >= empleado.getPrecioDinero().get(Integer.parseInt(valoresComida[0])-1)*Integer.parseInt(valoresComida[1])){
                                    empleado.comprarComidas(Cliente.getClienteActual(),1,Integer.parseInt(valoresComida[1]),Integer.parseInt(valoresComida[0]));
                                    primaryStage.close();
                                    try {
                                        start(new Stage());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    VentanaInformacion.showing("Confirmar compra comida","Ha comprado su comida correctamente!!","Aceptar",400,100);
                                }else{
                                    primaryStage.close();
                                    try {
                                        start(new Stage());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    VentanaInformacion.showing("Compra fallida","No cuenta el dinero suficiente!!","Aceptar",400,100);
                                }
                            }
                        });
                    }
                });


            }
        });
    }
}
