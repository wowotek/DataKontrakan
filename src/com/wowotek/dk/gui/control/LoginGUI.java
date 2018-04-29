package com.wowotek.dk.gui.control;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.db.DBAuthUserCredentials;
import com.wowotek.dk.db.DBAuthUserData;
import com.wowotek.dk.gui.FormLogin;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import javax.swing.JFrame;

public class LoginGUI
{

    private final FormLogin frameLogin;
    private final Object lock = new Object();

    private final ErrorReporting er;
    private final DBAuthUserCredentials dbuc;
    private final DBAuthUserData dbud;

    public LoginGUI(ErrorReporting er, Connection c)
    {
        this.er = er;
        this.dbuc = new DBAuthUserCredentials(c, er);
        this.dbud = new DBAuthUserData(c, er);
        this.frameLogin = new FormLogin(er, dbuc, dbud);
    }

    public boolean run()
    {
        frameLogin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
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
                    er.debug("Login GUI Closed !");
                }
            }
        };
        frameLogin.setVisible(true);
        t.start();
        
        frameLogin.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent arg0)
            {
                synchronized (lock)
                {
                    frameLogin.setVisible(false);
                    frameLogin.dispose();
                    lock.notify();
                }
            }
            
            public void windowClosed(WindowEvent arg1)
            {
                synchronized (lock)
                {
                    frameLogin.setVisible(false);
                    frameLogin.dispose();
                    lock.notify();
                }
            }

        });

        try
        {
            System.out.println(t.toString());
            t.join();
        }
        catch (InterruptedException e)
        {
            er.debug("Failed to Join GUI-runLoginScreen Thread !");
        }
        
        return frameLogin.SessionCond;
    }
}
