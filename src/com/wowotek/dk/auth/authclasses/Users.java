package com.wowotek.dk.auth.authclasses;

import com.wowotek.dk.exception.ClassVariableCannotBeEmpty;
import com.wowotek.dk.exception.VariableAlreadySetException;
import java.util.ArrayList;

public final class Users
{

    private String NamaLengkap = null;
    private String CardID = null;
    private String TTL = null;
    private String Phone1 = null;
    private String Phone2 = null;
    private String Work = null;
    private String WorkPlace = null;

    public Users(String nl, String cid, String ttl, String p1, String p2, String w, String w2) throws VariableAlreadySetException, ClassVariableCannotBeEmpty
    {
        this.setNamaLengkap(nl);
        this.setCardID(cid);
        this.setTTL(ttl);
        this.setPhone1(p1);
        this.setPhone2(p2);
        this.setWork(w);
        this.setWorkPlace(w2);
    }

    public Users(String nl, String cid, String ttl, String p1, String w, String w2) throws VariableAlreadySetException, ClassVariableCannotBeEmpty
    {
        this.setNamaLengkap(nl);
        this.setCardID(cid);
        this.setTTL(ttl);
        this.setPhone1(p1);
        this.setWork(w);
        this.setWorkPlace(w2);
    }

    public Users()
    {
    }

    private boolean checkVariable()
    {
        ArrayList<String> a = new ArrayList<>();
        a.add(this.NamaLengkap);
        a.add(this.CardID);
        a.add(this.TTL);
        a.add(this.Phone1);
        a.add(this.Phone2);
        a.add(this.Work);
        a.add(this.WorkPlace);

        for (String i : a)
        {
            if (i == null || i == "")
            {
                return false;
            }
        }
        return true;
    }

    public void setNamaLengkap(String NamaLengkap) throws VariableAlreadySetException, ClassVariableCannotBeEmpty
    {
        if ("".equals(this.NamaLengkap))
        {
            throw new ClassVariableCannotBeEmpty("Nama Lengkap Tidak Bisa Kosong");
        }
        else if (this.NamaLengkap != null)
        {
            throw new VariableAlreadySetException("Nama Lengkap sudah di Set, tidak dapat di mutasi Kembali");
        }
        else
        {
            this.NamaLengkap = NamaLengkap;
        }
    }

    public void setCardID(String CardID) throws VariableAlreadySetException, ClassVariableCannotBeEmpty
    {
        if ("".equals(this.CardID))
        {
            throw new ClassVariableCannotBeEmpty("No Kartu Tanda Pengenal Tidak Bisa Kosong");
        }
        else if (this.CardID == null)
        {
            this.CardID = CardID;
        }
        else
        {
            throw new VariableAlreadySetException("No Kartu Tanda Pengenal sudah di Set, tidak dapat di mutasi Kembali");
        }
    }

    public void setTTL(String TTL) throws VariableAlreadySetException, ClassVariableCannotBeEmpty
    {
        if ("".equals(this.TTL))
        {
            throw new ClassVariableCannotBeEmpty("Tempat dan Tanggal Lahir Tidak Bisa Kosong");
        }
        else if (this.TTL == null)
        {
            this.TTL = TTL;
        }
        else
        {
            throw new VariableAlreadySetException("Tempat dan Tanggal Lahir sudah di Set, tidak dapat di mutasi Kembali");
        }

    }

    public void setPhone1(String Phone1) throws VariableAlreadySetException, ClassVariableCannotBeEmpty
    {
        if ("".equals(this.Phone1))
        {
            throw new ClassVariableCannotBeEmpty("No Handphone Tidak Bisa Kosong");
        }
        else if (this.Phone1 == null)
        {
            this.Phone1 = Phone1;
        }
        else
        {
            throw new VariableAlreadySetException("No Handphone sudah di Set, tidak dapat di mutasi Kembali");
        }

    }

    public void setPhone2(String Phone2) throws VariableAlreadySetException
    {
        if (this.Phone2 == null)
        {
            this.Phone2 = Phone2;
        }
        else
        {
            throw new VariableAlreadySetException("No Handphone 2 sudah di Set, tidak dapat di mutasi Kembali");
        }

    }

    public void setWork(String Work) throws VariableAlreadySetException, ClassVariableCannotBeEmpty
    {
        if ("".equals(this.Work))
        {
            throw new ClassVariableCannotBeEmpty("Pekerjaan Tidak Bisa Kosong");
        }
        else if (this.Work == null)
        {
            this.Work = Work;
        }
        else
        {
            throw new VariableAlreadySetException("Pekerjaan sudah di Set, tidak dapat di mutasi Kembali");
        }

    }

    public void setWorkPlace(String WorkPlace) throws VariableAlreadySetException, ClassVariableCannotBeEmpty
    {
        if ("".equals(this.WorkPlace))
        {
            throw new ClassVariableCannotBeEmpty("Tempat Kerja Tidak Bisa Kosong");
        }
        else if (this.WorkPlace == null)
        {
            this.WorkPlace = WorkPlace;
        }
        else
        {
            throw new VariableAlreadySetException("Tempat Kerja sudah di Set, tidak dapat di mutasi Kembali");
        }

    }

    public String getNamaLengkap() throws ClassVariableCannotBeEmpty
    {
        if (checkVariable() == false)
        {
            return NamaLengkap;
        }
        else
        {
            throw new ClassVariableCannotBeEmpty("Nama Lengkap Tidak Bisa Kosong atau belum di Set");
        }
    }

    public String getCardID() throws ClassVariableCannotBeEmpty
    {
        if (checkVariable() == false)
        {
            return CardID;
        }
        else
        {
            throw new ClassVariableCannotBeEmpty("No Kartu Tanda Pengenal Tidak Bisa Kosong atau belum di Set");
        }
    }

    public String getTTL() throws ClassVariableCannotBeEmpty
    {
        if (checkVariable() == false)
        {
            return TTL;
        }
        else
        {
            throw new ClassVariableCannotBeEmpty("Tempat dan Tanggal Lahir Tidak Bisa Kosong atau belum di Set");
        }
    }

    public String getPhone1() throws ClassVariableCannotBeEmpty
    {
        if (checkVariable() == false)
        {
            return Phone1;
        }
        else
        {
            throw new ClassVariableCannotBeEmpty("No Handphone Tidak Bisa Kosong atau belum di Set");
        }
    }

    public String getPhone2() throws ClassVariableCannotBeEmpty
    {
        if (checkVariable() == false)
        {
            return Phone2;
        }
        else
        {
            throw new ClassVariableCannotBeEmpty("No Handphone 2 Tidak Bisa Kosong atau belum di Set");
        }
    }

    public String getWork() throws ClassVariableCannotBeEmpty
    {
        if (checkVariable() == false)
        {
            return Work;
        }
        else
        {
            throw new ClassVariableCannotBeEmpty("Pekerjaan Tidak Bisa Kosong atau belum di Set");
        }
    }

    public String getWorkPlace() throws ClassVariableCannotBeEmpty
    {
        if (checkVariable() == false)
        {
            return WorkPlace;
        }
        else
        {
            throw new ClassVariableCannotBeEmpty("Tempat Kerja Tidak Bisa Kosong atau belum di Set");
        }
    }

}
