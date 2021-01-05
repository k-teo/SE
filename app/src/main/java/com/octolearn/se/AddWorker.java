package com.octolearn.se;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddWorker extends AppCompatActivity {


    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText rateEditText;

    private String name;
    private String surname;
    private String rate;
    private int idInt;
    private String id;

    private String owner = "1";
    private EmployeeDB dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);

        dataBase = new EmployeeDB(this);

        nameEditText = (EditText) findViewById(R.id.nameAdd);
        surnameEditText = (EditText) findViewById(R.id.surnameAdd);
        rateEditText = (EditText) findViewById(R.id.rateAdd);

        Button addButton = (Button) findViewById(R.id.buttonAddEmployee);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (nameEditText.getText().toString().equals(""))? null : nameEditText.getText().toString();
                surname = (surnameEditText.getText().toString().equals(""))? null : surnameEditText.getText().toString();
                rate = (rateEditText.getText().toString().equals(""))? null : rateEditText.getText().toString();
                findId();

                if (dataBase.addEmployee(id, name, surname, rate, owner))
                {
                    Toast.makeText(AddWorker.this, "Employee Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddWorker.this, Workers.class));
                }
                else
                if (name == null && surname == null)
                    Toast.makeText(AddWorker.this, "Name and surname cannot be empty", Toast.LENGTH_SHORT).show();
                else if (name == null)
                    Toast.makeText(AddWorker.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                else if (surname == null)
                    Toast.makeText(AddWorker.this, "Surname cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findId(){
        Cursor cursor = dataBase.getAllEmployees();
        Employee employee;
        if (cursor.moveToLast()) {
            employee = new Employee(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );

            String lastId = employee.getId();
            idInt = Integer.parseInt(lastId) + 1;
        }
        else {
            idInt = 1;
        }
        id = Integer.toString(idInt);
    }
}