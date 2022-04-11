package by.academy.it.task08.dao;

import java.util.Map;

/**
 *
 */
public interface EntityDao {

    /**
     * @param entity
     */
    void create(Object entity);

    /**
     * @return -
     */
    Map<Long, Object> read();

    /**
     * @param id
     * @param entity
     */
    void update(Long id, Object entity);

    /**
     * @param id
     */
    void delete(Long id);

    void deleteAll();
}
