package cn.itcast.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JPA工具类,获得EntityManager对象
 */
public class JpaUtils {

    private static EntityManagerFactory factory;

    static  {
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    public static EntityManager getEntityManager() {
       return factory.createEntityManager();
    }
}
