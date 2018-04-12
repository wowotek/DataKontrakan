package com.wowotek.dk.db;

import static com.wowotek.dk.CONFIG.*;
import com.wowotek.dk.ErrorReporting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection
{

    private Connection c;
    private ErrorReporting er;
    
    public boolean DATABASE_CONNECTION_STATUS = false;

    private Connection getConnection()
    {
        return this.c;
    }

    public DBConnection(ErrorReporting _ER)
    {
        this.er = _ER;

        er.debug("Database Connection Initialization");
        for (int i = 0; i < CONNECTION_RETRY_TIMES; i++)
        {
            if (initConnection() == true)
            {
                er.debug("Connection Established, Total Tries = " + i, 0);
                this.DATABASE_CONNECTION_STATUS = true;
                break;
            }
            else
            {
                String dfs  = "Connection Failed";
                if(i < CONNECTION_RETRY_TIMES-1)
                {
                    dfs += ", Retrying...";
                    er.debug(dfs, 2);
                    try{Thread.sleep(3000);}catch (InterruptedException ex){}
                }
                else
                {
                    this.DATABASE_CONNECTION_STATUS = false;
                    er.debug("Database Connection Failed!", 3);
                }
            }
        }
    }

    public boolean initConnection()
    {
        String connectionURL = DB_LOCATION;
        Properties properties = new Properties();
        properties.setProperty("user", DB_USERNAME);
        properties.setProperty("password", DB_PASSWORD);
        properties.setProperty("useSSL", DB_USESSL);
        properties.setProperty("autoReconnect", DB_AUTORECONNECT);

        try
        {
            er.debug("Attempting to Handshake " + connectionURL);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            c = DriverManager.getConnection(connectionURL, properties);

            er.debug("Handshake Successful !" + connectionURL);
            return true;
        }
        catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException cnfe)
        {
            er.debug("Exception: " + cnfe.getMessage(), 2);
            return false;
        }
        catch (SQLException ex)
        {
            er.debug("SQLException: " + ex.getMessage(), 2);
            er.debug("SQLState: " + ex.getSQLState(), 2);
            er.debug("VendorError: " + ex.getErrorCode(), 2);
            return false;
        }
    }
}
