package by.academy.it.task08;

import by.academy.it.task08.dao.EntityDao;
import by.academy.it.task08.daoImpl.EntityDaoProvider;
import by.academy.it.task08.entity.CarEntity;

import java.util.Map;

/**
 * There is a Car entity, it has
 * - id;
 * - model
 * - color
 * - coast
 *
 * <p>The task is to create a project. Adding a table to the database must be
 * done through liquibase,
 * make tests using H2.</p>
 * Cover functionality with tests and make a report using the jacoco plugin.
 * Connect checkstyle to the project.
 * For the Car entity, make a DAO over each field, write your own annotation
 * MyColumn(name - the name of the column) with the name of the column,
 * write the annotation MyTable(name - the name of the table)
 * above the Car class.
 * Implement CRUD operations on Car using jdbc
 * <p>
 * - select
 * - update
 * - delete
 * - insert
 * <p>
 * Moreover, these operations should make a request to the database
 * using the annotations
 * MyColumn and MyTable (through reflection). i.e. if the user of
 * this API creates another entity, then
 * - select
 * - update
 * - delete
 * - insert
 * <p>should work without changing the internal logic</p>
 *
 * @author Alexander Grigorovich
 * @version 1.0
 */

final class App {

    private App() {
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        /**
         *
         */
        CarEntity car1 = new CarEntity(3, "Lada 2115", "Red", "1500$");
        /**
         *
         */
        CarEntity car2 = new CarEntity(4, "Lada 2111", "Green", "1000$");
        /**
         *
         */
        CarEntity car3 = new CarEntity(5, "Lada 2105", "White", "700$");
        /**
         *
         */
        CarEntity car4 = new CarEntity(2, "Lada 2103", "Brown", "700$");

        /**
         *
         */
        EntityDao dao =
                EntityDaoProvider.getNewInstance(CarEntity.class);

        /**
         *
         */
        dao.create(car1);
        /**
         *
         */
        dao.create(car2);
        /**
         *
         */
        dao.create(car3);

        /**
         *
         */
        Map<Long, Object> list = dao.read();
        for (Map.Entry<Long, Object> item : list.entrySet()) {
            System.out.printf("Key: %s  Object: %s \n", item.getKey(), item.getValue().toString());
        }

        dao.update(2L, car4);

        for (Map.Entry<Long, Object> item : list.entrySet()) {
            System.out.printf("Key: %s  Object: %s \n", item.getKey(), item.getValue().toString());
        }
        dao.delete(5L);

        for (Map.Entry<Long, Object> item : list.entrySet()) {
            System.out.printf("Key: %s  Object: %s \n", item.getKey(), item.getValue().toString());
        }

        dao.deleteAll();

        for (Map.Entry<Long, Object> item : list.entrySet()) {
            System.out.printf("Key: %s  Object: %s \n", item.getKey(), item.getValue().toString());
        }

    }
}