package com.wowotek.dk.db;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.auth.authclasses.UserData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBAuthUserData
{
    private final Connection c;
    private final ErrorReporting er;

    public DBAuthUserData(Connection C, ErrorReporting ER)
    {
        this.er = ER;
        this.c = C;
    }

    public boolean tambahUserData(UserData ud)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "insert into UserData(Nama, LegalID, TTL, Phone1, Phone2, Work, WorkPlace, RegID) "
                    + "Values (?, ?, ?)");
            ps.setString(1, ud.Nama);
            ps.setString(2, ud.LegalID);
            ps.setString(3, ud.TTL);
            ps.setString(4, ud.Phone1);
            ps.setString(5, ud.Phone2);
            ps.setString(6, ud.Work);
            ps.setString(7, ud.WorkPlace);
            ps.setString(8, ud.RegID);
            
            if (ps.executeUpdate() > 0)
            {
                er.debug("Menambahkan UserData Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Menambahkan UserData Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Menambah UserData,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }

    public boolean gantiUserData(UserData ud)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE UserData SET Nama=?, LegalID=?, TTL=?, Phone1=?, Phone2=?, Work=?, WorkPlace=? WHERE RegID=?");
            ps.setString(1, ud.Nama);
            ps.setString(2, ud.LegalID);
            ps.setString(3, ud.TTL);
            ps.setString(4, ud.Phone1);
            ps.setString(5, ud.Phone2);
            ps.setString(6, ud.Work);
            ps.setString(7, ud.WorkPlace);
            ps.setString(8, ud.RegID);

            if (ps.executeUpdate() > 0)
            {
                er.debug("Merubah data UserData Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Merubah UserData Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Merubah UserData,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }

    public boolean hapusUserData(String RegID)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "DELETE FROM UserData WHERE RegID=?");
            ps.setString(1, RegID);
            
            if (ps.executeUpdate() > 0)
            {
                er.debug("Menghapus data UserData Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Menghapus UserData Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Menghapus UserData,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }

    public UserData getUserData(String RegID)
    {
        UserData ud = null;
        PreparedStatement ps;
        ResultSet rs;

        try
        {
            er.debug("Mengambil data pemasukan...");
            ps = c.prepareStatement("SELECT * FROM UserData WHERE RegID=?");
            ps.setString(1, RegID);
            rs = ps.executeQuery();

            while (rs.next())
            {
                ud = new UserData(rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getString(6),
            rs.getString(7),
            rs.getString(8));
            }

            rs.close();
            ps.close();
            er.debug("Pengambilan data UserData Berhasil !", 0);
            return ud;
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Mengambil data UserData,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
        }
        
        return null;
    }
    
    public ArrayList<UserData> ambilDataUserData()
    {
        ArrayList<UserData> allUserData = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        try
        {
            er.debug("Mengambil data UserData...");
            ps = c.prepareStatement("SELECT * FROM UserData");
            rs = ps.executeQuery();

            while (rs.next())
            {
                allUserData.add(
                        new UserData(rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getString(6),
            rs.getString(7),
            rs.getString(8)));
            }

            rs.close();
            ps.close();
            er.debug("Pengambilan data UserData Berhasil !", 0);
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Mengambil data UserData,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
        }

        return allUserData;
    }
}
