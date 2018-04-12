package com.wowotek.dk;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ErrorReporting
{
    public int errID = 0;
    private BufferedWriter w;
    private boolean _LOGOPENED = false;

    public ErrorReporting()
    {
        _LOGOPENED = openFile();
        if (_LOGOPENED == true)
        {
            try
            {
                w.write("================= Session Started At "
                        + new Util().getDate(true)
                        + " ================\n\n");
            }
            catch (IOException ex)
            {
            }
            debug("Successfuly Opened Log File", 0);
        }
    }

    public void log(String error)
    {
        System.err.println("[CLOGS] " + error);
    }

    public void log(String error, int level)
    {
        String strLevel;
        switch (level)
        {
            case 0:
                strLevel = "CPASS";
                break;
            case 1:
                strLevel = "WARNG";
                break;
            case 2:
                strLevel = "CAUTN";
                break;
            case 3:
                strLevel = "SEVER";
                break;
            case 4:
                strLevel = "LETHL";
                break;
            default:
                strLevel = "CLOGS";
                break;
        }
        System.err.println("[" + strLevel + "] " + error);
    }

    public void log(String error, String logFile)
    {
        String finalString = error + ">>" + logFile;
        log(finalString);
    }

    public void log(String error, int level, String logFile)
    {
        String finalString = error + ">>" + logFile;
        log(finalString, level);
    }

    public void debug(String error)
    {
        String finalER =  "[" + new Util().getDate() + "] [" + this.errID + "] "+ error;
        System.err.println(finalER);
        this.errID++;
        if (_LOGOPENED == true)
        {
            try
            {
                w.append(finalER + "\n");
            }
            catch (IOException ex)
            {
            }
        }
    }

    public void debug(String error, int level)
    {
        String strLevel;
        switch (level)
        {
            case 0:
                strLevel = "CPASS";
                break;
            case 1:
                strLevel = "WARNG";
                break;
            case 2:
                strLevel = "CAUTN";
                break;
            case 3:
                strLevel = "SEVER";
                break;
            case 4:
                strLevel = "LETHL";
                break;
            default:
                strLevel = "CLOGS";
                break;
        }
        String finalER = "[" + new Util().getDate() + "] [" + strLevel + "] [" + this.errID + "] "+ error;
        System.err.println(finalER);
        this.errID++;
        if (_LOGOPENED == true)
        {
            try
            {
                w.append(finalER + "\n");
            }
            catch (IOException ex)
            {
            }
        }
    }

    public void debug(String error, String logFile)
    {
        String finalString = error + ">>" + logFile;
        debug(finalString);

    }

    public void debug(String error, int level, String logFile)
    {
        String finalString = error + ">>" + logFile;
        debug(finalString, level);
    }

    public boolean openFile()
    {
        try
        {
            debug("Opening Log File...");
            this.w = new BufferedWriter(new FileWriter("log", true));
            return true;
        }
        catch (IOException ex)
        {
            debug("Cannot Open Log File", 2);
            return false;
        }
    }

    public boolean closeFile()
    {
        debug("Closing Log File");
        try
        {
            w.write("\n");
            w.write("================= Session Closed At "
                        + new Util().getDate(true)
                        + " =================\n\n\n");
            w.close();
            return true;
        }
        catch (IOException ex)
        {
            return false;
        }
    }
}
