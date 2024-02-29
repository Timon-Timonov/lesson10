package org.example.dao.impl;

import org.example.dao.DAO;
import org.example.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class DaoImpl<T, R> implements DAO<T, R> {

    public static final String GET_ALL_ENTITIES = "SELECT s FROM %s s";
    private final Class<T> clazz;
    private EntityManager em;

    public DaoImpl(Class<T> clazz) {

        this.clazz = clazz;
        this.em = getEm();
    }

    @Override
    public T create(T object) {

        if (object != null) {
            getEm().getTransaction().begin();
            getEm().persist(object);
            getEm().getTransaction().commit();
            closeManager();
        }
        return object;
    }

    @Override
    public T get(R id) throws EntityNotFoundException {

        return getEm().find(clazz, id);
    }

    @Override
    public T update(T object) {

        T t = null;
        if (object != null) {
            getEm().getTransaction().begin();
            t = getEm().merge(object);
            getEm().getTransaction().commit();
            closeManager();
        }
        return t;
    }

    @Override
    public void delete(R id) throws EntityNotFoundException {

        if (id != null) {
            getEm().getTransaction().begin();
            Object rootEntity = getEm().getReference(clazz, id);
            getEm().remove(rootEntity);
            getEm().getTransaction().commit();
        }
    }

    @Override
    public List<T> getAll() {

        TypedQuery<T> query = getEm().createQuery(getAllSqlString(), clazz);
        return query.getResultList();
    }

    @Override
    public void closeManager() {

        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    protected EntityManager getEm() {

        if (em == null || !em.isOpen()) {
            em = HibernateUtil.getEntityManager();
        }
        return em;
    }

    protected String getAllSqlString() {

        return String.format(GET_ALL_ENTITIES, clazz.getSimpleName());
    }
}
