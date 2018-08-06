package com.example.nibulateam.dogwalkerappliction.Model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User {

    private  String userID;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Date age;
    private boolean isDogWalker;
    private  boolean isDogOwner;
    //pic//
    //dogWalker//
    //dogOwner//


    public User(String UserID,String Email,String Password,String FirstName,String LastName,Date Age,boolean isDogOwner,boolean isDogWalker)
    {
        userID=UserID;
        email=Email;
        password=Password;
        firstName=FirstName;
        lastName=LastName;
        age=Age;
        this.isDogOwner=isDogOwner;
        this.isDogWalker=isDogWalker;
    }

    public String getUserId()
    {
        return userID;
    }



public Map<String,Object> toMap()
{
    HashMap<String, Object> result = new HashMap<>();

    result.put("User Id",userID);
    result.put("FirstName",firstName);
    result.put("LastName",lastName);
    result.put("Email",email);
    result.put("Age",age.toString());
    result.put("isDogOwner",isDogOwner);
    result.put("isDogWalker",isDogWalker);

    return result;

}




}
