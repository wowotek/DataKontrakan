package com.wowotek.dk.db.dbclasses;

public class Pemasukan
{
    public final int ID;
    public final String TRANSAKSI;
    public final String SUMBER;
    public final int JUMLAH;

    public Pemasukan(int id, String transaksi, String sumber, int jumlah)
    {
        this.ID = id;
        this.TRANSAKSI = transaksi;
        this.SUMBER = sumber;
        this.JUMLAH = jumlah;
    }
}
