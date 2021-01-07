package com.studies.se;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyStations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stations);

        Button button1 = (Button) findViewById(R.id.station1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Workers.class);
                Bundle b = new Bundle();
                b.putString("owner", "1");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.station2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Workers.class);
                Bundle b = new Bundle();
                b.putString("owner", "2");
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}