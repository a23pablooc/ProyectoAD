module org.proyectojpa {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    exports org.proyectojpa.controller;
    opens org.proyectojpa.controller to javafx.fxml;
    exports org.proyectojpa.view;
    opens org.proyectojpa.view to javafx.fxml;
    exports org.proyectojpa.model;
    opens org.proyectojpa.model to org.hibernate.orm.core;
}