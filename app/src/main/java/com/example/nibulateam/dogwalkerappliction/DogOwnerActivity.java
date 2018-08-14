package com.example.nibulateam.dogwalkerappliction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nibulateam.dogwalkerappliction.Model.PetPackage.Pet_Package.Pet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class DogOwnerActivity extends AppCompatActivity{

    private Uri temp_Image;

    //UI//
    private ImageView addPetView;
    private ImageView addPetPhotoView;
    private TextView PetNameTextView;
    private EditText petNamePlain;
    private TextView addPetTextView;
    private Button nextButton;
    private Button addPhotoButton;
    private String petName;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;

    private static final int PICK_IMAGE=100;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpet1);

        addPetView=(ImageView)findViewById(R.id.addPetView);
        addPetPhotoView=(ImageView)findViewById(R.id.addPetPhotoView);
        PetNameTextView=(TextView)findViewById(R.id.PetNameTextView);
        addPetTextView=(TextView)findViewById(R.id.addPetTextView);
        petNamePlain=(EditText)findViewById(R.id.petNamePlain);
        nextButton=(Button)findViewById(R.id.nextButton);
        addPhotoButton=(Button)findViewById(R.id.addPhotoButton);
        petName=petNamePlain.getText().toString();

        mAuth = FirebaseAuth.getInstance();


        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });



    }

    private void openGallery()
    {
        Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode==RESULT_OK  && requestCode==PICK_IMAGE)
        {
            temp_Image=data.getData();
            addPetPhotoView.setImageURI(temp_Image);
        }


    }
}
