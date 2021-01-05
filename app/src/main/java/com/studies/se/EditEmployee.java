package com.studies.se;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditEmployee extends AppCompatActivity {

    private EmployeeDB dataBase;
    private TextView nameText;
    private TextView surnameText;
    private TextView birthText;
    private TextView experienceText;

    private String id;
    private String name;
    private String surname;
    private String rate;
    private String experience;
    private String birth;
    private String owner;

    private String nameNew;
    private String surnameNew;
    private String birthNew;
    private String experienceNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);

        dataBase = new EmployeeDB(this);

        nameText = (TextView) findViewById(R.id.nameEdit);
        surnameText = (TextView) findViewById(R.id.surnameEdit);
        birthText = (TextView) findViewById(R.id.birthEdit);
        experienceText = (TextView) findViewById(R.id.experienceEdit);

        Bundle b = getIntent().getExtras();
        if(b != null)
        {
            id = b.getString("id");
            name = b.getString("name");
            surname = b.getString("surname");
            rate = b.getString("rate");
            experience = b.getString("experience");
            birth = b.getString("birth");
            owner = b.getString("owner");
        }

        Button editButton = (Button) findViewById(R.id.buttonEditEmployee);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameNew = (nameText.getText().toString().equals("")) ? name : nameText.getText().toString();
                surnameNew = (surnameText.getText().toString().equals("")) ? surname : surnameText.getText().toString();
                birthNew = (birthText.getText().toString().equals("")) ? rate : birthText.getText().toString();
                experienceNew = (experienceText.getText().toString().equals("")) ? rate : experienceText.getText().toString();

                if (dataBase.updateEmployee(id, nameNew, surnameNew, rate, birthNew, experienceNew,owner))
                {
                    startActivity(new Intent(EditEmployee.this, Workers.class));
                }
            }
        });
    }

}