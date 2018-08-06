package com.example.nibulateam.dogwalkerappliction;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

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
    private static String TAG_New_User="New user";
    private static String TAG_Exisit_User="Exisit user";

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaccountactivity);

        mAuth = FirebaseAuth.getInstance();

/*
//exmple
        email="yaronmord@gmail.com";
        password="123456";
        signUpNewUsersWithEmail();
*/

//      signInExistingUsers("yaronmord@gmail.com","123456");



    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
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

        mDatabase.child("users").child(user.getUserId()).setValue(user);
    }

}
