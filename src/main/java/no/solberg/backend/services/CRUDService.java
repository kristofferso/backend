package no.solberg.backend.services;

import java.util.Collection;

public interface CRUDService <T, ID> {
    T findById(ID id);
    Collection<T> findAll();
    T add(T entity);
}
