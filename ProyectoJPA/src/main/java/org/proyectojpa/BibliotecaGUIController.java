package org.proyectojpa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BibliotecaGUIController {

    @FXML
    private void mostrarVentanaAgregarLibro(ActionEvent event) throws IOException {
        mostrarVentana("AgregarLibro.fxml", "Agregar Libro");
    }

    @FXML
    private void mostrarVentanaAgregarCliente(ActionEvent event) throws IOException {
        mostrarVentana("AgregarCliente.fxml", "Agregar Cliente");
    }

    @FXML
    private void mostrarVentanaRegistrarAlquiler(ActionEvent event) throws IOException {
        mostrarVentana("RegistrarAlquiler.fxml", "Registrar Alquiler");
    }

    private void mostrarVentana(String fxmlFile, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
