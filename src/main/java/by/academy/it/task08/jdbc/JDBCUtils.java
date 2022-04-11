package by.academy.it.task08.jdbc;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static by.academy.it.task08.jdbc.ConnectionPool.*;

public class JDBCUtils {

    /**
     * @return
     */
    private static HikariDataSource getConnectionPool() {
        HikariDataSource dataSource = null;
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(getUrl());
            config.setUsername(getUser());
            config.setPassword(getPassword());
            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = getConnectionPool().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * @return
     * @throws SQLException
     */
    private static DatabaseMetaData getDatabaseMetaData()
            throws SQLException {
        return getConnection().getMetaData();
    }

    /**
     * @return
     * @throws SQLException
     */
    public static String getNameAndVersionDatabase()
            throws SQLException {
        return getDatabaseMetaData().getDatabaseProductName()
                + getDatabaseMetaData().getDatabaseProductVersion();
    }

    /**
     * @return
     * @throws SQLException
     */
    public static String getNameAndVersionDriverDatabase()
            throws SQLException {
        return getDatabaseMetaData().getDatabaseProductName()
                + getDatabaseMetaData().getDatabaseProductVersion();
    }

    /**
     * @return
     * @throws SQLException
     */
    public static String getNameJDBCDriver()
            throws SQLException {
        return getDatabaseMetaData().getDatabaseProductName();
    }

    /**
     * @return
     * @throws SQLException
     */
    public static String getNameUserDatabase()
            throws SQLException {
        return getDatabaseMetaData().getUserName();
    }

    /**
     * @return
     * @throws SQLException
     */
    public static String getUrlDatabase()
            throws SQLException {
        return getDatabaseMetaData().getURL();
    }
}

