package com.wowotek.dk.db;

import com.wowotek.dk.ErrorReporting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBPemasukan
{
    private final Connection c;
    private final ErrorReporting er;
    
    public DBPemasukan(Connection C, ErrorReporting ER)
    {
        this.er = ER;
        this.c = C;
    }
    
    public boolean tambahPemasukan(String transaksi, String sumber, int jumlah)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "insert into pemasukan(Transaksi, Sumber, Jumlah) "+ 
                    "Values (?, ?, ?)");
            ps.setString(1, transaksi);
            ps.setString(2, sumber);
            ps.setInt(3, jumlah);
            
            if(ps.executeUpdate() > 0)
            {
                er.debug("Menambahkan Pemasukan Berhasil !");
            }
            else
            {
                throw new SQLException("Menambahkan Pemasukan Gagal !");
            }
        }
        catch(SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Menambah Pemasukan,\n" +
                     "Karena: " + ex.getMessage() + "\n" + 
                     "Error ID: " + er.errID);
            
        }
        return false;
    }
}
