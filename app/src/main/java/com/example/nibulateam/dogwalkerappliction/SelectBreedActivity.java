package com.example.nibulateam.dogwalkerappliction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nibulateam.dogwalkerappliction.Model.DogOwner;
import com.example.nibulateam.dogwalkerappliction.Model.User;

import org.w3c.dom.Text;

public class SelectBreedActivity extends AppCompatActivity {

    private User user;
    //private Intent intent;
    private String dogName;
    private Uri dogPhoto;


    //UI//

    private ImageView addPetView;
    private ImageView PetPhotoView;
    private TextView typeQuestionTextView;
    private EditText breedPlain;
    private TextView addPetTextView;
    private Button nextButton;
    private Button breedButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpet2);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        PetPhotoView=(ImageView)findViewById(R.id.PetPhotoView);
        addPetView=(ImageView)findViewById(R.id.PetPhotoView);
        typeQuestionTextView=(TextView)findViewById(R.id.typeQuestionTextView);
        addPetTextView=(TextView)findViewById(R.id.addPetTextView);
        //breedPlain=(EditText)findViewById(R.id.breedPlain);
        nextButton=(Button)findViewById(R.id.nextButton);
        breedButton=(Button)findViewById(R.id.breedButton);


        dogName = intent.getStringExtra("petName");
        typeQuestionTextView.setText("What type of Dog is "+dogName.toString()+" ? ");
        String uriString = intent.getStringExtra("petPhoto");
        Uri dogPhoto = Uri.parse(uriString);

        PetPhotoView.setImageURI(dogPhoto); //Set previous selected photo- to this page


        breedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectBreedIntent=new Intent(getApplicationContext(),BreedListActivity.class);
                startActivity(selectBreedIntent);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //Intent for next page
            }

            }


        );



    }

}