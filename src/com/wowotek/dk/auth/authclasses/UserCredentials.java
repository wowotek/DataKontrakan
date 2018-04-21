package com.wowotek.dk.auth.authclasses;

public class UserCredentials
{
    public String Username;
    public String Password;
    public String Email;
    public String RegID;

    public UserCredentials(String Username, String Password, String Email, String RegID)
    {
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
        this.RegID = RegID;
    }
    
    public UserCredentials(String Username, String Password)
    {
        this.Username = Username;
        this.Password = Password;
    }
}
