package by.academy.it.task08.jdbc;

import org.junit.Assert;
import org.junit.Test;

import static by.academy.it.task08.jdbc.ConnectionPool.*;

public class ConnectionPoolTest extends Assert {

    /**
     *
     */
    @Test
    public void urlTest(){
        assertEquals("jdbc:mysql://localhost:3306/task_08",getUrl());
    }

    /**
     *
     */
    @Test
    public void userTest(){
        assertEquals("root",getUser());
    }

    /**
     *
     */
    @Test
    public void passwordTest(){
        assertEquals("grigorovich_1",getPassword());
    }



}
