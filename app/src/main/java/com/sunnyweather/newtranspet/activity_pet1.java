package com.sunnyweather.newtranspet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class activity_pet1 extends AppCompatActivity {
    private ImageView pet1_turn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet1);

        pet1_turn = findViewById(R.id.pet1turn);
        pet1_turn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(activity_pet1.this,activity_petinfo.class);
                startActivity(intent);
            }
        });

    }
}