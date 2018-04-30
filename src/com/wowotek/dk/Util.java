package com.wowotek.dk;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util
{
    public final String StandardCharacter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

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

    public long getUnixTimestamp()
    {
        return (long)(System.currentTimeMillis() / 1000L);
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
    
    public boolean checkStringContainChar(String InputData, char CheckInput)
    {
        return InputData.indexOf(CheckInput) >= 0;
    }
    
    public boolean checkStringContainString(String InputData, String CheckInput)
    {
        for(char i: CheckInput.toCharArray())
        {
            if(checkStringContainChar(InputData, i))
                return true;
        }
        
        return false;
    }
    
    public String parseHarga(int harga)
    {
        NumberFormat f = NumberFormat.getCurrencyInstance(Locale.US);
        
        StringBuilder sb = new StringBuilder();
        sb.append(f.format(harga));
        sb.deleteCharAt(sb.toString().length()-1);
        sb.deleteCharAt(sb.toString().length()-1);
        sb.deleteCharAt(sb.toString().length()-1);
        sb.reverse();
        sb.deleteCharAt(sb.toString().length()-1);
        sb.append(" pR");
        sb.reverse();
        sb.append(",-");
        
        return sb.toString();
    }
    
    public String parseHarga(String Harga)
    {
        return parseHarga(Integer.parseInt(Harga));
    }
}
