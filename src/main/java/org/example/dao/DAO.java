package org.example.dao;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface DAO<T, R> {

    T create(T t);

    T get(R id) throws EntityNotFoundException;

    T update(T t);

    void delete(R id) throws EntityNotFoundException;

    List<T> getAll();

    void closeManager();
}
