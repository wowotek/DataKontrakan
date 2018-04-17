package com.wowotek.dk.db.dbclasses;

public class Pengeluaran
{

    public int ID;
    public String TRANSAKSI;
    public int JUMLAH;

    public Pengeluaran(int newID, String newTransaksi, int newJumlah)
    {
        this.ID = newID;
        this.TRANSAKSI = newTransaksi;
        this.JUMLAH = newJumlah;
    }

    public void setID(int newID)
    {
        this.ID = newID;
    }

    public void setTransaksi(String newTransaksi)
    {
        this.TRANSAKSI = newTransaksi;
    }

    public void setJumlah(int newJumlah)
    {
        this.JUMLAH = newJumlah;
    }
}
