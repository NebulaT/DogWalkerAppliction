package com.example.nibulateam.dogwalkerappliction;

import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BreedListActivity extends AppCompatActivity{

    private TextView breedTextView;
    private ImageView breedImageView;

    private TextView breedName;
    private ImageButton imageDog;

    private String[] dogBreed;
    private final Resources resources=getResources();

    int[] dogBreedImage = new int[]{R.drawable.dog_two};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breedlist);

        breedTextView=(TextView)findViewById(R.id.breedTextView);
        breedImageView=(ImageView)findViewById(R.id.breedImageView);
        breedName=(TextView)findViewById(R.id.breedName);
        imageDog=(ImageButton)findViewById(R.id.imageDog);



        dogBreed=resources.getStringArray(R.array.dog_breeds_array);
        Arrays.sort(dogBreed);

        imageDog.setImageResource(dogBreedImage[0]);
        //breedName.setText(dogBreed[]);


        }

    }

