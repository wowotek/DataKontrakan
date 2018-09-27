package com.wowotek.dk.db;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.db.dbclasses.DaftarHarga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class DBDaftarHarga
{
    private final Connection c;
    private final ErrorReporting er;

    public DBDaftarHarga(Connection C, ErrorReporting ER)
    {
        this.er = ER;
        this.c = C;
    }

    public boolean tambahDaftarHarga(DaftarHarga dh)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "insert into DaftarHarga(Nama, Harga, Prioritas) "
                    + "Values (?, ?, ?)");
            ps.setString(1, dh.NAMA);
            ps.setInt(2, dh.HARGA);
            ps.setString(3, dh.PRIORITAS);

            if (ps.executeUpdate() > 0)
            {
                er.debug("Menambahkan DaftarHarga Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Menambahkan DaftarHarga Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Menambah DaftarHarga,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }

    public boolean gantiDaftarHarga(DaftarHarga dh)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE DaftarHarga SET Nama=?, Harga=?, Prioritas=? WHERE ID=?");
            ps.setString(1, dh.NAMA);
            ps.setInt(2, dh.HARGA);
            ps.setString(3, dh.PRIORITAS);
            ps.setInt(4, dh.IDHARGA);

            if (ps.executeUpdate() > 0)
            {
                er.debug("Merubah data DaftarHarga Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Merubah DaftarHarga Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Merubah DaftarHarga,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }

    public boolean hapusDaftarHarga(int ID)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "DELETE FROM DaftarHarga WHERE ID=?");
            ps.setInt(1, ID);
            if (ps.executeUpdate() > 0)
            {
                er.debug("Menghapus data DaftarHarga Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Menghapus DaftarHarga Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Menghapus DaftarHarga,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }

    public ArrayList<DaftarHarga> ambilDataDaftarHarga()
    {
        ArrayList<DaftarHarga> allDaftarHarga = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        try
        {
            er.debug("Mengambil data pemasukan...");
            ps = c.prepareStatement("SELECT * FROM pemasukan");
            rs = ps.executeQuery();

            while (rs.next())
            {
                allDaftarHarga.add(
                        new DaftarHarga(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getInt(3),
                                rs.getString(4)));
            }

            rs.close();
            ps.close();
            er.debug("Pengambilan data DaftarHarga Berhasil !", 0);
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Mengambil data DaftarHarga,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
        }

        return allDaftarHarga;
    }
}
