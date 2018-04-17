package com.wowotek.dk.db.dbclasses;

public class Pemasukan
{

    public int ID;
    public String TRANSAKSI;
    public String SUMBER;
    public int JUMLAH;

    public Pemasukan(int id, String transaksi, String sumber, int jumlah)
    {
        this.ID = id;
        this.TRANSAKSI = transaksi;
        this.SUMBER = sumber;
        this.JUMLAH = jumlah;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public void setTransaksi(String TRANSAKSI)
    {
        this.TRANSAKSI = TRANSAKSI;
    }

    public void setSumber(String SUMBER)
    {
        this.SUMBER = SUMBER;
    }

    public void setJumlah(int JUMLAH)
    {
        this.JUMLAH = JUMLAH;
    }
}
