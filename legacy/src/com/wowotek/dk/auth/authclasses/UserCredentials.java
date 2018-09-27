package com.wowotek.dk.auth.authclasses;

public class UserCredentials
{
    public String Username;
    public char[] Password;
    public String Email;
    public String RegID;

    public UserCredentials(String Username, char[] Password, String Email, String RegID)
    {
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
        this.RegID = RegID;
    }
    
    public UserCredentials(String Username, char[] Password)
    {
        this.Username = Username;
        this.Password = Password;
    }
    
    public String getPasswordString()
    {
        StringBuilder n = new StringBuilder();
        n.append(this.Password);
        
        return n.toString();
    }
}
