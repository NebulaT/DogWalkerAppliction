package com.example.nibulateam.dogwalkerappliction;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.nibulateam.dogwalkerappliction.Model.User;

public class UserChoiceDogOrOwnerActivity extends AppCompatActivity{


    Button dogOwnerButton;
    Button dogWalkerButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userchoicewalkerorowner);

        dogOwnerButton=(Button)findViewById(R.id.dogOwnerButton);
        dogOwnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent dogOwnerIntent=new Intent(getApplicationContext(),NewAccountActivity.class);
                dogOwnerIntent.putExtra("choice","DogOwner");

                startActivity(dogOwnerIntent);
            }
        });

        dogWalkerButton=(Button)findViewById(R.id.dogWalkerButton);
        dogWalkerButton.setOnClickListener(new View.OnClickListener()  {

            @Override
            public void onClick(View view) {

                Intent dogWalkerIntenet=new Intent(getApplicationContext(),NewAccountActivity.class);
                dogWalkerIntenet.putExtra("choice","DogWalker");

                startActivity(dogWalkerIntenet);
            }
        });




        //User user=(User)getIntent().getSerializableExtra("user");


    }
}