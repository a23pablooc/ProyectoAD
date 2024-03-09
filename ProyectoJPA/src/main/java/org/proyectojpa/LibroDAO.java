package org.proyectojpa;

import jakarta.persistence.EntityManager;

import java.util.List;

public class LibroDAO implements IDAO<Libro> {
    @Override
    public Libro get(long id) {
        try (EntityManager em = EMF.get().createEntityManager()) {
            return em.find(Libro.class, id);
        }
    }

    @Override
    public List<Libro> getAll() {
        try (EntityManager em = EMF.get().createEntityManager()) {
            return em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
        }
    }

    @Override
    public void save(Libro libro) {
        try (EntityManager em = EMF.get().createEntityManager()) {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        }
    }

    @Override
    public void update(Libro libro) {
        try (EntityManager em = EMF.get().createEntityManager()) {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(Libro libro) {
        try (EntityManager em = EMF.get().createEntityManager()) {
            em.getTransaction().begin();
            em.remove(em.contains(libro) ? libro : em.merge(libro));
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteById(long id) {
        delete(get(id));
    }
}