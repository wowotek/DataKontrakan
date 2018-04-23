/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wowotek.dk.gui.control;

import com.wowotek.dk.ErrorReporting;

/**
 *
 * @author wowotek
 */
public class GUIFlow
{
    private final ErrorReporting er;
    
    public GUIFlow(ErrorReporting er)
    {
        this.er = er;
    }
    public void run()
    {
        LoginGUI lg = new LoginGUI(er);
        lg.run();
    }
}
