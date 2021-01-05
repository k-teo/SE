package com.octolearn.se;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Workers extends AppCompatActivity {

    private ListView employeesListView;
    private ArrayList<Employee> employees;
    private EmployeeAdapter employeeAdapter;
    private EmployeeDB dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers);

        dataBase = new EmployeeDB(this);
        employeesListView = (ListView) findViewById(R.id.employeeListView);
        employees = new ArrayList<>();

        loadFlashcardsFromDatabase();

        Button button = (Button) findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Workers.this, AddWorker.class));
            }
        });

        employeesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Workers.this, WorkerInfo.class);
                Bundle b = new Bundle();
                b.putString("id", employees.get(position).getId());
                b.putString("name", employees.get(position).getName());
                b.putString("surname", employees.get(position).getSurname());
                b.putString("rate", employees.get(position).getRate());
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadFlashcardsFromDatabase() {
        Cursor cursor = dataBase.getAllEmployees();

        if (cursor.moveToFirst()) {
            do {
                employees.add(new Employee(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            } while (cursor.moveToNext());

            employeeAdapter = new EmployeeAdapter(this, employees);
            employeeAdapter.notifyDataSetChanged();
            employeesListView.setAdapter(employeeAdapter);
        }
    }
}