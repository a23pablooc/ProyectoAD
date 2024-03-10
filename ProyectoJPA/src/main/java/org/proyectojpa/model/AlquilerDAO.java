package org.proyectojpa.model;

import jakarta.persistence.EntityManager;

import java.util.List;

public class AlquilerDAO implements IDAO<Alquiler> {

    private final EntityManager em;

    public AlquilerDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Alquiler get(long id) {
        return em.find(Alquiler.class, id);

    }

    public List<Alquiler> getAlquileresByCliente(Cliente cliente) {
        return em.createQuery("SELECT a FROM Alquiler a WHERE a.cliente = :cliente", Alquiler.class)
                .setParameter("cliente", cliente)
                .getResultList();

    }

    public List<Alquiler> getAlquileresByLibro(Libro libro) {
        return em.createQuery("SELECT a FROM Alquiler a WHERE a.libro = :libro", Alquiler.class)
                .setParameter("libro", libro)
                .getResultList();

    }

    @Override
    public List<Alquiler> getAll() {
        return em.createQuery("SELECT a FROM Alquiler a", Alquiler.class).getResultList();
    }

    @Override
    public boolean save(Alquiler alquiler) {
        try {
            em.getTransaction().begin();
            em.persist(alquiler);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Alquiler alquiler) {
        try {
            em.getTransaction().begin();
            em.merge(alquiler);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Alquiler alquiler) {
        try {
            em.getTransaction().begin();
            em.remove(em.contains(alquiler) ? alquiler : em.merge(alquiler));
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
