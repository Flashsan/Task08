package by.academy.it.task08.daoImpl;

import by.academy.it.task08.dao.EntityDao;
import by.academy.it.task08.jdbc.ConnectionPool;

/**
 *
 */
public final class EntityDaoProvider {

    /**
     *
     */
    private EntityDaoProvider() {

    }

    /**
     * @param aClass
     * @return
     */
    public static EntityDao getNewInstance(final Class aClass) {
        EntityDao entityDao = null;
        try {
            entityDao = new EntityDaoImpl(aClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entityDao;
    }
}
