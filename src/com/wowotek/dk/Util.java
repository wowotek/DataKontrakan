package com.wowotek.dk;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        
        if(BOTH == true)
            ft = new SimpleDateFormat("dd.MM.yyyy | hh:mm:ss");
        else
            ft = new SimpleDateFormat("hh:mm:ss");
        
        return (String) (ft.format(dNow));
    }
}
