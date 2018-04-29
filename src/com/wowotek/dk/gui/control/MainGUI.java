/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wowotek.dk.gui.control;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.db.DBPemasukan;
import com.wowotek.dk.db.DBPengeluaran;
import com.wowotek.dk.gui.FormDataKontrakan;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

    public MainGUI(ErrorReporting er, DBPengeluaran dbpen, DBPemasukan dbpem)
    {
        this.er = er;
        this.frameKontrakan = new FormDataKontrakan(er, dbpen, dbpem);
    }

    public void run()
    {
        frameKontrakan.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

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
