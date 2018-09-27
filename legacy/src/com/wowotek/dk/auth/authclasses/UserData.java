package com.wowotek.dk.auth.authclasses;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.Util;
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
        sb.append(new Util().getUnixTimestamp());
        sb.append(x.encode(sb.toString()));
        sb.append(x.encode(this.Nama));
        sb.append(x.encode(this.LegalID));
        sb.append(x.encode(this.TTL));
        sb.append(x.encode(this.Phone1));
        sb.append(x.encode(this.Phone2));
        sb.append(x.encode(this.Work));
        sb.append(x.encode(this.WorkPlace));
        return x.hash_1(x.encode(x.hash_1(x.hash(x.encode(x.hash_1(x.hash(sb.toString())))))));
    }
}
