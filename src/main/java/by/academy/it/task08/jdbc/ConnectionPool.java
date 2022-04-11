package by.academy.it.task08.jdbc;

import java.util.ResourceBundle;

/**
 *
 */
public class ConnectionPool {
    /**
     *
     */
    private static final String DATABASE_PROPERTIES = "liquibase";
    /**
     *
     */
    private static final String URL_PROPERTIES = "url";
    /**
     *
     */
    private static final String USER_PROPERTIES = "username";
    /**
     *
     */
    private static final String PASSWORD_PROPERTIES = "password";
    /**
     *
     */

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.
            getBundle(
                    DATABASE_PROPERTIES);

    /**
     * @return
     */
    private static String getValue(String property) {
        return RESOURCE_BUNDLE.getString(property);
    }

    /**
     * @return
     */
    public static String getUrl() {
        return getValue(URL_PROPERTIES);
    }

    /**
     * @return
     */
    public static String getUser() {
        return getValue(USER_PROPERTIES);
    }

    /**
     * @return
     */
    public static String getPassword() {
        return getValue(PASSWORD_PROPERTIES);
    }

}
