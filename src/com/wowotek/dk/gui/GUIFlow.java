/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wowotek.dk.gui;

import com.wowotek.dk.ErrorReporting;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author wowotek
 */
public class GUIFlow
{

    private final ErrorReporting er;

    private final FormLogin frameLogin = new FormLogin();
    private final FormDataKontrakan frameKontrakan = new FormDataKontrakan();

    private final Object lock = new Object();

    private boolean LoginDone = false;

    public GUIFlow(ErrorReporting er)
    {
        this.er = er;
    }

    private boolean runLoginScreen()
    {

        frameLogin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameLogin.setVisible(true);

        Thread t = new Thread()
        {
            @Override
            public void run()
            {
                synchronized (lock)
                {
                    while (frameLogin.isVisible())
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
                }
            }
        };
        t.start();

        frameLogin.addWindowListener(new WindowAdapter()
        {

            @Override
            public void windowClosing(WindowEvent arg0)
            {
                synchronized (lock)
                {
                    frameLogin.setVisible(false);
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
            er.debug("Failed to Join GUI-runLoginScreen Thread !");
        }

        return frameLogin.Inputed;
    }

    private void runMainScreen()
    {

        frameKontrakan.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frameKontrakan.setVisible(true);

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
                }
            }
        };
        t.start();

        frameKontrakan.addWindowListener(new WindowAdapter()
        {

            @Override
            public void windowClosing(WindowEvent arg0)
            {
                synchronized (lock)
                {
                    frameKontrakan.setVisible(false);
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
            er.debug("Failed to Join GUI-runLoginScreen Thread !");
        }
    }

    public void run()
    {
        this.LoginDone = runLoginScreen();
        if (LoginDone == false)
        {
            runMainScreen();
        }
    }
}
