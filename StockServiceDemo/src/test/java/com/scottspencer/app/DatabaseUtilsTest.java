package com.scottspencer.app;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.scottspencer.util.DatabaseInitializationException;
import com.scottspencer.util.DatabaseUtils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;



public class DatabaseUtilsTest {
	@Test
	public void testGoodInitFile() throws Exception {
		// This isn't testing anything but it is throwing an exception :-)
		// There are several errors in this test
		// 1) you are trying to initialize a database which you haven't gotten a connection to.
		// 2) the initializationfile script is testing the wrong database. This is for the hobbies database
		// 3) you have to create a connection to the database and configure the connection
		// I've copied the essential parts from other methods to show you
		// the correct sequence of steps, however, you are still going to have a problem running this test 
		// because you're using the wrong initializaton script. 
		Configuration configuration = DatabaseUtils.getConfiguration();
		String databaseUrl = configuration.getProperty("connection.url");
        String username = configuration.getProperty("hibernate.connection.username");
        String password = configuration.getProperty("hibernate.connection.password");
        Connection connection = DriverManager.getConnection(databaseUrl, username, password);
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
	}

//    @Test
//    public void testGoodInitFile() throws Exception {
//        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
//    }
//
//    @Test(expected = DatabaseInitializationException.class)
//    public void testBadInitFile() throws Exception {
//        DatabaseUtils.initializeDatabase("bogus");
//    }
//
//    @Test
//    public void testGetConnection() throws Exception{
//        Connection connection = DatabaseUtils.getConnection();
//        assertNotNull("verify that we can get a connection ok",connection);
//    }
//
//    @Test
//    public void testGetConnectionWorks() throws Exception{
//        Connection connection = DatabaseUtils.getConnection();
//        Statement statement = connection.createStatement();
//        boolean execute = statement.execute("select * from person");
//        assertTrue("verify that we can execute a statement",execute);
//    }

}
