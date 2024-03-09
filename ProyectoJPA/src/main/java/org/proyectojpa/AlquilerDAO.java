package org.proyectojpa;

import jakarta.persistence.EntityManager;

import java.util.List;

public class AlquilerDAO implements IDAO<Alquiler> {
    @Override
    public Alquiler get(long id) {
        try (EntityManager em = EMF.get().createEntityManager()) {
            return em.find(Alquiler.class, id);
        }
    }

    @Override
    public List<Alquiler> getAll() {
        try (EntityManager em = EMF.get().createEntityManager()) {
            return em.createQuery("SELECT a FROM Alquiler a", Alquiler.class).getResultList();
        }
    }

    @Override
    public void save(Alquiler alquiler) {
        try (EntityManager em = EMF.get().createEntityManager()) {
            em.getTransaction().begin();
            em.persist(alquiler);
            em.getTransaction().commit();
        }
    }

    @Override
    public void update(Alquiler alquiler) {
        try (EntityManager em = EMF.get().createEntityManager()) {
            em.getTransaction().begin();
            em.merge(alquiler);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(Alquiler alquiler) {
        try (EntityManager em = EMF.get().createEntityManager()) {
            em.getTransaction().begin();
            em.remove(em.contains(alquiler) ? alquiler : em.merge(alquiler));
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteById(long id) {
        delete(get(id));
    }
}
