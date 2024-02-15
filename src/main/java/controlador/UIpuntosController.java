/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import modelo.Punto;

//import org.json.simlple.JSONArray;


/**
 * FXML Controller class
 *
 * @author juan_d.velez_p
 */
//Extends Herencia Normal xd
//Interfaces son clases especiales (que permite tener mas de una herencia) que tienen el nombre con el estereotipo junto con el nombre de la interfaz seguido con los metodos
//Implements 
public class UIpuntosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public Canvas miCanvas;
    @FXML
    public Label labelX;
    @FXML
    public Label labelY;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonEnd;
    @FXML
    private Button buttonInvert;
    @FXML
    private Button buttonSort;
    
    public double[] lista;
    private LinkedList<Punto> Puntos = new LinkedList<>();
    @FXML
    private ListView<String> listaPuntos;
    
    @FXML
    private void handleCanvasClick(MouseEvent event){
    
        double x = event.getX();
        double y = event.getY();
        
        lista = new double[]{};
        System.out.println("Clic en las coordenadas: (" + x + ", " + y + ")");
        labelX.setText(String.valueOf("X: " + x));
        labelY.setText(String.valueOf("Y: " + y));
        lista = new double[]{x,y};
        
    
    }
    
    private Punto crearPunto(double x,double y){
        
        Punto punto = new Punto(x,y);
        return punto;
    
    }
    
    private void llenarList(){
    
        for(Punto punto:Puntos){
        
            listaPuntos.getItems().add(String.valueOf(punto.getX()) + ", " + String.valueOf(punto.getY()));
            
        }
        
    }
    
    @FXML
    private void puntoInicio(ActionEvent event) {
        
        listaPuntos.getItems().clear();
        Punto newPunto = crearPunto(lista[0], lista[1]);
        Puntos.add(0, newPunto);
        llenarList();
        labelX.setText(String.valueOf("X: "));
        labelY.setText(String.valueOf("Y: "));
        
        
        
    }
    
    @FXML
    private void puntoFinal(ActionEvent event) {
        
        listaPuntos.getItems().clear();
        Punto newPunto = crearPunto(lista[0], lista[1]);
        Puntos.add(newPunto);
        llenarList();
        labelX.setText(String.valueOf("X: "));
        labelY.setText(String.valueOf("Y: "));
        
    }

    @FXML
    private void invertirLista(ActionEvent event) {
        
        listaPuntos.getItems().clear();
        Collections.reverse(Puntos);
        llenarList();
    }

    @FXML
    private void ordenarLista(ActionEvent event) {
        
        listaPuntos.getItems().clear();
        Collections.sort(Puntos, new Comparator<Punto>() {

            @Override
            public int compare(Punto o1, Punto o2) {
                // Comparar por algún criterio personalizado (en este caso, por el atributo "valor")
                return Double.compare(o1.getX(), o2.getX());
            }
        });
        llenarList();
        
    }
    
    @FXML
    private void seleccionarPunto(MouseEvent event) {
        
        //int index = listaPuntos.getSelectionModel().getSelectedIndex();
        String punto = listaPuntos.getSelectionModel().getSelectedItem();
        
        
        

        
    }
    
    
    
    private void configurarColorFondo() {
        GraphicsContext gc = miCanvas.getGraphicsContext2D();
        
        // Establece el color de fondo del Canvas
        gc.setFill(Color.LIGHTBLUE);  // Puedes cambiar LIGHTBLUE a cualquier otro color

        // Llena el Canvas con el color de fondo
        gc.fillRect(0, 0, miCanvas.getWidth(), miCanvas.getHeight());
    }
    
    @Override //los parametros siempre van a hacer iguales pero el TODO es distinto
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarColorFondo();
    }    

    

    

    
    
}
