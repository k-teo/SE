package com.studies.se;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyStations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stations);

        TextView station1 = findViewById(R.id.nameStation1);
        TextView station2 = findViewById(R.id.nameStation2);

        String name1 = "Mercedes-Benz Wrocław";
        String name2 = "Nissan Wrocław";

        station1.setText(name1);
        station2.setText(name2);

        Button button1 = (Button) findViewById(R.id.station1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Employees.class);
                Bundle b = new Bundle();
                b.putString("owner", "1");
                b.putString("station", name1);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.station2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Employees.class);
                Bundle b = new Bundle();
                b.putString("owner", "2");
                b.putString("station", name2);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}