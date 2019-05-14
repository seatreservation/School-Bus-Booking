package com.example.busseatreservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class train_info extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_info);
        ImageButton ib1=(ImageButton)findViewById(R.id.imageButton1);
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t1=new Intent();
                t1.setClass(train_info.this,train_info_1.class);
                startActivity(t1);
            }
        });
        ImageButton ib2=(ImageButton)findViewById(R.id.imageButton2);
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t1=new Intent();
                t1.setClass(train_info.this,train_info_2.class);
                startActivity(t1);
            }
        });
        ImageButton ib3=(ImageButton)findViewById(R.id.imageButton3);
        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t1=new Intent();
                t1.setClass(train_info.this,train_info_3.class);
                startActivity(t1);
            }
        });
    }
}
