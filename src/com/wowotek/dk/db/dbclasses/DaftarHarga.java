package com.wowotek.dk.db.dbclasses;

public final class DaftarHarga
{
    public final int IDHARGA;
    public final String NAMA;
    public final int HARGA;
    public final String PRIORITAS;
    
    public DaftarHarga(int id, String nama, int harga, String p)
    {
        this.IDHARGA = id;
        this.NAMA = nama;
        this.HARGA = harga;
        this.PRIORITAS = p;
    }
}