package com.example.nibulateam.dogwalkerappliction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button createNewAccButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNewAccButton=(Button)findViewById(R.id.createNewAccButton);
        createNewAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent acc=new Intent(getApplicationContext(),NewAccountActivity.class);
                startActivity(acc);
            }
        });

    }
}
