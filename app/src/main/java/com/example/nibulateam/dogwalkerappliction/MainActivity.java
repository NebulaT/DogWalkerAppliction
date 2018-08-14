package com.example.nibulateam.dogwalkerappliction;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nibulateam.dogwalkerappliction.Model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    //ui//

    private Button createNewAccButton;

    //facebbok//
    private LoginButton facebookLoginButton;
    private CallbackManager mCallbackManager;
    private final static String facebook_TAG = "facebook-Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        createNewAccButton = (Button) findViewById(R.id.createNewAccButton);
        facebookLoginButton = (LoginButton) findViewById(R.id.facebookLoginButton);

        createNewAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent acc = new Intent(getApplicationContext(), NewAccountActivity.class);
                startActivity(acc);
            }
        });

        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginFacebook();
            }
        });


    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


    private void LoginFacebook() {

        mCallbackManager = CallbackManager.Factory.create();
        facebookLoginButton.setReadPermissions("email", "public_profile");

        facebookLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(facebook_TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                Log.d(facebook_TAG, "facebook:onCancel");
                // ...
            }


            @Override
            public void onError(FacebookException error) {
                Log.d(facebook_TAG, "facebook:onError", error);
                // ...
            }
        });


    }

    private void handleFacebookAccessToken(AccessToken token) {

        Log.d(facebook_TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(facebook_TAG, "signInWithCredential:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(facebook_TAG, "signInWithCredential:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });

    }
    private void updateUI(FirebaseUser user)
    {
        if(user!=null)
        {
            currentUser=user;
            String userId = user.getUid();

            User tuser=new User(userId,user.getDisplayName(),user.getEmail(),"facebookPassword",user.getPhoneNumber());
            addUserDataTo_DataBase(tuser);
            Log.d(facebook_TAG, "NewUserWithFacebook:success");
            Toast.makeText(MainActivity.this,"New user with Facebook",Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(MainActivity.this," user signout",Toast.LENGTH_SHORT).show();

            //next page!//
        }
        else
        {
            Log.d(facebook_TAG, "ExisitUserWithFacebook:failed");
        }

    }
    private void addUserDataTo_DataBase(User user)
    {
        if(user!=null) {


            mDatabase = FirebaseDatabase.getInstance().getReference();

            mDatabase.child("Users").child(user.getUserId()).child("UserName").setValue(user.getFirstName());

            mDatabase.child("Users").child(user.getUserId()).child("Phone").setValue(user.getPhoneNumber());

            mDatabase.child("Users").child(user.getUserId()).child("Email").setValue(user.getEmail());

            Log.d(facebook_TAG, "Add user to dataBase:success");


        }
        else
        {
            Log.d(facebook_TAG, "Add user to dataBase:fail");
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }



}
