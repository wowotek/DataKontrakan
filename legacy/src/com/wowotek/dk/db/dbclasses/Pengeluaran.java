package com.wowotek.dk.db.dbclasses;

public final class Pengeluaran
{
    public final int ID;
    public final String TRANSAKSI;
    public final int JUMLAH;

    public Pengeluaran(int newID, String newTransaksi, int newJumlah)
    {
        this.ID = newID;
        this.TRANSAKSI = newTransaksi;
        this.JUMLAH = newJumlah;
    }
}
