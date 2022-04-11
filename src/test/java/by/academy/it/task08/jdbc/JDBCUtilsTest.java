package by.academy.it.task08.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import static by.academy.it.task08.jdbc.JDBCUtils.getConnection;


public class JDBCUtilsTest extends Assert {

    static HikariDataSource dataSource = null;
    static Connection connection = null;


    @Test
    public void noEqualsConnectionTest() {

            try {
                HikariConfig config = new HikariConfig();
                config.setJdbcUrl("jdbc:mysql://localhost:3306/task_08");
                config.setUsername("root");
                config.setPassword("grigorovich_1");
                dataSource = new HikariDataSource(config);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                connection = dataSource.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }

        assertEquals(false, connection.equals(getConnection()));
    }

}
