package org.proyectojpa;

import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteDAO implements IDAO<Cliente> {
    @Override
    public Cliente get(long id) {
        try (EntityManager em = EMF.get().createEntityManager()) {
            return em.find(Cliente.class, id);
        }
    }

    @Override
    public List<Cliente> getAll() {
        try (EntityManager em = EMF.get().createEntityManager()) {
            return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        }
    }

    @Override
    public void save(Cliente cliente) {
        try (EntityManager em = EMF.get().createEntityManager()) {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        }
    }

    @Override
    public void update(Cliente cliente) {
        try (EntityManager em = EMF.get().createEntityManager()) {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(Cliente cliente) {
        try (EntityManager em = EMF.get().createEntityManager()) {
            em.getTransaction().begin();
            em.remove(em.contains(cliente) ? cliente : em.merge(cliente));
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteById(long id) {
        delete(get(id));
    }
}
