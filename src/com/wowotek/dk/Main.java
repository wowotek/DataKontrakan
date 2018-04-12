package com.wowotek.dk;

import com.wowotek.dk.db.DBConnection;

public class Main
{
    public static void main(String[] args)
    {
        ErrorReporting er = new ErrorReporting();
        DBConnection dbc = new DBConnection(er);
        
        if(dbc.DATABASE_CONNECTION_STATUS == false)
        {
            er.debug("Closing Program...", 4);
            er.closeFile();
        }
    }
}
