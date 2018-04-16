package com.wowotek.dk.auth;

import com.wowotek.dk.ErrorReporting;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Auth
{

    private MessageDigest crypt;
    private ErrorReporting er;

    public Auth(ErrorReporting er)
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
            elong.append(j);
            j++;
            if(j >= data.length())
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
        fin = data;
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
    
    public final String createBlock(String data1, String data2)
    {
        StringBuilder s = new StringBuilder();
        s.append(data1);
        s.append(hash(data2));
        s.append(hash(data1));
        s.append(data2);
        s.append(hash(data1));
        s.append(data2);
        s.append(data1);
        s.append(hash(data2));
        s.append(hash(s.toString()));
        
        String x = s.toString();
        for(int i=0; i<10; i++)
        {
            x = hash(x);
        }
        
        StringBuilder z = new StringBuilder();
        z.append(x);
        z.append(data1);
        z.append(data2);
        z.append(hash(x));
        z.append(hash(data1));
        z.append(hash(data2));
        z.append(hash(x));
        z.append(data2);
        z.append(data1);
        z.append(x);
        
        String fin = z.toString();
        for (int i = 0; i < 10; i++)
        {
            fin = hash(fin) + fin;
        }
        
        return hash(fin);
    }

}
