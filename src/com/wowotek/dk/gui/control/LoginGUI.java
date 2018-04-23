package com.wowotek.dk.gui.control;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.gui.FormLogin;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class LoginGUI
{
    private final FormLogin frameLogin;
    private final Object lock = new Object();
    
    private final ErrorReporting er;
    
    public LoginGUI(ErrorReporting er)
    {
        this.er = er;
        this.frameLogin = new FormLogin(er);
    }
    
    public boolean run()
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
                    frameLogin.purge();
                    er.debug("Login Screen Closed", 10);
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
}
