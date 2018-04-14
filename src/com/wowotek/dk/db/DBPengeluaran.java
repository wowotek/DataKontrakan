package com.wowotek.dk.db;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.classinstance.Pengeluaran;
import com.wowotek.dk.classinstance.Pengeluaran;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBPengeluaran
{

    private final Connection c;
    private final ErrorReporting er;

    public DBPengeluaran(Connection newConnection, ErrorReporting newErrorReporting)
    {
        this.c = newConnection;
        this.er = newErrorReporting;
    }

    public boolean tambahPengeluaran(Pengeluaran png)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "insert into Pengeluaran(Transaksi, Jumlah) "
                    + "Values (?, ?)");
            ps.setString(1, png.TRANSAKSI);
            ps.setInt(2, png.JUMLAH);

            if (ps.executeUpdate() > 0)
            {
                er.debug("Menambahkan Pengeluaran Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Menambahkan Pengeluaran Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Menambah Pengeluaran,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }

    public boolean gantiPengeluaran(Pengeluaran png)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE Pengeluaran SET Transaksi=?, Jumlah=? WHERE ID=?");
            ps.setString(1, png.TRANSAKSI);
            ps.setInt(2, png.JUMLAH);
            ps.setInt(3, png.ID);

            if (ps.executeUpdate() > 0)
            {
                er.debug("Merubah data Pengeluaran Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Merubah Pengeluaran Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Merubah Pengeluaran,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }

    public boolean hapusPengeluaran(int ID)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "DELETE FROM Pengeluaran WHERE ID=?");
            ps.setInt(1, ID);
            if (ps.executeUpdate() > 0)
            {
                er.debug("Menghapus data Pengeluaran Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Menghapus Pengeluaran Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Menghapus Pengeluaran,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }

    public ArrayList<Pengeluaran> ambilDataPengeluaran()
    {
        ArrayList<Pengeluaran> allPengeluaran = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        try
        {
            er.debug("Mengambil data pemasukan...");
            ps = c.prepareStatement("SELECT * FROM pemasukan");
            rs = ps.executeQuery();

            while (rs.next())
            {
                String NIM = rs.getString(1);
                String Nama = rs.getString(2);

                allPengeluaran.add(
                        new Pengeluaran(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4)));
            }

            rs.close();
            ps.close();
            er.debug("Pengambilan data Pengeluaran Berhasil !", 0);
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Mengambil data Pengeluaran,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
        }

        return allPengeluaran;
    }
}
