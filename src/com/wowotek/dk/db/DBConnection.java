package com.wowotek.dk.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection
{
    private Connection c;
    
    private Connection getConnection()
    {
        return this.c;
    }
    
    public DBConnection()
    {
        initConnection();
    }
    
    public void initConnection() {
        String connectionURL = "jdbc:mysql://localhost/kontrakan_punya";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        properties.setProperty("useSSL", "false");
        properties.setProperty("autoReconnect", "true");

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            c = DriverManager.getConnection(connectionURL, properties);
            
        } 
        catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException cnfe) {
            System.out.println("Exception: " + cnfe.getMessage());
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
