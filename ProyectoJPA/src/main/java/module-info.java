module org.proyectojpa.proyectojpa {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;


    opens org.proyectojpa to javafx.fxml;
    exports org.proyectojpa;
}