package InterfasGrafica;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FieldPanel extends Pane {
    String tituloCriterios;
    String[] criterios;
    String tituloValores;
    String[]  valores;
    boolean[] habilitado;

    /*Este atributo nos servira para definir que hara cada uno se los
    botones "Aceptar" en los diferentes FieldPanel*/
    public Button aceptar = new Button("Aceptar");


    public FieldPanel(String tituloCriterios,
                      String[] criterios,
                      String tituloValores,
                      String[] valores,
                      boolean[] habilitado) {
        super(new GridPane());
        this.tituloCriterios = tituloCriterios;
        this.criterios = criterios;
        this.tituloValores = tituloValores;
        this.valores = valores;
        this.habilitado = habilitado;

        GridPane grid = ((GridPane) this.getChildren().get(0));
        grid.setVgap(8);
        grid.setHgap(8);
        grid.setAlignment(Pos.CENTER);

        for(int i = 0; i < criterios.length;i++) {
            Label label = new Label(criterios[i]);
            label.setTextFill(Color.WHITE);
            label.setFont(new Font("Arial Black",15));
            grid.add(label, 0, i);
            TextField s = new TextField("");
            s.setAlignment(Pos.CENTER);
            try {
                s.setText(valores[i]);
                grid.add(s, 1, i);
            }catch(Exception e) {
                grid.add(s, 1, i);
            }
            try{
                if(!habilitado[i]) {
                    s.setEditable(false);
                }
            }catch(Exception e){

            }
        }

        Button borrar = new Button("Borrar");
        grid.add(aceptar, 0, criterios.length,2,1);
        grid.add(borrar, 1, criterios.length,2,1);


        borrar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                for(int i = 0; i < criterios.length;i++) {
                    TextField a = (TextField) grid.getChildren().get((2*i)+1);
                    try{
                        if(habilitado[i]) {
                            a.setText("");
                        }
                    }catch(Exception e){
                        a.setText("");
                    }
                }
            }
        });

    }
    /*
     * Metodo para conseguir cada uno de los valores ingresados,
     * segun el criterio recibido en el parametro
     */
    public String getValue(String criterio) {
        String valor="";
        for(int i = 0; i< this.criterios.length;i++) {
            if(this.criterios[i].equals(criterio)) {
                valor = this.valores[i];
            }
        }
        return valor;
    }
    /*
     * Guarda los datos ingresados por el usuario y borra el
     * contenido de los textfield
     */
    public void GuardarDatos() throws Exception{
        GridPane grid = ((GridPane) this.getChildren().get(0));
        for(int i = 0; i < criterios.length;i++) {
            TextField a;
            a = (TextField) grid.getChildren().get((2*i)+1);
            if(!(a.getText()).equals("")) {
                this.valores[i] = a.getText();
                a.setText("");
            }else {
                throw new Exception(this.criterios[i]);
            }
        }
    }
}
