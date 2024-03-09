import jakarta.persistence.EntityManager;

import java.util.List;

public class LibroDAO<T> implements IDAO<T> {
    

    @Override
    public T get(long id) {
        try (EntityManager em = EMF.get().createEntityManager()) {
            return em.find(T, id);
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public void save(T t) {

    }

    @Override
    public void update(T t) {

    }

    @Override
    public void delete(T t) {

    }

    @Override
    public void deleteById(long id) {

    }
}