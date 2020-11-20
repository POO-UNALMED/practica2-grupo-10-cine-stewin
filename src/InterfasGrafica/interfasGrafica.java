package InterfasGrafica;

import InterfasGrafica.Ventanas.CrearMenuRegistro;
import baseDatos.Escribir;
import baseDatos.Leer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.Vector;


public class interfasGrafica extends Application{
    Image imagenes;
    Vector<String> datosAutores = new Vector<String>(); /*Tendremos los autores aca */
    int contadorCines = 0;
    int contadorAutores = 0;

    //Metodo que se ejecuta al principio para leer la base de datos
    public void init(){
        Leer.Leer();
    }
    public void start(Stage primaryStage) throws Exception {
        String autor0 = ("Desarrolladores \nSepulveda Daniel Alejandro\ndasepulvedao@unal.edu.co");
        String autor1 = ("Desarrolladores \nOspina Amilder Stewin\naospinato@unal.edu.co");
        String autor2 = ("Desarrolladores \nMoreno Gelier Esteban\ngemorenog@unal.edu.co");
        String autor3 = ("Desarrolladores \nMolano Kevin Andres\nkmolano@unal.edu.co");
        datosAutores.add(autor0);
        datosAutores.add(autor1);
        datosAutores.add(autor2);
        datosAutores.add(autor3);
        Vector<ImageView> cinesVect = new Vector<ImageView>(); /*arreglo donde se guardaran los cines*/
        Vector<ImageView> autoresVect = new Vector<ImageView>(); /*Imagenes de nosotros*/


        //Definimos la pantalla de inicio
        FlowPane fondoPrincipal = new FlowPane();

        //Los elementos en esta tendran un separado de 5px
        fondoPrincipal.setVgap(0);
        fondoPrincipal.setHgap(0);

        //Definimos el panel izquierdo
        GridPane izquierda = new GridPane();

        //Definimos el panel derecho
        GridPane derecha = new GridPane();

        //Estas definimos las tres subregiones que tendra cada region(Izquierda derecha)
        Label unoIzquierda = new Label();
        Label dosIzquierda = new Label();
        //Label tresIzquierda = new Label("Aca debe ir el espacio para ingresar");

        //La parte tresIzquierda debe ser un GridPane para poder organizar todos los objetos
        GridPane tresIzquierda1 = new GridPane();

        //tresIzquierda.setMinWidth(350);
        //tresIzquierda.setMinHeight(300);
        tresIzquierda1.setMinHeight(350);
        tresIzquierda1.setMinHeight(300);
        //tresIzquierda.setStyle("-fx-border-color: red");
        tresIzquierda1.setStyle("-fx-border-color: red");
        GridPane unoDerecha = new GridPane ();
        //Este label sera el encargado de mostrar nuestra informacion
        Label textoAutores = new Label(datosAutores.get(0));
        Label dosDerecha = new Label("");

        //Aca ponemos cada una de los subregiones en su respectivo espacio
        izquierda.add(unoIzquierda,0,0);
        izquierda.add(dosIzquierda,0,1);
        izquierda.add(tresIzquierda1,0,2);
        izquierda.setAlignment(Pos.CENTER_LEFT);
        derecha.add(unoDerecha,0,0);
        derecha.add(dosDerecha,0,1);
        derecha.setAlignment(Pos.BASELINE_RIGHT);

        //Ponemos el gridPane izquierdo y dercho en su lugar correspondondiente
        fondoPrincipal.getChildren().add(izquierda);
        fondoPrincipal.getChildren().add(derecha);
        fondoPrincipal.setMinWidth(700);
        fondoPrincipal.setMinHeight(700);

        //RegionUno Izquierda
        //Conseguimos la imagenen de Bienvenida
        try{
            imagenes = new Image(new FileInputStream(System.getProperty("user.dir") + "/src/InterfasGrafica/Imagenes/Hola.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
        ImageView imagenArreglada = new ImageView(imagenes);
        //Podemos la imagen de Bienvenida en la parte superior del panel izquierdo
        unoIzquierda.setGraphic(imagenArreglada);

        //Centramos para que no se descuadre cuando se amplia
        fondoPrincipal.setAlignment(Pos.CENTER);


        //RegiosDos Izquierda
        //Debajo de la imagen de Bienvenida debemos tener una lista de imagenes que se cambia cada que dan clic sobre ella
        try{
            for(int i=0; i<5;i++){
                imagenes = new Image(new FileInputStream(System.getProperty("user.dir") + "/src/InterfasGrafica/Imagenes/cine/"+i+".png"));
                ImageView cines = new ImageView(imagenes);
                cinesVect.add(cines);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        //Ponemos por defecto la primera imagen
        dosIzquierda.setGraphic(cinesVect.get(0));
        //Luego, cada que un usuario saque su mouse de la region, esta cambiara de imagen
        dosIzquierda.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                contadorCines++;
                dosIzquierda.setGraphic(cinesVect.get(contadorCines%5));
            }
        });

        //RegionUno Derecha (Aun no esta, no se como :V)
        unoDerecha.setMinWidth(350);
        unoDerecha.setMinHeight(200);
        unoDerecha.setStyle("-fx-border-color: red");
        unoDerecha.setStyle("-fx-background-color: BLACK");

        //RegionDos Derecha (Aca deben ir nuestras imagenes) 500px de profundo
        //En esta parte deben ir imagenes con nuestras fotos y eso, pero por ahora no tengo tiempo de editarlas
        //El tamaño por si alguien quiere el 350x500px

        //Conseguimos todas las imagenes de nosotros
        try{
            for(int i=0; i<4;i++){
                imagenes = new Image(new FileInputStream(System.getProperty("user.dir") + "/src/InterfasGrafica/Imagenes/autores/"+i+".png"));
                ImageView autores = new ImageView(imagenes);
                //Las guardamos en este vector
                autoresVect.add(autores);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        //Siempre por defecto mostramos la imagen 0
        dosDerecha.setGraphic(autoresVect.get(0));
        dosDerecha.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                contadorAutores++;
                dosDerecha.setGraphic(autoresVect.get((contadorAutores%4)));
                unoDerecha.getChildren().remove(0);
                textoAutores.setText(datosAutores.get(contadorAutores%4));
                unoDerecha.getChildren().add(textoAutores);
                dosDerecha.setGraphic(autoresVect.get((contadorAutores%4)));
            }
        });

        //Agregamos los dos botones que iran en la parte tres de la izquierda
        //Boton para registrarse
        Button registrar = new Button("Registrarse");

        //Boton para entrar
        Button entrar = new Button("Entrar");

        //Centramos los botones
        tresIzquierda1.setAlignment(Pos.CENTER);
        tresIzquierda1.setVgap(10);
        tresIzquierda1.setHgap(10);

        //Definimos el tamano del boton registrar
        registrar.setMinWidth(100);
        registrar.setMinHeight(50);

        //Definimos el tamano del boton entrar
        entrar.setMinWidth(100);
        entrar.setMinHeight(50);


        //Ponemos los botones en su respectiva posiciion

        tresIzquierda1.add(entrar,0,0);
        tresIzquierda1.add(registrar,0,1);
        //Podemos la parte tres con fondo negro
        tresIzquierda1.setStyle("-fx-background-color: BLACK");

        //Evento que ocurre a la hora de registrar un usuario
        registrar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CrearMenuRegistro.showing("Registro");
                /*Cerramos la pagina donde estamos y debemos abrir una nueva pagina de todas las opciones*/
            }
        });

        //Evento que ocurre a la hora de ingreso de un usuario
        entrar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Intentando entrar");
            }
        });
        textoAutores.setTextFill(Color.WHITE);
        textoAutores.setFont(new Font("Arial Black",20));
        textoAutores.setEffect(new Glow(1.7f));

        //Area uno a la derecha
        unoDerecha.getChildren().add((textoAutores));
        unoDerecha.setAlignment(Pos.CENTER);

        //Evento que ocurre cuando le dan clic en nuestras hojas de vida
        textoAutores.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                contadorAutores++;
                unoDerecha.getChildren().remove(0);
                textoAutores.setText(datosAutores.get(contadorAutores%4));
                unoDerecha.getChildren().add(textoAutores);
                dosDerecha.setGraphic(autoresVect.get((contadorAutores%4)));
            }
        });


        //Le damos el titulo a la escena
        primaryStage.setTitle("Casa");

        //Le pasamos la pantalla de inicio y el tamaño por defecto
        primaryStage.setScene(new Scene(fondoPrincipal,700,700));

        /*De esta forma el tamano minimo de la ventana sera este, asi no se podra deformar
          Si el profesor la pone mas pequeña :v */
        primaryStage.setMinWidth(716);
        primaryStage.setMinHeight(716);

        //Mostramos
        primaryStage.show();
    }

    //Metodo que se ejecuta al principio para guardar los datos en la BaseDeDatos
    public void stop(){
        Escribir.Escribir();
    }
}