package org.proyectojpa.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.proyectojpa.model.EMF;

import java.io.IOException;

public class BibliotecaGUIApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/proyectojpa/BibliotecaGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Gestión de Biblioteca");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        EMF.get().createEntityManager().close();
    }
}