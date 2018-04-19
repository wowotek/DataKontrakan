package com.wowotek.dk;

import com.wowotek.dk.db.*;
import com.wowotek.dk.db.dbclasses.*;

public class Main
{

    private static final ErrorReporting er = new ErrorReporting();
    private static final DBConnection dbc = new DBConnection(er);

    private static final DBDaftarHarga dbdh = new DBDaftarHarga(dbc.c, er);
    private static final DBPemasukan dbpem = new DBPemasukan(dbc.c, er);
    private static final DBPengeluaran dbpen = new DBPengeluaran(dbc.c, er);

    public static void main(String[] args)
    {
        testConnection();
    }
    
    private static boolean testConnection()
    {
        boolean dbdhStatus, dbpemStatus, dbpenStatus;
        Util u = new Util();
        
        er.debug("Testing Connection...", 9);
        er.debug("Testing Daftar-Harga Connection", 9);
        u.delay(0.5);
        dbdhStatus = dbdh.tambahDaftarHarga(new DaftarHarga(1, "Galon2", 15000, "Rendah"));
        u.delay(0.5);
        
        er.debug("Testing Pemasukan Connection", 9);
        u.delay(0.5);
        dbpemStatus = dbpem.tambahPemasukan(new Pemasukan(1, "Dapet Uang Dari Ibu kontrakan", "Ibu Kontrakan", 20000));
        u.delay(0.5);
        
        er.debug("Testing Pengeluaran Connection", 9);
        u.delay(0.5);
        dbpenStatus = dbpen.tambahPengeluaran(new Pengeluaran(1, "Bayar Internet", 200000));
        u.delay(0.5);
        
        if (dbdhStatus && dbpemStatus && dbpenStatus)
        {
            er.debug("Connection Successful", 0);
            return true;
        }
        else
        {
            er.debug("Connection Failed", 3);
            return false;
        }
    }
}
