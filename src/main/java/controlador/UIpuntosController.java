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
//import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import modelo.Punto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

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

    public static Stage s;

    public double[] lista;
    private LinkedList<Punto> Puntos = new LinkedList<>();
    @FXML
    private ListView<String> listaPuntos;

    @FXML
    private void handleCanvasClick(MouseEvent event) {

        double x = event.getX();
        double y = event.getY();

        lista = new double[]{};
        System.out.println("Clic en las coordenadas: (" + x + ", " + y + ")");
        labelX.setText(String.valueOf("X: " + x));
        labelY.setText(String.valueOf("Y: " + y));
        lista = new double[]{x, y};

    } //Funcion para obtener la informacion del canva

    public void saveFile(ActionEvent e) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        FileChooser fileChooser = new FileChooser(); //Crear la clase filechooser para poder obtenerla
        fileChooser.setTitle("Guardar Archivo"); //Titulo que aparecera en la parte superior izquierda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos JSON", "*.json"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );

        File archivo = fileChooser.showSaveDialog(s);
        if (archivo != null) {
            try {
                // Convertir la lista de puntos a formato JSON
                String json = objectMapper.writeValueAsString(Puntos);

                // Guardar el JSON en el archivo seleccionado
                objectMapper.writeValue(archivo, Puntos);

                System.out.println("Puntos guardados correctamente en " + archivo.getAbsolutePath());
            } catch (IOException a) {
                a.printStackTrace();
                System.err.println("Error al guardar los puntos en JSON.");
            }

        }
    }

    private Punto crearPunto(double x, double y) {

        Punto punto = new Punto(x, y);
        return punto;

    } //Funcion para crear la clase punto

    private void llenarList() {

        for (Punto punto : Puntos) {

            listaPuntos.getItems().add(String.valueOf(punto.getX()) + ", " + String.valueOf(punto.getY()));

        }

    } //Funcion para llenar el Jlist 

    @FXML
    private void puntoInicio(ActionEvent event) {

        listaPuntos.getItems().clear();
        Punto newPunto = crearPunto(lista[0], lista[1]);
        Puntos.add(0, newPunto);
        llenarList();
        labelX.setText(String.valueOf("X: "));
        labelY.setText(String.valueOf("Y: "));

    } //Funcion para agrega el punto al inicio

    @FXML
    private void puntoFinal(ActionEvent event) {

        listaPuntos.getItems().clear();
        Punto newPunto = crearPunto(lista[0], lista[1]);
        Puntos.add(newPunto);
        llenarList();
        labelX.setText(String.valueOf("X: "));
        labelY.setText(String.valueOf("Y: "));

    } //Funcion para agrega el punto al inicio

    @FXML
    private void invertirLista(ActionEvent event) {

        listaPuntos.getItems().clear();
        Collections.reverse(Puntos);
        llenarList();
    }//Funcion para invertir la lista

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

    }//Funcion para ordenar la lista

    @FXML
    private void seleccionarPunto(MouseEvent event) {

        //int index = listaPuntos.getSelectionModel().getSelectedIndex();
        String punto = listaPuntos.getSelectionModel().getSelectedItem();
        System.out.println(punto);

    }//Funcion para seleccionar un punto de la lista y mostrarlo

    private void configurarColorFondo() {
        GraphicsContext gc = miCanvas.getGraphicsContext2D();

        // Establece el color de fondo del Canvas
        gc.setFill(Color.LIGHTBLUE);  // Puedes cambiar LIGHTBLUE a cualquier otro color

        // Llena el Canvas con el color de fondo
        gc.fillRect(0, 0, miCanvas.getWidth(), miCanvas.getHeight());
    }// Diseño del Canva

    @Override //los parametros siempre van a hacer iguales pero el TODO es distinto
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarColorFondo();
    }

}
