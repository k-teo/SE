package com.studies.se;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEmployee extends AppCompatActivity {


    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText phoneEditText;
    private EditText experienceEditText;

    private String name;
    private String surname;
    private String phone;
    private String experience;

    private int idInt;
    private String id;


    private String owner;
    private String station;
    private EmployeeDB dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        dataBase = new EmployeeDB(this);

        nameEditText = (EditText) findViewById(R.id.nameAdd);
        surnameEditText = (EditText) findViewById(R.id.surnameAdd);
        phoneEditText = (EditText) findViewById(R.id.phoneAdd);
        experienceEditText = (EditText) findViewById(R.id.experienceAdd);

        Bundle b = getIntent().getExtras();
        if(b != null)
        {
            owner = b.getString("owner");
            station = b.getString("station");
        }

        Button addButton = (Button) findViewById(R.id.buttonAddEmployee);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = (nameEditText.getText().toString().equals(""))? null : nameEditText.getText().toString();
                surname = (surnameEditText.getText().toString().equals(""))? null : surnameEditText.getText().toString();
                phone = (phoneEditText.getText().toString().equals(""))? null : phoneEditText.getText().toString();
                experience = (experienceEditText.getText().toString().equals(""))? null : experienceEditText.getText().toString();
                findId();

                if (dataBase.addEmployee(id, name, surname, null, phone, experience, owner))
                {
                    Toast.makeText(AddEmployee.this, "Employee Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddEmployee.this, Employees.class);
                    Bundle b = new Bundle();
                    b.putString("owner", owner);
                    b.putString("station", station);
                    intent.putExtras(b);
                    startActivity(intent);
                }
                else
                if (name == null && surname == null)
                    Toast.makeText(AddEmployee.this, "Name and surname cannot be empty", Toast.LENGTH_SHORT).show();
                else if (name == null)
                    Toast.makeText(AddEmployee.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                else if (surname == null)
                    Toast.makeText(AddEmployee.this, "Surname cannot be empty", Toast.LENGTH_SHORT).show();
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
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
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