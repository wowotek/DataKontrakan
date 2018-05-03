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
    public final DBConnection dbc;
    
    public final DBAuthUserCredentials dbauc;
    public final DBAuthUserData dbaud;
    
    public final DBPengeluaran dbpen;
    public final DBPemasukan dbpem;
    public final DBDaftarHarga dbdh;
    
    public final DBNamaApp dbna;
    
    public AllDatabase(DBConnection dbc, DBAuthUserCredentials dbauc, DBAuthUserData dbaud, DBPengeluaran dbpen, DBPemasukan dbpem, DBDaftarHarga dbdh, DBNamaApp dbna)
    {
        this.dbc = dbc;
        this.dbauc = dbauc;
        this.dbaud = dbaud;
        
        this.dbpen = dbpen;
        this.dbpem = dbpem;
        this.dbdh = dbdh;
        
        this.dbna = dbna;
    }
}
