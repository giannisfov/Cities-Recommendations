package com.example.mygeoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonMap;
    private Button buttonResult;
    private UserDao userDao;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonMap = findViewById(R.id.button1);
        buttonResult = findViewById(R.id.button2);


        // Map and Result Map button Listeners

        runOnUiThread(()-> buttonMap.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        }));

        runOnUiThread(()-> buttonResult.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ResultMapsActivity.class);
            startActivity(intent);
        }));

    }

}