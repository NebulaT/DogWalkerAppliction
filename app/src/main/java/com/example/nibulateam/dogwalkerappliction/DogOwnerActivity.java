package com.example.nibulateam.dogwalkerappliction;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import com.example.nibulateam.dogwalkerappliction.Model.Input;
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
    private Button addPhotoButton;
    private Button rotateButton;


    private String dogName;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;

    private Uri tempImage=null;
    private static final int PICK_IMAGE=100;



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
        addPhotoButton=(Button)findViewById(R.id.addPhotoButton);
        rotateButton=(Button)findViewById(R.id.rotateButton);

        mAuth = FirebaseAuth.getInstance();

        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addPetPhotoView.setRotation(addPetPhotoView.getRotation()+90);
            }
        });

        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri pathToImage= Uri.parse("android.resource://com.example.nibulateam.dogwalkerappliction/" + R.drawable.sittingdog);
                String imagePath = pathToImage.toString();

                dogName=petNamePlain.getText().toString();
                if(Input.isValidName(dogName)) {
                    Intent selectPhotoName_Intent = new Intent(getApplicationContext(), SelectBreedActivity.class);
                    selectPhotoName_Intent.putExtra("dogName", dogName);
                    if (tempImage != null) {
                        selectPhotoName_Intent.putExtra("dogPhoto", tempImage.toString());
                    } else {
                        //addPetPhotoView.setImageURI(pathToImage);
                        addPetPhotoView.setTag(pathToImage.toString());
                        selectPhotoName_Intent.putExtra("dogPhoto", "EmptyValue");

                    }

                    startActivity(selectPhotoName_Intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Dog Name must be set",Toast.LENGTH_LONG);
                }

                /*move to next page - to select breed of a dog*/
            }
        });

    }
      private void openGallery()
    {
        Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestedCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestedCode,resultCode,data);

        if(resultCode==RESULT_OK && requestedCode==PICK_IMAGE)
        {
            tempImage=data.getData();
            addPetPhotoView.setImageURI(tempImage);
        }


    }
}
