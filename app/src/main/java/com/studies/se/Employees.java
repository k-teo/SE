package com.studies.se;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Employees extends AppCompatActivity {

    private ListView employeesListView;
    private ArrayList<Employee> employees;
    private EmployeeAdapter employeeAdapter;
    private EmployeeDB dataBase;
    private String owner;
    private String station;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);

        dataBase = new EmployeeDB(this);
        employeesListView = (ListView) findViewById(R.id.employeeListView);
        employees = new ArrayList<>();

        Bundle b = getIntent().getExtras();
        if(b != null)
        {
            owner = b.getString("owner");
            station = b.getString("station");
        }

        TextView stationName = findViewById(R.id.employeesText);
        stationName.setText(station);

        loadFlashcardsFromDatabase();

        Button button = (Button) findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Employees.this, AddEmployee.class);
                Bundle b = new Bundle();
                b.putString("owner", owner);
                b.putString("station", station);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        employeesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Employees.this, EmployeeInfo.class);
                Bundle b = new Bundle();
                b.putString("id", employees.get(position).getId());
                b.putString("name", employees.get(position).getName());
                b.putString("surname", employees.get(position).getSurname());
                b.putString("rate", employees.get(position).getRate());
                b.putString("phone", employees.get(position).getBirth());
                b.putString("experience", employees.get(position).getExperience());
                b.putString("owner", employees.get(position).getOwner());
                b.putString("station", station);
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadFlashcardsFromDatabase() {

        Cursor cursor = dataBase.getAllEmployees(owner);

        if (cursor.moveToFirst()) {
            do {
                employees.add(new Employee(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                ));
            } while (cursor.moveToNext());

            employeeAdapter = new EmployeeAdapter(this, employees);
            employeeAdapter.notifyDataSetChanged();
            employeesListView.setAdapter(employeeAdapter);
        }
    }
}