/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wowotek.dk.db;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.db.dbclasses.NamaApp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author wowotek
 */
public class DBNamaApp
{
    private final Connection c;
    private final ErrorReporting er;

    public DBNamaApp(Connection C, ErrorReporting ER)
    {
        this.er = ER;
        this.c = C;
    }

    public boolean tambahNamaApp(NamaApp na)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "insert into NamaApp(RegID, Nama) "
                    + "Values (?, ?)");
            ps.setString(1, na.REGID);
            ps.setString(2, na.NAMA);
            
            if (ps.executeUpdate() > 0)
            {
                er.debug("Menambahkan NamaApp Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Menambahkan NamaApp Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Menambah NamaApp,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }

    public boolean gantiNamaApp(NamaApp na)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE NamaApp SET Nama=? WHERE RegID=?");
            ps.setString(1, na.NAMA);
            ps.setString(2, na.REGID);

            if (ps.executeUpdate() > 0)
            {
                er.debug("Merubah data NamaApp Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Merubah NamaApp Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Merubah NamaApp,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }

    public boolean hapusNamaApp(String RegID)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "DELETE FROM NamaApp WHERE RegID=?");
            ps.setString(1, RegID);
            
            if (ps.executeUpdate() > 0)
            {
                er.debug("Menghapus data NamaApp Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Menghapus NamaApp Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Menghapus NamaApp,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }

    public ArrayList<NamaApp> ambilDataNamaApp()
    {
        ArrayList<NamaApp> allNamaApp = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        try
        {
            er.debug("Mengambil data pemasukan...");
            ps = c.prepareStatement("SELECT * FROM NamaApp");
            rs = ps.executeQuery();

            while (rs.next())
            {
                allNamaApp.add(
                        new NamaApp(
                                rs.getString(1),
                                rs.getString(2)));
            }

            rs.close();
            ps.close();
            er.debug("Pengambilan data NamaApp Berhasil !", 0);
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Mengambil data NamaApp,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
        }

        return allNamaApp;
    }
}
