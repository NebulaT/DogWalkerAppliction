package com.example.nibulateam.dogwalkerappliction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.nibulateam.dogwalkerappliction.Model.Avaliailtiy_interface;

import java.io.Serializable;
import java.util.ArrayList;

public class Avaliability_Dogwalker_Activity extends Activity implements Avaliailtiy_interface,Serializable {





    private CheckBox[] checkBoxeseButtons;
    private int i;
    private ArrayList<Integer> saveCalander;
    private Button DoneB;
    private Intent intent;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_avaliability_dogwalker);
        intent=getIntent();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .9));

       // checkBoxeseButtons=new CheckBox[Res.length];
        getValueFromRegister();
            //saveCalander = new ArrayList<>();




        DoneB=(Button)findViewById(R.id.doneAvaButton);


        for ( i=0;i<Res.length;i++)
        {
          //  checkBoxeseButtons[i]=(CheckBox) findViewById(Res[i]);
            checkBoxeseButtons[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {


                    switch (view.getId())
                    {
                        case R.id.ava00:
                            checkFunc(0);
                            break;
                        case  R.id.ava01:
                            checkFunc(1);
                            break;
                        case  R.id.ava02:
                            checkFunc(2);
                            break;
                        case  R.id.ava03:
                            checkFunc(3);
                            break;
                        case  R.id.ava04:
                            checkFunc(4);
                            break;
                        case  R.id.ava05:
                            checkFunc(5);
                            break;
                        case  R.id.ava06:
                            checkFunc(6);
                            break;
                        case  R.id.ava10:
                            checkFunc(7);
                            break;
                        case  R.id.ava11:
                            checkFunc(8);
                            break;
                        case  R.id.ava12:
                            checkFunc(9);
                            break;
                        case  R.id.ava13:
                            checkFunc(10);
                            break;
                        case  R.id.ava14:
                            checkFunc(11);
                            break;
                        case  R.id.ava15:
                            checkFunc(12);
                            break;
                        case  R.id.ava16:
                            checkFunc(13);
                            break;
                        case  R.id.ava20:
                            checkFunc(14);
                            break;
                        case  R.id.ava21:
                            checkFunc(15);
                            break;
                        case  R.id.ava22:
                            checkFunc(16);
                            break;

                        case  R.id.ava23:
                            checkFunc(17);
                            break;
                        case  R.id.ava24:
                            checkFunc(18);
                            break;
                        case  R.id.ava25:
                            checkFunc(19);
                            break;
                        case  R.id.ava26:
                            checkFunc(20);
                            break;
                        case  R.id.ava30:
                            checkFunc(21);
                            break;
                        case  R.id.ava31:
                            checkFunc(22);
                            break;
                        case  R.id.ava32:
                            checkFunc(23);
                            break;
                        case  R.id.ava33:
                            checkFunc(24);
                            break;
                        case  R.id.ava34:
                            checkFunc(25);
                            break;
                        case  R.id.ava35:
                            checkFunc(26);
                            break;
                        case  R.id.ava36:
                            checkFunc(27);
                            break;
                        case  R.id.ava40:
                            checkFunc(28);
                            break;
                        case  R.id.ava41:
                            checkFunc(29);
                            break;
                        case  R.id.ava42:
                            checkFunc(30);
                            break;
                        case  R.id.ava43:
                            checkFunc(31);
                            break;
                        case  R.id.ava44:
                            checkFunc(32);
                            break;
                        case  R.id.ava45:
                            checkFunc(33);
                            break;
                        case  R.id.ava46:
                            checkFunc(34);
                            break;




                    }
                }
            });
        }

        DoneB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(saveCalander.isEmpty())
                {

                }
                else
                {
                    ReturnValueToRegister();

                }

            }
        });


}


   private void checkFunc(int num)
    {

        if(checkBoxeseButtons[num].isChecked())
        {

            saveCalander.add(Res_S01[num]);
            Toast.makeText(Avaliability_Dogwalker_Activity.this,"add "+Res_S01[num],Toast.LENGTH_SHORT).show();

        }
        else if(!checkBoxeseButtons[num].isChecked()) {
            if (saveCalander.contains(Res_S01[num]))
            {
                Toast.makeText(Avaliability_Dogwalker_Activity.this,"Remove "+Res_S01[num],Toast.LENGTH_SHORT).show();

                saveCalander.remove(Res_S01[num]);
            }

        }
    }

private void ReturnValueToRegister()
{
    Bundle extra = new Bundle();
    extra.putSerializable("saveCalander", saveCalander);

    Intent intent=new Intent();
   intent.putExtra("extra", extra);

   setResult(RESULT_OK,intent);

    finish();

}

private void getValueFromRegister()
{
    initCheckboxs();

    Bundle value = intent.getBundleExtra("extra");

    if (!value.getSerializable("avaCalander").equals(null))
    {
        saveCalander=((ArrayList<Integer>)value.getSerializable("avaCalander"));
    }
    else
    {
        saveCalander=new ArrayList<>();
    }

    if(!saveCalander.isEmpty())
    {
        for(int i=0;i<saveCalander.size();i++)
        {
            int index=saveCalander.get(i);

            checkBoxeseButtons[index].setChecked(true);
        }
    }


}

private void initCheckboxs()
{
    checkBoxeseButtons=new CheckBox[Res.length];


    for (int i=0;i<Res.length;i++)
    {
        checkBoxeseButtons[i]=(CheckBox)findViewById(Res[i]);
    }

}

}
