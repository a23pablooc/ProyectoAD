package org.proyectojpa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private int idCliente;

    @Column(name = "DNI", length = 9, nullable = false, unique = true)
    private String DNI;

    @Column(name = "nombre", length = 200, nullable = false)
    private String nombre;

    @Column(name = "email", length = 200)
    private String email;

    public Cliente() {
    }

    public Cliente(String DNI, String nombre, String email) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.email = email;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", DNI, nombre);
    }
}