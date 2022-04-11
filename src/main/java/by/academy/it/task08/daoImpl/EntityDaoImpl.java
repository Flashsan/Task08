package by.academy.it.task08.daoImpl;

import by.academy.it.task08.annatation.MyColumn;
import by.academy.it.task08.annatation.MyTable;
import by.academy.it.task08.dao.EntityDao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static by.academy.it.task08.jdbc.JDBCUtils.getConnection;

/**
 *
 */
public class EntityDaoImpl implements EntityDao {
    /**
     *
     */
    private static final String READ = "SELECT * FROM %s";

    /**
     *
     */
    private static final String DELETE =
            "DELETE FROM %s WHERE id = %d";
    /**
     *
     */
    private static final String DELETE_ALL =
            "TRUNCATE FROM %s";
    /**
     *
     */
    private static final String CREATE =
            "INSERT INTO %s (%s) VALUES (%s)";
    /**
     *
     */
    private static final String UPDATE =
            "UPDATE %s SET %s WHERE id = %d";
    /**
     *
     */
    private static final String SINGLE_QUOTE_SIGN = "'";
    /**
     *
     */
    private static final String COMMA_SIGN = ",";
    /**
     *
     */
    private static final String EQUAL_SIGN = "=";
    /**
     *
     */
    public static final int SHIFT_TO_COLUMN = 1;
    /**
     *
     */
    public static final int ID_COLUMN = 1;
    /**
     *
     */
    private String tableName;
    /**
     *
     */
    private final String[] tableColumnNames;
    /**
     *
     */
    private final String[] classFieldNames;
    /**
     *
     */
    private Constructor constructor;


    /**
     * @param aClass
     */
    public EntityDaoImpl(final Class aClass) {

        if (aClass.isAnnotationPresent(MyTable.class)) {
            tableName = ((MyTable) aClass.getAnnotation(MyTable.class)).name();
        } else {
            System.out.println("Annotation 'MyTable' is absent!");
        }

        Field[] fields = aClass.getDeclaredFields();
        int fieldCount = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(MyColumn.class)) {
                fieldCount++;
            }
            field.setAccessible(false);
        }

        tableColumnNames = new String[fieldCount];
        classFieldNames = new String[fieldCount];
        Class[] classFieldTypes = new Class[fieldCount];
        int index = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(MyColumn.class)) {
                tableColumnNames[index] =
                        field.getAnnotation(MyColumn.class)
                                .name();
                classFieldTypes[index] = field.getType();
                classFieldNames[index] = field.getName();
            }
            index++;
            field.setAccessible(false);
        }

        try {
            constructor = aClass.getConstructor(classFieldTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create new instance of persistent class.
     *
     * @param params - values of class fields
     * @return new instance of persistent class
     */
    private Object createEntity(final Object[] params) {
        Object entity = null;
        try {
            entity = constructor.newInstance(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * @return -
     */
    @Override
    public Map<Long, Object> read() {
        Map<Long, Object> map = new HashMap<>();
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet =
                        statement.executeQuery(// SELECT * FROM %s
                                String.format(READ, tableName));
        ) {
            Object[] paramsForCreation = new Object[classFieldNames.length];
            while (resultSet.next()) {
                for (int i = 0; i < tableColumnNames.length; i++) {
                    paramsForCreation[i] =
                            resultSet.getObject(i + SHIFT_TO_COLUMN);
                }
                map.put(resultSet.getLong(ID_COLUMN),
                        createEntity(paramsForCreation));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * @param id
     * @param entity
     */
    @Override
    public void update(final Long id,
                       final Object entity) {

        int updatedCount = 0;
        String[] params = getObjectParam(entity);

        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
        ) {
            updatedCount =
                    statement.executeUpdate(// UPDATE %s SET %s WHERE id = %d
                            String.format(UPDATE,
                                    tableName,
                                    IntStream.range(0, tableColumnNames.length)
                                            .mapToObj(i -> tableColumnNames[i]
                                                    .concat(EQUAL_SIGN)
                                                    .concat(params[i]))
                                            .collect(Collectors
                                                    .joining(COMMA_SIGN)), id));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (updatedCount != 1) {
            System.out.println(
                    "Updating transaction failed (updatedCount != 1)");
        }
    }

    /**
     * @param id
     */
    @Override
    public void delete(final Long id) {
        int deletedCount = 0;
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
        ) {
            deletedCount =
                    statement.executeUpdate(// DELETE FROM %s WHERE id = %d
                            String.format(DELETE,
                                    tableName,
                                    id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (deletedCount != 1) {
            System.out.println(
                    "Deletion transaction failed (deletedCount != 1)");
        }
    }

    /**
     *
     */
    @Override
    public void deleteAll() {
        int deletedCount = 0;
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
        ) {
            deletedCount =
                    statement.executeUpdate(// DELETE FROM %s
                            String.format(DELETE_ALL,
                                    tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (deletedCount != 1) {
            System.out.println(
                    "Deletion transaction failed (deletedCount != 1)");
        }
    }

    /**
     * @param entity
     */
    @Override
    public void create(final Object entity) {
        int insertedCount = 0;
        String[] params = getObjectParam(entity);

        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
        ) {
            insertedCount =
                    statement.executeUpdate(//INSERT INTO %s (%s) VALUES (%s)
                            String.format(CREATE,
                                    tableName,
                                    String.join(COMMA_SIGN, tableColumnNames),
                                    String.join(COMMA_SIGN, params)
                            ));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (insertedCount != 1) {
            System.out.println(
                    "Creation transaction failed (insertedCount != 1)");
        }
    }

    /**
     * @param entity
     * @return -
     */
    private String[] getObjectParam(final Object entity) {
        String[] param = new String[classFieldNames.length];
        try {
            for (int i = 0; i < classFieldNames.length; i++) {
                Field field = entity.getClass().
                        getDeclaredField(classFieldNames[i]);
                field.setAccessible(true);
                Object fieldValue = field.get(entity);
                if (fieldValue instanceof CharSequence) {
                    fieldValue = SINGLE_QUOTE_SIGN
                            .concat(((CharSequence) fieldValue)
                                    .toString())
                            .concat(SINGLE_QUOTE_SIGN);
                }
                param[i] = fieldValue.toString();
                field.setAccessible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return param;
    }
}

