package com.octolearn.se;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MyStations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stations);

        Button button = (Button) findViewById(R.id.station1);
        button.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Workers.class)));
    }
}