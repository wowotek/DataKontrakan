package com.wowotek.dk.auth;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.auth.authclasses.UserCredentials;
import com.wowotek.dk.auth.authclasses.UserData;
import com.wowotek.dk.db.DBAuthUserCredentials;
import com.wowotek.dk.db.DBAuthUserData;

public class Session
{
    private final ErrorReporting er;
    private final DBAuthUserCredentials db;
    private final DBAuthUserData dba;
    
    private UserCredentials input_uc;
    private UserCredentials online_uc;
    private UserData online_ud;
    
    private String LogedInAs;
    private boolean pass;
    
    public Session(DBAuthUserCredentials db, DBAuthUserData dba, ErrorReporting er)
    {
        this.er = er;
        this.db = db;
        this.dba = dba;
    }
    
    public boolean newSession(UserCredentials iuc)
    {
        this.input_uc = iuc;
        this.online_uc = db.getCredentials(input_uc.Username);
        if(this.online_uc == null)
        {
            er.debug("Username Not Found !", 2);
            this.pass = false;
            return false;
        }
        else
        {
            er.debug("Username Found, Checking UserData", 10);
            this.online_ud = dba.getUserData(this.online_uc.RegID);
            
            if(this.online_ud == null)
            {
                er.debug("UserData not Found !", 2);
                this.pass = false;
                return false;
            }
            else
            {
                er.debug("UserData Found, Checking Password");
                String OnlinePassword = this.online_uc.Password;
                String InputPassword = this.input_uc.Password;
                
                if(OnlinePassword.equals(InputPassword))
                {
                    this.LogedInAs = this.online_ud.Nama;
                    this.pass = true;
                    return true;
                }
            }
        }
        
        return false;
    }
}
