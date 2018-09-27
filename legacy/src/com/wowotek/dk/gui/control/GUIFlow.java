/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wowotek.dk.gui.control;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.db.AllDatabase;
import java.sql.Connection;

/**
 *
 * @author wowotek
 */
public class GUIFlow
{
    private final ErrorReporting er;
    private final AllDatabase ad;
    
    public GUIFlow(ErrorReporting er, AllDatabase ad)
    {
        this.er = er;
        this.ad = ad;
    }
    
    public void run()
    {
        LoginGUI lg = new LoginGUI(er, ad);
        MainGUI mg = new MainGUI(er, ad);
        mg.run();
//        if(lg.run())
//        {
//            mg.run();
//        }
    }
}
