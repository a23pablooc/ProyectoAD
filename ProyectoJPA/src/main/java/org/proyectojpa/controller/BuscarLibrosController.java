package org.proyectojpa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.proyectojpa.model.Libro;

import java.util.function.Predicate;

public class BuscarLibrosController {

    @FXML
    private TextField autorLibro;
    @FXML
    private TextField codigoLibro;
    @FXML
    private TextField idLibro;
    @FXML
    private TextField tituloLibro;
    @FXML
    private TextField anhoLibro;

    public Predicate<Libro> buscarLibros() {
        Predicate<Libro> filtro = libro -> true;

        if (!idLibro.getText().isBlank()) {
            int id = Integer.parseInt(idLibro.getText());
            filtro = filtro.and(libro -> libro.getIdLibro() == id);
        }

        if (!codigoLibro.getText().isBlank()) {
            String codigo = codigoLibro.getText();
            filtro = filtro.and(libro -> libro.getCodigo().equals(codigo));
        }

        if (!tituloLibro.getText().isBlank()) {
            String titulo = tituloLibro.getText();
            filtro = filtro.and(libro -> libro.getTitulo().equals(titulo));
        }

        if (!autorLibro.getText().isBlank()) {
            String autor = autorLibro.getText();
            filtro = filtro.and(libro -> libro.getAutores().contains(autor));
        }

        if (!anhoLibro.getText().isBlank()) {
            int anho = Integer.parseInt(anhoLibro.getText());
            filtro = filtro.and(libro -> libro.getAnho() == anho);
        }

        return filtro;
    }
}