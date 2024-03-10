package org.proyectojpa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.proyectojpa.model.Libro;

import java.util.List;

public class NuevoLibroController {
    @FXML
    private TextField codigo;
    @FXML
    private TextField titulo;
    @FXML
    private TextField anho;
    @FXML
    private TextField autores;

    public Libro nuevoLibro() {
        return new Libro(codigo.getText(), titulo.getText(), List.of(autores.getText().split(",")), Integer.parseInt(anho.getText()));
    }

}