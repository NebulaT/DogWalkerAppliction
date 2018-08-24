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

    private User user;
    private boolean flag;

    private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userchoicewalkerorowner);

        intent=getIntent();
        flag=(boolean)intent.getBooleanExtra("flag",false);


        if(flag==true) {
            user = (User) intent.getSerializableExtra("user");
        }
        else
            user=null;




        dogOwnerButton=(Button)findViewById(R.id.dogOwnerButton);
        dogOwnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user==null) {
                    Intent dogOwnerIntent = new Intent(getApplicationContext(), NewAccountActivity.class);
                    dogOwnerIntent.putExtra("choice", "DogOwner");
                    startActivity(dogOwnerIntent);
                }

            }
        });


        dogWalkerButton=(Button)findViewById(R.id.dogWalkerButton);
        dogWalkerButton.setOnClickListener(new View.OnClickListener()  {

            @Override
            public void onClick(View view) {

                if(user==null) {
                    Intent dogWalkerIntenet = new Intent(getApplicationContext(), NewAccountActivity.class);
                    dogWalkerIntenet.putExtra("choice", "DogWalker");

                    startActivity(dogWalkerIntenet);
                }
                else
                {
                    Intent dogWalkerIntenet = new Intent(getApplicationContext(), DogWalker_Register_Page01_Activity.class);
                    dogWalkerIntenet.putExtra("user",user);

                    startActivity(dogWalkerIntenet);
                }
            }
        });






    }
}
