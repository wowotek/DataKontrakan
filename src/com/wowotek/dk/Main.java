package com.wowotek.dk;

import com.wowotek.dk.auth.Auth;

public class Main
{
    public static void main(String[] args)
    {
        ErrorReporting er = new ErrorReporting();
        Auth a = new Auth(er);
        System.out.println();
    }

//    private static ErrorReporting er;
//    private static DBConnection dbc;
//
//    public static void main(String[] args)
//    {
//        er = new ErrorReporting();
//        dbc = new DBConnection(er);
//
//        if (dbc.DATABASE_CONNECTION_STATUS == false)
//        {
//            closingCeremonies();
//        }
//        else
//        {
//            for (int i = 0; i < 10; i++)
//            {
//                DBPemasukan dbp = new DBPemasukan(dbc.c, er);
//                dbp.ambilDataPemasukan();
//            }
//        }
//
//        closingCeremonies();
//    }
//
//    private static void closingCeremonies()
//    {
//        er.debug("Closing Program...", 4);
//        dbc.closeConnection();
//        er.closeFile();
//    }
}