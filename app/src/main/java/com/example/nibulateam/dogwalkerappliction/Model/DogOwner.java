package com.example.nibulateam.dogwalkerappliction.Model;

import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nibulateam.dogwalkerappliction.Model.PetPackage.Pet_Package.Pet;
import com.example.nibulateam.dogwalkerappliction.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.ArrayList;

public class DogOwner implements Serializable {

    private ArrayList<Pet> pets;
    private ArrayList<User> dogWalkers;

    public DogOwner() {
        pets = new ArrayList<Pet>();
        dogWalkers = new ArrayList<User>();
    }

    public DogOwner(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public ArrayList<User> getDogWalkers() {
        return dogWalkers;
    }

    public void setDogWalkers(ArrayList<User> dogWalkers) {
        this.dogWalkers = dogWalkers;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public void removePet(Pet pet)
    {
        if(pets.contains(pet))
        {
            pets.remove(pet);
        }
    }
    public void addDogWalker(User dogWalker)
    {
        if(dogWalker.isDogWalker())
        {
            if(!dogWalkers.contains(dogWalker))
                  dogWalkers.add(dogWalker);
        }
    }
    public void removeDogWalker(User dogWalker)
    {
        if(dogWalker.isDogWalker())
        {
            if(dogWalkers.contains(dogWalker))
                dogWalkers.remove(dogWalker);
        }
    }



}
