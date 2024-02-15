module com.mycompany.puntos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.puntos to javafx.fxml;
    opens controlador to javafx.fxml;
    exports com.mycompany.puntos;
    exports controlador;
    //exports vista;
}
