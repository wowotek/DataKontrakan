package com.wowotek.dk.db;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.classinstance.Pemasukan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBPemasukan
{

  private final Connection c;
  private final ErrorReporting er;

  public DBPemasukan(Connection C, ErrorReporting ER)
  {
    this.er = ER;
    this.c = C;
  }

  public boolean tambahPemasukan(Pemasukan pms)
  {
    try
    {
      PreparedStatement ps = c.prepareStatement(
              "insert into Pemasukan(Transaksi, Sumber, Jumlah) "
              + "Values (?, ?, ?)");
      ps.setString(1, pms.TRANSAKSI);
      ps.setString(2, pms.SUMBER);
      ps.setInt(3, pms.JUMLAH);

      if (ps.executeUpdate() > 0)
      {
        er.debug("Menambahkan Pemasukan Berhasil !", 0);
        ps.close();
        return true;
      }
      else
      {
        throw new SQLException("Menambahkan Pemasukan Gagal !");
      }
    }
    catch (SQLException ex)
    {
      er.debug("Terjadi Kesalahan saat Menambah Pemasukan,", 2);
      er.debugln("Karena: ");
      er.debugln("        " + ex.getMessage());
      return false;
    }
  }

  public boolean gantiPemasukan(Pemasukan pms)
  {
    try
    {
      PreparedStatement ps = c.prepareStatement(
              "UPDATE Pemasukan SET Transaksi=?, Sumber=?, Jumlah=? WHERE ID=?");
      ps.setString(1, pms.TRANSAKSI);
      ps.setString(2, pms.SUMBER);
      ps.setInt(3, pms.JUMLAH);
      ps.setInt(4, pms.ID);

      if (ps.executeUpdate() > 0)
      {
        er.debug("Merubah data Pemasukan Berhasil !", 0);
        ps.close();
        return true;
      }
      else
      {
        throw new SQLException("Merubah Pemasukan Gagal !");
      }
    }
    catch (SQLException ex)
    {
      er.debug("Terjadi Kesalahan saat Merubah Pemasukan,", 2);
      er.debugln("Karena: ");
      er.debugln("        " + ex.getMessage());
      return false;
    }
  }

  public boolean hapusPemasukan(int ID)
  {
    try
    {
      PreparedStatement ps = c.prepareStatement(
              "DELETE FROM Pemasukan WHERE ID=?");
      ps.setInt(1, ID);
      if (ps.executeUpdate() > 0)
      {
        er.debug("Menghapus data Pemasukan Berhasil !", 0);
        ps.close();
        return true;
      }
      else
      {
        throw new SQLException("Menghapus Pemasukan Gagal !");
      }
    }
    catch (SQLException ex)
    {
      er.debug("Terjadi Kesalahan saat Menghapus Pemasukan,", 2);
      er.debugln("Karena: ");
      er.debugln("        " + ex.getMessage());
      return false;
    }
  }

  public ArrayList<Pemasukan> ambilDataPemasukan()
  {
    ArrayList<Pemasukan> allPemasukan = new ArrayList<>();
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

        allPemasukan.add(
                new Pemasukan(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)));
      }

      rs.close();
      ps.close();
      er.debug("Pengambilan data Pemasukan Berhasil !", 0);
    }
    catch (SQLException ex)
    {
      er.debug("Terjadi Kesalahan saat Mengambil data Pemasukan,", 2);
      er.debugln("Karena: ");
      er.debugln("        " + ex.getMessage());
    }

    return allPemasukan;
  }
}
