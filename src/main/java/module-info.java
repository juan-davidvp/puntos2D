module com.mycompany.puntos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.puntos to javafx.fxml;
    opens modelo to com.fasterxml.jackson.databind;
    opens controlador to javafx.fxml;
    exports com.mycompany.puntos;
    exports modelo;
    exports controlador;
    //exports vista;
    //requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.databind;
}
