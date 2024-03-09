package org.proyectojpa;

import java.util.List;

public interface IDAO<T> {
    T get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);

    void deleteById(long id);
}