package com.example.nibulateam.dogwalkerappliction;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nibulateam.dogwalkerappliction.Model.Input;
import com.example.nibulateam.dogwalkerappliction.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class NewAccountActivity extends AppCompatActivity {

    private String email;
    private String password;
    private String phone;
    private String name;
    private String userId;

    private static String TAG_New_User="New user";
    private static String TAG_Exisit_User="Exisit user";

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;


    //ui//
    private Button creatButton;
    private EditText EmailET;
    private EditText PasswordET;
    private  EditText PhoneET;
    private  EditText NameET;
    private String UserID;

    private User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaccountactivity);

        mAuth = FirebaseAuth.getInstance();

        creatButton=(Button)findViewById(R.id.createButton);
        EmailET=(EditText) findViewById(R.id.emailPlain) ;
        NameET=(EditText)findViewById(R.id.namePlain);
        PasswordET=(EditText)findViewById(R.id.passwordPlain);
        PhoneET=(EditText)findViewById(R.id.phonePlain);




        creatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean check=checkInput();
              if(check==true)
              {
              email=EmailET.getText().toString();
              password=PasswordET.getText().toString();
              phone=PhoneET.getText().toString();
              name=NameET.getText().toString();
              signUpNewUsersWithEmail();

        }
        else {
                  Toast.makeText(NewAccountActivity.this, "Authentication failed.",
                          Toast.LENGTH_SHORT).show();
              }

            }
        });



/*
//exmple
        email="yaronmord@gmail.com";
        password="123456";
        signUpNewUsersWithEmail();
*/

//      signInExistingUsers("yaronmord@gmail.com","123456");



    }


    private void signUpNewUsersWithEmail() {
        if (email.equals(null) || password.equals(null)) {
            Toast.makeText(NewAccountActivity.this, "Authentication failed - Email or password",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG_New_User, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG_New_User, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(NewAccountActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });


        }
    }

    private void updateUI(FirebaseUser user)
    {

        if(user!=null)
        {
            currentUser=user;
            userId=user.getUid();
            this.user=new User(userId,name,email,password,phone);
            addUserDataTo_DataBase(this.user);
            Log.d(TAG_Exisit_User, "ExisitUserWithEmail:success");
            //next page!//
        }
        else
        {
            Log.d(TAG_Exisit_User, "ExisitUserWithEmail:failed");
        }
    }

    private void addUserDataTo_DataBase(User user)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Users").child(user.getUserId()).child("UserName").setValue(user.getFirstName());

        mDatabase.child("Users").child(user.getUserId()).child("Phone").setValue(user.getPhoneNumber());

        mDatabase.child("Users").child(user.getUserId()).child("Email").setValue(user.getEmail());




    }


    public boolean checkInput()
    {
        if(!Input.isValidEmail(EmailET.getText().toString()))
        {
            Toast.makeText(NewAccountActivity.this, "Email-Error",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
       else if(!Input.isValidName(NameET.getText().toString()))
        {
            Toast.makeText(NewAccountActivity.this, "Name-Error",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
       else if(!Input.isValidName(PhoneET.getText().toString()))
        {
            Toast.makeText(NewAccountActivity.this, "Phone-Error",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
      else  if(!Input.isValidName(PasswordET.getText().toString()))
        {
            Toast.makeText(NewAccountActivity.this, "Password-Error must be 4 letters and numbers",
                    Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;

    }

}
