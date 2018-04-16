package com.wowotek.dk.db;

import static com.wowotek.dk.CONFIG.*;
import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection
{

  public Connection c;
  private ErrorReporting er;

  public boolean DATABASE_CONNECTION_STATUS = false;

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
        String dfs = "Connection Failed";
        if (i < CONNECTION_RETRY_TIMES - 1)
        {
          dfs += ", Retrying...";
          er.debug(dfs, 2);
          new Util().delay(3);
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
      er.debug("Attempting to Handshake... URL: " + connectionURL);
      try
      {
        Thread.sleep(1500);
      }
      catch (InterruptedException ex)
      {
      }
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      c = DriverManager.getConnection(connectionURL, properties);

      er.debug("Handshake Successful !");
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
      er.debugln("SQLState: " + ex.getSQLState());
      er.debugln("VendorError: " + ex.getErrorCode());
      return false;
    }
  }

  public boolean closeConnection()
  {
    try
    {
      er.debug("Menutup Koneksi Database !");
      new Util().delay(1);
      this.c.close();
      new Util().delay(1);
      er.debug("Koneksi Berhasil di Tutup");
      return true;
    }
    catch (SQLException ex)
    {
      return false;
    }
  }
}
