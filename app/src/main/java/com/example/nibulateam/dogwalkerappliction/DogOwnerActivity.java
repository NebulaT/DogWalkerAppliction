package com.example.nibulateam.dogwalkerappliction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nibulateam.dogwalkerappliction.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class DogOwnerActivity extends AppCompatActivity{

    //UI//
    private ImageView addPetView;
    private ImageView addPetPhotoView;
    private TextView PetNameTextView;
    private EditText petNamePlain;
    private TextView addPetTextView;
    private Button nextButton;
    private Intent intent;
    private User user;

    private String petName;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpet1);
        intent=getIntent();
        user=(User)intent.getSerializableExtra("user");


        addPetView=(ImageView)findViewById(R.id.addPetView);
        addPetPhotoView=(ImageView)findViewById(R.id.addPetPhotoView);
        PetNameTextView=(TextView)findViewById(R.id.PetNameTextView);
        addPetTextView=(TextView)findViewById(R.id.addPetTextView);
        petNamePlain=(EditText)findViewById(R.id.petNamePlain);
        nextButton=(Button)findViewById(R.id.nextButton);

        petName=petNamePlain.getText().toString();

        mAuth = FirebaseAuth.getInstance();


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

    }
}
