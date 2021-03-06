package com.wowotek.dk;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ErrorReporting
{

    public int errID = 1;
    private BufferedWriter w;
    private boolean _LOGOPENED = false;
    private boolean verbose = false;
    public final String ERR_DEBUG_PREFIX = "[" + new Util().getDate() + "]";

    public ErrorReporting(boolean verbose)
    {
        this.verbose = verbose;
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
        if (this.verbose)
        {
            System.err.println("[" + strLevel + "] " + error);
        }
    }

    public void log(String error)
    {
        log(error, 5);
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
        //+ "[EID:" + this.errID + "]        " + error
        String finalER = "[" + new Util().getDate() + "]";
        for (int i = 0; i < Integer.toString(this.errID - 1).length() + 6; i++)
        {
            finalER += " ";
        }
        finalER += "        " + error;
        
        if(this.verbose)
            System.err.println(finalER);
        
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
                strLevel = "ERROR";
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
        String finalER = "[" + new Util().getDate() + "]" + "[EID:" + this.errID + "]" + "[" + strLevel + "] " + error;
        
        if(this.verbose)
            System.err.println(finalER);
        
        if (level != 0 || level != 4)
        {
            this.errID++;
        }
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

    public void debugln(String error)
    {
        int totalLength = ("[" + new Util().getDate() + "]").length() + Integer.toString(this.errID - 1).length() + 14;
        
        if(this.verbose)
        {
            for (int i = 0; i < totalLength; i++)
            {
                System.err.print(" ");
            }
            System.err.println(error);
        }
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
        debug("Closing Log File..");
        try
        {
            w.write("\n");
            w.write("================= Session Closed At "
                    + new Util().getDate(true)
                    + " =================\n\n\n");
            w.close();
            this._LOGOPENED = false;
            debug("Berhasil Menutup File Log", 0);
            return true;
        }
        catch (IOException ex)
        {
            return false;
        }
    }
}
