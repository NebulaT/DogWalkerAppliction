package com.example.nibulateam.dogwalkerappliction.Model;

import android.net.Uri;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {

    private  String userID;
    private String userName;
    private String password;
    private String email;
    private String age;
    private String gender;
    private boolean isDogWalker;
    private  boolean isDogOwner;
    private String phoneNumber;

    public DogWalker dogWalker;
    public DogOwner dogOwner;

    //pic//
    private Uri userImage=null;
    private String userUrlImage=null;

    public User(String UserID, String Name, String Email, String Password, String Phone)
    {
        userID=UserID;
        email=Email;
        password=Password;
        userName=Name;
        phoneNumber=Phone;

    }
    //defult co//
    public User()
    {

    }
    public User(String UserID,String Email,String Password,String FirstName,String Age,boolean isDogOwner,boolean isDogWalker)
    {
        userID=UserID;
        email=Email;
        password=Password;
        userName=FirstName;
        age=Age;
        this.isDogOwner=isDogOwner;
        this.isDogWalker=isDogWalker;
    }

    public void setUserImage(Uri userImage) {
        this.userImage = userImage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getUserImage() {
        return userUrlImage;
    }

    public String getUserUrlImage() {
        return userUrlImage;
    }

    public void setUserUrlImage(String userUrlImage) {
        this.userUrlImage = userUrlImage;
    }

    public String getFirstName() {
        return userName;
    }

    public void setFirstName(String firstName) {
        this.userName = firstName;
    }


    public String getPassword() {
        return password;
    }


    public String getEmail() {
        return email;
    }

    public  void setEmail(String Email)
    {
    if(!Email.equals(null))
    {
        this.email=Email;
    }
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isDogWalker() {
        return isDogWalker;
    }

    public void setDogWalker() {
        isDogWalker = true;

        if (isDogWalker== true) {
            this.dogWalker = new DogWalker();

        }

    }


    public boolean isDogOwner() {
        return isDogOwner;
    }

    public void setDogOwner(boolean dogOwner) {
        isDogOwner = dogOwner;

        if (isDogOwner== true)
        {
            this.dogOwner=new DogOwner();

        }

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }





    public String getUserId()
    {
        return userID;
    }



public Map<String,Object> toMap()
{
    HashMap<String, Object> result = new HashMap<>();

    result.put("User Id",userID);
    result.put("userName",userName);
    result.put("Email",email);
    result.put("Age",age.toString());
    result.put("isDogOwner",isDogOwner);
    result.put("isDogWalker",isDogWalker);

    return result;

}






}
