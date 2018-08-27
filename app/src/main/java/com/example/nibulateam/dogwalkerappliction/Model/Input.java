package com.example.nibulateam.dogwalkerappliction.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Patterns;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Input {

private static final int pass_lan=6;

    public static boolean isValidEmail(String Email)
    {

        if(Email.equals(null) || Email.isEmpty())
        {
            return false;
        }
        Pattern pattern=Patterns.EMAIL_ADDRESS;
        return pattern.matcher(Email).matches();
    }


    public static boolean isValidPhone(final String Phone)
    {
        Pattern pattern= Patterns.PHONE;
        return pattern.matcher(Phone).matches();
    }

    public static  boolean isValidName(final String Name)
    {

        if(Name.isEmpty() ||Name.equals(null) )
        {
            return false;
        }
        return true;
    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
    if(password.length()<pass_lan)
    {
        return false;
    }
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    public static Bitmap downloadImage(String stringUrl) {
        URL url;
        Bitmap bm = null;
        try {
            url = new URL(stringUrl);
            URLConnection ucon = url.openConnection();
            InputStream is;
            if (ucon instanceof HttpURLConnection) {
                HttpURLConnection httpConn = (HttpURLConnection) ucon;
                int statusCode = httpConn.getResponseCode();
                if (statusCode == 200) {
                    is = httpConn.getInputStream();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 8;
                    BufferedInputStream bis = new BufferedInputStream(is, 8192);
                     ByteBuffer baf=ByteBuffer.allocate(1024);
                    int current = 0;
                    while ((current = bis.read()) != -1) {
                        baf.put((byte) current);
                       // baf.append((byte) current);
                    }
                    byte[] rawImage= baf.array();
                   // byte[] rawImage = baf.toByteArray();
                    bm = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length);
                    bis.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

}
