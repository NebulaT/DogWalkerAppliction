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

import java.util.ArrayList;

public class DogOwner {

    private ArrayList<Pet> pets;

    public DogOwner()
    {

    }

    public DogOwner(ArrayList<Pet> pets) {
        this.pets = pets;
    }
}
