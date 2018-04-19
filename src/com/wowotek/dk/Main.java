package com.wowotek.dk;

import com.wowotek.dk.db.DBConnection;
import com.wowotek.dk.db.DBPemasukan;


public class Main
{
    private static ErrorReporting er;
    private static DBConnection dbc;

    public static void main(String[] args)
    {
        er = new ErrorReporting(true);
        dbc = new DBConnection(er);

        closingCeremonies();
    }

    private static void closingCeremonies()
    {
        er.debug("Closing Program...", 4);
        dbc.closeConnection();
        er.closeFile();
    }
}
