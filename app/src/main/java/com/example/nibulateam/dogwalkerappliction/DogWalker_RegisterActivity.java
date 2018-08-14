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
import android.widget.TextView;
import android.widget.Toast;

import com.example.nibulateam.dogwalkerappliction.Model.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class DogWalker_RegisterActivity extends AppCompatActivity {


    private static final String TAG_DATE="Dog_Walker_register";
    private Intent intent;
    private Button SelectDateButton,DoneButton;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private CheckBox SmallD,MediumD,LargeD;
    private int year,month,day;
    private User user;


    private RadioGroup radioGroupFM;
    private RadioButton MaleRB,FemaleRB;

    private Boolean isMale,isFemale;
    private String dogSize;
    private String Exp=null;
    private EditText ExprianceET;

    private boolean isInputGender,isInputBirthDay,isInputDogSize;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_dogwalker);

        intent=getIntent();
        user=(User)intent.getSerializableExtra("user");


        Toast.makeText(DogWalker_RegisterActivity.this,user.getEmail(),Toast.LENGTH_SHORT).show();


    SelectDateButton=(Button)findViewById(R.id.SelectDatebutton);
        SmallD=(CheckBox)findViewById(R.id.SmallCheckBox);
        MediumD=(CheckBox)findViewById(R.id.MediumCheckBox);
        LargeD=(CheckBox)findViewById(R.id.LacheckBox);
        MaleRB=(RadioButton)findViewById(R.id.MaleRadioButton);
        FemaleRB=(RadioButton)findViewById(R.id.FemaleRadioButton);
        ExprianceET=(EditText)findViewById(R.id.ExpEditText);
        DoneButton=(Button)findViewById(R.id.Donebutton);


        SelectDateButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            ChooseBirthday();
        }
    });
    mDateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Log.d(TAG_DATE,"onDateSet :"+i+"/"+i1+"/"+i2);
            isInputBirthDay=true;
        }
    };
/*
    radioGroupFM=(RadioGroup)findViewById(R.id.MaleFemalGruopB);

    radioGroupFM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {

            if(checkId==R.id.MaleRadioButton)
            {

            }
            if(checkId==R.id.FemaleRadioButton)
            {

            }

        }
    });
*/


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


        Exp=ExprianceET.getText().toString();

        DoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkInput()==true)
                {

                }
            }
        });


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
}
