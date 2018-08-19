package com.example.nibulateam.dogwalkerappliction;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;

public class Avaliability_Dogwalker_Activity extends Activity {

    private static final int ROW = 7;
    private static final int COL = 5;

    private TableLayout tableLayout;
    private ImageButton[][] imageButtons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_avaliability_dogwalker);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .9));


        imageButtons = new ImageButton[ROW][COL];


        for (int i=0;i<ROW;i++)
        {
            for(int j=0 ;j<COL;j++)
            {
                String temp="ava"+i+j;
               int Res=getResources().getIdentifier(temp,"id",getPackageName());
                imageButtons[i][j]=(ImageButton)findViewById(Res);

            //    imageButtons[i][j].setOnClickListener(this);
            }
        }

    }
}
