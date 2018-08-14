package com.example.nibulateam.dogwalkerappliction.Model.PetPackage.Pet_Package;

import android.net.Uri;

import com.example.nibulateam.dogwalkerappliction.Model.PetPackage.Pet_Package.Medical.Medical;

import java.util.Date;

public class Pet {

    private String dogName;
    private Uri dogPicture;
    private Date dateOfBirth;
    private String dogBreed;
    private Medical dogMedicalRecord;
    private float dogDistance;

    //SETTERS
    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public void setDogPicture(Uri dogPicture) {
        this.dogPicture = dogPicture;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    public void setDogMedicalRecord(Medical dogMedicalRecord) {
        this.dogMedicalRecord = dogMedicalRecord;
    }

    public void setDogDistance(float dogDistance) {
        this.dogDistance = dogDistance;
    }

    //GETTERS
    public String getDogName() {
        return dogName;
    }

    public Uri getDogPicture() {
        return dogPicture;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public Medical getDogMedicalRecord() {
        return dogMedicalRecord;
    }

    public float getDogDistance() {
        return dogDistance;
    }
}
