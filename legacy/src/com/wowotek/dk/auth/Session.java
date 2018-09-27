package com.wowotek.dk.auth;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.auth.authclasses.UserCredentials;
import com.wowotek.dk.auth.authclasses.UserData;
import com.wowotek.dk.db.AllDatabase;
import com.wowotek.dk.db.DBAuthUserCredentials;
import com.wowotek.dk.db.DBAuthUserData;
import com.wowotek.dk.db.DBNamaApp;
import com.wowotek.dk.db.dbclasses.NamaApp;

public class Session
{
    private final ErrorReporting er;
    private final DBAuthUserCredentials dbauc;
    private final DBAuthUserData dbaud;
    private final DBNamaApp dbna;
    
    
    private UserCredentials input_uc;
    private UserCredentials online_uc;
    private UserData online_ud;
    
    public String namaApp;
    
    private String LogedInAs;
    private boolean pass;
    
    public Session(AllDatabase ad, ErrorReporting er)
    {
        this.er = er;
        this.dbauc = ad.dbauc;
        this.dbaud = ad.dbaud;
        this.dbna = ad.dbna;
    }
    
    public boolean newSession(UserCredentials iuc)
    {
        boolean credCheck = checkCredential(iuc);
        this.namaApp = getNamaPerusahaan(this.online_uc);
        
        return (credCheck && (this.namaApp != null));
    }
    
    private String getNamaPerusahaan(UserCredentials iuc)
    {
        for(NamaApp i: this.dbna.ambilDataNamaApp())
        {
            if(iuc.RegID == i.REGID)
            {
                return i.NAMA;
            }
        }
        return null;
    }
    
    private boolean checkCredential(UserCredentials iuc)
    {
        this.input_uc = iuc;
        this.online_uc = dbauc.getCredentials(input_uc.Username);
        if(this.online_uc == null)
        {
            er.debug("Username Not Found !", 2);
            er.debug("Session Not Confirmed, Returning to : False", 2);
            return false;
        }
        else
        {
            er.debug("Username Found, Checking UserData", 0);
            this.online_ud = dbaud.getUserData(this.online_uc.RegID);
            
            if(this.online_ud == null)
            {
                er.debug("UserData not Found !", 2);
                er.debug("Session Not Confirmed, Returning to : False", 2);
                return false;
            }
            else
            {
                er.debug("UserData Found, Checking Password", 0);
                String OnlinePassword = this.online_uc.getPasswordString();
                String InputPassword = new Hash(er).crpytPassword(this.input_uc.Password, this.input_uc.Username);
                er.debug("Received Password : " + OnlinePassword, 10);
                er.debug("Inputted Password : " + InputPassword, 10);
                if(OnlinePassword.equals(InputPassword))
                {
                    this.LogedInAs = this.online_ud.Nama;
                    er.debug("Session Confirmed, Returning to : True", 10);
                    return true;
                }
                else
                {
                    er.debug("Wrong Password !", 2);
                    return false;
                }
            }
        }
    }
}
