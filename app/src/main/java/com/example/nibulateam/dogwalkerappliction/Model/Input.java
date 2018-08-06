package com.example.nibulateam.dogwalkerappliction.Model;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Input {



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

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

}
