package org.proyectojpa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.proyectojpa.model.Cliente;

import java.util.function.Predicate;

public class BuscarClientesController {
    @FXML
    private TextField emailCliente;
    @FXML
    private TextField idCliente;
    @FXML
    private TextField nombreCliente;
    @FXML
    private TextField dniCliente;

    public Predicate<Cliente> buscarClientes() {
        Predicate<Cliente> filtro = cliente -> true;

        if (!idCliente.getText().isBlank()) {
            int id = Integer.parseInt(idCliente.getText());
            filtro = filtro.and(cliente -> cliente.getIdCliente() == id);
        }

        if (!dniCliente.getText().isBlank()) {
            String dni = dniCliente.getText();
            filtro = filtro.and(cliente -> cliente.getDNI().equals(dni));
        }

        if (!nombreCliente.getText().isBlank()) {
            String nombre = nombreCliente.getText();
            filtro = filtro.and(cliente -> cliente.getNombre().equals(nombre));
        }

        if (!emailCliente.getText().isBlank()) {
            String email = emailCliente.getText();
            filtro = filtro.and(cliente -> cliente.getEmail().equals(email));
        }

        return filtro;
    }
}