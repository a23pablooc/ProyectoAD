package org.proyectojpa.model;

import jakarta.persistence.EntityManager;

import java.util.List;

public class ClienteDAO implements IDAO<Cliente> {

    private final EntityManager em;

    public ClienteDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Cliente get(long id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> getClientesByDNI(String dni) {
        return em.createQuery("SELECT c FROM Cliente c WHERE c.DNI = :dni", Cliente.class)
                .setParameter("dni", dni)
                .getResultList();

    }

    @Override
    public List<Cliente> getAll() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    @Override
    public boolean save(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.remove(em.contains(cliente) ? cliente : em.merge(cliente));
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
