package com.wowotek.dk;

import com.wowotek.dk.db.*;
import com.wowotek.dk.gui.control.GUIFlow;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main
{

    private static ErrorReporting er = new ErrorReporting(true);
    
    private static final DBConnection dbc = new DBConnection(er);
    private static final DBPengeluaran dbpen = new DBPengeluaran(dbc.c, er);
    private static final DBPemasukan dbpem = new DBPemasukan(dbc.c, er);
    private static final DBDaftarHarga dbdh = new DBDaftarHarga(dbc.c, er);
    
    private static final AllDatabase ad = new AllDatabase(dbpen, dbpem, dbdh);
    private static final GUIFlow g = new GUIFlow(er, dbc.c, ad);

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
