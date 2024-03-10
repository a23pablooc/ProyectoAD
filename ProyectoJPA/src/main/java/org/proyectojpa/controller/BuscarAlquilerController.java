package org.proyectojpa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.proyectojpa.model.Alquiler;
import java.util.function.Predicate;

public class BuscarAlquilerController
{

    @FXML
    private TextField libroAlquiler;
    @FXML
    private TextField idAlquiler;
    @FXML
    private TextField clienteAlquiler;
    @FXML
    private TextField fechaAlquiler;

    public Predicate<Alquiler> buscarAlquileres() {
        Predicate<Alquiler> filtro = alquiler -> true;

        if (!idAlquiler.getText().isBlank()) {
            int id = Integer.parseInt(idAlquiler.getText());
            filtro = filtro.and(alquiler -> alquiler.getIdAlquiler() == id);
        }

        if (!libroAlquiler.getText().isBlank()) {
            String libro = libroAlquiler.getText();
            filtro = filtro.and(alquiler -> alquiler.getLibro().getTitulo().equals(libro));
        }

        if (!clienteAlquiler.getText().isBlank()) {
            String cliente = clienteAlquiler.getText();
            filtro = filtro.and(alquiler -> alquiler.getCliente().getNombre().equals(cliente));
        }

        if (!fechaAlquiler.getText().isBlank()) {
            String fecha = fechaAlquiler.getText();
            filtro = filtro.and(alquiler -> alquiler.getFecha().toString().equals(fecha));
        }

        return filtro;
    }
}