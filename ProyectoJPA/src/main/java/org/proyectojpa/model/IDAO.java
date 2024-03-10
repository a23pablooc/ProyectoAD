package org.proyectojpa.model;

import java.util.List;

public interface IDAO<T> {
    T get(long id);

    List<T> getAll();

    boolean save(T t);

    boolean update(T t);

    boolean delete(T t);

    boolean deleteById(long id);
}