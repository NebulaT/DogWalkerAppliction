package com.example.nibulateam.dogwalkerappliction.Model.PetPackage.Pet_Package.Medical;

import java.util.ArrayList;

public class Medical {

    private float dogWeight;
    private String dogAllergies;
    private Vaccines dogVaccines;
    private String dogDisease;
    private ArrayList<String> dogMedicalRecord;

    public float getDogWeight() {
        return dogWeight;
    }

    public void setDogWeight(float dogWeight) {
        this.dogWeight = dogWeight;
    }

    public String getDogAllergies() {
        return dogAllergies;
    }

    public void setDogAllergies(String dogAllergies) {
        this.dogAllergies = dogAllergies;
    }

    public Vaccines getDogVaccines() {
        return dogVaccines;
    }

    public void setDogVaccines(Vaccines dogVaccines) {
        this.dogVaccines = dogVaccines;
    }

    public String getDogDisease() {
        return dogDisease;
    }

    public void setDogDisease(String dogDisease) {
        this.dogDisease = dogDisease;
    }

    public ArrayList<String> getDogMedicalRecord() {
        return dogMedicalRecord;
    }

    public void setDogMedicalRecord(ArrayList<String> dogMedicalRecord) {
        this.dogMedicalRecord = dogMedicalRecord;
    }
}
