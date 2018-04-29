package com.wowotek.dk;

import com.wowotek.dk.db.*;
import com.wowotek.dk.gui.control.GUIFlow;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main
{

    private static ErrorReporting er = new ErrorReporting(true);
    private static DBConnection dbc = new DBConnection(er);

    private static GUIFlow g = new GUIFlow(er, dbc.c);

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
