package com.wowotek.dk.auth;

import com.wowotek.dk.ErrorReporting;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hash
{

    private MessageDigest crypt;
    private ErrorReporting er;

    public Hash(ErrorReporting er)
    {
        this.er = er;

        try
        {
            this.crypt = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException ex)
        {
            er.debug("Cannot Create SHA-256 Instance", 2);
        }
    }

    public final String encode(String data)
    {
        StringBuilder elong = new StringBuilder();
        char[] bytedata = data.toCharArray();
        int j = 0;
        for (int i = 0; i < 512; i++)
        {
            elong.append(bytedata[j]);
            j++;
            if (j >= data.length())
            {
                j = 0;
            }
        }
        String encodedString = Base64.getEncoder().encodeToString(elong.toString().getBytes());
        return encodedString;
    }

    public final String hash(String data)
    {
        String fin;
        fin = encode(data);
        for (int j = 0; j < 500; j++)
        {
            byte[] result = crypt.digest(fin.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < result.length; i++)
            {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            fin = sb.toString();
        }
        return fin;
    }

    public final String hash(char[] data)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(data);
        return hash(encode(sb.toString()));
    }

    public final String hash_1(String data)
    {
        try
        {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            String fin;
            fin = encode(data);
            for (int j = 0; j < 500; j++)
            {
                byte[] result = sha1.digest(fin.getBytes());
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < result.length; i++)
                {
                    sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
                }
                fin = sb.toString();
            }
            return fin;
        }
        catch (NoSuchAlgorithmException ex)
        {
        }
        return null;
    }
    
    public final String hash_1(char[] data)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(data);
        return hash_1(sb.toString());
    }

    public final String createBlock(String data1, String data2, String data3)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data1.length(); i++)
        {
            sb.append(data1.toCharArray()[i]);
            try
            {
                sb.append(data2.toCharArray()[i]);
            }
            catch (Exception ex)
            {

            }

            try
            {
                sb.append(data3.toCharArray()[i]);
            }
            catch (Exception ex)
            {

            }
        }
        return hash(sb.toString());
    }
    
    public final String crpytPassword(char[] data1, String data2)
    {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(data1);
        
        
        StringBuilder sb = new StringBuilder();
        sb.append(encode(sb1.toString()));
        sb.append(hash(encode(data2)));
        sb.append(hash(encode(sb1.toString())));
        sb.append(encode(data2));
        sb.append(hash(sb.toString()));
        sb.append(encode(sb.toString()));
        
        String fin = encode(sb.toString());
        
        for (int i = 0; i < 100; i++)
        {
            fin = hash(encode(hash_1(fin)));
        }
        
        fin = hash_1(fin);
        for (int i = 0; i < 100; i++)
        {
            fin += hash(createBlock(fin, hash(fin+"Wowotek"), hash(encode("DataKontrakan"))));
        }
        
        return hash_1(hash(encode(fin)));
    }
    
    public boolean verify(String Original, String hashed)
    {
        return Original.equals(hashed);
    }
}
