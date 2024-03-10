package org.proyectojpa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.proyectojpa.model.Cliente;

public class NuevoClienteController {
    @FXML
    private TextField nombre;
    @FXML
    private TextField dni;
    @FXML
    private TextField email;

    public Cliente nuevoCliente() {
        return new Cliente(dni.getText(), nombre.getText(), email.getText());
    }
}