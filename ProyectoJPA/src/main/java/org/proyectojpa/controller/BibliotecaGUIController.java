package org.proyectojpa.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import org.proyectojpa.model.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class BibliotecaGUIController {
    private final LibroDAO libroDAO = new LibroDAO(EMF.get().createEntityManager());
    private final ClienteDAO clienteDAO = new ClienteDAO(EMF.get().createEntityManager());
    private final AlquilerDAO alquilerDAO = new AlquilerDAO(EMF.get().createEntityManager());

    @FXML
    private TableView<Libro> tablaLibros;
    @FXML
    private TableColumn<Libro, Integer> idLibro;
    @FXML
    private TableColumn<Libro, String> codigoLibro;
    @FXML
    private TableColumn<Libro, String> tituloLibro;
    @FXML
    private TableColumn<Libro, List<String>> autoresLibro;
    @FXML
    private TableColumn<Libro, Integer> anhoLibro;

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, Integer> idCliente;
    @FXML
    private TableColumn<Cliente, String> dniCliente;
    @FXML
    private TableColumn<Cliente, String> nombreCliente;
    @FXML
    private TableColumn<Cliente, String> emailCliente;

    @FXML
    private TableView<Alquiler> tablaAlquileres;
    @FXML
    private TableColumn<Alquiler, Integer> idAlquiler;
    @FXML
    private TableColumn<Alquiler, Date> fechaAlquiler;
    @FXML
    private TableColumn<Alquiler, Libro> libroAlquiler;
    @FXML
    private TableColumn<Alquiler, Cliente> clienteAlquiler;

    private FilteredList<Libro> librosFiltrados;
    private FilteredList<Cliente> clientesFiltrados;
    private FilteredList<Alquiler> alquileresFiltrados;

    @FXML
    public void initialize() {
        ejemplos();
        cargarLibros();
        cargarClientes();
        cargarAlquileres();
    }

    private void ejemplos() {
        Libro libro = new Libro();
        libro.setCodigo("123456");
        libro.setTitulo("El Quijote");
        libro.setAutores(List.of("Cervantes"));
        libro.setAnho(1605);
        libroDAO.save(libro);

        Cliente cliente = new Cliente();
        cliente.setDNI("12345678A");
        cliente.setNombre("Pepe");
        cliente.setEmail("pepe@email.com");
        clienteDAO.save(cliente);

        Alquiler alquiler = new Alquiler(libro, cliente, new Date());
        alquilerDAO.save(alquiler);
    }

    private void cargarLibros() {
        idLibro.setCellValueFactory(libro -> new SimpleObjectProperty<>(libro.getValue().getIdLibro()));
        codigoLibro.setCellValueFactory(libro -> new SimpleObjectProperty<>(libro.getValue().getCodigo()));
        tituloLibro.setCellValueFactory(libro -> new SimpleObjectProperty<>(libro.getValue().getTitulo()));
        autoresLibro.setCellValueFactory(libro -> new SimpleObjectProperty<>(libro.getValue().getAutores()));
        autoresLibro.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(List<String> autores, boolean empty) {
                super.updateItem(autores, empty);

                if (autores == null || empty) {
                    setText(null);
                } else {
                    String autoresStr = String.join(", ", autores);
                    setText(autoresStr);
                }
            }
        });
        anhoLibro.setCellValueFactory(libro -> new SimpleObjectProperty<>(libro.getValue().getAnho()));
        librosFiltrados = new FilteredList<>(FXCollections.observableList(libroDAO.getAll()));
        tablaLibros.setItems(librosFiltrados);
    }

    private void cargarClientes() {
        idCliente.setCellValueFactory(cliente -> new SimpleObjectProperty<>(cliente.getValue().getIdCliente()));
        dniCliente.setCellValueFactory(cliente -> new SimpleObjectProperty<>(cliente.getValue().getDNI()));
        nombreCliente.setCellValueFactory(cliente -> new SimpleObjectProperty<>(cliente.getValue().getNombre()));
        emailCliente.setCellValueFactory(cliente -> new SimpleObjectProperty<>(cliente.getValue().getEmail()));
        clientesFiltrados = new FilteredList<>(FXCollections.observableList(clienteDAO.getAll()));
        tablaClientes.setItems(clientesFiltrados);
    }

    private void cargarAlquileres() {
        idAlquiler.setCellValueFactory(alquiler -> new SimpleObjectProperty<>(alquiler.getValue().getIdAlquiler()));
        fechaAlquiler.setCellValueFactory(alquiler -> new SimpleObjectProperty<>(alquiler.getValue().getFecha()));
        libroAlquiler.setCellValueFactory(alquiler -> new SimpleObjectProperty<>(alquiler.getValue().getLibro()));
        clienteAlquiler.setCellValueFactory(alquiler -> new SimpleObjectProperty<>(alquiler.getValue().getCliente()));
        alquileresFiltrados = new FilteredList<>(FXCollections.observableList(alquilerDAO.getAll()));
        tablaAlquileres.setItems(alquileresFiltrados);
    }

    @FXML
    public void nuevoLibro() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(tablaLibros.getScene().getWindow());
        dialog.setTitle("Nuevo Libro");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/org/proyectojpa/view/NuevoLibro.fxml"));
        dialog.getDialogPane().setContent(fxmlLoader.load());

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            NuevoLibroController controller = fxmlLoader.getController();
            Libro libro = controller.nuevoLibro();
            libroDAO.save(libro);
            librosFiltrados.setPredicate(libro1 -> true);
        }
    }

    @FXML
    public void buscarLibros() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(tablaLibros.getScene().getWindow());
        dialog.setTitle("Buscar Libros");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/org/proyectojpa/view/BuscarLibros.fxml"));
        dialog.getDialogPane().setContent(fxmlLoader.load());

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            BuscarLibrosController controller = fxmlLoader.getController();
            Predicate<Libro> filtro = controller.buscarLibros();
            librosFiltrados.setPredicate(filtro);
        }
    }

    @FXML
    public void limpiarBusquedaLibro() {
        librosFiltrados.setPredicate(libro -> true);
    }

    @FXML
    public void nuevoAlquiler() {
        // TODO
    }

    @FXML
    public void devolverAlquiler() {
        // TODO
    }

    @FXML
    public void buscarAlquileres() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(tablaLibros.getScene().getWindow());
        dialog.setTitle("Buscar Alquileres");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/org/proyectojpa/view/BuscarAlquiler.fxml"));
        dialog.getDialogPane().setContent(fxmlLoader.load());

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            BuscarAlquilerController controller = fxmlLoader.getController();
            Predicate<Alquiler> filtro = controller.buscarAlquileres();
            alquileresFiltrados.setPredicate(filtro);
        }
    }

    @FXML
    public void limpiarBusquedaAlquileres() {
        alquileresFiltrados.setPredicate(alquiler -> true);
    }

    @FXML
    public void nuevoCliente() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(tablaLibros.getScene().getWindow());
        dialog.setTitle("Nuevo Cliente");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/org/proyectojpa/view/NuevoCliente.fxml"));
        dialog.getDialogPane().setContent(fxmlLoader.load());

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            NuevoClienteController controller = fxmlLoader.getController();
            Cliente cliente = controller.nuevoCliente();
            clienteDAO.save(cliente);
            clientesFiltrados.setPredicate(cliente1 -> true);
        }
    }

    @FXML
    public void buscarClientes() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(tablaLibros.getScene().getWindow());
        dialog.setTitle("Buscar Clientes");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/org/proyectojpa/view/BuscarClientes.fxml"));
        dialog.getDialogPane().setContent(fxmlLoader.load());

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            BuscarClientesController controller = fxmlLoader.getController();
            Predicate<Cliente> filtro = controller.buscarClientes();
            clientesFiltrados.setPredicate(filtro);
        }
    }

    @FXML
    public void limpiarBusquedaClientes() {
        clientesFiltrados.setPredicate(cliente -> true);
    }
}
