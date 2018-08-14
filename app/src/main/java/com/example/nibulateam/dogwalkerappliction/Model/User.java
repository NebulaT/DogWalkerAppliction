package com.example.nibulateam.dogwalkerappliction.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {

    private  String userID;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Date age;
    private boolean isDogWalker;
    private  boolean isDogOwner;
    private String phoneNumber;
    //pic//
    protected DogWalker dogWalker;
    protected DogOwner dogOwner;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }


    public String getEmail() {
        return email;
    }


    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public boolean isDogWalker() {
        return isDogWalker;
    }

    public void setDogWalker(boolean dogWalker) {
        isDogWalker = dogWalker;

        if (isDogWalker== true)
        {
            this.dogWalker=new DogWalker();

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

    public User(String UserID, String Name, String Email, String Password, String Phone)
    {
        userID=UserID;
        email=Email;
        password=Password;
        firstName=Name;
        phoneNumber=Phone;
    }
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
