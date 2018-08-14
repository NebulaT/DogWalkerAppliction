package com.example.nibulateam.dogwalkerappliction;

import android.app.DatePickerDialog;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nibulateam.dogwalkerappliction.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class DogWalker_RegisterActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;


    private static final String TAG_DATE="Dog_Walker_register";
    private Intent intent;
    private Button SelectDateButton,DoneButton;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private CheckBox SmallD,MediumD,LargeD;
    private EditText ExprianceET,AboutMeET;
    private SeekBar PriceSeekbar;
    private TextView UserNameTV;
    private TextView PriceTV;
    private int year,month,day;
    private User user;
    private int price;


    private RadioGroup radioGroupFM;
    private RadioButton MaleRB,FemaleRB;

    private Boolean isMale,isFemale;
    private String dogSize;
    private String Exp=null;
    private String AboutMe=null;



    private boolean isInputGender,isInputBirthDay,isInputDogSize;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_dogwalker);


        intent=getIntent();
        user=(User)intent.getSerializableExtra("user");
        mAuth = FirebaseAuth.getInstance();

        Toast.makeText(DogWalker_RegisterActivity.this,user.getEmail(),Toast.LENGTH_SHORT).show();


    SelectDateButton=(Button)findViewById(R.id.SelectDatebutton);
        SmallD=(CheckBox)findViewById(R.id.SmallCheckBox);
        MediumD=(CheckBox)findViewById(R.id.MediumCheckBox);
        LargeD=(CheckBox)findViewById(R.id.LacheckBox);
        MaleRB=(RadioButton)findViewById(R.id.MaleRadioButton);
        FemaleRB=(RadioButton)findViewById(R.id.FemaleRadioButton);
        ExprianceET=(EditText)findViewById(R.id.ExpEditText);
        DoneButton=(Button)findViewById(R.id.Donebutton);
        PriceSeekbar=(SeekBar)findViewById(R.id.PriceSeekBar);
        PriceTV=(TextView)findViewById(R.id.ShowPricetextView);
        UserNameTV=(TextView)findViewById(R.id.userNameTextView) ;
        AboutMeET=(EditText)findViewById(R.id.AboutMeEditText);

        PriceTV.setText("0$");

        UserNameTV.setText("Hello "+user.getFirstName());

        SelectDateButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ChooseBirthday();
        }
    });
    mDateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Log.d(TAG_DATE,"BirthdaySet :"+i+"/"+i1+"/"+i2);

            isInputBirthDay=true;
        }
    };



        MaleRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FemaleRB.isChecked())
                {
                    FemaleRB.setChecked(false);
                    isMale=true;
                    isFemale=false;

                }
            }
        });



        FemaleRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FemaleRB.isChecked())
                {
                    MaleRB.setChecked(false);
                    isFemale=true;
                    isMale=false;
                }
            }
        });

        SmallD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SmallD.isChecked())
                {
                  setDogSize("Small");
                }
                if(!SmallD.isChecked())
                {
                    RemoveDogSize("Small");
                }
            }
        });

        MediumD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MediumD.isChecked())
                {
                    setDogSize("Medium");
                }
                if(!MediumD.isChecked())
                {
                    RemoveDogSize("Medium");
                }
            }
        });

        LargeD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LargeD.isChecked())
                {
                    setDogSize("Large");
                }
                if(!LargeD.isChecked())
                {
                    RemoveDogSize("Large");
                }
            }
        });




        DoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInput()==true)
                {
                    Exp=ExprianceET.getText().toString();
                    AboutMe=AboutMeET.getText().toString();
                    // user.setAge(new Date(year,month,day));
                    user.dogWalker.setPrice(price);
                    user.dogWalker.setAboutMe(AboutMe);
                    user.dogWalker.setExperience(Exp);
                    user.setGender(GetGender());
                    user.dogWalker.setTypeOfDogs(dogSize);

                    addUserTo_DataBase();
                }
            }
        });



        PriceSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int prograss=0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                prograss=i;
                PriceTV.setText(prograss+" $");

                price=prograss;



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private void addUserTo_DataBase() {

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Users").child(user.getUserId()).child("isDogWalker").setValue(user.isDogWalker());
        mDatabase.child("Users").child(user.getUserId()).child("Gender").setValue(user.getGender());
        mDatabase.child("Users").child(user.getUserId()).child("Age").setValue(user.getAge());

        if (user.isDogWalker()) {



            mDatabase.child("Users").child(user.getUserId()).child("DogWalker").child("Price").setValue(user.dogWalker.getPrice());

            mDatabase.child("Users").child(user.getUserId()).child("DogWalker").child("About me").setValue(user.dogWalker.getAboutMe());

            mDatabase.child("Users").child(user.getUserId()).child("DogWalker").child("Experience").setValue(user.dogWalker.getExperience());

            mDatabase.child("Users").child(user.getUserId()).child("DogWalker").child("DogsType").setValue(user.dogWalker.getTypeOfDogs());


        }

       // Log.d(TAG_New_User, "Add user to dataBase:success");



    }


    private void ChooseBirthday()
    {
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
         month=calendar.get(Calendar.MONTH);
         day=calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog=new DatePickerDialog(DogWalker_RegisterActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);

        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    private void setDogSize(String size)
    {
        if(dogSize!=null)
        {
            dogSize+=size;
        }
        else
        {
            dogSize=size;
        }
    }
    private void RemoveDogSize(String size)
    {
        if(dogSize.contains(size))
        {
            dogSize.replace(size,"");
        }
    }

    private boolean checkInput()
    {

        if(isMale==true || isFemale==true)
        {
            isInputGender=true;
        }
        else
        {
            Toast.makeText(DogWalker_RegisterActivity.this,"Please choose gender",Toast.LENGTH_SHORT).show();
        }
        if(SmallD.isChecked() || LargeD.isChecked() || MediumD.isChecked())
        {
            isInputDogSize=true;
        }
        else
        {
            Toast.makeText(DogWalker_RegisterActivity.this,"Please choose dog type",Toast.LENGTH_SHORT).show();

        }
        if(isInputBirthDay==true && isInputDogSize==true && isInputGender==true)
        {
            return true;
        }



        return false;

    }


    private String GetGender()
    {
        if(isFemale)
        {
            return "Female";
        }
        else if(isMale)
        {
            return "Male";
        }
        else return null;
    }
}
