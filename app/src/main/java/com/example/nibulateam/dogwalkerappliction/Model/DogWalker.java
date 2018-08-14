package com.example.nibulateam.dogwalkerappliction.Model;

import java.io.Serializable;

public class DogWalker  implements Serializable {

private float rate;
private  float price;
private String aboutMe;
private String experience;
private String typeOfDogs;

//avaliability//

    public   DogWalker()
    {

    }


    public String getTypeOfDogs() {
        return typeOfDogs;
    }

    public void setTypeOfDogs(String typeOfDogs) {
        this.typeOfDogs = typeOfDogs;
    }



    public DogWalker(float rate, float price, String aboutMe, String experience) {
        this.rate = rate;
        this.price = price;
        this.aboutMe = aboutMe;
        this.experience = experience;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
