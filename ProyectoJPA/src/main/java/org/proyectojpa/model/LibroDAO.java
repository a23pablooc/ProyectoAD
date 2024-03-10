package org.proyectojpa.model;

import jakarta.persistence.EntityManager;

import java.util.List;

public class LibroDAO implements IDAO<Libro> {

    private final EntityManager em;

    public LibroDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Libro get(long id) {
        return em.find(Libro.class, id);
    }

    public List<Libro> getLibrosByCodigo(String codigo) {
        return em.createQuery("SELECT l FROM Libro l WHERE l.codigo = :codigo", Libro.class)
                .setParameter("codigo", codigo)
                .getResultList();

    }

    public List<Libro> getLibrosByTitulo(String titulo) {
        return em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo", Libro.class)
                .setParameter("titulo", titulo)
                .getResultList();

    }

    public List<Libro> getLibrosByAutor(String autor) {
        return em.createQuery("SELECT l FROM Libro l WHERE l.autores = :autor", Libro.class)
                .setParameter("autor", autor)
                .getResultList();

    }

    public List<Libro> getLibrosByAnho(int anho) {
        return em.createQuery("SELECT l FROM Libro l WHERE l.anho = :anho", Libro.class)
                .setParameter("anho", anho)
                .getResultList();

    }

    @Override
    public List<Libro> getAll() {
        return em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
    }

    @Override
    public boolean save(Libro libro) {
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Libro libro) {
        try {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Libro libro) {
        try {
            em.getTransaction().begin();
            em.remove(em.contains(libro) ? libro : em.merge(libro));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteById(long id) {
        return delete(get(id));
    }

}