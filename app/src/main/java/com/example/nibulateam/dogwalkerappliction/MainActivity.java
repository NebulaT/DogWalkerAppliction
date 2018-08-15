package com.example.nibulateam.dogwalkerappliction;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

//firebase//
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    //other//
    private boolean isLoginFacebook,isLoginGoogle;


    //ui//

    private Button createNewAccButton;

    //facebbok//
    private LoginButton facebookLoginButton;
    private CallbackManager mCallbackManager;
    private final static String facebook_TAG = "facebook-Login";

    //google//
    private final static String google_TAG = "google-Login";

    private SignInButton googleSignInButton;
    private static final int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        createNewAccButton = (Button) findViewById(R.id.createNewAccButton);
        facebookLoginButton = (LoginButton) findViewById(R.id.facebookLoginButton);
        googleSignInButton = (SignInButton) findViewById(R.id.googleLoginButton);

        createNewAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent acc = new Intent(getApplicationContext(), UserChoiceDogOrOwnerActivity.class);
                startActivity(acc);
            }
        });

        facebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLoginFacebook=true;
                isLoginGoogle=false;
                LoginFacebook();
            }
        });
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLoginFacebook=false;
                isLoginGoogle=true;
            signIn();
            }
        });


    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        updateUI_ToMain(currentUser);
    }

    private void updateUI_ToMain(FirebaseUser currentUser) {

        User user=new User();


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
                    updateUI(user,"facebook");
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(facebook_TAG, "signInWithCredential:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null,"");
                }
            }
        });

    }

    private void updateUI(FirebaseUser user,final  String  TAG) {
        if (user != null) {
            currentUser = user;
            String userId = user.getUid();
            User tuser = new User(userId, user.getDisplayName(), user.getEmail(), TAG+"Password", user.getPhoneNumber());
           // addUserDataTo_DataBase(tuser);
            Log.d(TAG, "NewUserWith"+TAG+":success");
            Toast.makeText(MainActivity.this, "New user with"+TAG, Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(MainActivity.this, " user signout", Toast.LENGTH_SHORT).show();

            //next page!//
        } else {
            Log.d(facebook_TAG, "ExisitUserWith:"+TAG+"failed");
        }

    }

    private void addUserDataTo_DataBase(User user) {
        if (user != null) {


            mDatabase = FirebaseDatabase.getInstance().getReference();

            mDatabase.child("Users").child(user.getUserId()).child("UserName").setValue(user.getFirstName());

            mDatabase.child("Users").child(user.getUserId()).child("Phone").setValue(user.getPhoneNumber());

            mDatabase.child("Users").child(user.getUserId()).child("Email").setValue(user.getEmail());

            Log.d(facebook_TAG, "Add user to dataBase:success");


        } else {
            Log.d(facebook_TAG, "Add user to dataBase:fail");
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(isLoginFacebook) {
            // Pass the activity result back to the Facebook SDK
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
        else if(isLoginGoogle) {


            // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
            if (requestCode == RC_SIGN_IN) {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account);
                } catch (ApiException e) {
                    // Google Sign In failed, update UI appropriately
                    Log.w(google_TAG, "Google sign in failed", e);
                    // ...
                }
            }
        }
    }

    public void LoginGoogle() {

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient=new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Toast.makeText(MainActivity.this,"Sign-in Google-Error",Toast.LENGTH_SHORT).show();
            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

    }

    private void signIn() {
        LoginGoogle();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(google_TAG, "firebaseAuthWithGoogle:" + acct.getId());


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(google_TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user,"Google");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(google_TAG, "signInWithCredential:failure", task.getException());

                            //Snackbar.make(findViewById(R.id.), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null,"");
                        }

                        // ...
                    }
                });
    }


    private void getUserData()
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void showData(DataSnapshot dataSnapshot) {

        for(DataSnapshot ds:dataSnapshot.getChildren())
        {
            User user=new User();
            user.setFirstName(ds.child(currentUser.getUid()).getValue(User.class).getFirstName());

        }
    }
}