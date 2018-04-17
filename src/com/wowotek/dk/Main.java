package com.wowotek.dk;

import com.wowotek.dk.auth.Crypt;
import com.wowotek.dk.auth.Block;
import com.wowotek.dk.auth.authclasses.Users;
import com.wowotek.dk.exception.ClassVariableCannotBeEmpty;
import com.wowotek.dk.exception.VariableAlreadySetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{

    public static void main(String[] args)
    {
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
