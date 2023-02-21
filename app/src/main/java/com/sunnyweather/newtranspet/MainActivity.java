package com.sunnyweather.newtranspet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {

    private TextView intopetinfo;
    private ImageView personnelcalling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intopetinfo = findViewById(R.id.petinfo);
        ImageView imageView24 = findViewById(R.id.imageView24);
        imageView24.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity_personnel.class);
            intent.putExtra("isUser",false);
            startActivity(intent);
        });
        intopetinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(MainActivity.this, activity_petinfo.class);
                startActivity(intent);
            }
        });
        personnelcalling = findViewById(R.id.personnelimge);
        personnelcalling.setOnClickListener(view -> {
            // Write a message to the database

            Intent intent = null;
            intent = new Intent(MainActivity.this, activity_personnel.class);
            intent.putExtra("isUser",true);
            startActivity(intent);
        });


    }

}