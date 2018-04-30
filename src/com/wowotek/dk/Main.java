package com.wowotek.dk;

import com.wowotek.dk.db.*;
import com.wowotek.dk.gui.control.GUIFlow;

public class Main
{
    private static final ErrorReporting er = new ErrorReporting(true);
    private static final DBConnection dbc = new DBConnection(er);

    private static final GUIFlow g = 
            new GUIFlow(er, new AllDatabase(dbc,
                                            new DBAuthUserCredentials(dbc.c, er),
                                            new DBAuthUserData(dbc.c, er), 
                                            new DBPengeluaran(dbc.c, er), 
                                            new DBPemasukan(dbc.c, er), 
                                            new DBDaftarHarga(dbc.c, er)));

    public static void main(String[] args) throws InterruptedException
    {
        run();
    }
    
    public static void run()
    {
        g.run();
        closingCeremonies();
    }
    
    public static void closingCeremonies()
    {
        er.closeFile();
        dbc.closeConnection();
        System.exit(0);
    }
}
