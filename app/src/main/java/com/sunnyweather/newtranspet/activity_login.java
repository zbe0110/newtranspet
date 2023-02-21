package com.sunnyweather.newtranspet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_login extends AppCompatActivity {
    private TextView intoregister;
    private ImageView intomain;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intoregister = findViewById(R.id.notice2);
        intoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(activity_login.this,activity_register.class);
                startActivity(intent);
            }
        });

        intomain = findViewById(R.id.loginbutton);
        intomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(activity_login.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}