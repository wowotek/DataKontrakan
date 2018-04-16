package com.wowotek.dk.auth;

public class Block
{

    public int ID;
    public String Username;
    public String Password;
    public String Tanggal;
    public Block BlockBefore;

    private boolean checkTanggal(String Tanggal)
    {
        char[] ct = Tanggal.toCharArray();
        char[] num =
        {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, '/'
        };
        // dd/mm/yy
        // 01234567
        boolean ret = false;

        for (char i : num)
        {
            for (char j : ct)
            {
                if (i != j)
                {
                    ret = false;
                }
            }
        }
        ret = true;
        if (ct[2] != '/' || ct[2] != '/')
        {
            ret = false;
        }
        
        return ret;
    }

//    public Block(int ID, String Username, String Password, String Tanggal, Block blockbefore)
//    {
//        this.ID = ID;
//        this.Username = Username;
//        this.Password = Password;
//        this.Tanggal = Tanggal;
//        this.BlockBefore = blockbefore;
//    }
}
