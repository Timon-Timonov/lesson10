package org.example.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManagerFactory EM_FACTORY =
        Persistence.createEntityManagerFactory("homeWork");

    private HibernateUtil() {
    }

    public static EntityManager getEntityManager() {

        return EM_FACTORY.createEntityManager();
    }

    public static void closeFactory() {

        EM_FACTORY.close();
    }
}
