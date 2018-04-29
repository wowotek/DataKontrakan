/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wowotek.dk.db;

/**
 *
 * @author wowotek
 */
public class AllDatabase
{
    public final DBPengeluaran dbpen;
    public final DBPemasukan dbpem;
    public final DBDaftarHarga dbdh;
    
    public AllDatabase(DBPengeluaran dbpen, DBPemasukan dbpem, DBDaftarHarga dbdh)
    {
        this.dbpen = dbpen;
        this.dbpem = dbpem;
        this.dbdh = dbdh;
    }
}
