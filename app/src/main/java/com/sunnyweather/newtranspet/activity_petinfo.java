package com.sunnyweather.newtranspet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class activity_petinfo extends AppCompatActivity {
    private LinearLayout jinmaoinfo;
    private ImageView petinfo_turn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petinfo);

        jinmaoinfo = findViewById(R.id.modulepet1);
        jinmaoinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(activity_petinfo.this,activity_pet1.class);
                startActivity(intent);
            }
        });
        petinfo_turn = findViewById(R.id.petinfoturn);
        petinfo_turn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(activity_petinfo.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}