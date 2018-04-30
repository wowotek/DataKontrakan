/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wowotek.dk.gui.control;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.db.AllDatabase;
import com.wowotek.dk.db.DBPemasukan;
import com.wowotek.dk.db.DBPengeluaran;
import com.wowotek.dk.gui.FormDataKontrakan;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

/**
 *
 * @author wowotek
 */
public class MainGUI
{

    private final FormDataKontrakan frameKontrakan;
    private final Object lock = new Object();

    private final ErrorReporting er;
    private final AllDatabase ad;

    public MainGUI(ErrorReporting er, AllDatabase ad)
    {
        this.er = er;
        this.ad = ad;
        this.frameKontrakan = new FormDataKontrakan(er, ad);
    }

    public void run()
    {
        frameKontrakan.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        final ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
        
        Thread t = new Thread()
        {
            @Override
            public void run()
            {
                synchronized (lock)
                {
                    while (frameKontrakan.isVisible())
                    {
                        try
                        {
                            lock.wait();
                        }
                        catch (InterruptedException e)
                        {
                            er.debug("User Intterupted GUI-RunLoginScreen Thread !");
                        }
                    }
                    // Do Something Here AFTER the Window Closed
                    er.debug("Main GUI Closed !");
                }
            }
        };
        frameKontrakan.setVisible(true);
        t.setName("Frame MainApp");
        t.start();
        es.scheduleAtFixedRate(this.frameKontrakan::updateTablePengeluaran, 0, 10, TimeUnit.SECONDS);

        er.debug  ("Thread Started -> ", 10);
        er.debugln("          ID : " + t.getId());
        er.debugln("        Name : " + t.getName());
        er.debugln("    Priority : " + t.getPriority());

        frameKontrakan.addWindowListener(new WindowAdapter()
        {

            @Override
            public void windowClosing(WindowEvent arg0)
            {
                synchronized (lock)
                {
                    frameKontrakan.setVisible(false);
                    frameKontrakan.dispose();
                    lock.notify();
                }
            }

            @Override
            public void windowClosed(WindowEvent arg1)
            {
                synchronized (lock)
                {
                    frameKontrakan.setVisible(false);
                    frameKontrakan.dispose();
                    lock.notify();
                }
            }
        });

        try
        {
            t.join();
        }
        catch (InterruptedException e)
        {
            er.debug("Failed to Join " + t.getName() + ":" + t.getId() + "!");
        }
    }
}
