package com.example.nibulateam.dogwalkerappliction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nibulateam.dogwalkerappliction.Model.User;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DogWalker_Register_Page02_Activity extends AppCompatActivity {

    private static final String TAG_New_User="New_User_Account";

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;

    private EditText ExprianceET,AboutMeET;
    private Button DoneButton;
    private User user;
    private Intent intent;


    private String Exp,AboutMe;
    private TextView UserNameTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_dogwalker_page02);


        intent=getIntent();
        user=(User)intent.getSerializableExtra("user");


        mAuth = FirebaseAuth.getInstance();




        UserNameTV=(TextView)findViewById(R.id.userNameTextView2);
        UserNameTV.setText("Hello "+user.getFirstName());


        ExprianceET=(EditText)findViewById(R.id.ExpEditText);
        AboutMeET=(EditText)findViewById(R.id.AboutmeEditText);
        DoneButton=(Button)findViewById(R.id.Donebutton);





        DoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exp=ExprianceET.getText().toString();
                AboutMe=AboutMeET.getText().toString();

                if(Exp==null)
                {
                    user.dogWalker.setExperience("");
                }
                else
                {
                    user.dogWalker.setExperience(Exp);

                }

                if(AboutMe==null)
                {
                    user.dogWalker.setAboutMe("");
                }
                else
                {
                    user.dogWalker.setAboutMe(AboutMe);

                }

                addUserTo_DataBase();
            }
        });


    }

    private void addUserTo_DataBase() {


    mDatabase = FirebaseDatabase.getInstance().getReference();

    //mDatabase.child("Users").child(user.getUserId()).child("UserName").setValue(user.getFirstName());
   // mDatabase.child("Users").child(user.getUserId()).child("Phone").setValue(user.getPhoneNumber());
   // mDatabase.child("Users").child(user.getUserId()).child("Email").setValue(user.getEmail());
    mDatabase.child("Users").child(user.getUserId()).child("isDogWalker").setValue(user.isDogWalker());
    mDatabase.child("Users").child(user.getUserId()).child("Gender").setValue(user.getGender());
    mDatabase.child("Users").child(user.getUserId()).child("Age").setValue(user.getAge());



    if (user.isDogWalker()) {


        mDatabase.child("Users").child(user.getUserId()).child("DogWalker").child("Price").setValue(user.dogWalker.getPrice());

        mDatabase.child("Users").child(user.getUserId()).child("DogWalker").child("About me").setValue(user.dogWalker.getAboutMe());

        mDatabase.child("Users").child(user.getUserId()).child("DogWalker").child("Experience").setValue(user.dogWalker.getExperience());

        mDatabase.child("Users").child(user.getUserId()).child("DogWalker").child("DogsType").setValue(user.dogWalker.getTypeOfDogs());

        if(!user.dogWalker.getAvaliability().isEmpty())
        {
            mDatabase.child("Users").child(user.getUserId()).child("DogWalker").child("Avaliability").setValue(user.dogWalker.getAvaliability());
        }

    }

    Log.d(TAG_New_User, "Add user to dataBase:success");


    }


}