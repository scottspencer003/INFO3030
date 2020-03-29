package com.scottspencer.util;


import com.scottspencer.app.DatabaseStockService;
import com.ibatis.common.jdbc.ScriptRunner;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class that contains database related utility methods.
 */
public class DatabaseUtils {

    // in a real program these values would be a configurable property and not hard coded.
    // JDBC driver name and database URL
    @SuppressWarnings("unused")
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //private static final String DB_URL = "jdbc:mysql://localhost:3306/stocks?serverTimezone=UTC";
    
    public static final String initializationFile = "./src/main/sql/db_initialization.sql";
    
    private static SessionFactory sessionFactory;
    private static Configuration configuration;

    //  Database credentials
//    private static final String USER = "monty";
//    private static final String PASS = "some_pass";
    
        

    /**
     * A utility method that runs a db initialize script.
     * @param initializationScript    full path to the script to run to create the schema
     * @throws DatabaseInitializationException
     */
    public static void initializeDatabase(String initializationScript) throws DatabaseInitializationException {

        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            ScriptRunner runner = new ScriptRunner(connection, false, false);
            InputStream inputStream = new  FileInputStream(initializationScript);

            InputStreamReader reader = new InputStreamReader(inputStream);  

            runner.runScript(reader);
            reader.close();
            connection.commit();
            connection.close();

        } catch (DatabaseConnectionException | SQLException |IOException e) {
           throw new DatabaseInitializationException("Could not initialize db because of:"
                   + e.getMessage(),e);
        }

    }
    
    /*
   * @return SessionFactory for use with database transactions
   */
    public static SessionFactory getSessionFactory() {

        // singleton pattern
        synchronized (DatabaseStockService.class) {
            if (sessionFactory == null) {

                Configuration configuration = getConfiguration();

                ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .buildServiceRegistry();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            }
        }
        return sessionFactory;
    }
    
    /**
     * Create a new or return an existing database configuration object.
     *
     * @return a Hibernate Configuration instance.
     */
    public static Configuration getConfiguration() {

        synchronized (DatabaseUtils.class) {
            if (configuration == null) {
                configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");
            }
        }
        return configuration;
    }
    
    public static Connection getConnection() throws DatabaseConnectionException{
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String databaseUrl = configuration.getProperty("connection.url");
            String username = configuration.getProperty("hibernate.connection.username");
            String password = configuration.getProperty("hibernate.connection.password");
            connection = DriverManager.getConnection(databaseUrl, username, password);
            
        } catch (ClassNotFoundException  | SQLException e)  {
           throw new  DatabaseConnectionException("Could not connection to database." + e.getMessage(), e);
        }
        return connection;
    }
}
