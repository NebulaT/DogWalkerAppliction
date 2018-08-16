package com.example.nibulateam.dogwalkerappliction.Model;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataBase {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;
    private User user;


    public DataBase(FirebaseUser currentUser)
    {
        this.currentUser=currentUser;
        mDatabase= FirebaseDatabase.getInstance().getReference();
    }


    public User getUser_dogWalker()
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                user=(User)getData_dogWalker(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return user;

    }
    public User getData_dogWalker(DataSnapshot dataSnapshot)
    {
        User user=new User();
        for(DataSnapshot ds:dataSnapshot.getChildren())
        {
            user.setUserID(currentUser.getUid());
            user.setFirstName(ds.child(currentUser.getUid()).getValue(User.class).getFirstName());
            user.setEmail(ds.child(currentUser.getUid()).getValue(User.class).getEmail());
            user.setPhoneNumber(ds.child(currentUser.getUid()).getValue(User.class).getPhoneNumber());
            user.setGender(ds.child(currentUser.getUid()).getValue(User.class).getGender());
            user.setDogWalker();
            user.dogWalker.setAboutMe(ds.child(currentUser.getUid()).child("DogWalker").getValue(User.class).dogWalker.getAboutMe());
            user.dogWalker.setExperience(ds.child(currentUser.getUid()).child("DogWalker").getValue(User.class).dogWalker.getExperience());
            user.dogWalker.setPrice(ds.child(currentUser.getUid()).child("DogWalker").getValue(User.class).dogWalker.getPrice());


        }

return user;

    }



}
