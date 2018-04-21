package com.wowotek.dk;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util
{

    public String getDate()
    {
        Date dNow = new Date();
        SimpleDateFormat ft;
        ft = new SimpleDateFormat("hh:mm:ss");
        return (String) (ft.format(dNow));
    }

    public String getDate(boolean BOTH)
    {
        Date dNow = new Date();
        SimpleDateFormat ft;

        if (BOTH == true)
        {
            ft = new SimpleDateFormat("dd.MM.yyyy | hh:mm:ss");
        } else
        {
            ft = new SimpleDateFormat("hh:mm:ss");
        }

        return (String) (ft.format(dNow));
    }

    public void delay(double seconds)
    {
        int w = (int) ((seconds * 1000) + 500);
        try
        {
            Thread.sleep(w);
        } catch (InterruptedException e)
        {
            try
            {
                Thread.currentThread().interrupt();
                Thread.currentThread().join();
            } catch (InterruptedException ex)
            {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.flush();
        System.err.flush();
    }
    
    public boolean checkTanggal(String tanggal)
    {
        return true;
    }
}
