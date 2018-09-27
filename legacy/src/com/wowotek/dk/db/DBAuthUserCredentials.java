  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wowotek.dk.db;

import com.wowotek.dk.ErrorReporting;
import com.wowotek.dk.auth.authclasses.UserCredentials;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author wowotek
 */
public class DBAuthUserCredentials
{
    private final Connection c;
    private final ErrorReporting er;

    public DBAuthUserCredentials(Connection C, ErrorReporting ER)
    {
        this.er = ER;
        this.c = C;
    }

    public boolean tambahUserCredentials(UserCredentials ud)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "insert into UserCredentials(Username, Password, Email, RegID) "
                    + "Values (?, ?, ?, ?)");
            ps.setString(1, ud.Username);
            ps.setString(2, ud.getPasswordString());
            ps.setString(3, ud.Email);
            ps.setString(4, ud.RegID);
            
            if (ps.executeUpdate() > 0)
            {
                er.debug("Menambahkan UserCredentials Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Menambahkan UserCredentials Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Menambah UserCredentials,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }

    public boolean gantiUserCredentials(UserCredentials ud)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE UserCredentials SET Password=?, Email=? WHERE Username=?");
            ps.setString(1, ud.getPasswordString());
            ps.setString(2, ud.Email);
            ps.setString(3, ud.Username);

            if (ps.executeUpdate() > 0)
            {
                er.debug("Merubah data UserCredentials Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Merubah UserCredentials Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Merubah UserCredentials,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }

    public boolean hapusUserCredentials(String RegID)
    {
        try
        {
            PreparedStatement ps = c.prepareStatement(
                    "DELETE FROM UserCredentials WHERE RegID=?");
            ps.setString(1, RegID);
            
            if (ps.executeUpdate() > 0)
            {
                er.debug("Menghapus data UserCredentials Berhasil !", 0);
                ps.close();
                return true;
            }
            else
            {
                throw new SQLException("Menghapus UserCredentials Gagal !");
            }
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Menghapus UserCredentials,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
            return false;
        }
    }
    
    public UserCredentials getCredentials(String Username)
    {
        UserCredentials uc = null;
        PreparedStatement ps;
        ResultSet rs;
        
        try
        {
            er.debug("Mengambil data UserCredentials...");
            ps = c.prepareStatement("SELECT * FROM UserCredentials WHERE Username=?");
            ps.setString(1, Username);
            rs = ps.executeQuery();

            while (rs.next())
            {
                uc = new UserCredentials(
                rs.getString(1),
                rs.getString(2).toCharArray(),
                rs.getString(3),
                rs.getString(4)
                );
            }

            rs.close();
            ps.close();
            er.debug("Pengambilan data UserCredentials Berhasil !", 0);
            return uc;
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Mengambil data UserCredentials,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
        }
        return null;
    }
    
    public ArrayList<UserCredentials> ambilDataUserCredentials()
    {
        ArrayList<UserCredentials> allUserCredentials = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        try
        {
            er.debug("Mengambil data pemasukan...");
            ps = c.prepareStatement("SELECT * FROM pemasukan");
            rs = ps.executeQuery();

            while (rs.next())
            {
                allUserCredentials.add(
                        new UserCredentials(rs.getString(1),
            rs.getString(2).toCharArray(),
            rs.getString(3),
            rs.getString(4)));
            }

            rs.close();
            ps.close();
            er.debug("Pengambilan data UserCredentials Berhasil !", 0);
        }
        catch (SQLException ex)
        {
            er.debug("Terjadi Kesalahan saat Mengambil data UserCredentials,", 2);
            er.debugln("Karena: ");
            er.debugln("        " + ex.getMessage());
        }

        return allUserCredentials;
    }
}
