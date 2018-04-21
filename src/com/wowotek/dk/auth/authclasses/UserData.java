package com.wowotek.dk.auth.authclasses;

public final class UserData
{
    public String Nama;
    public String LegalID;
    public String TTL;
    public String Phone1;
    public String Phone2;
    public String Work;
    public String WorkPlace;
    public String RegID;

    public UserData(String Nama, String LegalID, String TTL, String Phone1, String Phone2, String Work, String WorkPlace, String RegID)
    {
        this.Nama = Nama;
        this.LegalID = LegalID;
        this.TTL = TTL;
        this.Phone1 = Phone1;
        this.Phone2 = Phone2;
        this.Work = Work;
        this.WorkPlace = WorkPlace;
        this.RegID = RegID;
    }
}
