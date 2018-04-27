package com.wowotek.dk.auth.authclasses;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.auth.Hash;

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
    
    private ErrorReporting er;
    
    public UserData(String Nama, String LegalID, String TTL, String Phone1, String Phone2, String Work, String WorkPlace, ErrorReporting er)
    {
        this.Nama = Nama;
        this.LegalID = LegalID;
        this.TTL = TTL;
        this.Phone1 = Phone1;
        this.Phone2 = Phone2;
        this.Work = Work;
        this.WorkPlace = WorkPlace;
        this.er = er;
        this.RegID = createRegID();
    }
    
    public UserData(String Nama, String LegalID, String TTL, String Phone1, String Phone2, String Work, String WorkPlace, String RegID, ErrorReporting er)
    {
        this.Nama = Nama;
        this.LegalID = LegalID;
        this.TTL = TTL;
        this.Phone1 = Phone1;
        this.Phone2 = Phone2;
        this.Work = Work;
        this.WorkPlace = WorkPlace;
        this.RegID = RegID;
        this.er = er;
    }
    
    private String createRegID()
    {
        Hash x = new Hash(er);
        StringBuilder sb = new StringBuilder();
        sb.append(this.Nama);
        sb.append(this.LegalID);
        sb.append(this.TTL);
        sb.append(this.Phone1);
        sb.append(this.Phone2);
        sb.append(this.Work);
        sb.append(this.WorkPlace);
        return x.hash_1(x.hash(x.encode(x.hash_1(x.hash(sb.toString())))));
    }
}
