package com.example.nibulateam.dogwalkerappliction.Model;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.nibulateam.dogwalkerappliction.Model.PetPackage.Pet_Package.Pet;
import com.example.nibulateam.dogwalkerappliction.R;

import java.util.ArrayList;

public class DogOwner extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpet1);
    }

    private ArrayList<Pet> pets;

    public DogOwner(){}

    public DogOwner(ArrayList<Pet> pets) {
        this.pets = pets;
    }
}
