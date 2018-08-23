package com.example.nibulateam.dogwalkerappliction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nibulateam.dogwalkerappliction.Model.DogWalker;
import com.example.nibulateam.dogwalkerappliction.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class DogWalker_Register_Page01_Activity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;

    private static final String TAG_New_User="New_User_Account";
    private static final String TAG_DATE="Dog_Walker_register";
    private Intent intent;
    private Button SelectDateButton,NextButton,AvaButton;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private CheckBox SmallD,MediumD,LargeD;

    private SeekBar PriceSeekbar;
    private TextView UserNameTV,BirthdayTV;
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
    private ArrayList<Integer>avaCalander;

    private Uri userImage;
    private ImageView userImageView;



    private boolean isInputGender,isInputBirthDay,isInputDogSize;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_dogwalker_page01);


        intent=getIntent();
        user=(User)intent.getSerializableExtra("user");


        avaCalander=new ArrayList<Integer>();

        isInputBirthDay=false;

         SelectDateButton=(Button)findViewById(R.id.SelectDatebutton);
        SmallD=(CheckBox)findViewById(R.id.SmallCheckBox);
        MediumD=(CheckBox)findViewById(R.id.MediumCheckBox);
        LargeD=(CheckBox)findViewById(R.id.LacheckBox);
        MaleRB=(RadioButton)findViewById(R.id.MaleRadioButton);
        FemaleRB=(RadioButton)findViewById(R.id.FemaleRadioButton);
        NextButton=(Button)findViewById(R.id.Nextbutton);
        PriceSeekbar=(SeekBar)findViewById(R.id.PriceSeekBar);
        PriceTV=(TextView)findViewById(R.id.ShowPricetextView);
        UserNameTV=(TextView)findViewById(R.id.userNameTextView) ;
        BirthdayTV=(TextView)findViewById(R.id.birthdayTextView);
        AvaButton=(Button)findViewById(R.id.Avabutton);
        userImageView=(ImageView)findViewById(R.id.userPicIV);

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
            String m=String.valueOf(i1);
            String y=String.valueOf(i);
            String d=String.valueOf(i2);
            BirthdayTV.setText(d+'/'+m+'/'+y);
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
                    isInputDogSize=true;
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
                    isInputDogSize=true;
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
                    isInputDogSize=true;
                }
                if(!LargeD.isChecked())
                {
                    RemoveDogSize("Large");
                }
            }
        });




        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkInput();

                if( isInputBirthDay==true && isInputDogSize==true && isInputGender==true)
                {
                    user.setAge(BirthdayTV.getText().toString());
                    user.dogWalker.setPrice(price);
                    user.dogWalker.setAboutMe(AboutMe);
                    user.dogWalker.setExperience(Exp);
                    user.setGender(GetGender());
                    user.dogWalker.setTypeOfDogs(dogSize);

                    if(!avaCalander.isEmpty())
                    {
                        user.dogWalker.setAvaliability(avaCalander);
                    }


                    Intent dogWalkerCreationIntent=new Intent(getApplicationContext(),DogWalker_Register_Page02_Activity.class);
                    dogWalkerCreationIntent.putExtra("user",user);
                    startActivity(dogWalkerCreationIntent);


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


        AvaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Showavailability();
            }
        });


    }




    private void ChooseBirthday()
    {
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
         month=calendar.get(Calendar.MONTH);
         day=calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog=new DatePickerDialog(DogWalker_Register_Page01_Activity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);

        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    private void setDogSize(String size)
    {
        if(dogSize!=null)
        {
            dogSize+="+";
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

    private void checkInput()
    {

        if(isMale==true || isFemale==true)
        {
            isInputGender=true;
        }
        else
        {
            Toast.makeText(DogWalker_Register_Page01_Activity.this,"Please choose gender",Toast.LENGTH_SHORT).show();
            isInputGender=false;
        }
        if(SmallD.isChecked() || LargeD.isChecked() || MediumD.isChecked())
        {
            isInputDogSize=true;
        }
        else
        {
            Toast.makeText(DogWalker_Register_Page01_Activity.this,"Please choose dog type",Toast.LENGTH_SHORT).show();
            isInputDogSize=false;

        }
        if(isInputBirthDay==false)
        {
            Toast.makeText(DogWalker_Register_Page01_Activity.this,"Please Enter your birthday",Toast.LENGTH_SHORT).show();

        }



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

    private void Showavailability()
    {
        Bundle extra = new Bundle();
        extra.putSerializable("avaCalander", avaCalander);

        Intent intent=new Intent(getApplicationContext(),Avaliability_Dogwalker_Activity.class);
        intent.putExtra("extra", extra);

        //Intent intent =new Intent(getApplicationContext(),Avaliability_Dogwalker_Activity.class);
        //intent.putExtra("avaCalander",avaCalander);

        startActivityForResult(intent,999);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==999) {
            try {

                Bundle value = data.getBundleExtra("extra");
                avaCalander = (ArrayList<Integer>) value.getSerializable("saveCalander");

                Toast.makeText(DogWalker_Register_Page01_Activity.this, "the value return " + avaCalander.get(0), Toast.LENGTH_SHORT).show();
            } catch (NullPointerException exp) {
                Log.e("Exp", "value-null");
            }
        }

    }
}
