package org.proyectojpa.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLibro;

    @Column(length = 20, nullable = false, unique = true)
    private String codigo;

    @Column(length = 200, nullable = false)
    private String titulo;

    @ElementCollection
    private List<String> autores;

    @Column(name = "año")
    private int anho;

    public Libro() {
    }

    public Libro(String codigo, String titulo, List<String> autores, int anho) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autores = autores;
        this.anho = anho;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }

    @Override
    public String toString() {
        return titulo;
    }
}